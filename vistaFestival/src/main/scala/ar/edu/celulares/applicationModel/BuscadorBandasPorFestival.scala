package ar.edu.celulares.applicationModel
import java.util.ArrayList
import collection.JavaConversions._
import domain._
import ar.edu.celulares.home._
import org.joda.time.DateTime
import scala.collection.JavaConversions.asScalaBuffer
import scala.collection.JavaConverters._
import scala.collection.mutable.ListBuffer


@org.uqbar.commons.utils.Observable
class BuscadorBandasPorFestival(unCliente:Cliente) extends Buscador {
  var festival:Festival = _
  val cliente = unCliente
  var resultados: java.util.List[Banda] = List.empty[Banda]
  var bandaSeleccionada: Banda = _
  
  def search(){
    resultados = new ArrayList[Banda] 
    var nomCli:String=""
      if (cliente!=null) nomCli=cliente.toString()
   resultados = HomeEntradas.dameBandas(nomCli, festival)
  }
  
  def clear(){
   festival = null
  }
  
}