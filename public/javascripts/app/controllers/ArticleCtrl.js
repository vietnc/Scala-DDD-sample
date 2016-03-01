'use strict';
application.controller('ArticleCtrl',
    function ($scope, ArticleRepo) {
        $scope.articles = [];
        $scope.article = {};
        $scope.init = function () {
            ArticleRepo.getAll().then(
                function(result) {
                    $scope.articles = result;
                },
                function() {
                    $scope.errors.push({message: "Can not get data"});
                }
            );
        };

        $scope.getArticle = function (id) {
            ArticleRepo.getById(id).then(
                function(result) {
                    $scope.article = result;
                },
                function() {
                    $scope.errors.push({message: "Can not get data"});
                }
            );
        };
    }
);