var bookStoreApp = angular.module('paymentPage', ['ngCookies','ngRoute']);
var baseUrl="http://172.20.185.149:8080/api";
var imageBaseUrl="http://172.20.185.149:8080"; 
var absoluteUrlfake="file:///C:/Users/Microsoft/IdeaProjects/SE2Eshop/src/main/resources/static/bookDetail.html?";
var absoluteUrlOrigion="http://172.20.185.149:8080/bookDetail.html?";
var absoluteUrl=absoluteUrlfake;

bookStoreApp.controller('paymentPageController',function checkOutPageController($scope,$http,$cookies,$location,$routeParams) {
    $scope.token=$cookies.get("userToken");
    


});














//***********************************************************************************************00 */
