//package ar.edu.celulares.ui

/**
 * Es el hermano del BuscadorEntradaWindow, es abstracto pero implementa unos metodos y 
 * deja otros para las clases que heredan de el.
 * Para que funcione hay que casi copiar y pegar el codigo de BuscadorEntrada y despues
 * hacer las clases BuscadorFestivalXFestivalYNombre y BuscadorFestivalXClienteYFestival
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
import ar.edu.celulares.applicationModel.BuscadorFestival
import org.uqbar.arena.widgets.Selector
import domain.Cliente
import org.uqbar.arena.bindings.ObservableProperty
import ar.edu.celulares.home._
import domain._
import ar.edu.celulares.controller.FechaTransformer
import ar.edu.celulares.applicationModel.BuscadorBanda
import ar.edu.celulares.applicationModel.BuscadorBandaPorNombre
import ar.edu.celulares.ui._

abstract class BuscadorFestivalWindow(parent: WindowOwner, model: BuscadorFestival) extends BuscadorAbstractoWindow(parent, model) {

  
  
  def createResultsGrid(mainPanel: Panel) {
    var table = new Table[Festival](mainPanel, classOf[Festival])
    table.setHeigth(250)
    table.setWidth(600)
    table.bindItemsToProperty("resultados")
    table.bindSelectionToProperty("seleccionFestival")
    this.describeResultsGrid(table)
  }

  def describeResultsGrid(table: Table[Festival]) {
    new Column[Festival](table) //
      .setTitle("Nombre")
      .setFixedSize(170)
      .bindContentsToProperty("nombre")

    
  }
}

class BuscadorFestivalPorNombreWindow(parent: WindowOwner, model: BuscadorFestival) extends BuscadorFestivalWindow(parent, model) {
  
  def buscarBandasXNombre() {
    this.openDialog(new BuscadorBandaXNombreWindow(this, new BuscadorBandaPorNombre(model.seleccionFestival)))
  }
  
  def openDialog(dialog: Dialog[_]) {
    dialog.open
  }
  
  def agregarBotonesParaBusqueda(panelBusqueda: Panel) {

    var labelNombreCliente = new Label(panelBusqueda)
    labelNombreCliente.setText("Nombre de Festival:")
    labelNombreCliente.setForeground(Color.BLUE)
    new TextBox(panelBusqueda).bindValueToProperty("nombreFestival")

    
    new Button(panelBusqueda)
      .setCaption("Ver Bandas")
      .onClick(new MessageSend(this, "buscarBandasXNombre"))
     
    
   
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
