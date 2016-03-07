
'use strict';
application.controller('UserCtrl',function($scope){
    $scope.summitLogin = function (){
            $.ajax({
                url: "/user/login",
                type: "POST",
                data: $(".LoginForm").serialize(),
                success: function(data){
                    alert("Login Successfully ! " )
                },
                error: function(e){
                    alert ("Failed to login: " + e.getMessage())
                }
            });
    }
}
)
