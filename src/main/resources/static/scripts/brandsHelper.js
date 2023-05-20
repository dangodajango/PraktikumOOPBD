import { getUrlWithParams } from "./filtersHelper.js";
import { get, deleteConfirmed, handleError } from "./httpService.js";
import { deleteOnClick, showErrors } from "./formsHelper.js";
import { setResultsCount } from "./paginatorHelper.js";

const getBrandsList = (filters = {}) => {

  const urlWithParams = getUrlWithParams('/brands/all', filters);

  get(urlWithParams).then(async (response) => {
  let brandItems = '';
    const brands = (await response.json());

    for(let i = 0; i < brands.length; i++){
        brandItems += renderBrandItem(brands[i])
    }

    document.getElementById("brandsList").innerHTML = brandItems;


      const editButtons = document.getElementsByClassName("edit");

      for(let i = 0; i < editButtons.length; i++) {
        console.log(editButtons[i]);
        editButtons[i].addEventListener("click", () => {
          window.location.href = "brand?id=" + editButtons[i].getAttribute("data-id");
        })
      }

    const deleteButtons = document.getElementsByClassName("delete");

    for(let i = 0; i < deleteButtons.length; i++) {
        const id = deleteButtons[i].getAttribute("data-id");
        deleteButtons[i].addEventListener("click", () => deleteOnClick(id, () => {
          deleteConfirmed(`/brands/${id}`).then((response) => {
            getBrandsList();
          }, handleError)
        }))
    }

    setResultsCount(brands.length);
  });
}


const getBrandsDropdown = (search, afterLoading) => {
  let brandItems = search ? '<option value="">All</option>' : '';

  get('/brands/all').then(async (response) => {
    const brands = (await response.json());

    if(brands.length === 0 && !search) {
        if(window.confirm("There are no brands. Please create a brand first")) window.location.href = "brand";
    }

    for(let i = 0; i < brands.length; i++){
        brandItems += `<option value="${brands[i].id}">${brands[i].name}</option>`;
    }

    document.getElementById("brandsDropdown").innerHTML = brandItems;
    // if there is passed a function that should execute after loading (selecting items in the dropdown for example)
    if(afterLoading){
      afterLoading();
    }
  });
}


const renderBrandItem = (brand) => {
  return `<div class="brandItem" id="${brand.id}">
  <div>${brand.name}</div>
  <div class="note" data-id="${brand.id}">Click again to confirm delete</div>
   <div>
      <button class="edit" data-id="${brand.id}">Edit</button>
      <button class="delete" data-id="${brand.id}">Delete</button>
    </div>
  </div>`;
}

export { getBrandsList, renderBrandItem, getBrandsDropdown }