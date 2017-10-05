package co.com.ceiba.parqueadero.testdatabuilder;

import java.util.Date;

import co.com.ceiba.parqueadero.dominio.Parqueo;
import co.com.ceiba.parqueadero.dominio.Vehiculo;

public class ParqueoTestDataBuilder {
	private static final String TIPO = "MOTO";
	private static final String PLACA = "PON12C";
	private static final Integer CILINDRADA = 124;
	private static final Vehiculo VEHICULO = new Vehiculo(TIPO, PLACA, CILINDRADA);
	private static final Date FECHA_ENTRADA = new Date();
	private static final Date FECHA_SALIDA = new Date();
	private static final String DURACION = "0d";
	private static final Integer VALOR = 0;
	
	private Vehiculo vehiculo;
	private Date fechaEntrada;
	private Date fechaSalida;
	private String duracion;
	private Integer valor;
	
	public ParqueoTestDataBuilder() {
		this.vehiculo = VEHICULO; 
		this.fechaEntrada = FECHA_ENTRADA;
		this.fechaSalida = FECHA_SALIDA;
		this.duracion = DURACION;
		this.valor = VALOR;
	}

	public ParqueoTestDataBuilder conVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
		return this;
	}
	
	public ParqueoTestDataBuilder conFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
		return this;
	}
	
	public ParqueoTestDataBuilder conFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
		return this;
	}
	
	public ParqueoTestDataBuilder conDuracion(String duracion) {
		this.duracion = duracion;
		return this;
	}
	
	public ParqueoTestDataBuilder conValor(Integer valor) {
		this.valor = valor;
		return this;
	}

	public Parqueo build() {
		return new Parqueo(this.vehiculo, this.fechaEntrada, this.fechaSalida, this.duracion, this.valor);
	}
}
