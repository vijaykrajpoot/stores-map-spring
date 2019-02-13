<!DOCTYPE html>
<html ng-app="app">

<head>
    <meta charset="ISO-8859-1">
    <title>SDD - Map</title>
    <script
            src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular.min.js"></script>
    <script src="../view/app.js"></script>
    <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCAeq78kvgSx4px62xk93wCMkdm79gmG1c"></script>

</head>
<body>
<div ng-controller="UserCRUDCtrl" data-ng-init="init()">

    <br /> <br />

    <p style="color: green">{{message}}</p>
    <p style="color: red">{{errorMessage}}</p>

    <%--<a ng-click="getAllStores()">Get all Stores</a><br />--%>
    <%--<br /> <br />--%>
    <%--<div ng-repeat="store in stores">--%>
        <%--{{store.storeId}} {{store.storeName}}--%>
    <%--</div>--%>
    <%--<div id="map" style="height: 100%; width: 100%; position: relative; top: 0px; left: 0px; overflow:auto"></div>--%>

    <div id="map"></div>

</div>
</body>
</html>