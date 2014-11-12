package ar.edu.celulares.home

import scala.collection.JavaConversions.asScalaBuffer
import org.uqbar.commons.model.CollectionBasedHome
import org.uqbar.commons.model.UserException
import org.uqbar.commons.utils.Observable
import org.apache.commons.collections15.Predicate
import org.uqbar.commons.utils.ApplicationContext
import domain.Butaca
import domain.Sector
import domain.Fila
import domain._

@Observable
object HomeButacas extends CollectionBasedHome[Butaca]  {

  this.create(HomeSectores.get('A'), HomeFilas.get(1),1)
  this.create(HomeSectores.get('A'), HomeFilas.get(2),2)
  this.create(HomeSectores.get('A'), HomeFilas.get(3),3)
  this.create(HomeSectores.get('B'), HomeFilas.get(1),4)
  this.create(HomeSectores.get('B'), HomeFilas.get(2),5)
  this.create(HomeSectores.get('B'), HomeFilas.get(3),6)
  this.create(HomeSectores.get('C'), HomeFilas.get(1),7)
  this.create(HomeSectores.get('C'), HomeFilas.get(2),8)
  this.create(HomeSectores.get('C'), HomeFilas.get(3),9)
  this.create(HomeSectores.get('C'), HomeFilas.get(3),10)
  this.create(HomeSectores.get('C'), HomeFilas.get(3),11)
  this.create(HomeSectores.get('C'), HomeFilas.get(3),12)
  this.create(HomeSectores.get('C'), HomeFilas.get(3),13)
  this.create(HomeSectores.get('C'), HomeFilas.get(3),14)
  this.create(HomeSectores.get('C'), HomeFilas.get(3),15)
  
  def create(unSector:Sector, unaFila:Fila, id:Int): Unit = {
	var butaca = new Butaca(unSector,unaFila,id)
    this.create(butaca)
  }
  
  
  def search(id:Int = -1){
    butacas.filter{ butaca => this.coincide(butaca.id, id)}
  }
  
  def coincide(valorPosta:Int, valorEntrante:Int):Boolean = {
    if(valorEntrante == -1)
      return true
    return (valorEntrante == valorPosta)
  }
  
  def get(id: Int):Butaca =
    butacas.find(_.id== id).getOrElse(null) // Acá hay que pensar algo.
  
  def butacas: java.util.List[Butaca] = allInstances

  override def getEntityType = classOf[Butaca]

  override def createExample = new Butaca(HomeSectores.createExample,
      HomeFilas.createExample,1000)

  override def getCriterio(example: Butaca) = null
  
  def deleteButacas(pedido:Pedido) {
    for (entrada<- pedido._entradas){
      this.delete(entrada.butaca)
    }
  }
  
  
  
  
}