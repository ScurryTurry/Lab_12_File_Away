import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import static java.nio.file.StandardOpenOption.CREATE;
import javax.swing.JFileChooser;

/**
 * @author Peyton Terry terrypn@mail.uc.edu
 */

public class FileInspector {
    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec = "";
        int lineCount = 0;
        int wordCount = 0;
        int charCount = 0;

        try{
            File workingDirectory = new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(workingDirectory);

            if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();

                InputStream in =
                        new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(in));

                while (reader.ready()) {

                    rec = reader.readLine();
                    lineCount++;

                    String[] words = rec.split(" ");
                    wordCount += words.length;

                    charCount += rec.length();
                }
                reader.close();
                System.out.println("File Name: " + selectedFile.getName());
                System.out.println("-------------------------------------");
                System.out.printf("The total number of lines are %4d", lineCount);
                System.out.printf("\nThe total number of words are %4d", wordCount);
                System.out.printf("\nThe total number of characters are %4d", charCount);
                System.out.println("\n\nData file read");
            }
        }catch (FileNotFoundException e){
            System.out.println("File not found");
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}