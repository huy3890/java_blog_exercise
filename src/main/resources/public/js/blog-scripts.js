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
    	var value = $('#input-froala-editor').parent().parent().html();
    	$('#richtext').val(value);
    });
//    $('div#froala-editor').froalaEditor();
    $('div#froala-editor').froalaEditor({
    	toolbarButtons: ['fullscreen', 'bold', 'italic', 'underline', 'strikeThrough', 'subscript', 'superscript', '|', 'fontFamily', 'fontSize', 'color', 'inlineStyle', 'paragraphStyle', '|', 'paragraphFormat', 'align', 'formatOL', 'formatUL', 'outdent', 'indent', 'quote', '-', 'insertLink', 'insertImage', 'insertVideo', 'insertFile', 'insertTable', '|', 'emoticons', 'specialCharacters', 'insertHR', 'selectAll', 'clearFormatting', '|', 'print', 'help', 'html', '|', 'undo', 'redo'],
    	imageUploadURL: '/upload_image',
    	imageUploadParams: {
          id: 'my_editor'
        },
        fileUploadURL: '/upload_file',
        fileUploadParams: {
          id: 'my_editor'
        },
        imageManagerLoadURL: '/load_images',
        imageManagerDeleteURL: "/delete_image",
        imageManagerDeleteMethod: "POST"
    })
 // Catch image removal from the editor.
    .on('froalaEditor.image.removed', function (e, editor, $img) {
      $.ajax({
        // Request method.
        method: "POST",
        // Request URL.
        url: "/delete_image",
        // Request params.
        data: {
          src: $img.attr('src')
        }
      })
      .done (function (data) {
        console.log ('image was deleted');
      })
      .fail (function (err) {
        console.log ('image delete problem: ' + JSON.stringify(err));
      })
    })
    // Catch image removal from the editor.
    .on('froalaEditor.file.unlink', function (e, editor, link) {
      $.ajax({
        // Request method.
        method: "POST",
        // Request URL.
        url: "/delete_file",
        // Request params.
        data: {
          src: link.getAttribute('href')
        }
      })
      .done (function (data) {
        console.log ('file was deleted');
      })
      .fail (function (err) {
        console.log ('file delete problem: ' + JSON.stringify(err));
      })
    });
//    setTimeout(function() {
//        $('#messages li.info').fadeOut();
//    }, 3000);
});