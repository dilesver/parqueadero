package co.com.ceiba.parqueadero.dominio.unitaria;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import co.com.ceiba.parqueadero.dominio.Vehiculo;
import co.com.ceiba.parqueadero.testdatabuilder.VehiculoTestDataBuilder;

public class VehiculoTest {
	private static final String TIPO = "moto";
	private static final String PLACA = "pon12c";
	private static final Integer CILINDRADA = 124;
	
	@Test
	public void crearVehiculo() {
		// Arange
		VehiculoTestDataBuilder vehiculoTestDataBuilder = new VehiculoTestDataBuilder().
																conTipo(TIPO).conPlaca(PLACA).
																conCilindrada(CILINDRADA);
		
		// Act
		Vehiculo vehiculo = vehiculoTestDataBuilder.build();
		
		// Assert
		assertEquals(TIPO.toUpperCase(), vehiculo.getTipo());
		assertEquals(PLACA.toUpperCase(), vehiculo.getPlaca());
		assertEquals(CILINDRADA, vehiculo.getCilindrada());
	}
	
	@Test
	public void actualizarVehiculo() {
		// Arange
		VehiculoTestDataBuilder vehiculoTestDataBuilder = new VehiculoTestDataBuilder().
																conTipo(TIPO).conPlaca(PLACA).
																conCilindrada(CILINDRADA);
		
		// Act
		Vehiculo vehiculo1 = vehiculoTestDataBuilder.build();
		
		Vehiculo vehiculo2 = new Vehiculo();
		vehiculo2.setTipo(vehiculo1.getTipo());
		vehiculo2.setPlaca(vehiculo1.getPlaca());
		vehiculo2.setCilindrada(vehiculo1.getCilindrada());
		
		// Assert
		assertEquals(vehiculo1.getTipo(), vehiculo2.getTipo());
		assertEquals(vehiculo1.getPlaca(), vehiculo2.getPlaca());
		assertEquals(vehiculo1.getCilindrada(), vehiculo2.getCilindrada());
	}
}
