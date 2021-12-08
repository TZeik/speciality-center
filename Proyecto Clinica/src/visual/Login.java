package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.Label;
import javax.swing.JTextField;
import java.awt.Panel;
import java.awt.FlowLayout;
import javax.swing.border.TitledBorder;

import arrancar.Cliente;
import arrancar.Servidor;
import logica.Clinica;

import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.Window.Type;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import java.awt.Cursor;
import java.awt.ComponentOrientation;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JPasswordField pswLogin;
	private JTextField txtLogin;
	static Socket sfd = null;
	static DataInputStream EntradaSocket;
	static DataOutputStream SalidaSocket;

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the frame.
	 */
	public Login() {
		setFocusTraversalKeysEnabled(false);
		setUndecorated(true);


		setResizable(false);
		setTitle("Autentificaci\u00F3n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 800, 500);
		panel.setBackground(Color.WHITE);
		contentPane.add(panel);
		panel.setLayout(null);
		
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				int keyCode = e.getID();
				if(keyCode == KeyEvent.VK_ENTER) {
					System.out.println("Pressed Enter");
					String psw = new String(pswLogin.getPassword());
					
					if(Clinica.getInstance().confirmLogin(txtLogin.getText(), psw) == true) {
						Principal window = new Principal();
						dispose();
						window.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(panel, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		JPanel exitPanel = new JPanel();
		exitPanel.setBackground(Color.WHITE);
		exitPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		exitPanel.setBounds(750, 0, 50, 40);
		panel.add(exitPanel);
		exitPanel.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("X");
		lblNewLabel_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(1);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				exitPanel.setBackground(new Color(220, 20, 60));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				exitPanel.setBackground(Color.WHITE);
			}
		});
		lblNewLabel_4.setFont(new Font("Roboto", Font.PLAIN, 17));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(0, 0, 50, 40);
		exitPanel.add(lblNewLabel_4);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/images/bgi.png")));
		lblNewLabel.setBounds(345, 0, 455, 500);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(0, 0, 345, 500);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(Color.BLACK);
		separator_1.setBounds(10, 339, 325, 1);
		panel_1.add(separator_1);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.BLACK);
		separator.setBounds(10, 236, 325, 1);
		panel_1.add(separator);
		
		JLabel lblNewLabel_1 = new JLabel(" CL\u00CDNICA ORTENCIAS");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Roboto Medium", Font.PLAIN, 24));
		lblNewLabel_1.setBounds(10, 29, 325, 64);
		panel_1.add(lblNewLabel_1);
		lblNewLabel_1.setIcon(new ImageIcon(Login.class.getResource("/images/miniLogo.png")));
		
		Label label_1 = new Label("USUARIO");
		label_1.setForeground(Color.DARK_GRAY);
		label_1.setFont(new Font("Roboto Black", Font.BOLD, 14));
		label_1.setBounds(10, 180, 325, 22);
		panel_1.add(label_1);
		
		txtLogin = new JTextField();

		txtLogin.setFont(new Font("Roboto", Font.PLAIN, 14));
		txtLogin.setBorder(null);
		txtLogin.setForeground(Color.GRAY);
		txtLogin.setText("Ingrese su nombre de usuario");
		txtLogin.setBounds(20, 203, 300, 30);
		panel_1.add(txtLogin);
		txtLogin.setColumns(10);
		
		
		pswLogin = new JPasswordField();

		pswLogin.setForeground(Color.GRAY);
		pswLogin.setText("************");
		pswLogin.setToolTipText("");
		pswLogin.setBorder(null);
		pswLogin.setBounds(20, 306, 300, 30);
		panel_1.add(pswLogin);
		
		Label label = new Label("CONTRASE\u00D1A");
		label.setForeground(Color.DARK_GRAY);
		label.setFont(new Font("Roboto Black", Font.BOLD, 14));
		label.setBounds(10, 282, 250, 22);
		panel_1.add(label);
		
		txtLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				String psw = new String(pswLogin.getPassword());
				
				if(txtLogin.getText().equals("Ingrese su nombre de usuario")) {
					txtLogin.setText("");
					txtLogin.setForeground(Color.BLACK);
				}
				
				if(psw.isEmpty()) {
					pswLogin.setText("************");
					pswLogin.setForeground(Color.GRAY);
				}
				
			}
		});
		
		pswLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				String psw = new String(pswLogin.getPassword());
				String empty = new String("");
				
				if(txtLogin.getText().isEmpty()){
					txtLogin.setText("Ingrese su nombre de usuario");
					txtLogin.setForeground(Color.GRAY);
				}
				
				if(psw.equals("************")) {
					pswLogin.setText(empty);
					pswLogin.setForeground(Color.BLACK);
				}
			}
		});
		
		JLabel lblNewLabel_2 = new JLabel("INICIAR SESI\u00D3N");
		lblNewLabel_2.setFont(new Font("Roboto Medium", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(10, 117, 325, 40);
		panel_1.add(lblNewLabel_2);
		
		JPanel panel_2 = new JPanel();
		panel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				String psw = new String(pswLogin.getPassword());
				
				if(Clinica.getInstance().confirmLogin(txtLogin.getText(), psw) == true) {
					
					Principal window = new Principal();
					dispose();
					window.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(panel, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				panel_2.setBackground(SystemColor.activeCaption);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panel_2.setBackground(SystemColor.textHighlight);
			}
		});
		panel_2.setBackground(SystemColor.textHighlight);
		panel_2.setForeground(SystemColor.activeCaption);
		panel_2.setBounds(85, 400, 154, 50);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("INGRESAR");

		lblNewLabel_3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel_3.setForeground(SystemColor.text);
		lblNewLabel_3.setFont(new Font("Roboto", Font.BOLD, 14));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(0, 0, 154, 50);
		panel_2.add(lblNewLabel_3);
		
	}
}
