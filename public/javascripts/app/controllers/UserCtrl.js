
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
                error: function(e){
                    alert ("Failed to login: " + e.getMessage())
                }
            });
    }
}
)
