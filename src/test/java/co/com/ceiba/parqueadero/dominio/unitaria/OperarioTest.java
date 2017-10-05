package co.com.ceiba.parqueadero.dominio.unitaria;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.Test;

import co.com.ceiba.parqueadero.dominio.Operario;
import co.com.ceiba.parqueadero.dominio.Parqueo;
import co.com.ceiba.parqueadero.dominio.Vehiculo;
import co.com.ceiba.parqueadero.dominio.repositorio.RepositorioParqueo;
import co.com.ceiba.parqueadero.dominio.repositorio.RepositorioVehiculo;
import co.com.ceiba.parqueadero.testdatabuilder.ParqueoTestDataBuilder;
import co.com.ceiba.parqueadero.testdatabuilder.VehiculoTestDataBuilder;

public class OperarioTest {
	
	@Test
	public void entradaVehiculoParqueaderoTest() {
		// Arrange
		VehiculoTestDataBuilder vehiculoTestDataBuilder = new VehiculoTestDataBuilder();
		Vehiculo vehiculo = vehiculoTestDataBuilder.build();
		RepositorioVehiculo respositorioVehiculo = mock(RepositorioVehiculo.class);
		when(respositorioVehiculo.obtenerPorPlaca(vehiculo.getPlaca())).thenReturn(vehiculo);
		
		ParqueoTestDataBuilder parqueoTestDataBuilder = new ParqueoTestDataBuilder().conVehiculo(vehiculo);
		Parqueo parqueo = parqueoTestDataBuilder.build();
		RepositorioParqueo respositorioParqueo = mock(RepositorioParqueo.class);
		when(respositorioParqueo.obtenerVehiculoParqueadoPorPlaca(vehiculo.getPlaca())).thenReturn(vehiculo);
		when(respositorioParqueo.entrada(any(Parqueo.class))).thenReturn(true);
		
		Operario operario = new Operario(respositorioVehiculo, respositorioParqueo);
		
		// Act
		boolean parqueado = operario.entradaVehiculoParqueadero(vehiculo, new Date());
		
		// Assert
		assertTrue(parqueado);
	}
}
