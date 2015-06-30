/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
window.onload = function () {        
    document.getElementById("CreateNnaj:numDoc").value = "";
    document.getElementById("CreateNnaj:edad").disabled = true;
    document.getElementById("CreateNnaj:razonLibreta").disabled = true;
    document.getElementById("CreateNnaj:razonDoc").disabled = true;
}
function changeTipoDocumento(par) {
    if (par.value == "NT") {
        document.getElementById("CreateNnaj:numDoc").disabled = true;
        document.getElementById("CreateNnaj:numDoc").value = "";
        document.getElementById("CreateNnaj:razonDoc").disabled = false;        
    } else {
        document.getElementById("CreateNnaj:numDoc").disabled = false;
        document.getElementById("CreateNnaj:razonDoc").disabled = true;
    }
    
    if (par.value == "SD") {
        document.getElementById("CreateNnaj:numDoc").disabled = true;
        document.getElementById("CreateNnaj:numDoc").value = "SD";
    }
}
function changeTipoLibreta(par) {    
    if (par.value == "LNT") {
        document.getElementById("CreateNnaj:numLibreta").disabled = true;
        document.getElementById("CreateNnaj:numLibreta").value = "";
        document.getElementById("CreateNnaj:razonLibreta").disabled = false;
        document.getElementById("CreateNnaj:portaLM").disabled = true;
    } else {
        document.getElementById("CreateNnaj:numLibreta").disabled = false;
        document.getElementById("CreateNnaj:portaLM").disabled = false;
        document.getElementById("CreateNnaj:razonLibreta").disabled = true;
    }
}
function habilitarEdad(show) {
    // Get the panel using its ID
    var obj = document.getElementById("CreateNnaj:sEstimada");
    if (show) {
        document.getElementById("CreateNnaj:edad").disabled = false;
    } else {
        document.getElementById("CreateNnaj:edad").disabled = true;
    }
}
function calcularEdadEstimda(){
    var f = new Date();
    var edadActual = document.getElementById("CreateNnaj:edad").value;    
   // alert((f.getDate() + "/" + (f.getMonth() + 1) + "/" + (f.getFullYear()-edadActual))+"");
    document.getElementById("CreateNnaj:fNacimiento").value = (f.getDate() + "/" + (f.getMonth() + 1) + "/" + (f.getFullYear()-edadActual))+"";
}

function obtenerEdad(){   
    alert("Ingrese a obtener edad "+fecha);
	 var fechaNacimiento = crearDate(fecha.value);
	 var fechaActual = new Date();
	 var mes=fechaActual.getMonth()-fechaNacimiento.getMonth();
	 var dia=fechaActual.getDate()-fechaNacimiento.getDate();
	 
	 var edadT=(fechaActual.getFullYear()-fechaNacimiento.getFullYear());
	 if(mes<0){
		 edadT=edadT-1;
	 }else if(((mes)==0)&&(dia<0)){
		 edadT=edadT-1;
	 }
         document.getElementById("CreateNnaj:edad").value = edadT;	 
}

function crearDate(fstring){
     var ano;
     var mes;
     var dia;

     if(FormatoFecha == "AAAA-MM-DD"){
		ano = parseInt(fstring.substring(0,4),10);
		mes = parseInt(fstring.substring(5,7),10)-1;
		dia = parseInt(fstring.substring(8,10),10);
     }
     else if(FormatoFecha == "DD/MM/AAAA"){
		dia = parseInt(fstring.substring(0,2),10);
		mes = parseInt(fstring.substring(3,5),10)-1;
		ano = parseInt(fstring.substring(6,10),10);
     }

     var someDate = new Date(ano,mes,dia);
     return someDate;
}




