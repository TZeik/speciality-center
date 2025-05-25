package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import logica.Clinica;
import logica.Enfermedad;
import logica.Vacuna;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VacunaList extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private static JTextField txtBuscar;
	private JButton btnEliminar;
	private JButton btnEditar;
	public static DefaultTableModel model;
	private static Object[] row;
	private Vacuna selected = null;
	private TableRowSorter trs = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VacunaList frame = new VacunaList();
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
	public VacunaList() {
		setTitle("Lista de vacunas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 573, 469);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 108, 527, 269);
		panel.add(scrollPane);
		
		btnEliminar = new JButton("Elminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow()>=0) {
					if(JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar " + selected.getNombre() + "?", "Vacunas", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						Clinica.getInstance().getMisVacunas().remove(selected);
						Clinica.getInstance().guardarClinica();
						loadVacTable("");
						botonesDef();
						} else {
							botonesDef();
					}
					
				}
			}
		});
		btnEliminar.setEnabled(false);
		btnEliminar.setBounds(348, 386, 90, 25);
		panel.add(btnEliminar);
		
		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegVacuna edit = new RegVacuna(selected);
				edit.setVisible(true);
			}
		});
		btnEditar.setEnabled(false);
		btnEditar.setBounds(248, 386, 90, 25);
		panel.add(btnEditar);
		
		table = new JTable();
		table.getTableHeader().setReorderingAllowed(false);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int select = table.getSelectedRow();
				if(select != -1) {
					selected = Clinica.getInstance().SearchVacuna((String)table.getValueAt(select, 0));
					btnEliminar.setEnabled(true);
					btnEditar.setEnabled(true);
				}
			}
		});
		model = new DefaultTableModel(){
		    @Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		String[] headers = {"Codigo","Nombre","Tipo"};
		model.setColumnIdentifiers(headers);
		table.setModel(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Filtros", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 11, 527, 86);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		txtBuscar = new JTextField();
		txtBuscar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				trs.setRowFilter(RowFilter.regexFilter("(?i)"+txtBuscar.getText(), 1));
			}
		});
		trs = new TableRowSorter(model);
		table.setRowSorter(trs);
		txtBuscar.setBounds(10, 50, 360, 25);
		panel_1.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Buscar vacuna: ");
		lblNewLabel.setBounds(10, 25, 360, 14);
		panel_1.add(lblNewLabel);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setBounds(448, 386, 90, 25);
		panel.add(btnSalir);
	
		loadVacTable(null);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				loadVacTable(null);
			}
		});
	}
	
	public static void loadVacTable(String search) {
		txtBuscar.setText("");
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		
			for (Vacuna vac : Clinica.getInstance().getMisVacunas()) {
				row[0] = vac.getCodigo();
				row[1] = vac.getNombre();
				row[2] = vac.getAnnoCreacion();
				model.addRow(row);
			}

	}
	private void botonesDef() {
		btnEliminar.setEnabled(false);
		btnEditar.setEnabled(false);
	}
}
