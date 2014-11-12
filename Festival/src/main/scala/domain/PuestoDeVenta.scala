package domain


import org.uqbar.commons.utils.Observable
import org.uqbar.commons.model.Entity

@Observable
class PuestoDeVenta(unNombre:String) extends Entity{
	var entradasVendidas: List[Entrada] = List.empty[Entrada];
	var nombre: String = unNombre;

	
	override def toString():String ={
	  return nombre
	}
	
	def agregarEntrada(unaEntrada:Entrada){
	  entradasVendidas = entradasVendidas.+:(unaEntrada);
	}
	
	def sacarEntrada(unaEntrada:Entrada){
	  entradasVendidas =entradasVendidas.diff(List(unaEntrada));
	}

}