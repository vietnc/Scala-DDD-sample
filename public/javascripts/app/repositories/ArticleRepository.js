'use strict';
repository.factory('ArticleRepo', function ($resource, Article) {
    var repo = {};
    function toArticle(response) {
        return new Article(response);
    }

    repo.toList = function(response, toEntity) {
        var list = [];
        angular.forEach(response, function (data) {
            list.push(toEntity(data));
        });
        return list;
    };

    repo.getAll = function() {
        return $resource('/article')
            .query()
            .$promise
            .then(
                function(res){ return repo.toList(res, toArticle)}
            );
    };

    repo.getById = function(id) {
        return $resource('/article/' + id)
            .get()
            .$promise
            .then(
                function(res){ return toArticle(res)},
                function(e) {console.log("Error to get article byId " + id)}
            );
    };

    repo.store = function(articleRequest){
        return $resource('/article/add',articleRequest )
            .save()
            .$promise
            .then(
                function(res){ return console.log(res)},
                function(e) {alert("Error to store article" + articleRequest)}
            );
    }

    return repo;
});