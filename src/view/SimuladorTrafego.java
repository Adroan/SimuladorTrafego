/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author vinny
 */
public class SimuladorTrafego extends JFrame{
    
    //JLabel
    private JLabel qtdCarros;
    private JLabel intervaloInsercao;
    
    //JTextField
    private JTextField jtfQtdCarros;
    private JTextField jtfIntervaloInsercao;
    
    
    //JButton
    private JButton jbIniciar,jbEncerrar;
    
    //Layouts
    private GridBagLayout layout;
    private GridBagConstraints constraints;
    
    //Panel
    private JPanel panelPrincipal,panelOpcoes;
    private JTable estrada;

    public SimuladorTrafego() {
        //Configura a view
        setTitle("Simulador de Trafego");
        setBounds(200, 200, 1280, 720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setLayout(new BorderLayout());

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
        
        //Inicia os panels do sistema
        panelPrincipal = new JPanel(layout);
        panelOpcoes = new JPanel(layout);
        
        //Inicia componentes de interface
        qtdCarros = new JLabel("Quantidade de carros");
        intervaloInsercao = new JLabel("Intervalo de Insercao (ms): ");
        jtfQtdCarros = new JTextField();
        jtfIntervaloInsercao = new JTextField();
        jbIniciar = new JButton("Iniciar Simulacao");
        jbEncerrar = new JButton("Encerrar Simulacao");
        
        //Adiciona os itens no painel de opcoes
        constraints.gridx = 0;
        constraints.gridy = 0;
        panelOpcoes.add(qtdCarros, constraints);
        
        constraints.gridx = 1;
        constraints.gridy = 0;
        panelOpcoes.add(jtfQtdCarros, constraints);
        
        constraints.gridx = 2;
        constraints.gridy = 0;
        panelOpcoes.add(intervaloInsercao, constraints);
        
        constraints.gridx = 3;
        constraints.gridy = 0;
        panelOpcoes.add(jtfIntervaloInsercao, constraints);
        
        constraints.gridx = 1;
        constraints.gridy = 2;
        panelOpcoes.add(jbIniciar, constraints);
        
        constraints.gridx = 3;
        constraints.gridy = 2;
        panelOpcoes.add(jbEncerrar, constraints);
        
        add("North",panelOpcoes);
        
        
        // criar o tabuleiro e seus componentes
        estrada = new JTable();
        estrada.setModel(new EstradaTableModel());
        for (int x = 0; x < estrada.getColumnModel().getColumnCount(); x++) {
            estrada.getColumnModel().getColumn(x).setWidth(25);
            estrada.getColumnModel().getColumn(x).setMinWidth(25);
            estrada.getColumnModel().getColumn(x).setMaxWidth(25);
        }
        estrada.setRowHeight(25);
        estrada.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        estrada.setShowGrid(true);
        estrada.setGridColor(Color.red);
        estrada.setIntercellSpacing(new Dimension(0, 0));
        estrada.setDefaultRenderer(Object.class, new EstradaRenderer());


        //Adiciona o tabuleiro no centro da tela
        add("Center", estrada);
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
                //return gerenciador.getPeca(col, row);
                return null;
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
