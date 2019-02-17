var app = angular.module('app',[]);

app.controller('SddMapController', ['$scope','$compile','SddMapService', function ($scope,$compile,SddMapService) {

// in controller init map
    $scope.init = function () {
        SddMapService.getAllStores()
            .then(function success(response){
                    console.log('Load all stores@init==>'+ response.data);
                    $scope.stores = angular.fromJson(response.data);
                    $scope.message='';
                    $scope.errorMessage = '';
                    $scope.map = new google.maps.Map(document.getElementById('map'), {
                        zoom: 4,
                        center: { lat: $scope.stores[0].storeLatLng.lat, lng: $scope.stores[0].storeLatLng.lng },
                        mapTypeId: google.maps.MapTypeId.ROADMAP
                    });
                    var infowindow = new google.maps.InfoWindow();

                    console.log('Generating map->');
                    for(var i=0; i <  $scope.stores.length; i++){
                      //  alert('$scope.stores[i].storeLatLng.lat:'+ $scope.stores[i].storeLatLng.lat +'$scope.stores[i].storeLatLng.lng:'+$scope.stores[i].storeLatLng.lng);
                        var content= $scope.stores[i].storeName;
                        var marker = new google.maps.Marker({
                            position: new google.maps.LatLng($scope.stores[i].storeLatLng.lat, $scope.stores[i].storeLatLng.lng),
                            map: $scope.map,
                            title: $scope.stores[i].storeName
                            });

                        google.maps.event.addListener(marker,'click', (function(marker,content,infowindow){
                            return function() {
                                infowindow.setContent(content);
                                infowindow.open(map,marker);
                            };
                        })(marker,content,infowindow));

                    }
                    console.log('Map Generation Done');
                    },
                function error (response ){
                    $scope.message='';
                    $scope.errorMessage = 'Error getting stores';
                });

    };

}]);

app.service('SddMapService',['$http', function ($http) {

    this.getAllStores = function getAllStores(){
        return $http({
            method: 'GET',
            url: '/api/stores'
        });
    }

}]);