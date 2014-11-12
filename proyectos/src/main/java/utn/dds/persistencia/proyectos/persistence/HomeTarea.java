package utn.dds.persistencia.proyectos.persistence;

import static utn.dds.persistencia.proyectos.persistence.ModelManager.getManager;

import java.util.List;

import javax.persistence.Query;

import utn.dds.persistencia.proyectos.model.Tarea;
import utn.dds.persistencia.proyectos.model.TareaCompuesta;

public class HomeTarea {

	public void save(Tarea tarea) {
		getManager().persist(tarea);
	}
	
	public List<Tarea> all() {
		Query query = getManager().createQuery("from " + Tarea.class.getSimpleName());
		return query.getResultList();
	}
	
	public List<Tarea> allTareasSimples() {
		Query query = getManager().createQuery("from "+Tarea.class.getSimpleName()+" where tareaPadre is not null");
		return query.getResultList();
	}
	
	public List<Tarea> allTareasCompuestas() {
		Query query = getManager().createQuery("from "+ TareaCompuesta.class.getSimpleName());
		return query.getResultList();
	}
	
	public List<Tarea> getTareasDeMenosDeDosDias(){
		Query query = getManager().createQuery("from "+ Tarea.class.getSimpleName() +" where costoTiempo > 15");
		return query.getResultList();
	}
}
