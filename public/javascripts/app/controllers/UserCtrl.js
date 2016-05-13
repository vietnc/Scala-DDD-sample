
'use strict';
application.controller('UserCtrl',function($scope){
    $scope.summitLogin = function (){
        var userLogin = $(".LoginForm #email").val();
            $.ajax({
                url: "/user/login",
                type: "POST",
                data: $(".LoginForm").serialize(),
                success: function(data){

                    $scope.Common.login(userLogin);
                    $scope.$apply();
                    alert("Login Successfully ! " );
                },
                error: function(req){
                    alert ("Failed to login: " + req.responseText)
                }
            });
    }
    $scope.summitSignin = function(){

        $.ajax({
            url: "/user/signin",
            type: "POST",
            data: $(".SigninForm").serialize(),
            success: function(data){
                alert("User Account created!" );
            },
            error: function(req){
                alert ("Failed to SignIn: " + req.responseText)
            }
        });
    }
}
)
