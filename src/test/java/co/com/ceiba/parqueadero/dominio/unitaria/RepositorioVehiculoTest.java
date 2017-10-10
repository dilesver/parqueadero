package co.com.ceiba.parqueadero.dominio.unitaria;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.parqueadero.ParqueaderoBackendApplication;
import co.com.ceiba.parqueadero.dominio.Vehiculo;
import co.com.ceiba.parqueadero.persistencia.dao.ParqueoDao;
import co.com.ceiba.parqueadero.persistencia.dao.VehiculoDao;
import co.com.ceiba.parqueadero.persistencia.repositorio.RepositorioVehiculoPersistencia;
import co.com.ceiba.parqueadero.testdatabuilder.VehiculoTestDataBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = ParqueaderoBackendApplication.class)
@ActiveProfiles("test")
public class RepositorioVehiculoTest {
	@Autowired
	RepositorioVehiculoPersistencia repositorio;
	
	@Autowired
	VehiculoDao vehiculoDao;
	
	@Autowired
	ParqueoDao parqueoDao;
	
	@Before
	public void before() {
		parqueoDao.deleteAll();
		vehiculoDao.deleteAll();
	}
	
	@Test
	public void guardarVehiculo() {
		// Arrange
		Vehiculo vehiculo = new VehiculoTestDataBuilder().build();
		repositorio.agregar(vehiculo);
		
		// Act
		Vehiculo vehiculo_guardado = repositorio.obtenerPorPlaca(vehiculo.getPlaca());
		repositorio.eliminar(vehiculo_guardado.getId());
		
		// Assert
		 Assert.assertEquals(vehiculo.getPlaca(), vehiculo_guardado.getPlaca());
	}
	
	@Test
	public void consultarVehiculoInexistence() {
		// Arrange
		Vehiculo vehiculo = new VehiculoTestDataBuilder().conPlaca("").build();
		
		// Act
		Vehiculo vehiculo_encontrado = repositorio.obtenerPorPlaca(vehiculo.getPlaca());
		
		// Assert
		 Assert.assertNull(vehiculo_encontrado);
	}
}
