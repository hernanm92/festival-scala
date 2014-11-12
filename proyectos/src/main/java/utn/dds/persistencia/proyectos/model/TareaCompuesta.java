package utn.dds.persistencia.proyectos.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.*;


@Entity
public class TareaCompuesta extends Tarea {
	
	@JoinColumn(name = "tareaPadre")
	@OneToMany(cascade = CascadeType.PERSIST)
	List<Tarea> subtareas = new ArrayList<Tarea>();
	
	public TareaCompuesta(double unCosto, ComplejidadMinima unaComplejidad) {
		super(unCosto,unaComplejidad);
	}

	public double costoTotal(){
		return super.costoTotal() + this.costoDeLasSubtareas() + this.overhead();
	}
	
	public double costoDeLasSubtareas(){
		if(subtareas.isEmpty())
			return 0;
		else{
			Iterator<Tarea> it = subtareas.iterator();
			double suma=0;
			while (it.hasNext()){
				suma += it.next().costoTotal();
			}
			return suma;
		}
	}

	public double overhead(){
		if(this.subtareas.size()>3)
			return super.costoBase()*0.04;
		else 
			return 0;
	}
	
	public void agregarSubtarea(Tarea tarea){
		subtareas.add(tarea);
	}
}