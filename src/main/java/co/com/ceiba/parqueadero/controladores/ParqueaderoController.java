package co.com.ceiba.parqueadero.controladores;

import java.util.Date;

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
import co.com.ceiba.parqueadero.persistencia.builder.ParqueoBuilder;
import co.com.ceiba.parqueadero.persistencia.builder.VehiculoBuilder;
import co.com.ceiba.parqueadero.persistencia.dao.ParqueoDao;
import co.com.ceiba.parqueadero.persistencia.dao.VehiculoDao;
import co.com.ceiba.parqueadero.persistencia.entidad.ParqueoEntity;
import co.com.ceiba.parqueadero.persistencia.entidad.VehiculoEntity;

@Controller
@RequestMapping(path="/parqueadero")
@ResponseBody
public class ParqueaderoController {
	@Autowired
	private ParqueoDao parqueoDao;
	
	@Autowired
	private VehiculoDao vehiculoDao;
	
	@PostMapping(path = "/entrada")
	public ResponseEntity<Parqueo> entrada (@RequestBody Parqueo parqueo) {
		
		Vehiculo vehiculo = parqueo.getVehiculo();
		
		// Revisar que el vehiculo sea reportado con el número de placa
		if (vehiculo.getPlaca() == null || vehiculo.getTipo() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		// Revisar si el vehiculo ya se encuentra en el parqueadero
		else if (parqueoDao.findByVehiculoPlacaAndFechaSalidaIsNull(vehiculo.getPlaca()) != null) {
			return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
		}
		
		// Revisar si el vehiculo no está registrado, si no lo está es registrado
		VehiculoEntity vehiculoEntity = vehiculoDao.findByPlaca(vehiculo.getPlaca());		
		if(vehiculoEntity == null) {
			vehiculoEntity = VehiculoBuilder.convertirAEntity(vehiculo);
			vehiculoDao.save(vehiculoEntity);
		}
		
		// Se realiza el registro del parqueo
		ParqueoEntity parqueoEntity = ParqueoBuilder.convertirAEntity(parqueo);
		parqueoEntity.setVehiculo(vehiculoEntity);
		parqueoDao.save(parqueoEntity);
		
		return new ResponseEntity<>(ParqueoBuilder.convertirADominio(parqueoEntity), HttpStatus.OK);
	}
	
	@PostMapping(path = "/salida")
	public ResponseEntity<ParqueoEntity> salida (@RequestBody Vehiculo vehiculo) {
		
		ParqueoEntity parqueo = parqueoDao.findByVehiculoPlacaAndFechaSalidaIsNull(vehiculo.getPlaca());
		
		if (parqueo == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		else {
			 parqueo.setFechaSalida(new Date());
			 parqueoDao.save(parqueo);
		}
		
		return new ResponseEntity<>(parqueo, HttpStatus.OK);
	}
	
	@GetMapping(path = "/dipsonibilidad", params = {"tipo"})
	public ResponseEntity<Integer> disponibilidad (@RequestParam String tipo) {
		
		int count = parqueoDao.countByVehiculoTipoAndFechaSalidaIsNull(tipo.toUpperCase());
		
		/*if ((tipo.toUpperCase() == "CARRO" && count >= 20) || (tipo.toUpperCase() == "MOTO" && count >= 10)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}*/
		
		return new ResponseEntity<>(count, HttpStatus.OK);
	}
	
	@GetMapping(params = {"placa"})
	public ResponseEntity<ParqueoEntity> getByPlaca (@RequestParam String placa) {
		
		ParqueoEntity parqueo = parqueoDao.findByVehiculoPlacaAndFechaSalidaIsNull(placa.toUpperCase());
		
		if (parqueo == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(parqueo, HttpStatus.OK);
	}
	
	@GetMapping(path = "/all") 
	public Iterable<ParqueoEntity> getAllParqueos() {
		return parqueoDao.findAll();
	}
}
