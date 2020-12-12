$(document).ready(function(){

	var oEditors = [];
	nhn.husky.EZCreator.createInIFrame({
	    oAppRef: oEditors,
	    elPlaceHolder: "content",
	    sSkinURI: "/js/webEditor/SmartEditor2Skin.html",
	    fCreator:"createSEditor2",
	    fOnAppLoad : function(){
//	    	oEditors.getById["content"].exec("PASTE_HTML", []);
        },
	});

    // 글 번호 없으면 수정/삭제버튼 숨김
    var idVal = $('#postId').val();
    // 작성자인지 확인하고, 아니면 수정/삭제버튼 숨김
    var loginIdVal  = $('#userId').val();
    var authorIdVal = $('#authorId').val();

    if (idVal == null || idVal == "") {
        $("#btn-delete").hide();
        $("#btn-update").hide();
    } else if (loginIdVal != authorIdVal || loginIdVal != authorIdVal) {
        $("#btn-delete").hide();
        $("#btn-save").hide();
        $("#btn-update").hide();
    } else {
        $("#btn-save").hide();
    }

    // 저장
    $("#btn-save").click(function() {
        oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);
        fn_insert();
    });

    // 수정
    $("#btn-update").click(function() {
        oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);
        fn_update();
    });

    // 삭제
    $("#btn-delete").click(function() {
        if (confirm("삭제 하시겠습니까?")){
            fn_delete();
        }
    });

    // 취소
    $("#btn-cancel").click(function() {
        document.location.href = "/";
    });

});

function fn_insert() {

    var userId = $('#dataForm #userId').val();
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

function fn_update() {
    var data = {
        title   : $('#title').val(),
        content : $('#content').val()
    };

console.log(data)

    var id = $('#postId').val();

    $.ajax({
        type        : 'PUT',
        url         : '/api/v1/posts/' + id,
        dataType    : 'json',
        contentType : 'application/json; charset=utf-8',
        data        : JSON.stringify(data)
    }).done(function () {
        alert("글이 수정 되었습니다.");
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