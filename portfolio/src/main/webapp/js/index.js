function sayHello() {
    fetch("/data").then(response => response.text()).then((helloString) => {
        document.getElementById('helloString').innerText = helloString;
    });
}
