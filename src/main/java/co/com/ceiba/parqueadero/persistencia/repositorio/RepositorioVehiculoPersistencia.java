package co.com.ceiba.parqueadero.persistencia.repositorio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.parqueadero.dominio.Vehiculo;
import co.com.ceiba.parqueadero.dominio.repositorio.RepositorioVehiculo;
import co.com.ceiba.parqueadero.persistencia.builder.VehiculoBuilder;
import co.com.ceiba.parqueadero.persistencia.dao.VehiculoDao;
import co.com.ceiba.parqueadero.persistencia.entidad.VehiculoEntity;

@Service
public class RepositorioVehiculoPersistencia implements RepositorioVehiculo {
	@Autowired
	private VehiculoDao dao;
	
	private VehiculoEntity entity;
	
	@Override
	public Vehiculo obtenerPorPlaca(String placa) {
		entity = dao.findByPlaca(placa.toUpperCase());
		
		return VehiculoBuilder.convertirADominio(entity);
	}

	@Override
	public boolean agregar(Vehiculo vehiculo) {
		entity = dao.save(VehiculoBuilder.convertirAEntity(vehiculo));
		
		return entity.getId() != null;
	}

	@Override
	public boolean eliminar(Long id) {
		entity = dao.findOne(id);
		dao.delete(entity);
		
		return false;
	}

	@Override
	public Vehiculo obtenerPorId(Long id) {
		return VehiculoBuilder.convertirADominio(dao.findOne(id));
	}

	@Override
	public List<Vehiculo> obtenerVehiculos() {
		List<Vehiculo> vehiculos = new ArrayList<>();
		
		Iterable<VehiculoEntity> listEntities = dao.findAll();
		
		for(VehiculoEntity vehiculoEntity : listEntities) {
			vehiculos.add(VehiculoBuilder.convertirADominio(vehiculoEntity));
		}
		
		return vehiculos;
	}
}
