package domain

import org.joda.time._
import org.joda.convert._

class EntradaVIP(unPuesto:PuestoDeVenta, uncliente: Cliente,unTipoCliente: TipoCliente, unaButaca: Butaca,fechaDeCompra: DateTime, unFestival:Festival)
	extends Entrada(unPuesto, uncliente, unTipoCliente, null , unaButaca,fechaDeCompra) {
  noche = unFestival.noches.head;
  precioDeVenta=this.precioFinal();

override def devolver(fechaDevolucion : DateTime): Double ={
		//Ver bien los return algo, no le preste atencion
		if (devuelta) {
			return -1;
		}
		if  (!festival.entradasVendidas.contains(this)){
			//NO encuentra la estrada en la lista de vendidas
			return -2;
		}
		if (fechaDevolucion.isAfter(noche.fecha.plusDays(10)) ){
	    //No se puede devolver porque estamos en los ultimos 10 dias
			return -3;
		}
		for(noche <- festival.noches){
			noche.butacasLibres=noche.butacasLibres.+:(butaca);
		}
		this.puestoDeVenta.sacarEntrada(this)
		
  	  	devuelta=true;
  	  	return precioDeVenta*0.5;
  	}


override def anular() {
  
	anularVenta
    for(noche <- festival.noches){
  		  noche.butacasLibres=noche.butacasLibres.+:(butaca);
  	  }
	this.puestoDeVenta.sacarEntrada(this)
  
}


override def precioFinal(): Double = {
	var valorEntradaBase = 0.0; 
	var valorExtraPorNoche = 0.0;
	var descuentoTipoPersona = 0.0; 
	var precio = 0.0; 
	var dtoAnticipada = 0.0;
	var subtot = 0.0;
	var total =0.0;
	
  for(noche <- festival.noches){
    valorEntradaBase = butaca.precioBase();
    valorExtraPorNoche = noche.valorExtra();
    descuentoTipoPersona = tipoCliente.dtoTipoPersona(valorEntradaBase,festival);
    precio = valorEntradaBase + valorExtraPorNoche - descuentoTipoPersona;
    dtoAnticipada = festival.calcularDescuentoAnticipa(precio, noche,this.fechaCompra);
    subtot = precio - dtoAnticipada;
    total= total + subtot;
  }
  return total*1.5;
}
  
  override def comprar() {
    //No tendria que ser un lista contiene entrada? en vez de un "=="?
    //O tendria que sacarlo si lo verifico antes
    	if  (festival.entradasVendidas.==(this)){
    		return ;
    	}
    	for(noche <- festival.noches){
    		noche.butacasLibres= noche.butacasLibres.diff(List(butaca));
    	}
    	festival.entradasVendidas=festival.entradasVendidas.+:(this);
    	this.puestoDeVenta.agregarEntrada(this)
    	this.imprimir(); 
    	return;
  }
  	  
}