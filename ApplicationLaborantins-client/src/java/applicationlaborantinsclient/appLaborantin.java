/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationlaborantinsclient;

import AnalyseRemote.AnalyseSBRemote;
import AnalyseRemote.Demande;
import javax.jms.Connection;
import javax.jms.Session;
import javax.jms.Topic;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Nicolas
 */
public class appLaborantin extends javax.swing.JFrame {

    private static AnalyseSBRemote analyseSB;
    private Topic topic = null;
    private Connection connection = null;
    private Session session = null;
    
    /**
     * Creates new form appLaborantin
     */
    public appLaborantin() {
        initComponents();
        this.setLocationRelativeTo(null);
    }
    
    public appLaborantin(Topic top, Session sess, Connection con, AnalyseSBRemote analysesb) {
        initComponents();
        this.setLocationRelativeTo(null);
        
        topic = top;
        connection = con;
        session = sess;
        analyseSB = analysesb;
        
        
        analyseSB.addDemandeListener((Demande d) -> {
            // exécution dans l'EDT
            SwingUtilities.invokeLater(() -> {
                // à chaque fois que la température change, on met à jour le textfield
                newDemandeRB.setSelected(true);
                nbrDemandesLabel.setText((Integer.parseInt(nbrDemandesLabel.getText()) + 1));
            });
        });
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
        jScrollPane1 = new javax.swing.JScrollPane();
        demandesList = new javax.swing.JList<>();
        newDemandeRB = new javax.swing.JRadioButton();
        nbrDemandesLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Nouvelle demande :");

        jLabel2.setText("Liste des demandes :");

        demandesList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                demandesListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(demandesList);

        newDemandeRB.setText("New!");

        nbrDemandesLabel.setText("0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nbrDemandesLabel)
                        .addGap(40, 40, 40)
                        .addComponent(newDemandeRB)
                        .addGap(0, 176, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1)))
                .addGap(16, 16, 16))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(newDemandeRB)
                    .addComponent(nbrDemandesLabel))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void demandesListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_demandesListMouseClicked
        int index = demandesList.locationToIndex(evt.getPoint());
        ListModel dlm = demandesList.getModel();
        Demande demande = (Demande) dlm.getElementAt(index);
        
        Resultats result = new Resultats(topic, session, connection, analyseSB, demande);
        result.setVisible(true);
    }//GEN-LAST:event_demandesListMouseClicked

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
            java.util.logging.Logger.getLogger(appLaborantin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(appLaborantin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(appLaborantin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(appLaborantin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new appLaborantin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> demandesList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel nbrDemandesLabel;
    private javax.swing.JRadioButton newDemandeRB;
    // End of variables declaration//GEN-END:variables
}
