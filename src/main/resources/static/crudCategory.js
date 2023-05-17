import { get, handleError, post, put } from "./scripts/httpService.js";
import { showErrors } from "./scripts/formsHelper.js";

const id = new URLSearchParams(window.location.search).get('id');

if(id){

  const response = await get('/categories/' + id);
  const category = (await response.json());

  console.log('fetched category', category);

  document.getElementById("title").value = category.title;
  document.getElementById("description").value = category.description;
}
else {
  document.querySelector("#editCategory>h3").innerHTML = 'Create category';
}

const redirectToCategories = () => {
  window.location.href = 'categories';
}

const validate = (data) => {
  const errors = [];
  if(data.title.length < 5) errors.push('title');
  if(data.description.length < 5) errors.push('description');

  showErrors(errors);
  return errors.length === 0;
}

document.getElementById("save").addEventListener("click", () => {
  const newCategoryData = {
    title: document.getElementById("title").value,
    description: document.getElementById("description").value
  }

  console.log('saved', newCategoryData);

  const isValid = validate(newCategoryData);
  console.log('is valid:', isValid);
  if(isValid){
    if(id){
        put('/categories/' + id, newCategoryData).then((data) => {
            redirectToCategories();
          }, handleError)
        }
    else {
      post('/categories/create', newCategoryData).then((data) => {
        redirectToCategories();
      }, handleError)
    }
  }
});

document.getElementById("cancel").addEventListener("click", () => {
  redirectToCategories();
});