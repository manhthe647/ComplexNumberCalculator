package view;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

public class ComplexNumberView extends javax.swing.JFrame {

    public ComplexNumberView() {
        initComponents();
        addPanel();
        setCustomCursor();
    }

  private void setCustomCursor() {
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    URL imageURL = getClass().getClassLoader().getResource("img/zenitsu-cry.png");

    if (imageURL != null) {
        Image originalCursorImage = toolkit.getImage(imageURL);
        
        int newWidth = 100; // Đặt chiều rộng mới
        int newHeight = 100; // Đặt chiều cao mới
        Image scaledCursorImage = originalCursorImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

        // Tạo ImageIcon để đảm bảo hình ảnh đã được thay đổi kích thước
        ImageIcon scaledIcon = new ImageIcon(scaledCursorImage);
        Image finalCursorImage = scaledIcon.getImage();

        Point cursorHotSpot = new Point(0, 0);
        Cursor customCursor = toolkit.createCustomCursor(finalCursorImage, cursorHotSpot, "CustomCursor");
        
        setCursor(customCursor);
    } else {
        System.err.println("Không tìm thấy hình ảnh cho con trỏ tùy chỉnh");
        
        // Thiết lập con trỏ mặc định nếu không tìm thấy hình ảnh
        setCursor(Cursor.getDefaultCursor());
    }
}
   
    private void initComponents() {
      
        setTitle("Số phức");
        setSize(1000, 510);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    }
    
    private void addPanel(){
       
        ShowModePanel showModePanel = new ShowModePanel();
//        showModePanel.setPreferredSize(new Dimension(800, 480));
        
        SelectModePanel selectModePanel = new SelectModePanel(showModePanel);
//        selectModePanel.setPreferredSize(new Dimension(200, 480));
        add(selectModePanel);
        
        add(showModePanel);
        
        
    }

  
}