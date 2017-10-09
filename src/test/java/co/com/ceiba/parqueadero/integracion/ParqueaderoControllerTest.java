package co.com.ceiba.parqueadero.integracion;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import co.com.ceiba.parqueadero.ParqueaderoBackendApplication;
import co.com.ceiba.parqueadero.dominio.Parqueo;
import co.com.ceiba.parqueadero.dominio.Vehiculo;
import co.com.ceiba.parqueadero.persistencia.builder.ParqueoBuilder;
import co.com.ceiba.parqueadero.persistencia.builder.VehiculoBuilder;
import co.com.ceiba.parqueadero.persistencia.dao.ParqueoDao;
import co.com.ceiba.parqueadero.persistencia.dao.VehiculoDao;
import co.com.ceiba.parqueadero.persistencia.entidad.ParqueoEntity;
import co.com.ceiba.parqueadero.persistencia.entidad.VehiculoEntity;
import co.com.ceiba.parqueadero.testdatabuilder.ParqueoTestDataBuilder;
import co.com.ceiba.parqueadero.testdatabuilder.VehiculoTestDataBuilder;
import co.com.ceiba.parqueadero.utilidades.JsonHelper;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = ParqueaderoBackendApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ParqueaderoControllerTest {
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private VehiculoDao vehiculoDao;
	
	@Autowired
	private ParqueoDao parqueoDao;
	
	@Test
	public void prquearVehiculo() throws IOException, Exception {
	    // Arrange
	    Vehiculo vehiculo = new VehiculoTestDataBuilder().build();
	
	    // Act
	    mvc.perform(post("/parqueadero/entrada").contentType(MediaType.APPLICATION_JSON).
	    		content(JsonHelper.toJson(vehiculo))).andExpect(status().isCreated());
	    
	    ParqueoEntity parqueoCreado = parqueoDao.findByVehiculoPlacaAndFechaSalidaIsNull(vehiculo.getPlaca());
	    parqueoDao.delete(parqueoCreado);
	    
	    VehiculoEntity vehicuoCreado = vehiculoDao.findByPlaca(vehiculo.getPlaca());
	    vehiculoDao.delete(vehicuoCreado);
	    
	    // Assert
	    Assert.assertNotNull(parqueoCreado);
	}
	
	@Test
	public void salidaVehiculo() throws IOException, Exception {
	    // Arrange
	    Vehiculo vehiculo = new VehiculoTestDataBuilder().conPlaca("jke23c").build();
	    VehiculoEntity vehiculoCreado = vehiculoDao.save(VehiculoBuilder.convertirAEntity(vehiculo));
	
	    // Act
	    /*mvc.perform(post("/parqueadero/salida").contentType(MediaType.APPLICATION_JSON).
	    		content(JsonHelper.toJson(vehiculo))).andExpect(status().isOk());*/
	    vehiculoDao.delete(vehiculoCreado);
	
	    // Assert
	}
	
	@Test
	public void listadoParqueos() throws IOException, Exception {
	    // Arrange
	    
	    // Act
	    mvc.perform(get("/parqueadero/all")).andExpect(status().isOk());
	    
	    // Assert
	}
}
