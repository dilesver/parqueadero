package co.com.ceiba.parqueadero.dao;

import org.springframework.data.repository.CrudRepository;
import co.com.ceiba.parqueadero.entidad.VehiculoEntity;

public interface VehiculoDao extends CrudRepository<VehiculoEntity, Long> {
	
	public VehiculoEntity findByPlaca(String placa);
}
