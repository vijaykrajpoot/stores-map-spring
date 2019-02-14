var app = angular.module('app',[]);

app.controller('UserCRUDCtrl', ['$scope','$compile','UserCRUDService', function ($scope,$compile,UserCRUDService) {

    $scope.updateUser = function () {
        UserCRUDService.updateUser($scope.user.id,$scope.user.name,$scope.user.email)
          .then(function success(response){
              $scope.message = 'User data updated!';
              $scope.errorMessage = '';
          },
          function error(response){
              $scope.errorMessage = 'Error updating user!';
              $scope.message = '';
          });
    }

    $scope.getUser = function () {
        var id = $scope.user.id;
        UserCRUDService.getUser($scope.user.id)
          .then(function success(response){
              $scope.user = response.data;
              $scope.user.id = id;
              $scope.message='';
              $scope.errorMessage = '';
          },
          function error (response ){
              $scope.message = '';
              if (response.status === 404){
                  $scope.errorMessage = 'User not found!';
              }
              else {
                  $scope.errorMessage = "Error getting user!";
              }
          });
    }

    $scope.addUser = function () {
        if ($scope.user != null && $scope.user.name) {
            UserCRUDService.addUser($scope.user.name, $scope.user.email)
              .then (function success(response){
                  $scope.message = 'User added!';
                  $scope.errorMessage = '';
              },
              function error(response){
                  $scope.errorMessage = 'Error adding user!';
                  $scope.message = '';
            });
        }
        else {
            $scope.errorMessage = 'Please enter a name!';
            $scope.message = '';
        }
    }

    $scope.deleteUser = function () {
        UserCRUDService.deleteUser($scope.user.id)
          .then (function success(response){
              $scope.message = 'User deleted!';
              $scope.user = null;
              $scope.errorMessage='';
          },
          function error(response){
              $scope.errorMessage = 'Error deleting user!';
              $scope.message='';
          })
    }

    $scope.getAllUsers = function () {
        UserCRUDService.getAllUsers()
          .then(function success(response){

              $scope.users = response.data._embedded.users;
              $scope.message='';
              $scope.errorMessage = '';
          },
          function error (response ){
              $scope.message='';
              $scope.errorMessage = 'Error getting users!';
          });
    }



    $scope.getAllStores = function () {
        UserCRUDService.getAllStores()
            .then(function success(response){
                    console.log('Load all stores'+ response.data);
                    $scope.stores = angular.fromJson(response.data);
               //     alert(stores.)
                    $scope.message='';
                    $scope.errorMessage = '';
                },
                function error (response ){
                    $scope.message='';
                    $scope.errorMessage = 'Error getting stores';
                });
    }


// in controller init map
    $scope.init = function () {
        UserCRUDService.getAllStores()
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

app.service('UserCRUDService',['$http', function ($http) {

    this.getUser = function getUser(userId){
        return $http({
          method: 'GET',
          url: 'users/'+userId
        });
	}

    this.addUser = function addUser(name, email){
        return $http({
          method: 'POST',
          url: 'users',
          data: {name:name, email:email}
        });
    }

    this.deleteUser = function deleteUser(id){
        return $http({
          method: 'DELETE',
          url: 'users/'+id
        })
    }

    this.updateUser = function updateUser(id,name,email){
        return $http({
          method: 'PATCH',
          url: 'users/'+id,
          data: {name:name, email:email}
        })
    }

    this.getAllUsers = function getAllUsers(){
        return $http({
          method: 'GET',
          url: 'users'
        });
    }

    this.getAllStores = function getAllStores(){
        return $http({
            method: 'GET',
            url: '/api/stores'
        });
    }

}]);