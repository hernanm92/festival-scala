package utn.dds.persistencia.proyectos.model; 

import javax.persistence.*;

import utn.dds.persistencia.proyectos.persistence.PersistentObject;

@Table(name="Complejidades")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity
public class ComplejidadMinima extends PersistentObject{

	public double costo(double tiempo){
		return tiempo*25;
	}
}
