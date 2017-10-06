package co.com.ceiba.parqueadero.persistencia.builder;

import co.com.ceiba.parqueadero.dominio.Parqueo;
import co.com.ceiba.parqueadero.persistencia.entidad.ParqueoEntity;

public class ParqueoBuilder {
	private ParqueoBuilder() {}
	
	public static ParqueoEntity convertirAEntity(Parqueo parqueo) {
		ParqueoEntity entity = new ParqueoEntity();
		entity.setId(parqueo.getId());
		entity.setVehiculo(VehiculoBuilder.convertirAEntity(parqueo.getVehiculo()));
		entity.setFechaEntrada(parqueo.getFechaEntrada());
		entity.setFechaSalida(parqueo.getFechaSalida());
		entity.setDuracion(parqueo.getDuracion());
		entity.setValor(parqueo.getValor());
		
		return entity;
	}
	
	public static Parqueo convertirADominio(ParqueoEntity entity) {
		Parqueo parqueo = null;
		
		if(entity != null) {
			parqueo = new Parqueo(entity.getId(), VehiculoBuilder.convertirADominio(entity.getVehiculo()), entity.getFechaEntrada(), entity.getFechaSalida(), entity.getDuracion(), entity.getValor()); 
		}
		
		return parqueo;
	}
}
