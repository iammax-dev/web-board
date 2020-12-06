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

function fn_modify() {

    var user_id = $('#dataForm #userId').val();
    var title = $("#dataForm #title").val();
    var content = $('#dataForm #content').val();

    if( title == ""  || title == null || title == '&nbsp;' || title == '<p>&nbsp;</p>')  {
         alert("제목을 입력하세요.");
         $('#title').focus();
         return;
    }

    if( content == ""  || content == null || content == '&nbsp;' || content == '<p>&nbsp;</p>')  {
         alert("내용을 입력하세요.");
         oEditors.getById["content"].exec("FOCUS"); //포커싱
         return;
    }

    var data = {
        user_id : parseInt(userId),
        title   : title,
        author  : $('#dataForm #author').val(),
        content : content
    };

console.log(data)

    $.ajax({
        type        : 'POST',
        url         : '/api/v1/posts',
        dataType    : 'json',
        contentType : 'application/json; charset=utf-8',
        data        : JSON.stringify(data)
    }).done(function () {
        alert("글이 등록 되었습니다.");
        window.location.href = '/';
    }).fail(function (error) {
        alert(JSON.stringify(error));
    });

}

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