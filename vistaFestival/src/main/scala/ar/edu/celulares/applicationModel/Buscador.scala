package ar.edu.celulares.applicationModel
import domain._
import ar.edu.celulares.home._

@org.uqbar.commons.utils.Observable
abstract class Buscador extends Serializable{
  def search();
  def clear();
}