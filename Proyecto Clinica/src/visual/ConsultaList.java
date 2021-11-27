package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logica.Clinica;
import logica.Consulta;
import logica.Medico;
import logica.Paciente;
import logica.Usuario;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.ConcurrentModificationException;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class ConsultaList extends JFrame {

	private JPanel contentPane;
	private JTable table;
	public static DefaultTableModel model;
	private static Object[] row;
	private Consulta selected = null;
	private static JComboBox<String> cbxPaciente;
	private static JComboBox<String> cbxMedico;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaList frame = new ConsultaList();
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
	public ConsultaList() {
		setTitle("Lista de consultas");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 688, 527);
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
		panel_1.setBorder(new TitledBorder(null, "Filtros", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 11, 637, 107);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		DefaultComboBoxModel<String> medicoModel = new DefaultComboBoxModel<String>();
		medicoModel.addElement("Todos");
		for(Usuario user : Clinica.getInstance().misMedicos()) {
			medicoModel.addElement(user.getNombre());
		}
		
		cbxMedico = new JComboBox<String>();
		cbxMedico.setBounds(320, 71, 300, 25);
		cbxMedico.setModel(medicoModel);
		panel_1.add(cbxMedico);
		
		if(Clinica.getInstance().getLogedUser() instanceof Medico) {
			cbxMedico.setSelectedIndex(Clinica.getInstance().GetLogedMedicoIndex());
			cbxMedico.setFont(new Font("Tahoma", Font.BOLD, 11));
			cbxMedico.setEnabled(false);
		}

		JLabel lblMedico = new JLabel("M\u00E9dico: ");
		lblMedico.setBounds(320, 51, 300, 14);
		panel_1.add(lblMedico);
		
		DefaultComboBoxModel<String> pacienteModel = new DefaultComboBoxModel<String>();
		pacienteModel.addElement("Todos");
		for(Paciente pac : Clinica.getInstance().getMisPacientes())
			pacienteModel.addElement(pac.getNombre());
		cbxPaciente = new JComboBox<String>();
		cbxPaciente.setModel(pacienteModel);
		cbxPaciente.setBounds(10, 71, 300, 25);
		panel_1.add(cbxPaciente);
		
		JLabel lblPaciente = new JLabel("Paciente: ");
		lblPaciente.setBounds(10, 51, 300, 14);
		panel_1.add(lblPaciente);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 129, 637, 292);
		panel.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int select = table.getSelectedRow();
				if(select != -1) {
					selected = Clinica.getInstance().SearchConsulta(table.getValueAt(select, 0).toString());
				}
			}
		});
		model = new DefaultTableModel() {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		
		String[] headers = {"Codigo","Paciente","Enfermedad","Medico","Fecha"};
		model.setColumnIdentifiers(headers);
		table.setModel(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setBounds(557, 441, 90, 25);
		panel.add(btnSalir);
		
		cbxPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadConsTable();
			}
		});
		
		cbxMedico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadConsTable();
			}
		});
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(panel, "¿Está seguro que desea eliminar la consulta " + selected.getCodigo() + "?", "Eliminar consulta", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					
					try {
						Clinica.getInstance().EliminarConsulta(selected.getCodigo());
					}catch(ConcurrentModificationException e1) {
						loadConsTable();
					}

					Clinica.getInstance().guardarClinica();
				}

			}
		});
		btnEliminar.setBounds(458, 441, 90, 25);
		panel.add(btnEliminar);
		
		JButton btnRevisar = new JButton("Revisar");
		btnRevisar.setBounds(359, 442, 89, 23);
		panel.add(btnRevisar);
		
		loadConsTable();
	}
	
	public static void loadConsTable() {
		int opcion = 0;
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		
		if(cbxPaciente.getSelectedIndex() == 0 && cbxMedico.getSelectedIndex() == 0) {
			opcion = 0;
		}
		if(cbxPaciente.getSelectedIndex() != 0 && cbxMedico.getSelectedIndex() == 0) {
			opcion = 1;
		}
		if(cbxPaciente.getSelectedIndex() == 0 && cbxMedico.getSelectedIndex() != 0) {
			opcion = 2;
		}
		if(cbxPaciente.getSelectedIndex() != 0 && cbxMedico.getSelectedIndex() != 0) {
			opcion = 3;
		}
		
		switch (opcion) {
		case 0:
			for(Consulta cons : Clinica.getInstance().misConsultas()) {
				
					row[0] = cons.getCodigo();
					row[1] = Clinica.getInstance().BuscarPacienteByConsultaCode(cons.getCodigo()).getNombre();
					row[2] = cons.getEnfermedad().getNombre();
					row[3] = cons.getMiMedico().getNombre();
					row[4] = cons.getFecha().get(Calendar.DAY_OF_MONTH) + "/" + cons.getFecha().get(Calendar.MONTH) + "/" + cons.getFecha().get(Calendar.YEAR);
					model.addRow(row);
			}
			break;
		case 1:
			for(Consulta cons : Clinica.getInstance().SearchPacienteByName(cbxPaciente.getSelectedItem().toString()).getHistorial().getMisConsultas()) {
				row[0] = cons.getCodigo();
				row[1] = cbxPaciente.getSelectedItem().toString();
				row[2] = cons.getEnfermedad().getNombre();
				row[3] = cons.getMiMedico().getNombre();
				row[4] = cons.getFecha().get(Calendar.DAY_OF_MONTH) + "/" + cons.getFecha().get(Calendar.MONTH) + "/" + cons.getFecha().get(Calendar.YEAR);
				model.addRow(row);
			}
			break;
		case 2:
			for (Paciente pac : Clinica.getInstance().getMisPacientes()) {	
				for(Consulta cons : pac.getHistorial().getMisConsultas()) {
					if(cons.getMiMedico().getNombre().equalsIgnoreCase(cbxMedico.getSelectedItem().toString())) {
						row[0] = cons.getCodigo();
						row[1] = pac.getNombre();
						row[2] = cons.getEnfermedad().getNombre();
						row[3] = cons.getMiMedico().getNombre();
						row[4] = cons.getFecha().get(Calendar.DAY_OF_MONTH) + "/" + cons.getFecha().get(Calendar.MONTH) + "/" + cons.getFecha().get(Calendar.YEAR);
						model.addRow(row);
					}
				}


			}
			break;
		case 3:
			for(Consulta cons : Clinica.getInstance().SearchPacienteByName(cbxPaciente.getSelectedItem().toString()).getHistorial().getMisConsultas()) {
				if(cons.getMiMedico().getNombre().equalsIgnoreCase(cbxMedico.getSelectedItem().toString())) {
					row[0] = cons.getCodigo();
					row[1] = cbxPaciente.getSelectedItem().toString();
					row[2] = cons.getEnfermedad().getNombre();
					row[3] = cons.getMiMedico().getNombre();
					row[4] = cons.getFecha().get(Calendar.DAY_OF_MONTH) + "/" + cons.getFecha().get(Calendar.MONTH) + "/" + cons.getFecha().get(Calendar.YEAR);
					model.addRow(row);
				}
			}
			break;

		default:
			break;
		}

	}
}
