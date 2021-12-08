package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logica.Consulta;
import logica.Paciente;
import logica.Vacuna;

import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;

public class RevHistorial extends JFrame {

	private JPanel contentPane;
	private JTable consultaTable;
	private JTable vacunaTable;
	public Paciente miPaciente;
	public static Object[] row;
	public static DefaultTableModel consultaModel;
	public static DefaultTableModel vacunaModel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					RevHistorial frame = new RevHistorial(null);
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
	public RevHistorial(Paciente pac) {
		miPaciente = pac;
		setResizable(false);
		setEnabled(false);
		setTitle("Revisar historial");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 702, 555);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Historial de consultas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 11, 411, 449);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 23, 391, 415);
		panel_1.add(scrollPane);
		

		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Vacunas recibidas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(431, 11, 245, 449);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 23, 225, 415);
		panel_2.add(scrollPane_1);
		
		consultaModel = new DefaultTableModel() {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		
		String[] consultaHeaders = {"Enfermedad", "Medico", "Fecha"};
		consultaModel.setColumnIdentifiers(consultaHeaders);
		
		vacunaModel = new DefaultTableModel() {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		
		String[] vacunaHeaders = {"Vacuna","Fecha"}; 
		vacunaModel.setColumnIdentifiers(vacunaHeaders);
		
		consultaTable = new JTable();
		consultaTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		consultaTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		consultaTable.setModel(consultaModel);
		scrollPane.setViewportView(consultaTable);
		
		vacunaTable = new JTable();
		vacunaTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		vacunaTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		vacunaTable.setModel(vacunaModel);
		scrollPane_1.setViewportView(vacunaTable);
		
		JButton btnNewButton = new JButton("Salir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBounds(570, 474, 106, 31);
		panel.add(btnNewButton);
		
		loadConsultaTable();
		loadVacunaTable();
	}
	
	private void loadConsultaTable() {
		consultaModel.setRowCount(0);
		row = new Object[consultaModel.getColumnCount()];
		String fecha;
		
		for(Consulta cons : miPaciente.getHistorial().getMisConsultas()) {
			row[0] = cons.getEnfermedad().getNombre();
			row[1] = cons.getMiMedico().getNombre();
			fecha = cons.getFecha().get(Calendar.DAY_OF_MONTH) + "/" + cons.getFecha().get(Calendar.MONTH) + "/" + cons.getFecha().get(Calendar.YEAR);
			row[2] = fecha;
			
			consultaModel.addRow(row);
		}
	}
	
	private void loadVacunaTable() {
		vacunaModel.setRowCount(0);
		row = new Object[vacunaModel.getColumnCount()];
		String fecha;
		
		for(Vacuna vac : miPaciente.getHistorial().getMisVacunas()) {
			row[0] = vac.getNombre();
			fecha  = vac.getFechaVacunacion().get(Calendar.DAY_OF_MONTH) + "/" + vac.getFechaVacunacion().get(Calendar.MONTH) + "/" + vac.getFechaVacunacion().get(Calendar.YEAR);
			row[1] = fecha;
			
			vacunaModel.addRow(row);
		}
	}
}
