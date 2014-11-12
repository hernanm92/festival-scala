package domain

import org.uqbar.commons.utils.Observable

@Observable
class Pago(entrada:Entrada,cliente:String,tarjeta:String) {
	var _entrada = entrada
	var _cliente = cliente
	var _tarjeta = tarjeta
	
	def cobrar(){}
}