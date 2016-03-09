'use strict';
application.controller('ArticleCtrl',['$scope','$cookies','ArticleRepo',
    function ($scope,$cookies, ArticleRepo) {
        $scope.articles = [];
        $scope.article = {};

        $scope.init = function () {
            ArticleRepo.getAll().then(
                function (result) {
                    $scope.articles = result;
                },
                function () {
                    alert("ERROR to init" + result)
                }
            );
        };

        $scope.getArticle = function (id) {
            ArticleRepo.getById(id).then(
                function (result) {
                    $scope.article = result;
                },
                function () {
                    alert("ERROR to get Article:" + result)
                }
            );
        };

        $scope.createAticle = function () {
            var articleRequest = {
                title: $scope.AddForm.title == undefined ? "" : $scope.AddForm.title,
                content: $scope.AddForm.content == undefined ? "" : $scope.AddForm.content,
                email: $scope.UserInfo.email == "" ? $scope.AddForm.email: $scope.UserInfo.email,
            }
            ArticleRepo.store(articleRequest).then(
                function(res){
                    $scope.init()
                }
                , function(e){  //failure
                    alert("ERROR to store: " + e)
                }

            );
        }

    }]);