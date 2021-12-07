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

	
	public Cliente() {
		
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

	// Arrancar el programa
	
	public static void main(String[] args) {
		
			Cliente cliente = new Cliente();
			
	}
}
