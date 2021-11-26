package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logica.Cita;
import logica.Clinica;
import logica.Enfermedad;
import logica.Medico;
import logica.Usuario;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class CitaList extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private static JTextField txtBuscar;
	public static DefaultTableModel model;
	private static Object[] row;
	private Cita selected = null;
	public int select;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CitaList frame = new CitaList(1);
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
	public CitaList(int opcion) {
		setTitle("Lista de citas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 807, 474);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JTextArea txaFinalidad = new JTextArea();
		txaFinalidad.setLineWrap(true);
		txaFinalidad.setEditable(false);
		txaFinalidad.setBounds(569, 120, 202, 250);
		panel.add(txaFinalidad);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 100, 549, 270);
		panel.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				select = table.getSelectedRow();
				if(select != -1) {
					selected = Clinica.getInstance().SearchCita((String)table.getValueAt(select, 0));
					txaFinalidad.setText(selected.getFinalidad());
					
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
		
		String[] headers = {"Codigo","Nombre","Cedula","Medico"};
		model.setColumnIdentifiers(headers);
		table.setModel(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Filtros", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 11, 761, 78);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(10, 40, 431, 25);
		panel_1.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		JLabel lblBuscarCita = new JLabel("Buscar cita: ");
		lblBuscarCita.setBounds(10, 20, 390, 14);
		panel_1.add(lblBuscarCita);
		
		DefaultComboBoxModel medicoModel = new DefaultComboBoxModel();
		medicoModel.addElement("Todos");
		for(Usuario user : Clinica.getInstance().misMedicos()) {
			medicoModel.addElement(user.getNombre());
		}
		JComboBox cbxMedico = new JComboBox();
		cbxMedico.setModel(medicoModel);
		cbxMedico.setBounds(451, 40, 300, 25);
		panel_1.add(cbxMedico);
		
		
		if(Clinica.getInstance().getLogedUser() instanceof Medico) {
			cbxMedico.setSelectedIndex(Clinica.getInstance().GetLogedMedicoIndex());
			cbxMedico.setFont(new Font("Tahoma", Font.BOLD, 11));
			cbxMedico.setEnabled(false);
		}
		
		JLabel lblNewLabel = new JLabel("M\u00E9dico: ");
		lblNewLabel.setBounds(451, 20, 300, 14);
		panel_1.add(lblNewLabel);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setBounds(681, 389, 90, 25);
		panel.add(btnSalir);
		
		JButton btnDynamic = new JButton("<dynamic>");
		if(opcion == 0) {
			btnDynamic.setText("Revisar");
		}
		if(opcion == 1) {
			btnDynamic.setText("Seleccionar");
		}
		btnDynamic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(opcion == 0) {
					
				}
				if(opcion == 1) {
					
					Clinica.getInstance().setSelectedCita(Clinica.getInstance().SearchCita((String) table.getValueAt(select, 0)));
					dispose();
				}
			}
		});
		btnDynamic.setBounds(561, 389, 110, 25);
		panel.add(btnDynamic);
		
		JLabel lblFinalidad = new JLabel("Finalidad: ");
		lblFinalidad.setBounds(569, 100, 202, 14);
		panel.add(lblFinalidad);
		
		loadCitaTable(null);
	}
	
	public static void loadCitaTable(String search) {
		txtBuscar.setText("");
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		
		if(Clinica.getInstance().getLogedUser() instanceof Medico){
			for (Cita cita : Clinica.getInstance().SoloCitasMedico()) {
				row[0] = cita.getCodigo();
				row[1] = cita.getNombre();
				row[2] = cita.getCedula();
				row[3] = cita.getMedico().getNombre();
				model.addRow(row);
			}
		}else {
			for (Cita cita : Clinica.getInstance().getMisCitas()) {
				row[0] = cita.getCodigo();
				row[1] = cita.getNombre();
				row[2] = cita.getCedula();
				row[3] = cita.getMedico().getNombre();
				model.addRow(row);
			}
		}


	}
	
}
