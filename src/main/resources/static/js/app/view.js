$(document).ready(function(){

    // 작성자인지 확인하고, 아니면 수정/삭제버튼 숨김
    var loginIdVal  = $('#userId').val();
    var authorIdVal = $('#authorId').val();
    if (loginIdVal != authorIdVal || loginIdVal != authorIdVal) {
            $("#btn-delete").hide();
            $("#btn-modify").hide();
        }

    // 삭제
    $("#btn-delete").click(function() {
        fn_delete();
    });

    // 수정
    $("#btn-modify").click(function() {
        document.location.href = "/posts/update/" + $('#postId').val();
    });

    // 목록
    $("#btn-cancel").click(function() {
        document.location.href = "/";
    });

    // 댓글 저장
    $("#replySubmit").click(function() {
        fn_replySubmit();
    });

});

function fn_delete() {
    var id = $('#postId').val();

    $.ajax({
        type        : 'DELETE',
        url         : '/api/v1/posts/' + id,
        dataType    : 'json',
        contentType : 'application/json; charset=utf-8'
    }).done(function () {
        alert("글이 삭제 되었습니다.");
        window.location.href = '/';
    }).fail(function (error) {
        alert(JSON.stringify(error));
    });
}

function fn_replySubmit() {

	var replyName = $("#replyName").val();
	var replyContents = $("#replyContents").val();

    if (replyContents != null && replyContents != "") {

       var data = {
            post_id       : $("#postId").val(),
            replyAuthor  : replyName,
            replyContent : replyContents
        };

    console.log(data)

        $.ajax({
            type        : 'POST',
            url         : '/reply',
            dataType    : 'json',
            contentType : 'application/json; charset=utf-8',
            data        : JSON.stringify(data)
        }).done(function () {
            alert("댓글이 등록 되었습니다.");
            location.reload();
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });

    } else {
        alert("댓글 내용을 입력해주세요");
    }

}