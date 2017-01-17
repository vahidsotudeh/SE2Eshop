var bookStoreApp = angular.module('trackingPage', ['ngCookies','ngRoute']);
var baseUrl="http://192.168.43.191:8080/api";
var imageBaseUrl="http://192.168.43.191:8080"; 
var absoluteUrlfake="file:///C:/Users/Microsoft/IdeaProjects/SE2Eshop/src/main/resources/static/bookDetail.html?";
var absoluteUrlOrigion="http://172.20.185.149:8080/bookDetail.html?";
var absoluteUrl=absoluteUrlfake;

bookStoreApp.controller('trackingPageController',function checkOutPageController($scope,$http,$cookies,$location,$routeParams) {
    $scope.token=$cookies.get("userToken");
     $scope.completePayment=function () { 
        $http.get(baseUrl+"/getTrack?token="+$scope.token).then(function (resp) { 
                 $scope.trackCode=resp.data;
         },function (error) { 

          });

      }
      $scope.completePayment();


});














//***********************************************************************************************00 */
