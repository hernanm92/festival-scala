//package ar.edu.celulares.ui

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
import ar.edu.celulares.applicationModel.BuscadorEntrada
import org.uqbar.arena.widgets.Selector
import domain.Cliente
import org.uqbar.arena.bindings.ObservableProperty
import ar.edu.celulares.home.HomeClientes
import domain.Entrada
import ar.edu.celulares.controller.FechaTransformer
import ar.edu.celulares.ui._

abstract class BuscadorEntradaWindow(parent: WindowOwner, model: BuscadorEntrada) extends BuscadorAbstractoWindow(parent, model) {

  def createResultsGrid(mainPanel: Panel) {
    var table = new Table[Entrada](mainPanel, classOf[Entrada])
    table.setHeigth(250)
    table.setWidth(600)
    table.bindItemsToProperty("resultados")
    table.bindValueToProperty("entradaSeleccionada")
    this.describeResultsGrid(table)
  }

  def describeResultsGrid(table: Table[Entrada]) {
    new Column[Entrada](table) //
      .setTitle("Nro de Factura")
      .setFixedSize(70)
      .bindContentsToProperty("nroFactura")

    new Column[Entrada](table) //
      .setTitle("Cliente")
      .setFixedSize(150)
      .bindContentsToProperty("nombreCliente")

    new Column[Entrada](table) //
      .setTitle("Punto De Venta")
      .setFixedSize(100)
      .bindContentsToProperty("puestoDeVenta")

    new Column[Entrada](table) //
      .setTitle("Fecha")
      .setFixedSize(100)
      .bindContentsToTransformer(new FechaTransformer)

    new Column[Entrada](table)
      .setTitle("Festival")
      .setFixedSize(120)
      .bindContentsToProperty("festival")

    new Column[Entrada](table)
      .setTitle("Precio")
      .setFixedSize(70)
      .bindContentsToProperty("precioDeVenta")
  }
}

class BuscadorEntradaxClienteWindow(parent:WindowOwner,model:BuscadorEntrada) extends BuscadorEntradaWindow(parent,model){
  def agregarBotonesParaBusqueda(panelBusqueda: Panel) {  
  var labelNombreCliente = new Label(panelBusqueda)
    labelNombreCliente.setText("Nombre de Cliente:")
    labelNombreCliente.setForeground(Color.BLUE)
    new TextBox(panelBusqueda).bindValueToProperty("nombreCliente")
  }
  
  override def addActions(actionsPanel: Panel) {
	super.addActions(actionsPanel)
	 new Button(actionsPanel) //
      .setCaption("Anular Seleccionada")
      .onClick(new MessageSend(getModelObject, "anularSeleccionada"))
  }
}

class BuscadorEntradaXClienteYFechaWindow(parent: WindowOwner, model: BuscadorEntrada) extends BuscadorEntradaxClienteWindow(parent, model) {
  
   override def agregarBotonesParaBusqueda(panelBusqueda: Panel) {
    super.agregarBotonesParaBusqueda(panelBusqueda)
    var labelFechaDesde = new Label(panelBusqueda)
    labelFechaDesde.setText("Fecha desde(AAAA-MM-DD):")
    labelFechaDesde.setForeground(Color.BLUE)
    new TextBox(panelBusqueda).bindValueToProperty("fechaDesde")

    var labelFechaHasta = new Label(panelBusqueda)
    labelFechaHasta.setText("Fecha hasta(AAAA-MM-DD):")
    labelFechaHasta.setForeground(Color.BLUE)
    new TextBox(panelBusqueda).bindValueToProperty("fechaHasta")
  }
}

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




