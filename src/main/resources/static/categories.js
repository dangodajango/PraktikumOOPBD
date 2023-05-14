import { getCategoriesList } from "./scripts/categoriesHelper.js";

getCategoriesList();

document.getElementById("applyFilters").addEventListener("click", () => {
  const nameSearch = document.getElementById("name").value;
  const filters = {
    name: nameSearch
  }

  console.log(filters);

  getCategoriesList(filters)
});

