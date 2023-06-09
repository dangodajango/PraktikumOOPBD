import { getBrandsDropdown } from "./scripts/brandsHelper.js";
import { getCategoriesDropdown } from "./scripts/categoriesHelper.js";
import { deleteOnClick, showErrors } from "./scripts/formsHelper.js";
import { renderShoeDetails } from "./scripts/shoesHelper.js";
import { deleteConfirmed, get, handleError, post, put } from "./scripts/httpService.js";

const id = new URLSearchParams(window.location.search).get('id');

const imageURL = document.getElementById("imageURL");
const name = document.getElementById("name");
const imagePreview = document.getElementById("imagePreview");
const sizesMin = document.getElementById("sizesMin");
const sizesMax = document.getElementById("sizesMax");
const price = document.getElementById("price");
const brandsDropdown = document.getElementById("brandsDropdown");

const defaultImg = 'https://www.generationsforpeace.org/wp-content/uploads/2018/03/empty.jpg';

imageURL.addEventListener("input", () => {
  console.log(imageURL)
  imagePreview.style.backgroundImage = `url(${imageURL.value})` ?? defaultImg;
})

if(id){

  const response = await get('/shoes/' + id);
  const shoe = (await response.json());

  console.log('fetched shoe', shoe);
  
  /* View shoe */

  document.getElementById("viewShoe").innerHTML = renderShoeDetails(shoe);
  imagePreview.style.backgroundImage = `url(${shoe.imageURL})`;

  document.getElementById("openEdit").addEventListener("click", () => {
    document.getElementById("viewShoe").style.display = 'none';
    document.getElementById("editShoe").style.display = 'block';
  });

  document.getElementById("deleteShoe").addEventListener("click", () => deleteOnClick(id, () => {
    deleteConfirmed(`/shoes/${id}`).then((response) => {
      redirectToShoes();
    }, handleError)
  }))

  /* Edit shoe */

  name.value = shoe.name;
  imagePreview.style.backgroundImage = `url(${shoe.imageURL})`;
  imageURL.value = shoe.imageURL;
  sizesMin.value = shoe.minSize;
  sizesMax.value = shoe.maxSize;
  price.value = shoe.price;

  getBrandsDropdown(false, () => {
    brandsDropdown.value = shoe.brandId;
   });

  getCategoriesDropdown(() => {
      for(let i = 0; i < shoe.categoryIds.length; i++){
        const selectedOption = document.querySelector(`#categoriesDropdown>option[value="${shoe.categoryIds[i]}"]`);
        if(selectedOption) selectedOption.selected = true;
      }
  });

}
else {
  document.getElementById("viewShoe").style.display = 'none';
  document.getElementById("editShoe").style.display = 'block';

  imagePreview.style.backgroundImage = `url('${defaultImg}')`

  document.querySelector("#editShoe>h3").innerHTML = 'Create shoe';
  getBrandsDropdown(false);
  getCategoriesDropdown();

}

const redirectToShoes = () => {
  window.location.href = 'index';
}

const validate = (data) => {
  const errors = [];
  if(data.name.length < 5) errors.push('name');
  if(data.imageURL.length < 5) errors.push('imageURL');
  if(data.price < 1) errors.push('price');
  if(data.minSize === '' || data.maxSize === '' || Number(data.minSize) >= Number(data.maxSize) || Number(data.minSize) < 1) errors.push('sizes');
  if(data.categoryIds.length === 0) errors.push('categories');

  showErrors(errors);
  return errors.length === 0;
}

document.getElementById("save").addEventListener("click", () => {

  let categoryIds = [];
  const options = document.querySelectorAll("#categoriesDropdown>option");
  for(let i = 0; i < options.length; i++){
    if(options[i].selected) categoryIds.push(options[i].value);
  }

  const newShoeData = {
    name: name.value,
    imageURL: imageURL.value,
    minSize: Number(sizesMin.value),
    maxSize: Number(sizesMax.value),
    price: Number(price.value),
    brandId: brandsDropdown.value,
    categoryIds: categoryIds,
  }

  console.log('saved', newShoeData);

  const isValid = validate(newShoeData);
  console.log('is valid:', isValid);
  if(isValid){
    if(id){
      put('/shoes/' + id, newShoeData).then((data) => {
        redirectToShoes();
      }, handleError)
    }
    else {
      post('/shoes/create', newShoeData).then((data) => {
        redirectToShoes();
      }, handleError)
    }
  }
});

document.getElementById("cancel").addEventListener("click", () => {
  redirectToShoes();
});