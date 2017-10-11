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
		
		return (entity != null) ? VehiculoBuilder.convertirADominio(entity.getVehiculo()) : null;
	}

	@Override
	public boolean entrada(Parqueo parqueo) {
		
		entity = dao.save(ParqueoBuilder.convertirAEntity(parqueo)); 
		
		return entity != null;
	}

	@Override
	public Parqueo salida(String placa, Date fechaSalida, String duracion, Integer valor) {
		Parqueo parqueo = obtener(placa);
		
		if (parqueo != null) {
			
			parqueo.setFechaSalida(fechaSalida);
			parqueo.setDuracion(duracion);
			parqueo.setValor(valor);
			
			entity = dao.save(ParqueoBuilder.convertirAEntity(parqueo));
			
			return (entity != null) ? ParqueoBuilder.convertirADominio(entity) : null;
		}
		else {
			return null;
		}
	}

	@Override
	public Parqueo obtener(String placa) {
		
		entity = dao.findByVehiculoPlacaAndFechaSalidaIsNull(placa);
		
		return (entity != null) ? ParqueoBuilder.convertirADominio(entity) : null;
	}

	@Override
	public boolean diponibilidad(String tipo) {
		int count = dao.countByVehiculoTipoAndFechaSalidaIsNull(tipo.toUpperCase());
		
		switch (tipo.toUpperCase()) {
			case "CARRO":
				return count < Constantes.NUMERO_MAXIMO_CARROS;
			case "MOTO":
				return count < Constantes.NUMERO_MAXIMO_MOTOS;
			default:
				return false;
		}
	}
	
	@Override
	public String cuposDiponibles(String tipo) {
		int count = dao.countByVehiculoTipoAndFechaSalidaIsNull(tipo.toUpperCase());
		
		switch (tipo.toUpperCase()) {
			case "CARRO":
				return String.valueOf((Constantes.NUMERO_MAXIMO_CARROS - count) + " / " + Constantes.NUMERO_MAXIMO_CARROS);
			case "MOTO":
				return String.valueOf((Constantes.NUMERO_MAXIMO_MOTOS - count) + " / " + Constantes.NUMERO_MAXIMO_MOTOS);
			default:
				return "";
		}
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
