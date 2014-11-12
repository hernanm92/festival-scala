package utn.dds.persistencia.futbol.persistence;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import utn.dds.persistencia.futbol.model.InscripcionEstandar;
import utn.dds.persistencia.futbol.model.Jugador;
import utn.dds.persistencia.futbol.model.Partido;

public class HomePartidoTest extends PersistentTest {

	private HomePartido homePartidos = new HomePartido();
	private HomeJugador homeJugadores = new HomeJugador();
	
	@Before
	public void initialize() {
		for (int i = 0; i < 10; i++) {
			Jugador jugador = new Jugador("Jugador");
			homeJugadores.save(jugador);
		}
	}
	
	@Test
	public void testAllJugadores() {
		assertEquals(10, homeJugadores.all().size());
	}
	
	@Test
	public void testAllPartidos() {
		grabarPartidosCerrados(2);
		grabarPartidosAbiertos(2);
		
		assertEquals(4, homePartidos.all().size());
	}
	
	@Test
	public void testPartidosAbiertos() {
		grabarPartidosCerrados(3);
		grabarPartidosAbiertos(2);
		
		assertEquals(3, homePartidos.partidosAbiertos().size());
	}

	private void grabarPartidosAbiertos(int cantidad) {
		for (int i = 0; i < cantidad; i++) {
			homePartidos.save(new Partido());
		}
	}

	private void grabarPartidosCerrados(int cantidad) {
		for (int i = 0; i < cantidad; i++) {
			Partido partido = new Partido();
			inscribirJugadoresEstandar(partido);
			partido.cerrar();
			homePartidos.save(partido);
		}
	}

	private void inscribirJugadoresEstandar(Partido partido) {
		for (Jugador jugador : homeJugadores.all()) {
			partido.addInscripcion(new InscripcionEstandar(jugador));
		}
	}
	
}
