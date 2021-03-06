/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationlaborantinsclient;

import java.util.ArrayList;
import java.util.List;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.Topic;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import AnalyseRemote.AnalyseSBRemote;
import AnalyseRemote.Analyses;
import AnalyseRemote.Demande;
import com.sun.xml.ws.util.StringUtils;

/**
 * @author Nicolas
 */
public class Resultats extends javax.swing.JFrame {

    private static AnalyseSBRemote analyseSB;

    private Topic topic = null;
    private Connection connection = null;
    private Session session = null;
    private Demande demande = null;
    private MessageProducer producer = null;
    private DefaultListModel dlm;
    private List<Analyses> listAnal;


    /**
     * Creates new form Resultats
     */
    public Resultats() {
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

        jLabel2 = new javax.swing.JLabel();
        ana1Label = new javax.swing.JLabel();
        ana2Label = new javax.swing.JLabel();
        ana3Label = new javax.swing.JLabel();
        ana4Label = new javax.swing.JLabel();
        ana5Label = new javax.swing.JLabel();
        patientLabel = new javax.swing.JLabel();
        ana6Label = new javax.swing.JLabel();
        leucocytesTF = new javax.swing.JTextField();
        hematiesTF = new javax.swing.JTextField();
        hemoglobineTF = new javax.swing.JTextField();
        hematocritesTF = new javax.swing.JTextField();
        vgmTF = new javax.swing.JTextField();
        ccmhTF = new javax.swing.JTextField();
        ana7Label = new javax.swing.JLabel();
        ana8Label = new javax.swing.JLabel();
        ana9Label = new javax.swing.JLabel();
        tcmhTF = new javax.swing.JTextField();
        vihTF = new javax.swing.JTextField();
        globulesblancTF = new javax.swing.JTextField();
        sendButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        demandeLabel = new javax.swing.JLabel();
        medecinLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        analysesJList = new javax.swing.JList<>();

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ana1Label.setText("Leucocytes :");
        ana1Label.setEnabled(false);

        ana2Label.setText("Hématies :");
        ana2Label.setEnabled(false);

        ana3Label.setText("Hémoglobine :");
        ana3Label.setEnabled(false);

        ana4Label.setText("Hématocrites :");
        ana4Label.setEnabled(false);

        ana5Label.setText("V.G.M :");
        ana5Label.setEnabled(false);

        patientLabel.setText("Demande numero :");

        ana6Label.setText("C.C.M.H :");
        ana6Label.setEnabled(false);

        leucocytesTF.setEnabled(false);

        hematiesTF.setEnabled(false);

        hemoglobineTF.setEnabled(false);

        hematocritesTF.setEnabled(false);

        vgmTF.setEnabled(false);

        ccmhTF.setEnabled(false);

        ana7Label.setText("T.C.M.H :");
        ana7Label.setEnabled(false);

        ana8Label.setText("V.I.H :");
        ana8Label.setEnabled(false);

        ana9Label.setText("Globules blanc :");
        ana9Label.setEnabled(false);

        tcmhTF.setEnabled(false);

        vihTF.setEnabled(false);

        globulesblancTF.setEnabled(false);

        sendButton.setText("Envoyer");
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Medecin :");

        demandeLabel.setText("demande");

        medecinLabel.setText("medecin");

        analysesJList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(analysesJList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(patientLabel)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(demandeLabel)
                                .addGap(56, 56, 56)
                                .addComponent(jLabel1)
                                .addGap(8, 8, 8)
                                .addComponent(medecinLabel))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(ana6Label)
                                        .addComponent(ana4Label)
                                        .addComponent(ana2Label)
                                        .addComponent(ana1Label)
                                        .addComponent(ana5Label))
                                    .addComponent(ana3Label, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(hematocritesTF)
                                    .addComponent(vgmTF)
                                    .addComponent(ccmhTF)
                                    .addComponent(hematiesTF)
                                    .addComponent(leucocytesTF, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                                    .addComponent(hemoglobineTF))
                                .addGap(21, 21, 21)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(ana8Label)
                                    .addComponent(ana9Label)
                                    .addComponent(ana7Label))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(globulesblancTF, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                                    .addComponent(vihTF)
                                    .addComponent(tcmhTF))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(sendButton)
                        .addGap(122, 122, 122))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(patientLabel)
                    .addComponent(jLabel1)
                    .addComponent(demandeLabel)
                    .addComponent(medecinLabel))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ana1Label)
                            .addComponent(leucocytesTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ana7Label)
                            .addComponent(tcmhTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(ana2Label)
                                    .addComponent(hematiesTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ana3Label)
                                    .addComponent(hemoglobineTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(ana4Label)
                                    .addComponent(hematocritesTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(ana5Label)
                                    .addComponent(vgmTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ana6Label)
                                    .addComponent(ccmhTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(ana8Label)
                                    .addComponent(vihTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(ana9Label)
                                    .addComponent(globulesblancTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                        .addComponent(sendButton))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendButtonActionPerformed
        try {
            //Ajout du resultat de l'analyse à la DB
            //analyseSB.insertAnalyse("item", "value", 2);
            JTextField[] textField = {leucocytesTF, hematiesTF, hemoglobineTF, hematocritesTF, vgmTF, ccmhTF, tcmhTF, vihTF, globulesblancTF};
            String[] values = {"leucocytes", "hematies", "hemoglobine", "hematocrites", "vgm", "ccmh", "tcmh", "vih", "globulesblanc"};
            for (int i = 0, textFieldLength = textField.length; i < textFieldLength; i++) {
                JTextField tf = textField[i];
                if (tf.isEnabled())
                    listAnal.add(createAnalyse(values[i], tf.getText()));
            }

            for (int i = 0; i < listAnal.size(); i++) {
                analyseSB.updateAnalyse(listAnal.get(i));
            }

            analyseSB.updateDemande(demande);
            analyseSB.sendJMSMessageToAnalyseTopic(demande);
            JOptionPane.showMessageDialog(null, "Resultats de l'analyse envoyés.");
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "JMS Error : " + ex.getMessage());
        }
    }//GEN-LAST:event_sendButtonActionPerformed

    private Analyses createAnalyse(String item, String value) {
        Analyses temp = new Analyses();
        temp.setRefDemande(demande.getId());
        temp.setItem(item);
        temp.setValeur(value);
        return temp;
    }

    public Resultats(Session sess, Connection con, AnalyseSBRemote analyseBean, Demande dem) {
        initComponents();
        this.setLocationRelativeTo(null);

        connection = con;
        session = sess;
        analyseSB = analyseBean;
        demande = dem;
        dlm = new DefaultListModel();
        listAnal = new ArrayList<>();


        try {
            producer = session.createProducer(topic);
            demandeLabel.setText(Integer.toString(demande.getId()));
            medecinLabel.setText(Integer.toString(demande.getRefMedecin()));
            this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            List<Analyses> a = analyseSB.getAnalyses(demande.getId());

            for (int i = 0; i < a.size(); i++) {
                switch (a.get(i).getItem()) {
                    case "leucocytes":
                        ana1Label.setEnabled(true);
                        leucocytesTF.setEnabled(true);
                        break;
                    case "hematies":
                        ana2Label.setEnabled(true);
                        hematiesTF.setEnabled(true);
                        break;
                    case "hemoglobine":
                        ana3Label.setEnabled(true);
                        hemoglobineTF.setEnabled(true);
                        break;
                    case "hematocrites":
                        ana4Label.setEnabled(true);
                        hematocritesTF.setEnabled(true);
                        break;
                    case "vgm":
                        ana5Label.setEnabled(true);
                        vgmTF.setEnabled(true);
                        break;
                    case "ccmh":
                        ana6Label.setEnabled(true);
                        ccmhTF.setEnabled(true);
                        break;
                    case "tcmh":
                        ana7Label.setEnabled(true);
                        tcmhTF.setEnabled(true);
                        break;
                    case "vih":
                        ana8Label.setEnabled(true);
                        vihTF.setEnabled(true);
                        break;
                    case "globulesblanc":
                        ana9Label.setEnabled(true);
                        globulesblancTF.setEnabled(true);
                        break;
                    default:
                        break;
                }
                dlm.addElement(StringUtils.capitalize(a.get(i).getItem()));
            }
            analysesJList.setModel(dlm);
        } catch (JMSException ex) {
            JOptionPane.showMessageDialog(null, "JMS Error : " + ex.getMessage());
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
            java.util.logging.Logger.getLogger(Resultats.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Resultats.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Resultats.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Resultats.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                new Resultats().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ana1Label;
    private javax.swing.JLabel ana2Label;
    private javax.swing.JLabel ana3Label;
    private javax.swing.JLabel ana4Label;
    private javax.swing.JLabel ana5Label;
    private javax.swing.JLabel ana6Label;
    private javax.swing.JLabel ana7Label;
    private javax.swing.JLabel ana8Label;
    private javax.swing.JLabel ana9Label;
    private javax.swing.JList<String> analysesJList;
    private javax.swing.JTextField ccmhTF;
    private javax.swing.JLabel demandeLabel;
    private javax.swing.JTextField globulesblancTF;
    private javax.swing.JTextField hematiesTF;
    private javax.swing.JTextField hematocritesTF;
    private javax.swing.JTextField hemoglobineTF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField leucocytesTF;
    private javax.swing.JLabel medecinLabel;
    private javax.swing.JLabel patientLabel;
    private javax.swing.JButton sendButton;
    private javax.swing.JTextField tcmhTF;
    private javax.swing.JTextField vgmTF;
    private javax.swing.JTextField vihTF;
    // End of variables declaration//GEN-END:variables

}
