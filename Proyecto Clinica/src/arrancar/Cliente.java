package arrancar;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import logica.Clinica;
import visual.Login;
import visual.regUser;

public class Cliente extends Thread{
	
	public Cliente() {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Clinica.getInstance().cargarClinica();
					
					if(Clinica.getInstance().isFirst() == false) {
						
						Login frame = new Login();
						frame.setVisible(true);
					}else {
						regUser register = new regUser(null);
						register.setVisible(true);
						register.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						register.addWindowListener(new WindowAdapter() {
							@Override
							public void windowClosed(WindowEvent e) {
								Login frame = new Login();
								frame.setVisible(true);
							}
						});
					}

				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Se ha producido un error", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	// Arrancar el programa
	
	public static void main(String[] args) {
		
			Cliente cliente = new Cliente();
			
	}
}

