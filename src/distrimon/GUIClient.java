package distrimon;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;

import configuracao.Configuracao;
import configuracao.MecanismoComunicacao;
import factory.ClienteFactory;

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
public class GUIClient extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel jPconfig;
	private JPanel jPcampoBatalha;
	private JTextArea jTAlog;
	private JScrollPane jScrollPane1;
	private JLabel jLadversario;
	private JProgressBar jPBdistrimon;
	private JLabel jLdistrimon;
	private JButton jBdefesa;
	private JButton jBespecial;
	private JButton jBchute;
	private JButton jBsoco;
	private JButton jBsquirtle;
	private JButton jBcharmander;
	private JButton jBbulbasaur;
	private JPanel jPlog;
	private JProgressBar jPBadversario;
	private JButton jBnenhum;
	private JButton jBmewtwo;
	private JButton jBpikachu;
	private JComboBox<String> jCBmecanismo;
	private JLabel jLabel5;
	private JLabel jLabel4;
	private JButton jBconectar;
	private JTextField jTFporta;
	private JLabel jLabel3;
	private JTextField jTFendereco;
	private JLabel jLabel2;
	private JTextField jTFjogador;
	private JLabel jLabel1;

	private ClienteFactory cliente;
	private boolean emBatalha;
	private boolean emEscolha;
	private boolean emPremio;
	
	private String gfx;
	
	ImageIcon distrimon;
	ImageIcon adversario;
	
	int qtdSoco;
	int qtdChute;
	int qtdEspecial;
	
	boolean ativarAcoes;
	
	boolean temPikachu;
	boolean temMewtwo;
	
	int vidaDtm;
	int vidaAdv;
	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				GUIClient inst = new GUIClient();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public GUIClient() {
		super();
		
		emBatalha = false;
		emEscolha = false;
		emPremio = false;
		ativarAcoes = false;
		
		gfx = "distrimon/gfx/";
		
		temPikachu = false;
		temMewtwo = false;
		
		vidaDtm = 100;
		vidaAdv = 100;
		
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
				jPconfig = new JPanel();
				getContentPane().add(jPconfig, BorderLayout.NORTH);
				jPconfig.setBorder(BorderFactory.createTitledBorder("Configuração"));
				{
					jLabel1 = new JLabel();
					jPconfig.add(jLabel1);
					jLabel1.setText("Nome do Jogador:");
					jLabel1.setPreferredSize(new java.awt.Dimension(104, 24));
				}
				{
					jTFjogador = new JTextField();
					jPconfig.add(jTFjogador);
					jTFjogador.setPreferredSize(new java.awt.Dimension(160, 24));
					jTFjogador.setText("Jogador " + (new Random().nextInt(10) + 1));
				}
				{
					jLabel2 = new JLabel();
					jPconfig.add(jLabel2);
					jLabel2.setText("Endereço:");
					jLabel2.setPreferredSize(new java.awt.Dimension(64, 24));
				}
				{
					jTFendereco = new JTextField();
					jPconfig.add(jTFendereco);
					jTFendereco.setText("localhost");
					jTFendereco.setPreferredSize(new java.awt.Dimension(64, 24));
				}
				{
					jLabel3 = new JLabel();
					jPconfig.add(jLabel3);
					jLabel3.setText("Porta:");
					jLabel3.setPreferredSize(new java.awt.Dimension(40, 24));
				}
				{
					jTFporta = new JTextField();
					jPconfig.add(jTFporta);
					jTFporta.setText("4545");
					jTFporta.setPreferredSize(new java.awt.Dimension(40, 24));
				}
				{
					DefaultComboBoxModel<String> jCBmecanismoModel = new DefaultComboBoxModel<String>(
							new String[] { "TCP", "UDP" });
					jCBmecanismo = new JComboBox<String>();
					jPconfig.add(jCBmecanismo);
					jCBmecanismo.setModel(jCBmecanismoModel);
					jCBmecanismo.setPreferredSize(new java.awt.Dimension(56, 24));
				}
				{
					jBconectar = new JButton();
					jPconfig.add(jBconectar);
					jBconectar.setText("Conectar");
					jBconectar.setPreferredSize(new java.awt.Dimension(160, 24));
					jBconectar.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							SwingWorker<Object, Void> worker = new SwingWorker<Object, Void>() {
								public Object doInBackground() {
									jLabel1.setEnabled(false);
									jTFjogador.setEnabled(false);
									jLabel2.setEnabled(false);
									jTFendereco.setEnabled(false);
									jLabel3.setEnabled(false);
									jTFporta.setEnabled(false);
									jCBmecanismo.setEnabled(false);
									jBconectar.setEnabled(false);
									
									conectar();
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
				File f = new File("src/" + gfx + "arena.png");
				BufferedImage img = ImageIO.read(f);
	             
				jPcampoBatalha = new BackgroundPanel(img);
				getContentPane().add(jPcampoBatalha, BorderLayout.CENTER);
			}
			{
				jPlog = new JPanel();
				BorderLayout jPlogLayout = new BorderLayout();
				jPlog.setLayout(jPlogLayout);
				getContentPane().add(jPlog, BorderLayout.SOUTH);
				jPlog.setBorder(BorderFactory.createTitledBorder("Log"));
				{
					jScrollPane1 = new JScrollPane();
					jPlog.add(jScrollPane1, BorderLayout.CENTER);
					{
						jTAlog = new JTextArea();
						jScrollPane1.setViewportView(jTAlog);
						jTAlog.setEditable(false);
						jTAlog.setEnabled(false);
						jTAlog.setPreferredSize(new java.awt.Dimension(771, 64));
					}
				}
			}
			pack();
			setSize(800, 600);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void desenharCampoBatalha() {
		jPcampoBatalha.removeAll();
		
		if(emEscolha) {
			jPcampoBatalha.setLayout(new FlowLayout());
			{
				jBbulbasaur = new JButton();
				jPcampoBatalha.add(jBbulbasaur);
				jBbulbasaur.setText("Bulbasaur");
				jBbulbasaur.setIcon(new ImageIcon(getClass().getClassLoader().getResource(gfx + "bulbasaur_front.png")));
				jBbulbasaur.setVerticalTextPosition(SwingConstants.BOTTOM);
				jBbulbasaur.setHorizontalTextPosition(SwingConstants.CENTER);
				jBbulbasaur.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							cliente.getCliente().envia("BULBASAUR");
							distrimon = new ImageIcon(getClass().getClassLoader().getResource(gfx + "bulbasaur_back.png"));
							logger("Distrimon escolhido: Bulbasaur");
							emEscolha = false;
							desenharCampoBatalha();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				});
			}
			{
				jBcharmander = new JButton();
				jPcampoBatalha.add(jBcharmander);
				jBcharmander.setText("Charmander");
				jBcharmander.setIcon(new ImageIcon(getClass().getClassLoader().getResource(gfx + "charmander_front.png")));
				jBcharmander.setHorizontalTextPosition(SwingConstants.CENTER);
				jBcharmander.setVerticalTextPosition(SwingConstants.BOTTOM);
				jBcharmander.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							cliente.getCliente().envia("CHARMANDER");
							distrimon = new ImageIcon(getClass().getClassLoader().getResource(gfx + "charmander_back.png"));
							logger("Distrimon escolhido: Charmander");
							emEscolha = false;
							desenharCampoBatalha();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				});
			}
			{
				jBsquirtle = new JButton();
				jPcampoBatalha.add(jBsquirtle);
				jBsquirtle.setText("Squirtle");
				jBsquirtle.setIcon(new ImageIcon(getClass().getClassLoader().getResource(gfx + "squirtle_front.png")));
				jBsquirtle.setHorizontalTextPosition(SwingConstants.CENTER);
				jBsquirtle.setVerticalTextPosition(SwingConstants.BOTTOM);
				jBsquirtle.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							cliente.getCliente().envia("SQUIRTLE");
							distrimon = new ImageIcon(getClass().getClassLoader().getResource(gfx + "squirtle_back.png"));
							logger("Distrimon escolhido: Squirtle");
							emEscolha = false;
							desenharCampoBatalha();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				});
			}
			{
				if(temPikachu) {
					jBpikachu = new JButton();
					jPcampoBatalha.add(jBpikachu);
					jBpikachu.setText("Pikachu");
					jBpikachu.setIcon(new ImageIcon(getClass().getClassLoader().getResource(gfx + "pikachu_front.png")));
					jBpikachu.setVerticalTextPosition(SwingConstants.BOTTOM);
					jBpikachu.setHorizontalTextPosition(SwingConstants.CENTER);
					jBpikachu.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							try {
								cliente.getCliente().envia("PIKACHU");
								distrimon = new ImageIcon(getClass().getClassLoader().getResource(gfx + "pikachu_front.png"));
								logger("Distrimon escolhido: Pikachu");
								emEscolha = false;
								desenharCampoBatalha();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}
					});
				}
			}
			{
				if(temMewtwo) {
					jBmewtwo = new JButton();
					jPcampoBatalha.add(jBmewtwo);
					jBmewtwo.setText("Mewtwo");
					jBmewtwo.setIcon(new ImageIcon(getClass().getClassLoader().getResource(gfx + "mewtwo_front.png")));
					jBmewtwo.setHorizontalTextPosition(SwingConstants.CENTER);
					jBmewtwo.setVerticalTextPosition(SwingConstants.BOTTOM);
					jBmewtwo.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							try {
								cliente.getCliente().envia("MEWTWO");
								distrimon = new ImageIcon(getClass().getClassLoader().getResource(gfx + "mewtwo_front.png"));
								logger("Distrimon escolhido: Mewtwo");
								emEscolha = false;
								desenharCampoBatalha();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}
					});
				}
			}
		}else if(emBatalha) {
			jPcampoBatalha.setLayout(null);
			{
				jLadversario = new JLabel();
				jPcampoBatalha.add(jLadversario);
				jLadversario.setIcon(adversario);
				jLadversario.setBounds(632, 48, 80, 80);
			}
			{
				jPBadversario = new JProgressBar();
				jPcampoBatalha.add(jPBadversario);
				jPBadversario.setBounds(192, 80, 392, 24);
				jPBadversario.setMaximum(100);
				jPBadversario.setValue(vidaAdv);
				jPBadversario.setIndeterminate(false);
			}
			{
				jLabel4 = new JLabel();
				jPcampoBatalha.add(jLabel4);
				jLabel4.setText("HP:");
				jLabel4.setBounds(560, 56, 24, 24);
			}
			{
				jLdistrimon = new JLabel();
				jPcampoBatalha.add(jLdistrimon);
				jLdistrimon.setIcon(distrimon);
				jLdistrimon.setBounds(64, 264, 80, 80);
			}
			{
				jPBdistrimon = new JProgressBar();
				jPcampoBatalha.add(jPBdistrimon);
				jPBdistrimon.setBounds(192, 298, 392, 24);
				jPBdistrimon.setMaximum(100);
				jPBdistrimon.setValue(vidaDtm);
				jPBdistrimon.setIndeterminate(false);
			}
			{
				jLabel5 = new JLabel();
				jPcampoBatalha.add(jLabel5);
				jLabel5.setText("HP:");
				jLabel5.setBounds(192, 272, 24, 24);
			}
			{
				jBsoco = new JButton();
				jPcampoBatalha.add(jBsoco);
				jBsoco.setText("Soco (" + qtdSoco + "/10)");
				jBsoco.setBounds(32, 360, 160, 40);
				if(ativarAcoes)
					jBsoco.setEnabled(true);
				else
					jBsoco.setEnabled(false);
				if(qtdSoco <= 0)
					jBsoco.setEnabled(false);
				jBsoco.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							cliente.getCliente().envia("SOCO");
							qtdSoco--;
							logger("Ataque SOCO selecionado!");
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				});
			}
			{
				jBchute = new JButton();
				jPcampoBatalha.add(jBchute);
				jBchute.setText("Chute (" + qtdChute + "/10)");
				jBchute.setBounds(216, 360, 160, 40);
				if(ativarAcoes)
					jBchute.setEnabled(true);
				else
					jBchute.setEnabled(false);
				if(qtdChute <= 0)
					jBchute.setEnabled(false);
				jBchute.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							cliente.getCliente().envia("CHUTE");
							qtdChute--;
							logger("Ataque CHUTE selecionado!");
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				});
			}
			{
				jBespecial = new JButton();
				jPcampoBatalha.add(jBespecial);
				jBespecial.setText("Especial (" + qtdEspecial + "/5)");
				jBespecial.setBounds(400, 360, 160, 40);
				if(ativarAcoes)
					jBespecial.setEnabled(true);
				else
					jBespecial.setEnabled(false);
				if(qtdEspecial <= 0)
					jBespecial.setEnabled(false);
				jBespecial.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							cliente.getCliente().envia("ESPECIAL");
							qtdEspecial--;
							logger("Ataque ESPECIAL selecionado!");
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				});
			}
			{
				jBdefesa = new JButton();
				jPcampoBatalha.add(jBdefesa);
				jBdefesa.setText("Defesa");
				jBdefesa.setBounds(584, 360, 160, 40);
				if(ativarAcoes)
					jBdefesa.setEnabled(true);
				else
					jBdefesa.setEnabled(false);
				jBdefesa.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							cliente.getCliente().envia("DEFESA");
							logger("DEFESA selecionada!");
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				});
			}
		}else if(emPremio) {
			jPcampoBatalha.setLayout(new FlowLayout());
			{
				if(!temPikachu) {
					jBpikachu = new JButton();
					jPcampoBatalha.add(jBpikachu);
					jBpikachu.setText("Pikachu");
					jBpikachu.setIcon(new ImageIcon(getClass().getClassLoader().getResource(gfx + "pikachu_front.png")));
					jBpikachu.setVerticalTextPosition(SwingConstants.BOTTOM);
					jBpikachu.setHorizontalTextPosition(SwingConstants.CENTER);
					jBpikachu.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							try {
								cliente.getCliente().envia("PIKACHU");
								logger("Prêmio escolhido: Pikachu");
								emPremio = false;
								temPikachu = true;
								desenharCampoBatalha();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}
					});
				}
			}
			{
				if(!temMewtwo) {
					jBmewtwo = new JButton();
					jPcampoBatalha.add(jBmewtwo);
					jBmewtwo.setText("Mewtwo");
					jBmewtwo.setIcon(new ImageIcon(getClass().getClassLoader().getResource(gfx + "mewtwo_front.png")));
					jBmewtwo.setHorizontalTextPosition(SwingConstants.CENTER);
					jBmewtwo.setVerticalTextPosition(SwingConstants.BOTTOM);
					jBmewtwo.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							try {
								cliente.getCliente().envia("MEWTWO");
								logger("Prêmio escolhido: Mewtwo");
								emPremio = false;
								desenharCampoBatalha();
								temMewtwo = true;
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}
					});
				}
			}
			{
				jBnenhum = new JButton();
				jPcampoBatalha.add(jBnenhum);
				jBnenhum.setText("Missingno.");
				jBnenhum.setIcon(new ImageIcon(getClass().getClassLoader().getResource(gfx + "missingno.png")));
				jBnenhum.setHorizontalTextPosition(SwingConstants.CENTER);
				jBnenhum.setVerticalTextPosition(SwingConstants.BOTTOM);
				jBnenhum.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							cliente.getCliente().envia("NADA");
							logger("Prêmio escolhido: Nada!");
							emPremio = false;
							desenharCampoBatalha();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				});
			}
		}
		
		jPcampoBatalha.repaint();
	}

	private void logger(String msg) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		jTAlog.setText(sdf.format(Calendar.getInstance().getTime()) + ": " + msg + "\n" + jTAlog.getText());
	}
	
	private void conectar() {
		try {
			cliente = new ClienteFactory();
			if(jCBmecanismo.getSelectedIndex() == 0) {
				Configuracao.ConfiguracoaAtual().setMecanismoComunicacao(MecanismoComunicacao.TCP);
			}else {
				Configuracao.ConfiguracoaAtual().setMecanismoComunicacao(MecanismoComunicacao.UDP);
			}
			
			logger("Iniciando conexão...");
			cliente.conectar(InetAddress.getByName(jTFendereco.getText()), Integer.parseInt(jTFporta.getText()));
			jTAlog.setEnabled(true);
			logger("Validando jogador...");
			cliente.getCliente().envia(jTFjogador.getText());
			
			conversador();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void conversador() {
		String msg;
		try {
			msg = cliente.getCliente().recebe();
			
			if(msg.equals("BEMVINDO")) {
				logger("Conexão bem sucedida!");
				conversador();
			}else if(msg.equals("OUTRONOME")) {
				logger("Conexão recusada!");
				logger("Nome de usuário indisponível, escolha outro!");
				jLabel1.setEnabled(true);
				jTFjogador.setEnabled(true);
				jLabel2.setEnabled(true);
				jTFendereco.setEnabled(true);
				jLabel3.setEnabled(true);
				jTFporta.setEnabled(true);
				jBconectar.setEnabled(true);
			}else if(msg.equals("AGUARDE")) {
				logger("Aguardando adversário...");
				conversador();
			}else if(msg.equals("BATALHA")) {
				logger("Adversário encontrado, batalha vai começar!");
				conversador();
			}else if(msg.equals("AGUARDEADVERSARIO")) {
				logger("Aguarde movimento do adversário...");
				conversador();
			}else if(msg.equals("ESCOLHER")) {
				logger("Escolha o seu Distrimon!");
				emEscolha = true;
				emBatalha = false;
				emPremio = false;
				qtdSoco = 10;
				qtdChute = 10;
				qtdEspecial = 5;
				
				vidaDtm = 100;
				vidaAdv = 100;
				
				desenharCampoBatalha();
				conversador();
			}else if(msg.equals("BULBASAUR")) {
				adversario = new ImageIcon(getClass().getClassLoader().getResource(gfx + "bulbasaur_front.png"));
				emEscolha = false;
				emBatalha = true;
				emPremio = false;
				desenharCampoBatalha();
				conversador();
			}else if(msg.equals("CHARMANDER")) {
				adversario = new ImageIcon(getClass().getClassLoader().getResource(gfx + "charmander_front.png"));
				emEscolha = false;
				emBatalha = true;
				emPremio = false;
				desenharCampoBatalha();
				conversador();
			}else if(msg.equals("SQUIRTLE")) {
				adversario = new ImageIcon(getClass().getClassLoader().getResource(gfx + "squirtle_front.png"));
				emEscolha = false;
				emBatalha = true;
				emPremio = false;
				desenharCampoBatalha();
				conversador();
			}else if(msg.equals("PIKACHU")) {
				adversario = new ImageIcon(getClass().getClassLoader().getResource(gfx + "pikachu_front.png"));
				emEscolha = false;
				emBatalha = true;
				emPremio = false;
				desenharCampoBatalha();
				conversador();
			}else if(msg.equals("MEWTWO")) {
				adversario = new ImageIcon(getClass().getClassLoader().getResource(gfx + "mewtwo_front.png"));
				emEscolha = false;
				emBatalha = true;
				emPremio = false;
				desenharCampoBatalha();
				conversador();
			}else if(msg.equals("AGUARDETURNO")) {
				ativarAcoes = false;
				desenharCampoBatalha();
				conversador();
			}else if(msg.equals("ATAQUE")) {
				logger("Seu turno! Ataque!");
				ativarAcoes = true;
				desenharCampoBatalha();
				conversador();
			}else if(msg.contains("DANOADV")) {
				int danoadv = Integer.parseInt(msg.replace("DANOADV ", ""));
				vidaAdv -= danoadv;
				jPBadversario.setValue(vidaAdv);
				logger("Adversário sofreu " + danoadv + " de dano!");
				conversador();
			}else if(msg.contains("DANODTM")) {
				int dano = Integer.parseInt(msg.replace("DANODTM ", ""));
				vidaDtm -= dano;
				jPBadversario.setValue(vidaDtm);
				logger("Seu distrimon sofreu " + dano + " de dano!");
				
				if(vidaDtm > 0) {
					cliente.getCliente().envia("VIVO");
					logger("Ainda dá pra vencer, não desista!");
				}else {
					cliente.getCliente().envia("MORTO");
					logger("Infelizmente você perdeu!");
				}
				
				conversador();
			}else  if(msg.equals("PREMIO")) {
				JOptionPane.showMessageDialog(null, "Parabéns! Você venceu, escolha seu prêmio!");
				emEscolha = false;
				emBatalha = false;
				emPremio = true;
				
				desenharCampoBatalha();
				conversador();
			}else  if(msg.equals("VAZA")) {
				JOptionPane.showMessageDialog(null, "Infelizmente você perdeu! Será desconectado...");
				System.exit(-1);
				
			}
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}