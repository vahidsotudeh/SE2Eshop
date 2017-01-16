var bookStoreApp = angular.module('detailPage', ['ngCookies','ngRoute']);
var baseUrl="http://172.20.185.149:8080/api";
var imageBaseUrl="http://172.20.185.149:8080"; 
var absoluteUrl="file:///C:/Users/Microsoft/IdeaProjects/SE2Eshop/src/main/resources/static/BookDetail.html?";


bookStoreApp.controller('bookDetailController',function bookDetailController($scope,$http,$cookies,$location,$routeParams) {
            $scope.currUrl=$location.absUrl().replace(absoluteUrl,"").replace("#!/","");
            $scope.bookId=$scope.currUrl.replace("bookId=","");
            $scope.shoppingCartItemsCount=0;   
            if($cookies.get("shoppingCartItemsCount")){
                $scope.shoppingCartItemsCount=$cookies.get("shoppingCartItemsCount");
            }         
         $scope.fetchBookDetail=function () {  $http.get(baseUrl+"/books/"+$scope.bookId).then(function (resp) { 
                $scope.book=resp.data;
                $scope.book.imageAddress=imageBaseUrl+$scope.book.imageAddress;

         },function (err) { 
             alert("error");
          });
        }
        alert($cookies.get("shoppingCartItemsCount"));
        $scope.fetchBookDetail();
        $cookies.put('shoppingCartItemsCount','1');
        // $cookieStore.put('shoppingCartItemsCount','1');
});