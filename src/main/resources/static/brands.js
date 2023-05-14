import { getBrandsList } from "./scripts/brandsHelper.js";

getBrandsList();

document.getElementById("applyFilters").addEventListener("click", () => {
  const nameSearch = document.getElementById("name").value;
  const filters = {};

  if(nameSearch != '') filters.name = nameSearch;

  getBrandsList(filters);
});
