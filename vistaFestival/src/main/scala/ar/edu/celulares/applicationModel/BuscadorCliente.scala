package ar.edu.celulares.applicationModel
import java.util.ArrayList
import collection.JavaConversions._
import domain._
import ar.edu.celulares.home._
import org.joda.time.DateTime

@org.uqbar.commons.utils.Observable
 class BuscadorCliente extends Buscador {
  var nombreCliente:String= ""
  var apellidoCliente:String = ""
  var resultados: java.util.List[Cliente] = _
  var clienteSeleccionado: Cliente = _
  
  def search(){
   resultados = new ArrayList[Cliente]
   resultados =  HomeClientes.search(nombreCliente, apellidoCliente)
  }
  
  def clear(){
    nombreCliente = " "
    apellidoCliente = " "
  }
  

}