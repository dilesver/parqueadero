package co.com.ceiba.parqueadero.integracion;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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
import co.com.ceiba.parqueadero.dominio.Vehiculo;
import co.com.ceiba.parqueadero.persistencia.builder.VehiculoBuilder;
import co.com.ceiba.parqueadero.persistencia.dao.VehiculoDao;
import co.com.ceiba.parqueadero.persistencia.entidad.VehiculoEntity;
import co.com.ceiba.parqueadero.testdatabuilder.VehiculoTestDataBuilder;
import co.com.ceiba.parqueadero.utilidades.JsonHelper;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = ParqueaderoBackendApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class VehiculoControllerTest {
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private VehiculoDao dao;
	
	@Test
	public void crearVehiculo() throws IOException, Exception {
	    // Arrange
	    Vehiculo vehiculo = new VehiculoTestDataBuilder().build();
	
	    // Act
	    mvc.perform(post("/vehiculos").contentType(MediaType.APPLICATION_JSON).
	    		content(JsonHelper.toJson(vehiculo))).andExpect(status().isCreated());

	    VehiculoEntity vehiculoCreado = dao.findByPlaca(vehiculo.getPlaca());
	    dao.delete(vehiculoCreado);
	    
	    // Assert
	    Assert.assertNotNull(vehiculoCreado);
	}
	
	@Test
	public void recuperarVehiculo() throws IOException, Exception {
	    // Arrange
	    Vehiculo vehiculo = new VehiculoTestDataBuilder().conPlaca("jkm547").build();
	    VehiculoEntity vehiculoCreado = dao.save(VehiculoBuilder.convertirAEntity(vehiculo));
	
	    // Act
	    mvc.perform(get("/vehiculos/" + vehiculoCreado.getId())).andExpect(status().isOk());
	    dao.delete(vehiculoCreado);
	
	    // Assert
	}
	
	@Test
	public void recuperarVehiculoInExistente() throws IOException, Exception {
	    // Arrange
	
	    // Act
	    mvc.perform(get("/vehiculos/0")).andExpect(status().isNotFound());
	
	    // Assert
	}
	
	@Test
	public void eliminarVehiculo() throws IOException, Exception {
	    // Arrange
	    Vehiculo vehiculo = new VehiculoTestDataBuilder().conPlaca("dzl122").build();
	    VehiculoEntity vehiculoCreado = dao.save(VehiculoBuilder.convertirAEntity(vehiculo));
	
	    // Act
	    mvc.perform(delete("/vehiculos/" + vehiculoCreado.getId())).andExpect(status().isNoContent());
	    dao.delete(vehiculoCreado);
	    
	    // Assert
	}
	
	@Test
	public void listadoVehiculos() throws IOException, Exception {
	    // Arrange
	    
	    // Act
	    mvc.perform(get("/vehiculos/all")).andExpect(status().isOk());
	    
	    // Assert
	}
}
