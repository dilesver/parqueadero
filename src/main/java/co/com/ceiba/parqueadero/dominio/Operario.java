package co.com.ceiba.parqueadero.dominio;

public class Operario {
	private String documento;
	private String nombre;
	private String usuario;
	private String clave;
	
	public Operario(String documento, String nombre, String usuario, String clave) {
		this.documento = documento;
		this.nombre = nombre;
		this.usuario = usuario;
		this.clave = clave;
	}
	
	public String getDocumento() {
		return documento;
	}
	
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getUsuario() {
		return usuario;
	}
	
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public String getClave() {
		return clave;
	}
	
	public void setClave(String clave) {
		this.clave = clave;
	}
}
