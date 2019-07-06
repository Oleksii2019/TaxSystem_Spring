angular.module("login_form",[])
    .controller("AppCtrl", function ($scope, $http) {
        $scope.auth = {};
        let resultMessageEl = document.getElementById('resultMessage');
        let exampleInputLoginEl = document.getElementById('exampleInputLogin1');
        let exampleInputPasswordEl = document.getElementById('exampleInputPassword1');
        let exampleInputLoginLabel = document.getElementById('exampleInputLoginLabel');
        let exampleInputPasswordLabel = document.getElementById('exampleInputPasswordLabel');
        exampleInputLoginEl.addEventListener('input', () => {
            exampleInputLoginLabel.style.color = 'black';
            exampleInputPasswordLabel.style.color = 'black';
            exampleInputLoginEl.style.color = 'black';
            $scope.message = '';
            console.log($scope.auth);
        });
        $scope.sendForm = function(auth){
            $http({
                method: "POST",
                url: "/login",
                data: $.param(auth),
                headers: { "Content-Type" : "application/x-www-form-urlencoded" }
            }).then(
                (data) => {
                    resultMessageEl.style.color = 'green';
                    $scope.message = "Доступ разрешен";
                    exampleInputLoginEl.value = '';
                    exampleInputPasswordEl.value = '';
                    console.log("Ok");
                },
                (error) => {
                    exampleInputLoginLabel.style.color = 'red';
                    exampleInputPasswordLabel.style.color = 'red';
                    resultMessageEl.style.color = 'red';
                    exampleInputLoginEl.value = '';
                    exampleInputPasswordEl.value = '';
                    $scope.message = "При авторизации произошла ошибка"
                    console.log("Ex");
                }
            );
        }
    });