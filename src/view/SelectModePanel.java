package view;

import controller.SelectModeController;
import java.awt.Color;

public class SelectModePanel extends javax.swing.JPanel {
    private final SelectModeController controller;
    
    public SelectModePanel(ShowModePanel showModePanel) {
        initComponents();
        controller = new SelectModeController(showModePanel);

        basicModeBtn.addActionListener(controller);
        advanceModeBtn.addActionListener(controller);

    }
    


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        advanceModeBtn = new javax.swing.JButton();
        basicModeBtn = new javax.swing.JButton();

        setBackground(new java.awt.Color(153, 153, 255));
        setMaximumSize(null);
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(129, 283));

        advanceModeBtn.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        advanceModeBtn.setText("Nâng cao");
        advanceModeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                advanceModeBtnActionPerformed(evt);
            }
        });

        basicModeBtn.setBackground(new java.awt.Color(255, 255, 0));
        basicModeBtn.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        basicModeBtn.setText("Cơ bản");
        basicModeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                basicModeBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(advanceModeBtn)
                    .addComponent(basicModeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(146, 146, 146)
                .addComponent(basicModeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(advanceModeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void basicModeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_basicModeBtnActionPerformed
        // TODO add your handling code here:
        basicModeBtn.setBackground(new Color(255,255,0));
        advanceModeBtn.setBackground(new Color(255,255,255));
    }//GEN-LAST:event_basicModeBtnActionPerformed

    private void advanceModeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_advanceModeBtnActionPerformed
        // TODO add your handling code here:
        basicModeBtn.setBackground(new Color(255,255,255));
        advanceModeBtn.setBackground(new Color(255,255,0));
    }//GEN-LAST:event_advanceModeBtnActionPerformed
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton advanceModeBtn;
    private javax.swing.JButton basicModeBtn;
    // End of variables declaration//GEN-END:variables
}
