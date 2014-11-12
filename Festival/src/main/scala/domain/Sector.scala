package domain

import org.uqbar.commons.utils.Observable
import org.uqbar.commons.model.Entity

@Observable
class Sector(unPrecio: Double, unSector: Char) extends Entity {
	var _precioBase:Double = unPrecio;
	var id: Char = unSector;

	def precioBase = _precioBase
	
	override def toString(): String ={
	  return "" + id + ""
	}
}