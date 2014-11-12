//package ar.edu.celulares.ui

/**
 * Es el hermano del BuscadorEntradaWindow, es abstracto pero implementa unos metodos y 
 * deja otros para las clases que heredan de el.
 * Para que funcione hay que casi copiar y pegar el codigo de BuscadorEntrada y despues
 * hacer las clases BuscadorBandaXFestivalYNombre y BuscadorBandaXClienteYFestival
 * Recieeeen ahi se puede ver si funciona o no.
 * Tambien hay que crear el aplication model para cada caso, tambien es muy parecido al de Buscador
 * Hay que agregar dos botones mas al menu principal para que se puedan llamar a estas clases desde ahi 
 *
 * 
 * ALE: ok!
 * */

import java.awt.Color
import org.uqbar.arena.actions.MessageSend
import org.uqbar.arena.bindings.NotNullObservable
import org.uqbar.arena.layout.ColumnLayout
import org.uqbar.arena.layout.HorizontalLayout
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.widgets.TextBox
import org.uqbar.arena.widgets.tables.Column
import org.uqbar.arena.widgets.tables.Table
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import com.uqbar.commons.collections.Transformer
import ar.edu.celulares.applicationModel.BuscadorBanda
import org.uqbar.arena.widgets.Selector
import domain.Cliente
import org.uqbar.arena.bindings.ObservableProperty
import ar.edu.celulares.home._
import domain._
import ar.edu.celulares.controller.FechaTransformer
import ar.edu.celulares.ui._

abstract class BuscadorBandaWindow(parent: WindowOwner, model: BuscadorBanda) extends BuscadorAbstractoWindow(parent, model) {

  def createResultsGrid(mainPanel: Panel) {
    var table = new Table[Banda](mainPanel, classOf[Banda])
    table.setHeigth(250)
    table.setWidth(600)
    table.bindItemsToProperty("resultados")
    this.describeResultsGrid(table)
  }

  def describeResultsGrid(table: Table[Banda]) {
    new Column[Banda](table) //
      .setTitle("Nombre")
      .setFixedSize(70)
      .bindContentsToProperty("nombre")

    
  }
}

class BuscadorBandaXNombreWindow(parent: WindowOwner, model: BuscadorBanda) extends BuscadorBandaWindow(parent, model) {
  
   def agregarBotonesParaBusqueda(panelBusqueda: Panel) {

    var labelNombreCliente = new Label(panelBusqueda)
    labelNombreCliente.setText("Nombre de Banda:")
    labelNombreCliente.setForeground(Color.BLUE)
    new TextBox(panelBusqueda).bindValueToProperty("nombreBanda")
/*
    var labelFechaDesde = new Label(panelBusqueda)
    labelFechaDesde.setText("Fecha desde(AAAA-MM-DD):")
    labelFechaDesde.setForeground(Color.BLUE)
    new TextBox(panelBusqueda).bindValueToProperty("fechaDesde")

    var labelFechaHasta = new Label(panelBusqueda)
    labelFechaHasta.setText("Fecha hasta(AAAA-MM-DD):")
    labelFechaHasta.setForeground(Color.BLUE)
    new TextBox(panelBusqueda).bindValueToProperty("fechaHasta")*/
  }
}
/*
class BuscadorEntradaXPuntoDeVentaYFestival(parent: WindowOwner, model: BuscadorEntrada) extends BuscadorEntradaWindow(parent, model) {
  
   def agregarBotonesParaBusqueda(panelBusqueda: Panel) {

    var labelNombrePuestoDeVenta = new Label(panelBusqueda)
    labelNombrePuestoDeVenta.setText("Puesto De Venta:")
    labelNombrePuestoDeVenta.setForeground(Color.BLUE)
    new TextBox(panelBusqueda).bindValueToProperty("nombrePuestoDeVenta")

    var labelNombreFestival = new Label(panelBusqueda)
    labelNombreFestival.setText("Festival")
    labelNombreFestival.setForeground(Color.BLUE)
    new TextBox(panelBusqueda).bindValueToProperty("nombreFestival")
  }
}
*/



