var bookStoreApp = angular.module('checkOutPage', ['ngCookies','ngRoute']);
var baseUrl="http://localhost:8080/api";
var imageBaseUrl="http://localhost:8080";
var absoluteUrlfake="file:///F:/bookstore/SE2Eshop/src/main/resources/static/bookDetail.html?";
var absoluteUrlOrigion="http://localhost:8080/bookDetail.html?";
var absoluteUrl=absoluteUrlfake;

bookStoreApp.controller('checkOutPageController',function checkOutPageController($scope,$http,$cookies,$location,$routeParams) {
    $scope.discount=0;
    $scope.totalPrice=0;
    var haveList=false;
    $scope.isLogin=false;
    $scope.token=$cookies.get("userToken");
    if(typeof $scope.token!="undefined"){
        $scope.isLogin=true;
    }

    $scope.shoppingCartItemsCount=$cookies.get("shoppingCartItemsCount")|0;
    $scope.discountCode=$cookies.get("discountCode")|0;

    var test=$cookies.get("bookList");
    if(typeof test!="undefined")  {
        haveList=true;
        $scope.bookIds=$cookies.get("bookList");
        $scope.bookIds=$scope.bookIds.split("-");
    }
    if(haveList)
    if($scope.bookIds[$scope.bookIds.length-1]==""){
            $scope.bookIds.splice($scope.bookIds.length-1,1);
    }
    var isNull=false;
    if(haveList){
        if($scope.bookIds[0]==""){
            isNull=true;
            $scope.bookIds.splice(0,1);
        }
        $scope.bookDetails=new Array($scope.bookIds.length);
        for(var j=0;j<$scope.bookIds.length;j++){
            $scope.bookDetails[j]=[];
        }
    }

    $scope.looper=0;
    $scope.fetchBooksListById=function (id) { 
                    $http.get(baseUrl+"/books/"+$scope.bookIds[id]).then(function (resp) { 
                        $scope.temp=resp.data;
                        $scope.bookDetails[id].price=$scope.temp.price;
                        $scope.bookDetails[id].id=$scope.temp.id;
                        $scope.bookDetails[id].title=$scope.temp.title;
                        $scope.bookDetails[id].count=$cookies.get("book:"+$scope.bookIds[id]);
                        $scope.totalPrice+=($scope.temp.price*$scope.bookDetails[id].count);
                    });            
     }
        if(haveList)
            if($scope.bookIds.length>0 &&!isNull)
            for(var i=0;i<$scope.bookIds.length;i++){
                 $scope.fetchBooksListById(i);           
            }

    $scope.computeDiscount=function () { 
        $http.get(baseUrl+"/discount?code="+$scope.discountCode).then(function (resp) { 
            $scope.discount=resp.data;
            if(resp.data==0){
                 alert("متاسفانه کد تخفیف شما معتبر نمی باشد");
            }else{
                $cookies.put("discountCode",$scope.discountCode);
            }
         },function (error) { 
            alert("متاسفانه کد تخفیف شما معتبر نمی باشد");
          });        
     }
     $scope.removeItem=function (itemId) { 
         $cookies.put("individualItems",$cookies.get("individualItems")-1);
         $cookies.put("shoppingCartItemsCount",$cookies.get("shoppingCartItemsCount")-$cookies.get("book:"+itemId));
         var index=$scope.bookIds.indexOf(itemId);
         $scope.totalPrice-=($cookies.get("book:"+itemId)*$scope.bookDetails[index].price)
         $cookies.remove("book:"+itemId);
         $scope.bookIds.splice(index,1);
         $scope.bookDetails.splice(index,1);
         var newList="";
         for(var j=0;j<$scope.bookIds.length;j++){
             newList=$scope.bookIds[j]+"-"+newList;
         }
         if(newList==""){
             $cookies.remove("bookList");
         }
         $cookies.put("bookList",newList);
         $scope.shoppingCartItemsCount=$cookies.get("shoppingCartItemsCount");
      }

      $scope.login=function () { 
            //  var parameter = JSON.stringify({username:$scope.username, password:$scope.password});

            //  $http({
            //     url: baseUrl+"/login",
            //     method: "POST",
            //     data: parameter,
            //     headers: {'Content-Type': 'application/json'}
            // }).then(function (resp) { 
            //      alert(resp.data);
            //      $(".modal_close").click();
            //   },function (error) { 
            //     alert("err");
            //    });

             $http.get(baseUrl+"/login?username="+$scope.username+"&"+"password="+$scope.password).then(function (resp) { 
                 $(".modal_close").click();
                 $cookies.put("userToken",resp.data);
                 $scope.isLogin=true;
                 $scope.token=resp.data;
                 $http.get(baseUrl+"/status?token="+$scope.token).then(function (resp) { 
                    $scope.user=resp.data;
                    $scope.isLogin=true;
                 },function (param) {
                     alert("vay!!");
                   });
              },function (error) { 
                alert("نام کاربری یا رمز عبور نادرست است");
               });


       }
            if($scope.isLogin){
                $http.get(baseUrl+"/status?token="+$scope.token).then(function (resp) { 
                        $scope.user=resp.data;
                        $scope.isLogin=true;
                 },function (param) {
                     alert("vay!!");
                   });
            }
            $scope.logOut=function () { 
                 $http.get(baseUrl+"/logout?token="+$scope.token).then(function (resp) { 
                        delete($scope.user);
                        $cookies.remove("userToken");
                        $scope.isLogin=false;
                 },function (param) {
                     alert("vay!!");
                   });
             }

             $scope.signUp=function () { 
                 if($scope.nameFa.length>3&&$scope.address.length>3&&$scope.phoneNumber.length>8&&$scope.username.length>3&&$scope.password.length>3){
                     $http.get(baseUrl+"/register?nameFa="+$scope.nameFa+"&address="+$scope.address+"&phoneNumber="+$scope.phoneNumber+"&username="+$scope.username+"&password="+$scope.password)
                     .then(function (resp) { 
                            $(".modal_close").click();
                            $cookies.put("userToken",resp.data);
                            $scope.isLogin=true;
                            $scope.token=resp.data;
                            $http.get(baseUrl+"/status?token="+$scope.token)
                            .then(function (resp1) { 
                                    $scope.user=resp1.data;
                                },function (param) {
                                    alert("مشکلی وجود دارد لطفا 10 دقیقه دیگر تلاش کنید");
                                }
                            );
                        },function (error) { 
                                    alert("مشکلی در ثبت نام به وجود آمد دوباره تلاش کنید");
                        }
                    );
                 }
                 else{
                     alert("لطفا همه ی فیلد ها را با مقادیر مناسب پر کنید");
                 }
              }

              $scope.payment=function () { 
                  if(!$scope.isLogin){
                      alert("برای تکمیل خرید باید وارد سایت شوید");
                      return;
                  }
                  else{
                                  //  var parameter = JSON.stringify({username:$scope.username, password:$scope.password});
                      var myarr=[];
                          for (var seeder=0;seeder < $scope.bookDetails.length;seeder++){
                            //   alert($scope.bookDetails[seeder].id);
                                myarr[seeder] = {
                                    id:   $scope.bookDetails[seeder].id,
                                    count: $scope.bookDetails[seeder].count
                                };
                            }
                      var booksInOrder=JSON.stringify(myarr);
                      var paymentOrder=JSON.stringify({token:$cookies.get("userToken"),items:myarr,discountCode:$scope.discountCode});
                     $.ajax({
                        url: baseUrl+"/orderBooks",
                        type: "POST",
                        data: paymentOrder,
                        contentType: "application/json",
                        complete: function (param) { 
                                    $http.get(baseUrl+"/orderStat?token="+$scope.token).then(function (resp) { 
                                        alert("سفارش ثبت شده");
                                        window.location.replace("payment.html");
                                     },function (errr) {
                                         
                                      });
                         }
                    });

                    //  $http({
                    //         url: baseUrl+"/orderBooks",
                    //         method: "POST",
                    //         data: paymentOrder,
                    //         headers: {'Content-Type': 'application/json'}
                    //     }).then(function (resp) { 
                    //          alert("resp.data");
                    //       },function (error) { 
                    //             // alert("err");
    
                    //        });

                  }
               }



});














//***********************************************************************************************00 */
