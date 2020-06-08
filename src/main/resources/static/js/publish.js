function selectTag(value) {
    var previous = $('#tag').val();
    if(previous){
        if(previous.indexOf(value) == -1){
            $('#tag').val(previous +'，'+value);
        }
    }else {
        $('#tag').val(value);
    }
}
function openTag() {
  $('#select-tags').show ();
}