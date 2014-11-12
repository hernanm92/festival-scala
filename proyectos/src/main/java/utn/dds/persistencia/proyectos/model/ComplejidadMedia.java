package utn.dds.persistencia.proyectos.model; 

import javax.persistence.*;

@Entity
public class ComplejidadMedia extends ComplejidadMinima{
	
	public double costo(double tiempo){
		double costo = super.costo(tiempo);
		return costo+costo * 0.05;
	}
}
