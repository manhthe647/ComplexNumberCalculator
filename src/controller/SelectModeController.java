package controller;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.SelectModePanel;
import view.ShowModePanel;

public class SelectModeController implements ActionListener {
    
    private final ShowModePanel showModePanel;
    
    public SelectModeController(ShowModePanel showModePanel){
        this.showModePanel=showModePanel;

    }
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "Cơ bản":
                onBasicModeButtonClick();
                
                break;
            case "Nâng cao":
                onAdvanceModeButtonClick();   
              
                break;
        }
    }
    
    
    public void onBasicModeButtonClick() {
        System.out.println("Basic mode button clicked");
        showModePanel.showBasicModePanel();
    }

    public void onAdvanceModeButtonClick() {
        System.out.println("Advance mode button clicked");
        showModePanel.showAdvanceModePanel();
    }
    
    
}
