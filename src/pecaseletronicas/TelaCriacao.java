package pecaseletronicas;
import java.sql.*;
import javax.swing.JOptionPane;
import pecaseletronicas.dao.Conexao;
import net.proteanit.sql.DbUtils;
import java.awt.Color;
import pecaseletronicas.telaPrincipal; 
import static pecaseletronicas.telaPrincipal.table;

public class TelaCriacao extends javax.swing.JFrame {

    

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;


    public TelaCriacao() {
        initComponents();

        Color cor = new Color(255,255,255); 
        
        getContentPane().setBackground(cor);
        
        conexao = Conexao.conector();
        //System.out.println(conexao);
        if (conexao == null){
            dbconect.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaseletronicas/img/dberro.png")));  
            System.out.println("Deu erro "+conexao);
       }else {
           dbconect.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaseletronicas/img/dbok.png")));
          System.out.println("Deu certo "+conexao);
       } 
    }

   public void AdicionarPeca(){

        String sql = "INSERT into pecas (caixa, peca, tipo) values (?,?,?); ";

           try {
             pst =  conexao.prepareStatement(sql);
             pst.setString(1, inputcaixa.getText());
             pst.setString(2, inputpeca.getText());
             pst.setString(3, ComboBox.getSelectedItem().toString());
             
             
             // validaçao dos campos obrigatorios 
             if ((inputpeca.getText().isEmpty() || ComboBox.getSelectedItem().equals("SELECIONE") || inputcaixa.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null,"DIGITE E SELECIONE OS CAMPOS OBRIGATÓRIOS");
                
            } else {
                  int adicionado = pst.executeUpdate();
                System.out.println(adicionado);
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "PEÇA CADASTRADA COM SUCESSO!");
                    inputpeca.setText(null);
                    ComboBox.setSelectedItem("SELECIONE");
                    inputcaixa.setText(null);
                    // CboUsuPerfil.setSelectedItem(null);
                    this.dispose();
                    String sql2 = "SELECT caixa as 'Caixa', tipo as 'Categoria/Tipo', peca as 'Peças', id FROM pecas ORDER BY caixa ASC;"; 
                pst = conexao.prepareStatement(sql2);
                rs = pst.executeQuery();
           
                table.setModel(DbUtils.resultSetToTableModel(rs));
                    
                }
            }
             // a linha abaixo executa a query       
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

}

     public void excluirPeca(){
  
    int confirma = JOptionPane.showConfirmDialog(null, "TEM CERTEZA QUE SEJA REMOVER ESSA PEÇA? ", "ATENÇÃO", JOptionPane.YES_NO_OPTION);

        
        if (confirma == JOptionPane.YES_NO_OPTION) {
            String sql = "delete from pecas where id = ?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, id.getText());
                int apagado = pst.executeUpdate();
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "PEÇA REMOVIDA COM SUCESSO! ATUALIZE A TABELA ");

                    id.setText(null);
                    inputpeca.setText(null);
                    inputcaixa.setText(null);
                    ComboBox.setSelectedItem("SELECIONE");
                    this.dispose();
                    String sql2 = "SELECT caixa as 'Caixa', tipo as 'Categoria/Tipo', peca as 'Peças',  id FROM pecas ORDER BY caixa ASC;"; 
                pst = conexao.prepareStatement(sql2);
                rs = pst.executeQuery();
           
                table.setModel(DbUtils.resultSetToTableModel(rs));
                    
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    this.dispose();
}

   public void editar(){ 
       

    String sql = "UPDATE pecas SET caixa = ?, peca = ? , tipo = ?  WHERE id = ?;";    

       try {
            pst = conexao.prepareStatement(sql);
            
             pst.setString(1, inputcaixa.getText());
             pst.setString(2, inputpeca.getText());
             pst.setString(3, ComboBox.getSelectedItem().toString());
             pst.setString(4, id.getText());


        
 if ((inputpeca.getText().isEmpty() || ComboBox.getSelectedItem().equals("SELECIONE") || inputcaixa.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null,"DIGITE E SELECIONE OS CAMPOS OBRIGATÓRIOS");
                
            } else {
            
        int alterado = pst.executeUpdate();

        if(alterado > 0 ){
        JOptionPane.showMessageDialog(null, "PEÇA ALTERADA COM SUCESSO");
        this.dispose();
        String sql2 = "SELECT caixa as 'Caixa', tipo as 'Categoria/Tipo', peca as 'Peças',  id FROM pecas ORDER BY caixa ASC;"; 
            pst = conexao.prepareStatement(sql2);
            rs = pst.executeQuery();
           
            table.setModel(DbUtils.resultSetToTableModel(rs));
        
       }
    } 
       
       } catch (SQLException e) {
         JOptionPane.showMessageDialog(null, e);
          
       }
    
   }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        inputcaixa = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        Adicionar = new javax.swing.JToggleButton();
        Fechar = new javax.swing.JToggleButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        inputpeca = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        ComboBox = new javax.swing.JComboBox<>();
        dbconect = new javax.swing.JLabel();
        Editar = new javax.swing.JButton();
        id = new javax.swing.JTextField();

        setTitle("Cadastrar uma peça");
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        inputcaixa.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel1.setText("*Caixa");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel2.setText("*Categoria/Tipo ");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel3.setText("*Nome da peça ");

        Adicionar.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        Adicionar.setForeground(new java.awt.Color(51, 102, 0));
        Adicionar.setText("Adicionar");
        Adicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AdicionarActionPerformed(evt);
            }
        });

        Fechar.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        Fechar.setForeground(new java.awt.Color(204, 0, 0));
        Fechar.setText("Excluir");
        Fechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FecharActionPerformed(evt);
            }
        });

        inputpeca.setColumns(20);
        inputpeca.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        inputpeca.setRows(5);
        jScrollPane1.setViewportView(inputpeca);

        jLabel4.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(204, 0, 0));
        jLabel4.setText("*Campos obrigatórios");

        ComboBox.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        ComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECIONE", "TRANSISTOR", "CIRCUITOS INTEGRADOS CI", "MOSFET", "FET", "TRIAC SCR / THYRISTOR", "DIODOS", "SMD", " " }));
        ComboBox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ComboBoxMouseClicked(evt);
            }
        });

        dbconect.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pecaseletronicas/img/dberro.png"))); // NOI18N

        Editar.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        Editar.setForeground(new java.awt.Color(0, 102, 204));
        Editar.setText("Editar");
        Editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditarActionPerformed(evt);
            }
        });

        id.setEditable(false);
        id.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(Adicionar))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3)
                        .addComponent(jLabel2)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(28, 28, 28)
                            .addComponent(dbconect))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(inputcaixa, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(id, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane1)
                            .addComponent(ComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(145, 145, 145)
                        .addComponent(Editar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 296, Short.MAX_VALUE)
                        .addComponent(Fechar)))
                .addGap(84, 84, 84))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(inputcaixa, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(dbconect))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(63, 63, 63)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Adicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Fechar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Editar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(110, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(905, 538));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void AdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AdicionarActionPerformed
        AdicionarPeca();
    }//GEN-LAST:event_AdicionarActionPerformed

    private void ComboBoxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ComboBoxMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboBoxMouseClicked

    private void FecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FecharActionPerformed
        // método para fechar a tela 
       excluirPeca();
    }//GEN-LAST:event_FecharActionPerformed

    private void EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarActionPerformed

      editar(); 
    }//GEN-LAST:event_EditarActionPerformed

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
            java.util.logging.Logger.getLogger(TelaCriacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCriacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCriacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCriacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCriacao().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JToggleButton Adicionar;
    public static javax.swing.JComboBox<String> ComboBox;
    public javax.swing.JButton Editar;
    private javax.swing.JToggleButton Fechar;
    private javax.swing.JLabel dbconect;
    public static javax.swing.JTextField id;
    public static javax.swing.JTextField inputcaixa;
    public static javax.swing.JTextArea inputpeca;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
