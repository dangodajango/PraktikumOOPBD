
const setResultsCount = (data) => {
  const count = typeof data === 'number' ? data : data.length;
  document.getElementById("resultsCount").innerHTML = count;
  document.getElementById("noResults").style.display = count === 0 ? 'block' : 'none';
}

export { setResultsCount }