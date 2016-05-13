function buttomMostrarOcultarLinha(i) {
    if( $('#linha_'+i).is(':visible') ) {
        $("#linha_"+i).slideUp();
        $('#button_'+i).removeClass('menos').addClass('mais');
    } else {
        $("#linha_"+i).slideDown(); 
        $('#button_'+i).removeClass('mais').addClass('menos');
    }
}