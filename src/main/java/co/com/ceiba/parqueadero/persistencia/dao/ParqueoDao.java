package co.com.ceiba.parqueadero.persistencia.dao;

import org.springframework.data.repository.CrudRepository;

import co.com.ceiba.parqueadero.persistencia.entidad.ParqueoEntity;

public interface ParqueoDao extends CrudRepository<ParqueoEntity, Long> {
	
	public ParqueoEntity findByVehiculoPlacaAndFechaSalidaIsNull(String placa);
	public int countByVehiculoTipoAndFechaSalidaIsNull(String tipo);
}
