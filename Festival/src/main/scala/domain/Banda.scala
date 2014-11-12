package domain

import org.uqbar.commons.utils.Observable
import org.uqbar.commons.model.Entity

@Observable
class Banda(unCategoria:Categoria,unNombre:String) extends Entity{
	var _categoria:Categoria = unCategoria;
	var nombre: String = unNombre;
	
	def cat = _categoria.toString;
	
	def categoria = _categoria;
	def categoria(unaCategoria: Categoria){
			_categoria = unaCategoria
	}
	
	override def toString():String ={
	  return nombre
	}

}