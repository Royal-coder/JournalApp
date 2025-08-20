// This section contains code for displaying different section under men.html

const shirtlink  = document.getElementById("shirtlink");
const tshirtlink = document.getElementById("tshirtlink");
const shirtsection = document.getElementById("shirtsection");
const tshirtsection = document.getElementById("tshirtsection");


// show shirt section and hide tshirt section
shirtlink.addEventListener("click", (event) => {
    event.preventDefault(); //prevent page reload
    shirtsection.style.display = "block";
    tshirtsection.style.display = "none";
});

// show tshirt section and hide shirt section
tshirtlink.addEventListener("click", (event) => {
    event.preventDefault(); //prevent page reload
    shirtsection.style.display = "none";
    tshirtsection.style.display = "block";
});

shirtsection.style.display = "block";
