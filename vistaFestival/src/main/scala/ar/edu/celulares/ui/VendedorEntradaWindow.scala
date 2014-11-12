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
import ar.edu.celulares.applicationModel.VendedorEntrada
import org.uqbar.arena.widgets.Selector
import domain.TipoDePago
import org.uqbar.arena.bindings.ObservableProperty
import ar.edu.celulares.home.HomeEntradas
import org.uqbar.arena.bindings.PropertyAdapter
import org.uqbar.arena.widgets.TextBox
import domain.Festival
import ar.edu.celulares.home.HomeFestivales
import ar.edu.celulares.home.HomeTiposDePago
import domain.TipoDePago
import domain.TipoCliente
import domain.Noche
import domain.Sector
import domain.Fila
import ar.edu.celulares.home.HomeTipoDeClientes
import ar.edu.celulares.home.HomeNoches
import ar.edu.celulares.home.HomeFilas
import ar.edu.celulares.home.HomeSectores
import ar.edu.celulares.home.HomeButacas
import domain.Butaca
import ar.edu.celulares.home.HomePuestosDeVenta
import org.uqbar.arena.widgets.tables.Column
import org.uqbar.arena.widgets.tables.Table
import domain.Entrada
import domain.EntradaComun
import domain.EntradaVIP
import org.uqbar.arena.bindings.NotNullObservable
import org.uqbar.arena.layout.ColumnLayout
import org.uqbar.arena.layout.HorizontalLayout

class VendedorEntradaWindow(parent: WindowOwner) extends Dialog[VendedorEntrada](parent, new VendedorEntrada) {

  getModelObject.clearTodo()

  override def createMainTemplate(mainPanel: Panel) = {
    this.setTitle("Vendedor de Entradas")
    this.setTaskDescription("Ingrese los datos necesarios para generar la entrada")

    super.createMainTemplate(mainPanel)
    
    this.createResultsGrid(mainPanel)
    this.createGridActions(mainPanel)

  }

  override def createFormPanel(mainPanel: Panel) = {

    var form = new Panel(mainPanel)
    form.setLayout(new ColumnLayout(2))

    new Label(form).setText("Nombre:")
    new TextBox(form).bindValueToProperty("nombre")

    new Label(form).setText("Apellido:")
    new TextBox(form).bindValueToProperty("apellido")
    
    new Label(form).setText("PuestoDeVenta")
    var selectorPuesto = new Selector[Festival](form)
    selectorPuesto.allowNull(false)
    selectorPuesto.bindValueToProperty("puestoDeVenta")
    var propiedadPuesto = selectorPuesto.bindItems(new ObservableProperty(HomePuestosDeVenta, "puestos"))

    new Label(form).setText("Festival")
    var selectorFestival = new Selector[Festival](form)
    selectorFestival.allowNull(false)
    selectorFestival.bindValueToProperty("festival")
    var propiedadFestivales = selectorFestival.bindItems(new ObservableProperty(HomeFestivales, "festivales"))
    
    new Label(form).setText("Tipo de Pago")
    var selectorTipoDePago = new Selector[TipoDePago](form)
    selectorTipoDePago.allowNull(false)
    selectorTipoDePago.bindValueToProperty("tipoDePago")
    var propiedadTipoDePago= selectorTipoDePago.bindItems(new ObservableProperty(HomeTiposDePago, "tiposDePago"))
    
    new Label(form).setText("Tipo de Cliente")
    var selectorTipoDeCliente = new Selector[TipoCliente](form)
    selectorTipoDeCliente.allowNull(false)
    selectorTipoDeCliente.bindValueToProperty("tipoDeCliente")
    var propiedadTipoDeCliente= selectorTipoDeCliente.bindItems(new ObservableProperty(HomeTipoDeClientes, "tiposDeCliente"))
    
    new Label(form).setText("Noche")
    var selectorNoche= new Selector[Noche](form)
    selectorNoche.allowNull(false)
    selectorNoche.bindValueToProperty("noche")
    var propiedadNoche= selectorNoche.bindItemsToProperty("nochesDisponibles")
    selectorNoche.setWidth(150)
    
    new Label(form).setText("Sector")
    var selectorSector= new Selector[Sector](form)
    selectorSector.allowNull(false)
    selectorSector.bindValueToProperty("sector")
    var propiedadSector= selectorSector.bindItems(new ObservableProperty(HomeSectores, "sectores"))
    
    
    new Label(form).setText("Fila")
    var selectorFila = new Selector[Fila](form)
    selectorFila.allowNull(false)
    selectorFila.bindValueToProperty("fila")
    var propiedadFila= selectorFila.bindItems(new ObservableProperty(HomeFilas, "filas"))
    
    new Label(form).setText("Butaca")
    var selectorButaca= new Selector[Butaca](form)
    selectorButaca.allowNull(false)
    selectorButaca.bindValueToProperty("butaca")
    var propiedadButaca= selectorButaca.bindItemsToProperty( "butacasDisponibles")
    selectorButaca.setWidth(120)
    
    
    
    //		propiedadModelos.setAdapter(new PropertyAdapter(classOf[Modelo], "descripcionEntera"))

  

    /**
     * De ahora en mas, lo que esta abajo es de una entrada en particular y no del pedido de todas las entradas.
     */

//    new Label(form).setText("Noche:")
//    new Label(form).setForeground(Color.RED)
//      .bindValueToProperty("noche")
//
//    new Label(form).setText("Butaca:")
//    new Label(form).setForeground(Color.RED)
//      .bindValueToProperty("butaca")

    //Creo un label por cada informacion que tenga de la entrada
  }

  
  def createResultsGrid(mainPanel: Panel) {
    var table = new Table[Entrada](mainPanel, classOf[Entrada])
    table.setHeigth(200)
    table.setWidth(450)
    table.bindItemsToProperty("entradas")
    table.bindValueToProperty("entradaSeleccionada")
    this.describeResultsGrid(table)
  }

  /**
   * Define las columnas de la grilla Cada columna se puede bindear 1) contra una propiedad del model, como
   * en el caso del número o el nombre 2) contra un transformer que recibe el model y devuelve un tipo
   * (generalmente String), como en el caso de Recibe Resumen de Cuenta
   *
   * @param table
   */
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

    new Column[Entrada](table)
      .setTitle("Festival")
      .setFixedSize(120)
      .bindContentsToProperty("festival")

    new Column[Entrada](table)
      .setTitle("Precio")
      .setFixedSize(70)
      .bindContentsToProperty("precioDeVenta")
  }

  def createGridActions(mainPanel: Panel) {
    var actionsPanel = new Panel(mainPanel)
    actionsPanel.setLayout(new HorizontalLayout)
    var remove = new Button(actionsPanel)
      .setCaption("Borrar Entrada")
      .onClick(new MessageSend(getModelObject, "eliminarEntradaSeleccionada"))
      
    var comprar = new Button(actionsPanel)
      .setCaption("Comprar Entradas")
      .onClick(new MessageSend(getModelObject, "comprarEntradas"))

    // Deshabilitar los botones si no hay ningún elemento seleccionado en la grilla.
    var elementSelected = new NotNullObservable("entradaSeleccionada")
    remove.bindEnabled(elementSelected)
  }
  
  override def addActions(actions: Panel) = {
    new Button(actions)
      .setCaption("Agregar Entrada")
      .onClick(new MessageSend(getModelObject, "agregarEntrada"))
      .setAsDefault.disableOnError
     
    new Button(actions)
      .setCaption("Limpiar Cliente")
      .onClick(new MessageSend(getModelObject, "clearTodo"))
      .setAsDefault.disableOnError 
      
     new Button(actions)
      .setCaption("Limpiar Entrada")
      .onClick(new MessageSend(getModelObject, "clearEntrada"))
      .setAsDefault.disableOnError     
      

    new Button(actions) //
      .setCaption("Salir")
      .onClick(new MessageSend(this, "cancel"))
  }

}