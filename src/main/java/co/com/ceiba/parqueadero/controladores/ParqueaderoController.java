package co.com.ceiba.parqueadero.controladores;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import co.com.ceiba.parqueadero.dominio.Parqueo;
import co.com.ceiba.parqueadero.dominio.Vehiculo;
import co.com.ceiba.parqueadero.dominio.repositorio.RepositorioParqueo;
import co.com.ceiba.parqueadero.dominio.repositorio.RepositorioVehiculo;
import co.com.ceiba.parqueadero.persistencia.repositorio.RepositorioParqueoPersistencia;
import co.com.ceiba.parqueadero.persistencia.repositorio.RepositorioVehiculoPersistencia;

@Controller
@RequestMapping(path="/parqueadero")
@ResponseBody
public class ParqueaderoController {
	
	@Autowired
	private RepositorioVehiculo repositorioVehiculo;
	
	@Autowired
	private RepositorioParqueo repositorioParqueo;
	
	public ParqueaderoController() {
		this.repositorioVehiculo = new RepositorioVehiculoPersistencia();
		this.repositorioParqueo = new RepositorioParqueoPersistencia();
	}
	
	@PostMapping(path = "/entrada")
	public ResponseEntity<Boolean> entrada (@RequestBody Vehiculo vehiculo) {
		
		// Revisar que el vehiculo sea reportado con el número de placa
		if (vehiculo.getPlaca() == null || vehiculo.getTipo() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}		
		// Revisar si el vehiculo ya se encuentra en el parqueadero
		else if (repositorioParqueo.obtenerVehiculoParqueadoPorPlaca(vehiculo.getPlaca()) != null) {
			return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
		}
		
		Vehiculo vehiculo_a_estacionar = repositorioVehiculo.obtenerPorPlaca(vehiculo.getPlaca());
		
		// Revisar si el vehiculo no está registrado, si no lo está es registrado
		if(vehiculo_a_estacionar == null) {
			vehiculo_a_estacionar = repositorioVehiculo.agregar(vehiculo);
		}
		
		// Se realiza el registro del parqueo
		Parqueo parqueo = new Parqueo(vehiculo_a_estacionar, new Date());
		Boolean parqueado = repositorioParqueo.entrada(parqueo);
		
		return new ResponseEntity<>(parqueado, HttpStatus.CREATED);
	}
	
	@PostMapping(path = "/salida")
	public ResponseEntity<Parqueo> salida (@RequestBody Vehiculo vehiculo) {
		Date fechaSalida = new Date();
		Parqueo parqueo = repositorioParqueo.obtener(vehiculo.getPlaca());
		
		if (parqueo != null) {
			parqueo  = repositorioParqueo.salida(vehiculo.getPlaca(), fechaSalida, "0 min", 0);
			
			return new ResponseEntity<>(parqueo, HttpStatus.OK);
		}
		else{
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(path = "/dipsonibilidad", params = {"tipo"})
	public ResponseEntity<Boolean> disponibilidad (@RequestParam String tipo) {
		
		boolean disponible = repositorioParqueo.diponibilidad(tipo);
		
		return (disponible) ? new ResponseEntity<>(disponible, HttpStatus.OK) :
			new ResponseEntity<>(disponible, HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(path = "/all")
	public ResponseEntity<List<Parqueo>> getAllParqueos() {
		return new ResponseEntity<>(repositorioParqueo.obtenerParqueos(), HttpStatus.OK);
	}
}