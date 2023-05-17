import { get, handleError, post, put } from "./scripts/httpService.js";
import { showErrors } from "./scripts/formsHelper.js";

const id = new URLSearchParams(window.location.search).get('id');

if(id){

  const response = await get('/' + id);
  const brand = (await response.json()).data;
  console.log('fetched brand', brand);

  document.getElementById("name").value = brand.name;
  document.getElementById("description").value = brand.description;
}
else {
  document.querySelector("#editBrand>h3").innerHTML = 'Create brand';
}

const redirectToBrands = () => {
  window.location.href = 'brands';
}

const validate = (data) => {
  const errors = [];
  if(data.name.length === 0 || data.name.length > 100) errors.push('name');
  if(data.description.length < 5 || data.description.length > 100) errors.push('description');

  showErrors(errors);
  return errors.length === 0;
}

document.getElementById("save").addEventListener("click", () => {
  const newBrandData = {
    name: document.getElementById("name").value,
    description: document.getElementById("description").value
  }
  console.log('saved', newBrandData);

  const isValid = validate(newBrandData);
  console.log('is valid:', isValid);
  if(isValid){
    if(id){
      put('/' + id, newBrandData).then((data) => {
        redirectToBrands();
      }, handleError)
    }
    else {
      post("/brands", newBrandData).then((data) => {
        redirectToBrands();
      }, handleError)
    }
  }
});

document.getElementById("cancel").addEventListener("click", () => {
  redirectToBrands();
});