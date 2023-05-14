const getUrlWithParams = (url, filters) => {
  console.log(url, filters);

  const hasFilters = Object.keys(filters).length > 0;

  return url + (hasFilters ? '?' + new URLSearchParams(filters).toString() : '');
}

export { getUrlWithParams }