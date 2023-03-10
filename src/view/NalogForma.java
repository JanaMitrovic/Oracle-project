/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Controller;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.entity.Mesto;
import model.entity.Nalog;
import model.tables.TableModelMesto;
import model.tables.TableModelNalog;

/**
 *
 * @author HP
 */
public class NalogForma extends javax.swing.JFrame {

    /**
     * Creates new form NalogForma
     */
    public NalogForma() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        fillTable();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblNalog = new javax.swing.JTable();
        btnAddNalog = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnStavke = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblNalog.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblNalog);

        btnAddNalog.setText("Dodaj novi nalog");
        btnAddNalog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddNalogActionPerformed(evt);
            }
        });

        btnUpdate.setText("Izmeni nalog");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnStavke.setText("Stavke naloga");
        btnStavke.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStavkeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 962, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnStavke)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnUpdate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAddNalog)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddNalog)
                    .addComponent(btnUpdate)
                    .addComponent(btnStavke))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddNalogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddNalogActionPerformed
        Nalog n = new Nalog();
        AddNalogForma form = new AddNalogForma(this, true, n, "INSERT");
        form.setVisible(true);
    }//GEN-LAST:event_btnAddNalogActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        if(tblNalog.getSelectedRow() < 0){
            JOptionPane.showMessageDialog(this, "Morate izabrati nalog koji zelite da izmenite!", "Greska", JOptionPane.ERROR_MESSAGE);
        }else{
            try {
                Nalog n = ((TableModelNalog) tblNalog.getModel()).getSelectedObject(tblNalog.getSelectedRow());
                AddNalogForma form = new AddNalogForma(this, true, n, "UPDATE");
                form.setVisible(true);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnStavkeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStavkeActionPerformed
        if(tblNalog.getSelectedRow() < 0){
            JOptionPane.showMessageDialog(this, "Morate izabrati nalog!", "Greska", JOptionPane.ERROR_MESSAGE);
        }else{
            try {
                Nalog n = ((TableModelNalog) tblNalog.getModel()).getSelectedObject(tblNalog.getSelectedRow());
                StavkeNalogaForma form = new StavkeNalogaForma(this, true, n);
                form.setVisible(true);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnStavkeActionPerformed

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
            java.util.logging.Logger.getLogger(NalogForma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NalogForma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NalogForma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NalogForma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NalogForma().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddNalog;
    private javax.swing.JButton btnStavke;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblNalog;
    // End of variables declaration//GEN-END:variables

    private void fillTable() {
        try {
            List<Nalog> list = Controller.getInstance().getNalogList();
            TableModelNalog modelN = new TableModelNalog(list);
            tblNalog.setModel(modelN);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Greska prilikom ucitavanja!", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }
    
    public void refreshTable(){
        List<Nalog> shownN = loadObjects();
        TableModelNalog modelN = (TableModelNalog) tblNalog.getModel();
        modelN.setList(shownN);
        modelN.fireTableDataChanged();
    }
    
     private List<Nalog> loadObjects() {
        try {
            return Controller.getInstance().getNalogList();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
}
