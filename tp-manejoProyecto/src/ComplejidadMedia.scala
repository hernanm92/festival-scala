class ComplejidadMedia extends ComplejidadMinima {

   override def obtenerCosto(tiempo:Integer):Double ={
    return (tiempo * 25)*1.05;
  }
  override def diasMaximoAtraso(tiempo:Integer):Double ={
    return tiempo*0.1;
  }
  
}
  