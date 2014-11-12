package utn.dds.persistencia.proyectos.persistence;

import static utn.dds.persistencia.proyectos.persistence.ModelManager.getManager;
import java.util.List;
import javax.persistence.Query;
import utn.dds.persistencia.proyectos.model.Proyecto;;

public class HomeProyecto {

	public void save(Proyecto proyecto) {
		getManager().persist(proyecto);
	}
	
	public List<Proyecto> all() {
		Query query = getManager().createQuery("from Proyecto");
		return query.getResultList();
	}

}
