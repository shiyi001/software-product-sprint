function sayHello() {
    fetch("/data").then(response => response.json()).then((comments) => {
        console.log(comments);
        const statsListElement = document.getElementById('comments-container');
        statsListElement.innerHTML = '';
        for (var comment_id in comments) {
            statsListElement.appendChild(createListElement(comments[comment_id]));
        }
    });
}

/** Creates an <li> element containing text. */
function createListElement(text) {
  const liElement = document.createElement('li');
  liElement.innerText = text;
  return liElement;
}