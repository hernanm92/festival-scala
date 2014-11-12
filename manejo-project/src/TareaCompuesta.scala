class TareaCompuesta(_tiempo:Integer, _complejidad:ComplejidadMinima, _impuesto:List[Impuesto]) extends Tarea(_tiempo, _complejidad, _impuesto){
	var subTareas: List[Tarea] = List.empty[Tarea];
	
	override def obtenerCostoConImpuesto():Double = {
	  var _costo:Double =0;
	  subTareas.foreach
	  	{ tarea => _costo=_costo + tarea.obtenerCostoConImpuesto() }
	  
	  if (tieneOverhead())
	  	return _costo*1.04;
	  return _costo;
	}
	def agregarTarea(unaTarea:Tarea){
	  subTareas:+unaTarea;
	}
	
	
	def tieneOverhead():Boolean={	
	  return (subTareas.length>3);
	}
	
	override def obtenerTiempo():Integer ={
	  var _tiempo:Integer =0;
	  subTareas.foreach
	  	{ tarea => _tiempo=_tiempo + tarea.obtenerTiempo() }
	  return _tiempo;
	}
	

}