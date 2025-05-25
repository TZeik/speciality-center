package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logica.Administrador;
import logica.Clinica;
import logica.Medico;
import logica.Secretario;
import logica.Usuario;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Window.Type;
import javax.swing.UIManager;
import java.awt.Color;

public class UserList extends JFrame {

	private JPanel contentPane;
	public static DefaultTableModel model;
	private JTable table;
	private static JComboBox cbxTipo;
	private static Object[] row;
	private Usuario selected = null;
	private JButton btnEliminar;
	private JButton btnEditar;
	private JButton btnRevisar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserList frame = new UserList();
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
	public UserList() {
		setTitle("Lista de usuarios");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 659, 434);
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
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Filtros", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(10, 11, 614, 96);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tipo de usuario: ");
		lblNewLabel.setBounds(10, 35, 300, 14);
		panel_1.add(lblNewLabel);
		
		cbxTipo = new JComboBox();
		cbxTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int aux= cbxTipo.getSelectedIndex();
				loadUserTable(aux);
			}
		});
		cbxTipo.setModel(new DefaultComboBoxModel(new String[] {"Todos", "Administrador", "M\u00E9dico", "Secretario"}));
		cbxTipo.setBounds(10, 60, 300, 25);
		panel_1.add(cbxTipo);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 118, 614, 222);
		panel.add(scrollPane);
		
		table = new JTable();
		table.getTableHeader().setReorderingAllowed(false);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int select = table.getSelectedRow();
				if(select != -1) {
					selected = Clinica.getInstance().SearchUsuarioCode((String)table.getValueAt(select, 0));
					btnEliminar.setEnabled(true);
					btnEditar.setEnabled(true);
					btnRevisar.setEnabled(true);
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
		String[] headers = {"Codigo","Nombre","ID","Contraseña","Tipo de usuario"};
		model.setColumnIdentifiers(headers);
		table.setModel(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Salir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBounds(534, 351, 90, 25);
		panel.add(btnNewButton);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow()>=0) {
					if (JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar a " + selected.getNombre() + "?", "Eliminar usuario", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						Clinica.getInstance().getMisUsuarios().remove(selected);
						Clinica.getInstance().guardarClinica();
						loadUserTable(0);
						botonesDef();
					}else {
						botonesDef();
					}
					
				}
			}
		});
		btnEliminar.setEnabled(false);
		btnEliminar.setBounds(434, 352, 90, 25);
		panel.add(btnEliminar);
		
		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				regUser edit = new regUser(selected);
				edit.setVisible(true);
			}
		});
		btnEditar.setEnabled(false);
		btnEditar.setBounds(334, 352, 90, 25);
		panel.add(btnEditar);
		
		btnRevisar = new JButton("Revisar");
		btnRevisar.setEnabled(false);
		btnRevisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRevisar.setBounds(234, 352, 90, 25);
		panel.add(btnRevisar);
		
		loadUserTable(0);
	}
	
	public static void loadUserTable(int selection) {
		model.setRowCount(0);
		row = new Object[model.getColumnCount()];
		switch (selection) {
		case 0:
			for (Usuario user : Clinica.getInstance().getMisUsuarios()) {
				row[0] = user.getCodigo();
				row[1] = user.getNombre();
				row[2] = user.getId();
				row[3] = user.getPassword();
				if (user instanceof Administrador) {row[4] = "Administrador";}
				if (user instanceof Medico) {row[4] = "Medico";}
				if (user  instanceof Secretario) {row[4] = "Secretario";}
				model.addRow(row);
			}
			break;
		case 1:
			for (Usuario user : Clinica.getInstance().tipoEspecifico(0)){
				if(user instanceof Administrador) {
					row[0] = user.getCodigo();
					row[1] = user.getNombre();
					row[2] = user.getId();
					row[3] = user.getPassword();
					if (user instanceof Administrador) {row[4] = "Administrador";}
					if (user instanceof Medico) {row[4] = "Medico";}
					if (user  instanceof Secretario) {row[4] = "Secretario";}
					model.addRow(row);
				}	
			}
			break;
		case 2:
			for (Usuario user : Clinica.getInstance().tipoEspecifico(1)){
				if(user instanceof Medico) {
					row[0] = user.getCodigo();
					row[1] = user.getNombre();
					row[2] = user.getId();
					row[3] = user.getPassword();
					row[3] = user.getPassword();
					if (user instanceof Administrador) {row[4] = "Administrador";}
					if (user instanceof Medico) {row[4] = "Medico";}
					if (user  instanceof Secretario) {row[4] = "Secretario";}
					model.addRow(row);
					
				}

			}
			break;
		case 3:
			for (Usuario user : Clinica.getInstance().tipoEspecifico(2)){
				if(user instanceof Secretario) {
					row[0] = user.getCodigo();
					row[1] = user.getNombre();
					row[2] = user.getId();
					row[3] = user.getPassword();
					row[3] = user.getPassword();
					if (user instanceof Administrador) {row[4] = "Administrador";}
					if (user instanceof Medico) {row[4] = "Medico";}
					if (user  instanceof Secretario) {row[4] = "Secretario";}
					model.addRow(row);
				}
				
			}
			break;

		default:
			break;
		}
		

	}
	
	private void botonesDef() {
		btnEliminar.setEnabled(false);
		btnEditar.setEnabled(false);
		btnRevisar.setEnabled(false);
	}
}


