import { getCategoriesList } from "./scripts/categoriesHelper.js";

getCategoriesList();

document.getElementById("applyFilters").addEventListener("click", () => {
  const nameSearch = document.getElementById("name").value;
  const filters = {};

  if(nameSearch != '') filters.name = nameSearch;

  getCategoriesList(filters)
});

