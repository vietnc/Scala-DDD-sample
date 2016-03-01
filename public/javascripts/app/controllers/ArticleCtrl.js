'use strict';
application.controller('ArticleCtrl',
    function ($scope, ArticleRepo) {

        $scope.articles = [];
        $scope.init = function () {
            ArticleRepo.getAll().then(
                function(result) {
                    $scope.articles = result;
                },
                function() {
                    $scope.errors.push({message: "Can not get data"});
                }
            );
        }
    }
);