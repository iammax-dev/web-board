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

    $("#btn-cancel").click(function() {
        document.location.href = "/";
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