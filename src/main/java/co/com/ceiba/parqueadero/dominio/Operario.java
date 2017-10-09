package co.com.ceiba.parqueadero.dominio;

import java.util.Date;
import java.util.List;

import co.com.ceiba.parqueadero.dominio.repositorio.RepositorioParqueo;
import co.com.ceiba.parqueadero.dominio.repositorio.RepositorioVehiculo;

public class Operario {
	
	private RepositorioVehiculo repositorioVehiculo;
	private RepositorioParqueo repositorioParqueo;
	
	public Operario() {
		
	}
	
	public Operario(RepositorioVehiculo repositorioVehiculo, RepositorioParqueo repositorioParqueo) {
		this.repositorioVehiculo = repositorioVehiculo;
		this.repositorioParqueo = repositorioParqueo;
	}
	
	public boolean entradaVehiculoParqueadero(Vehiculo vehiculo, Date fechaEntrada) {
		Vehiculo vehiculo_a_estacionar = null;
		
		if(!vehiculoRegistrado(vehiculo)) {
			vehiculo_a_estacionar = registrarVehiculo(vehiculo);
		}
		
		if (vehiculo_a_estacionar != null && repositorioParqueo.obtenerVehiculoParqueadoPorPlaca(vehiculo_a_estacionar.getPlaca()) == null) {
			Parqueo parqueo = new Parqueo(vehiculo_a_estacionar, fechaEntrada);
			
			return repositorioParqueo.entrada(parqueo);
		}
		else {
			return false;
		}		
	}
	
	public Parqueo salidaVehiculoParqueadero(Vehiculo vehiculo, Date fechaSalida) {
		String duracion = "0m";
		Integer valor = 0;
		
		return repositorioParqueo.salida(vehiculo.getPlaca(), fechaSalida, duracion, valor);
	}
	
	public boolean vehiculoRegistrado(Vehiculo vehiculo) {
		return repositorioVehiculo.obtenerPorPlaca(vehiculo.getPlaca()) != null;
	}
	
	public Vehiculo registrarVehiculo(Vehiculo vehiculo) {
		return repositorioVehiculo.agregar(vehiculo);
	}
	
	public boolean vehiculoEnParqueadero(String placa) {
		return repositorioParqueo.obtenerVehiculoParqueadoPorPlaca(placa) != null;
	}
	
	public List<Parqueo> listadoParqueos(){
		return repositorioParqueo.obtenerParqueos();
	}
}
