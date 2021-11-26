package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logica.Clinica;
import logica.Medico;
import logica.Usuario;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConsultaList extends JFrame {

	private JPanel contentPane;
	private JTable table;

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
		setBounds(100, 100, 750, 516);
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
		panel_1.setBounds(10, 11, 714, 107);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		DefaultComboBoxModel<String> medicoModel = new DefaultComboBoxModel<String>();
		medicoModel.addElement("Todos");
		for(Usuario user : Clinica.getInstance().misMedicos()) {
			medicoModel.addElement(user.getNombre());
		}
		
		JComboBox<String> cbxMedico = new JComboBox<String>();
		cbxMedico.setBounds(10, 71, 300, 25);
		cbxMedico.setModel(medicoModel);
		panel_1.add(cbxMedico);
		
		int index = 0;
		int i = 0;
		if(Clinica.getInstance().getLogedUser() instanceof Medico) {
			for(Usuario user : Clinica.getInstance().misMedicos()) {
				if(user.getCodigo().equalsIgnoreCase(Clinica.getInstance().getLogedUser().getCodigo())) {
					index = i;
				}
				i++;
			}
			cbxMedico.setSelectedIndex(index);
			cbxMedico.setEnabled(false);
		}
		
		JLabel lblMedico = new JLabel("M\u00E9dico: ");
		lblMedico.setBounds(10, 51, 300, 14);
		panel_1.add(lblMedico);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 129, 714, 292);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setBounds(634, 441, 90, 25);
		panel.add(btnSalir);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(535, 441, 90, 25);
		panel.add(btnEliminar);
		
		JButton btnRevisar = new JButton("Revisar");
		btnRevisar.setBounds(436, 442, 89, 23);
		panel.add(btnRevisar);
	}
}
