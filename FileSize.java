import java.io.File;

public class FileSize {
    public static void main(String[] args) {  
        String filePath = args[0];
        
        File file = new File(filePath);
        
        if (file.exists()) {
            long fileSize = file.length();
            System.out.println("Kích thước của tệp tin là: " + fileSize + " bytes");
        } else {
            System.out.println("Tệp tin không tồn tại.");
        }
    }
}
