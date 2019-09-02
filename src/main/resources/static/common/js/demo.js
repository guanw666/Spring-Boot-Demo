// 评论
function comment2Target(targetId, targetType, comment) {
    if (!comment) {
        alert("评论内容不能为空！");
        return;
    }
    $.ajax({
        type: "POST",
        url: "/comment",
        data: JSON.stringify({
            parentId: targetId,
            comment: comment,
            type: targetType
        }),
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function (response) {
            if (response.code === 200) {
                window.location.reload();
            } else {
                if (response.code === 2004) {
                    var isAccepted = confirm(response.message);
                    if (isAccepted) {
                        window.open("https://github.com/login/oauth/authorize?client_id=Iv1.161462b963dc85f9&redirect_uri=http://localhost:8080/callback&scope=user&state=1");
                        localStorage.setItem("closable", "true");
                    }
                } else {
                    alert(response.message);
                }
            }
        }
    });
}

// 回复问题
function replyQuestion() {
    var questionId = $("#questionId").val();
    var comment = $("#comment").val();
    comment2Target(questionId, 1, comment);
}

// 回复评论
function replyComment(e) {
    var targetId = e.value;
    var comment = $("#comment-id-" + targetId)[0].value;
    comment2Target(targetId, 2, comment);
}

// 折叠/显示二级评论div
function collapseComments(e) {
    var questionCommentId = e.attributes.data.value;
    var secondCommentDiv = $("#second-comment-div-" + questionCommentId);
    var isHide = secondCommentDiv.hasClass("collapse");
    if (isHide) {
        e.classList.add("comment-icon-active");
        secondCommentDiv.removeClass("collapse");
        $.get("/comment/" + questionCommentId, function (response) {
            var datas = response.data;
            console.log(datas);
            if (datas.length === 0) {
                secondCommentDiv.children(":first").html("暂无评论");
            } else {
                var innerHtml = "";
                for (var i = 0; i < datas.length; i++) {
                    var data = datas[i];
                    innerHtml += "<div class=\"col-lg-12 col-md-12 col-sm-12 col-xs-12\">" +
                        "<div class='media-left'>" +
                        "                                <img class='media-object avatar-img' src='" + data.user.avatarUrl + "'>" +
                        "                            </div>" +
                        "                            <div class='media-body'>" +
                        "                                <div>" + data.user.name + "</div>" +
                        "                                <div>" + data.comment + "</div>" +
                        "                                <div><span class='pull-right'>" + moment(data.gmtCreate).format('YYYY-MM-DD HH:mm:ss') + "</span></div>" +
                        "                            </div>" +
                        "</div>"
                }
                secondCommentDiv.children(":first").html(innerHtml);
            }
        });
    } else {
        e.classList.remove("comment-icon-active");
        secondCommentDiv.addClass("collapse");
    }
}