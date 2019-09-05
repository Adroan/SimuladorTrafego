/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controler.Gerenciador;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author vinny
 */
public class SimuladorTrafego extends JFrame implements ActionListener{
    
    //JLabel
    private JLabel qtdCarros;
    private JLabel intervaloInsercao;
    private JLabel modo; 
    private JLabel quantidadeCarrosRodando;
    private JLabel tempoDeExecucao;
    
    //JTextField
    private JTextField jtfQtdCarros;
    private JTextField jtfIntervaloInsercao;
    private JTextField jtfQuantidadeCarrosRodando;
    private JTextField jtfTempoDeExecucao;
    
    
    //JButton
    private JButton jbIniciar,jbEncerrar;
    private JRadioButton jrbSemaforo,jrbMonitor;
    private ButtonGroup bgOpcoes;

    
    //Layouts
    private GridBagLayout layout;
    private GridLayout gridLayout;
    private GridBagConstraints constraints;
    
    //Panel
    private JPanel panelPrincipal,panelOpcoes,panelEsquerda,panelDireita;
    private JTable estrada;
    
    //Gerenciador
    private Gerenciador gerenciador;

    public SimuladorTrafego() {
        //Configura a view
        setTitle("Simulador de Trafego");
        setBounds(200, 200, 1280, 720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setLayout(new BorderLayout());
        gerenciador = Gerenciador.getInstance();
        initComponents();
        
    }
    
    public static void main(String[] args) {
        try {
            SimuladorTrafego d = new SimuladorTrafego();
            d.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    
    private void initComponents(){
        
        //Inicia os componentes de layout
        constraints = new GridBagConstraints();
        layout = new GridBagLayout();
        gridLayout = new GridLayout(3, 3,10,10);
        
        //Inicia os panels do sistema
        panelPrincipal = new JPanel();
        panelOpcoes = new JPanel(gridLayout);
        panelEsquerda = new JPanel();
        panelDireita = new JPanel();
        
        //Inicia componentes de interface
        
        //JLabels
        qtdCarros = new JLabel("Quantidade de carros");
        intervaloInsercao = new JLabel("Intervalo de Insercao (ms): ");
        modo = new JLabel("Selecione o modo: ");
        quantidadeCarrosRodando = new JLabel("Carros rodando: ");
        tempoDeExecucao = new JLabel("Tempo de execucao: ");
        
        //JTextFields
        jtfQtdCarros = new JTextField();
        jtfIntervaloInsercao = new JTextField();
        jtfQuantidadeCarrosRodando = new JTextField();
        jtfTempoDeExecucao = new JTextField();
        
        //Buttons
        jbIniciar = new JButton("Iniciar Simulacao");
        jbEncerrar = new JButton("Encerrar Simulacao");
        jbEncerrar.setEnabled(false);
        jrbSemaforo = new JRadioButton("Semaforo", true);
        jrbMonitor = new JRadioButton("Monitor", false);
        bgOpcoes = new ButtonGroup();
        bgOpcoes.add(jrbSemaforo);
        bgOpcoes.add(jrbMonitor);
        
        //Adiciona os itens no painel de opcoes
        panelOpcoes.add(qtdCarros);
        panelOpcoes.add(jtfQtdCarros);
        panelOpcoes.add(jbIniciar);
        panelOpcoes.add(intervaloInsercao);
        panelOpcoes.add(jtfIntervaloInsercao);
        panelOpcoes.add(jbEncerrar);
        panelOpcoes.add(modo);
        panelOpcoes.add(jrbSemaforo);
        panelOpcoes.add(jrbMonitor);
        
        add(panelOpcoes,BorderLayout.NORTH);
        
        
        // criar o tabuleiro e seus componentes
        estrada = new JTable();
        estrada.setModel(new EstradaTableModel());
        for (int x = 0; x < estrada.getColumnModel().getColumnCount(); x++) {
            estrada.getColumnModel().getColumn(x).setWidth(23);
            estrada.getColumnModel().getColumn(x).setMinWidth(23);
            estrada.getColumnModel().getColumn(x).setMaxWidth(23);
        }
        estrada.setRowHeight(23);
        estrada.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        estrada.setShowGrid(false);
        estrada.setIntercellSpacing(new Dimension(0, 0));
        estrada.setDefaultRenderer(Object.class, new EstradaRenderer());


        //Adiciona o tabuleiro no centro da tela
        panelEsquerda.add(quantidadeCarrosRodando);
        panelEsquerda.add(jtfQuantidadeCarrosRodando);
        panelEsquerda.add(tempoDeExecucao);
        panelEsquerda.add(jtfTempoDeExecucao);
        panelEsquerda.setLayout(new GridLayout(4, 2, 10, 10));
        panelPrincipal.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelPrincipal.add(estrada);
        
        //add(panelEsquerda,BorderLayout.WEST);
        add(panelPrincipal,BorderLayout.CENTER);
        //add(panelDireita,BorderLayout.EAST);
        
        //Adicionando eventos
        jbIniciar.addActionListener(this);
        jbEncerrar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == jbIniciar){
            try{
                int intervalo;
                boolean entrouCatch = false;
                int qtdCarro = Integer.parseInt(jtfQtdCarros.getText());
                if(qtdCarro <= 0){
                    qtdCarro = qtdCarro / 0;
                }
                if(!jtfIntervaloInsercao.getText().equals("")){
                    try{
                        intervalo = Integer.parseInt(jtfIntervaloInsercao.getText());
                        if(intervalo <= 0){
                            intervalo = intervalo / 0;
                        }
                    }catch(Exception exe){
                        JOptionPane.showMessageDialog(rootPane, "Intervalo inválido");
                        entrouCatch = true;
                    }
                    
                }
                if(!entrouCatch)
                    iniciarSimulacao();
                //Chamar método
                
            }catch(Exception ex){
                JOptionPane.showMessageDialog(rootPane, "Quantidade de carros inválida");
            }
        }
        if(e.getSource() == jbEncerrar){
            encerrarSimulacao();
        }
        
    }
    
    public void iniciarSimulacao() {
        jbIniciar.setEnabled(false);
        jbEncerrar.setEnabled(true);
        jrbMonitor.setEnabled(false);
        jrbSemaforo.setEnabled(false);
        jtfQtdCarros.setEnabled(false);
        jtfIntervaloInsercao.setEnabled(false);
    }
    
    public void encerrarSimulacao(){
        jbIniciar.setEnabled(true);
        jbEncerrar.setEnabled(false);
        jrbMonitor.setEnabled(true);
        jrbSemaforo.setEnabled(true);
        jtfQtdCarros.setEnabled(true);
        jtfIntervaloInsercao.setEnabled(true);
    }
    
    class EstradaTableModel extends AbstractTableModel {

        private static final long serialVersionUID = 1L;

        @Override
        public int getColumnCount() {
            return 25;
        }

        @Override
        public int getRowCount() {
            return 25;
        }

        @Override
        public Object getValueAt(int row, int col) {
            try {
                return gerenciador.getImageMatriz(col, row);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.toString());
                return null;
            }
        }

    }
    
    class EstradaRenderer extends DefaultTableCellRenderer {

        private static final long serialVersionUID = 1L;

        public Component getTableCellRendererComponent(JTable table,
                Object value, boolean isSelected, boolean hasFocus, int row,
                int column) {

            setIcon((ImageIcon) value);

            return this;
        }

    }
}
