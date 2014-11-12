package domain

import org.uqbar.commons.utils.Observable
import org.uqbar.commons.model.Entity

@Observable
abstract class TipoCliente(unNombre:String) extends Entity {

  var nombre:String= unNombre
  
  def dtoTipoPersona(precioBase: Double, festival: Festival): Double;
  
  override def toString:String = nombre

}

class TipoCliente_MenorDe12Acompaniado(unNombre:String) extends TipoCliente(unNombre) {

  override def dtoTipoPersona(precioBase: Double, festival: Festival): Double = {
    if (festival.descuentos.contains("menores de 12"))
      return (precioBase * 0.50);
    else
      return 0.0;
  }

}

class TipoCliente_MenorDe12NoAcompaniado(unNombre:String) extends TipoCliente(unNombre) {

  override def dtoTipoPersona(precioBase: Double, festival: Festival): Double = {
    return 0.0;
  }
  
}

class TipoCliente_Menor(unNombre:String) extends TipoCliente(unNombre) {

  override def dtoTipoPersona(precioBase: Double, festival: Festival): Double = {
    if (festival.descuentos.contains("menores de 18")) {
      if (precioBase > 100)
        return (precioBase * 0.20);
      if ((precioBase <= 100) && (precioBase > 50)) {
        return (10.0);
      } else
        return 0;
    }
    return 0.0;
  }

}

class TipoCliente_Mayor(unNombre:String) extends TipoCliente(unNombre) {
  override def dtoTipoPersona(precioBase: Double, festival: Festival): Double = {
    return 0;
  }

}

class TipoCliente_Jubilado(unNombre:String) extends TipoCliente(unNombre) {
  override def dtoTipoPersona(precioBase: Double, festival: Festival): Double = {
    if (festival.descuentos.contains("jubilados")) {
      return (precioBase * 0.15);
    }
    return 0;
  }
  
}

class TipoCliente_Mujer(unNombre:String) extends TipoCliente(unNombre) {

  override def dtoTipoPersona(precioBase: Double, festival: Festival): Double = {
    if (festival.descuentos.contains("mujeres")) {
      //println("Entradas vendidas en el festival " +festival.nombre +": " + festival.cantidadEntradas )
      if ((festival.cantidadEntradas == 0) || (festival.entradasDeMujeresConDescVendidas / festival.cantidadEntradas) < 0.20) {
        return (precioBase * 0.20);
      }
    }
    return 0;
  }

}