import { getUrlWithParams } from "./filtersHelper.js";
import { get } from "./httpService.js";
import { setResultsCount } from "./paginatorHelper.js";

const getBrandsList = (filters = {}) => {

  let brandItems = '';

  const urlWithParams = getUrlWithParams('/brands', filters);

  get(urlWithParams).then(async (response) => {
    const reponseJSON = (await response.json());
    const brands = reponseJSON.data;

    for(let i = 0; i < brands.length; i++){
    brandItems += renderBrandItem(brands[i])
    }

    document.getElementById("brandsList").innerHTML = brandItems;

    brandItems = document.getElementsByClassName("brandItem");

    for(let i = 0; i < brandItems.length; i++) {
      brandItems[i].addEventListener("click", () => {
        window.location.href = "brand?id=" + brandItems[i].getAttribute("data-id");
      })
    }
    setResultsCount(brands.length);
  });
}


const getBrandsDropdown = (search) => {
  let brandItems = search ? '<option value="">All</option>' : '';

  get('/brands').then(async (response) => {
    const reponseJSON = (await response.json());
    const brands = reponseJSON.data;

    for(let i = 0; i < brands.length; i++){
        brandItems += `<option value="${brands[i].id}">${brands[i].name}</option>`;
    }

    document.getElementById("brandsDropdown").innerHTML = brandItems;
   });
}


const renderBrandItem = (brand) => {
  return `<div class="brandItem" id="${brand.id}">
  <div>${brand.name}</div>
  <div><button class="edit" id="${brand.id}">Edit</button><button class="delete">Delete</button></div>
  </div>`;
}

export { getBrandsList, renderBrandItem, getBrandsDropdown }