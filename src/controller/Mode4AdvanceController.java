package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.ComplexNumber;
import view.Mode4AdvancePanel;

public class Mode4AdvanceController implements ActionListener  {
    private final Mode4AdvancePanel view;
    private final ComplexNumber model;
    
    public Mode4AdvanceController(Mode4AdvancePanel view, ComplexNumber model ){
        this.view=view;
        this.model=model;
    }
    
     public void actionPerformed(ActionEvent e) {
        try {
          double real = view.getRealValueMode4();
          double imag = view.getImagValueMode4();
          int exp = view.getExplValueMode4();

           
          if (real>10e6 || real < -10e6 || imag <-10e6 || imag >10e6 || exp<0 || exp > 20000 ) {
               view.alert("Ngoài phạm vi tính toán");
         }else{
                 nthRootComplexNumber( real, imag,  exp);
                view.alert("");
              }

        } catch (NumberFormatException ex) {
            view.alert("Xuất hiện lỗi định dạng");
            System.out.println("Sai định dạng");
         
        }
    }
    
    public void nthRootComplexNumber(double real, double imag, int exp){
        ComplexNumber a=new ComplexNumber(real, imag);

         //Lấy giá trị r
         double r=a.modulus();
        //Lấy giá trị góc rad ( làm tròn 3 chữ số thập phân )
         double angle=Math.round(a.angleRadians()*1000.0)/1000.0;
        //Lấy giá trị sin
         double sinValue=Math.round(Math.sin((angle+(exp*2*Math.PI))/exp)*1000.0)/1000.0;
         //Lấy giá trị cos
         double cosValue=Math.round(Math.cos((angle+(exp*2*Math.PI))/exp)*1000.0)/1000.0;
        
        String label1="z =" +a.toString().substring(1,a.toString().length()-1);
        String label2="√z =" + "√"+r +" * " + "[ cos ("+exp+"*"+angle+") + i * sin (" +exp+"*"+angle+") ]";
        String label3="√z ="+ "√"+r + " * "+ "[ cos (" + (angle+(exp*2*Math.PI))/exp + ") + i * sin ( " + (angle+(exp*2*Math.PI))/exp+ ") ]";
        String label4="√z ="+ "√"+r + " * ("+  cosValue +"+"+sinValue +"i)";
    
       
        view.setLabel1(label1);
        view.setLabel2(label2);
        view.setLabel3(label3);
        view.setLabel4(label4);
   

    }

}
    
    

