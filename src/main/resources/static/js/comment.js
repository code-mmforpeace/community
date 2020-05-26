function commentCommit() {
    var questionId = $('#question_id').val();
    var content = $('#commentContent').val();
    comment(questionId,content,1);
}

function secondCommentCommit(e) {
    var id = e.getAttribute("data-id");
    var comment = $("#second-"+id).val();
    comment(id,comment,2);
}



function comment(targetId, content, type) {

    if(!content){
        alert("不能回复空内容！")
        return;
    }

    $.ajax({
        type:"POST",
        contentType:"application/json",
        url:"/comment",
        data: JSON.stringify({
            "questionId":targetId,
            "comment":content,
            "type":type
        }),
        success:function (data) {
            if(data.success){
                var b1 = confirm(data.toast);
                if(type==1){
                if(b1){
                    window.location.href="/question/"+targetId;
                }
                }else {
                    window.location.reload();
                }
            }else {
                if(data.code == 2002){
                    var b = confirm(data.errMsg);
                    if(b){

                    }
                }
                window.alert(data.errMsg);
            }
        }
    });
}

function openCollapse(e) {

    var id = e.getAttribute("data-id");
    var comments = $("#comment-" + id);
    var url = '/secondComment/';

    // 获取一下二级评论的展开状态
    var collapse = e.getAttribute("data-collapse");
    if (collapse) {
        // 折叠二级评论
        comments.removeClass("in");
        e.removeAttribute("data-collapse");
        e.classList.remove("active");
    } else {
        if(comments.children().length != 1) {
            //展开二级评论
            comments.addClass("in");
            // 标记二级评论展开状态
            e.setAttribute("data-collapse", "in");
            e.classList.add("active");
        }else {
            $.getJSON(url + id, function (data) {
                if (data.success) {
                    $.each(data.secondComments.reverse(), function (index, comment) {
                        var mediaLeftElement = $("<div/>", {
                            "class": "media-left"
                        }).append($("<img/>", {
                            "class": "media-object image",
                            "src": comment.user.imageUrl
                        }));

                        var mediaBodyElement = $("<div/>", {
                            "class": "media-body"
                        }).append($("<h5/>", {
                            "class": "media-heading",
                            "html": comment.user.zkGithubUsername
                        })).append($("<div/>", {
                            "html": comment.zkComment
                        })).append($("<div/>", {
                            "class": "menu"
                        }).append($("<span/>", {
                            "class": "pull-right",
                            "html": moment(comment.commentCreateTime).format('YYYY-MM-DD')
                        })));

                        var mediaElement = $("<div/>", {
                            "class": "media"
                        }).append(mediaLeftElement).append(mediaBodyElement);

                        var commentElement = $("<div/>", {
                            "class": "col-lg-12 col-md-12 col-sm-12 col-xs-12 comment-section"
                        }).append(mediaElement);

                        comments.prepend(commentElement);
                    });
                }
            });
            //展开二级评论
            comments.addClass("in");
            // 标记二级评论展开状态
            e.setAttribute("data-collapse", "in");
            e.classList.add("active");
        }
    }
}