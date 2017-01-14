var phonecatApp = angular.module('mainPage', []);

// Define the `PhoneListController` controller on the `phonecatApp` module
phonecatApp.controller('bookListController', function bookListController($scope,$http) {
            // $http.get('http://172.20.188.60:8080/api/book-summary').success(function(resp) {
            //     alert("as");
            //     $scope.books = resp.data;
            // });
        $scope.loadData = function () {
             $http.get("172.20.188.60:8080/api/book-summary")
            .then(function(data){
                $scope.data = data; //return if success on fetch
                alert("aa");
            }, function(error, status) {
                alert(status);
              $scope.data = "error in fetching data"; //return if error on fetch
        });}
        $scope.loadData(); //return loadData function
            alert("as");
});
