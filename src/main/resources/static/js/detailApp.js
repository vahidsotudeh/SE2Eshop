var bookStoreApp = angular.module('detailPage', ['ngCookies','ngRoute']);
var baseUrl="http://172.20.185.149:8080/api";
var imageBaseUrl="http://172.20.185.149:8080"; 
var absoluteUrlfake="file:///C:/Users/Microsoft/IdeaProjects/SE2Eshop/src/main/resources/static/BookDetail.html?";
var absoluteUrlOrigion="http://localhost:8080/BookDetail.html?";
var absoluteUrl=absoluteUrlOrigion;

bookStoreApp.controller('bookDetailController',function bookDetailController($scope,$http,$cookies,$location,$routeParams) {
            $scope.currUrl=$location.absUrl().replace(absoluteUrl,"").replace("#!/","");
            $scope.bookId=$scope.currUrl.replace("bookId=","");
            $scope.shoppingCartItemsCount=$cookies.get("shoppingCartItemsCount")|0;   
            $scope.numbers=[1,2,3,4,5];
            // if($cookies.get("shoppingCartItemsCount")){
            //     $scope.shoppingCartItemsCount=$cookies.get("shoppingCartItemsCount");
            // }         
         $scope.fetchBookDetail=function () {  $http.get(baseUrl+"/books/"+$scope.bookId).then(function (resp) { 
                $scope.book=resp.data;
                $scope.book.imageAddress=imageBaseUrl+$scope.book.imageAddress;

         },function (err) { 
             alert("error");
          });
        }
        // alert($cookies.get("shoppingCartItemsCount"));
        $scope.fetchBookDetail();
        $scope.addToShoppingCart=function () {
            $scope.shoppingCartItemsCount+=$scope.selectedCount;
            $cookies.put("shoppingCartItemsCount",$scope.shoppingCartItemsCount);
            $scope.individualItems=$cookies.get("individualItems")|1;
            $cookies.put("individualItems",$scope.individualItems);
            $cookies.put("book:"+bookId,$scope.selectedCount);
          }
        // $cookieStore.put('shoppingCartItemsCount','1');
});