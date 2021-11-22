package logica;

public class Medico extends Usuario {
	
	private String codigo;
	private String especialidad;
	
	public Medico(String codigo, String login, String id, String password, String nombre, String telefono) {
		super(codigo, login, id, password, nombre, telefono);
		
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
