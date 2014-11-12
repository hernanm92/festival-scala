package utn.dds.persistencia.proyectos.model; 

import javax.persistence.*;
import utn.dds.persistencia.proyectos.persistence.*;

@Table(name="Impuestos")	//En la base de datos se va a llamar asi
@Entity
public class Impuesto extends PersistentObject{
	
	public Impuesto(char tipo, double porcentaje){
		this.tipoDeImpuesto = tipo;
		this.porcentaje = porcentaje;
	}
	
	@Column(name="tipoDeImpuesto")
	char tipoDeImpuesto;
	
	@Column(name="porcentaje")
	double porcentaje;
	
	public double costoImpositivo(double costoBase){
		return costoBase * porcentaje;
	}
}
