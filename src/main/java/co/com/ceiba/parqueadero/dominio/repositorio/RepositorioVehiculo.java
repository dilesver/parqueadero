package co.com.ceiba.parqueadero.dominio.repositorio;

import co.com.ceiba.parqueadero.dominio.Vehiculo;

public interface RepositorioVehiculo {
	
	/**
	 * Permite obtener un vehiculo por placa 
	 * @param placa
	 * @return
	 */
	Vehiculo obtenerPorPlaca(String placa);
	
	/**
	 * Permite agregar un vehiculo al repositorio
	 * @param vehiculo
	 * @return
	 */
	boolean agregar(Vehiculo vehiculo);
}
