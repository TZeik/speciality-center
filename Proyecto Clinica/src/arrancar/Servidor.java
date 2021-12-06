package arrancar;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;

import logica.Clinica;
import logica.Usuario;

public class Servidor extends Thread {
	
	public static Vector usuarios = new Vector();
	
	public static void main (String args[]) {
		
		Clinica.getInstance().cargarClinica();

		ServerSocket sfd = null;
		
		try {
			sfd = new ServerSocket(3000);
			
		}catch(IOException ioe) {
			JOptionPane.showMessageDialog(null, "Comunicación rechazada "+ioe, "Error de conexión", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
		
		while(true) {
			
			try {
				Socket nsfd = sfd.accept();
		        System.out.println("Conexion aceptada de: "+nsfd.getInetAddress());
			    Flow flujo = new Flow(nsfd);
			    Thread t = new Thread(flujo);
		        t.start();
		        
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Se ha producido un error de conexión", "Error de conexión", JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}

}
