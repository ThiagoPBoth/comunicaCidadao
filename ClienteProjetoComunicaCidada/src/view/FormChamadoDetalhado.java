/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import modelDominio.Chamado;


/**
 *
 * @author joao.pedro_combateaf
 */
public class FormChamadoDetalhado extends javax.swing.JDialog {

    private Chamado chamado;
    /**
     * Creates new form FormChamadoDetalhado
     */
    public FormChamadoDetalhado(Chamado chamado) {
        
        this.chamado = chamado;
        
        initComponents();
         
       if (chamado.getImagem() != null) {
         
           int targetWidth = 450; // largura desejada da imagem redimensionada
        int targetHeight = 450; // altura desejada da imagem redimensionada

    try {
        byte[] imagemRedimensionada = resizeImage(chamado.getImagem(), targetWidth, targetHeight);
        ImageIcon icon = new ImageIcon(imagemRedimensionada);
        jLabelImagem.setIcon(icon);

     //faça algo com a imagem redimensionada
    } catch (IOException e) {
        System.out.println(e);
    // trate a exceção de entrada/saída
    }
           
           
        }
        
        
              
        
        jTextAreaDescricao.setEditable(false);
        jLabelIdChamado.setText(String.valueOf(chamado.getIdChamado()));
        jLabelTitulo.setText(chamado.getTitulo());
        jLabelSetor.setText(chamado.getSetor());
        jTextAreaDescricao.setText(chamado.getDescricao());
        
        if (chamado.getStatus()==0){
            jRadioButtonNaoSolucionado.setSelected(true);
            
        } else if (chamado.getStatus()==1){
            jRadioButtonEmProcesso.setSelected(true);
            
        } else if (chamado.getStatus()==2) {
            jRadioButtonSolucionado.setSelected(true);
        }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jLabelSetor = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabelTitulo = new javax.swing.JLabel();
        jLabelIdChamado = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButtonVoltar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaDescricao = new javax.swing.JTextArea();
        jLabelImagem = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButtonAlterarStatus = new javax.swing.JButton();
        jRadioButtonSolucionado = new javax.swing.JRadioButton();
        jRadioButtonEmProcesso = new javax.swing.JRadioButton();
        jRadioButtonNaoSolucionado = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelSetor.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel2.add(jLabelSetor, new org.netbeans.lib.awtextra.AbsoluteConstraints(119, 181, 141, 32));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel3.setText("Descrição:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 225, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel5.setText("Setor:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 181, -1, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Título:");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 137, -1, -1));

        jLabelTitulo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel2.add(jLabelTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(117, 137, 141, 32));

        jLabelIdChamado.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel2.add(jLabelIdChamado, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 99, 60, 32));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel4.setText("ID do chamado:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 99, -1, -1));

        jButtonVoltar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButtonVoltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/viewImages/icons8-esquerda-50.png"))); // NOI18N
        jButtonVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVoltarActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonVoltar, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 27, 75, 38));

        jTextAreaDescricao.setEditable(false);
        jTextAreaDescricao.setBackground(new java.awt.Color(255, 255, 255));
        jTextAreaDescricao.setColumns(20);
        jTextAreaDescricao.setLineWrap(true);
        jTextAreaDescricao.setRows(5);
        jTextAreaDescricao.setWrapStyleWord(true);
        jScrollPane1.setViewportView(jTextAreaDescricao);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(43, 269, 269, 115));
        jPanel2.add(jLabelImagem, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 150, 435, 354));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setText("Status:");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(42, 402, -1, -1));

        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jButtonAlterarStatus.setBackground(new java.awt.Color(204, 204, 204));
        jButtonAlterarStatus.setText("ALTERAR STATUS");
        jButtonAlterarStatus.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jButtonAlterarStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlterarStatusActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButtonSolucionado);
        jRadioButtonSolucionado.setText("Solucionado");
        jRadioButtonSolucionado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonSolucionadoActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButtonEmProcesso);
        jRadioButtonEmProcesso.setText("Em processo para solucionar");

        buttonGroup1.add(jRadioButtonNaoSolucionado);
        jRadioButtonNaoSolucionado.setText("Não solucionado");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(101, 101, 101)
                .addComponent(jButtonAlterarStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jRadioButtonNaoSolucionado)
                .addGap(18, 18, 18)
                .addComponent(jRadioButtonEmProcesso)
                .addGap(12, 12, 12)
                .addComponent(jRadioButtonSolucionado, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(356, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButtonSolucionado)
                    .addComponent(jRadioButtonEmProcesso)
                    .addComponent(jRadioButtonNaoSolucionado))
                .addGap(18, 18, 18)
                .addComponent(jButtonAlterarStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 430, 450));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel6.setText("Imagem do Chaamdo:");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 80, -1, -1));

        jLabel7.setText(" (Nem todos os chamados possuem Imagem cadastrada)");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 120, -1, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 6, 940, 570));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVoltarActionPerformed
        // TODO add your handling code here:
        this.dispose();
        
    }//GEN-LAST:event_jButtonVoltarActionPerformed

    private void jRadioButtonSolucionadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonSolucionadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButtonSolucionadoActionPerformed

    private void jButtonAlterarStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarStatusActionPerformed
        // TODO add your handling code here:
        int status = -1;
        
        
        jRadioButtonNaoSolucionado.toString();
        
        if (jRadioButtonNaoSolucionado.isSelected()){
            
             status = 0;
            
            
        } else if (jRadioButtonEmProcesso.isSelected() ){
            
             status = 1;
            
        } else if ( jRadioButtonSolucionado.isSelected()) {
            
             status = 2;
            
        }
            Chamado chamado = new Chamado(Integer.parseInt(jLabelIdChamado.getText()), status);

            Boolean result =ClienteComunicaCidadao.ccont.alterarChamado(chamado);

            if (!result) {
                JOptionPane.showMessageDialog(this, "Erro ao alterar a chamado");
            } else {
                dispose();
            }
        
    }//GEN-LAST:event_jButtonAlterarStatusActionPerformed

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
            java.util.logging.Logger.getLogger(FormChamadoDetalhado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormChamadoDetalhado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormChamadoDetalhado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormChamadoDetalhado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormChamadoDetalhado(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButtonAlterarStatus;
    private javax.swing.JButton jButtonVoltar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabelIdChamado;
    private javax.swing.JLabel jLabelImagem;
    private javax.swing.JLabel jLabelSetor;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRadioButtonEmProcesso;
    private javax.swing.JRadioButton jRadioButtonNaoSolucionado;
    private javax.swing.JRadioButton jRadioButtonSolucionado;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaDescricao;
    // End of variables declaration//GEN-END:variables


public static byte[] resizeImage(byte[] originalImage, int targetWidth, int targetHeight) throws IOException {
    BufferedImage originalBufferedImage = ImageIO.read(new ByteArrayInputStream(originalImage));
    int type = originalBufferedImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalBufferedImage.getType();
    BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, type);
    Graphics2D g = resizedImage.createGraphics();
    g.drawImage(originalBufferedImage, 0, 0, targetWidth, targetHeight, null);
    g.dispose();
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    ImageIO.write(resizedImage, "png", baos);
    baos.flush();
    byte[] resizedImageInByte = baos.toByteArray();
    baos.close();
    return resizedImageInByte;
}


}




