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

import co.com.ceiba.parqueadero.dao.VehiculoDao;
import co.com.ceiba.parqueadero.entidad.VehiculoEntity;

@Controller
@RequestMapping(path="/vehiculos")
@ResponseBody
public class VehiculoController {
	@Autowired
	private VehiculoDao vehiculoDao;
	
	@PostMapping
	public ResponseEntity<VehiculoEntity> create (@RequestBody VehiculoEntity vehiculo) {	
		
		vehiculoDao.save(vehiculo);
		
		return new ResponseEntity<>(vehiculo, HttpStatus.CREATED);
	}
	
	@GetMapping(params= {"id"})
	public ResponseEntity<VehiculoEntity> edit (@RequestParam Long id) {
		
		VehiculoEntity vehiculo = vehiculoDao.findOne(id);
		
		if (vehiculo == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(vehiculo, HttpStatus.OK);
	}
	
	@PutMapping(path="/{id}")
	public ResponseEntity<VehiculoEntity> update (@PathVariable Long id, @RequestBody VehiculoEntity vehiculo) {	
		
		VehiculoEntity vehiculoEntity = vehiculoDao.findOne(id);
		
		if (vehiculoEntity == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		vehiculoEntity.setTipo(vehiculo.getTipo().toUpperCase());
		vehiculoEntity.setPlaca(vehiculo.getPlaca().toUpperCase());
		vehiculoEntity.setCilindrada(vehiculo.getCilindrada());
		vehiculoDao.save(vehiculoEntity);
		
		return new ResponseEntity<>(vehiculo, HttpStatus.OK);
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<VehiculoEntity> delete (@PathVariable Long id) {	
		
		VehiculoEntity vehiculoEntity = vehiculoDao.findOne(id);
		
		if (vehiculoEntity == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		vehiculoDao.delete(vehiculoEntity);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping(params= {"placa"})
	public ResponseEntity<VehiculoEntity> getByPlaca (@RequestParam String placa) {
		
		VehiculoEntity vehiculo = vehiculoDao.findByPlaca(placa.toUpperCase());
		
		if (vehiculo == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(vehiculo, HttpStatus.OK);
	}
	
	@GetMapping(path="/all") 
	public Iterable<VehiculoEntity> getAllVehiculos() {
		return vehiculoDao.findAll();
	}
}
