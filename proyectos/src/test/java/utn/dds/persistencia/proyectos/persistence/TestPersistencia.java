package utn.dds.persistencia.proyectos.persistence; 

import java.util.List;

import org.junit.*;

import utn.dds.persistencia.proyectos.model.*;
import static org.junit.Assert.assertEquals;
import static utn.dds.persistencia.proyectos.persistence.ModelManager.beginTransaction;
import static utn.dds.persistencia.proyectos.persistence.ModelManager.rollback;


public class TestPersistencia  {
	
	HomeTarea homeTarea = new HomeTarea();
	HomeImpuesto homeImpuesto = new HomeImpuesto();
	HomeProyecto homeProyecto = new HomeProyecto();
	HomeComplejidad homeComplejidad = new HomeComplejidad();
	
	@Before
	public void init() {
		beginTransaction();
		Proyecto proyecto = new Proyecto();
		Impuesto impuestoA = new Impuesto('A', 0.12);
		Impuesto impuestoB = new Impuesto('B', 0.22);
		Impuesto impuestoC = new Impuesto('C', 0.07);
		ComplejidadMinima minima = new ComplejidadMinima();
		ComplejidadMaxima maxima = new ComplejidadMaxima();
		homeComplejidad.save(minima);
		homeComplejidad.save(maxima);
		int i;
		for (i=0;i <= 5; i++) {
			Tarea tarea1 = new Tarea(2*i, minima);
			Tarea tarea2 = new Tarea(5*i, maxima);
			tarea1.agregarUnImpuesto(impuestoA);
			tarea2.agregarUnImpuesto(impuestoB);
			TareaCompuesta tarea3 = new TareaCompuesta(i+1,minima);
			tarea3.agregarSubtarea(tarea1);
			tarea3.agregarSubtarea(tarea2);
			tarea3.agregarUnImpuesto(impuestoC);
			proyecto.agregarUnaTarea(tarea3);
		}
		homeProyecto.save(proyecto);
	}
	
	@After
	public void finish() {
		rollback();
	}

	@Test
	public void contextUp() {
		ModelManager.getManager();
	}
	
	@Test
	public void testAllTareas(){
		assertEquals (18, homeTarea.all().size());
	}
	
	@Test
	public void testAllSimples(){
		assertEquals (12, homeTarea.allTareasSimples().size());
	}
	
	@Test
	public void testAllTareasCompuestas(){
		assertEquals (6, homeTarea.allTareasCompuestas().size());
	}

	@Test
	public void testAllProyectos(){
		assertEquals (1, homeProyecto.all().size());
	}
	
	@Test
	public void testAllComplejidades(){
		assertEquals (2, homeComplejidad.all().size());
	}
	
	@Test
	public void testAllImpuestos(){
		assertEquals (3, homeImpuesto.all().size());
	}
	
	@Test 
	public void testTareasDeMenosDe2Dias(){
		List<Tarea> lista = homeTarea.getTareasDeMenosDeDosDias(); 
		assertEquals (2, lista.size());
	}

}
