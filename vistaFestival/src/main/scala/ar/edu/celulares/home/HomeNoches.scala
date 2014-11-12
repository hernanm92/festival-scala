package ar.edu.celulares.home

import scala.collection.JavaConversions._
import scala.collection.JavaConversions.asScalaBuffer
import scala.collection.JavaConverters._
import org.uqbar.commons.model.CollectionBasedHome
import org.uqbar.commons.model.UserException
import org.uqbar.commons.utils.Observable
import org.apache.commons.collections15.Predicate
import org.uqbar.commons.utils.ApplicationContext

import org.joda.time.DateTime

import domain._

@Observable
object HomeNoches extends CollectionBasedHome[Noche] {

  /** Noches del NoviembreFest**/
 var festival1 = new Festival("noviembreFest")
 var festival2 = new Festival("diciembreFest")
  
  this.create(festival1, new DateTime("2013-12-20"),
    20, 1, List[Banda](HomeBandas.get("Los Piojos")), HomeButacas.butacas);

  this.create(festival1, new DateTime("2013-12-21"),
    20, 2, List[Banda](HomeBandas.get("arbol")), HomeButacas.butacas);

  this.create(festival1, new DateTime("2013-12-22"),
    19, 3, List[Banda](HomeBandas.get("la Vela Puerca"), HomeBandas.get("NTVG")), HomeButacas.butacas);

  this.create(festival1, new DateTime("2013-12-23"),
    19, 4, List[Banda](HomeBandas.get("callejeros"), HomeBandas.get("Los Rolling Stone")), HomeButacas.butacas);

  this.create(festival1, new DateTime("2013-12-24"),
    21, 5, List[Banda](HomeBandas.get("los autenticos decadentes")), HomeButacas.butacas);

  /** Noches del DiciembreFest**/

  this.create(festival2, new DateTime("2013-12-26"),
    23, 6, List[Banda](HomeBandas.get("los autenticos decadentes"),HomeBandas.get("callejeros")), HomeButacas.butacas);
  
  this.create(festival2, new DateTime("2013-12-27"),
    22, 7, List[Banda](HomeBandas.get("E")), HomeButacas.butacas);
  
  this.create(festival2, new DateTime("2013-12-28"),
    21, 8, List[Banda](HomeBandas.get("callejeros"), HomeBandas.get("Los Rolling Stone")), HomeButacas.butacas);
  
  
  

  def create(unFestival: Festival, unaFecha: DateTime, unaHora: Int, unId: Int,
    lasBandas: List[Banda], lasButacas: Seq[Butaca]): Unit = {

    /*Es muuuuuy turbio lo que hice en la linea de abajo con las butacas, sino no me dejaba*/
    var noche = new Noche(unFestival, unaFecha, unaHora, unId,
      lasBandas, lasButacas.toList);
    this.create(noche)
  }

  /**-------Filtros por ID  - Begin ---------**/

  def searchPorInt(id: Int = -1) {
    noches.filter { noche => this.coincideInt(noche.id, id) }
  }

  def coincideInt(valorPosta: Int, valorEntrante: Int): Boolean = {
    if (valorEntrante == -1)
      return true
    return (valorEntrante == valorPosta)
  }

  def getPorInt(id: Int): Noche =
    noches.find(_.id == id).getOrElse(null); // Acá hay que pensar algo.

  /**--------Filtros por ID  - End---------- **/

  /** ----Filtros por Festival  - Begin ------**/
  
  
  def search(nombreFestival:String=""):Seq[Noche] = {
    return noches.filter { noche => this.coincide(noche.festival.nombre, nombreFestival) }
  }

  def coincide(valorPosta: String, valorEntrante: String): Boolean = {
    if (valorEntrante == "")
      return true
    return valorPosta.toString().toLowerCase().contains(valorEntrante.toString().toLowerCase())
  }

  def get(nombreFestival: String = ""): Noche =
    noches.find(noche => this.coincide(noche.festival.nombre, nombreFestival)).getOrElse(null) 
  
  
  
  /**------Filtros por Festival  - End --------**/

  def noches: Seq[Noche] = allInstances

  override def getEntityType = classOf[Noche]

  override def createExample = new Noche(HomeFestivales.createExample, new DateTime("2013-12-31"),
    21, 500, List[Banda](HomeBandas.createExample), List[Butaca](HomeButacas.createExample))

  override def getCriterio(example: Noche) = null

}