/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author vinny
 */
public class TelaPrincipal extends JFrame{
    
    private JLabel qtdCarros;
    private JTextField jtfQtdCarros;
    private JRadioButton opcoes;

    public TelaPrincipal() {
        //Configura a view
        setTitle("Simulador de Trafego");
        setBounds(200, 200, 900, 100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setLayout(new BorderLayout());

        initComponents();
    }
    
    
    private void initComponents(){
        
    }
    
    
    
    class EstradaTableModel extends AbstractTableModel {

        private static final long serialVersionUID = 1L;

        @Override
        public int getColumnCount() {
            return 5;
        }

        @Override
        public int getRowCount() {
            return 5;
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
