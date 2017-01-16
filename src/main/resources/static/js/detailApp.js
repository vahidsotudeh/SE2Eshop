var bookStoreApp = angular.module('detailPage', ['ngCookies','ngRoute']);
var baseUrl="http://172.20.185.149:8080/api";
var imageBaseUrl="http://172.20.185.149:8080"; 


bookStoreApp.controller('bookDetailController',function bookDetailController($scope,$http,$cookies,$route,$location,$routeParams) {
            alert($location.search());


});