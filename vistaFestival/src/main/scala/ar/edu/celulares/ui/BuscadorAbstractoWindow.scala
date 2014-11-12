package ar.edu.celulares.ui


import java.awt.Color
import org.uqbar.arena.actions.MessageSend
import org.uqbar.arena.bindings.NotNullObservable
import org.uqbar.arena.bindings.ObservableProperty
import org.uqbar.arena.layout.ColumnLayout
import org.uqbar.arena.layout.HorizontalLayout
import org.uqbar.arena.widgets._
import com.uqbar.commons.collections.Transformer
import domain.Cliente
import domain.Entrada
import ar.edu.celulares.applicationModel.BuscadorEntrada
import ar.edu.celulares.home.HomeClientes
import ar.edu.celulares.controller.FechaTransformer
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.arena.windows.Dialog
import ar.edu.celulares.applicationModel.Buscador

abstract class BuscadorAbstractoWindow(parent: WindowOwner,model: Buscador) extends Dialog[Buscador](parent, model) {

	getModelObject.search()
  
  override def createMainTemplate(mainPanel: Panel) = {
    this.setTitle("Buscador")
    this.setTaskDescription("Ingrese los parametros de busqueda")

    super.createMainTemplate(mainPanel)

    this.createResultsGrid(mainPanel)
  }


  override def createFormPanel(mainPanel: Panel) = {
    var searchFormPanel = new Panel(mainPanel)
    searchFormPanel.setLayout(new ColumnLayout(2))
    this.agregarBotonesParaBusqueda(searchFormPanel)
  }


  override def addActions(actionsPanel: Panel) {
    new Button(actionsPanel)
      .setCaption("Buscar")
      .onClick(new MessageSend(getModelObject, "search"))
      .setAsDefault
      .disableOnError

    new Button(actionsPanel) //
      .setCaption("Limpiar")
      .onClick(new MessageSend(getModelObject, "clear"))
  }

  /**
   *------Metodos abstractos que tiene que implementar las subclases, sino no funciona 
   **/
  def createResultsGrid(mainPanel: Panel);
  def agregarBotonesParaBusqueda(panel:Panel);
  


  }


