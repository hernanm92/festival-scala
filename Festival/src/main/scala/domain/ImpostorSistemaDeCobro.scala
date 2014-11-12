package domain

import org.junit.Assert._


class ImpostorSistemaDeCobro(estaConectado:Boolean,valida:Boolean) extends SistemaDeCobro{
	  var _estaConectado = estaConectado;
	  var _valida = valida;
    
	override def cobrar(precioDeVenta:Double, nombreCliente:String, numeroTarjeta:String){
	  estaConectado();
	  validar();
	}
	
	
	override def estaConectado(){
	  if(estaConectado==false){
	    throw new DesconexionException();
	  }
	}
	
	override def validar(){
	  if (valida ==false){
	    throw new ValidacionException();
	  }
	}
  }