package ar.edu.celulares.home

import scala.collection.JavaConversions._
import domain.Noche
import domain.Banda
import domain.Butaca
import domain.Categoria
import domain.Sector
import domain.Fila
import domain.EntradaComun
import domain.PagoEnEfectivo
import domain.Entrada
import domain.PagoConTarjeta
import domain.Pedido
import domain.TipoCliente_Mayor
import domain.TipoCliente_Mujer
import domain.TipoCliente_Menor
import domain.TipoCliente_Jubilado
import domain.TipoCliente_MenorDe12Acompaniado
import domain.TipoCliente_MenorDe12NoAcompaniado
import domain.Cliente
import domain.Festival
import domain.Pago
import scala.collection.JavaConversions.asScalaBuffer
import scala.collection.JavaConverters._
import org.uqbar.commons.model.CollectionBasedHome
import org.uqbar.commons.utils.Observable
import org.joda.time.DateTime
import domain.NroFactura
import java.awt.geom.FlatteningPathIterator

@Observable
object HomeFestivales extends CollectionBasedHome[Festival] {

  this.create("NoviembreFest",List[String]("mujeres", "menores de 18", "menores de 12", "jubilados"),
      HomeNoches.search("noviembreFest"));
      
  this.create("DiciembreFest",List[String]("mujeres", "menores de 18", "menores de 12", "jubilados"),
      HomeNoches.search("diciembreFest"));    
  
  
  def create(nombre: String, descuentos: List[String],noches:java.util.List[Noche]): Unit = {
    var noviembreFest:Festival  = new Festival(nombre)
    noviembreFest.descuentos = descuentos
    noviembreFest.noches = noches.asScala.toList
    this.create(noviembreFest)
  }
  
  
    def searchXFestival(nombre:String = "", festival:Festival) : List[Banda] ={
	  var r1 =  searchBandasFestival(festival).filter{unaBanda=> this.coincide(unaBanda.toString(), nombre)}
	  return r1.foldLeft(List.empty[Banda]){( lista:List[Banda], unaBanda )=> lista.+:(unaBanda)}.removeDuplicates
  }
  
  def searchBandasFestival(festival:Festival):Seq[Banda] ={
    var nom:String=""
    if (festival!=null) nom= festival.nombre
    var n1= this.search(nom)
    var r1 = n1.map{unFestival=> unFestival.noches}.flatten
    var r2 = r1.map{unaNoche => unaNoche.bandas}.flatten
    return r2
  }
  
  def search(nombre: String = "") = {
    festivales.filter { festival => this.coincide(festival.nombre, nombre) }
  }

  def coincide(valorPosta: String, valorEntrante: String): Boolean = {
    if (valorEntrante == "")
      return true
    return valorPosta.toString().toLowerCase().contains(valorEntrante.toString().toLowerCase())
  }

  def get(nombre: String = ""): Festival =
    festivales.find(festival => this.coincide(festival.nombre, nombre)).getOrElse(null) // Acá hay que pensar algo.
  
  def festivales: java.util.List[Festival] = allInstances

  override def getEntityType = classOf[Festival]

  override def createExample = new Festival("navidadFest")

  override def getCriterio(example: Festival) = null

}