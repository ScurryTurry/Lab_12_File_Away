import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import static java.nio.file.StandardOpenOption.*;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * @author Peyton Terry terrypn@mail.uc.edu
 */

public class DataSaver {
    public static void main(String[] args) {
        ArrayList<String> recs = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        boolean done = false;

        do {
            System.out.println("Enter the following information to complete the records.");
            String firstName = SafeInput.getNonZeroLenString(in, "Enter the first name");
            String lastName = SafeInput.getNonZeroLenString(in, "Enter the last name");
            String id = SafeInput.getNonZeroLenString(in, "Enter the ID");
            String email = SafeInput.getNonZeroLenString(in, "Enter the email");
            int birthYear = SafeInput.getRangedInt(in, "Enter Birth Year", 1930, 2024);

            String rec = String.format("%s %s %s %s %s", firstName, lastName, id, email, birthYear);
            recs.add(rec);

            done = SafeInput.getYNConfirm(in, "Are you finished?");

        } while (!done);

        File workingDirectory = new File(System.getProperty("user.dir"));
        String fileName = SafeInput.getNonZeroLenString(in, "Enter the file name");
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\" + fileName + ".csv");

        try {
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer
                    = new BufferedWriter(new OutputStreamWriter(out));

            for (String rec : recs) {
                writer.write(rec, 0, rec.length());
                writer.newLine();
            }
            writer.close();
            System.out.println("Data file written.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



