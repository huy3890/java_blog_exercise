$(function() {
    $('#messages li').click(function() {
        $(this).fadeOut();
    });
    $('#datetimepicker1').datetimepicker();
    $("#publishPostLink").on("click", function(e) {
    	var link = $(this).attr('href');
    	var date = $('#datetimepicker1').data("DateTimePicker").date().unix();
//    	e.preventDefault();
    	$(this).attr("href", link+"/"+date);
    });
    $('div#froala-editor').froalaEditor();
    setTimeout(function() {
        $('#messages li.info').fadeOut();
    }, 3000);
});