class Proyecto {
	var tareas: List[Tarea] = List.empty[Tarea];
	
	def obtenerTiempoTotal():Integer = {
	  var _tiempo:Integer =0;
	  tareas.foreach
	  	{ tarea => _tiempo=_tiempo + tarea.obtenerTiempo() }
	  return _tiempo;
	}
	
	def obtenerCostoTotal():Double = {
	  var _costo:Double =0;
	  tareas.foreach
	  	{ tarea => _costo=_costo + tarea.obtenerCostoConImpuesto() }
	  return _costo;
	}
}