let select = document.getElementById("listofreport");

(function() {
    let button1 = document.getElementById("accBtn");
    let button2 = document.getElementById("reclBtn");
    select.addEventListener("change", function(e) {
        button1.disabled = false;
        button2.disabled = false;
    });
})();

angular.module("users_form", [])
    .controller("UserCtrl", ["$scope", "$http", function ($scope, $http) {
        console.log("userCtrl")
        $scope.getReports = function() {
            $http({
                method: "GET",
                url: "not_format/reports_officer",
                headers: {"Content-Type": "application/json"}
            }).then(
                function (data) {
                    console.log("Read");
                    for (let i = 0; i < Object.keys(data.data).length; i++) {
                        let option = document.createElement("option");
                        let temp = (i + 1).toString() + ". " + "Report from "
                            + data.data[i].taxpayerName.toString() + " "
                            + data.data[i].creationTime.toString();
                        option.setAttribute("value",
                            data.data[i].taxpayerLogin.toString() + ":-"
                            + data.data[i].creationTime.toString());
                        option.innerHTML = temp.toString();
                        select.appendChild(option);
                    }
                    console.log(Object.keys(data.data).length);
                    console.log(data.data[0].name);
                    console.log(data.data);
                },
                function (error) {
                    console.log("userCtrl error")
                }
            );
        }
    }]);
