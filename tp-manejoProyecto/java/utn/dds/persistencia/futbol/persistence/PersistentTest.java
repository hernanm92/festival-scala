package utn.dds.persistencia.futbol.persistence;

import static utn.dds.persistencia.futbol.persistence.ModelManager.beginTransaction;
import static utn.dds.persistencia.futbol.persistence.ModelManager.rollback;

import org.junit.After;
import org.junit.Before;

public abstract class PersistentTest {
	
	@Before
	public void init() {
		beginTransaction();		
	}
	
	@After
	public void finish() {
		rollback();
	}
	
}
