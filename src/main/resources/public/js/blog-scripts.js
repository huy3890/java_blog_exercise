$(function() {
	setTimeout(function(){ 
		$('#messages li').fadeOut();
	}, 3000);
    $('#messages li').click(function() {
        $(this).fadeOut();
    });
    $('#datetimepicker1').datetimepicker();
    $("#publishPostLink").on("click", function(e) {
    	var link = $(this).attr('href');
    	var dateValue = $('#datetimepicker1').find("input").val();
    	if(!dateValue){
    		e.preventDefault();
    		alert("Please choose a date time to publish this post!");
    	}
    	var date = $('#datetimepicker1').data("DateTimePicker").date().unix();
//    	e.preventDefault();
    	$(this).attr("href", link+"/"+date);
    });
    $("#deleteUser").on("click", function(e) {
    	e.preventDefault();
    	$('#confirm').modal({
    	      backdrop: 'static',
    	      keyboard: false
    	    })
    	    .one('click', '#delete', function(e) {
//    	      $form.trigger('submit');
    	    });
    });
    $("#createPost").on("click", function(e) {
    	var value = $('#input-froala-editor').html();
    	$('#richtext').val(value);
    });
    $('div#froala-editor').froalaEditor();
//    setTimeout(function() {
//        $('#messages li.info').fadeOut();
//    }, 3000);
});