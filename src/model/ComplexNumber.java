package model;

public class ComplexNumber {
    double real;
    double imaginary;

    public ComplexNumber() {
        this.real = 0;
        this.imaginary = 0;
    }
    
    public ComplexNumber(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }
/**
 * Hàm cộng hai số phức.
 *
 * @param a số phức thứ nhất.
 * @param b số phức thứ hai.
 * @return kết quả của phép cộng hai số phức a và b.
 */
    public static ComplexNumber add(ComplexNumber a, ComplexNumber b) {
        return new ComplexNumber(a.real + b.real, a.imaginary + b.imaginary);
    }
/**
 * Hàm trừ hai số phức.
 *
 * @param a số phức thứ nhất.
 * @param b số phức thứ hai.
 * @return kết quả của phép trừ hai số phức a và b.
 */
    public static ComplexNumber subtract(ComplexNumber a, ComplexNumber b) {
        return new ComplexNumber(a.real - b.real, a.imaginary - b.imaginary);
    }
/**
 * Hàm nhân hai số phức.
 *
 * @param a số phức thứ nhất.
 * @param b số phức thứ hai.
 * @return kết quả của phép nhân hai số phức a và b.
 */
    public static ComplexNumber multiply(ComplexNumber a, ComplexNumber b) {
        double real = a.real * b.real - a.imaginary * b.imaginary;
        double imaginary = a.real * b.imaginary + a.imaginary * b.real;
        return new ComplexNumber(real, imaginary);
    }
/**
 * Hàm chia hai số phức.
 *
 * @param a số phức thứ nhất.
 * @param b số phức thứ hai.
 * @return kết quả của phép chia hai số phức a và b, hoặc null nếu phép chia không hợp lệ (mẫu số bằng 0).
 * @throws ArithmeticException nếu phép chia không hợp lệ (mẫu số bằng 0).
 */
    public static ComplexNumber divide(ComplexNumber a, ComplexNumber b) {
        
       try {   
            double realPart = (a.real * b.real + a.imaginary * b.imaginary) / (Math.pow(b.real, 2) + Math.pow(b.imaginary, 2));
            double imaginaryPart = (a.imaginary * b.real - a.real * b.imaginary) / (Math.pow(b.real, 2) + Math.pow(b.imaginary, 2));
            return new ComplexNumber(realPart, imaginaryPart);
            
        } catch (ArithmeticException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
 /**
 * Hàm chuyển đổi một chuỗi ký tự sang số phức.
 *
 * Hàm này chuyển đổi một chuỗi đầu vào biểu diễn số phức dạng a + bi hoặc (a + bi)
 * thành một đối tượng ComplexNumber. Trong đó, a là phần thực và b là phần ảo của số phức.
 * Hàm xử lý các trường hợp dấu '+' và '-' có thể xuất hiện trong chuỗi đầu vào.
 *
 * @param input chuỗi ký tự biểu diễn số phức dạng a + bi hoặc (a + bi).
 * @return một đối tượng mới của lớp ComplexNumber, chứa giá trị số phức tương ứng với chuỗi đầu vào.
 * @throws NumberFormatException nếu định dạng chuỗi đầu vào không đúng.
 */
      public static ComplexNumber parseComplexNumber(String input) {
        //check cặp ngoặc và xóa bỏ
        if(input.contains("(") &&  input.contains(")")){
            StringBuilder sb = new StringBuilder(input);
            int openCount = 0;
            int closeCount = 0;

            for(int i=0; i<sb.toString().length();i++){
                char ch = sb.charAt(i);
                if(ch=='('){
                    openCount++;
                }else if(ch==')'){
                    closeCount++;
                }
            }
            
        // Nếu có đúng một cặp dấu ngoặc đơn và chuỗi có độ dài lớn hơn 2
        // loại bỏ dấu ngoặc đơn khỏi chuỗi để tiếp tục xử lý. 
         if(openCount==1 && closeCount==1 && sb.toString().length()>2){
             input=sb.toString();
             input = input.replace("(", "");
             input = input.replace(")", "");
         }
         
   }
        
        // Khởi tạo các biến cần thiết để xử lý chuỗi đầu vào
        double realPart = 0;
        double imaginaryPart = 0;
        int sign = 1;
        int lastSign = 1;
        String temp = "";

        // Duyệt qua các ký tự trong chuỗi đầu vào
        for (int i = 0; i < input.length(); i++) {
        char c = input.charAt(i);

    // Nếu gặp dấu '+' hoặc '-', cập nhật giá trị của sign
         if (c == '+' || c == '-') {
           sign = (c == '+') ? 1 : -1;

        // Nếu chuỗi temp không rỗng, xử lý phần tử trước đó
        if (temp.length() > 0) {
            if (input.charAt(i - 1) == 'i') {
                imaginaryPart += lastSign * Double.parseDouble(temp.substring(0, temp.length() - 1));
            } else {
                realPart += lastSign * Double.parseDouble(temp);
            }
        }
         // Làm rỗng chuỗi temp và cập nhật lastSign
                 temp = "";
                 lastSign = sign;
        } else {
        temp += c;
        }
    }

     // Xử lý phần tử cuối cùng của chuỗi đầu vào
        if (temp.length() > 0) {
        if (temp.charAt(temp.length() - 1) == 'i') {
        imaginaryPart += lastSign * Double.parseDouble(temp.substring(0, temp.length() - 1));
        } else {
         realPart += lastSign * Double.parseDouble(temp);
        }   
    }

    // Trả về đối tượng ComplexNumber với phần thực và phần ảo tương ứng
        return new ComplexNumber(realPart, imaginaryPart);
}
      
/**
 * Hàm tính độ lớn của số phức hiện tại.
 *
 * Độ lớn của một số phức z = a + bi (với a, b là các số thực) được tính bằng công thức:
 * |z| = sqrt(a^2 + b^2).
 * 
 * @return độ lớn của số phức hiện tại.
 */
     public double magnitude() {
        return Math.sqrt(real * real + imaginary * imaginary);
    }

    public double angleRadians() {
        return Math.atan2(imaginary, real);
    }

    public double angleDegrees() {
        return Math.toDegrees(Math.atan2(imaginary, real));
    } 
    
    
/**
 * Hàm lấy phần thực của số phức.
 *
 * @return giá trị phần thực của số phức hiện tại.
 */
     public double getReal() {
        return this.real;                                                               
    }
 
/**
 * Hàm lấy phần ảo của số phức.
 *
 * @return giá trị phần ảo của số phức hiện tại.
 */
    public double getImag() {
        return this.imaginary;
    }

    public double modulus() {
    double result = Math.sqrt(real * real + imaginary * imaginary);
    return Math.round(result * 1000.0) / 1000.0;
}
   
 /**
 * Hàm tính căn bậc hai của số phức hiện tại.
 *
 * Căn bậc hai của một số phức có dạng z = a + bi, với a, b là các số thực, được tính
 * bằng công thức: z = sqrt(magnitude) * (cos(theta/2) + i * sin(theta/2)),
 * trong đó magnitude là độ lớn của số phức, và theta là góc pha (tính bằng radian).
 * 
 * @return một đối tượng mới của lớp ComplexNumber, chứa giá trị căn bậc hai của số phức hiện tại.
 */
    public ComplexNumber squareRoot() {
        double magnitude = this.magnitude();
        double angleRadians = this.angleRadians();
        double sqrtMagnitude = Math.sqrt(magnitude);
        double halfAngle = angleRadians / 2;
        return new ComplexNumber(sqrtMagnitude * Math.cos(halfAngle), sqrtMagnitude * Math.sin(halfAngle));
    }
/**
 * Hàm biểu diễn số phức hiện tại dưới dạng số mũ phức.
 *
 * Số mũ phức có dạng: z = r * e^(θi), trong đó r là độ lớn của số phức,
 * θ là góc pha (tính bằng radian), và i là đơn vị ảo.
 * 
 * @return một chuỗi ký tự biểu diễn số phức hiện tại dưới dạng số mũ phức, với
 *         độ chính xác là 2 chữ số sau dấu phẩy.
 */
    public String exponential() {
        double magnitude = this.magnitude();
        double angleRadians = this.angleRadians();
        return String.format("%.3f * e^(%.3f)i", magnitude, angleRadians);
    }
/**
 * Hàm tính góc pha của số phức hiện tại theo độ.
 *
 * Góc pha là góc giữa trục thực và đường nối từ gốc tọa độ đến điểm biểu diễn số phức
 * trong mặt phẳng phức. Hàm này sử dụng hàm Math.atan2() để tính góc pha theo radian,
 * sau đó chuyển đổi sang độ và làm tròn đến 2 chữ số thập phân.
 * 
 * @return góc pha của số phức hiện tại tính theo độ, làm tròn đến 2 chữ số thập phân.
 */
  public double argumentDegrees() {
    double result = Math.toDegrees(Math.atan2(imaginary, real));
    return Math.round(result * 1000.0) / 1000.0;
}   
/**
 * Hàm tính góc pha của số phức hiện tại theo radian.
 *
 * Góc pha là góc giữa trục thực và đường nối từ gốc tọa độ đến điểm biểu diễn số phức
 * trong mặt phẳng phức. Hàm này sử dụng hàm Math.atan2() để tính góc pha theo radian
 * và làm tròn đến 2 chữ số thập phân.
 * 
 * @return góc pha của số phức hiện tại tính theo radian, làm tròn đến 2 chữ số thập phân.
 */
public double argumentRadians() {
    double result = Math.atan2(imaginary, real);
    return Math.round(result * 1000.0) / 1000.0;
}
/**
 * Hàm tạo một số phức mới từ biểu diễn cực của nó.
 *
 * Biểu diễn cực của một số phức bao gồm độ lớn (magnitude) và góc pha (angleDegrees) tính theo độ.
 * Hàm này chuyển đổi góc pha sang radian, tính phần thực và ảo của số phức dựa trên công thức
 * z = r * (cos(θ) + i * sin(θ)), sau đó tạo ra một số phức mới với các giá trị phần thực và ảo
 * vừa tính được.
 * 
 * @param magnitude độ lớn của số phức.
 * @param angleDegrees góc pha của số phức tính theo độ.
 * @return một đối tượng mới của lớp ComplexNumber, chứa giá trị số phức dựa trên biểu diễn cực đã cho.
 */
public static ComplexNumber fromPolar(double magnitude, double angleDegrees) {
    double angleRadians = Math.toRadians(angleDegrees);
    double real = magnitude * Math.cos(angleRadians);
    double imaginary = magnitude * Math.sin(angleRadians);
    return new ComplexNumber(real, imaginary);
}

    @Override
    public String toString() {
        // Làm tròn số phức với độ chính xác là 6 chữ số thập phân
        String realRounded = String.format("%.3f", real);
        String imaginaryRounded = String.format("%.3f", Math.abs(imaginary));
        return "(" + realRounded + (imaginary < 0 ? " - " : " + ") + imaginaryRounded + "i)";
    }
}

                                                                                                            