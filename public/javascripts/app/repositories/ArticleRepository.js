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
            .then(function(res){ return repo.toList(res, toArticle)});
    };

    repo.getById = function(id) {
        return $resource('/article/' + id)
            .query()
            .$promise
            .then(function(res){ return toArticle(res)},
                function(e) {console.log(123)}
            );
    };

    return repo;
});