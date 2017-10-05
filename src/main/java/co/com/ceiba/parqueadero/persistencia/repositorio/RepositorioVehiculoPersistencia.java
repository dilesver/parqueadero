package co.com.ceiba.parqueadero.persistencia.repositorio;

import org.springframework.beans.factory.annotation.Autowired;

import co.com.ceiba.parqueadero.dominio.Vehiculo;
import co.com.ceiba.parqueadero.dominio.repositorio.RepositorioVehiculo;
import co.com.ceiba.parqueadero.persistencia.builder.VehiculoBuilder;
import co.com.ceiba.parqueadero.persistencia.dao.VehiculoDao;
import co.com.ceiba.parqueadero.persistencia.entidad.VehiculoEntity;

public class RepositorioVehiculoPersistencia implements RepositorioVehiculo {
	@Autowired
	private VehiculoDao dao;
	
	private VehiculoEntity entity;
	
	@Override
	public Vehiculo obtenerPorPlaca(String placa) {
		entity = dao.findByPlaca(placa);
		
		return VehiculoBuilder.convertirADominio(entity);
	}

	@Override
	public boolean agregar(Vehiculo vehiculo) {
		entity = dao.save(VehiculoBuilder.convertirAEntity(vehiculo));
		
		return entity.getId() != null;
	}
}
