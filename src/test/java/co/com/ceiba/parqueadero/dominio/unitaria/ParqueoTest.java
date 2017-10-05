package co.com.ceiba.parqueadero.dominio.unitaria;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;

import co.com.ceiba.parqueadero.dominio.Parqueo;
import co.com.ceiba.parqueadero.dominio.Vehiculo;
import co.com.ceiba.parqueadero.testdatabuilder.ParqueoTestDataBuilder;

public class ParqueoTest {
	private static final String TIPO = "moto";
	private static final String PLACA = "pon12c";
	private static final Integer CILINDRADA = 124;
	private static final Vehiculo VEHICULO = new Vehiculo(TIPO, PLACA, CILINDRADA);
	private static final Date FECHA_ENTRADA = new Date();
	private static final Date FECHA_SALIDA = new Date();
	private static final String DURACION = "0d";
	private static final Integer VALOR = 0;
	
	@Test
	public void crearParqueoCompleto() {
		// Arange
		ParqueoTestDataBuilder parqueoTestDataBuilder = new ParqueoTestDataBuilder().
															conVehiculo(VEHICULO).
															conFechaEntrada(FECHA_ENTRADA).
															conFechaSalida(FECHA_SALIDA).
															conDuracion(DURACION).
															conValor(VALOR);
		
		// Act
		Parqueo parqueo = parqueoTestDataBuilder.build();
		
		// Assert
		assertEquals(VEHICULO, parqueo.getVehiculo());
		assertEquals(FECHA_ENTRADA, parqueo.getFechaEntrada());
		assertEquals(FECHA_SALIDA, parqueo.getFechaSalida());
		assertEquals(DURACION, parqueo.getDuracion());
		assertEquals(VALOR, parqueo.getValor());
	}
	
	@Test
	public void crearParqueoVehiculoFechaEntrada() {
		// Arange
		ParqueoTestDataBuilder parqueoTestDataBuilder = new ParqueoTestDataBuilder().
															conVehiculo(VEHICULO).
															conFechaEntrada(FECHA_ENTRADA);
		
		// Act
		Parqueo parqueo1 = parqueoTestDataBuilder.build();
		Parqueo parqueo2 = new Parqueo(parqueo1.getVehiculo(), parqueo1.getFechaEntrada());
		
		// Assert
		assertEquals(parqueo1.getVehiculo(), parqueo2.getVehiculo());
		assertEquals(parqueo1.getFechaEntrada(), parqueo2.getFechaEntrada());
	}
	
	@Test
	public void actualizarParqueo() {
		// Arange
		ParqueoTestDataBuilder parqueoTestDataBuilder = new ParqueoTestDataBuilder().
															conVehiculo(VEHICULO).
															conFechaEntrada(FECHA_ENTRADA).
															conFechaSalida(FECHA_SALIDA).conDuracion(DURACION).
															conValor(VALOR);
		
		// Act
		Parqueo parqueo1 = parqueoTestDataBuilder.build();
		Parqueo parqueo2 = new Parqueo();
		parqueo2.setVehiculo(parqueo1.getVehiculo());
		parqueo2.setFechaEntrada(parqueo1.getFechaEntrada());
		parqueo2.setFechaSalida(parqueo1.getFechaSalida());
		parqueo2.setDuracion(parqueo1.getDuracion());
		parqueo2.setValor(parqueo1.getValor());
		
		// Assert
		assertEquals(VEHICULO, parqueo2.getVehiculo());
		assertEquals(FECHA_ENTRADA, parqueo2.getFechaEntrada());
		assertEquals(FECHA_SALIDA, parqueo2.getFechaSalida());
		assertEquals(DURACION, parqueo2.getDuracion());
		assertEquals(VALOR, parqueo2.getValor());
	}
}
