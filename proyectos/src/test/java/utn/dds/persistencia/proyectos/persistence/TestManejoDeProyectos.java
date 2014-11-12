package utn.dds.persistencia.proyectos.persistence; 

import org.junit.*;

import utn.dds.persistencia.proyectos.model.*;
import static org.junit.Assert.assertEquals;

public class TestManejoDeProyectos {
	
	Proyecto aprobarOperativos;
	Tarea armarGrupo;
	Tarea codear;
	Tarea testear;
	Tarea usarVmServer;
	TareaCompuesta entregaFinal;
	TareaCompuesta hacerTp;
	Impuesto compraDeRedbull;
	Impuesto horasDeSueño;
	ComplejidadMaxima reJodido;
	ComplejidadMedia tranca;
	ComplejidadMinima unaBoludez;
	
	@Before
	public void initialize() {
		reJodido = new ComplejidadMaxima();
		tranca = new ComplejidadMedia();
		unaBoludez = new ComplejidadMinima();
		compraDeRedbull = new Impuesto('A',0.03);
		horasDeSueño = new Impuesto('B', 0.07);
		aprobarOperativos = new Proyecto();
		armarGrupo = new Tarea(19, reJodido);
		codear = new Tarea(17, tranca);
		testear = new Tarea(5,reJodido);
		usarVmServer = new Tarea(21, unaBoludez);
		entregaFinal = new TareaCompuesta(40, unaBoludez);
		hacerTp = new TareaCompuesta(30, unaBoludez);
	}
	
	@Test
	public void costoDeUnaSubtareaSinImpuesto(){
		assertEquals( 446.25,codear.costoBase(),0);
	}
	
	@Test
	public void costoDeUnaSubtareaConMasDeUnImpuesto(){
		codear.agregarUnImpuesto(compraDeRedbull);
		codear.agregarUnImpuesto(horasDeSueño);
		assertEquals( 446.25,codear.costoBase(),0);
		assertEquals( 490.875, codear.costoTotal(), 0);
	}
	
	@Test
	public void costoDeUnaTareaCompuestaSinImpuestos(){
		assertEquals( 1000,entregaFinal.costoTotal(),0);
	}
	
	@Test
	public void costoDeUnaSubtareaDeComplejidadMinima(){
		usarVmServer.agregarUnImpuesto(compraDeRedbull);
		assertEquals( 540.75, usarVmServer.costoTotal(),0); 
	}
	
	@Test
	public void costoDeUnaSubtareaDeComplejidadMedia(){
		codear.agregarUnImpuesto(compraDeRedbull);
		assertEquals( 459.6375, codear.costoTotal(), 0);
	}
	
	
	@Test
	public void costoDeUnaSubtareaConComplejidadMaximaYQueTieneMenosDeDiezDias(){
		testear.agregarUnImpuesto(horasDeSueño);
		assertEquals (143.1125, testear.costoTotal(), 0);
	}
	
	@Test
	public void costoDeUnaSubtareaDeComplejidadMaximaYQueTieneMasDeDiezDias(){
		armarGrupo.agregarUnImpuesto(horasDeSueño);
		assertEquals( 640.1275, armarGrupo.costoTotal(), 0);
	}
	
	
	@Test
	public void costoDeUnaTareaConPocasSubtareas(){
		entregaFinal.agregarSubtarea(armarGrupo);
		entregaFinal.agregarSubtarea(codear);
		
		assertEquals (1000,entregaFinal.costoBase(),0);
		assertEquals (2044.5,entregaFinal.costoTotal(),0);
	}
	
	@Test
	public void costoDeTareaConMuchasSubtareasQueAlFinalTieneOverhead(){
		entregaFinal.agregarSubtarea(armarGrupo);
		entregaFinal.agregarSubtarea(codear);
		entregaFinal.agregarSubtarea(testear);
		entregaFinal.agregarSubtarea(usarVmServer);

		assertEquals (1000,entregaFinal.costoBase(),0);
		assertEquals (2743.25,entregaFinal.costoTotal(),0);
	}
	
	@Test
	public void costoDeTodoUnProyecto(){
		entregaFinal.agregarUnImpuesto(compraDeRedbull);
		entregaFinal.agregarSubtarea(armarGrupo);
		hacerTp.agregarSubtarea(testear);
		hacerTp.agregarSubtarea(usarVmServer);
		hacerTp.agregarUnImpuesto(horasDeSueño);
		aprobarOperativos.agregarUnaTarea(entregaFinal);
		aprobarOperativos.agregarUnaTarea(hacerTp);
		
		assertEquals (3089.5,aprobarOperativos.costoTotal(),0);
	}
}
