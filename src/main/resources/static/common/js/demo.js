// 评论
function doComment() {
    var questionId = $("#questionId").val();
    var comment = $("#comment").val();
    if (!comment) {
        alert("评论内容不能为空！");
        return;
    }
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