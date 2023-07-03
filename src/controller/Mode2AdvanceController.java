package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.ComplexNumber;
import view.Mode2AdvancePanel;

public class Mode2AdvanceController implements ActionListener  {
    private final Mode2AdvancePanel view;
    private final ComplexNumber model;
    
    public Mode2AdvanceController(Mode2AdvancePanel view, ComplexNumber model ){
        this.view=view;
        this.model=model;
    }
    
     public void actionPerformed(ActionEvent e) {
        try {
         
         if(view.getValue1().equals("") || view.getValue2().equals("") || view.getValue3().equals("")){
             view.alert("Vui lòng nhập dữ liệu !");
         }
         else{
            ComplexNumber a= ComplexNumber.parseComplexNumber(view.getValue1());
            ComplexNumber b= ComplexNumber.parseComplexNumber(view.getValue2());
            ComplexNumber c= ComplexNumber.parseComplexNumber(view.getValue3());
            solveEquationCplx(a,b,c);
            view.alert("");  
         }
        

        } catch (NumberFormatException ex) {
            view.alert("Xuất hiện lỗi định dạng !");
            System.out.println("Sai định dạng");
         
        }
        
 }      
    
    
    public void solveEquationCplx(ComplexNumber a, ComplexNumber b, ComplexNumber c){
        // tinh delta=b^2-4*a*c;
        //b^2
        ComplexNumber temp1=ComplexNumber.multiply(b, b);
        //a*c
        ComplexNumber temp2=ComplexNumber.multiply(a, c);
        //4*a*c
        ComplexNumber temp3=ComplexNumber.multiply(new ComplexNumber(4,0), temp2);
        // tính delta
        ComplexNumber delta=ComplexNumber.subtract(temp1, temp3);
        //tính căn delta
        ComplexNumber sqrtDelta=delta.squareRoot();
        
        //tính z1=(-b-sqrtDelta)/2a
        //-b
        ComplexNumber temp4=ComplexNumber.multiply(new ComplexNumber(-1,0), b);
        //-b-sqrtDelta
        ComplexNumber temp5=ComplexNumber.subtract(temp4, sqrtDelta);
        //2a
        ComplexNumber temp6=ComplexNumber.multiply(new ComplexNumber(2,0), a);
        //z1
        ComplexNumber z1=ComplexNumber.divide(temp5, temp6);
        
        //tính z2=(-b+sqrtDelta)/2a
        //-b
        ComplexNumber temp7=ComplexNumber.multiply(new ComplexNumber(-1,0), b);
        //-b+sqrtDelta
        ComplexNumber temp8=ComplexNumber.add(temp7, sqrtDelta);
        //2a
        ComplexNumber temp9=ComplexNumber.multiply(new ComplexNumber(2,0), a);
        //z2
        ComplexNumber z2=ComplexNumber.divide(temp8, temp9);
        
        String label0="Δ = b^2 - 4*a*c = "+ b +"^2 - 4 * " + a +" * " + c;
        String label1="Δ = "+delta.toString().substring(1, delta.toString().length()-1);
        String label2="√(Δ)= "+sqrtDelta.toString().substring(1, sqrtDelta.toString().length()-1);
        String label3="z1 = "+z1.toString().substring(1, z1.toString().length()-1);
        String label4="z2 = "+z2.toString().substring(1, z2.toString().length()-1);

        view.setLabel0(label0);
        view.setLabel1(label1);
        view.setLabel2(label2);
        view.setLabel3(label3);
        view.setLabel4(label4);
        
    }

}
    
    

