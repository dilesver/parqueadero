package co.com.ceiba.parqueadero.persistencia.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.com.ceiba.parqueadero.persistencia.entidad.ParqueoEntity;

@Repository
public interface ParqueoDao extends CrudRepository<ParqueoEntity, Long> {
	
	public ParqueoEntity findByVehiculoPlacaAndFechaSalidaIsNull(String placa);
	public int countByVehiculoTipoAndFechaSalidaIsNull(String tipo);
}
