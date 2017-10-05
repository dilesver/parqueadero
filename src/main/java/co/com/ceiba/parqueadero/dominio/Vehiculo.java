package co.com.ceiba.parqueadero.dominio;

public class Vehiculo {
	private String tipo;
	private String placa;
	private Integer cilindrada;
	
	public Vehiculo() {}
	
	public Vehiculo(String tipo, String placa, Integer cilindrada) {
		this.tipo = tipo.toUpperCase();
		this.placa = placa.toUpperCase();
		this.cilindrada = cilindrada;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo.toUpperCase();
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa.toUpperCase();
	}

	public Integer getCilindrada() {
		return cilindrada;
	}

	public void setCilindrada(Integer cilindrada) {
		this.cilindrada = cilindrada;
	}
}
