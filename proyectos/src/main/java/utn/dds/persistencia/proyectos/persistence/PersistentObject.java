package utn.dds.persistencia.proyectos.persistence;

import javax.persistence.*;

@MappedSuperclass
public abstract class PersistentObject {
	
	@Id
	@GeneratedValue
	Long id;
}
