var bookStoreApp = angular.module('detailPage', ['ngCookies','ngRoute']);
var baseUrl="http://172.20.185.149:8080/api";
var imageBaseUrl="http://172.20.185.149:8080"; 
var absoluteUrlfake="file:///C:/Users/Microsoft/IdeaProjects/SE2Eshop/src/main/resources/static/bookDetail.html?";
var absoluteUrlOrigion="http://172.20.185.149:8080/bookDetail.html?";
var absoluteUrl=absoluteUrlfake;

bookStoreApp.controller('bookDetailController',function bookDetailController($scope,$http,$cookies,$location,$routeParams) {
            $scope.currUrl=$location.absUrl().replace(absoluteUrl,"").replace("#!/","");
            $scope.bookId=$scope.currUrl.replace("bookId=","");
            $scope.shoppingCartItemsCount=$cookies.get("shoppingCartItemsCount")|0;
            $scope.numbers=[1,2,3,4,5];
         $scope.fetchBookDetail=function () {  $http.get(baseUrl+"/books/detail/"+$scope.bookId).then(function (resp) { 
                $scope.book=resp.data;
                $scope.book.imageAddress=imageBaseUrl+$scope.book.imageAddress;

         },function (err) { 
             alert("error");
          });
        }
        $scope.fetchBookDetail();
        $scope.addToShoppingCart=function () {
            if($scope.selectedCount){
                $scope.shoppingCartItemsCount+=parseInt($scope.selectedCount);
                $cookies.put("shoppingCartItemsCount",$scope.shoppingCartItemsCount);
                var lastCount=0;
                var check=$cookies.get("book:"+$scope.bookId);
                if(typeof check !="undefined"){
                    $scope.individualItems=$cookies.get("individualItems")+1|1;
                    var list=$cookies.get("bookList");
                    lastCount=parseInt($cookies.get("book:"+$scope.bookId));
                    if(list)
                        $cookies.put("bookList",list+"-"+$scope.bookId);
                    else{
                        $cookies.put("bookList",$scope.bookId);
                    }
                }
                $cookies.put("individualItems",$scope.individualItems);
                lastCount+=parseInt($scope.selectedCount);
                $cookies.put("book:"+$scope.bookId,lastCount);
            }
          }
        
});