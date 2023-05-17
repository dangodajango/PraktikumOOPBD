
const setResultsCount = (count) => {
  document.getElementById("resultsCount").innerHTML = count;
  document.getElementById("noResults").style.display = count === 0 ? 'block' : 'none';
}

export { setResultsCount }