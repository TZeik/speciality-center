package arrancar;

import java.awt.EventQueue;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import logica.Clinica;
import visual.Login;

public class Cliente {
	
	static Socket sfd = null;
	static DataInputStream EntradaSocket;
	static DataOutputStream SalidaSocket;
	public static boolean loged = false;

// Arrancar el programa
	
	public static void main(String[] args) {
		
		try {
			sfd = new Socket("127.0.0.1", 3000);
		    EntradaSocket = new DataInputStream(new BufferedInputStream(sfd.getInputStream()));
		    SalidaSocket = new DataOutputStream(new BufferedOutputStream(sfd.getOutputStream()));
		    
		} catch (UnknownHostException e1) {
			
			e1.printStackTrace();
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		
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

	public static boolean isLoged() {
		return loged;
	}

	public static void setLoged(boolean loged) {
		Cliente.loged = loged;
	}


}
