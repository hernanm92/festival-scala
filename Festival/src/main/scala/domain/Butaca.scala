package domain

import org.uqbar.commons.utils.Observable
import org.uqbar.commons.model.Entity

@Observable
class Butaca(unSector : Sector, unaFila:Fila,unId:Int)extends Entity {

		var sector : Sector = unSector;
		var fila : Fila = unaFila;
		var id: Int = unId;
		var codigo: String = "";
		
		def precioBase(): Double ={
		  return sector.precioBase + fila.precioBase;
		}
		
		/**IMPORTANTE!!!!!!!!
		 * La butaca tiene estado!!
		 * EL codigo es propio de un festival, entonces las butacas no se 
		 * pueden compartir entre festivales, a menos queeee
		 * haya una coleccion de codigos y listo.
		 * Pensarlo bien. TODO  
		 * **/
		
		
		def setCodigo(unCodigo: String){
		  codigo=unCodigo;
		}
		
		override def toString():String ={
		  return ""+id+" (Sector "+ sector.toString+", Fila "+fila.toString +")"
		}
		
}