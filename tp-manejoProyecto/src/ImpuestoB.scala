class ImpuestoB(_porcentaje:Double) extends Impuesto{
	porcentaje=_porcentaje;
	
	def costoImpositivo(costoTareas:Double):Double = {
	  return costoTareas*1.05;
	}
}