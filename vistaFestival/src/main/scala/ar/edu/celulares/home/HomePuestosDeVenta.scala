package ar.edu.celulares.home


import scala.collection.JavaConversions.asScalaBuffer
import org.uqbar.commons.model.CollectionBasedHome
import org.uqbar.commons.model.UserException
import org.uqbar.commons.utils.Observable
import org.apache.commons.collections15.Predicate
import org.uqbar.commons.utils.ApplicationContext
import domain.Banda
import domain.Categoria
import domain.PuestoDeVenta

@Observable
object HomePuestosDeVenta extends CollectionBasedHome[PuestoDeVenta]  {
  
  this.create("Estadio Velez")
  this.create("Unicenter")
  this.create("DOT")
  this.create("Kiosko TITO")
  this.create("Abasto")
  
  
  def create(unNombre:String) {
    var puesto= new PuestoDeVenta(unNombre)
    this.create(puesto)
  }

  def search(nombre:String = "") = {
    puestos.filter { banda => this.coincide(banda.nombre, nombre) }
  }

  def coincide(valorPosta: String, valorEntrante:String): Boolean = {
    if (valorEntrante == "") 
      return true
    return valorPosta.toString().toLowerCase().contains(valorEntrante.toString().toLowerCase())
  }
  
  
  def get(nombre:String = ""): PuestoDeVenta=
    puestos.find(puesto => this.coincide(puesto.nombre,nombre)).getOrElse(null) // Acá hay que pensar algo.

  def puestos: java.util.List[PuestoDeVenta] = allInstances

  override def getEntityType = classOf[PuestoDeVenta]

  override def createExample = new PuestoDeVenta("CasaX")

  override def getCriterio(example: PuestoDeVenta) = null

}