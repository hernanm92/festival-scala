package domain

import org.joda.time._
import org.joda.convert._
import org.uqbar.commons.utils.Observable
import org.joda.time.format.DateTimeFormatter
import org.uqbar.commons.model.Entity

@Observable
class Festival(unNombre:String = "") extends Entity {

  var noches: List[Noche] = List.empty[Noche];
  var entradasVendidas: List[Entrada] = List.empty[Entrada];
  var diasMinDescuentoAnticipada: Int = 30;
  var porcentajeDescuentoAnticipada: Double = 0.10;
  var bandas: List[Banda] = List.empty[Banda];
  var descuentos: List[String] = List.empty[String];
  var pagosPendientes: List[Pago] = List.empty[Pago];
  var nombre: String = unNombre

  def cantidadEntradas(): Int = {
    var entradas = 0;
    for (unaNoche <- noches) {
      entradas += unaNoche.butacas.length;
    }
    return entradas;
  }

  def entradasDeMujeresConDescVendidas(): Int = {
    entradasVendidas.filter(entrada => (entrada.tipoCliente.isInstanceOf[TipoCliente_Mujer])).length;
    return 0;
  }

  def buscarEntradaVendida(unNroFactura: Int): Entrada = {
    var nro = unNroFactura
    return entradasVendidas.filter(e => (e.nroFactura == nro)).head
  }

  def calcularDescuentoAnticipa(precio: Double, noche: Noche, fechaDeCompra: DateTime): Double = {

    var diffInDays = diferenciasDeFechas(fechaDeCompra, noche.fecha);
    if (diffInDays > diasMinDescuentoAnticipada) {
      return precio * porcentajeDescuentoAnticipada;
    } else {
      return 0.0;
    }
    return 0.0;
  }

  def diferenciasDeFechas(fechaInicial: DateTime, fechaFinal: DateTime): Int = Days.daysBetween(fechaInicial, fechaFinal).getDays()

  //Funcion que realiza el cobro de todas los pagos pendientes
  //TODO Completar para que se pueda implementar los pagos luego de que queden pendientes
  def cobrarPagos() {
    for (unPago <- this.pagosPendientes) {
      unPago.cobrar()
    }

  }

  def cambiarCategoriaBanda(nombreBanda: String, id: Int, costoExtra: Double) {
    var bandaBuscada = ((bandas.filter(unaBanda => unaBanda.nombre == nombreBanda)).head)
    bandaBuscada.categoria(new Categoria(id, costoExtra))
  }

  def agregarPagoPendiente(unPago: Pago) {
    pagosPendientes = pagosPendientes.+:(unPago);
  }

  def agregarBanda(unaBanda: Banda) {
    bandas = bandas.+:(unaBanda);
  }

  def agregarDescuento(unDescuento: String) {
    descuentos = descuentos.+:(unDescuento);
  }

  def sacarDescuento(unDescuento: String) {
    var nuevosDescuentos = descuentos.filterNot(elemento => elemento.equals(unDescuento));
    descuentos = nuevosDescuentos;

  }
  
  override def toString():String = {return nombre}

}
