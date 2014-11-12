package ar.edu.celulares.applicationModel

import domain.Entrada
import ar.edu.celulares.home.HomeEntradas
import ar.edu.celulares.home.HomeEntradas
import domain.Cliente
import domain.Noche
import domain.Butaca
import java.util.Date
import domain.TipoDePago
import domain.TipoCliente
import org.joda.time.DateTime
import domain.Festival
import domain.PuestoDeVenta


@org.uqbar.commons.utils.Observable
class MostradorEntrada extends Serializable {

	var cliente: Cliente=_
	var tipoCliente: TipoCliente=_
	var noche: Noche=_
	var butaca: Butaca=_
	var nroFactura : Int=_
	var precioDeVenta: Double=_
	var fechaCompra: DateTime=_
	var devuelta: Boolean = false
	var festival: Festival =_
	var puestoDeVenta:PuestoDeVenta=_
	
	def precioEnPesos:String ={ return "$" + precioDeVenta}
	
	//def nombreFestival:String ={fechaCompra.toString()}

	// ********************************************************
	// ** Acciones
	// ********************************************************

	def cargarEntradaharco = { 
	  var entrada: Entrada = HomeEntradas.createExample
	  cliente = entrada.cliente
	  noche = entrada.noche
	  butaca = entrada.butaca
	  nroFactura = entrada.nroFactura
	  precioDeVenta= entrada.precioDeVenta
	  fechaCompra= entrada.fechaCompra
	  tipoCliente = entrada.tipoCliente
	  devuelta= entrada.devuelta
	  festival = entrada.festival
	  puestoDeVenta = entrada.puestoDeVenta
	  
	  
	  
	}
	
	

	def clear() = {
		nroFactura = 0
	}



}