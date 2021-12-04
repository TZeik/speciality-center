package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logica.Cita;
import logica.Clinica;
import logica.Usuario;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.HierarchyListener;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.HierarchyEvent;
import java.awt.Font;

public class CrearCita extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtCedula;
	private JTextField txtDireccion;
	private JTextField txtTelefono;
	private JTextField txtEspecialidad;
	private JTextField txtFechaCita;
	private JComboBox cbxSexo;
	private JComboBox cbxDia;
	private JComboBox cbxMes;
	private JComboBox cbxAnno;
	private JComboBox cbxMedico;
	private JTextArea txaFinalidad;
	private JComboBox cbxDia_1;
	private JComboBox cbxMes_1;
	private JComboBox cbxAnno_1;
	private JComboBox cbxHora;
	private JComboBox cbxMinuto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrearCita frame = new CrearCita(0);
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
	public CrearCita(int option) {
		setTitle("Crear cita");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 679, 546);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		
		DefaultComboBoxModel diaModel = new DefaultComboBoxModel();
		DefaultComboBoxModel diaModel_1 = new DefaultComboBoxModel();
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblName = new JLabel("Nombre: ");
		lblName.setBounds(10, 30, 300, 14);
		panel.add(lblName);
		
		JLabel lblCedula = new JLabel("C\u00E9dula: ");
		lblCedula.setBounds(10, 100, 300, 14);
		panel.add(lblCedula);
		
		JLabel lblSexo = new JLabel("Sexo: ");
		lblSexo.setBounds(10, 170, 285, 14);
		panel.add(lblSexo);
		
		JLabel lblTelefono = new JLabel("Tel\u00E9fono: ");
		lblTelefono.setBounds(10, 240, 300, 14);
		panel.add(lblTelefono);
		
		JLabel lblDireccion = new JLabel("Direcci\u00F3n: ");
		lblDireccion.setBounds(10, 310, 300, 14);
		panel.add(lblDireccion);
		
		JLabel lblEspecialidad = new JLabel("Especialidad: ");
		lblEspecialidad.setBounds(320, 170, 285, 14);
		panel.add(lblEspecialidad);
		
		JLabel lblNacimiento = new JLabel("Fecha de nacimiento: ");
		lblNacimiento.setBounds(320, 30, 285, 14);
		panel.add(lblNacimiento);
		
		JLabel lblFechaHoy = new JLabel("Fecha de hoy:");
		lblFechaHoy.setBounds(10, 366, 285, 14);
		panel.add(lblFechaHoy);
		
		JLabel lblMedico = new JLabel("Medico: ");
		lblMedico.setBounds(320, 240, 285, 14);
		panel.add(lblMedico);
		
		JLabel lblFinalidad = new JLabel("Finalidad: ");
		lblFinalidad.setBounds(320, 310, 285, 14);
		panel.add(lblFinalidad);
		
		txtName = new JTextField();
		txtName.setBounds(10, 50, 285, 25);
		panel.add(txtName);
		txtName.setColumns(10);
		
		txtCedula = new JTextField();
		txtCedula.setColumns(10);
		txtCedula.setBounds(10, 120, 285, 25);
		panel.add(txtCedula);
		
		txtDireccion = new JTextField();
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(10, 260, 285, 25);
		panel.add(txtDireccion);
		
		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(10, 330, 285, 25);
		panel.add(txtTelefono);
		
		txtEspecialidad = new JTextField();
		txtEspecialidad.setColumns(10);
		txtEspecialidad.setBounds(320, 190, 320, 25);
		panel.add(txtEspecialidad);
		
		txaFinalidad = new JTextArea();
		txaFinalidad.setLineWrap(true);
		txaFinalidad.setBounds(320, 330, 320, 100);
		panel.add(txaFinalidad);
		
		cbxDia = new JComboBox();
		
		cbxMes = new JComboBox();
		cbxMes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(cbxMes.getSelectedIndex() == 0 || cbxMes.getSelectedIndex() == 2 || cbxMes.getSelectedIndex() == 4 || cbxMes.getSelectedIndex() == 6 || cbxMes.getSelectedIndex() == 7 || cbxMes.getSelectedIndex() == 9 || cbxMes.getSelectedIndex() == 11) {
					if(diaModel.getSize() == 30) {
						diaModel.addElement(31);
					}
					if(diaModel.getSize() == 28) {
						diaModel.addElement(29);
						diaModel.addElement(30);
						diaModel.addElement(31);
					}
				}
				
				if(cbxMes.getSelectedIndex() == 3 || cbxMes.getSelectedIndex() == 5 || cbxMes.getSelectedIndex() == 8 || cbxMes.getSelectedIndex() == 10) {
					if(diaModel.getSize() == 31) {
						if(cbxDia.getSelectedIndex()+1 < 31) {
							diaModel.removeElementAt(30);
						}else {
							cbxDia.setSelectedIndex(29);
							diaModel.removeElementAt(30);
						}
					}
					
					if(diaModel.getSize() == 28) {
						diaModel.addElement(30);
						diaModel.addElement(31);
					}
					

					
				}
				
				if(cbxMes.getSelectedIndex() == 1) {
					if(diaModel.getSize() == 31) {
						if(cbxDia.getSelectedIndex() + 1 < 30) {
							diaModel.removeElementAt(30);
							diaModel.removeElementAt(29);
							diaModel.removeElementAt(28);
						} else {
							cbxDia.setSelectedIndex(27);
							diaModel.removeElementAt(30);
							diaModel.removeElementAt(29);
							diaModel.removeElementAt(28);
						}
						
					}
					
					if(diaModel.getSize() == 30) {
						if(cbxDia.getSelectedIndex() + 1 < 30) {
							diaModel.removeElementAt(29);
							diaModel.removeElementAt(28);
						} else {
							cbxDia.setSelectedIndex(27);
							diaModel.removeElementAt(29);
							diaModel.removeElementAt(28);
						}
					}
					
				}
			}
		});

		cbxMes.setModel(new DefaultComboBoxModel(new String[] {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"}));
		cbxMes.setBounds(380, 50, 130, 25);
		panel.add(cbxMes);
		

		if(cbxMes.getSelectedIndex() == 0 || cbxMes.getSelectedIndex() == 2 || cbxMes.getSelectedIndex() == 4 || cbxMes.getSelectedIndex() == 6 || cbxMes.getSelectedIndex() == 7 || cbxMes.getSelectedIndex() == 9 || cbxMes.getSelectedIndex() == 11) {
			for(int i = 1; i <= 31; i++) {
				diaModel.addElement(i);
			}
		}
		
		if(cbxMes.getSelectedIndex() == 3 || cbxMes.getSelectedIndex() == 5 || cbxMes.getSelectedIndex() == 8 || cbxMes.getSelectedIndex() == 10) {
			for(int i = 1; i <= 30; i++) {
				diaModel.addElement(i);
			}
		}
		
		if(cbxMes.getSelectedIndex() == 1) {
			for(int i = 1; i <= 28; i++) {
				diaModel.addElement(i);
			}
		}
		

		cbxDia.setModel(diaModel);
		cbxDia.setBounds(320, 50, 50, 25);
		panel.add(cbxDia);
		
		DefaultComboBoxModel annoModel  = new  DefaultComboBoxModel();
		int index = -1;
		for(int i = Calendar.getInstance().get(Calendar.YEAR) - 100; i <= Calendar.getInstance().get(Calendar.YEAR); i++) {
			annoModel.addElement(i);
			index++;
		}
		cbxAnno = new JComboBox();
		cbxAnno.setModel(annoModel);
		cbxAnno.setSelectedIndex(index);
		cbxAnno.setBounds(520, 50, 120, 25);
		panel.add(cbxAnno);
		
		txtFechaCita = new JTextField();
		txtFechaCita.setEditable(false);
		txtFechaCita.setBounds(10, 386, 285, 25);
		txtFechaCita.setText( + Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + "/" + Calendar.getInstance().get(Calendar.MONTH) + "/" + Calendar.getInstance().get(Calendar.YEAR));
		panel.add(txtFechaCita);
		txtFechaCita.setColumns(10);
		
		
		DefaultComboBoxModel medicoModel = new DefaultComboBoxModel();
		for(Usuario user : Clinica.getInstance().misMedicos()) {
			medicoModel.addElement(user.getNombre());
		}
		cbxMedico = new JComboBox();
		cbxMedico.setModel(medicoModel);
		cbxMedico.setBounds(320, 260, 320, 25);
		panel.add(cbxMedico);
		
		cbxSexo = new JComboBox();
		cbxSexo.setModel(new DefaultComboBoxModel(new String[] {"Masculino", "Femenino"}));
		cbxSexo.setBounds(10, 190, 285, 25);
		panel.add(cbxSexo);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setBounds(550, 461, 90, 25);
		panel.add(btnSalir);
		
		JButton btnCrear = new JButton("Crear");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cita newCita = new Cita(Clinica.getInstance().GenerateCitaCode());
				Calendar tempDate = Calendar.getInstance();
				Calendar tempDate2 = Calendar.getInstance();
				tempDate.set((int)cbxAnno.getSelectedItem(), cbxMes.getSelectedIndex()+1, (int)cbxDia.getSelectedItem());
				tempDate2.set((int)cbxAnno_1.getSelectedItem(), cbxMes_1.getSelectedIndex()+1, (int)cbxDia_1.getSelectedItem(), cbxHora.getSelectedIndex(), cbxMinuto.getSelectedIndex()+1);
				newCita.setNombre(txtName.getText());
				newCita.setCedula(txtCedula.getText());
				newCita.setGenero(cbxSexo.getSelectedItem().toString());
				newCita.setTelefono(txtTelefono.getText());
				newCita.setDireccion(txtDireccion.getText());
				newCita.setFechaNacimiento(tempDate);
				newCita.setFechaCita(Calendar.getInstance());
				newCita.setEspecialidad(txtEspecialidad.getText());
				newCita.setFinalidad(txaFinalidad.getText());
				newCita.setMedico(Clinica.getInstance().SearchMedicoByName(cbxMedico.getSelectedItem().toString()));
				JOptionPane.showMessageDialog(panel, "Se ha creado una cita correctamente", "Cita completada", JOptionPane.INFORMATION_MESSAGE);
				
				Clinica.getInstance().getMisCitas().add(newCita);
				if(option == 1) {
					Clinica.getInstance().getMisPacientes().get(Clinica.getInstance().buscarPacienteIndex(Clinica.getInstance().getSelectedPaciente())).getHistorial().getMisCitas().add(newCita);
				}
				Clinica.getInstance().setCitaCodeGenerator(Clinica.getInstance().getCitaCodeGenerator() + 1);
				Clinica.getInstance().guardarClinica();


				dispose();
			}
		});
		btnCrear.setBounds(445, 461, 90, 25);
		panel.add(btnCrear);
		
		JLabel lblFechaDeCita = new JLabel("Fecha de cita programada:");
		lblFechaDeCita.setBounds(320, 100, 190, 14);
		panel.add(lblFechaDeCita);
		

		
		cbxDia_1 = new JComboBox();
		cbxDia_1.setBounds(320, 120, 37, 25);
		panel.add(cbxDia_1);
		
		cbxMes_1 = new JComboBox();
		cbxMes_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(cbxMes_1.getSelectedIndex() == 0 || cbxMes_1.getSelectedIndex() == 2 || cbxMes_1.getSelectedIndex() == 4 || cbxMes_1.getSelectedIndex() == 6 || cbxMes_1.getSelectedIndex() == 7 || cbxMes_1.getSelectedIndex() == 9 || cbxMes_1.getSelectedIndex() == 11) {
					if(diaModel_1.getSize() == 30) {
						diaModel.addElement(31);
					}
					if(diaModel.getSize() == 28) {
						diaModel.addElement(29);
						diaModel.addElement(30);
						diaModel.addElement(31);
					}
				}
				
				if(cbxMes_1.getSelectedIndex() == 3 || cbxMes_1.getSelectedIndex() == 5 || cbxMes_1.getSelectedIndex() == 8 || cbxMes_1.getSelectedIndex() == 10) {
					if(diaModel.getSize() == 31) {
						if(cbxDia_1.getSelectedIndex()+1 < 31) {
							diaModel_1.removeElementAt(30);
						}else {
							cbxDia_1.setSelectedIndex(29);
							diaModel_1.removeElementAt(30);
						}
					}
					
					if(diaModel_1.getSize() == 28) {
						diaModel_1.addElement(30);
						diaModel_1.addElement(31);
					}
					

					
				}
				
				if(cbxMes_1.getSelectedIndex() == 1) {
					if(diaModel_1.getSize() == 31) {
						if(cbxDia_1.getSelectedIndex() + 1 < 30) {
							diaModel_1.removeElementAt(30);
							diaModel_1.removeElementAt(29);
							diaModel_1.removeElementAt(28);
						} else {
							cbxDia_1.setSelectedIndex(27);
							diaModel_1.removeElementAt(30);
							diaModel_1.removeElementAt(29);
							diaModel_1.removeElementAt(28);
						}
						
					}
					
					if(diaModel_1.getSize() == 30) {
						if(cbxDia_1.getSelectedIndex() + 1 < 30) {
							diaModel_1.removeElementAt(29);
							diaModel_1.removeElementAt(28);
						} else {
							cbxDia_1.setSelectedIndex(27);
							diaModel_1.removeElementAt(29);
							diaModel_1.removeElementAt(28);
						}
					}
					
				}
			}
		});
		cbxMes_1.setModel(new DefaultComboBoxModel(new String[] {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"}));
		cbxMes_1.setBounds(367, 120, 87, 25);
		panel.add(cbxMes_1);
		
		cbxAnno_1 = new JComboBox();
		cbxAnno_1.setSelectedIndex(-1);
		cbxAnno_1.setBounds(464, 120, 68, 25);
		panel.add(cbxAnno_1);
		
		if(cbxMes_1.getSelectedIndex() == 0 || cbxMes_1.getSelectedIndex() == 2 || cbxMes_1.getSelectedIndex() == 4 || cbxMes_1.getSelectedIndex() == 6 || cbxMes_1.getSelectedIndex() == 7 || cbxMes_1.getSelectedIndex() == 9 || cbxMes_1.getSelectedIndex() == 11) {
			for(int i = 1; i <= 31; i++) {
				diaModel_1.addElement(i);
			}
		}
		
		if(cbxMes_1.getSelectedIndex() == 3 || cbxMes_1.getSelectedIndex() == 5 || cbxMes_1.getSelectedIndex() == 8 || cbxMes_1.getSelectedIndex() == 10) {
			for(int i = 1; i <= 30; i++) {
				diaModel_1.addElement(i);
			}
		}
		
		if(cbxMes_1.getSelectedIndex() == 1) {
			for(int i = 1; i <= 28; i++) {
				diaModel_1.addElement(i);
			}
		}
		
		DefaultComboBoxModel annoModel_1  = new  DefaultComboBoxModel();
		int index1 = -1;
		for(int i = Calendar.getInstance().get(Calendar.YEAR); i <= Calendar.getInstance().get(Calendar.YEAR)+5; i++) {
			annoModel_1.addElement(i);
		}
		
		cbxDia_1.setModel(diaModel_1);
		cbxAnno_1.setModel(annoModel_1);
		cbxAnno_1.setSelectedIndex(0);
		
		cbxHora = new JComboBox();
		cbxHora.setBounds(542, 120, 44, 25);
		panel.add(cbxHora);
		
		cbxMinuto = new JComboBox();
		cbxMinuto.setBounds(596, 120, 44, 25);
		panel.add(cbxMinuto);
		
		DefaultComboBoxModel horaModel = new DefaultComboBoxModel();
		DefaultComboBoxModel minutoModel = new DefaultComboBoxModel();
		String temp;
		
		for(int i = 0; i <= 24; i++) {
			if(i < 10) {
				temp = "0"+i;
			}else {
				temp = ""+i;	
			}
			
			horaModel.addElement(temp);
		}
		
		for(int i = 0; i < 60; i++) {
			if(i < 10) {
				temp = "0"+i;
			}else {
				temp = ""+i;
			}
			
			minutoModel.addElement(temp);
		}
		
		cbxHora.setModel(horaModel);
		cbxMinuto.setModel(minutoModel);
		
		JLabel lblNewLabel = new JLabel(":");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(588, 114, 37, 35);
		panel.add(lblNewLabel);
		
		if(option == 1) {
			llenarDatosPaciente();
		}

	}
	
	public void llenarDatosPaciente() {
		int genderIndex = 0;
		txtName.setText(Clinica.getInstance().getSelectedPaciente().getNombre());
		txtCedula.setText(Clinica.getInstance().getSelectedPaciente().getCedula());
		if(Clinica.getInstance().getSelectedPaciente().getGenero() == "Femenino") {
			genderIndex = 1;
		}
		cbxSexo.setSelectedIndex(genderIndex);
		txtTelefono.setText(Clinica.getInstance().getSelectedPaciente().getTelefono());
		txtDireccion.setText(Clinica.getInstance().getSelectedPaciente().getDireccion());
		cbxDia.setSelectedItem(Clinica.getInstance().getSelectedPaciente().getFechaNacimiento().get(Calendar.DAY_OF_MONTH));
		cbxMes.setSelectedItem(Clinica.getInstance().getSelectedPaciente().getFechaNacimiento().get(Calendar.MONTH));
		cbxAnno.setSelectedItem(Clinica.getInstance().getSelectedPaciente().getFechaNacimiento().get(Calendar.YEAR));
		
		txtName.setEnabled(false);
		txtCedula.setEnabled(false);
		cbxSexo.setEnabled(false);
		txtTelefono.setEnabled(false);
		txtDireccion.setEnabled(false);
		cbxDia.setEnabled(false);
		cbxMes.setEnabled(false);
		cbxAnno.setEnabled(false);
	}
}
