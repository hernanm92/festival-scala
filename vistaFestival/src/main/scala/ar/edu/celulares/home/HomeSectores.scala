package ar.edu.celulares.home

import scala.collection.JavaConversions.asScalaBuffer
import org.uqbar.commons.model.CollectionBasedHome
import org.uqbar.commons.model.UserException
import org.uqbar.commons.utils.Observable
import org.apache.commons.collections15.Predicate
import org.uqbar.commons.utils.ApplicationContext

import domain.Sector

@Observable
object HomeSectores extends CollectionBasedHome[Sector]  {
  
  this.create(80.0, 'A')
  this.create(60.0, 'B')
  this.create(40.0, 'C')
  
  def create(valor:Double , letra: Char){
    var sector = new Sector(valor,letra)
    this.create(sector)
  }
  
  def search(letra:Char= '\0'){
    sectores.filter{ sector => this.coincide(sector.id , letra)}
  }
  
  def coincide(valorPosta:Char, valorEntrante:Char):Boolean = {
    if(valorEntrante == '\0')
      return true
    return (valorEntrante == valorPosta)
  }
  
    def get(id: Char ='\0'):Sector =
    sectores.find(_.id== id).getOrElse(null) // Acá hay que pensar algo.
  
  
  def sectores: java.util.List[Sector] = allInstances

  override def getEntityType = classOf[Sector]

  override def createExample = new Sector(10.0,'X')

  override def getCriterio(example: Sector) = null
}