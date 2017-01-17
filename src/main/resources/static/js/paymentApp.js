var bookStoreApp = angular.module('paymentPage', ['ngCookies','ngRoute']);
var baseUrl="http://localhost:8080/api";
var imageBaseUrl="http://localhost:8080";
var absoluteUrlfake="file:///F:/bookstore/SE2Eshop/src/main/resources/static/bookDetail.html?";
var absoluteUrlOrigion="http://localhost:8080/bookDetail.html?";
var absoluteUrl=absoluteUrlOrigion;

bookStoreApp.controller('paymentPageController',function checkOutPageController($scope,$http,$cookies,$location,$routeParams) {
    $scope.token=$cookies.get("userToken");
    $http.get(baseUrl+"/orderStat?token="+$scope.token).then(function (resp) {
        $scope.order=resp.data;
     });

     $scope.completePayment=function () { 
                 window.location.replace("trackingCode.html");
      }


});














//***********************************************************************************************00 */
