package utn.dds.persistencia.proyectos.persistence;

import static utn.dds.persistencia.proyectos.persistence.ModelManager.getManager;
import java.util.List;
import utn.dds.persistencia.proyectos.model.ComplejidadMinima;

public class HomeComplejidad {

	public void save(ComplejidadMinima complejidad) {
		getManager().persist(complejidad);
	}
	
	public List<ComplejidadMinima> all() {
		return getManager().createQuery("from ComplejidadMinima").getResultList();
	}

}