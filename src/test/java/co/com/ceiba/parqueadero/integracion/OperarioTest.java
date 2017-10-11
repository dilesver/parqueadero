package co.com.ceiba.parqueadero.integracion;

import static org.junit.Assert.assertNotNull;
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
import co.com.ceiba.parqueadero.dominio.Parqueo;
import co.com.ceiba.parqueadero.dominio.Vehiculo;
import co.com.ceiba.parqueadero.persistencia.dao.ParqueoDao;
import co.com.ceiba.parqueadero.persistencia.dao.VehiculoDao;
import co.com.ceiba.parqueadero.persistencia.repositorio.RepositorioParqueoPersistencia;
import co.com.ceiba.parqueadero.persistencia.repositorio.RepositorioVehiculoPersistencia;
import co.com.ceiba.parqueadero.testdatabuilder.VehiculoTestDataBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = ParqueaderoBackendApplication.class)
@ActiveProfiles("test")
public class OperarioTest {
	
	@Autowired	
	private RepositorioVehiculoPersistencia repositorioVehiculo;
	
	@Autowired
	private RepositorioParqueoPersistencia repositorioParqueo;
	
	private Vehiculo vehiculo;
	
	@Autowired
	private Operario operario;
	
	@Autowired
	VehiculoDao vehiculoDao;
	
	@Autowired
	ParqueoDao parqueoDao;
	
	@Before
	public void before() {
		parqueoDao.deleteAll();
		vehiculoDao.deleteAll();
		
		vehiculo = new VehiculoTestDataBuilder().build();
		//operario = new Operario(repositorioVehiculo, repositorioParqueo);
	}
	
	@Test
	public void registrarVehiculo() {
		// Arrange
		/*Vehiculo vehiculoBuscado = repositorioVehiculo.obtenerPorPlaca(vehiculo.getPlaca());
		if (vehiculoBuscado != null) {
			repositorioVehiculo.eliminar(vehiculoBuscado.getId());
		}*/
		
		if (operario.vehiculoRegistrado(vehiculo)) {
			repositorioVehiculo.eliminar(repositorioVehiculo.obtenerPorPlaca(vehiculo.getPlaca()).getId());
		}
		
		// Act
		boolean vehiculoRegistrado = operario.registrarVehiculo(vehiculo) != null;
		
		if (vehiculoRegistrado) {
			Vehiculo vehiculoEncontrado = repositorioVehiculo.obtenerPorPlaca(vehiculo.getPlaca());
			repositorioVehiculo.eliminar(vehiculoEncontrado.getId());
		}
		
		// Assert
		assertTrue(vehiculoRegistrado);
	}
	
	@Test
	public void entrarVehiculo_al_Prqueadero() {
		// Arrange
		Date fechaEntrada = new Date();
		
		// Act
		boolean parqueado = operario.entradaVehiculoParqueadero(vehiculo, fechaEntrada);
				
		// Assert
		assertTrue(parqueado);
	}
	
	/*@Test
	public void salidaVehiculo_al_Parqueadero() {
		// Arrange
		Date fechaSalida = new Date();
		Vehiculo vehiculo_a_estacionar = operario.registrarVehiculo(vehiculo);
		operario.entradaVehiculoParqueadero(vehiculo_a_estacionar, fechaSalida);
		
		// Act
		Parqueo parqueado = operario.salidaVehiculoParqueadero(vehiculo_a_estacionar, fechaSalida);
				
		// Assert
		assertNotNull(parqueado);
	}*/
	
	@Test
	public void consultarVehiculoRegistrado() {
		// Arrange
		boolean vehiculoGuardado = operario.registrarVehiculo(vehiculo) != null;
		
		// Act
		boolean vehiculoRegistrado = (vehiculoGuardado) ? operario.vehiculoRegistrado(vehiculo) : false;
		
		// Assert
		assertTrue(vehiculoRegistrado);
	}
}
