/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

/**
 *
 * @author HP
 */
public class MainForm extends javax.swing.JFrame {

    /**
     * Creates new form MainForm
     */
    public MainForm() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        btnNalozi = new javax.swing.JButton();
        btnResenja = new javax.swing.JButton();
        bntZapisnici = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jmiPlatniPromet = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jmiPoreskiObveznik = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jmiMesto = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jmiFilijala = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        jmiRoba = new javax.swing.JMenuItem();

        jMenu2.setText("jMenu2");

        jMenuItem1.setText("jMenuItem1");

        jMenuItem2.setText("jMenuItem2");

        jMenu4.setText("jMenu4");

        jMenuItem3.setText("jMenuItem3");

        jMenuItem4.setText("jMenuItem4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnNalozi.setText("Nalozi");
        btnNalozi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNaloziActionPerformed(evt);
            }
        });

        btnResenja.setText("Resenja");
        btnResenja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResenjaActionPerformed(evt);
            }
        });

        bntZapisnici.setText("Zapisnici");
        bntZapisnici.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntZapisniciActionPerformed(evt);
            }
        });

        jMenu1.setText("Platni promet");

        jmiPlatniPromet.setText("Pregled");
        jmiPlatniPromet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiPlatniPrometActionPerformed(evt);
            }
        });
        jMenu1.add(jmiPlatniPromet);

        jMenuBar1.add(jMenu1);

        jMenu3.setText("Poreski obveznik");

        jmiPoreskiObveznik.setText("Pregled");
        jmiPoreskiObveznik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiPoreskiObveznikActionPerformed(evt);
            }
        });
        jMenu3.add(jmiPoreskiObveznik);

        jMenuBar1.add(jMenu3);

        jMenu5.setText("Mesto");

        jmiMesto.setText("Pregled");
        jmiMesto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiMestoActionPerformed(evt);
            }
        });
        jMenu5.add(jmiMesto);

        jMenuBar1.add(jMenu5);

        jMenu6.setText("Filijala");

        jmiFilijala.setText("Pregled");
        jmiFilijala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiFilijalaActionPerformed(evt);
            }
        });
        jMenu6.add(jmiFilijala);

        jMenuBar1.add(jMenu6);

        jMenu7.setText("Roba");

        jmiRoba.setText("Pregled");
        jmiRoba.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiRobaActionPerformed(evt);
            }
        });
        jMenu7.add(jmiRoba);

        jMenuBar1.add(jMenu7);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNalozi, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                    .addComponent(btnResenja, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                    .addComponent(bntZapisnici, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(58, Short.MAX_VALUE)
                .addComponent(btnNalozi, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnResenja, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bntZapisnici, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jmiPlatniPrometActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiPlatniPrometActionPerformed
        PlatniPrometForma form = new PlatniPrometForma();
        form.setVisible(true);
    }//GEN-LAST:event_jmiPlatniPrometActionPerformed

    private void jmiPoreskiObveznikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiPoreskiObveznikActionPerformed
        PoreskiObveznikForma form = new PoreskiObveznikForma();
        form.setVisible(true);
    }//GEN-LAST:event_jmiPoreskiObveznikActionPerformed

    private void jmiMestoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiMestoActionPerformed
        MestoForma form = new MestoForma();
        form.setVisible(true);
    }//GEN-LAST:event_jmiMestoActionPerformed

    private void jmiFilijalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiFilijalaActionPerformed
        FilijalaForma form = new FilijalaForma();
        form.setVisible(true);
    }//GEN-LAST:event_jmiFilijalaActionPerformed

    private void btnNaloziActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNaloziActionPerformed
       NalogForma form = new NalogForma();
       form.setVisible(true);
    }//GEN-LAST:event_btnNaloziActionPerformed

    private void btnResenjaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResenjaActionPerformed
        ResenjeForma form = new ResenjeForma();
        form.setVisible(true);
    }//GEN-LAST:event_btnResenjaActionPerformed

    private void bntZapisniciActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntZapisniciActionPerformed
        ZapisnikForma form = new ZapisnikForma();
        form.setVisible(true);
    }//GEN-LAST:event_bntZapisniciActionPerformed

    private void jmiRobaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiRobaActionPerformed
        RobaForma form = new RobaForma();
        form.setVisible(true);
    }//GEN-LAST:event_jmiRobaActionPerformed

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
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntZapisnici;
    private javax.swing.JButton btnNalozi;
    private javax.swing.JButton btnResenja;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jmiFilijala;
    private javax.swing.JMenuItem jmiMesto;
    private javax.swing.JMenuItem jmiPlatniPromet;
    private javax.swing.JMenuItem jmiPoreskiObveznik;
    private javax.swing.JMenuItem jmiRoba;
    // End of variables declaration//GEN-END:variables
}