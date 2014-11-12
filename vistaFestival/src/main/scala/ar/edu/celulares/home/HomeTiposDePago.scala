package ar.edu.celulares.home

import scala.collection.JavaConversions.asScalaBuffer
import org.uqbar.commons.model.CollectionBasedHome
import org.uqbar.commons.model.UserException
import org.uqbar.commons.utils.Observable
import org.apache.commons.collections15.Predicate
import org.uqbar.commons.utils.ApplicationContext
import domain.TipoDePago
import domain.PagoConTarjeta
import domain.PagoEnEfectivo

@Observable
object HomeTiposDePago extends CollectionBasedHome[TipoDePago]  {
  
  this.create(new PagoConTarjeta("Tarjeta"))
  this.create(new PagoEnEfectivo("Efectivo"))

  
  def search(unNombre:String = ""){
    tiposDePago.filter{ tipo => this.coincide(tipo.nombre, unNombre)}
  }

  def coincide(valorPosta: String, valorEntrante: String): Boolean = {
    if (valorEntrante == "")
      return true
    return valorPosta.toString().toLowerCase().contains(valorEntrante.toString().toLowerCase())
  }
  
    def get(unNombre:String=""):TipoDePago =
    tiposDePago.find(tipo => this.coincide(tipo.nombre, unNombre)).getOrElse(null) // Acá hay que pensar algo.
  
  
  def tiposDePago: java.util.List[TipoDePago] = allInstances

  override def getEntityType = classOf[TipoDePago]

  override def createExample = new PagoEnEfectivo()

  override def getCriterio(example: TipoDePago) = null
}