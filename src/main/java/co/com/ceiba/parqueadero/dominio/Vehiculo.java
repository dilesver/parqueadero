package co.com.ceiba.parqueadero.dominio;

public class Vehiculo {
	private String tipo;
	private String placa;
	private int cilindrada;
	
	public Vehiculo(String tipo, String placa, int cilindrada) {
		this.tipo = tipo;
		this.placa = placa;
		this.cilindrada = cilindrada;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public int getCilindrada() {
		return cilindrada;
	}

	public void setCilindrada(int cilindrada) {
		this.cilindrada = cilindrada;
	}
}
