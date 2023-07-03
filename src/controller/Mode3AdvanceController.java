package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.ComplexNumber;
import view.Mode3AdvancePanel;

public class Mode3AdvanceController implements ActionListener  {
    private final Mode3AdvancePanel view;
    private final ComplexNumber model;
    
    public Mode3AdvanceController(Mode3AdvancePanel view, ComplexNumber model ){
        this.view=view;
        this.model=model;
    }
    
     public void actionPerformed(ActionEvent e) {
         try {
             double realValue = view.getRealValueMode3();
             double imagValue = view.getImagValueMode3();
             int expValue = view.getExplValueMode3();
             
            if (realValue>10e6 || realValue < -10e6 || imagValue <-10e6 || imagValue >10e6 || expValue <0 || expValue > 20000 ) {
            view.alert("Ngoài phạm vi tính toán");
             }else{
            //hiển thị số phức
                moivreFormula( realValue, imagValue,  expValue);
                view.alert("");
              }

        } catch (NumberFormatException ex) {
            view.alert("Sai định dạng !");
            System.out.println("Sai định dạng");
         
        }
    }
    
    public void moivreFormula(double realValue, double imagValue, int expValue){
       
        ComplexNumber a=new ComplexNumber(realValue, imagValue);
        //Lấy giá trị r
         double r=a.modulus();
        //Lấy giá trị góc rad ( làm tròn 3 chữ số thập phân )
         double angle=Math.round(a.angleRadians()*1000.0)/1000.0;
        //Lấy giá trị sin
         double sinValue=Math.round(Math.sin(expValue*angle)*1000.0)/1000.0;
         //Lấy giá trị cos
         double cosValue=Math.round(Math.cos(expValue*angle)*1000.0)/1000.0;

         
        String label1="z ^ "+expValue+" = ( "+a.toString().substring(1,a.toString().length()-1)+" ) ^ "+expValue;
        String label2="z ^ "+expValue+" = "+r+" ^ "+expValue+" * [ cos ("+expValue+"*"+angle+") + i * sin (" +expValue+"*"+angle+") ]";
        String label3="z ^ "+expValue+" = "+r+" ^ "+expValue+" * [ cos ("+expValue*angle+") + i * sin (" +expValue*angle+") ]";
        String label4="z ^ "+expValue+" = "+r+" ^ "+expValue+" * ( "+cosValue +" + i * "+sinValue+" )";
        
        view.setLabel1(label1);
        view.setLabel2(label2);
        view.setLabel3(label3);
        view.setLabel4(label4);

    }

}
    
    

