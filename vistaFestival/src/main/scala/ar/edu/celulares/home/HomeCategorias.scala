package ar.edu.celulares.home

import scala.collection.JavaConversions.asScalaBuffer
import org.uqbar.commons.model.CollectionBasedHome
import org.uqbar.commons.model.UserException
import org.uqbar.commons.utils.Observable
import org.apache.commons.collections15.Predicate
import org.uqbar.commons.utils.ApplicationContext
import domain.Sector
import domain.Categoria

@Observable
object HomeCategorias extends CollectionBasedHome[Categoria]  {
  
  this.create(1, 00.0)
  this.create(2, 50.0)
  this.create(3, 100.0)
  this.create(4, 200.0)
  
  def create(unNivel: Int,unCostoExtra: Double){
    var categoria= new Categoria(unNivel,unCostoExtra)
    this.create(categoria)
  }
  
  def search(unNivel:Int = -1){
    categorias.filter{ categoria => this.coincide(categoria._nivel , unNivel)}
  }
  
  def coincide(valorPosta:Int, valorEntrante:Int):Boolean = {
    if(valorEntrante == -1)
      return true
    return (valorEntrante == valorPosta)
  }
  
    def get(unNivel:Int = -1):Categoria =
    categorias.find(_._nivel == unNivel).getOrElse(null) // Acá hay que pensar algo.
  
  
  def categorias: java.util.List[Categoria] = allInstances

  override def getEntityType = classOf[Categoria]

  override def createExample = new Categoria(25,1500.0)

  override def getCriterio(example: Categoria) = null
}