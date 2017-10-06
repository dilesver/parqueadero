package co.com.ceiba.parqueadero.dominio.repositorio;

import java.util.Date;
import java.util.List;

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
	 * Permite la salida de un vehiculo del repositorio
	 * @param placa
	 * @param fechaSalida
	 * @param duracion
	 * @param valor
	 * @return
	 */
	Parqueo salida(String placa, Date fechaSalida, String duracion, Integer valor);
	
	/**
	 * Permite obtener los datos del parqueo por medio de la placa 
	 * @param placa
	 * @return
	 */
	Parqueo obtener(String placa);
	
	/**
	 * Permite consultar por tipo de vehiculo, si hay disponibilidad para estacionar
	 * @param tipo
	 * @return
	 */
	boolean diponibilidad(String tipo);
	
	/**
	 * Obtener el listado completo de parqueos
	 * @return
	 */
	List<Parqueo> obtenerParqueos();
}
