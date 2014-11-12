package ar.edu.celulares.applicationModel

import java.util.ArrayList
import collection.JavaConversions._
import domain._
import ar.edu.celulares.home._
import org.joda.time.DateTime

@org.uqbar.commons.utils.Observable
abstract class BuscadorFestival extends Buscador {
  
  var nombreFestival:String= ""
  var seleccionFestival:Festival = _
    
  var resultados: java.util.List[Festival] = _
  var festivalSeleccionado: Festival = _
 
  def search() = {
    resultados = new ArrayList[Festival]   
    resultados = this.buscarFestival() //Cada subclase lo define, algo asi como un ""Template Method"" CREO
  }

  def clear() = {
    
    nombreFestival = ""
  }
  
  def buscarFestival():Seq[Festival];

}

@org.uqbar.commons.utils.Observable
class BuscadorFestivalPorNombre extends BuscadorFestival{
  
  def buscarFestival():Seq[Festival] ={
    return HomeFestivales.search(nombreFestival)
    
  }
}

