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

public class Login extends JFrame {

	private JPanel contentPane;
	private JPasswordField pswLogin;
	private JTextField txtLogin;
	private JButton btnIngresar;

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

		setResizable(false);
		setTitle("Autentificaci\u00F3n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 548, 325);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), "Ingresar usuario", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
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
		
		btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String psw = new String(pswLogin.getPassword());
				
				if(Clinica.getInstance().confirmLogin(txtLogin.getText(), psw) == true) {
					Principal window = new Principal();
					dispose();
					window.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(panel, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnIngresar.setBounds(385, 180, 125, 30);
		panel.add(btnIngresar);
		
		pswLogin = new JPasswordField();
		pswLogin.setBounds(10, 185, 250, 20);
		panel.add(pswLogin);
		
		Label label = new Label("Contrase\u00F1a: ");
		label.setBounds(10, 157, 250, 22);
		panel.add(label);
		
		txtLogin = new JTextField();
		txtLogin.setBounds(10, 131, 250, 20);
		panel.add(txtLogin);
		txtLogin.setColumns(10);
		
		Label label_1 = new Label("Usuario: ");
		label_1.setBounds(10, 103, 250, 22);
		panel.add(label_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 225, 499, 40);
		panel.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Todos los derechos reservados");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel, BorderLayout.CENTER);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 24, 500, 73);
		panel.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("La Clinique");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel_1, BorderLayout.CENTER);
		
		if(Clinica.getInstance().isFirst() == true) {
			regUser register = new regUser();
			dispose();
			register.setVisible(true);
		}
		
	}
}
