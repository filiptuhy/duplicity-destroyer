/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import src.Files;

/**
 *
 * @author filipovsluha
 */
public class GuiJFrame extends javax.swing.JFrame {
    
 private final static Logger logger = Logger.getLogger(GuiJFrame.class.getName());
 private static FileHandler fileHandler = null;


Files files;
List<String> duplicatezzList = new ArrayList<>();
int mp3Number = 1;

    public GuiJFrame() {
        files = new Files();
        initComponents();
        
        try {
            fileHandler=new FileHandler("loggerExample.log", false);
            } catch (SecurityException | IOException e) {
                e.printStackTrace();
            }
            Logger logger1 = Logger.getLogger("");
            fileHandler.setFormatter(new SimpleFormatter());
            logger1.addHandler(fileHandler);
            logger1.setLevel(Level.CONFIG);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        OpenFolderButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        DeleteSelectedButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        OpenFolderButton.setText("Open Folder");
        OpenFolderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OpenFolderButtonActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null}
            },
            new String [] {
                "Name", "Path", "Delete"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable1);

        DeleteSelectedButton.setText("Delete selected");
        DeleteSelectedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteSelectedButtonActionPerformed(evt);
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
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 587, Short.MAX_VALUE)
                        .addGap(44, 44, 44))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(OpenFolderButton)
                        .addGap(18, 18, 18)
                        .addComponent(DeleteSelectedButton)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(OpenFolderButton)
                    .addComponent(DeleteSelectedButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

  
       
public void showDuplicatesInTable()
{ 
    DefaultTableModel  myModel = (DefaultTableModel ) jTable1.getModel();
    int i = myModel.getRowCount()-1;
    while (myModel.getRowCount() > 0) 
    {        
             myModel.removeRow(i);
             i--;

    }
         
     //myModel.fireTableDataChanged();
    
    for(String dupo : files.getDuplicates())
    {
        String[] name = dupo.split("\\\\");        
        myModel.addRow(new Object[]{name[name.length-1],dupo, false});   
    }
    //myModel.fireTableDataChanged();
}

    File selectedFile;
    private void OpenFolderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OpenFolderButtonActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) 
        {
           selectedFile = fileChooser.getSelectedFile();
          System.out.println(selectedFile.getName());         
          
          mp3Number = files.addAllFromFolder(new File(selectedFile.getAbsolutePath()));
          showDuplicatesInTable();
        }
    }//GEN-LAST:event_OpenFolderButtonActionPerformed

    private void DeleteSelectedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteSelectedButtonActionPerformed
        final TableModel model = jTable1.getModel();
        int numberOfDeletedFilez = 0;
        int reply = JOptionPane.showConfirmDialog(null, "Are you sure that you want to delete all ?", "Delete duplicates: " , JOptionPane.INFORMATION_MESSAGE);
        if (reply == JOptionPane.YES_OPTION) 
        {
            for (int i =1; i < model.getRowCount(); i++) 
            {
                for (int j = 1; j <model.getColumnCount(); j++) 
                {
                    if (model.getValueAt(i, j).equals(true)) 
                    {
                        String fileToBeDeleted = (String) model.getValueAt(i, 0);
                        jTextArea1.append("Deleted "+fileToBeDeleted+"\n");
                        File file = new File((String) model.getValueAt(i, 1));
                        file.delete();
                        numberOfDeletedFilez++;
                        logger.log(Level.INFO, "File deleted: {0}", fileToBeDeleted);
                    }
                }
            } 
         JOptionPane.showMessageDialog(null, numberOfDeletedFilez+" files were succesfully deleted.", "Deleted " , JOptionPane.INFORMATION_MESSAGE);        
         mp3Number = files.addAllFromFolder(new File(selectedFile.getAbsolutePath()));
         showDuplicatesInTable();
        }
    }//GEN-LAST:event_DeleteSelectedButtonActionPerformed
    
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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GuiJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GuiJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GuiJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GuiJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GuiJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton DeleteSelectedButton;
    private javax.swing.JButton OpenFolderButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}