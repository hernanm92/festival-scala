class ComplejidadMaxima extends ComplejidadMinima{

  override def obtenerCosto(tiempo:Integer):Double ={
    if(tiempo<=10)
    	return (tiempo * 25)*1.07;
    else
      return (tiempo * 25)*1.07 + 10 *(tiempo-10);
  } 

}