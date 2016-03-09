'use strict';
application.controller('LayoutCtrl', ['$scope','$cookies',
    function ($scope,$cookies) {

        $scope.UserInfo = {email:"", isAuth: false}

        $scope.Common= {
            isLogin : function(){
                if( $scope.UserInfo.email != undefined && $scope.UserInfo.email != ""){
                    return true;
                }else{
                    var user = $cookies.getObject("userinfo")
                    if(user != undefined && user.email != undefined && user.email != ""){
                        $scope.UserInfo = user;
                        return true;
                    }
                }
                return false;
            },
            login: function(email){
                $scope.UserInfo.email = email;
                $scope.UserInfo.isAuth = true;
                $cookies.putObject("userinfo",{email:email, isAuth:true}, {expires: 60})

            },
            logout: function(){
                $scope.UserInfo.email = "";
                $scope.UserInfo.isAuth = false;
                $cookies.putObject("userinfo",{});
            }
        }
    }]
)
