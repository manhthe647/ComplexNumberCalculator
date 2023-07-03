package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.ComplexNumber;
import view.Mode6AdvancePanel;

public class Mode6AdvanceController implements ActionListener  {
    private final Mode6AdvancePanel view;
    private final ComplexNumber model;
    
    public Mode6AdvanceController(Mode6AdvancePanel view, ComplexNumber model ){
        this.view=view;
        this.model=model;
    }
    
     public void actionPerformed(ActionEvent e) {
    try {
        double u = Double.parseDouble(view.getVoltageValue());
        double w = Double.parseDouble(view.getOmegaValue());
        double angle = Double.parseDouble(view.getAngleValue());
        double r = Double.parseDouble(view.getResistorValue());
        double l = Double.parseDouble(view.getInductanceValue());
        double c = Double.parseDouble(view.getCapacitanceValue());

        if (u > 10e3 || u <=0 || w > 10e3 || w <=0 || r > 10e3 || r <= 0 || l > 10e3 || l <= 0 || c > 10e3 || c <=0 || w <= 0 || w > 10e3) {
            view.alert("Ngoài phạm vi tính toán");
        } else {
            view.alert("");
            switch(e.getActionCommand()){
                case "Tính tổng trở":
                    totalResistanceCalc(u, w, r, l, c);          
                    break;
                case "biểu thức dòng điện":
                    intensiteCalc(u,  w,  angle,  r,  l,  c);
                    break;
                case "điện áp RC":
                    rcCircuitCalc( u,  w,  angle,  r,  l,  c);
                    break;
                case "điện áp LC":
                    lcCircuitCalc( u,  w,  angle,  r,  l,  c);
                    break;
       
            }
            
        }
    }   catch (NumberFormatException ex) {
        view.alert("Xuất hiện lỗi định dạng");

        System.out.println("Sai định dạng");
    }
}
     
    //Dao động tổng hợp
    public void totalResistanceCalc(double u, double w, double r, double l, double c){

        double zlValue=w*l;
        double zcValue=1/(w*c*(10e-6));
        ComplexNumber z=new ComplexNumber(r, zlValue-zcValue);
        
        String label1="Cảm kháng: ZL="+zlValue+", Tụ kháng: ZC="+zcValue;
        String label2="Trở kháng tổng hợp: ~Z=R+i(ZL-ZC)="+r+"+i("+zlValue+"-"+zcValue+")";
        String label3="Tổng trở của mạch: Z="+z.modulus()+" Ω";
        String label4="Điện áp trễ pha hơn dòng điện là "+z.angleDegrees()+" độ";

         
        view.setLabel1(label1);
        view.setLabel2(label2);
        view.setLabel3(label3);
        view.setLabel4(label4);

    }
    
    public void intensiteCalc(double u, double w, double angle, double r, double l, double c){
       
        double zlValue=w*l;
        double zcValue=1/(w*c*(10e-6));
        ComplexNumber z=new ComplexNumber(r, zlValue-zcValue);
        ComplexNumber u0=ComplexNumber.fromPolar(u, angle);
        ComplexNumber i=ComplexNumber.divide(u0, z);
        
        String label1="i=u/(~Z)";
        String label2="i=(u0∠φ)/(R+i(ZL-ZC)";
        String label3="i = "+ i.modulus() +" cos("+w+" t + "+i.angleRadians()+") (A)";
        String label4="";

         
        view.setLabel1(label1);
        view.setLabel2(label2);
        view.setLabel3(label3);
        view.setLabel4(label4);
    }
    
    public void rcCircuitCalc(double u, double w, double angle, double r, double l, double c){
        double zlValue=w*l;
        double zcValue=1/(w*c*(10e-6));
        ComplexNumber z=new ComplexNumber(r, zlValue-zcValue);
        ComplexNumber u0=ComplexNumber.fromPolar(u, angle);
        ComplexNumber i=ComplexNumber.divide(u0, z);
        
        ComplexNumber zrc=new ComplexNumber(r, -zcValue);
        ComplexNumber urc=ComplexNumber.multiply(i, zrc);
        
        String label1="uRC=i*(~zRC)";
        String label2="uRC = "+ urc.modulus() +" * cos("+w+" t + "+urc.angleRadians()+") (V)";
        String label3="";
        String label4="";


        view.setLabel1(label1);
        view.setLabel2(label2);
        view.setLabel3(label3);
        view.setLabel4(label4);
    }
    
    public void lcCircuitCalc(double u, double w, double angle, double r, double l, double c){
        double zlValue=w*l;
        double zcValue=1/(w*c*(10e-6));
        ComplexNumber z=new ComplexNumber(r, zlValue-zcValue);
        ComplexNumber u0=ComplexNumber.fromPolar(u, angle);
        ComplexNumber i=ComplexNumber.divide(u0, z);
        
        ComplexNumber zlc=new ComplexNumber(r, zlValue);
        ComplexNumber ulc=ComplexNumber.multiply(i, zlc);
        
        String label1="uLC=i*(~ZLC)";
        String label2="uLC = "+ ulc.modulus() +" * cos("+w+" t + "+ulc.angleRadians()+") (V)";
        String label3="";
        String label4="";

        view.setLabel1(label1);
        view.setLabel2(label2);
        view.setLabel3(label3);
        view.setLabel4(label4);
        
        

    }

}
    
    

