package domain



import org.joda.time._
import org.joda.convert._

import org.uqbar.commons.model.Entity
import org.uqbar.commons.utils.Observable
import scala.collection.immutable.Nil
import scala.util.control.Exception

@Observable
abstract class Entrada(unPuesto:PuestoDeVenta, uncliente: Cliente, unTipoCliente: TipoCliente, unaNoche: Noche, unaButaca: Butaca,
    fechaDeCompra: DateTime = new DateTime("2013-12-01")) extends Entity {

	var cliente: Cliente= uncliente;
	var tipoCliente: TipoCliente= unTipoCliente;
	var noche: Noche= unaNoche;
	var butaca: Butaca= unaButaca;
	var nroFactura : Int= NroFactura.SacarNroFactura;
	var precioDeVenta: Double=_;
	var fechaCompra: DateTime = fechaDeCompra
	var devuelta: Boolean = false;
	var puestoDeVenta: PuestoDeVenta = unPuesto

	/*
tipoDePago que tenga un objeto de una clase pagoEnEfectivos o de una clase pagoConTarjeta. 
Que en el metodo comprar de la entrada llame a tipoDePago.comprar()
*/


def comprar();
	
	def nombreCliente = cliente.toString
  
def festival = noche.festival

def anular();

def anularVenta(){
  	festival.entradasVendidas = festival.entradasVendidas.filter(entrada=> entrada != this)
}	//Saca la entrada de la lista de entradas vendidas

  def precioFinal(): Double; 
  
  def imprimir(){
	  /*envia imprimir a la impresora fiscal*/
  }
  	
  def devolver(fechaDevolucion : DateTime): Double;
 
}