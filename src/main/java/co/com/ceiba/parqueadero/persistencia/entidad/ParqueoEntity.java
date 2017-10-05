package co.com.ceiba.parqueadero.persistencia.entidad;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;


@Entity
@Table(name = "parqueos")
public class ParqueoEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	private VehiculoEntity vehiculo;
	
	@Column(nullable = false)
	@CreationTimestamp
	private Date fechaEntrada;
	
	@Column(nullable = true)
	private Date fechaSalida;
	
	@Column(nullable = true)
	private String duracion;
	
	@Column(nullable = true)
	private Integer valor;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public VehiculoEntity getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(VehiculoEntity vehiculo) {
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
