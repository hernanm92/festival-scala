package domain

import org.joda.time._
import org.joda.convert._
import org.uqbar.commons.utils.Observable
import scala.collection.JavaConversions.asScalaBuffer
import org.uqbar.commons.model.Entity

@Observable
class Noche(unFestival: Festival,unaFecha:DateTime,unaHora:Int, unId :Int,
    lasBandas:List[Banda],lasButacas:List[Butaca]) extends Entity {
  
	var festival: Festival = unFestival
	var fecha: DateTime = unaFecha;
	var horaInicio: Int = unaHora;
	var bandas: List[Banda] = lasBandas;
	var butacasLibres: List[Butaca] = lasButacas;
	var butacasDePromocion: List[Butaca] = List.empty[Butaca];;
	var butacas: List[Butaca] = lasButacas;
	var id: Int  = unId;
	
	def reservarButaca(unaButacaPromocional: Butaca, suCodigo: String): Boolean ={
	  unaButacaPromocional.setCodigo(suCodigo);
	  butacasLibres= butacasLibres.diff(List(unaButacaPromocional));
  	  butacasDePromocion= butacasDePromocion.+:(unaButacaPromocional);
	  return true
	}
	
	
	def valorExtra():Double = {
	  return this.maxCategoria().costoExtra;
	}
	
	def maxCategoria():Categoria = {
			val categorias = bandas.map{unaBanda => unaBanda.categoria};
			return categorias.maxBy{unaCategoria => unaCategoria.nivel};
	}
	
	def cambiarCategoriaBanda(unNombreBanda:String, unId: Int, costoExtra: Int){
	  
	}


	override def toString():String = {
	  return fecha.getYear() + "-" +
	  fecha.getMonthOfYear() + "-" +
	  fecha.getDayOfMonth() + {
	    if(festival !=null)
	    " ( " +festival.nombre + " )"
	    else
	      ""}
	  //Seguramente se puede hacer mas facil y mejor 
	}
}

