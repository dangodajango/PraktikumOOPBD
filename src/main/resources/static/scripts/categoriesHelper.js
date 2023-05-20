import { getUrlWithParams } from "./filtersHelper.js";
import { get, deleteConfirmed, handleError } from "./httpService.js";
import { deleteOnClick, showErrors } from "./formsHelper.js";
import { setResultsCount } from "./paginatorHelper.js";

const getCategoriesList = (filters = {}) => {

  const urlWithParams = getUrlWithParams('/categories/all', filters);

  get(urlWithParams).then(async (response) => {
    const categories = (await response.json());

      let categoryItems = '';

      for(let i = 0; i < categories.length; i++){
        categoryItems += renderCategoryItem(categories[i])
      }

      document.getElementById("categoriesList").innerHTML = categoryItems;

      const editButtons = document.getElementsByClassName("edit");

      for(let i = 0; i < editButtons.length; i++) {
        editButtons[i].addEventListener("click", () => {
          window.location.href = "category?id=" + editButtons[i].getAttribute('data-id');
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

const getCategoriesDropdown = (afterLoading) => {
  let categoryItems = '';

  get('/categories/all').then(async (response) => {
    const categories = (await response.json());

    if(categories.length === 0) {
        if(window.confirm("There are no categories. Please create a category first")) window.location.href = "category";
    }

    for(let i = 0; i < categories.length; i++){
        categoryItems += `<option value="${categories[i].id}">${categories[i].title}</option>`;
    }

    document.getElementById("categoriesDropdown").innerHTML = categoryItems;

    // if there is passed a function that should execute after loading (selecting items in the dropdown for example)
    if(afterLoading){
      afterLoading();
    }
  });
}

const renderCategoryItem = (category) => {
console.log(category)
  return `<div class="categoryItem" data-id="${category.id}">
  <div>${category.title}</div>
  <div class="note" data-id="${category.id}">Click again to confirm delete</div>
   <div>
      <button class="edit" data-id="${category.id}">Edit</button>
      <button class="delete" data-id="${category.id}">Delete</button>
    </div>
  </div>`;
}


export { getCategoriesList, renderCategoryItem, getCategoriesDropdown }