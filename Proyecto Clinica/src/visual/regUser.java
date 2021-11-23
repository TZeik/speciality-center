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
import logica.Usuario;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class regUser extends JFrame {

	private JPanel contentPane;
	private JTextField txtUser;
	private JPasswordField pswRegister;
	private JPasswordField pswConfirm;
	private JTextField txtName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					regUser frame = new regUser();
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
	public regUser() {
		setAlwaysOnTop(true);
		setType(Type.UTILITY);
		setTitle("Registrar usuario");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 540, 376);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 11, 495, 30);
		panel.add(panel_1);
		
		JComboBox cbxUserType = new JComboBox();
		cbxUserType.setModel(new DefaultComboBoxModel(new String[] {"Administrador", "M\u00E9dico", "Secretario"}));
		cbxUserType.setBounds(10, 220, 234, 25);
		panel.add(cbxUserType);
		
		txtUser = new JTextField();
		txtUser.setBounds(10, 160, 234, 25);
		panel.add(txtUser);
		txtUser.setColumns(10);
		
		JLabel lblUser = new JLabel("Usuario: ");
		lblUser.setBounds(10, 135, 203, 14);
		panel.add(lblUser);
		
		JLabel lblPassword = new JLabel("Contrase\u00F1a: ");
		lblPassword.setBounds(265, 69, 229, 14);
		panel.add(lblPassword);
		
		pswRegister = new JPasswordField();
		pswRegister.setBounds(265, 160, 240, 25);
		panel.add(pswRegister);
		
		JLabel lblNewLabel_1_1 = new JLabel("Confirmar contrase\u00F1a: ");
		lblNewLabel_1_1.setBounds(265, 135, 229, 14);
		panel.add(lblNewLabel_1_1);
		
		pswConfirm = new JPasswordField();
		pswConfirm.setBounds(265, 94, 240, 25);
		panel.add(pswConfirm);
		
		JLabel lblNewLabel_2 = new JLabel("Tipo de usuario: ");
		lblNewLabel_2.setBounds(10, 200, 234, 14);
		panel.add(lblNewLabel_2);
		
		JButton btnCancel = new JButton("Cancelar");
		btnCancel.setBounds(405, 283, 100, 30);
		panel.add(btnCancel);
		
		JButton btnRegister = new JButton("Registrar");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String psw1 = new String(pswRegister.getPassword());
				String psw2 = new String(pswConfirm.getPassword());
				
				if(txtName.getText().isEmpty() == true || txtUser.getText().isEmpty() == true || psw1.isEmpty() == true|| psw2.isEmpty() == true) {
					JOptionPane.showMessageDialog(panel, "No puede dejar casillas vacias", "Error", JOptionPane.ERROR_MESSAGE);
				}else if(psw1.equals(psw2)) {
					
					switch (cbxUserType.getSelectedIndex()) {
					
					case 0:
						Administrador newAdmin = new Administrador(Clinica.getInstance().getUserCodeGenerator());
						newAdmin.setId(txtUser.getText());
						newAdmin.setPassword(psw1);
						newAdmin.setNombre(txtName.getText());
						Clinica.getInstance().getMisUsuarios().add(newAdmin);
						JOptionPane.showMessageDialog(panel , "El usuario se ha creado exitosamente", "Registro completo", JOptionPane.INFORMATION_MESSAGE);
						Clinica.getInstance().guardarClinica();
						dispose();
						break;
					case 1:
						Medico newMedic = new Medico(Clinica.getInstance().getUserCodeGenerator());
						newMedic.setId(txtUser.getText());
						newMedic.setPassword(psw1);
						newMedic.setNombre(txtName.getText());
						Clinica.getInstance().getMisUsuarios().add(newMedic);
						JOptionPane.showMessageDialog(panel, "El usuario se ha creado exitosamente", "Registro completo", JOptionPane.INFORMATION_MESSAGE);
						Clinica.getInstance().guardarClinica();
						dispose();
						break;
					case 2:
						Secretario newSecretary = new Secretario(Clinica.getInstance().getUserCodeGenerator());
						newSecretary.setId(txtUser.getText());
						newSecretary.setPassword(psw1);
						newSecretary.setNombre(txtName.getText());
						Clinica.getInstance().getMisUsuarios().add(newSecretary);
						JOptionPane.showMessageDialog(panel, "El usuario se ha creado exitosamente", "Registro completo", JOptionPane.INFORMATION_MESSAGE);
						Clinica.getInstance().guardarClinica();
						dispose();
						break;

					}
				}else if(psw1 != psw2){
					JOptionPane.showMessageDialog(panel, "Las contraseñas no coinciden", "Error", JOptionPane.ERROR_MESSAGE);
					pswRegister.setText("");
					pswConfirm.setText("");
				}
				

			}
		});
		btnRegister.setBounds(285, 283, 100, 30);
		panel.add(btnRegister);
		
		txtName = new JTextField();
		txtName.setBounds(10, 94, 234, 25);
		panel.add(txtName);
		txtName.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nombre visible: ");
		lblNewLabel.setBounds(10, 69, 203, 14);
		panel.add(lblNewLabel);
		
		if(Clinica.getInstance().isFirst() == true) {
			cbxUserType.setSelectedIndex(0);
			cbxUserType.setEnabled(false);
			txtUser.setText("Admin");
		}
	}
}
