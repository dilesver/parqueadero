package co.com.ceiba.parqueadero.persistencia.builder;

import co.com.ceiba.parqueadero.dominio.Vehiculo;
import co.com.ceiba.parqueadero.persistencia.entidad.VehiculoEntity;

public class VehiculoBuilder {
	private VehiculoBuilder() {}
	
	public static VehiculoEntity convertirAEntity(Vehiculo vehiculo) {
		VehiculoEntity entity = new VehiculoEntity();
		entity.setTipo(vehiculo.getTipo());
		entity.setPlaca(vehiculo.getPlaca());
		entity.setCilindrada(vehiculo.getCilindrada());
		
		return entity;
	}
	
	public static Vehiculo convertirADominio(VehiculoEntity entity) {
		Vehiculo vehiculo = null;
		
		if(entity != null) {
			vehiculo = new Vehiculo(entity.getTipo(), entity.getPlaca(), entity.getCilindrada()); 
		}
		
		return vehiculo;
	}
}
