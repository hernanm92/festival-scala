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
import ar.edu.celulares.applicationModel.BuscadorBandasPorFestival
import org.uqbar.arena.widgets.Selector
import domain.Cliente
import org.uqbar.arena.bindings.ObservableProperty
import ar.edu.celulares.home.HomeClientes
import ar.edu.celulares.home.HomeFestivales
import domain.Entrada
import ar.edu.celulares.controller.FechaTransformer
import ar.edu.celulares.ui._
import domain._

class BuscadorBandasPorFestivalWindow(parent: WindowOwner, model: BuscadorBandasPorFestival) extends BuscadorAbstractoWindow(parent, model)  {

  def createResultsGrid(mainPanel: Panel) {
    var table = new Table[Banda](mainPanel, classOf[Banda])
    table.setHeigth(250)
    table.setWidth(600)
    table.bindItemsToProperty("resultados")
    table.bindValueToProperty("bandaSeleccionada")
    this.describeResultsGrid(table)
  }

  def describeResultsGrid(table: Table[Banda]) {
    new Column[Banda](table) //
      .setTitle("Banda")
      .setFixedSize(70)
      .bindContentsToProperty("nombre")
      
     new Column[Banda](table) //
      .setTitle("Categoria")
      .setFixedSize(70)
      .bindContentsToProperty("cat")
  }
  
  
  
   def agregarBotonesParaBusqueda(panelBusqueda: Panel){
    new Label(panelBusqueda).setText("Festival")
    var selectorFestival = new Selector[Festival](panelBusqueda)
    selectorFestival.allowNull(false)
    selectorFestival.bindValueToProperty("festival")
    var propiedadFestivales = selectorFestival.bindItems(new ObservableProperty(HomeFestivales, "festivales"))

   }
}