package ar.edu.celulares.applicationModel


import java.util.ArrayList
import collection.JavaConversions._
import domain.Entrada
import ar.edu.celulares.home._
import org.joda.time.DateTime


@org.uqbar.commons.utils.Observable
abstract class BuscadorEntrada extends Buscador {
  
  // Para mi esto no anda porque faltan los atributos cuando se busca pro punto de venta.
  var nombreCliente:String= ""
  var fechaDesde:String = ""
  var fechaHasta:String=""
  var nombrePuestoDeVenta:String=""
  var nombreFestival:String=""
    
  var resultados: java.util.List[Entrada] = _
  var entradaSeleccionada: Entrada = _

  def search() = {
    resultados = new ArrayList[Entrada]   
    resultados = this.buscarEntrada() //Cada subclase lo define, algo asi como un ""Template Method"" CREO
  }

  def clear() = {
    
    nombreCliente=""
    fechaDesde=""
    fechaHasta=""
    nombrePuestoDeVenta=""
    nombreFestival=""
  }
  
  def buscarEntrada():Seq[Entrada];

}

@org.uqbar.commons.utils.Observable
class BuscadorEntradaPorCliente extends BuscadorEntrada{
  
  def buscarEntrada():Seq[Entrada] ={
    return HomeEntradas.search(nombreCliente, fechaDesde,fechaHasta)
    
  }
  
  def anularSeleccionada() {
   if (entradaSeleccionada!=null){
   HomeEntradas.delete(entradaSeleccionada) 
   HomeButacas.create(entradaSeleccionada.butaca)
   resultados = resultados.-(entradaSeleccionada)
   }
   }
}

@org.uqbar.commons.utils.Observable
class BuscadorEntradaPorPuntoDeVenta extends BuscadorEntrada{
  
  def buscarEntrada():Seq[Entrada] ={
    return HomeEntradas.searchPuestoFestival(nombrePuestoDeVenta,nombreFestival)
    
  }
}




