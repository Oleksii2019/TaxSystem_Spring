let select = document.getElementById("listofreport");

(function() {
    let button = document.getElementById("chBtn");
    select.addEventListener("change", function(e) {
        button.disabled = false;
    });
})();

angular.module("users_form", [])
    .controller("UserCtrl", ["$scope", "$http", function ($scope, $http) {
        console.log("userCtrl")
        $scope.getReports = function() {
            $http({
                method: "GET",
                url: "not_format/reports_payer",
                headers: {"Content-Type": "application/json"}
            }).then(
                function (data) {
                    console.log("Read");
                    for (let i = 0; i < Object.keys(data.data).length; i++) {
                        let option = document.createElement("option");
                        let asses_var = " - Assessed: " + data.data[i].note.toString();
                        if(data.data[i].assessed === false) {
                            asses_var = " - Not assessed";
                        }
                        let temp = (i + 1).toString() + ". " + "Report "
                            + data.data[i].creationTime.toString()
                            + asses_var;
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

// "creationTime":"2019-07-14T21:51:54","assessed":false,"accepted":false,"acceptTime":null,"taxpayerLogin":"Nike1","taxofficerLogin":"Mike1"},{"creationTime":"2019-07-14T21:54:09","assessed":false,"accepted":false,"acceptTime":null,"taxpayerLogin":"Nike1","taxofficerLogin":"Mike1"}]

// angular.module("replist_form", [])
//     .controller("repListCtrl", ["$scope", "$http", function ($scope, $http) {
//         console.log("repListCtrl")
//         $scope.createReport = function() {
//             $http({
//                 method: "POST",
//                 url: "payer_report_list",
//                 data: $.param(reportlist),
//                 headers: {"Content-Type" : "application/x-www-form-urlencoded"}
//             }).then(
//                 function (data) {
//                     console.log("Read2");
//                     console.log(data.data);
//                 },
//                 function (error) {
//                     console.log("userCtrl error")
//                 }
//             );
//         }
//     }]);