google.charts.load('current', {'packages':['corechart']});
google.charts.setOnLoadCallback(drawChart);

/** Creates a chart and adds it to the page. */
function drawChart() {
    fetch('./cat-data').then(response => response.json())
    .then((catVotes) => {
    const data = new google.visualization.DataTable();
    data.addColumn('string', 'Cats');
    data.addColumn('number', 'Votes');
    Object.keys(catVotes).forEach((cat) => {
        data.addRow([cat, catVotes[cat]]);
    });

    const options = {
        'title': 'Favorite Cats',
        'width':500,
        'height':400
    };

    const chart = new google.visualization.ColumnChart(
        document.getElementById('chart-container'));
    chart.draw(data, options);
    });
}

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