package ar.edu.celulares.controller

import com.uqbar.commons.collections.Transformer
import domain.Entrada

class FechaTransformer extends Transformer[Entrada, String] {

	override def transform(entrada: Entrada) : String = 
	  return entrada.fechaCompra.getYear() + "-" +
	  entrada.fechaCompra.getMonthOfYear() + "-" +
	  entrada.fechaCompra.getDayOfMonth()
	
}