package ar.edu.celulares.home

import scala.collection.JavaConversions.asScalaBuffer
import org.uqbar.commons.model.CollectionBasedHome
import org.uqbar.commons.model.UserException
import org.uqbar.commons.utils.Observable
import org.apache.commons.collections15.Predicate
import org.uqbar.commons.utils.ApplicationContext
import domain.Sector
import domain.Categoria
import domain.Cliente

@Observable
object HomeClientes extends CollectionBasedHome[Cliente]  {

  
  this.create("Carlos", "Fernandez", "5432524545", "|40 anios|DNI = 17.897.364")

  
  this.create("Jose", "Gomez", "123324545", "|60 anios|DNI = 8.897.364");
  this.create("Facundo", "Hernandez", "862466754", "|15 anios|DNI = 42.897.364");
  this.create("Pablo", "Gimenez", "543252654", "|20 anios|DNI = 37.897.364");
  this.create("Pedrito", "Benitez", "4532524545", "|11 anios|DNI = 47.897.364");
  this.create("Florencia", "Rodriguez", "734624545", "|19 anios|DNI = 39.897.364");
  
  def create(nombre: String, apellido: String, nroTarjeta: String="", legales: String){
    var cliente = new Cliente(nombre, apellido, nroTarjeta, legales)
    this.create(cliente)
  }
  
  def search(nombre:String = "", apellido:String = "") ={
    clientes.filter { cliente => this.coincide(cliente._nombre, nombre) && this.coincide(cliente._apellido, apellido)  }
    }
  

  def coincide(valorPosta: String, valorEntrante: String): Boolean = {
    if (valorEntrante == "")
      return true
    return valorPosta.toString().toLowerCase().contains(valorEntrante.toString().toLowerCase())
  }

  def get(nombre: String = ""): Cliente =
    clientes.find(cliente=> this.coincide(cliente._nombre, nombre)).getOrElse(null) // Acá hay que pensar algo.
  
  def clientes: java.util.List[Cliente] = allInstances

  override def getEntityType = classOf[Cliente]

  override def createExample = new Cliente("Nene","Malo","123123123","asdasd  asasd" )

  override def getCriterio(example: Cliente) = null
  
}