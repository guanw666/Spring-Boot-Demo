// 评论
function doComment() {
    var questionId = $("#questionId").val();
    var comment = $("#comment").val();
    $.ajax({
        type: "POST",
        url: "/comment",
        data: JSON.stringify({
            parentId: questionId,
            comment: comment,
            type: 1
        }),
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function (response) {
            if (response.code === 200) {
                $("#comment_section").hide();
            } else {
                alert(response.message);
            }
        }
    });
}