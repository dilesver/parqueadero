package co.com.ceiba.parqueadero.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import co.com.ceiba.parqueadero.dominio.Vehiculo;
import co.com.ceiba.parqueadero.persistencia.builder.VehiculoBuilder;
import co.com.ceiba.parqueadero.persistencia.dao.VehiculoDao;
import co.com.ceiba.parqueadero.persistencia.entidad.VehiculoEntity;

@Controller
@RequestMapping(path="/vehiculos")
@ResponseBody
public class VehiculoController {
	@Autowired
	private VehiculoDao vehiculoDao;
	
	@PostMapping
	public ResponseEntity<Vehiculo> create (@RequestBody Vehiculo vehiculo) {
		
		VehiculoEntity entity = VehiculoBuilder.convertirAEntity(vehiculo);
		vehiculoDao.save(entity);
		
		return new ResponseEntity<>(VehiculoBuilder.convertirADominio(entity), HttpStatus.CREATED);
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Vehiculo> recovery (@PathVariable Long id) {
		
		Vehiculo vehiculo = VehiculoBuilder.convertirADominio(vehiculoDao.findOne(id));
		
		if (vehiculo == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(vehiculo, HttpStatus.OK);
	}
	
	@PutMapping(path = "/{id}")
	public ResponseEntity<Vehiculo> update (@PathVariable Long id, @RequestBody Vehiculo vehiculo) {	
		
		VehiculoEntity entity = vehiculoDao.findOne(id);
		
		if (entity == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		entity.setTipo(vehiculo.getTipo().toUpperCase());
		entity.setPlaca(vehiculo.getPlaca().toUpperCase());
		entity.setCilindrada(vehiculo.getCilindrada());
		vehiculoDao.save(entity);
		
		return new ResponseEntity<>(VehiculoBuilder.convertirADominio(entity), HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Vehiculo> delete (@PathVariable Long id) {	
		
		VehiculoEntity entity = vehiculoDao.findOne(id);
		
		if (entity == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		vehiculoDao.delete(entity);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping(params = {"placa"})
	public ResponseEntity<Vehiculo> getByPlaca (@RequestParam String placa) {
		
		Vehiculo vehiculo = VehiculoBuilder.convertirADominio(vehiculoDao.findByPlaca(placa.toUpperCase()));
		
		if (vehiculo == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(vehiculo, HttpStatus.OK);
	}
	
	@GetMapping(path = "/all") 
	public Iterable<VehiculoEntity> getAllVehiculos() {
		return vehiculoDao.findAll();
	}
}
