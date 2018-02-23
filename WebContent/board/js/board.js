
$(function summernote() {
    $('.summernote').summernote({
      placeholder: "내용을 입력해주세요",
      height: 350,          // 기본 높이값
      minHeight: null,      // 최소 높이값(null은 제한 없음)
      maxHeight: null,      // 최대 높이값(null은 제한 없음)
      maxWidth : 500,
      focus: true,          // 페이지가 열릴때 포커스를 지정함
      lang: 'ko-KR',      // 한국어 지정(기본값은 en-US)
      callbacks: {
          onImageUpload: function(files, editor, welEditable) {
            for (var i = files.length - 1; i >= 0; i--) {
              sendFile(files[i], this);
            }
          }
        },
    	  dialogsFade: true,
    	  fontNames: ['Roboto Light', 'Roboto Regular', 'Roboto Bold'],
    	  toolbar: [
    	  
    	  ['fontsize', ['fontsize']],
    	  ['font', ['style','bold', 'italic', 'underline', 'clear']],
    	  ['color', ['color']],
    	  ['para', ['ul', 'ol', 'paragraph']],
    	  //['height', ['height']],
    	  ['table', ['table']],
    	  ['insert', [/*'picture',*/'link']],
    	  //['view', ['fullscreen', 'codeview']],
    	  //['misc', ['undo','redo']]
    	  ]
    	  });
    	  });

function sendFile(file, el) {
    var form_data = new FormData();
    form_data.append('file', file);
    $.ajax({
      data: form_data,
      type: "POST",
      url: '/semi/writePro.ls',
      cache: false,
      contentType: false,
      enctype: 'multipart/form-data',
      processData: false,
      success: function(url) {
        $(el).summernote('editor.insertImage', url);
        $('#imageBoard > ul').append('<li><img src="'+url+'" width="480" height="auto"/></li>');
      }
    });
}

