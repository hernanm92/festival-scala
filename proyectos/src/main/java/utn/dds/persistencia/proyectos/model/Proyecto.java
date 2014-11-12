package utn.dds.persistencia.proyectos.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.*;
import utn.dds.persistencia.proyectos.persistence.*;

@Table(name="Proyectos")
@Entity
public class Proyecto extends PersistentObject{
	
	@JoinColumn(name="proyecto")
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	List<Tarea> tareas = new ArrayList<Tarea>();
	
	public double costoTotal(){
		Iterator<Tarea> it = tareas.iterator();
		double suma=0;
		while (it.hasNext()){
			suma += it.next().costoTotal();
		}
		return suma;
	}
	
	public void agregarUnaTarea(Tarea tarea){
		tareas.add(tarea);
	}
}
