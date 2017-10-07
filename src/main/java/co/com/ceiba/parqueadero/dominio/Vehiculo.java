package co.com.ceiba.parqueadero.dominio;

public class Vehiculo {
	private Long id;
	private String tipo;
	private String placa;
	private Integer cilindraje;
	
	public Vehiculo() {}
	
	public Vehiculo(String tipo, String placa, Integer cilindrada) {
		this.tipo = tipo.toUpperCase();
		this.placa = placa.toUpperCase();
		this.cilindraje = cilindrada;
	}
	
	public Vehiculo(Long id, String tipo, String placa, Integer cilindrada) {
		this.id = id;
		this.tipo = tipo.toUpperCase();
		this.placa = placa.toUpperCase();
		this.cilindraje = cilindrada;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Integer getCilindraje() {
		return cilindraje;
	}

	public void setCilindraje(Integer cilindraje) {
		this.cilindraje = cilindraje;
	}
}
