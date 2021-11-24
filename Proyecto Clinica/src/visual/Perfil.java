package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Window.Type;
import javax.swing.border.TitledBorder;

import logica.Administrador;
import logica.Clinica;
import logica.Medico;
import logica.Secretario;

import java.awt.Dialog.ModalExclusionType;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Perfil extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtUser;
	private JPasswordField pswUser;
	private JTextField txtTel;
	private JTextField txtDynamic;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Perfil frame = new Perfil();
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
	public Perfil() {
		setType(Type.UTILITY);
		setTitle("Perfil");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 679, 464);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Perfil de " + Clinica.getInstance().getLogedUser().getNombre(), TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Estadisticas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(399, 23, 244, 381);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Informaci\u00F3n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 23, 367, 381);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		
		JButton btnEditar = new JButton("Editar perfil");
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setEnabled(false);
		
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtName.setEditable(true);
				txtTel.setEditable(true);
				txtDynamic.setEditable(true);
				btnEditar.setEnabled(false);
				btnGuardar.setEnabled(true);

			}
		});
		btnEditar.setBounds(257, 345, 100, 25);
		panel_2.add(btnEditar);
		
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtName.setEditable(false);
				txtTel.setEditable(false);
				txtDynamic.setEditable(false);
				btnGuardar.setEnabled(false);
				btnEditar.setEnabled(true);
				
				if(Clinica.getInstance().getLogedUser() instanceof  Administrador) {
					Administrador newAdmin = new Administrador(Clinica.getInstance().getLogedUser().getCodigo());
					newAdmin.setNombre(txtName.getText());
					newAdmin.setTelefono(txtTel.getText());
					newAdmin.setPuesto(txtDynamic.getText());
					Clinica.getInstance().EditUsuario(newAdmin);
					Clinica.getInstance().guardarClinica();
					JOptionPane.showMessageDialog(panel, "Se ha guardado con éxito", "Guardar usuario", JOptionPane.INFORMATION_MESSAGE);
				}
				if(Clinica.getInstance().getLogedUser() instanceof  Medico) {
					Medico newMed = new Medico(Clinica.getInstance().getLogedUser().getCodigo());
					newMed.setNombre(txtName.getText());
					newMed.setTelefono(txtTel.getText());
					newMed.setEspecialidad(txtDynamic.getText());
					Clinica.getInstance().EditUsuario(newMed);
					Clinica.getInstance().guardarClinica();
					JOptionPane.showMessageDialog(panel, "Se ha guardado con éxito", "Guardar usuario", JOptionPane.INFORMATION_MESSAGE);
				}
				if(Clinica.getInstance().getLogedUser() instanceof  Secretario) {
					Secretario newSec = new Secretario(Clinica.getInstance().getLogedUser().getCodigo());
					newSec.setNombre(txtName.getText());
					newSec.setTelefono(txtTel.getText());
					Clinica.getInstance().EditUsuario(newSec);
					Clinica.getInstance().guardarClinica();
					JOptionPane.showMessageDialog(panel, "Se ha guardado con éxito", "Guardar usuario", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnGuardar.setBounds(147, 345, 100, 25);
		panel_2.add(btnGuardar);
		
		txtName = new JTextField();
		txtName.setEditable(false);
		txtName.setBounds(10, 70, 347, 25);
		txtName.setText(Clinica.getInstance().getLogedUser().getNombre());
		panel_2.add(txtName);
		txtName.setColumns(10);
		
		JLabel lblVisible = new JLabel("Nombre visible: ");
		lblVisible.setBounds(10, 50, 347, 14);
		panel_2.add(lblVisible);
		
		txtUser = new JTextField();
		txtUser.setEditable(false);
		txtUser.setBounds(10, 120, 347, 25);
		txtUser.setText(Clinica.getInstance().getLogedUser().getId());
		panel_2.add(txtUser);
		txtUser.setColumns(10);
		
		pswUser = new JPasswordField();
		pswUser.setEditable(false);
		pswUser.setBounds(10, 170, 250, 25);
		pswUser.setText("0123456789ABCDEFGHIJKLMN");
		panel_2.add(pswUser);
		
		JLabel lblUser = new JLabel("Usuario (ID): ");
		lblUser.setBounds(10, 100, 347, 14);
		panel_2.add(lblUser);
		
		JLabel lblPassword = new JLabel("Contrase\u00F1a: ");
		lblPassword.setBounds(10, 150, 347, 14);
		panel_2.add(lblPassword);
		
		JButton btnCambiar = new JButton("Cambiar");
		btnCambiar.setBounds(267, 170, 90, 25);
		panel_2.add(btnCambiar);
		
		txtTel = new JTextField();
		txtTel.setEditable(false);
		txtTel.setBounds(10, 220, 347, 25);
		txtTel.setText(Clinica.getInstance().getLogedUser().getTelefono());
		panel_2.add(txtTel);
		txtTel.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Telefono:  ");
		lblNewLabel_3.setBounds(10, 200, 347, 14);
		panel_2.add(lblNewLabel_3);
		
		if(Clinica.getInstance().getLogedUser() instanceof Administrador || Clinica.getInstance().getLogedUser() instanceof Medico) {
			
			JLabel lblDynamic = new JLabel("<dynamic>");
			lblDynamic.setBounds(10, 250, 347, 14);
			panel_2.add(lblDynamic);
			
			txtDynamic = new JTextField();
			txtDynamic.setEditable(false);
			txtDynamic.setBounds(10, 270, 347, 25);
			panel_2.add(txtDynamic);
			txtDynamic.setColumns(10);
			
			if(Clinica.getInstance().getLogedUser() instanceof Administrador) {
				Administrador admin = (Administrador)Clinica.getInstance().getLogedUser();
				lblDynamic.setText("Puesto: ");
				txtDynamic.setText(admin.getPuesto());
			}
			
			if(Clinica.getInstance().getLogedUser() instanceof Medico) {
				Medico med = (Medico)Clinica.getInstance().getLogedUser();
				lblDynamic.setText("Especialidad: ");
				txtDynamic.setText(med.getEspecialidad());
			}
		}

	}
}