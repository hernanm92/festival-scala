
//import java.util.Conversion

package domain

import org.uqbar.commons.utils.Observable
import scala.collection.immutable.Nil
import java.sql.Struct
import org.uqbar.commons.model.Entity

//import java.util.Conversion

@Observable
abstract class TipoDePago(unNombre:String="") extends Entity {
  var nombre:String = unNombre

  def comprar(unaEntrada: Entrada): Boolean;
  
  override def toString():String = nombre
}

class PagoEnEfectivo(unNombre:String="") extends TipoDePago(unNombre) {
  def comprar(unaEntrada: Entrada): Boolean = {

    var festival = unaEntrada.festival

    if (festival.entradasVendidas.==(unaEntrada)) {
      return false;
    }
    unaEntrada.comprar();
    return true;

  }

}

class PagoConTarjeta(unNombre:String="") extends TipoDePago(unNombre) {
  var _sisCobro: SistemaDeCobro = null

  override def comprar(unaEntrada: Entrada): Boolean = {

    var festival = unaEntrada.festival

    if (festival.entradasVendidas.==(unaEntrada)) {
      return false; //NO encuentra la estrada en la lista de vendidas 
    }
    val nombreCliente = unaEntrada.cliente._apellido.+(", ").+(unaEntrada.cliente._nombre);
    val numeroTarjeta = unaEntrada.cliente._nroTarjeta;

    unaEntrada.comprar();

    try {
      _sisCobro.cobrar(unaEntrada.precioDeVenta, nombreCliente, numeroTarjeta);
      return true;
    } catch {
      case e: DesconexionException => { festival.agregarPagoPendiente(new Pago(unaEntrada, nombreCliente, numeroTarjeta)); unaEntrada.anularVenta }
      case e: ValidacionException => unaEntrada.anular()
      //loguear venta no realizada o informar por pantalla
    }
    return false;
  }

} 

