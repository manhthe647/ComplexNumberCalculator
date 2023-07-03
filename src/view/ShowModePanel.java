                                                                            /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.CardLayout;
import java.awt.Dimension;

public class ShowModePanel extends javax.swing.JPanel{
    
    private CardLayout cardLayout;
    public ShowModePanel(){
        initComponents();
    }
    
    private void initComponents(){
        
        setSize(850, 480);

        cardLayout = new CardLayout();
        setLayout(cardLayout);

        BasicModePanel basicModePanel = new BasicModePanel();
        basicModePanel.setPreferredSize(new Dimension(850, 500));
        add(basicModePanel, "BasicModePanel");

        AdvanceModePanel advanceModePanel = new AdvanceModePanel();
        advanceModePanel.setPreferredSize(new Dimension(850, 500));
        add(advanceModePanel, "AdvanceModePanel");
        
    }
    
    public void showBasicModePanel() {
        cardLayout.show(this, "BasicModePanel");
    }
    
    public void showAdvanceModePanel(){
        cardLayout.show(this, "AdvanceModePanel");
    }
}
