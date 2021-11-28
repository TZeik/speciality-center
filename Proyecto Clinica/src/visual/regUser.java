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
	private Usuario update;
	private JComboBox cbxUserType;
	private JButton btnRegister;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					regUser frame = new regUser(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param selected 
	 */
	public regUser(Usuario aux) {
		update = aux;
		if (update == null ) {
			setTitle("Registrar usuario");
		} else {
			setTitle("Editar usuario");
		}
		setAlwaysOnTop(true);
		setType(Type.UTILITY);
		setTitle("Registrar usuario");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		panel_1.setBounds(10, 11, 495, 47);
		panel.add(panel_1);
		
		cbxUserType = new JComboBox();
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
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(405, 283, 100, 30);
		panel.add(btnCancel);
		
		btnRegister = new JButton("");
		if (update == null ) {
			btnRegister.setText("Registrar");
		} else {
			btnRegister.setText("Editar");
		}
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String psw1 = new String(pswRegister.getPassword());
				String psw2 = new String(pswConfirm.getPassword());
				
				if(txtName.getText().isEmpty() == true || txtUser.getText().isEmpty() == true || psw1.isEmpty() == true|| psw2.isEmpty() == true) {
					JOptionPane.showMessageDialog(panel, "No puede dejar casillas vacias", "Error", JOptionPane.ERROR_MESSAGE);
				}else if(psw1.equals(psw2)) {
					if(update == null) {
					
					switch (cbxUserType.getSelectedIndex()) {
					
					case 0:
						Administrador newAdmin = new Administrador(Clinica.getInstance().GenerateUserCode());
						newAdmin.setId(txtUser.getText());
						newAdmin.setPassword(psw1);
						newAdmin.setNombre(txtName.getText());
						Clinica.getInstance().getMisUsuarios().add(newAdmin);
						Clinica.getInstance().setUserCodeGenerator(Clinica.getInstance().getUserCodeGenerator() + 1);
						JOptionPane.showMessageDialog(panel , "El usuario se ha creado exitosamente", "Registro completo", JOptionPane.INFORMATION_MESSAGE);
						Clinica.getInstance().guardarClinica();
						dispose();
						break;
					case 1:
						Medico newMedic = new Medico(Clinica.getInstance().GenerateUserCode());
						newMedic.setId(txtUser.getText());
						newMedic.setPassword(psw1);
						newMedic.setNombre(txtName.getText());
						Clinica.getInstance().getMisUsuarios().add(newMedic);
						Clinica.getInstance().setUserCodeGenerator(Clinica.getInstance().getUserCodeGenerator() + 1);
						JOptionPane.showMessageDialog(panel, "El usuario se ha creado exitosamente", "Registro completo", JOptionPane.INFORMATION_MESSAGE);
						Clinica.getInstance().guardarClinica();
						dispose();
						break;
					case 2:
						Secretario newSecretary = new Secretario(Clinica.getInstance().GenerateUserCode());
						newSecretary.setId(txtUser.getText());
						newSecretary.setPassword(psw1);
						newSecretary.setNombre(txtName.getText());
						Clinica.getInstance().getMisUsuarios().add(newSecretary);
						Clinica.getInstance().setUserCodeGenerator(Clinica.getInstance().getUserCodeGenerator() + 1);
						JOptionPane.showMessageDialog(panel, "El usuario se ha creado exitosamente", "Registro completo", JOptionPane.INFORMATION_MESSAGE);
						Clinica.getInstance().guardarClinica();
						dispose();
						break;

					}
				   }else {
						switch (cbxUserType.getSelectedIndex()) {
						
						case 0:
							
							update.setId(txtUser.getText());
							update.setPassword(psw1);
							update.setNombre(txtName.getText());
							Clinica.getInstance().getMisUsuarios().get(Clinica.getInstance().buscarUsuarioIndex(update)).setNombre(update.getNombre());
							Clinica.getInstance().getMisUsuarios().get(Clinica.getInstance().buscarUsuarioIndex(update)).setId(update.getId());
							Clinica.getInstance().getMisUsuarios().get(Clinica.getInstance().buscarUsuarioIndex(update)).setPassword(update.getPassword());
							Clinica.getInstance().guardarClinica();
							JOptionPane.showMessageDialog(panel , "El usuario se ha editado exitosamente", "Edición completada", JOptionPane.INFORMATION_MESSAGE);
							dispose();
							break;
						case 1:
							update.setId(txtUser.getText());
							update.setPassword(psw1);
							update.setNombre(txtName.getText());
							Clinica.getInstance().getMisUsuarios().get(Clinica.getInstance().buscarUsuarioIndex(update)).setNombre(update.getNombre());
							Clinica.getInstance().getMisUsuarios().get(Clinica.getInstance().buscarUsuarioIndex(update)).setId(update.getId());
							Clinica.getInstance().getMisUsuarios().get(Clinica.getInstance().buscarUsuarioIndex(update)).setPassword(update.getPassword());
							Clinica.getInstance().guardarClinica();
							JOptionPane.showMessageDialog(panel, "El usuario se ha editado exitosamente", "Edición completada", JOptionPane.INFORMATION_MESSAGE);
							dispose();
							break;
						case 2:
							update.setId(txtUser.getText());
							update.setPassword(psw1);
							update.setNombre(txtName.getText());
							Clinica.getInstance().getMisUsuarios().get(Clinica.getInstance().buscarUsuarioIndex(update)).setNombre(update.getNombre());
							Clinica.getInstance().getMisUsuarios().get(Clinica.getInstance().buscarUsuarioIndex(update)).setId(update.getId());
							Clinica.getInstance().getMisUsuarios().get(Clinica.getInstance().buscarUsuarioIndex(update)).setPassword(update.getPassword());
							Clinica.getInstance().guardarClinica();
							JOptionPane.showMessageDialog(panel, "El usuario se ha editado exitosamente", "Edición completada", JOptionPane.INFORMATION_MESSAGE);
							dispose();
							break;

						}
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
		}else {
			cbxUserType.setEnabled(true);
			txtUser.setText("");
		}
	}
	
	private void loadUser(){
		if(update != null) {
			txtName.setText(update.getNombre());
			txtUser.setText(update.getId());
			pswRegister.setText(update.getPassword());
			pswConfirm.setText(update.getPassword());
			if (update instanceof Administrador) {
				cbxUserType.setSelectedIndex(0);
			}else if (update instanceof Medico) {
				cbxUserType.setSelectedIndex(1);
			}else if (update instanceof Secretario) {
				cbxUserType.setSelectedIndex(2);
			}

		}
		
	}
}
