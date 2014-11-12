package ar.edu.celulares.home

import scala.collection.JavaConversions._
import scala.collection.JavaConversions.asScalaBuffer
import org.uqbar.commons.model.CollectionBasedHome
import org.uqbar.commons.utils.Observable
import org.joda.time.DateTime
import domain._
import org.joda.time.Days

@Observable
object HomeEntradas extends CollectionBasedHome[Entrada] {
  
  this.cargarEntradasIniciales();
  

  def entradas: Seq[Entrada] = allInstances

  override def getEntityType = classOf[Entrada]

  override def createExample = new EntradaComun(HomePuestosDeVenta.createExample, HomeClientes.createExample, HomeTipoDeClientes.createExample,
    HomeNoches.createExample, HomeButacas.createExample)

  override def getCriterio(example: Entrada) = null
  
  def searchPuestoFestival(nombrePuesto:String="",nombreFestival:String="") = {
     entradas.filter { entrada =>
      this.coincide(nombrePuesto, entrada.puestoDeVenta.toString) &&
        this.coincide(nombreFestival, entrada.festival.toString)
    }
  }

  def search(nombreCliente: String = "", fechaDesde: String = "", fechaHasta: String = "") = {
   entradas.filter { entrada =>
      this.coincide(nombreCliente, entrada.cliente.toString) &&
        this.coincideFechas(entrada.fechaCompra, fechaDesde, fechaHasta)
    }
  }
  
  def buscarEntradasFestival(nombreCliente: String ,unFestival: Festival):Seq[Entrada] = {
   return entradas.filter { entrada =>
      	this.coincide(unFestival, entrada.festival.toString() ) && this.coincide(nombreCliente,entrada.cliente.toString())
    }
  }
  
  def dameNoches(nombreCliente:String,unFestival:Festival):Seq[Noche] = {
	return buscarEntradasFestival(nombreCliente,unFestival).map(unaEntrada => unaEntrada.noche)

  }
  
  def dameBandas(nombreCliente:String,unFestival:Festival): List[Banda] = {
	return dameNoches(nombreCliente:String,unFestival:Festival).foldLeft(List.empty[Banda]){( lista:List[Banda], unaNoche )=> lista.++(unaNoche.bandas)
	  }.removeDuplicates

  }

  def coincide(expectedValue: Any, realValue: Any): Boolean = {
    if (expectedValue == null) {
      return true
    }
    if (realValue == null) {
      return false
    }
    return realValue.toString().toLowerCase().contains(expectedValue.toString().toLowerCase())
  }

  def coincideFechas(fechaCompra: DateTime, fechaDesde: String, fechaHasta: String): Boolean = {

    if (fechaDesde == "" && fechaHasta == "")
      return true;
    if (fechaDesde == "")
      return Days.daysBetween(fechaCompra, new DateTime(fechaHasta)).getDays() > 0;
    if (fechaHasta == "")
      return Days.daysBetween(new DateTime(fechaDesde), fechaCompra).getDays() > 0;

    return Days.daysBetween(new DateTime(fechaDesde), fechaCompra).getDays() > 0 &&
      Days.daysBetween(fechaCompra, new DateTime(fechaHasta)).getDays() > 0;

    return true
  }

  def cargarEntradasIniciales() {
    var pedidosIniciales: List[Pedido] = List.empty
    var pedido: Pedido = null
    pedido = new Pedido(HomePuestosDeVenta.get("unicenter"), HomeClientes.get("carlos"), HomeTiposDePago.get("efectivo"),new DateTime("2013-10-01"));
    pedido.agregarEntradaComun(HomeTipoDeClientes.get("mayor"), HomeNoches.getPorInt(6), HomeButacas.get(2));
    pedido.agregarEntradaComun(HomeTipoDeClientes.get("mayor"), HomeNoches.getPorInt(6), HomeButacas.get(8));
    pedido.agregarEntradaComun(HomeTipoDeClientes.get("mayor"), HomeNoches.getPorInt(6), HomeButacas.get(7));
    pedido.comprar();

    pedidosIniciales = pedidosIniciales.+:(pedido)

    pedido = new Pedido(HomePuestosDeVenta.get("abasto"), HomeClientes.get("pedrito"), HomeTiposDePago.get("efectivo"),new DateTime("2013-10-20"));
    pedido.agregarEntradaComun(HomeTipoDeClientes.get("no acompaniado"), HomeNoches.getPorInt(4), HomeButacas.get(14));
    pedido.comprar();

    pedidosIniciales = pedidosIniciales.+:(pedido)

    pedido = new Pedido(HomePuestosDeVenta.get("estadio"), HomeClientes.get("florencia"), HomeTiposDePago.get("efectivo"),new DateTime("2013-11-05"));
    pedido.agregarEntradaComun(HomeTipoDeClientes.get("mujer"), HomeNoches.getPorInt(7), HomeButacas.get(14));
    pedido.comprar();

    pedidosIniciales = pedidosIniciales.+:(pedido)

    pedido = new Pedido(HomePuestosDeVenta.get("kiosko"), HomeClientes.get("facu"), HomeTiposDePago.get("efectivo"),new DateTime("2013-11-15"));
    pedido.agregarEntradaComun(HomeTipoDeClientes.get("menor de 18"), HomeNoches.getPorInt(2), HomeButacas.get(1));
    pedido.agregarEntradaComun(HomeTipoDeClientes.get("mayor"), HomeNoches.getPorInt(2), HomeButacas.get(2));
    pedido.agregarEntradaComun(HomeTipoDeClientes.get("mayor"), HomeNoches.getPorInt(2), HomeButacas.get(3));
    pedido.agregarEntradaComun(HomeTipoDeClientes.get("jubilado"), HomeNoches.getPorInt(2), HomeButacas.get(4));
    pedido.agregarEntradaComun(HomeTipoDeClientes.get("mujer"), HomeNoches.getPorInt(2), HomeButacas.get(5));
    pedido.agregarEntradaComun(HomeTipoDeClientes.get("mayor"), HomeNoches.getPorInt(2), HomeButacas.get(6));
    pedido.agregarEntradaComun(HomeTipoDeClientes.get("mayor"), HomeNoches.getPorInt(2), HomeButacas.get(7));
    pedido.comprar();

    pedidosIniciales = pedidosIniciales.+:(pedido)

    pedido = new Pedido(HomePuestosDeVenta.get("abasto"), HomeClientes.get("jose"), HomeTiposDePago.get("efectivo"),new DateTime("2013-11-11"));
    pedido.agregarEntradaComun(HomeTipoDeClientes.get("menor de 18"), HomeNoches.getPorInt(7), HomeButacas.get(10));
    pedido.agregarEntradaComun(HomeTipoDeClientes.get("mujer"), HomeNoches.getPorInt(2), HomeButacas.get(10));
    pedido.agregarEntradaComun(HomeTipoDeClientes.get("jubilado"), HomeNoches.getPorInt(3), HomeButacas.get(10));
    pedido.comprar();

    pedidosIniciales = pedidosIniciales.+:(pedido)

    pedido = new Pedido(HomePuestosDeVenta.get("DOT"), HomeClientes.get("pablo"), HomeTiposDePago.get("efectivo"),new DateTime("2013-09-11"));
    pedido.agregarEntradaComun(HomeTipoDeClientes.get("menor"), HomeNoches.getPorInt(6), HomeButacas.get(15));
    pedido.agregarEntradaComun(HomeTipoDeClientes.get("jubilado"), HomeNoches.getPorInt(3), HomeButacas.get(15));
    pedido.agregarEntradaComun(HomeTipoDeClientes.get("mayor"), HomeNoches.getPorInt(4), HomeButacas.get(15));
    pedido.comprar();

    pedidosIniciales = pedidosIniciales.+:(pedido)

    pedido = new Pedido(HomePuestosDeVenta.get("DOT"), HomeClientes.get("pedrito"), HomeTiposDePago.get("efectivo"),new DateTime("2013-11-29"));
    pedido.agregarEntradaComun(HomeTipoDeClientes.get("mujer"), HomeNoches.getPorInt(6), HomeButacas.get(1));
    pedido.agregarEntradaComun(HomeTipoDeClientes.get("jubilado"), HomeNoches.getPorInt(5), HomeButacas.get(2));
    pedido.agregarEntradaComun(HomeTipoDeClientes.get("no acompaniado"), HomeNoches.getPorInt(5), HomeButacas.get(3));
    pedido.comprar();

    pedidosIniciales = pedidosIniciales.+:(pedido)

    for (pedido <- pedidosIniciales)
      createEntradas(pedido)

  }

  def createEntradas(pedido: Pedido): Unit = {
    for (entrada <- pedido._entradas)
      this.create(entrada)

  }
  
  def deleteEntradas(pedido:Pedido) {
    for (entrada <- pedido._entradas)
      this.delete(entrada)
  }

}