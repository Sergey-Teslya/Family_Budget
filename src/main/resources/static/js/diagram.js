
alert("test");

// Load the Visualization API and the corechart package.
google.charts.load('current', {'packages': ['corechart', 'bar']});

// Set a callback to run when the Google Visualization API is loaded.
google.charts.setOnLoadCallback(drawChart);
google.charts.setOnLoadCallback(testChart);

// Callback that creates and populates a data table,
// instantiates the pie chart, passes in the data and
// draws it.
function drawChart() {

    var jsonData = $.ajax({
        url: "/dataPieChartDiagramOneWeek",
        dataType: "json",
        async: false
    }).responseText;

    // Create the data table.
    var data = new google.visualization.DataTable(jsonData);

    // Set chart options
    var options = {
        'title': 'How Much Pizza I Ate Last Night',
        'width': 800,
        'height': 500,
        colors: ['#1e7e34', "#cc3300", "#856404"],
        legend: 'bottom',
        pieSliceText: 'none',
        pieHole: 0.8
    };

    // Instantiate and draw our chart, passing in some options.
    var chart = new google.visualization.PieChart(document.getElementById('chart'));
    chart.draw(data, options);
}

function testChart() {

    var jsonData = $.ajax({
        url: "/dataColumnChartDiagramOneWeek",
        dataType: "json",
        async: false
    }).responseText;

    // Create the data table.
    var data = new google.visualization.DataTable(jsonData);

    // Set chart options
    var options = {
        'title': 'How Much Pizza I Ate Last Night',
        'width': 800,
        'height': 500,
        colors: ['#1e7e34', "#cc3300", "#856404"]
    };

    // Instantiate and draw our chart, passing in some options.
    var chart = new google.visualization.ColumnChart(document.getElementById('chart_test'));
    chart.draw(data, options);
}