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
          window.location.href = "category?id=" + editButtons[i].getAttribute('id');
        })
      }

        const deleteButtons = document.getElementsByClassName("delete");

        for(let i = 0; i < deleteButtons.length; i++) {
            const id = deleteButtons[i].getAttribute("data-id");
            deleteButtons[i].addEventListener("click", () => deleteOnClick(id, () => {
              deleteConfirmed(`/categories/${id}`).then((response) => {
                getCategoriesList();
              }, handleError)
            }))
        }

        setResultsCount(categories.length);
    });
}

const getCategoriesDropdown = () => {
  let categoryItems = '';

  get('/categories').then(async (response) => {
    const reponseJSON = (await response.json());
    const categories = reponseJSON.data;
      for(let i = 0; i < categories.length; i++){
        categoryItems += `<option value="${categories[i].id}">${categories[i].name}</option>`;
      }

    document.getElementById("categoriesDropdown").innerHTML = categoryItems;
  });
}

const renderCategoryItem = (category) => {
  return `<div class="categoryItem" id="${category.id}">
  <div>${category.name}</div>
  <div><button class="edit" id="${category.id}">Edit</button><button class="delete">Delete</button></div>
  </div>`;
}


export { getCategoriesList, renderCategoryItem, getCategoriesDropdown }