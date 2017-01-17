var bookStoreApp = angular.module('mainPage', ['ngCookies','ngRoute']);
var baseUrl="http://localhost:8080/api";
var imageBaseUrl="http://localhost:8080";
 bookStoreApp.controller('moreBooks', function($scope,$http,$cookies,$route,$location,$routeParams) {
     $scope.name = 'moreBooks';
     $scope.params = $routeParams;
     $scope.counter=0;
     $scope.moreLinkClicked=false;
 });

bookStoreApp.controller('bookListController',function bookListController($scope,$http,$cookies,$route,$location,$routeParams) {
     $scope.clickCounter=1;
     $scope.moreLinkClicked=false;
     $scope.manyBooksCounter=[];
     $scope.moreBtnOnClick=function () { 
         $scope.clickCounter++;
         $scope.len=$scope.clickCounter*6;
         $http.get(baseUrl+"/book-summary?len="+$scope.len+"&start="+($scope.len-6)+"&order=score").then(function (data) { 
            $scope.manyBooks=data.data;
            $scope.moreLinkClicked=true;
            for(var i=0;i<$scope.manyBooks.length;i++){
                  $scope.manyBooks[i].imageAddress=imageBaseUrl+$scope.manyBooks[i].imageAddress;
            }
            // alert($scope.clickCounter);
            $scope.manyBooksCounter[$scope.clickCounter-1]=$scope.manyBooks;
            // alert(data.data);
          },function (error) { 
            // alert("a");
           });

      }

        $scope.getBooksOrderBySaleCount = function () {
             $http.get(baseUrl+"/book-summary?order=saleCount&len=6")
            .then(function(data){
                $scope.bestSellers = data.data; //return if success on fetch
                for(var i=0;i<$scope.bestSellers.length;i++){
                  $scope.bestSellers[i].imageAddress=imageBaseUrl+$scope.bestSellers[i].imageAddress;
                  
                }
            }, function(resp) {
              $scope.books.title = "error in fetching data"; 
            });
        }
        $scope.getBooksOrderByScore = function () {
             $http.get(baseUrl+"/book-summary?order=score&len=6")
            .then(function(data){

                $scope.highScores= data.data;

                for(var i=0;i<$scope.highScores.length;i++){
                  $scope.highScores[i].imageAddress=imageBaseUrl+$scope.highScores[i].imageAddress;
                  
                }
            }, function(resp) {
              $scope.books.title = "error in fetching data"; 
            });
        }
        
                // $cookies.put("username", "123");
                // $cookies.put("username1", "123");
                // $cookies.put("username2", "123");
                // $cookies.put("username3", "123");
                // $cookies.put("username4", "123");
                // $cookies.put("username5", "123");
                     //   alert($cookies.get("username"));
        $scope.getBooksOrderBySaleCount(); //return loadData function
        $scope.getBooksOrderByScore();

        
    });