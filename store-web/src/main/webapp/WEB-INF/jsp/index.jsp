<!DOCTYPE html>
<html ng-app="app">

<head>
    <meta charset="ISO-8859-1">
    <title>SDD - Map</title>
    <link rel = "stylesheet" type = "text/css" href = "../view/style.css" />
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCAeq78kvgSx4px62xk93wCMkdm79gmG1c"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular.min.js"></script>
    <script src="../view/app.js"></script>
</head>
<body>
<div ng-controller="SddMapController" data-ng-init="init()">
   <div id="map"></div>
</div>



</body>
</html>