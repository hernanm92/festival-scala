class Tarea(_tiempo:Integer, _complejidad:ComplejidadMinima, _impuesto:List[Impuesto]) {
  
	var impuesto:List[Impuesto] = _impuesto;
	var complejidad:ComplejidadMinima =_complejidad;
	var tiempo: Integer=_tiempo;

	
	def obtenerCostoSinImpuesto():Double = {
	  return complejidad.obtenerCosto(tiempo);
	}

	def obtenerCostoConImpuesto():Double = {
	  var _costo:Double=obtenerCostoSinImpuesto();
	  impuesto.foreach
	  	{ unImpuesto => _costo= unImpuesto.costoImpositivo(_costo) }
	   return _costo;
	}
	def obtenerTiempo():Integer = {
	  return tiempo;
	}
	def tieneConTiempoMenorA(cantDias:Integer):Boolean ={
	  return tiempo<cantDias;
	}
	
}