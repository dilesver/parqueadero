package co.com.ceiba.parqueadero.integracion;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.parqueadero.ParqueaderoBackendApplication;
import co.com.ceiba.parqueadero.dominio.Operario;
import co.com.ceiba.parqueadero.dominio.Vehiculo;
import co.com.ceiba.parqueadero.persistencia.repositorio.RepositorioParqueoPersistencia;
import co.com.ceiba.parqueadero.persistencia.repositorio.RepositorioVehiculoPersistencia;
import co.com.ceiba.parqueadero.testdatabuilder.VehiculoTestDataBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = ParqueaderoBackendApplication.class)
@ActiveProfiles("test")
public class OperarioTest {
	
	@Autowired	
	RepositorioVehiculoPersistencia repositorioVehiculo;
	
	@Autowired
	RepositorioParqueoPersistencia repositorioParqueo;
	
	private Vehiculo vehiculo;
	private Operario operario;
	
	@Before
	public void before() {
		vehiculo = new VehiculoTestDataBuilder().build();
		operario = new Operario(repositorioVehiculo, repositorioParqueo);
	}
	
	@Test
	public void registrarVehiculo() {
		// Arrange
		
		// Act
		boolean vehiculoRegistrado = operario.registrarVehiculo(vehiculo) != null;
		
		// Assert
		assertTrue(vehiculoRegistrado);
	}
	
	/*@Test
	public void entrarVehiculo_al_Prqueadero() {
		// Arrange
		Date fechaEntrada = new Date();
		
		// Act
		boolean parqueado = operario.entradaVehiculoParqueadero(vehiculo, fechaEntrada);
		
		// Assert
		assertTrue(parqueado);
	}*/
}
