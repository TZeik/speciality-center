package logica;

public class Medico extends Usuario {
	
	private String codigo;
	private String especialidad;
	
	public Medico(String codigo, String id, String password, String nombre, String telefono) {
		super(codigo, id, password, nombre, telefono);
		
		this.codigo = codigo;
		this.especialidad = "";
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

}
