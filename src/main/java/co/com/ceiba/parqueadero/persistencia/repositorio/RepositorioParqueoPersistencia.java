package co.com.ceiba.parqueadero.persistencia.repositorio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.parqueadero.dominio.Constantes;
import co.com.ceiba.parqueadero.dominio.Parqueo;
import co.com.ceiba.parqueadero.dominio.Vehiculo;
import co.com.ceiba.parqueadero.dominio.repositorio.RepositorioParqueo;
import co.com.ceiba.parqueadero.persistencia.builder.ParqueoBuilder;
import co.com.ceiba.parqueadero.persistencia.builder.VehiculoBuilder;
import co.com.ceiba.parqueadero.persistencia.dao.ParqueoDao;
import co.com.ceiba.parqueadero.persistencia.entidad.ParqueoEntity;

@Service
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
	public Parqueo salida(String placa, Date fechaSalida, String duracion, Integer valor) {
		Parqueo parqueo = obtener(placa);
		
		if (parqueo.getVehiculo() != null) {
			
			parqueo.setFechaSalida(fechaSalida);
			parqueo.setDuracion(duracion);
			parqueo.setValor(valor);
			
			entity = dao.save(ParqueoBuilder.convertirAEntity(parqueo));
			
			return ParqueoBuilder.convertirADominio(entity);
		}
		else {
			return null;
		}
	}

	@Override
	public Parqueo obtener(String placa) {
		
		entity = dao.findByVehiculoPlacaAndFechaSalidaIsNull(placa);
		
		return ParqueoBuilder.convertirADominio(entity);
	}

	@Override
	public boolean diponibilidad(String tipo) {
		int count = dao.countByVehiculoTipoAndFechaSalidaIsNull(tipo.toUpperCase());
		
		return (tipo.toUpperCase() == "CARRO" && count < Constantes.NUMERO_MAXIMO_CARROS) ||
			   (tipo.toUpperCase() == "MOTO" && count < Constantes.NUMERO_MAXIMO_MOTOS);
	}

	@Override
	public List<Parqueo> obtenerParqueos() {
		List<Parqueo> parqueos = new ArrayList<>();
		
		Iterable<ParqueoEntity> listEntities = dao.findAll();
		
		for(ParqueoEntity parqueoEntity : listEntities) {
			parqueos.add(ParqueoBuilder.convertirADominio(parqueoEntity));
		}
		
		return parqueos;
	}
}
