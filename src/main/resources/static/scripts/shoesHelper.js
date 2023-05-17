import { getUrlWithParams } from "./filtersHelper.js";
import { get } from "./httpService.js";
import { setResultsCount } from "./paginatorHelper.js";

const getShoeList = (filters = {}) => {

  let shoeItems = '';

  const urlWithParams = getUrlWithParams('/shoes/all', filters);

  get(urlWithParams).then(async (response) => {
    const shoes = (await response.json());
    console.log(shoes);

    let shoeItems = '';

    for(let i = 0; i < shoes.length; i++){
        shoeItems += renderShoeItem(shoes[i]);
    }

    document.getElementById("shoeList").innerHTML = shoeItems;

    shoeItems = document.getElementsByClassName("shoeItem");

    for(let i = 0; i < shoeItems.length; i++) {
        shoeItems[i].addEventListener("click", () => {
          window.location.href = "shoe?id=" + shoeItems[i].getAttribute('id');
        })
    }
    setResultsCount(shoes.length);
   });
}


const renderShoeItem = (shoe) => {
  return `<div class="shoeItem" id="${shoe.id}">
    <div class="shoeItem--image" style="background-image: url(${shoe.imageURL})"></div>
    <div class="shoeItem--label">${shoe.name}</div>
    <div class="shoeItem--price">${shoe.price}lv</div>
  </div>`
}


const renderShoeDetails = (shoe) => {
  return `<div id="shoeDetails">
    <div class="shoeDetails--image" style="background-image: url(${shoe.imageURL})"></div>
    <div id="shoeDetails--leftpanel">
      <div id="shoeDetails--label">${shoe.name}</div>
      <div id="shoeDetails--sizes">Comes in sizes: ${shoe.sizes.join(' - ')} (EU)</div>
      <div id="shoeDetails--gender">Suitable for: ${shoe.gender.join(', ').replace('m', 'Men').replace('f', 'Women')}</div>
      <div id="shoeDetails--price">${shoe.price}lv</div>
      
      <div class="note" data-id="${shoe.id}">Click again to confirm delete</div>
      <button id="openEdit">Edit</button><button id="deleteShoe" data-id="${shoe.id}">Delete</button>
    </div>
  </div>`
}


export { getShoeList, renderShoeItem, renderShoeDetails }