package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JPanel;

public class AdvanceModePanel extends javax.swing.JPanel {

    private CardLayout cardLayout;
    private JPanel cards;
    private SelectAdvanceModePanel selectAdvanceModePanel;

    public AdvanceModePanel() {
        initComponents();
        addPanel();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {
        setLayout(new BorderLayout());
    }// </editor-fold>

    private void addPanel() {
        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);
        
        Mode1AdvancePanel mode1AdvancePanel = new Mode1AdvancePanel();
        cards.add(mode1AdvancePanel, "mode1");

        Mode2AdvancePanel mode2AdvancePanel = new Mode2AdvancePanel();
        cards.add(mode2AdvancePanel, "mode2");

        Mode3AdvancePanel mode3AdvancePanel = new Mode3AdvancePanel();
        cards.add(mode3AdvancePanel, "mode3");

        Mode4AdvancePanel mode4AdvancePanel = new Mode4AdvancePanel();
        cards.add(mode4AdvancePanel, "mode4");

        Mode5AdvancePanel mode5AdvancePanel = new Mode5AdvancePanel();
        cards.add(mode5AdvancePanel, "mode5");
        
        Mode6AdvancePanel mode6AdvancePanel = new Mode6AdvancePanel();
        cards.add(mode6AdvancePanel, "mode6");

        add(cards, BorderLayout.CENTER);

        // Add selectAdvanceModePanel
        selectAdvanceModePanel = new SelectAdvanceModePanel(this);
        add(selectAdvanceModePanel, BorderLayout.WEST);
    }

    public void showMode1() {
        cardLayout.show(cards, "mode1");
    }

    public void showMode2() {
        cardLayout.show(cards, "mode2");
    }

    public void showMode3() {
        cardLayout.show(cards, "mode3");
    }

    public void showMode4() {
        cardLayout.show(cards, "mode4");
    }

    public void showMode5() {
        cardLayout.show(cards, "mode5");
    }
    
    public void showMode6() {
        cardLayout.show(cards, "mode6");
    }

}