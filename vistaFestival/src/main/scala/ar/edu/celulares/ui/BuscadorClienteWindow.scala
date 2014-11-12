package ar.edu.celulares.ui

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
import ar.edu.celulares.applicationModel.BuscadorCliente
import ar.edu.celulares.applicationModel.BuscadorBandasPorFestival
import org.uqbar.arena.widgets.Selector
import domain.Cliente
import org.uqbar.arena.bindings.ObservableProperty
import ar.edu.celulares.home.HomeClientes
import domain.Entrada
import ar.edu.celulares.controller.FechaTransformer
import ar.edu.celulares.ui._

class BuscadorClienteWindow(parent: WindowOwner, model: BuscadorCliente) extends BuscadorAbstractoWindow(parent, model) {

  def createResultsGrid(mainPanel: Panel) {
    var table = new Table[Cliente](mainPanel, classOf[Cliente])
    table.setHeigth(250)
    table.setWidth(600)
    table.bindItemsToProperty("resultados")
    table.bindValueToProperty("clienteSeleccionado")
    this.describeResultsGrid(table)
  }

  def describeResultsGrid(table: Table[Cliente]) {
    new Column[Cliente](table) //
      .setTitle("Nombre")
      .setFixedSize(70)
      .bindContentsToProperty("_nombre")

    new Column[Cliente](table) //
      .setTitle("Apellido")
      .setFixedSize(150)
      .bindContentsToProperty("_apellido")

    new Column[Cliente](table) //
      .setTitle("Numero Tarjeta")
      .setFixedSize(100)
      .bindContentsToProperty("_nroTarjeta")

    new Column[Cliente](table)
      .setTitle("Datos Legales")
      .setFixedSize(120)
      .bindContentsToProperty("_restoDatosLegales")
  }
  
   def agregarBotonesParaBusqueda(panelBusqueda: Panel){
     
     
	var labelNombreCliente = new Label(panelBusqueda)
    labelNombreCliente.setText("Nombre de Cliente:")
    labelNombreCliente.setForeground(Color.BLUE)
    new TextBox(panelBusqueda).bindValueToProperty("nombreCliente")
    
    var labelApellidoCliente = new Label(panelBusqueda)
    labelApellidoCliente.setText("Apellido de Cliente:")
    labelApellidoCliente.setForeground(Color.BLUE)
    new TextBox(panelBusqueda).bindValueToProperty("apellidoCliente")
    
    new Button(panelBusqueda)
      .setCaption("Ver Bandas")
      .onClick(new MessageSend(this, "buscarBandasPorFestival"))

   }
   
   def openDialog(dialog: Dialog[_]) {
    dialog.open
  }
   
   def buscarBandasPorFestival(){
     this.openDialog(new BuscadorBandasPorFestivalWindow(this,new BuscadorBandasPorFestival(model.clienteSeleccionado)))
     }
}