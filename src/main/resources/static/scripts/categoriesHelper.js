const mockCategories = [
  {id: '1', name: 'Sports', description: 'Running shoes, hiking shoes, etc'},
  {id: '2', name: 'Casual', description: 'For walks in parks'},
  {id: '3', name: 'Elegant', description: 'For a formal dinner'}
]

import { getUrlWithParams } from "./filtersHelper.js";
import { get } from "./httpService.js";
import { setResultsCount } from "./paginatorHelper.js";

const getCategoriesList = (filters = {}) => {

  const urlWithParams = getUrlWithParams('/categories', filters);

  get(urlWithParams).then(async (response) => {
    const reponseJSON = (await response.json());
    const categories = reponseJSON.data;

      let categoryItems = '';

      for(let i = 0; i < categories.length; i++){
        categoryItems += renderCategoryItem(categories[i])
      }

      document.getElementById("categoriesList").innerHTML = categoryItems;

      const editButtons = document.getElementsByClassName("edit");

      for(let i = 0; i < editButtons.length; i++) {
        editButtons[i].addEventListener("click", () => {
          window.location.href = "category.html?id=" + editButtons[i].getAttribute('id');
        })
      }
        setResultsCount(categories.length);
    });
}

const getCategoriesDropdown = () => {
  let categoryItems = '';

  for(let i = 0; i < mockCategories.length; i++){
    categoryItems += `<option value="${mockCategories[i].id}">${mockCategories[i].name}</option>`;
  }
  
  document.getElementById("categoriesDropdown").innerHTML = categoryItems;
}

const renderCategoryItem = (category) => {
  return `<div class="categoryItem" id="${category.id}">
  <div>${category.name}</div>
  <div><button class="edit" id="${category.id}">Edit</button><button class="delete">Delete</button></div>
  </div>`;
}


const getCategoryById = (categoryId) => {
  return mockCategories.filter((c) => c.id === categoryId)[0];
}

export { getCategoriesList, renderCategoryItem, getCategoryById, getCategoriesDropdown }