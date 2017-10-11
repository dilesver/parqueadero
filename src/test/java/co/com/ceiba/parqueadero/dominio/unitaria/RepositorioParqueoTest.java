package co.com.ceiba.parqueadero.dominio.unitaria;

import java.util.Date;

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
import co.com.ceiba.parqueadero.dominio.Parqueo;
import co.com.ceiba.parqueadero.dominio.Vehiculo;
import co.com.ceiba.parqueadero.persistencia.dao.ParqueoDao;
import co.com.ceiba.parqueadero.persistencia.dao.VehiculoDao;
import co.com.ceiba.parqueadero.persistencia.repositorio.RepositorioParqueoPersistencia;
import co.com.ceiba.parqueadero.persistencia.repositorio.RepositorioVehiculoPersistencia;
import co.com.ceiba.parqueadero.testdatabuilder.ParqueoTestDataBuilder;
import co.com.ceiba.parqueadero.testdatabuilder.VehiculoTestDataBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = ParqueaderoBackendApplication.class)
@ActiveProfiles("test")
public class RepositorioParqueoTest {
	@Autowired
	RepositorioVehiculoPersistencia repositorioVehiculos;
	
	@Autowired
	RepositorioParqueoPersistencia repositorioParqueo;
	
	@Autowired
	VehiculoDao vehiculoDao;
	
	@Autowired
	ParqueoDao parqueoDao;
	
	@Before
	public void before() {
		//parqueoDao.deleteAll();
		//vehiculoDao.deleteAll();
	}
	
	@Test
	public void parquearVehiculo() {
		// Arrange
		Vehiculo vehiculo = new VehiculoTestDataBuilder().build();
		vehiculo = repositorioVehiculos.agregar(vehiculo);
		vehiculo = repositorioVehiculos.obtenerPorPlaca(vehiculo.getPlaca());
		
		Parqueo parqueo = new ParqueoTestDataBuilder().conVehiculo(vehiculo).build();
		
		// Act
		boolean parqueado = repositorioParqueo.entrada(parqueo);
		
		// Assert
		 Assert.assertTrue(parqueado);
	}

	@Test
	public void salidaVehiculo() {
		// Arrange
		Vehiculo vehiculo = new VehiculoTestDataBuilder().build();
		
		// Act
		boolean salida    = repositorioParqueo.salida(vehiculo.getPlaca(), new Date(), "0 min", 0) != null;
		
		// Assert
		 Assert.assertFalse(salida);
	}
}
