function eventoSelectRadioCriterio(n){
    var num = document.getElementById("criterioValue").value;
    if(n == num){
        if(n==0){
            document.getElementById("criterioPlano").checked = false;
        }
        if(n==1){
            document.getElementById("criterioPendencia").checked = false;
        }
        document.getElementById("criterioValue").value = -1;
    }else{
        document.getElementById("criterioValue").value = n;
    }
    submitFormRead();
}