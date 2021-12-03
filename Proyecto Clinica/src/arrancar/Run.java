package arrancar;

import java.awt.EventQueue;

import logica.Clinica;
import visual.Login;

public abstract class Run {

// Arrancar el programa
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Clinica.getInstance().cargarClinica();
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
