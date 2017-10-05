package co.com.ceiba.parqueadero.dominio;

import java.util.Date;

import co.com.ceiba.parqueadero.dominio.repositorio.RepositorioParqueo;
import co.com.ceiba.parqueadero.dominio.repositorio.RepositorioVehiculo;

public class Operario {
	private RepositorioVehiculo repositorioVehiculo;
	private RepositorioParqueo repositorioParqueo;
	
	public Operario(RepositorioVehiculo repositorioVehiculo, RepositorioParqueo repositorioParqueo) {
		this.repositorioVehiculo = repositorioVehiculo;
		this.repositorioParqueo = repositorioParqueo;
	}
	
	public boolean entradaVehiculoParqueadero(Vehiculo vehiculo, Date fechaEntrada) {
		
		if (vehiculoRegistrado(vehiculo) && repositorioParqueo.obtenerVehiculoParqueadoPorPlaca(vehiculo.getPlaca()) != null) {
			Parqueo parqueo = new Parqueo(vehiculo, fechaEntrada);
			
			return repositorioParqueo.entrada(parqueo);
		}
		
		return false;
	}
	
	public boolean vehiculoRegistrado(Vehiculo vehiculo) {
		return (repositorioVehiculo.obtenerPorPlaca(vehiculo.getPlaca()) != null);
	}
}
