import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);


        System.out.printf("Enter source file:");
        String sourcePath = in.nextLine();
        System.out.printf("Enter destination file:");
        String destPath = in.nextLine();


        File sourceFile = new File(sourcePath);
        File destFile = new File(destPath);


        try {
            copyFileUsingJava7Files(sourceFile, destFile);
            //copyFileUsingStream(sourceFile, destFile);
            System.out.printf("Copy completed");
        } catch (IOException ioe) {
            System.out.printf("Can't copy that file");
            System.out.printf(ioe.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    static void copyFileUsingJava7Files(File source, File dest) throws Exception {
        Files.copy(source.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }

    void copyFileUsingStream(File source, File dest) throws Exception {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            is.close();
            os.close();
        }

    }

}