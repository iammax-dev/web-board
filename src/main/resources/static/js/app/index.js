$(document).ready(function(){
    
	$("#postCreate").click(function() {
		document.location.href = "/posts/save";
	});

    $("#search").on("click", function(event){
        self.location = "/search?selectSearch=" + $("#selectSearch option:selected").val()
            + "&keyword=" + $('#keyword').val()
    });

});