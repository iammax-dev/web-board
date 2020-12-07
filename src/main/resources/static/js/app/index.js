$(document).ready(function(){
    
	$("#postCreate").click(function() {
		document.location.href = "/posts/save";
	});

    $("#search").on("click", function(event){
        self.location = "/search?selectSearch=" + $("#selectSearch option:selected").val()
            + "&keyword=" + $('#keyword').val()
    });

});


//function fn_search() {
//
//    var selectSearch = $("#selectSearch option:selected").val();
//    var keyword      = $('#keyword').val();
//    var data = {
//        selectSearch : selectSearch,
//        keyword      : keyword
//    };
//
//console.log(data)
//jQuery.ajaxSettings.traditional = true;
//    $.ajax({
//        type        : 'GET',
//        url         : '/search',
////        dataType    : 'json',
////        contentType : 'application/json; charset=utf-8',
//        data        : data,
//        success: function () {
//            alert("검색성공.");
//            document.location.href = "/search?selectSearch=" selectSearch + "&keyword=" + keyword
//        },
//        error: function (error) {
//            alert(JSON.stringify(error));
//        }
//    });
//}