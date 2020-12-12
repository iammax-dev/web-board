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

    // 댓글 삭제
    $("button[name=replyDelete]").click(function() {
        fn_replyDelete();
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
    $(event.target).closest('tr').find("span[name='rContents']").remove();
    $(event.target).closest('tr').find("span[name='rContents']").append()
    $(event.target).closest('tr').find("td[name='rModify']").prepend('<input type="text" name="rModifyContents" id="replyContents" class="form-control form-control-sm col-sm-8" th:value="${list.replyContent}">')
	$(event.target).closest('tr').find("input[name='rModifyContents']").focus();

	$(event.target).closest('tr').find("td[name='appendTd']").prepend("<button type='button' class='btn btn-outline-success btn-sm' id='modifyComplete' onclick=fn_replyModifyComplete()>수정 완료</button>")
	$(event.target).closest('tr').find("button[name='replyModify']").remove();

}

function fn_replyModifyComplete() {

    var replyId             = $(event.target).closest('tr').find("input[name='ridx']").val();
    var replyModifyContents = $(event.target).closest('tr').find("input[name='rModifyContents']").val();

    var data = {
        id      : parseInt(replyId),
        replyContent : replyModifyContents
    };

console.log(data)

	$.ajax({
	    type        : 'PUT',
        url         : '/api/reply/' + replyId,
        data        : JSON.stringify(data),
        contentType : 'application/json; charset=UTF-8',
        success: function () {
        	alert("댓글이 수정 되었습니다.");
        	location.reload();
        },
        error: function (resultMap) {
            alert("error " );
            console.log(JSON.stringify(error));
        }
    });

}

function fn_replyDelete() {

	var replyId = $(event.target).closest('tr').find("input[name='ridx']").val();

console.log(replyId)

	var bDelete = confirm("삭제하시겠습니까?");
	if (bDelete) {

        var data = {
            id      : parseInt(replyId)
        };

		$.ajax({
            type        : 'DELETE',
            url         : '/api/reply/' + replyId,
            data        : JSON.stringify(data),
            contentType : 'application/json; charset=UTF-8',
            success: function () {
                alert("댓글이 삭제 되었습니다.");
                location.reload();
            },
            error: function (resultMap) {
                alert("error " );
                console.log(JSON.stringify(error));
            }
        });

	}

}