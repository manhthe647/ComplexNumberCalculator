
package controller;
import view.AdvanceModePanel;

public class SelectAdvanceModeController {
    private AdvanceModePanel advanceModePanel;
    
    public SelectAdvanceModeController(){}
    
    public SelectAdvanceModeController(AdvanceModePanel advanceModePanel){
        this.advanceModePanel=advanceModePanel;
    }
    
    public void mode1Click(){
        System.out.println("Mode1_Click");
        advanceModePanel.showMode1();
    }
    
    public void mode2Click(){
        System.out.println("Mode2_Click");
        advanceModePanel.showMode2();
    }
    
    public void mode3Click(){
        System.out.println("Mode3_Click");
        advanceModePanel.showMode3();
    }
    
    public void mode4Click(){
        System.out.println("Mode4_Click");
        advanceModePanel.showMode4();
    }
    
    public void mode5Click(){
        System.out.println("Mode5_Click");
        advanceModePanel.showMode5();
    }
    
    public void mode6Click(){
        System.out.println("Mode6_Click");
        advanceModePanel.showMode6();
    }
    
   
    
    
}
