package utn.dds.persistencia.proyectos.model; 

import javax.persistence.*;

@Entity
public class ComplejidadMaxima extends ComplejidadMinima{

	@Override
	public double  costo(double tiempo){
		double costo = super.costo(tiempo);
		costo = costo * 1.07;
		if(tiempo > 10)
			return costo + (tiempo-10) * 10;
		else 
			return costo;
	}
}
