
package pecaseletronicas;
import java.sql.*;
import javax.swing.JOptionPane;
import pecaseletronicas.dao.Conexao;
import net.proteanit.sql.DbUtils;
import java.awt.Color;
import javax.swing.JFrame;

public class telaPrincipal extends javax.swing.JFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    public telaPrincipal() {
        initComponents();

        JFrame frame = new JFrame("Minha Janela Tela Cheia");

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

       


        Color cor = new Color(153,204,255); 
        getContentPane().setBackground(cor);
        conexao = Conexao.conector();
        //System.out.println(conexao);
        if (conexao == null){
            dbconect.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaseletronicas/img/dberro.png")));  
            System.out.println("Deu erro "+conexao);
       }else {
           dbconect.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaseletronicas/img/dbok.png")));
          //System.out.println("Deu certo "+conexao);
       } 
    }
    
    private void Adicionar(){
   
       TelaCriacao TelaAdicionar = new TelaCriacao(); 
       TelaAdicionar.setVisible(true);
    
    }
    
    private void pesquisa_tipo(){
       
        String sql = "SELECT caixa as 'Caixa', tipo as 'Categoria/Tipo', peca as 'Peças', id from pecas where tipo like ?";
        try {
            pst = conexao.prepareStatement(sql);
            // passand o conteudo da caixa de pesquisa 
            // para o interroga
            // atencaçoa ao porcetagem que a continuaalao da string 
            pst.setString(1, BscTipo.getText() + "%");
            rs = pst.executeQuery();
            // alinha abaixo usas a biblioteca ara preencher a tabela 
            table.setModel(DbUtils.resultSetToTableModel(rs));
           // System.out.println("deu certo ");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            //System.out.println("deu erro "+e);
        }
    }
    private void pesquisa_caixa(){
       
        String sql = "SELECT caixa as 'Caixa', tipo as 'Categoria/Tipo', peca as 'Peças', id from pecas where peca like ?";
        try {
            pst = conexao.prepareStatement(sql);
            // passand o conteudo da caixa de pesquisa 
            // para o interroga
            // atencaçoa ao porcetagem que a continuaalao da string 
            pst.setString(1, "%" + BscCaixa.getText() + "%");
            rs = pst.executeQuery();
            // alinha abaixo usas a biblioteca para preencher a tabela 
            table.setModel(DbUtils.resultSetToTableModel(rs));
            //System.out.println("deu certo ");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            //System.out.println("deu erro "+e);
        }
    }

    public void selectAll(){
      String sql = "SELECT caixa as 'Caixa', tipo as 'Categoria/Tipo', peca as 'Peças', id FROM pecas ORDER BY caixa ASC;";
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
           
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
              JOptionPane.showMessageDialog(null, e);
        }

    }


    private void setar_campos(){
       
       TelaCriacao TelaEdicao = new TelaCriacao(); 
       TelaEdicao.setVisible(true);
       
       int  setar = table.getSelectedRow();
       TelaEdicao.inputcaixa.setText(table.getModel().getValueAt(setar,0).toString());
       TelaEdicao.inputpeca.setText(table.getModel().getValueAt(setar,2).toString());
       TelaEdicao.ComboBox.setSelectedItem(table.getModel().getValueAt(setar,1).toString());
       TelaEdicao.id.setText(table.getModel().getValueAt(setar,3).toString()); 
       TelaEdicao.Adicionar.setEnabled(false);
        
    }

          

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField4 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btnadd = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        dbconect = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        BscTipo = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        BscCaixa = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 204, 255));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(2346, 1214, -1, -1));

        btnadd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaseletronicas/img/add.png"))); // NOI18N
        btnadd.setToolTipText("ADICIONAR");
        btnadd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnadd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnaddMouseClicked(evt);
            }
        });
        btnadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaddActionPerformed(evt);
            }
        });
        getContentPane().add(btnadd, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 80, 70));

        table.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "CAIXA", "PEÇA", "CATEGORIA"
            }
        ));
        table.setAlignmentX(0.7F);
        table.setAlignmentY(0.7F);
        table.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        table.setEditingColumn(1);
        table.setEditingRow(1);
        table.setMaximumSize(new java.awt.Dimension(2147483647, 322));
        table.setRowHeight(25);
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        table.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tableKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 1500, 600));

        dbconect.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaseletronicas/img/dberro.png"))); // NOI18N
        getContentPane().add(dbconect, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 10, 70, 70));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel7.setText("Buscar por Categoria/Tipo");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        BscTipo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BscTipo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                BscTipoKeyReleased(evt);
            }
        });
        getContentPane().add(BscTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 30, 460, -1));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel6.setText("Buscar por Nome da peça");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 260, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaseletronicas/img/refresh.png"))); // NOI18N
        jButton1.setToolTipText("ATUALIZAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 80, -1, -1));

        BscCaixa.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BscCaixa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                BscCaixaKeyReleased(evt);
            }
        });
        getContentPane().add(BscCaixa, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 90, 490, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnaddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnaddMouseClicked
         
    }//GEN-LAST:event_btnaddMouseClicked

    private void btnaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddActionPerformed
        Adicionar();
    }//GEN-LAST:event_btnaddActionPerformed

    private void tableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableKeyReleased
    
    }//GEN-LAST:event_tableKeyReleased

    private void BscTipoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BscTipoKeyReleased
        // TODO add your handling code here:
       pesquisa_tipo();
    }//GEN-LAST:event_BscTipoKeyReleased

    private void BscCaixaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BscCaixaKeyReleased
        // TODO add your handling code here:
         pesquisa_caixa();
    }//GEN-LAST:event_BscCaixaKeyReleased

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        // TODO add your handling code here:
       setar_campos();
    }//GEN-LAST:event_tableMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here
       selectAll();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        // TODO add your handling code here:

    }//GEN-LAST:event_formComponentResized

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(telaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(telaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(telaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(telaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new telaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField BscCaixa;
    private javax.swing.JTextField BscTipo;
    private javax.swing.JButton btnadd;
    private javax.swing.JLabel dbconect;
    public javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField4;
    public static javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
