package ar.edu.celulares.applicationModel

import scala.collection.JavaConversions._
import domain.Entrada
import ar.edu.celulares.home.HomeEntradas
import ar.edu.celulares.home._
import domain._
import domain.Cliente
import domain.Entrada
import domain.EntradaComun
import org.joda.time._
import org.joda.convert._
import domain.EntradaVIP
import domain.Noche
import domain.Butaca
import java.util.Date
import domain.TipoDePago
import domain.TipoCliente
import domain.Sector
import domain.Fila
import domain.Festival
import ar.edu.celulares.home.HomeNoches
import ar.edu.celulares.home.HomeButacas
import domain.PuestoDeVenta
import scala.collection.JavaConversions.asScalaBuffer
import scala.collection.JavaConverters._
import scala.collection.mutable.ListBuffer
import java.util.Collections
import java.util.ArrayList




@org.uqbar.commons.utils.Observable
class VendedorEntrada extends Serializable {
  
  type JList[A] = java.util.List[A]
  //	Para todas las entradas
  var tipoDePago: TipoDePago = _
  var nombre: String = _
  var apellido: String = _
  var entradas: JList[Entrada] = List.empty[Entrada]
  var entradaSeleccionada: Entrada = _
  var _festival: Festival = _
  var puestoDeVenta:PuestoDeVenta=_
  
  /**Getters y Setters necesarios para algunos combos que dependen de otros**/
  
  /**No se si esta bien el filter con esas condiciones pero funciona PARECE**/
  def festival = _festival
  def festival_=(unFestival: Festival) {
    _festival = unFestival
    nochesDisponibles = HomeNoches.noches.filter{unaNoche=> HomeEntradas.coincide(_festival, unaNoche.festival) }
  }
  
  def fila = _fila
  def fila_=(unaFila: Fila) {
    _fila = unaFila
    butacasDisponibles = HomeButacas.butacas.filter { x => (x.fila == fila && x.sector == sector)}
  }
  
  def sector = _sector
  def sector_=(unSector: Sector) {
    _sector = unSector
    butacasDisponibles = HomeButacas.butacas.filter {x => (x.fila == fila && x.sector == sector)}
  }
  
  /**Fin Getters y Setters**/
  

  //	Para cada Entrada
  var tipoEntrada: Entrada = _
  var noche: Noche = _
  var tipoDeCliente: TipoCliente = _
  var butaca: Butaca = _
  var _fila: Fila = _
  var _sector: Sector = _
  var precioDeVenta: Double = _
  
  
  var nochesDisponibles:JList[Noche] = _
  var butacasDisponibles: JList[Butaca] = _
  def precioEnPesos: String = { return "$" + precioDeVenta }

  def clearTodo() = {
    tipoDePago = null
    nombre = null
    apellido = null
    entradas = List.empty[Entrada]
    festival = null
    clearEntrada()
  }

  def clearEntrada() = {
    tipoEntrada = null
    noche = null
    tipoDeCliente = null
    butaca = null
    fila = null
    sector = null
    precioDeVenta = 0.0
  }

 def agregarEntrada() = {
   if (!(butaca==null || tipoDeCliente==null || noche==null || puestoDeVenta==null)){ 
	var entrada = new EntradaComun(puestoDeVenta,new Cliente(nombre,apellido,"","naranaa"),tipoDeCliente,noche,butaca, new DateTime())
	entradas = entradas.+:(entrada)
	HomeButacas.delete(entrada.butaca)
	clearEntrada()}
 }
 
 def eliminarEntradaSeleccionada() ={
   HomeButacas.create(entradaSeleccionada.butaca)
   entradas = entradas.-(entradaSeleccionada)
 }
 
 def comprarEntradas() = {
   var entradasPedido = List.empty[Entrada]
   var pedido = new Pedido(puestoDeVenta, new Cliente(nombre,apellido,"","naranaa"), tipoDePago,DateTime.now());
   for (entradaAAgregar <- entradas){
     HomeEntradas.create(entradaAAgregar)
     entradasPedido = entradasPedido.+:(entradaAAgregar)
   }
   pedido._entradas = entradasPedido
   HomeEntradas.createEntradas(pedido)
   HomeClientes.create(nombre,apellido,"","")
   clearTodo()
 }
  
  
  
}