package co.com.ceiba.parqueadero.controladores;

import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import co.com.ceiba.parqueadero.dominio.Operario;
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
	private RepositorioVehiculo repositorioVehiculo;
	private RepositorioParqueo repositorioParqueo;
	private Operario operario;
	
	public ParqueaderoController() {
		this.repositorioVehiculo = new RepositorioVehiculoPersistencia();
		this.repositorioParqueo = new RepositorioParqueoPersistencia();
		this.operario = new Operario(repositorioVehiculo, repositorioParqueo);
	}
	
	@PostMapping(path = "/entrada")
	public ResponseEntity<Boolean> entrada (@RequestBody Vehiculo vehiculo) {
		
		// Revisar que el vehiculo sea reportado con el número de placa
		if (vehiculo.getPlaca() == null || vehiculo.getTipo() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}		
		// Revisar si el vehiculo ya se encuentra en el parqueadero
		else if (operario.vehiculoEnParqueadero(vehiculo.getPlaca())) {
			return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
		}
		
		// Revisar si el vehiculo no está registrado, si no lo está es registrado
		if(!operario.vehiculoRegistrado(vehiculo)) {
			operario.registrarVehiculo(vehiculo);
		}
		
		// Se realiza el registro del parqueo
		Boolean parqueadeo = operario.entradaVehiculoParqueadero(vehiculo, new Date());
		
		return new ResponseEntity<>(parqueadeo, HttpStatus.OK);
	}
	
	@PostMapping(path = "/salida")
	public ResponseEntity<Parqueo> salida (@RequestBody Vehiculo vehiculo) {
		Date fechaSalida = new Date();
		Parqueo parqueo = operario.salidaVehiculoParqueadero(vehiculo, fechaSalida);

		return new ResponseEntity<>(parqueo, HttpStatus.OK);
	}
	
	@GetMapping(path = "/dipsonibilidad", params = {"tipo"})
	public ResponseEntity<Boolean> disponibilidad (@RequestParam String tipo) {
		
		return new ResponseEntity<>(repositorioParqueo.diponibilidad(tipo), HttpStatus.OK);
	}
	
	@GetMapping(path = "/all")
	public ResponseEntity<List<Parqueo>> getAllParqueos() {
		return new ResponseEntity<>(repositorioParqueo.obtenerParqueos(), HttpStatus.OK);
	}
}