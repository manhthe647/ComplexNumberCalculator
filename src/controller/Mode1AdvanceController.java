package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.ComplexNumber;
import view.Mode1AdvancePanel;

public class Mode1AdvanceController implements ActionListener  {
    private final Mode1AdvancePanel view;
    private final ComplexNumber model;
    
    public Mode1AdvanceController(Mode1AdvancePanel view, ComplexNumber model ){
        this.view=view;
        this.model=model;
    }
    
     public void actionPerformed(ActionEvent e) {
        try {
            double a = view.getRealValueMode1();
            double b = view.getImagValueMode1();
            ComplexNumber temp=new ComplexNumber(a,b);
          if (a>10e6 || a < -10e6 || b<-10e6 || b>10e6 ) {
            view.alert("Ngoài phạm vi tính toán");
         }else{
   
                 complexNumberInfo(temp);    
              }

        } catch (NumberFormatException ex) {
            view.alert("Xuất hiện lỗi định dạng");
            System.out.println("Sai định dạng");
         
        }
    }
    
    public void complexNumberInfo(ComplexNumber temp){
     
        String label1=temp.toString().substring(1, temp.toString().length()-1);
        String label2= temp.modulus() +"∠"+temp.argumentDegrees()+"°";
        String label3= temp.modulus() +"*"+"(cos"+temp.argumentDegrees()+"+sin"+temp.argumentDegrees()+")";
        String label4=temp.exponential();
        String label5="r=|z|="+temp.modulus()+" magnitude";
        String label6="θ=arg z="+temp.angleDegrees()+" deg="+temp.angleRadians()+" rad";
        String label7=Double.toString(temp.getReal());
        String label8=Double.toString(temp.getImag());
       
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
    
    

