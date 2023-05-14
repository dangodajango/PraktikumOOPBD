const mockBrands = [
  {id: '1', name: 'Puma', description: 'Running shoes'},
  {id: '2', name: 'Nike', description: 'Sports shoes'},
  {id: '3', name: 'Tamaris', description: 'For a formal dinners'},
  {id: '4', name: 'Converse', description: 'Cool kid'}
]

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
        window.location.href = "brand.html?id=" + brandItems[i].getAttribute("data-id");
      })
    }
    setResultsCount(brands.length);
  });
}


const getBrandsDropdown = (search) => {
  let brandItems = search ? '<option value="">All</option>' : '';

  for(let i = 0; i < mockBrands.length; i++){
    brandItems += `<option value="${mockBrands[i].id}">${mockBrands[i].name}</option>`;
  }

  document.getElementById("brandsDropdown").innerHTML = brandItems;
}


const renderBrandItem = (brand) => {
  return `<div class="brandItem" id="${brand.id}">
  <div>${brand.name}</div>
  <div><button class="edit" id="${brand.id}">Edit</button><button class="delete">Delete</button></div>
  </div>`;
}

const getBrandById = (brandId) => {
  return mockBrands.filter((c) => c.id === brandId)[0];
}

export { getBrandsList, renderBrandItem, getBrandById, getBrandsDropdown }