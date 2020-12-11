$(document).ready(function(){
    // 글 관련

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

    // 댓글 관련

    // 댓글 저장
    $("#replySubmit").click(function() {
        fn_replySubmit();
    });

    // 댓글 수정
    $('button[name=replyModify]').click(function() {
        fn_replyModify();
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

    var userId = $('#dataForm #userId').val();
    var postId = $('#postId').val();
	var replyName = $("#replyName").val();
	var replyContents = $("#replyContents").val();

    if (replyContents != null && replyContents != "") {

       var data = {
            post_id      : parseInt(postId),
            user_id      : parseInt(userId),
            replyAuthor  : replyName,
            replyContent : replyContents
        };

    console.log(data)

        $.ajax({
            type        : 'POST',
            url         : '/api/reply/',
            dataType    : 'json',
            contentType : 'application/json; charset=utf-8',
            data        : JSON.stringify(data)
        }).done(function () {
            alert("댓글이 등록 되었습니다.");
            location.reload();
        }).fail(function (error) {
            alert("에러임 ㅇㅇ");
            console.log(JSON.stringify(error));
        });

    } else {
        alert("댓글 내용을 입력해주세요");
    }

}

function fn_replyModify() {

	$(event.target).closest('tr').find("input[name='rContents']").attr("readonly", false);
	$(event.target).closest('tr').find("input[name='rContents']").addClass("form-control");
	$(event.target).closest('tr').find("input[name='rContents']").focus();

	$(event.target).closest('tr').find("td[name='appendTd']").prepend("<button type='button' class='btn btn-outline-success btn-sm' id='modifyComplete' onclick=fn_replyModifyComplete()>수정 완료</button>")
	$(event.target).closest('tr').find("button[name='replyModify']").remove();

}

function fn_replyModifyComplete() {

	var ridx = $(event.target).closest('tr').find("input[name='ridx']").val();
	var rName = $(event.target).closest('tr').find("input[name='rName']").val();
	var rContent = $(event.target).closest('tr').find("input[name='rContents']").val();

    var data = {
        post_id      : $("#postId").val(),
        replyId      : ridx,
        replyAuthor  : rName,
        replyContent : rContent
    };
console.log(data)
	$.ajax({
        url: '/updateReply',
        type: 'POST',
        data: oPostDt,
        contentType: 'application/json; charset=UTF-8',
        success: function () {
        	alert("수정 되었습니다.");
        	location.reload();
        },
        error: function (resultMap) {
            alert("error " );
        }
    });

}

function fn_replyDelete() {

	var ridx = $(event.target).closest('tr').find("input[name='ridx']").val();
	console.log(ridx)

	var bDelete = confirm("삭제하시겠습니까?");
	if (bDelete) {

		var oParam = {};
		oParam["idx"]       = $("#dataForm #idx").val();
		oParam["ridx"]      = ridx;

	    var oPostDt = JSON.stringify(oParam);

		$.ajax({
	        url: '/deleteReply',
	        type: 'POST',
	        data: oPostDt,
	        contentType: 'application/json; charset=UTF-8',
	        success: function () {
	        	alert("삭제 되었습니다.");
	        	location.reload();
	        },
	        error: function (resultMap) {
	            alert("error " );
	        }
	    });

	}

}