package co.com.ceiba.parqueadero.dao;

import org.springframework.data.repository.CrudRepository;

import co.com.ceiba.parqueadero.entidad.ParqueoEntity;

public interface ParqueoDao extends CrudRepository<ParqueoEntity, Long> {
	
	public ParqueoEntity findByVehiculoPlacaAndFechaSalidaIsNull(String placa);
}
