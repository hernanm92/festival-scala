package utn.dds.persistencia.proyectos.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.*;
import utn.dds.persistencia.proyectos.persistence.*;

@Table(name= "Tareas")
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public class Tarea extends PersistentObject{
	
	public Tarea(double unCosto, ComplejidadMinima unaComplejidad){
		costoTiempo= unCosto;
		complejidad = unaComplejidad;
	}
	
	@ManyToOne
	ComplejidadMinima complejidad;
	
	@Column(name="Tiempo")
	double costoTiempo;
	
	@ManyToMany(cascade=CascadeType.ALL)
	private List<Impuesto> impuestos =new ArrayList<Impuesto>();
	
	public double costoTotal(){
		return this.costoBase() + this.costoDeLosImpuestos();
	}
	
	public double costoDeLosImpuestos(){
		if(getImpuestos().isEmpty())
			return 0;
		else{
			Iterator<Impuesto> it = getImpuestos().iterator();
			double suma=0;
			while (it.hasNext()){
				suma += it.next().costoImpositivo(this.costoBase());
			}
			return suma;
		}
	}
	
	public double costoBase(){
		return complejidad.costo(costoTiempo);
	}
	
	public void agregarUnImpuesto(Impuesto impuesto) {
		getImpuestos().add(impuesto);
	}

	public List<Impuesto> getImpuestos() {
		return impuestos;
	}

	public void setImpuestos(List<Impuesto> impuestos) {
		this.impuestos = impuestos;
	}
}
	
