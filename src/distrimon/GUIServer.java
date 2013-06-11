package distrimon;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;

import comunicacao.Cliente;

/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class GUIServer extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel jPconfig;
	private JScrollPane jScrollPane2;
	private JComboBox<String> jCBmecanismo;
	private JList<String> jLclientes;
	private static JTextArea jTAlog;
	private JScrollPane jScrollPane1;
	private JPanel jPclientes;
	private JPanel jPlog;
	private JButton jBiniciar;
	private JTextField jTFporta;
	private JLabel jLabel1;
	private static DefaultListModel<String> jLclientesModel;

	static DistriServer server;
	static ArrayList<Cliente> jogadoresBatalhando;
	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				GUIServer inst = new GUIServer();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public GUIServer() {
		super();
		
		jogadoresBatalhando = new ArrayList<Cliente>();
		
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
				jPconfig = new JPanel();
				FlowLayout jPconfigLayout = new FlowLayout();
				getContentPane().add(jPconfig, BorderLayout.NORTH);
				jPconfig.setBorder(BorderFactory.createTitledBorder("Configurações"));
				jPconfig.setLayout(jPconfigLayout);
				{
					jLabel1 = new JLabel();
					jPconfig.add(jLabel1);
					jLabel1.setText("Porta:");
					jLabel1.setPreferredSize(new java.awt.Dimension(40, 24));
				}
				{
					jTFporta = new JTextField();
					jPconfig.add(jTFporta);
					jTFporta.setText("4545");
					jTFporta.setPreferredSize(new java.awt.Dimension(80, 24));
				}
				{
					DefaultComboBoxModel<String> jCBmecanismoModel= new DefaultComboBoxModel<String>(
							new String[] { "TCP", "UDP" });
					jCBmecanismo = new JComboBox<String>();
					jPconfig.add(jCBmecanismo);
					jCBmecanismo.setModel(jCBmecanismoModel);
					jCBmecanismo.setPreferredSize(new java.awt.Dimension(56, 24));
				}
				{
					jBiniciar = new JButton();
					jPconfig.add(jBiniciar);
					jBiniciar.setText("Iniciar Servidor");
					jBiniciar.setPreferredSize(new java.awt.Dimension(160, 24));
					jBiniciar.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							SwingWorker<Object, Void> worker = new SwingWorker<Object, Void>() {
								public Object doInBackground() {
									jLabel1.setEnabled(false);
									jTFporta.setEnabled(false);
									jCBmecanismo.setEnabled(false);
									jBiniciar.setEnabled(false);
									logger("Iniciando servidor...");
									if(jCBmecanismo.getSelectedIndex() == 0) {
										server = new DistriServer(Integer.parseInt(jTFporta.getText()), true);
									}else {
										server = new DistriServer(Integer.parseInt(jTFporta.getText()), false);
									}
									jTAlog.setEnabled(true);
									jLclientes.setEnabled(true);
									jBiniciar.setText("Servidor iniciado!");
									logger("Servidor iniciado!");
									
									server.aceitaConexao();
									return null;
								}
								public void done() {
								}
							};
							worker.execute();
						}
					});
				}
			}
			{
				jPlog = new JPanel();
				BorderLayout jPlogLayout = new BorderLayout();
				jPlog.setLayout(jPlogLayout);
				getContentPane().add(jPlog, BorderLayout.CENTER);
				jPlog.setBorder(BorderFactory.createTitledBorder("Log"));
				{
					jScrollPane1 = new JScrollPane();
					jPlog.add(jScrollPane1, BorderLayout.CENTER);
					{
						jTAlog = new JTextArea();
						jScrollPane1.setViewportView(jTAlog);
						jTAlog.setEditable(false);
						jTAlog.setEnabled(false);
					}
				}
			}
			{
				jPclientes = new JPanel();
				BorderLayout jPclientesLayout = new BorderLayout();
				jPclientes.setLayout(jPclientesLayout);
				getContentPane().add(jPclientes, BorderLayout.EAST);
				jPclientes.setBorder(BorderFactory.createTitledBorder("Jogadores conectados"));
				{
					jScrollPane2 = new JScrollPane();
					jPclientes.add(jScrollPane2, BorderLayout.CENTER);
					{
						jLclientes = new JList<String>();
						jScrollPane2.setViewportView(jLclientes);
						jLclientesModel = new DefaultListModel<String>();
						jLclientes.setModel(jLclientesModel);
						jLclientes.setPreferredSize(new java.awt.Dimension(128, 114));
						jLclientes.setEnabled(false);
					}
				}
			}
			pack();
			setSize(640, 480);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void logger(String msg) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		jTAlog.setText(sdf.format(Calendar.getInstance().getTime()) + " - " + msg + "\n" + jTAlog.getText());
	}
	
	public static void atualizarListaJogadores(ArrayList<String> nomesJogadores) {
		jLclientesModel.removeAllElements();
		
		for(int i = 0; i < nomesJogadores.size(); i++)
			jLclientesModel.addElement(nomesJogadores.get(i));
	}
	
	public static void verificarBatalha() {
		String nomeJogador1 = null;
		Cliente jogador1 = null;
		String nomeJogador2 = null;
		Cliente jogador2 = null;
		
		for(int i = 0; i < server.jogadores.size(); i++) {
			if(!jogadoresBatalhando.contains(server.jogadores.get(i))) {
				if(jogador1 == null) {
					nomeJogador1 = server.nomesJogadores.get(i);
					jogador1 = server.jogadores.get(i);
				}else if(jogador2 == null) {
					nomeJogador2 = server.nomesJogadores.get(i);
					jogador2 = server.jogadores.get(i);
					
					logger(nomeJogador1 + " e " + nomeJogador2 + " aptos a batalhar!");
					
					new Batalha(jogador1, jogador2, nomeJogador1, nomeJogador2);
					
					break;
				}
			}
		}
		
		
			
	}
}