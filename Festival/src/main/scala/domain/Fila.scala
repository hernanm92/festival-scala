package domain
import org.uqbar.commons.utils.Observable
import org.uqbar.commons.model.Entity

@Observable
class Fila(unNumero:Int, unPrecioBase:Double)extends Entity {
	var numeroFila: Int = unNumero;
	var _precioBase:Double = unPrecioBase;
	
	def precioBase = _precioBase
	
	override def toString():String = {
	  return "" +numeroFila + ""
	}
}