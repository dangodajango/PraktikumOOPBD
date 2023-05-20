import { getCategoriesList } from "./scripts/categoriesHelper.js";

getCategoriesList();

document.getElementById("applyFilters").addEventListener("click", () => {
  const titleSearch = document.getElementById("title").value;
  const filters = {};

  if(titleSearch != '') filters.title = titleSearch;

  getCategoriesList(filters)
});

