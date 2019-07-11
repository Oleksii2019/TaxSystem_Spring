angular.module("login_form",[])
    .controller("AppCtrl", function ($scope, $http) {
        $('#goBtn').prop('disabled',true);
        $scope.auth = {};
        let resultMessageEl = document.getElementById('resultMessage');
        let exampleInputLoginEl = document.getElementById('exampleInputLogin1');
        let exampleInputPasswordEl = document.getElementById('exampleInputPassword1');
        let exampleInputLoginLabel = document.getElementById('exampleInputLoginLabel');
        let exampleInputPasswordLabel = document.getElementById('exampleInputPasswordLabel');
        let messageLoginOk = document.getElementById('messageLoginOK').innerText;
        let messageLoginErr = document.getElementById('messageLoginErr').innerText;
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
                url: "/login_natural_person",
                data: $.param(auth),
                headers: { "Content-Type" : "application/x-www-form-urlencoded" }
            }).then(
                (data) => {
                    $('#goBtn').prop('disabled',false);
                    resultMessageEl.style.color = 'green';
                    $scope.message = messageLoginOk; //"Доступ разрешен";
                    exampleInputLoginEl.value = '';
                    exampleInputPasswordEl.value = '';
                    console.log("Ok");
                },
                (error) => {
                    $('#goBtn').prop('disabled',true);
                    exampleInputLoginLabel.style.color = 'red';
                    exampleInputPasswordLabel.style.color = 'red';
                    resultMessageEl.style.color = 'red';
                    exampleInputLoginEl.value = '';
                    exampleInputPasswordEl.value = '';
                    $scope.message = messageLoginErr; //"При авторизации произошла ошибка"
                    console.log("Ex");
                }
            );
        }
    });