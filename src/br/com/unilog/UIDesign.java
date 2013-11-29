package br.com.unilog;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import br.com.unilog.bo.MontaXMLBO;
import br.com.unilog.enuns.TipoNota;
import br.com.unilog.to.Carregamento;
import br.com.unilog.to.Nota;
import br.com.unilog.to.Vagao;
import br.com.unilog.utilitario.Utilitario;

import com.thoughtworks.xstream.XStream;
import com.toedter.calendar.JDateChooser;
import com.vale.sc.unificacaocomercial.unicom.invokerunicom.incarregamento.services.impl.ejb.InCarregamentoEJB;

public class UIDesign extends JFrame {

	private static final String XML_SAVE = "./saveToConfig.xml";
	private static final String[] LIST_HEADER_VAGAO = new String[] {
		"Tipo Vagão", "Número", "Owner", "Nota" 
	};
	private static final String[] LIST_HEAD_NOTAS = new String[] {
		"Tipo Nota", "Série", "Número", "Data", "Valor", "Peso", "Chave"
	};

	private static InCarregamentoEJB _services = null;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField host;
	private JTextField usuario;
	private JTextField senha;
	private Font font = new Font("Verdana", Font.PLAIN, 12);
	private Font font_bold = new Font("Verdana", Font.BOLD, 12);
	private JTextField serieCarregamento;
	private JTextField numeroNotaFiscal;
	private JTextField fluxo;
	private JTextField pesoCarregamento;
	private JTextField numeroAtrelados;
	private JTextField serieNotaFiscal;
	private JTextField numeroNota;
	private JTextField valorNotaFiscal;
	private JTextField pesoNotaFiscal;
	private JTextField chaveNotaFiscal;
	private JTable notasFiscais;
	private JTextField tipoVagao;
	private JTextField numeroVagao;
	private JTextField ownerVagao;
	private JTable vagoesNotas;
	private List<Nota> notasList;
	private List<Vagao> vagoesList;
	private Carregamento carregamento;
	private JDateChooser dataCriacaoCarregamento;
	private ButtonGroup group;
	private JComboBox<Nota> notaFiscalVagao;
	private JComboBox<String> tipoOperacao;
	private JRadioButton rdbtnSim;
	private JRadioButton rdbtnNo;
	private JButton btnEditarNotas;
	private JButton btnExcluir;
	private JDateChooser dataNota;
	private MontaXMLBO montaXML;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIDesign frame = new UIDesign();
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public UIDesign() {
		montaXML = new MontaXMLBO(); //Inicia o object
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 869, 838);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JPanel operacao = new JPanel();
		operacao.setBounds(5, 5, 423, 75);
		operacao.setBorder(creatTitleBorder("Operação"));
		contentPane.add(operacao);
		operacao.setLayout(null);

		JLabel lblOperao = new JLabel("Tipo:");
		lblOperao.setBounds(10, 30, 40, 20);
		lblOperao.setFont(font);
		operacao.add(lblOperao);

		tipoOperacao = new JComboBox<String>();
		tipoOperacao.setFont(font);
		tipoOperacao.setModel(new DefaultComboBoxModel<String>(new String[] {
				"1-Inclusao", "2-Alteracao", "3-Cancelamento",
				"4- Reliberacao", "5- Cancelamento Restrito" }));
		tipoOperacao.setBounds(45, 30, 365, 20);
		operacao.add(tipoOperacao);

		JPanel dadosConexao = new JPanel();
		dadosConexao.setBounds(434, 5, 423, 75);
		dadosConexao.setBorder(creatTitleBorder("Conexão"));
		contentPane.add(dadosConexao);
		dadosConexao.setLayout(null);

		JLabel lblNewLabel = new JLabel("Host:");
		lblNewLabel.setFont(font);
		lblNewLabel.setBounds(10, 20, 40, 20);
		dadosConexao.add(lblNewLabel);

		host = new JTextField();
		host.setBounds(50, 20, 360, 20);
		dadosConexao.add(host);
		host.setColumns(10);

		JLabel lblUsurio = new JLabel("Usu\u00E1rio:");
		lblUsurio.setFont(font);
		lblUsurio.setBounds(10, 45, 60, 20);
		dadosConexao.add(lblUsurio);

		usuario = new JTextField();
		usuario.setColumns(10);
		usuario.setBounds(75, 45, 130, 20);
		dadosConexao.add(usuario);

		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(font);
		lblSenha.setBounds(215, 45, 60, 20);
		dadosConexao.add(lblSenha);

		senha = new JTextField();
		senha.setColumns(10);
		senha.setBounds(280, 45, 130, 20);
		dadosConexao.add(senha);

		JPanel cabecalho = new JPanel();
		cabecalho.setBounds(5, 80, 852, 80);
		cabecalho.setBorder(creatTitleBorder("Cabeçalho"));
		contentPane.add(cabecalho);
		cabecalho.setLayout(null);

		JLabel lblSrie = new JLabel("S\u00E9rie:");
		lblSrie.setBounds(10, 20, 60, 20);
		lblSrie.setFont(font);
		cabecalho.add(lblSrie);

		serieCarregamento = new JTextField();
		serieCarregamento.setBounds(75, 20, 100, 20);
		serieCarregamento.setColumns(10);
		cabecalho.add(serieCarregamento);

		JLabel lblNmero = new JLabel("N\u00FAmero:");
		lblNmero.setFont(font);
		lblNmero.setBounds(185, 20, 60, 20);
		cabecalho.add(lblNmero);

		numeroNotaFiscal = new JTextField();
		JTextField numeroCte = numeroNotaFiscal;
		numeroCte.setColumns(10);
		numeroCte.setBounds(250, 20, 150, 20);
		cabecalho.add(numeroCte);

		JLabel lblFluxo = new JLabel("Fluxo:");
		lblFluxo.setFont(font);
		lblFluxo.setBounds(410, 20, 60, 20);
		cabecalho.add(lblFluxo);

		fluxo = new JTextField();
		fluxo.setColumns(10);
		fluxo.setBounds(480, 20, 150, 20);
		cabecalho.add(fluxo);

		JLabel lblData = new JLabel("Data:");
		lblData.setFont(font);
		lblData.setBounds(645, 20, 50, 20);
		cabecalho.add(lblData);

		dataCriacaoCarregamento = new JDateChooser();
		dataCriacaoCarregamento.setBounds(705, 20, 130, 20);
		cabecalho.add(dataCriacaoCarregamento);

		JLabel lblPeso = new JLabel("Peso:");
		lblPeso.setFont(font);
		lblPeso.setBounds(10, 50, 60, 20);
		cabecalho.add(lblPeso);

		pesoCarregamento = new JTextField();
		pesoCarregamento.setColumns(10);
		pesoCarregamento.setBounds(75, 50, 120, 20);
		cabecalho.add(pesoCarregamento);

		JLabel lblnico = new JLabel("\u00DAnico:");
		lblnico.setFont(font);
		lblnico.setBounds(290, 50, 60, 20);
		cabecalho.add(lblnico);

		rdbtnSim = new JRadioButton("Sim");
		rdbtnSim.setSelected(true);
		rdbtnSim.setBounds(370, 50, 50, 20);
		rdbtnSim.setFont(font);
		rdbtnSim.setMnemonic(1);
		cabecalho.add(rdbtnSim);

		rdbtnNo = new JRadioButton("N\u00E3o");
		rdbtnNo.setBounds(459, 50, 50, 20);
		rdbtnNo.setFont(font);
		rdbtnNo.setMnemonic(0);
		cabecalho.add(rdbtnNo);

		group = new ButtonGroup();
		group.add(rdbtnSim);
		group.add(rdbtnNo);

		JLabel lblNmeroDeAtrelados = new JLabel("N\u00FAmero de Atrelados:");
		lblNmeroDeAtrelados.setFont(font);
		lblNmeroDeAtrelados.setBounds(625, 50, 150, 20);
		cabecalho.add(lblNmeroDeAtrelados);

		numeroAtrelados = new JTextField();
		numeroAtrelados.setColumns(10);
		numeroAtrelados.setBounds(785, 50, 50, 20);
		cabecalho.add(numeroAtrelados);

		JPanel notas = new JPanel();
		notas.setBounds(5, 160, 852, 294);
		notas.setBorder(creatTitleBorder("Nota Fiscal"));
		notas.setLayout(null);
		contentPane.add(notas);

		JLabel label = new JLabel("Tipo:");
		label.setBounds(10, 20, 40, 20);
		label.setFont(font);
		notas.add(label);

		final JComboBox<String> tipoNota = new JComboBox<String>();
		tipoNota.setModel(new DefaultComboBoxModel(TipoNota.values()));
		tipoNota.setBounds(50, 20, 200, 20);
		tipoNota.setFont(font);
		notas.add(tipoNota);
		
		JLabel serieNota = new JLabel("S\u00E9rie:");
		serieNota.setFont(font);
		serieNota.setBounds(271, 20, 40, 20);
		notas.add(serieNota);

		serieNotaFiscal = new JTextField();
		serieNotaFiscal.setColumns(10);
		serieNotaFiscal.setBounds(317, 20, 100, 20);
		notas.add(serieNotaFiscal);
		
		
		JLabel label_2 = new JLabel("N\u00FAmero:");
		label_2.setFont(font);
		label_2.setBounds(427, 20, 60, 20);
		notas.add(label_2);
		
		numeroNota = new JTextField();
		numeroNota.setColumns(10);
		numeroNota.setBounds(492, 20, 150, 20);
		notas.add(numeroNota);
		
		JLabel label_3 = new JLabel("Data:");
		label_3.setFont(font);
		label_3.setBounds(652, 20, 50, 20);
		notas.add(label_3);
		
		dataNota = new JDateChooser();
		dataNota.setBounds(712, 20, 130, 20);
		notas.add(dataNota);
		
		JLabel lblValor = new JLabel("Valor:");
		lblValor.setFont(font);
		lblValor.setBounds(10, 50, 40, 20);
		notas.add(lblValor);
		
		valorNotaFiscal = new JTextField();
		valorNotaFiscal.setColumns(10);
		valorNotaFiscal.setBounds(56, 50, 100, 20);
		notas.add(valorNotaFiscal);
		
		pesoNotaFiscal = new JTextField();
		pesoNotaFiscal.setColumns(10);
		pesoNotaFiscal.setBounds(230, 50, 100, 20);
		notas.add(pesoNotaFiscal);
		
		JLabel lblPeso_1 = new JLabel("Peso:");
		lblPeso_1.setFont(font);
		lblPeso_1.setBounds(184, 50, 40, 20);
		notas.add(lblPeso_1);
		
		JLabel lblChave = new JLabel("Chave:");
		lblChave.setFont(font);
		lblChave.setBounds(352, 50, 50, 20);
		notas.add(lblChave);
		
		chaveNotaFiscal = new JTextField();
		chaveNotaFiscal.setColumns(10);
		chaveNotaFiscal.setBounds(408, 50, 434, 20);
		notas.add(chaveNotaFiscal);
		
		JPanel vagoes = new JPanel();
		vagoes.setBounds(5, 454, 852, 304);
		vagoes.setBorder(creatTitleBorder("Vagões"));
		contentPane.add(vagoes);
		vagoes.setLayout(null);		
		
		JButton btnSalvar_1 = new JButton("Salvar");
		btnSalvar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					if(notasList==null)
						notasList = new ArrayList<Nota>(); //Inicia nota
					
					//Notas adicionadas
					notasList.add(new Nota((TipoNota)tipoNota.getSelectedItem(), serieNotaFiscal.getText().trim(),
							numeroNota.getText().trim(), dataNota.getDate(),
							Double.parseDouble(valorNotaFiscal.getText().trim()),
							Integer.parseInt(pesoNotaFiscal.getText().trim()), chaveNotaFiscal.getText().trim()));

					serieNotaFiscal.setText("");
					numeroNota.setText("");
					dataNota.setDate(null);
					valorNotaFiscal.setText("");
					pesoNotaFiscal.setText("");
					chaveNotaFiscal.setText("");					
					
					updateNotasFiscais();
					updateComboNotaFiscal();
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, "Error:"+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		btnSalvar_1.setBounds(770, 80, 75, 20);
		btnSalvar_1.setFont(font);
		notas.add(btnSalvar_1);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setFont(font);
		lblTipo.setBounds(10, 20, 40, 20);
		vagoes.add(lblTipo);
		
		tipoVagao = new JTextField();
		tipoVagao.setColumns(10);
		tipoVagao.setBounds(100, 20, 150, 20);
		vagoes.add(tipoVagao);
		
		JLabel lblNmero_1 = new JLabel("N\u00FAmero:");
		lblNmero_1.setFont(font);
		lblNmero_1.setBounds(300, 20, 80, 20);
		vagoes.add(lblNmero_1);
		
		numeroVagao = new JTextField();
		numeroVagao.setColumns(10);
		numeroVagao.setBounds(390, 21, 150, 20);
		vagoes.add(numeroVagao);
		
		JLabel lblOwner = new JLabel("Owner:");
		lblOwner.setFont(font);
		lblOwner.setBounds(590, 20, 50, 20);
		vagoes.add(lblOwner);
		
		ownerVagao = new JTextField();
		ownerVagao.setColumns(10);
		ownerVagao.setBounds(690, 20, 150, 20);
		vagoes.add(ownerVagao);
		
		JButton incluirVagao = new JButton("Salvar");
		incluirVagao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					if(vagoesList==null)
						vagoesList = new ArrayList<Vagao>(); //Inicia nota
					
					//Vagões adicionadas
					Vagao vagao = new Vagao(tipoVagao.getText().trim(), numeroVagao.getText().trim(), ownerVagao.getText().trim(), ((Nota)notaFiscalVagao.getSelectedItem()));
					vagoesList.add(vagao);
					
					updateVagoesNotas();
					
					tipoVagao.setText("");
					numeroVagao.setText("");
					ownerVagao.setText("");
					notaFiscalVagao.setSelectedIndex(0);
					
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, "Error:"+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		incluirVagao.setFont(font);
		incluirVagao.setBounds(765, 80, 75, 20);
		vagoes.add(incluirVagao);
		
		JButton btnGerar = new JButton("Gerar");
		btnGerar.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				parseToXml();
				conexaoEjb();
			}
		});
		btnGerar.setFont(font);
		btnGerar.setBounds(285, 780, 75, 20);
		contentPane.add(btnGerar);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				obtemXml();
			}
		});
		btnSalvar.setFont(font);
		btnSalvar.setBounds(370, 780, 75, 20);
		contentPane.add(btnSalvar);
		
		JButton btnSair = new JButton("Sair");
		btnSair.setFont(font);
		btnSair.setBounds(455, 780, 75, 20);
		contentPane.add(btnSair);

		this.setTitle("UNILOG");
		parseToDTO();

		
		JLabel lblNotaFiscal = new JLabel("Nota Fiscal:");
		lblNotaFiscal.setFont(font);
		lblNotaFiscal.setBounds(10, 50, 90, 20);
		vagoes.add(lblNotaFiscal);
		
		notaFiscalVagao = new JComboBox<Nota>();
		notaFiscalVagao.setModel(modelItensCombo());
		notaFiscalVagao.setFont(font);
		notaFiscalVagao.setBounds(110, 50, 254, 20);
		vagoes.add(notaFiscalVagao);
		
		//Notas Fiscais
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 109, 832, 140);
		notas.add(scrollPane);
		
		notasFiscais = new JTable();
		notasFiscais.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		notasFiscais.setModel(novoModel(notasList,LIST_HEAD_NOTAS));
		scrollPane.setViewportView(notasFiscais);
		
		btnEditarNotas = new JButton("Editar");
		btnEditarNotas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selectedRow = notasFiscais.getSelectedRow();
				Nota nota = notasList.get(selectedRow);
				serieNotaFiscal.setText(nota.getSerie());
				numeroNota.setText(nota.getNumero());
				dataNota.setDate(nota.getDate());
				valorNotaFiscal.setText(Double.toString(nota.getValor()));
				pesoNotaFiscal.setText(Integer.toString(nota.getPeso()));
				chaveNotaFiscal.setText(nota.getNfeKey());
				notasList.remove(selectedRow);
				tipoNota.setSelectedItem(nota.getTipoNota());
			}
		});
		btnEditarNotas.setFont(font);
		btnEditarNotas.setBounds(320, 260, 75, 20);
		notas.add(btnEditarNotas);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selectedRow = notasFiscais.getSelectedRow();
				notasList.remove(selectedRow);
				updateNotasFiscais();
				updateComboNotaFiscal();
			}
		});
		
		btnExcluir.setFont(font);
		btnExcluir.setBounds(410, 260, 75, 20);
		notas.add(btnExcluir);
		
		//Vagões
		JScrollPane panelVagoesNotas = new JScrollPane();
		panelVagoesNotas.setBounds(10, 110, 832, 155);
		vagoes.add(panelVagoesNotas);
		
		vagoesNotas = new JTable();
		vagoesNotas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		vagoesNotas.setModel(novoModel(vagoesList, LIST_HEADER_VAGAO));
		panelVagoesNotas.setViewportView(vagoesNotas);		
		
		JButton editarVagoes = new JButton("Editar");
		editarVagoes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = vagoesNotas.getSelectedRow();
				Vagao v = vagoesList.get(selectedRow);
				
				tipoVagao.setText(v.getTipo());
				numeroVagao.setText(v.getNumero());
				ownerVagao.setText(v.getOwner());
				notaFiscalVagao.setSelectedItem(v.getNota());;
				
				vagoesList.remove(selectedRow);
			}
		});
		
		editarVagoes.setFont(font);
		editarVagoes.setBounds(320, 275, 75, 20);
		vagoes.add(editarVagoes);
		
		JButton button_1 = new JButton("Excluir");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = vagoesNotas.getSelectedRow();
				vagoesList.remove(selectedRow);
				updateVagoesNotas();
			}
		});
		button_1.setFont(font);
		button_1.setBounds(410, 275, 75, 20);
		vagoes.add(button_1);
		
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private DefaultComboBoxModel<Nota> modelItensCombo() {
		DefaultComboBoxModel<Nota> model = new DefaultComboBoxModel<>();
		
		if(notasList!=null && notasList.size()>0){
			model = new DefaultComboBoxModel(notasList.toArray());
		}
		
		return model;
	}

	private DefaultTableModel novoModel(List<?> lista, String[] headers) {
		return new DefaultTableModel(
			Utilitario.valoresForJtable(lista),
			headers
		){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
	}

	private TitledBorder creatTitleBorder(String title) {
		return BorderFactory.createTitledBorder(null, title, TitledBorder.LEFT,TitledBorder.TOP, font_bold);
	}
	
	private void parseToXml() {
		XStream xStream = new XStream();
		bindToCarregamento();
		
	    try {
	    	BufferedWriter writer = new BufferedWriter(new FileWriter(XML_SAVE));
			writer.write(xStream.toXML(carregamento).trim());
			writer.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error:"+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}



	private void bindToCarregamento() {
		carregamento = new Carregamento();
		
		carregamento.setAtrelados(Integer.parseInt(numeroAtrelados.getText().trim()));
		carregamento.setDataCriacao(dataCriacaoCarregamento.getDate());
		carregamento.setFluxo(fluxo.getText().trim());
		carregamento.setNumero(numeroNotaFiscal.getText().trim());
		carregamento.setPeso(Integer.parseInt(pesoCarregamento.getText().trim()));
		carregamento.setSerie(serieCarregamento.getText().trim());
		carregamento.setUnico((group.getSelection().getMnemonic()==1 ? true : false));
		carregamento.setNotas(notasList);
		carregamento.setVagoes(vagoesList);
		carregamento.setHost(host.getText().trim());
		carregamento.setUsuario(usuario.getText().trim());
		carregamento.setSenha(senha.getText().trim());
		carregamento.setTipoOperacao((String)tipoOperacao.getSelectedItem());
	}
	
	
	
	private void parseToDTO() {
		XStream xStream = new XStream();
		StringBuffer stringBuffer = new StringBuffer();
		
		try {
			File file = new File(XML_SAVE);
			FileReader fileReader;
			fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				stringBuffer.append(line);
				stringBuffer.append("\n");
			}
			fileReader.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error:"+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		String xml = stringBuffer.toString();
		//Somente se náo for vazio ou nulo
		if(xml!=null && !xml.isEmpty()){
			Carregamento fromXML = (Carregamento)xStream.fromXML(xml);
			
			numeroAtrelados.setText(Integer.toString(fromXML.getAtrelados()));
			dataCriacaoCarregamento.setDate(fromXML.getDataCriacao());
			fluxo.setText(fromXML.getFluxo());
			numeroNotaFiscal.setText(fromXML.getNumero());
			pesoCarregamento.setText(Integer.toString(fromXML.getPeso()));
			serieCarregamento.setText(fromXML.getSerie());
			
			if(fromXML.getUnico())
				rdbtnSim.setSelected(true);
			else
				rdbtnNo.setSelected(true);
			
			notasList = fromXML.getNotas();
			vagoesList = fromXML.getVagoes();
			host.setText(fromXML.getHost());
			usuario.setText(fromXML.getUsuario());
			senha.setText(fromXML.getSenha());
			tipoOperacao.setSelectedItem(fromXML.getTipoOperacao());
		}
		
	}

	private void updateNotasFiscais() {
		notasFiscais.setModel(novoModel(notasList,LIST_HEAD_NOTAS));
		notasFiscais.updateUI();
		notasFiscais.repaint();
	}

	private void updateComboNotaFiscal() {
		notaFiscalVagao.setModel(modelItensCombo());
		notaFiscalVagao.repaint();
		notaFiscalVagao.updateUI();
	}

	private void updateVagoesNotas() {
		vagoesNotas.setModel(novoModel(vagoesList, LIST_HEADER_VAGAO));
		vagoesNotas.updateUI();
		vagoesNotas.repaint();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void conexaoEjb(){
		if(_services==null){
			Hashtable env = new Hashtable();
			env.put("java.naming.factory.initial", "com.evermind.server.rmi.RMIInitialContextFactory");
			env.put("java.naming.security.principal", usuario.getText().trim()); //Login
			env.put("java.naming.security.credentials", senha.getText().trim()); //Senha
			env.put("java.naming.provider.url", host.getText().trim()); //Url conexao
			env.put("dedicated.rmicontext", "false");
			env.put("dedicated.connection", "true");
			
			try{
				Context context = new InitialContext(env);
				Object obj = context.lookup("InCarregamento");
				if (obj != null) {
					Object home = PortableRemoteObject.narrow(obj, Class.forName("com.vale.sc.unificacaocomercial.unicom.invokerunicom.incarregamento.services.impl.ejb.InCarregamentoHome", false, Thread.currentThread().getContextClassLoader()));
					Method method = home.getClass().getMethod("create", null);
					_services = (InCarregamentoEJB)method.invoke(home, null);
					System.out.println(_services.syncFreight(obtemXml(), ""));
				}
			}catch(Exception e){
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}
	}



	private String obtemXml() {
		bindToCarregamento();
		return montaXML.criarXMLsend(carregamento);
	}
	
}