var main = {
    init : function(){
        var _this = this;

        $('#btn-submit').on('click', function() {
            _this.update();
        });

        $('#btn-delete').on('click', function() {
            _this.delete();
        });

        $("#btn-cancel").click(function() {
            document.location.href = "/";
        });
    },
    update : function() {
        var data = {
            title   : $('#title').val(),
            content : $('#content').val()
        };

        var id = $('#id').val();

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
    },
    delete : function() {
        var id = $('#id').val();

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
};

main.init();