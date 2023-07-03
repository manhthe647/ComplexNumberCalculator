package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalculatorComplexNumber {
    final double PI = 3.141592;
    public StringBuilder currentExpression=new StringBuilder(); //lưu trữ giá trị của biểu thức
    public String ans;
//    List resultList=new ArrayList();
//    List<String> tokens = new LinkedList<>();
    public StringBuilder resultListString=new StringBuilder(); 
    
    //check đầu vào string
    public boolean checkExpression(String input) {
    // Kiểm tra xem đầu vào có chứa hai dấu phép toán không hợp lệ liên tiếp hay không
    String[] invalidSequences = {"+*", "*+", "-*", "*-", "/*", "*/", "**", "//", "((", "))"};
    
    for (String invalidSequence : invalidSequences) {
        if (input.contains(invalidSequence)) {
            return false;
        }
    }
     // Kiểm tra số kèm theo dấu ngoặc
        return !input.matches(".*\\d+\\(.*");

    }
     //tach chuoi so phuc
    public static void splitExpression(String expressionInput, List tokens){
        String delimiter = "+-*/";
        StringBuilder currentToken = new StringBuilder();
        Stack<Character> parenthesisStack = new Stack<>();
        
        for (int i = 0; i <expressionInput.length(); i++) {
            char c = expressionInput.charAt(i);
            
            // Nếu gặp ngoặc mở, đẩy vào stack
            if (c == '(') {
                parenthesisStack.push(c);
                currentToken.append(c);
            }  else if (c == ')') {
                // Nếu gặp ngoặc đóng, xóa ngoặc mở khỏi stack và thêm ngoặc đóng vào token
                if (!parenthesisStack.empty()) {
                    parenthesisStack.pop();
                }
                currentToken.append(c);
            } else if (delimiter.contains(Character.toString(c)) && parenthesisStack.isEmpty()) {
                // Nếu gặp delimiter và stack rỗng (ngoài ngoặc), thêm token hiện tại vào list và bắt đầu token mới
                tokens.add(currentToken.toString());
                tokens.add(Character.toString(c));
                currentToken.setLength(0);
            } else {
                currentToken.append(c);
            }
        }

        if (currentToken.length() > 0) {
            tokens.add(currentToken.toString());
        }
     
    }
    
    //chuyển đổi -- thành dấu +, --- thành dấu -,...
    public static String simplifyExpression(String expression) {
    StringBuilder simplifiedExpression = new StringBuilder();
    int minusCount = 0;

    for (int i = 0; i < expression.length(); i++) {
        char c = expression.charAt(i);

        if (c == '-') {
            minusCount++;
        } else {
            if (minusCount > 0) {
                char sign = minusCount % 2 == 0 ? '+' : '-';
                simplifiedExpression.append(sign);
                minusCount = 0;
            }
            simplifiedExpression.append(c);
        }
    }

    if (minusCount > 0) {
        char sign = minusCount % 2 == 0 ? '+' : '-';
        simplifiedExpression.append(sign);
    }

    return simplifiedExpression.toString();
}
    
    
    //Tinh toan
    public static ComplexNumber calcExpression(List<String> tokens) {
    ComplexNumber result=new ComplexNumber(0,0); 

    List<Object> expression = new ArrayList<>();
   
    for (String token : tokens) {
        if (token.equals("*") || token.equals("/") ||token.equals("+") || token.equals("-") ) {
            expression.add(token);
        } else{
            //sai chỗ parse cộng trừ
            expression.add(ComplexNumber.parseComplexNumber(token));

        }
    }


    // Tính toán các phép nhân và chia trước
    for (int i = 0; i < expression.size(); i++) {
        if (expression.get(i) instanceof String && (expression.get(i).equals("*") || expression.get(i).equals("/"))) {
            ComplexNumber leftOperand = (ComplexNumber) expression.get(i - 1);
            ComplexNumber rightOperand = (ComplexNumber) expression.get(i + 1);

            if (expression.get(i).equals("*")) {
                expression.set(i, ComplexNumber.multiply(leftOperand, rightOperand));
            } else {
                expression.set(i, ComplexNumber.divide(leftOperand, rightOperand));
            }

            expression.remove(i + 1);
            expression.remove(i - 1);
            i--;
        }
    }     
            System.out.println(expression);

    // Tính toán các phép cộng và trừ
    result = (ComplexNumber) expression.get(0);
    for (int i = 1; i < expression.size(); i += 2) {
        ComplexNumber operand = (ComplexNumber) expression.get(i + 1);

        if (expression.get(i).equals("+")) {
            result = ComplexNumber.add(result, operand);
        } else {
            result = ComplexNumber.subtract(result, operand);
        }
    }
    
    return result;
        
}   
 
    public static void main(String[] args) {
        String expressionInput = "1+2i+3*(4+5i)-9*9i";
        List<String> tokens = new LinkedList<>();
        splitExpression(expressionInput, tokens);     

        ComplexNumber result = calcExpression(tokens);

        System.out.println(result.toString());
       
    }
}

