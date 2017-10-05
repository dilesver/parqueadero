package co.com.ceiba.parqueadero.persistencia.dao;

import org.springframework.data.repository.CrudRepository;

import co.com.ceiba.parqueadero.persistencia.entidad.VehiculoEntity;

public interface VehiculoDao extends CrudRepository<VehiculoEntity, Long> {
	
	public VehiculoEntity findByPlaca(String placa);
}
