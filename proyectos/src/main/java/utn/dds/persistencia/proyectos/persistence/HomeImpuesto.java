package utn.dds.persistencia.proyectos.persistence;

import static utn.dds.persistencia.proyectos.persistence.ModelManager.getManager;
import java.util.List;
import utn.dds.persistencia.proyectos.model.Impuesto;

public class HomeImpuesto {

	public void save(Impuesto impuesto) {
		getManager().persist(impuesto);
	}
	
	public List<Impuesto >all() {
		return getManager().createQuery("from Impuesto").getResultList();
	}

}
