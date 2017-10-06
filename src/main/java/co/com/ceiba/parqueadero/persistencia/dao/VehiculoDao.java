package co.com.ceiba.parqueadero.persistencia.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.com.ceiba.parqueadero.persistencia.entidad.VehiculoEntity;

@Repository
public interface VehiculoDao extends CrudRepository<VehiculoEntity, Long> {
	
	public VehiculoEntity findByPlaca(String placa);
}
