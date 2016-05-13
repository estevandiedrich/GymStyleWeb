function eventoSelectRadioCriterio(n){
    var num = document.getElementById("criterioDebitoDia").value;
    if(n == num){
        if(n==0){
            document.getElementById("criterioDebito").checked = false;
        }
        if(n==1){
            document.getElementById("criterioEmDia").checked = false;
        }    
        document.getElementById("criterioDebitoDia").value = -1;
        document.getElementById("criterioTodos").checked = true;
    }else{
        document.getElementById("criterioDebitoDia").value = n;
    }
    submitFormRead();
}