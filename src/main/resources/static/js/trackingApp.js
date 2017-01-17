var bookStoreApp = angular.module('trackingPage', ['ngCookies','ngRoute']);
var baseUrl="http://localhost:8080/api";
var imageBaseUrl="http://localhost:8080";
var absoluteUrlfake="file:///F:/bookstore/SE2Eshop/src/main/resources/static/bookDetail.html?";
var absoluteUrlOrigion="http://localhost:8080/bookDetail.html?";
var absoluteUrl=absoluteUrlOrigion;

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
