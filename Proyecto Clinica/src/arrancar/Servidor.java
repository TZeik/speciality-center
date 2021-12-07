package arrancar;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;

import logica.Clinica;
import logica.Usuario;

public class Servidor extends Thread {
	
	public static ArrayList<Usuario> onlineUsers = new ArrayList<Usuario>();
	
	public static void main(String[] args) {

		ServerSocket sfd = null;
		
		try {
			sfd = new ServerSocket(6000);
			
		}catch(IOException ioe) {
			JOptionPane.showMessageDialog(null, "Comunicación rechazada "+ioe, "Error de conexión", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
		
		while(true) {
			
			try {
				Socket nsfd = sfd.accept();
		        System.out.println("Conexion aceptada de: "+nsfd.getInetAddress());
		        DataInputStream flujoLectura = new DataInputStream(new BufferedInputStream(nsfd.getInputStream()));
		        int unByte;
		        File archivoSalida = new File("C:\\Users\\The Mask Power\\git\\proyecto_clinica\\Proyecto Clinica\\respaldo\\respaldo.dat");
		        FileOutputStream escritor = new FileOutputStream(archivoSalida);
		        
		        while((unByte = flujoLectura.read()) != -1) {
		        	escritor.write(unByte);
		        }
		        escritor.close();
		        
			} catch (IOException e) {
				//JOptionPane.showMessageDialog(null, "Se ha producido un error de conexión", "Error de conexión", JOptionPane.ERROR_MESSAGE);
				System.out.println("Se ha desconectado: ");
			}
		}
		
	}
	
	public void hacerRespaldo() {
		
	}


}
