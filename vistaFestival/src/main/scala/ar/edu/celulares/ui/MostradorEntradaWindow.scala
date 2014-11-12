package ar.edu.celulares.ui

import org.uqbar.arena.windows.WindowOwner
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.layout.ColumnLayout
import org.uqbar.arena.widgets.Label
import java.awt.Color
import org.uqbar.arena.windows.SimpleWindow
import ar.edu.celulares.applicationModel.MostradorEntrada
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.actions.MessageSend

class MostradorEntradaWindow(parent: WindowOwner) extends Dialog[MostradorEntrada](parent,new MostradorEntrada) {
  
  getModelObject().cargarEntradaharco
  
  override def createMainTemplate(mainPanel: Panel) = {
    this. setTitle("Mostrador de Entradas")
    this.setTaskDescription("No haga nada, aca esta la entrada fea")
    
    super.createMainTemplate(mainPanel)
    
  }
  
  override def createFormPanel(mainPanel: Panel) = {
	var verEntradaPanel = new Panel(mainPanel)
	verEntradaPanel.setLayout(new ColumnLayout(2))

    new Label(verEntradaPanel).setText("Numero de Factura:")
    new Label(verEntradaPanel) .setForeground(Color.RED)
		.bindValueToProperty("nroFactura");
	
	new Label(verEntradaPanel).setText("Puesto de Venta:")
    new Label(verEntradaPanel) .setForeground(Color.RED)
		.bindValueToProperty("puestoDeVenta");
		
		
	new Label(verEntradaPanel).setText("Cliente:")
    new Label(verEntradaPanel).setForeground(Color.RED)
		.bindValueToProperty("cliente");
		
	new Label(verEntradaPanel).setText("Tipo de Cliente:")
    new Label(verEntradaPanel).setForeground(Color.RED)
		.bindValueToProperty("tipoCliente");
		
	new Label(verEntradaPanel).setText("Precio:")
    new Label(verEntradaPanel).setForeground(Color.RED)
		.bindValueToProperty("precioEnPesos");
		
	new Label(verEntradaPanel).setText("Noche:")
    new Label(verEntradaPanel).setForeground(Color.RED)
		.bindValueToProperty("noche");	
		
    new Label(verEntradaPanel).setText("Butaca:")
    new Label(verEntradaPanel).setForeground(Color.RED)
		.bindValueToProperty("butaca");
		
	new Label(verEntradaPanel).setText("Festival:")
    new Label(verEntradaPanel).setForeground(Color.RED)
		.bindValueToProperty("festival");	
		
  }
  
    
	override def addActions(actions: Panel) = {
		new Button(actions)
			.setCaption("Aceptar")
			.onClick(new MessageSend(this, "accept"))
			.setAsDefault.disableOnError

		new Button(actions) //
			.setCaption("Cancelar")
			.onClick(new MessageSend(this, "cancel"))
	}
  

}