package co.com.ceiba.parqueadero.dominio.repositorio;

import java.util.List;

import co.com.ceiba.parqueadero.dominio.Vehiculo;

public interface RepositorioVehiculo {
	
	/**
	 * Permite obtener un vehiculo por placa 
	 * @param placa
	 * @return
	 */
	Vehiculo obtenerPorPlaca(String placa);
	
	/**
	 * Permite obtener un vehiculo por Id
	 * @param id
	 * @return
	 */
	Vehiculo obtenerPorId(Long id);
	
	/**
	 * Permite agregar un vehiculo al repositorio
	 * @param vehiculo
	 * @return
	 */
	Vehiculo agregar(Vehiculo vehiculo);
	
	/**
	 * Permite eliminar vehiculo por id
	 * @param id
	 * @return
	 */
	boolean eliminar(Long id);
	
	/**
	 * Obtener el listado completo de vehiculos
	 * @return
	 */
	List<Vehiculo> obtenerVehiculos();
}
