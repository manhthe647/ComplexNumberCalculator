package controller;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.event.DocumentListener;
import model.CalculatorComplexNumber;
import model.ComplexNumber;
import org.w3c.dom.events.DocumentEvent;
import view.BasicModePanel;

public class BasicModeController implements ActionListener  {
    private final BasicModePanel view;
    private final ComplexNumber cplxModel;
    private final CalculatorComplexNumber cplxCalc;
    
    public BasicModeController(BasicModePanel view,ComplexNumber cplxModel,CalculatorComplexNumber cplxCalc){
        this.view=view;
        this.cplxModel=cplxModel;
        this.cplxCalc=cplxCalc;
    }
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "=":
                onClickEqualBtn();
                break;
            case "DEL":
                cplxCalc.currentExpression.deleteCharAt(cplxCalc.currentExpression.length() - 1);
                break;
            case "C":
                cplxCalc.currentExpression.setLength(0);
                showResult("");
                break;
                
            case "0":
                cplxCalc.currentExpression.append("0");
               
                break;
            case "1":
                cplxCalc.currentExpression.append("1");
               
                break;
            case "2":
              cplxCalc.currentExpression.append("2");
               
                break;
            case "3":
                cplxCalc.currentExpression.append("3");
               
                break;
            case "4":
                cplxCalc.currentExpression.append("4");
             
                break;
            case "5":
                cplxCalc.currentExpression.append("5");

                break;
            case "6":
                cplxCalc.currentExpression.append("6");
               
                break;
             case "7":
                cplxCalc.currentExpression.append("7");
               
                break;
             case "8":
                cplxCalc.currentExpression.append("8");
              
                break;
             case "9":
                cplxCalc.currentExpression.append("9");
              
                break;
             case "+":
                cplxCalc.currentExpression.append("+");
            
                break;
             case "-":
                cplxCalc.currentExpression.append("-");
             
                break;
             case "*":
                cplxCalc.currentExpression.append("*");
              
                break;
             case "/":
                cplxCalc.currentExpression.append("/");
              
                break;
             case "(":
                cplxCalc.currentExpression.append("(");
              
                break;
            case ")":
                cplxCalc.currentExpression.append(")");
              
                break;
            case ".":
                cplxCalc.currentExpression.append(".");
               
                break;
            case "i":
                cplxCalc.currentExpression.append("i");
               
                break;
            case "Ans":
                cplxCalc.currentExpression.append("("+cplxCalc.ans+")");
                break;
                
            case "SAVE":
                onClickSaveBtn();
                break;
                
            case "OPEN":
                onClickOpenBtn();
                break;
                
            case "CANCLE":
                onClickCancelBtn();
                break;
            
        }
        
        showExpressionCurrent(cplxCalc.currentExpression.toString());
        
    }
    
    public void showExpressionCurrent(String text){
        view.setInputFieldText(text);
    }
    
    public void showResult(String text){
        view.setResultFieldText(text);
    }
    
     public void showResultList(String text){
        view.setResultListFieldText(text);
    }
    
    public void showAlert(String text){
        view.alert(text);
    }
    
    public void onClickEqualBtn(){
        String expressionInput;
        List<String> tokens = new LinkedList<>();
        showResult("");
        showAlert("");
        try{
            boolean check=cplxCalc.checkExpression(cplxCalc.currentExpression.toString());
            if(check){
               expressionInput = cplxCalc.currentExpression.toString();
               String simplifiedExpression = cplxCalc.simplifyExpression(cplxCalc.currentExpression.toString());
               CalculatorComplexNumber.splitExpression(simplifiedExpression, tokens);
               ComplexNumber result = CalculatorComplexNumber.calcExpression(tokens);
               String output=result.toString().substring(1, result.toString().length()-1);
               cplxCalc.ans=output;
               showResult(output);
               cplxCalc.resultListString.append(cplxCalc.currentExpression.toString()+" = "+output+"\n");
               showResultList(cplxCalc.resultListString.toString());
            }
            else{
              showAlert("Biểu thức không được hỗ trợ!!!");
            }
            
        }catch(NumberFormatException e){
           showAlert("Biểu thức không được hỗ trợ !");
        }catch (Exception e) {
            showAlert("Biểu thức không được hỗ trợ !");
         }
    }
    
    public void onClickSaveBtn(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Lưu kết quả");
        fileChooser.setPreferredSize(new Dimension(fileChooser.getPreferredSize().width * 2, fileChooser.getPreferredSize().height * 2));
        fileChooser.setCurrentDirectory(new java.io.File("."));

        // Thiết lập tên file mặc định là thời gian hiện tại
        SimpleDateFormat formatter = new SimpleDateFormat("'Date' yyyy.MM.dd 'Time' HH.mm.ss");
        Date currentTime= new Date();
        
        String defaultFileName = formatter.format(currentTime)+ ".txt";
        fileChooser.setSelectedFile(new File(defaultFileName));

        int userSelection = fileChooser.showSaveDialog(view);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try {
                FileWriter writer = new FileWriter(fileToSave);
                writer.write(view.getResultListFieldText());
       
                writer.close();
            } catch (IOException ex) {
                // Xử lý ngoại lệ nếu có
                ex.printStackTrace();

            } 
        }
 }
    
    public void onClickOpenBtn(){
         JFileChooser fileChooser = new JFileChooser();
        fileChooser.setPreferredSize(new Dimension(fileChooser.getPreferredSize().width * 2, fileChooser.getPreferredSize().height * 2));
        fileChooser.setDialogTitle("Mở thư mục");
    // set the current directory of the file chooser
        fileChooser.setCurrentDirectory(new java.io.File("."));
    // show dialog to open the file
    int result = fileChooser.showOpenDialog(new JFrame());
    if (result == JFileChooser.APPROVE_OPTION) { // user selects a file
      try {
        // create a BufferedReader to read the file
        BufferedReader reader = new BufferedReader(new FileReader(fileChooser.getSelectedFile()));
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        while ((line = reader.readLine()) != null) {
          stringBuilder.append(line+"\n");
        }
        // display the contents of the file
        
        cplxCalc.resultListString.setLength(0);
        
        System.out.println(stringBuilder);
        showResultList(stringBuilder.toString());
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    }
 }
    
    
    public void onClickCancelBtn(){
        cplxCalc.resultListString.setLength(0);
        showResultList("");
    }
    
    
 
     
}
    
    

