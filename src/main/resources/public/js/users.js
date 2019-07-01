angular.module("users_form",[])
    .controller("UserCtrl", ["$scope", "$http", function ($scope, $http) {
        console.log("userCtrl")
        $scope.users = [];
        $scope.getUsers = function(){
            $http({
                method: "GET",
                url: "not_format/users",
                headers: { "Content-Type" : "application/json" }
            }).then(
                function(data) {
                    $scope.users = data.data; // .users
                    console.log(data.data);
                },
                function(error) {
                    console.log("userCtrl error")
                }
            );
        }
    }]);