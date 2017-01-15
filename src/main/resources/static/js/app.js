var phonecatApp = angular.module('mainPage', []);

// Define the `PhoneListController` controller on the `phonecatApp` module

        //  jqeury.ajax({ 
        //      type: "GET",
        //      dataType: "json",
        //      url: "http://172.20.188.60:8080/api/book-summary",
        //      success: function(data){        
        //         alert(data);
        //      }
        //  });   
phonecatApp.controller('bookListController', function bookListController($scope,$http) {
            // $http.get('http://172.20.188.60:8080/api/book-summary').success(function(resp) {
            //     alert("as");
            //     $scope.books = resp.data;
            // });
        $scope.loadData = function () {
             $http.get("http://172.20.188.60:8080/api/book-summary")
            .then(function(data){
                alert(data.data[0].title+" sd");
                $scope.books = data.data; //return if success on fetch
            }, function(resp) {
                alert(resp.status);
              $scope.books.title = "error in fetching data"; //return if error on fetch
            });
        }
        
        $scope.login=function (data) {
        //     $http({url: "http://172.20.188.60:8080/login",
        //     method:"POST",
        //     data: {username:"amir",password:"123"},
        //     headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        // })
        $http.post("http://172.20.188.60:8080/login","username=amir&password=123&grant_type=password")
            .then(function (param) { 
                alert(param.data);
             },function (param) {  
                alert(param.status);

             });    
        }

        $scope.loadData(); //return loadData function
        
    });
