

// Load the Visualization API and the corechart package.
google.charts.load('current', {'packages': ['corechart', 'bar']});

// Set a callback to run when the Google Visualization API is loaded.
google.charts.setOnLoadCallback(PieChartDataOfTheDay);
google.charts.setOnLoadCallback(PieChartDataOfTheWeek);
google.charts.setOnLoadCallback(PieChartDataOfTheMonth);
google.charts.setOnLoadCallback(ColumnChartDataOfWeek);
google.charts.setOnLoadCallback(ColumnChartDataOfMonth);

// Callback that creates and populates a data table,
// instantiates the pie chart, passes in the data and
// draws it.

function PieChartDataOfTheDay() {

    var jsonData = $.ajax({
        url: "/dataPieChartDiagramOneDay",
        dataType: "json",
        async: false
    }).responseText;

    // Create the data table.
    var data = new google.visualization.DataTable(jsonData);

    // Set chart options
    var options = {
        colors: ['#e74a3b', '#f6c23e', '#858796', '#4e73df', '#1cc88a', '#36b9cc'],
        legend: 'bottom',
        pieSliceText: 'none',
        pieHole: 0.8
    };

    // Instantiate and draw our chart, passing in some options.
    var chart = new google.visualization.PieChart(document.getElementById('pie-chart-day'));
    chart.draw(data, options);
}

function PieChartDataOfTheWeek() {

    var jsonData = $.ajax({
        url: "/dataPieChartDiagramOneWeek",
        dataType: "json",
        async: false
    }).responseText;

    // Create the data table.
    var data = new google.visualization.DataTable(jsonData);

    // Set chart options
    var options = {
        colors: ['#e74a3b', '#f6c23e', '#858796', '#4e73df', '#1cc88a', '#36b9cc'],
        legend: 'bottom',
        pieSliceText: 'none',
        pieHole: 0.8
    };

    // Instantiate and draw our chart, passing in some options.
    var chart = new google.visualization.PieChart(document.getElementById('pie-chart-week'));
    chart.draw(data, options);
}

function PieChartDataOfTheMonth() {

    var jsonData = $.ajax({
        url: "/dataPieChartDiagramOneMonth",
        dataType: "json",
        async: false
    }).responseText;

    // Create the data table.
    var data = new google.visualization.DataTable(jsonData);

    // Set chart options
    var options = {
        colors: ['#e74a3b', '#f6c23e', '#858796', '#4e73df', '#1cc88a', '#36b9cc'],
        legend: 'bottom',
        pieSliceText: 'none',
        pieHole: 0.8
    };

    // Instantiate and draw our chart, passing in some options.
    var chart = new google.visualization.PieChart(document.getElementById('pie-chart-month'));
    chart.draw(data, options);
}

function ColumnChartDataOfWeek() {

    var jsonData = $.ajax({
        url: "/dataColumnChartDiagramOneWeek",
        dataType: "json",
        async: false
    }).responseText;

    // Create the data table.
    var data = new google.visualization.DataTable(jsonData);

    // Set chart options
    var options = {
        colors: ['#e74a3b', '#f6c23e', '#858796', '#4e73df', '#1cc88a', '#36b9cc']
    };

    // Instantiate and draw our chart, passing in some options.
    var chart = new google.visualization.ColumnChart(document.getElementById('column-chart-week'));
    chart.draw(data, options);
}

function ColumnChartDataOfMonth() {

    var jsonData = $.ajax({
        url: "/dataColumnChartDiagramMonth",
        dataType: "json",
        async: false
    }).responseText;

    // Create the data table.
    var data = new google.visualization.DataTable(jsonData);

    // Set chart options
    var options = {
        colors: ['#e74a3b', '#f6c23e', '#858796', '#4e73df', '#1cc88a', '#36b9cc']
    };

    // Instantiate and draw our chart, passing in some options.
    var chart = new google.visualization.ColumnChart(document.getElementById('column-chart-month'));
    chart.draw(data, options);
}