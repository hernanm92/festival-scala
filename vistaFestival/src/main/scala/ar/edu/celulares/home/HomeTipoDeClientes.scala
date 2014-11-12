package ar.edu.celulares.home

import scala.collection.JavaConversions.asScalaBuffer
import org.uqbar.commons.model.CollectionBasedHome
import org.uqbar.commons.model.UserException
import org.uqbar.commons.utils.Observable
import org.apache.commons.collections15.Predicate
import org.uqbar.commons.utils.ApplicationContext

import domain.Cliente
import domain.TipoCliente
import domain.TipoCliente_Mayor
import domain.TipoCliente_Mujer
import domain.TipoCliente_Menor
import domain.TipoCliente_Jubilado
import domain.TipoCliente_MenorDe12Acompaniado
import domain.TipoCliente_MenorDe12NoAcompaniado

@Observable
object HomeTipoDeClientes extends CollectionBasedHome[TipoCliente]  {

  this.create(new TipoCliente_Mayor("Cliente Mayor"))
  this.create(new TipoCliente_Mujer("Cliente Mujer"))
  this.create(new TipoCliente_Menor("Cliente Menor de 18"))
  this.create(new TipoCliente_Jubilado("Cliente Jubilado"))
  this.create(new TipoCliente_MenorDe12Acompaniado("Cliente Menor de 12 Acompaniado"))
  this.create(new TipoCliente_MenorDe12NoAcompaniado("Cliente Menor de 12 No Acompaniado"))
  
  def search(nombre:String = ""){
    tiposDeCliente.filter { tipoCliente => this.coincide(tipoCliente.nombre, nombre)  }
    }
  

  def coincide(valorPosta: String, valorEntrante: String): Boolean = {
    if (valorEntrante == "")
      return true
    return valorPosta.toString().toLowerCase().contains(valorEntrante.toString().toLowerCase())
  }

  def get(nombre: String = ""): TipoCliente =
    tiposDeCliente.find(tipoCliente=> this.coincide(tipoCliente.nombre, nombre)).getOrElse(null) // Acá hay que pensar algo.
  
  def tiposDeCliente: java.util.List[TipoCliente] = allInstances

  override def getEntityType = classOf[TipoCliente]

  override def createExample = new TipoCliente_Mayor("Ezpezialll")

  override def getCriterio(example: TipoCliente) = null
  
}