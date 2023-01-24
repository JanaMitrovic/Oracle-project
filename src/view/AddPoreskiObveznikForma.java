/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Controller;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.entity.Broj;
import model.entity.Mesto;
import model.entity.PoreskiObveznik;
import model.entity.Ulica;

/**
 *
 * @author HP
 */
public class AddPoreskiObveznikForma extends javax.swing.JFrame {

    String operation;
    PoreskiObveznikForma parent;
    PoreskiObveznik po;
    /**
     * Creates new form AddPoreskiObveznikForm
     */
    public AddPoreskiObveznikForma(PoreskiObveznikForma parent, boolean modal, PoreskiObveznik po, String operation) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.parent = parent;
        this.po = po;
        this.operation = operation;
        fillCmbMesto();
        if (operation.equals("UPDATE")) {
            adjustFields(po);
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
        jLabel6 = new javax.swing.JLabel();
        txtPIB = new javax.swing.JTextField();
        txtTip = new javax.swing.JTextField();
        txtIme = new javax.swing.JTextField();
        txtUlica = new javax.swing.JTextField();
        txtBroj = new javax.swing.JTextField();
        cmbMesto = new javax.swing.JComboBox<>();
        btnAdd = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("PIB:");

        jLabel2.setText("Ime:");

        jLabel3.setText("Tip:");

        jLabel4.setText("Ulica:");

        jLabel5.setText("Broj:");

        jLabel6.setText("Mesto:");

        cmbMesto.setEditable(true);

        btnAdd.setText("Dodaj poreskog obveznika");
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
                        .addGap(40, 40, 40)
                        .addComponent(txtPIB))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAdd))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6)
                            .addComponent(jLabel2))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtIme)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtUlica, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtBroj, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE))
                            .addComponent(txtTip)
                            .addComponent(cmbMesto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtPIB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtIme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(txtUlica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBroj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cmbMesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addComponent(btnAdd)
                .addContainerGap(59, Short.MAX_VALUE))
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
            java.util.logging.Logger.getLogger(AddPoreskiObveznikForma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddPoreskiObveznikForma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddPoreskiObveznikForma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddPoreskiObveznikForma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new AddPoreskiObveznikForma().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JComboBox<Mesto> cmbMesto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField txtBroj;
    private javax.swing.JTextField txtIme;
    private javax.swing.JTextField txtPIB;
    private javax.swing.JTextField txtTip;
    private javax.swing.JTextField txtUlica;
    // End of variables declaration//GEN-END:variables

    private void adjustFields(PoreskiObveznik po) {
        this.txtPIB.setText(String.valueOf(po.getPIB()));
        this.txtPIB.setEnabled(false);
        this.txtIme.setText(po.getIme());
        this.txtTip.setText(po.getTip());
        this.txtUlica.setText(po.getBroj().getUlica().getNaziv());
        this.txtBroj.setText(po.getBroj().getBroj());
        this.cmbMesto.setSelectedItem(po.getBroj().getUlica().getMesto());
        System.out.println(cmbMesto.getSelectedItem());
        this.btnAdd.setText("Izmeni poreskog obveznika");
    }

    private void fillCmbMesto() {
        try {
            cmbMesto.removeAllItems();
            List<Mesto> mesta = Controller.getInstance().getMestaList();
            for (Mesto mesto : mesta) {
                cmbMesto.addItem(mesto);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Greska prilikom ucitavanja mesta!", "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void insert() throws Exception {
        if (txtPIB.getText().isEmpty() || txtIme.getText().isEmpty() || txtTip.getText().isEmpty() || txtUlica.getText().isEmpty() || txtBroj.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Morate popuniti sva polja!");
            return;
        }

        int PIB = Integer.parseInt(txtPIB.getText());
        String ime = txtIme.getText();
        String tip = txtTip.getText();
        String nazivUlice = txtUlica.getText();
        String br = txtBroj.getText();
        Mesto mesto = (Mesto) cmbMesto.getSelectedItem();
        
        PoreskiObveznik po = new PoreskiObveznik(PIB, null, ime, tip);
        
        if (Controller.getInstance().exists(po) == 1) {
            JOptionPane.showMessageDialog(this, "Ovakav rekord vac postoji!");
            this.setVisible(false);
            dispose();
            return;
        }
        
        Ulica ulica = new Ulica(0, nazivUlice, mesto);
        if(Controller.getInstance().exists(ulica) == 0){
            Controller.getInstance().insert(ulica);
        }
        
        int IdUlica = Controller.getInstance().getId(ulica);
        ulica.setIdUlica(IdUlica);
        
        Broj broj = new Broj(0, br, ulica);
        if(Controller.getInstance().exists(broj) == 0){
            Controller.getInstance().insert(broj);
        }
        
        int IdBroj = Controller.getInstance().getId(broj);
        broj.setIdBroj(IdBroj);
        
        po.setBroj(broj);
        
        Controller.getInstance().insert2(po);
        JOptionPane.showMessageDialog(this, "Uspesno dodat novi poreski obveznik!", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
        parent.refreshTable();
        this.setVisible(false);
        dispose();
    }

    private void update() throws Exception {
        if (txtPIB.getText().isEmpty() || txtIme.getText().isEmpty() || txtTip.getText().isEmpty() || txtUlica.getText().isEmpty() || txtBroj.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Morate popuniti sva polja!");
            return;
        }

        int PIB = Integer.parseInt(txtPIB.getText());
        String ime = txtIme.getText();
        String tip = txtTip.getText();
        String nazivUlice = txtUlica.getText();
        String br = txtBroj.getText();
        Mesto mesto = (Mesto) cmbMesto.getSelectedItem();
        
        PoreskiObveznik po = new PoreskiObveznik(PIB, null, ime, tip);
        
        Ulica ulica = new Ulica(0, nazivUlice, mesto);
        if(Controller.getInstance().exists(ulica) == 0){
            Controller.getInstance().insert(ulica);
        }
        
        int IdUlica = Controller.getInstance().getId(ulica);
        ulica.setIdUlica(IdUlica);
        
        Broj broj = new Broj(0, br, ulica);
        if(Controller.getInstance().exists(broj) == 0){
            Controller.getInstance().insert(broj);
        }
        
        int IdBroj = Controller.getInstance().getId(broj);
        broj.setIdBroj(IdBroj);
        
        po.setBroj(broj);
        
        Controller.getInstance().update(po);
        JOptionPane.showMessageDialog(this, "Uspesno ste izmenili poreskog obveznika!", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
        parent.refreshTable();
        this.setVisible(false);
        dispose();
    }
}
