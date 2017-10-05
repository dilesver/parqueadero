package co.com.ceiba.parqueadero.dominio.repositorio;

import co.com.ceiba.parqueadero.dominio.Parqueo;
import co.com.ceiba.parqueadero.dominio.Vehiculo;

public interface RepositorioParqueo {
	
	/**
	 * Permite obtener el vehiculo parqueado por placa 
	 * @param placa
	 * @return
	 */
	Vehiculo obtenerVehiculoParqueadoPorPlaca(String placa);
	
	/**
	 * Permite agregar un parqueo al repositorio
	 * @param parqueo
	 * @return
	 */
	boolean entrada(Parqueo parqueo);
	
	/**
	 * Permite la salida de un parqueo en el repositorio
	 * @param parqueo
	 * @return
	 */
	boolean salida(Parqueo parqueo);
	
	/**
	 * Permite obtener los datos del parqueo por medio de la placa 
	 * @param placa
	 * @return
	 */
	Parqueo obtener(String placa);
}
