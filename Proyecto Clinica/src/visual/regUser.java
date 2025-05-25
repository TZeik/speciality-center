package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Window.Type;
import javax.swing.border.TitledBorder;

import exceptions.UserRegistrationException;
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
import java.awt.Dialog.ModalExclusionType;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;

public class regUser extends JFrame {

	private JPanel contentPane;
	private JTextField txtUser;
	private JPasswordField pswRegister;
	private JPasswordField pswConfirm;
	private JTextField txtName;
	private Usuario update;
	private JComboBox cbxUserType;
	private JPanel btnDynamic;
	private JPanel btnCancelar;
	private JPanel headers;
	private JPanel body;
	private JLabel background;
	private JLabel Logo;
	private JLabel lblNewLabel_1;
	private JSeparator separator;
	private JSeparator separator_1;
	private JSeparator separator_2;
	private JSeparator separator_3;
	private JPanel exitPanel;
	private JPanel minimizePanel;
	private JLabel exitLogo;
	private JLabel minimizeLogo;
	private JLabel lblCancelar;
	private JLabel lblDynamic;

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
		setUndecorated(true);

		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		update = aux;
		if (update == null ) {
			setTitle("Registrar usuario");
		} else {
			setTitle("Editar usuario");
		}
		setAlwaysOnTop(true);
		setTitle("Registrar usuario");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 761, 452);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 779, 452);
		panel.setBackground(Color.WHITE);
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblNewLabel_1 = new JLabel("LAS ORTENCIAS");
		lblNewLabel_1.setForeground(SystemColor.text);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Roboto", Font.BOLD, 24));
		lblNewLabel_1.setBounds(527, 213, 214, 52);
		panel.add(lblNewLabel_1);
		
		Logo = new JLabel("");
		Logo.setIcon(new ImageIcon(regUser.class.getResource("/images/logo.png")));
		Logo.setBounds(571, 77, 125, 125);
		panel.add(Logo);
		
		headers = new JPanel();
		headers.setBackground(Color.WHITE);
		headers.setBounds(0, 0, 769, 40);
		panel.add(headers);
		headers.setLayout(null);
		
		exitPanel = new JPanel();
		
		exitPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exitPanel.setBackground(Color.RED);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				exitPanel.setBackground(Color.WHITE);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		
		exitPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(1);
			}
		});
		exitPanel.setBorder(null);
		exitPanel.setBackground(Color.WHITE);
		exitPanel.setBounds(701, 0, 60, 40);
		headers.add(exitPanel);
		exitPanel.setLayout(null);
		
		exitLogo = new JLabel("X");

		exitLogo.setFont(new Font("Roboto", Font.BOLD, 18));
		exitLogo.setHorizontalAlignment(SwingConstants.CENTER);
		exitLogo.setBounds(0, 0, 60, 40);
		exitPanel.add(exitLogo);
		
		minimizePanel = new JPanel();
		minimizePanel.setBorder(null);
		minimizePanel.setBackground(Color.WHITE);
		minimizePanel.setBounds(641, 0, 60, 40);
		headers.add(minimizePanel);
		minimizePanel.setLayout(null);
		
		minimizeLogo = new JLabel("\u2014");
		minimizePanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				minimizePanel.setBackground(Color.GRAY);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				minimizePanel.setBackground(Color.WHITE);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				setState(Frame.ICONIFIED);
			}
		});
		minimizeLogo.setFont(new Font("Roboto", Font.BOLD, 18));
		minimizeLogo.setHorizontalAlignment(SwingConstants.CENTER);
		minimizeLogo.setBounds(0, 0, 60, 40);
		minimizePanel.add(minimizeLogo);
		
		body = new JPanel();
		body.setBackground(SystemColor.control);
		body.setBounds(0, 0, 506, 452);
		panel.add(body);
		body.setLayout(null);
		
		cbxUserType = new JComboBox();
		cbxUserType.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(64, 64, 64)));
		cbxUserType.setFont(new Font("Roboto", Font.PLAIN, 14));
		cbxUserType.setBackground(Color.WHITE);
		cbxUserType.setBounds(24, 361, 461, 30);
		body.add(cbxUserType);
		cbxUserType.setModel(new DefaultComboBoxModel(new String[] {"Administrador", "M\u00E9dico", "Secretario"}));
		
		JLabel lblNewLabel_2 = new JLabel("TIPO DE USUARIO");
		lblNewLabel_2.setFont(new Font("Roboto", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(24, 336, 234, 14);
		body.add(lblNewLabel_2);
		
		txtUser = new JTextField();
		txtUser.setBackground(SystemColor.control);
		txtUser.setFont(new Font("Roboto", Font.PLAIN, 14));
		txtUser.setBorder(null);
		txtUser.setBounds(24, 79, 461, 30);
		body.add(txtUser);
		txtUser.setColumns(10);
		
		separator_3 = new JSeparator();
		separator_3.setBackground(Color.BLACK);
		separator_3.setBounds(24, 319, 461, 1);
		body.add(separator_3);
		
		JLabel lblUser = new JLabel("NOMBRE DE USUARIO");
		lblUser.setFont(new Font("Roboto", Font.PLAIN, 14));
		lblUser.setBounds(24, 54, 203, 14);
		body.add(lblUser);
		
		pswRegister = new JPasswordField();
		pswRegister.setBackground(SystemColor.control);
		pswRegister.setBorder(null);
		pswRegister.setBounds(24, 225, 461, 30);
		body.add(pswRegister);
		
		separator_2 = new JSeparator();
		separator_2.setBackground(Color.BLACK);
		separator_2.setBounds(24, 255, 461, 1);
		body.add(separator_2);
		
		JLabel lblNewLabel_1_1 = new JLabel("CONFIRMAR CONTRASE\u00D1A");
		lblNewLabel_1_1.setFont(new Font("Roboto", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(24, 200, 229, 14);
		body.add(lblNewLabel_1_1);
		
		pswConfirm = new JPasswordField();
		pswConfirm.setBackground(SystemColor.control);
		pswConfirm.setBorder(null);
		pswConfirm.setBounds(24, 150, 461, 30);
		body.add(pswConfirm);
		
		separator_1 = new JSeparator();
		separator_1.setBackground(Color.BLACK);
		separator_1.setBounds(24, 180, 461, 1);
		body.add(separator_1);
		
		JLabel lblPassword = new JLabel("CONTRASE\u00D1A");
		lblPassword.setFont(new Font("Roboto", Font.PLAIN, 14));
		lblPassword.setBounds(24, 125, 229, 14);
		body.add(lblPassword);
		
		txtName = new JTextField();
		txtName.setFont(new Font("Roboto", Font.PLAIN, 14));
		txtName.setBackground(SystemColor.control);
		txtName.setBorder(null);
		txtName.setBounds(24, 289, 461, 30);
		body.add(txtName);
		txtName.setColumns(10);
		
		separator = new JSeparator();
		separator.setBackground(new Color(0, 0, 0));
		separator.setBounds(24, 109, 461, 1);
		body.add(separator);
		
		JLabel lblNewLabel = new JLabel("NOMBRE VISIBLE");
		lblNewLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
		lblNewLabel.setBounds(24, 264, 203, 14);
		body.add(lblNewLabel);
		
		btnCancelar = new JPanel();
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				btnCancelar.setBackground(SystemColor.activeCaption);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnCancelar.setBackground(SystemColor.textHighlight);
			}
			
		});
		btnCancelar.setBackground(SystemColor.textHighlight);
		btnCancelar.setBounds(364, 411, 120, 30);
		body.add(btnCancelar);
		btnCancelar.setLayout(null);
		
		lblCancelar = new JLabel("Cancelar");
		lblCancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblCancelar.setForeground(SystemColor.text);
		lblCancelar.setFont(new Font("Roboto", Font.PLAIN, 14));
		lblCancelar.setHorizontalAlignment(SwingConstants.CENTER);
		lblCancelar.setBounds(0, 0, 120, 30);
		btnCancelar.add(lblCancelar);
		
		btnDynamic = new JPanel();
		btnDynamic.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseEntered(MouseEvent e) {
				btnDynamic.setBackground(SystemColor.activeCaption);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnDynamic.setBackground(SystemColor.textHighlight);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {

				String psw1 = new String(pswRegister.getPassword());
				String psw2 = new String(pswConfirm.getPassword());
				
				if(txtName.getText().isEmpty() || txtUser.getText().isEmpty() || psw1.isEmpty() || psw2.isEmpty() ) {
					JOptionPane.showMessageDialog(panel, "No puede dejar casillas vacias", "Error", JOptionPane.ERROR_MESSAGE);
				}else if(psw1.equals(psw2)) {
					if(update == null) {
					
					switch (cbxUserType.getSelectedIndex()) {
					
					case 0:
						Administrador newAdmin = new Administrador(Clinica.getInstance().GenerateUserCode());
						newAdmin.setId(txtUser.getText());
						newAdmin.setPassword(psw1);
						newAdmin.setNombre(txtName.getText());
						try {
							Clinica.getInstance().confirmRegistration(newAdmin);
							Clinica.getInstance().getMisUsuarios().add(newAdmin);
							Clinica.getInstance().setUserCodeGenerator(Clinica.getInstance().getUserCodeGenerator() + 1);
							JOptionPane.showMessageDialog(panel , "El usuario se ha creado exitosamente", "Registro completo", JOptionPane.INFORMATION_MESSAGE);
							Clinica.getInstance().guardarClinica();
							dispose();
						} catch (UserRegistrationException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
						}

						break;
					case 1:
						Medico newMedic = new Medico(Clinica.getInstance().GenerateUserCode());
						newMedic.setId(txtUser.getText());
						newMedic.setPassword(psw1);
						newMedic.setNombre(txtName.getText());
						try {
							Clinica.getInstance().confirmRegistration(newMedic);
							Clinica.getInstance().getMisUsuarios().add(newMedic);
							Clinica.getInstance().setUserCodeGenerator(Clinica.getInstance().getUserCodeGenerator() + 1);
							JOptionPane.showMessageDialog(panel, "El usuario se ha creado exitosamente", "Registro completo", JOptionPane.INFORMATION_MESSAGE);
							Clinica.getInstance().guardarClinica();
							dispose();
						} catch (UserRegistrationException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
						}
						break;
					case 2:
						Secretario newSecretary = new Secretario(Clinica.getInstance().GenerateUserCode());
						newSecretary.setId(txtUser.getText());
						newSecretary.setPassword(psw1);
						newSecretary.setNombre(txtName.getText());
						try {
							Clinica.getInstance().confirmRegistration(newSecretary);
							Clinica.getInstance().getMisUsuarios().add(newSecretary);
							Clinica.getInstance().setUserCodeGenerator(Clinica.getInstance().getUserCodeGenerator() + 1);
							JOptionPane.showMessageDialog(panel, "El usuario se ha creado exitosamente", "Registro completo", JOptionPane.INFORMATION_MESSAGE);
							Clinica.getInstance().guardarClinica();
							dispose();
						} catch (UserRegistrationException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
						}
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
		btnDynamic.setBackground(SystemColor.textHighlight);
		btnDynamic.setBounds(224, 411, 120, 30);
		body.add(btnDynamic);
		btnDynamic.setLayout(null);
		
		lblDynamic = new JLabel("<dynamic>");
		lblDynamic.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblDynamic.setForeground(SystemColor.text);
		lblDynamic.setFont(new Font("Roboto", Font.PLAIN, 14));
		lblDynamic.setHorizontalAlignment(SwingConstants.CENTER);
		lblDynamic.setBounds(0, 0, 120, 30);
		btnDynamic.add(lblDynamic);
		
		background = new JLabel("");
		background.setIcon(new ImageIcon(regUser.class.getResource("/images/bkg.jpg")));
		background.setBounds(507, 0, 262, 452);
		panel.add(background);
		
		if(Clinica.getInstance().isFirst() == true) {
			cbxUserType.setSelectedIndex(0);
			cbxUserType.setEnabled(false);
			txtUser.setText("Admin");
		}else {
			cbxUserType.setEnabled(true);
			txtUser.setText("");
		}
		loadUser();
		
		if(update == null) {
			lblDynamic.setText("Registrar");
		}else {
			lblDynamic.setText("Editar");
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
