package logica;

public class Administrador extends Usuario {
	
	private String codigo;
	private String puesto;
	
	public Administrador(String codigo, String id, String password, String nombre, String telefono) {
		super(codigo, id, password, nombre, telefono);
		
		this.codigo = codigo;
		this.puesto = "";
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

}
