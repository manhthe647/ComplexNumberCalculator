package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.ComplexNumber;
import view.Mode5AdvancePanel;

public class Mode5AdvanceController implements ActionListener  {
    private final Mode5AdvancePanel view;
    private final ComplexNumber model;
    
    public Mode5AdvanceController(Mode5AdvancePanel view, ComplexNumber model ){
        this.view=view;
        this.model=model;
    }
    
     public void actionPerformed(ActionEvent e) {
         try {
            double x1Length=Double.parseDouble(view.getX1LengthMode5());
            double x2Length=Double.parseDouble(view.getX2LengthMode5());
            double angle1=Double.parseDouble(view.getAngle1LengthMode5());
            double angle2=Double.parseDouble(view.getAngle2LengthMode5());

          if (x1Length < -10e3 || x1Length >10e3 || x2Length < -10e3 || x2Length >10e3 || angle1 <-10e3 || angle1 > 10e3 ||angle2 < -10e3 || angle2 > 10e3  ) {
            view.alert("Ngoài phạm vi tính toán");
         }else{
                oscillationSynthesis(x1Length,angle1, x2Length, angle2);
                view.alert("");

              }

        } catch (NumberFormatException ex) {
            view.alert("Xuất hiện lỗi định dạng !");
            System.out.println("Sai định dạng");
         
        }
    }
    
    //Dao động tổng hợp
    public void oscillationSynthesis(double x1Length,double x2Length,double angle1,double angle2){
        
        ComplexNumber temp1=ComplexNumber.fromPolar(x1Length, angle1);
        ComplexNumber temp2=ComplexNumber.fromPolar(x2Length, angle2);
        ComplexNumber z=ComplexNumber.add(temp1, temp2);
        
        String label1="x1 = "+x1Length + " ∠ " + angle1;
        String label2="x2 = "+x2Length + " ∠ " + angle2;
        String label3="Tổng hợp dao động";
        String label4="x = x1 + x2";
        String label5="x = "+x1Length + " ∠ " + angle1 +" + "+x2Length + " ∠ " + angle2;
        String label6="x ="+z.modulus()+" ∠ "+z.angleDegrees();
        String label7="Phương trình dao động tổng hợp có dạng ";
        String label8="x = "+ z.modulus() +"*cos(wt + "+ z.angleDegrees()+")";
    
        view.setLabel1(label1);
        view.setLabel2(label2);
        view.setLabel3(label3);
        view.setLabel4(label4);
        view.setLabel5(label5);
        view.setLabel6(label6);
        view.setLabel7(label7);
        view.setLabel8(label8);

    }

}
    
    

