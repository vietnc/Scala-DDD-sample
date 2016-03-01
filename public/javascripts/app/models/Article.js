'use strict';
model.value('Article', function(data) {
    this.id = data.id;
    this.title = data.title;
    this.content = data.content;
    this.email = data.email;
    this.createdDate = data.createdDate;
});