angular.module("users_form", [])
    .controller("UserCtrl", ["$scope", "$http", function ($scope, $http) {
        console.log("userCtrl")
        $scope.getUsers = function() {
            $http({
                method: "GET",
                url: "/payer_report_list/creation",
                headers: {"Content-Type": "application/json"}
            }).then(
                function (data) {
                    console.log("Read");
                    console.log(data.data);
                },
                function (error) {
                    console.log("userCtrl error")
                }
            );
        }
    }]);