package co.com.ceiba.parqueadero.dominio;

import java.util.Date;

public class Parqueo {
	private Vehiculo vehiculo;
	private Date fechaEntrada;
	private Date fechaSalida;
	private String duracion;
	private Integer valor;
	
	public Parqueo() {}
	
	public Parqueo(Vehiculo vehiculo, Date fechaEntrada) {
		this.vehiculo = vehiculo;
		this.fechaEntrada = fechaEntrada;
	}
	
	public Parqueo(Vehiculo vehiculo, Date fechaEntrada, Date fechaSalida, String duracion, Integer valor) {
		this.vehiculo = vehiculo;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.duracion = duracion;
		this.valor = valor;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public Date getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public String getDuracion() {
		return duracion;
	}

	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}	
}
