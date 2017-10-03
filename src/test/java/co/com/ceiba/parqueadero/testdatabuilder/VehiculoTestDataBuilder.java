package co.com.ceiba.parqueadero.testdatabuilder;

import co.com.ceiba.parqueadero.dominio.Vehiculo;

public class VehiculoTestDataBuilder {
	private static final String TIPO = "MOTO";
	private static final String PLACA = "PON12C";
	private static final Integer CILINDRADA = 124;
	
	private String tipo;
	private String placa;
	private Integer cilindrada;
	
	public VehiculoTestDataBuilder() {
		this.tipo = TIPO;
		this.placa = PLACA;
		this.cilindrada = CILINDRADA;
	}

	public VehiculoTestDataBuilder conTipo(String tipo) {
		this.tipo = tipo;
		return this;
	}
	
	public VehiculoTestDataBuilder conPlaca(String placa) {
		this.placa = placa;
		return this;
	}
	
	public VehiculoTestDataBuilder conCilindrada(Integer cilindrada) {
		this.cilindrada = cilindrada;
		return this;
	}

	public Vehiculo build() {
		return new Vehiculo(this.tipo, this.placa, this.cilindrada);
	}
}
