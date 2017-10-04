package co.com.ceiba.parqueadero.controladores;

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

import co.com.ceiba.parqueadero.dao.ParqueoDao;
import co.com.ceiba.parqueadero.dao.VehiculoDao;
import co.com.ceiba.parqueadero.dominio.Parqueo;
import co.com.ceiba.parqueadero.dominio.Vehiculo;
import co.com.ceiba.parqueadero.entidad.ParqueoEntity;
import co.com.ceiba.parqueadero.entidad.VehiculoEntity;
import co.com.ceiba.parqueadero.repositorio.ParqueoRespositorio;
import co.com.ceiba.parqueadero.repositorio.VehiculoRepositorio;

@Controller
@RequestMapping(path="/parqueos")
@ResponseBody
public class ParqueoController {
	@Autowired
	private ParqueoDao parqueoDao;
	
	@Autowired
	private VehiculoDao vehiculoDao;
	
	@PostMapping
	public ResponseEntity<Parqueo> create (@RequestBody Parqueo parqueo) {
		
		Vehiculo vehiculo = parqueo.getVehiculo();
		VehiculoEntity vehiculoEntity = vehiculoDao.findByPlaca(vehiculo.getPlaca());
		
		if(vehiculoEntity == null) {
			vehiculoEntity = VehiculoRepositorio.convertirAEntity(vehiculo);
			vehiculoDao.save(vehiculoEntity);
		}
		
		ParqueoEntity parqueoEntity = ParqueoRespositorio.convertirAEntity(parqueo);
		parqueoEntity.setVehiculo(vehiculoEntity);
		parqueoDao.save(parqueoEntity);
		
		return new ResponseEntity<>(ParqueoRespositorio.convertirADominio(parqueoEntity), HttpStatus.OK);
	}
	
	@GetMapping(params= {"placa"})
	public ResponseEntity<ParqueoEntity> getByPlaca (@RequestParam String placa) {
		
		ParqueoEntity parqueo = parqueoDao.findByVehiculoPlacaAndFechaSalidaIsNull(placa.toUpperCase());
		
		if (parqueo == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(parqueo, HttpStatus.OK);
	}
	
	@GetMapping(path="/all") 
	public Iterable<ParqueoEntity> getAllParqueos() {
		return parqueoDao.findAll();
	}
}
