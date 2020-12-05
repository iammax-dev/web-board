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


    // 저장
    $("#btn-save").click(function() {
        oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);
        fn_insert();
    });

    $("#btn-cancel").click(function() {
        document.location.href = "/";
    });

});

function fn_insert() {

    var content = $('#dataForm #content').val()

//    if( content == ""  || content == null || content == '&nbsp;' || content == '<p>&nbsp;</p>')  {
//         alert("내용을 입력하세요.");
//         oEditors.getById["content"].exec("FOCUS"); //포커싱
//         return;
//    }

    var data = {
        title   : $("#dataForm #title").val(),
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