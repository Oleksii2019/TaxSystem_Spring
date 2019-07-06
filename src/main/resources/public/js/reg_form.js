angular.module("registration_form",[])
    .controller("AppCtrl", function ($scope, $http) {
        $scope.auth = {};
        let resultMessageEl = document.getElementById('resultMessage');
        let exampleInputNameEl = document.getElementById('exampleInputName');
        let exampleInputLoginEl = document.getElementById('exampleInputLogin');
        let exampleInputPasswordEl = document.getElementById('exampleInputPassword');
        let inputNameLabel = document.getElementById('inputNameLabel');
        let inputLoginLabel = document.getElementById('inputLoginLabel');
        let inputPasswordLabel = document.getElementById('inputPasswordLabel');
        let messageRegOk = document.getElementById('messageOK').innerText;
        let messageRegErr = document.getElementById('messageErr').innerText;
        let messageRegDbErr = document.getElementById('messageDbErr').innerText;
        exampleInputNameEl.addEventListener('input', () => {
            exampleInputLoginEl.style.color = 'black';
            inputNameLabel.style.color = 'black';
            inputLoginLabel.style.color = 'black';
            inputPasswordLabel.style.color = 'black';
            $scope.message = '';
        });
        $scope.sendForm = function(auth){
            $http({
                method: "POST",
                url: "/reg_form",
                data: $.param(auth),
                headers: { "Content-Type" : "application/x-www-form-urlencoded" }
            }).then(
                (data) => {
                    console.log(auth);
                    resultMessageEl.style.color = 'green';
                    $scope.message = messageRegOk; //'Успешно зарегистрирован';
                    exampleInputNameEl.value = '';
                    exampleInputLoginEl.value = '';
                    exampleInputPasswordEl.value = '';
                },
                (error) => {
                    console.log("Ex");
                    console.log(error.data.message);
                    console.log(auth);
                    resultMessageEl.style.color = 'red';
                    $scope.message = messageRegErr; // 'При регистрации произошла ошибка';
                    if (error.data.message === "x_reg") {
                        exampleInputLoginEl.style.color = 'red';
                        exampleInputLoginEl.value = auth["login"];
                    }
                    if (error.data.message === "x_DB") {
                        $scope.message = messageRegDbErr; //'Ошибка при обоащении к базе данных';
                    }
                    // resultMessageEl.style.color = 'red';
                    // inputNameLabel.style.color = 'red';
                    // inputLoginLabel.style.color = 'red';
                    // inputPasswordLabel.style.color = 'red';
                    // exampleInputNameEl.value = '';
                    // exampleInputLoginEl.value = '';
                    // exampleInputPasswordEl.value = '';
                    // $scope.message = 'При регистрации произошла ошибка';
                }
            );
        }
    });