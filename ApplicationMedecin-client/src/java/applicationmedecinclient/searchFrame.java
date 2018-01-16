/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationmedecinclient;

import AnalyseRemote.AnalyseSBRemote;
import AnalyseRemote.Demande;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.Topic;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListModel;
import patientRemote.Patient;
import patientRemote.patientSBRemote;

/**
 *
 * @author Nicolas
 */
public class searchFrame extends javax.swing.JFrame implements MessageListener {

    private static patientSBRemote patientSB;
    private static AnalyseSBRemote analyseSB;
    private MessageConsumer consumer = null;
    private DefaultListModel dlmResultat;
    
    /**
     * Creates new form searchFrame
     */
    public searchFrame() {
        initComponents();
        this.setLocationRelativeTo(null);
    }
    
    public searchFrame(patientSBRemote patientBean, AnalyseSBRemote analyseBean, Session sess, Topic topic) {
        
        try {
            Image image=(new javax.swing.ImageIcon(getClass().getResource("/Images/sang.jpg"))).getImage();
            this.setContentPane(new ImagePanel(image));
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.pack();
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
        patientSB = patientBean;
        analyseSB = analyseBean;
        listPanel.setVisible(false);
        dlmResultat = new DefaultListModel();
        resultatsPanel.setVisible(true);
        
        try{
            consumer = sess.createConsumer(topic);
            consumer.setMessageListener(this);
        }catch(Exception ex){
        System.out.println(ex.getMessage());
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
        patientTF = new javax.swing.JTextField();
        listPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        patientsJList = new javax.swing.JList<>();
        searchButton = new javax.swing.JButton();
        resultatsPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        resultatJList = new javax.swing.JList<>();
        hideButton = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        showResultatJMItem = new javax.swing.JMenu();
        addPatient = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel1.setText("Rechercher un patient :");

        patientsJList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "nom prenom" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        patientsJList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                patientsJListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(patientsJList);

        javax.swing.GroupLayout listPanelLayout = new javax.swing.GroupLayout(listPanel);
        listPanel.setLayout(listPanelLayout);
        listPanelLayout.setHorizontalGroup(
            listPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, listPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        listPanelLayout.setVerticalGroup(
            listPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(listPanelLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 24, Short.MAX_VALUE))
        );

        searchButton.setText("Rechercher");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        jLabel2.setText("Resultats disponibles :");

        resultatJList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Pas de résultats disponibles" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(resultatJList);

        hideButton.setText("X");
        hideButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hideButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout resultatsPanelLayout = new javax.swing.GroupLayout(resultatsPanel);
        resultatsPanel.setLayout(resultatsPanelLayout);
        resultatsPanelLayout.setHorizontalGroup(
            resultatsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(resultatsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(resultatsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(resultatsPanelLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
                        .addComponent(hideButton))
                    .addGroup(resultatsPanelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane2)))
                .addContainerGap())
        );
        resultatsPanelLayout.setVerticalGroup(
            resultatsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(resultatsPanelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(resultatsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(hideButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                .addContainerGap())
        );

        showResultatJMItem.setText("Options");

        addPatient.setText("Ajout patient");
        addPatient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPatientActionPerformed(evt);
            }
        });
        showResultatJMItem.add(addPatient);

        jMenuItem4.setText("Resultats");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        showResultatJMItem.add(jMenuItem4);

        jMenuBar1.add(showResultatJMItem);

        jMenu2.setText("Help?");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(174, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(patientTF, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(searchButton)
                                    .addGap(50, 50, 50))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(9, 9, 9)
                                    .addComponent(listPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(jLabel1)))
                        .addGap(199, 199, 199))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(resultatsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(161, 161, 161))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(patientTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(searchButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(listPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(resultatsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        DefaultListModel dlm = new DefaultListModel();
        
        listPanel.setVisible(true);
        
        String[] parts = patientTF.getText().split(" ");
        
        List<Patient> p = patientSB.getPatients(parts[0], parts.length >= 2 ? parts[1] : " ");

        for(int i = 0; i < p.size(); i++)
        {
            dlm.addElement(p.get(i));
            patientsJList.setModel(dlm);
        }

    }//GEN-LAST:event_searchButtonActionPerformed

    private void patientsJListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_patientsJListMouseClicked
        int index = patientsJList.locationToIndex(evt.getPoint());
        ListModel dlm = patientsJList.getModel();
        Patient patient = (Patient) dlm.getElementAt(index);
        
        analysesFrame anal = new analysesFrame(patient, analyseSB, patientSB);
        anal.setVisible(true);
    }//GEN-LAST:event_patientsJListMouseClicked

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        resultatsPanel.setVisible(true);
        //this.setSize (470, 415);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void hideButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hideButtonActionPerformed
        resultatsPanel.setVisible(false);
        //this.setSize (470, 312);
    }//GEN-LAST:event_hideButtonActionPerformed

    private void addPatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPatientActionPerformed
        addPatientJDialog patientJD = new addPatientJDialog(this, true);
        
        if(patientJD.isOK() == true){
            Patient p = new Patient();
            p.setNom(patientJD.getNom());
            p.setPrenom(patientJD.getPrenom());
            p.setAdresse(patientJD.getAdresse());
            p.setLogin(p.getNom() + p.getPrenom());
            patientSB.insertPatient(p);
        }
        
        patientJD.dispose();
    }//GEN-LAST:event_addPatientActionPerformed

    @Override
    public void onMessage(Message message){
        
        try{
            resultatsPanel.setVisible(true);
            //Ajout de la demande à la liste
            ObjectMessage objMessage = (ObjectMessage) message;
            Demande dem = (Demande) objMessage.getObject();
            System.out.println("Demande id : " + dem.getId());
            List<Patient> lp = new ArrayList<Patient>();
            lp = patientSB.getPatientById(dem.getRefPatient());
            dlmResultat.addElement("Résultats disponibles pour : " + lp.get(0));
            resultatJList.setModel(dlmResultat);
            
 
        }catch(Exception ex){
            System.out.println("Jms reception error : " + ex);
        }
    }
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
            java.util.logging.Logger.getLogger(searchFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(searchFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(searchFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(searchFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new searchFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem addPatient;
    private javax.swing.JButton hideButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel listPanel;
    private javax.swing.JTextField patientTF;
    private javax.swing.JList<String> patientsJList;
    private javax.swing.JList<String> resultatJList;
    private javax.swing.JPanel resultatsPanel;
    private javax.swing.JButton searchButton;
    private javax.swing.JMenu showResultatJMItem;
    // End of variables declaration//GEN-END:variables
}
