package domain
object NroFactura {

	var _nroFactura = 1;
	
	
	def SacarNroFactura(): Int =	{
		var auxiliar = _nroFactura;
		_nroFactura += 1;
		return auxiliar;
	}
}