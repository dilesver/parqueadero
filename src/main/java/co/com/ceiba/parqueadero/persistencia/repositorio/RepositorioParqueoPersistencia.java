package co.com.ceiba.parqueadero.persistencia.repositorio;

import org.springframework.beans.factory.annotation.Autowired;

import co.com.ceiba.parqueadero.dominio.Parqueo;
import co.com.ceiba.parqueadero.dominio.Vehiculo;
import co.com.ceiba.parqueadero.dominio.repositorio.RepositorioParqueo;
import co.com.ceiba.parqueadero.persistencia.builder.ParqueoBuilder;
import co.com.ceiba.parqueadero.persistencia.builder.VehiculoBuilder;
import co.com.ceiba.parqueadero.persistencia.dao.ParqueoDao;
import co.com.ceiba.parqueadero.persistencia.entidad.ParqueoEntity;

public class RepositorioParqueoPersistencia implements RepositorioParqueo {
	@Autowired
	private ParqueoDao dao;
	
	private ParqueoEntity entity;
	
	@Override
	public Vehiculo obtenerVehiculoParqueadoPorPlaca(String placa) {
		entity = dao.findByVehiculoPlacaAndFechaSalidaIsNull(placa);
		
		return VehiculoBuilder.convertirADominio(entity.getVehiculo());
	}

	@Override
	public boolean entrada(Parqueo parqueo) {
		
		entity = dao.save(ParqueoBuilder.convertirAEntity(parqueo)); 
		
		return entity.getId() != null;
	}

	@Override
	public boolean salida(Parqueo parqueo) {
		
		entity = dao.save(ParqueoBuilder.convertirAEntity(parqueo));
		
		return entity.getValor() != null;
	}

	@Override
	public Parqueo obtener(String placa) {
		
		entity = dao.findByVehiculoPlacaAndFechaSalidaIsNull(placa);
		
		return ParqueoBuilder.convertirADominio(entity);
	}

}
