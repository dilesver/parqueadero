package co.com.ceiba.parqueadero.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import co.com.ceiba.parqueadero.dominio.Vehiculo;
import co.com.ceiba.parqueadero.dominio.repositorio.RepositorioVehiculo;
import co.com.ceiba.parqueadero.persistencia.repositorio.RepositorioVehiculoPersistencia;

@Controller
@RequestMapping(path="/vehiculos")
@ResponseBody
public class VehiculoController {
	
	@Autowired
	private RepositorioVehiculo repositorioVehiculo;
	
	public VehiculoController() {
		this.repositorioVehiculo = new RepositorioVehiculoPersistencia();
	}
	
	@PostMapping
	public ResponseEntity<Vehiculo> create (@RequestBody Vehiculo vehiculo) {
		
		repositorioVehiculo.agregar(vehiculo);
		
		return new ResponseEntity<>(vehiculo, HttpStatus.CREATED);
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Vehiculo> recovery (@PathVariable Long id) {
		
		Vehiculo vehiculo = repositorioVehiculo.obtenerPorId(id);
		
		if (vehiculo == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(vehiculo, HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Vehiculo> delete (@PathVariable Long id) {	
		
		repositorioVehiculo.eliminar(id);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping(path = "/all") 
	public ResponseEntity<List<Vehiculo>> getAllVehiculos() {
		return new ResponseEntity<>(repositorioVehiculo.obtenerVehiculos(), HttpStatus.OK);
	}
}
