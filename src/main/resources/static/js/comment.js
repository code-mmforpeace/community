function commentCommit() {
    var questionId = $('#question_id').val();
    var content = $('#commentContent').val();
    commentCommit(questionId,content,1);
};

function secondCommentCommit(e) {
    var id = e.getAttribute("data-id");
    var comment = $("#second-"+id).val();
    commentCommit(id,comment,2);
}



function commentCommit(targetId, content, type) {

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
    var comment = $('#comment-'+id);
    if(comment.hasClass("in")){
        comment.removeClass("in");
        e.classList.remove("active");
    }else{
        $.getJSON("/comment"+id,function (data) {

        });
        comment.addClass("in");
        e.classList.add("active");
    }
    //comment.toggleClass("in");
    console.log(id);
}