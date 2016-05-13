var Manage = {
    init: function(){
        Manage.addHandlers();
    },
    addHandlers: function(){
       // Manage.addFormHandlers();
        $("#btCapturar").unbind().click(function(){
            var val = "digitalAjaxRead.do?id="+$("#id").val();
            $.post(val, $(this).serialize(), function(d){
                $("#corpoDigitais").html(d);
            });
            return false;
        });
    },
    addFormHandlers: function(){
        $("#digitalForm").unbind().submit(function(){
            $.post($(this).attr("action"), $(this).serialize(), Manage.searchCallback);
            return false;
        });
    },
    searchCallback: function(d){
        $("#corpoDigitais").html(d);
        $(".btnDelete").unbind().click(Manage.btnDeleteClick);
    }
}

$(function(){
    Manage.init();
});