package ar.edu.celulares.home

import scala.collection.JavaConversions.asScalaBuffer
import org.uqbar.commons.model.CollectionBasedHome
import org.uqbar.commons.model.UserException
import org.uqbar.commons.utils.Observable
import org.apache.commons.collections15.Predicate
import org.uqbar.commons.utils.ApplicationContext

import domain.Fila

@Observable
object HomeFilas extends CollectionBasedHome[Fila]  {
  
  this.create(1, 50.0);
  this.create(2, 40.0);
  this.create(3, 30.0);

  def create(id: Int, valor: Double) {
    var sector = new Fila(id, valor)
    this.create(sector)
  }

  def search(id:Int = -1){
    filas.filter{ fila => this.coincide(fila.numeroFila, id)}
  }
  
  def coincide(valorPosta:Int, valorEntrante:Int):Boolean = {
    if(valorEntrante == -1)
      return true
    return (valorEntrante == valorPosta)
  }
  
  def get(id: Int): Fila =
    filas.find(_.numeroFila == id).getOrElse(null) // Acá hay que pensar algo.

  def filas: java.util.List[Fila] = allInstances

  override def getEntityType = classOf[Fila]

  override def createExample = new Fila(100, 5.0)

  override def getCriterio(example: Fila) = null

}