/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Controller;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.entity.StavkaNaloga;
import model.entity.StavkaResenja;
import model.entity.VrstaPrihoda;

/**
 *
 * @author HP
 */
public class AddStavkaResenjaForma extends javax.swing.JFrame {

    String operation;
    StavkeResenjaForma parent;
    StavkaResenja sr;
    
    /**
     * Creates new form AddStavkaResenjaForma
     */
    public AddStavkaResenjaForma(StavkeResenjaForma parent, boolean modal, StavkaResenja sr, String operation) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.parent = parent;
        this.sr = sr;
        this.operation = operation;
        
        fillCmbVrstaPrihoda();
        
        if (operation.equals("UPDATE")) {
            adjustFields(sr);
        } else {
            try {
                int rb = Controller.getInstance().getNextBroj(sr);
                txtRb.setText(String.valueOf(++rb));
                txtRb.setEnabled(false);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Greska prilikom ucitavanja rednog broja!", "Greska", JOptionPane.ERROR_MESSAGE);
            }
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtRb = new javax.swing.JTextField();
        txtDatumOd = new javax.swing.JTextField();
        txtDatumDo = new javax.swing.JTextField();
        txtIznos = new javax.swing.JTextField();
        cmbVrstaPrihoda = new javax.swing.JComboBox();
        btnAdd = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Rb:");

        jLabel2.setText("DatumOd:");

        jLabel3.setText("DatumDo:");

        jLabel4.setText("Vrsta prihoda:");

        jLabel5.setText("Iznos:");

        cmbVrstaPrihoda.setEditable(true);

        btnAdd.setText("Dodaj stavku");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(117, 117, 117)
                        .addComponent(txtRb))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(79, 79, 79)
                        .addComponent(txtDatumOd, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(80, 80, 80)
                        .addComponent(txtDatumDo, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4))
                        .addGap(55, 55, 55)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtIznos, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
                            .addComponent(cmbVrstaPrihoda, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAdd)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtRb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtDatumOd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtDatumDo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cmbVrstaPrihoda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtIznos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnAdd)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        try {
            if (this.operation.equals("INSERT")) {
                this.insert();
            } else if (this.operation.equals("UPDATE")) {
                this.update();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAddActionPerformed

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
            java.util.logging.Logger.getLogger(AddStavkaResenjaForma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddStavkaResenjaForma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddStavkaResenjaForma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddStavkaResenjaForma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new AddStavkaResenjaForma().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JComboBox cmbVrstaPrihoda;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField txtDatumDo;
    private javax.swing.JTextField txtDatumOd;
    private javax.swing.JTextField txtIznos;
    private javax.swing.JTextField txtRb;
    // End of variables declaration//GEN-END:variables

    private void adjustFields(StavkaResenja sr) {
        txtRb.setText(String.valueOf(sr.getRb()));
        txtRb.setEnabled(false);
        txtDatumOd.setText(String.valueOf(sr.getDatumOd()));
        txtDatumDo.setText(String.valueOf(sr.getDatumDo()));
        cmbVrstaPrihoda.setSelectedItem(sr.getVrstaPrihoda());
        txtIznos.setText(String.valueOf(sr.getIznos()));
        btnAdd.setText("Izmeni stavku");
    }

    private void fillCmbVrstaPrihoda() {
        try {
            cmbVrstaPrihoda.removeAllItems();
            List<VrstaPrihoda> list = Controller.getInstance().getVrstaPrihodaList();
            for (VrstaPrihoda vrstaPrihoda : list) {
                cmbVrstaPrihoda.addItem(vrstaPrihoda);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Greska prilikom ucitavanja vrsta prihoda!", "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void insert() throws Exception {
        if (txtDatumOd.getText().isEmpty() || txtDatumDo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Morate popuniti sva polja!");
            return;
        }
        
        int rb = Integer.parseInt(txtRb.getText());
        String dod = txtDatumOd.getText();
        String ddo = txtDatumDo.getText();
        VrstaPrihoda vp = (VrstaPrihoda) cmbVrstaPrihoda.getSelectedItem();
        double iznos = Double.parseDouble(txtIznos.getText());
        
        Date datumOd = new Date();
        Date datumDo = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            datumOd = sdf.parse(dod);
            datumDo = sdf.parse(ddo);
        } catch (ParseException ex) {
            Logger.getLogger(AddNalogForma.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Datum mora biti u odgovarajucem formatu!");
            return;
        }
        
        sr.setRb(rb);
        sr.setDatumOd(datumOd);
        sr.setDatumDo(datumDo);
        sr.setVrstaPrihoda(vp);
        sr.setIznos(iznos);
        
        Controller.getInstance().insert2(sr);
        JOptionPane.showMessageDialog(this, "Uspesno dodata stavka resenja!", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
        parent.refreshTable();
        this.setVisible(false);
        dispose();
    }

    private void update() throws Exception {
        if (txtDatumOd.getText().isEmpty() || txtDatumDo.getText().isEmpty() || txtIznos.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Morate popuniti sva polja!");
            return;
        }
        
        int rb = Integer.parseInt(txtRb.getText());
        String dod = txtDatumOd.getText();
        String ddo = txtDatumDo.getText();
        VrstaPrihoda vp = (VrstaPrihoda) cmbVrstaPrihoda.getSelectedItem();
        double iznos = Double.parseDouble(txtIznos.getText());
        
        Date datumOd = new Date();
        Date datumDo = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            datumOd = sdf.parse(dod);
            datumDo = sdf.parse(ddo);
        } catch (ParseException ex) {
            Logger.getLogger(AddNalogForma.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Datum mora biti u odgovarajucem formatu!");
            return;
        }
        
        StavkaResenja stavka = new StavkaResenja(rb, sr.getResenje(), datumOd, datumDo, vp, iznos);
        
        Controller.getInstance().update(stavka);
        JOptionPane.showMessageDialog(this, "Uspesno izmenjena stavka!", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
        parent.refreshTable();
        this.setVisible(false);
        dispose();
    }
}
