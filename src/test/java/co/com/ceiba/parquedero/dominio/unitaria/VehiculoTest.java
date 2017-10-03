package co.com.ceiba.parquedero.dominio.unitaria;

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
		//arange
		VehiculoTestDataBuilder vehiculoTestDataBuilder = new VehiculoTestDataBuilder().
																conTipo(TIPO).conPlaca(PLACA).
																conCilindrada(CILINDRADA);
		
		//act
		Vehiculo vehiculo = vehiculoTestDataBuilder.build();
		
		//assert
		assertEquals(TIPO.toUpperCase(), vehiculo.getTipo());
		assertEquals(PLACA.toUpperCase(), vehiculo.getPlaca());
		assertEquals(CILINDRADA, vehiculo.getCilindrada());
	}
}
