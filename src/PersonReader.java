
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import static java.lang.System.out;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.CREATE;
import javax.swing.JFileChooser;


public class PersonReader
{

    public static void main(String[] args)
    {
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec = "";

        try
        {
            File workingDirectory = new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(workingDirectory);

            if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();
                InputStream in =
                        new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(in));

                System.out.printf("%-10s %-12s %-12s %-8s %-4s%n", "ID#", "Firstname", "Lastname", "Title", "YOB");
                System.out.println("==================================================");
                while(reader.ready())
                {
                    rec = reader.readLine();
                    String[] fields = rec.split(",");
                    if (fields.length == 5) {
                        String id = fields[0].trim();
                        String firstName = fields[1].trim();
                        String lastName = fields[2].trim();
                        String title = fields[3].trim();
                        int yob = Integer.parseInt(fields[4].trim());

                        // Format into columns
                        System.out.printf("%-10s %-12s %-12s %-8s %-4d%n",
                                id, firstName, lastName, title, yob);
                    }
                }
                reader.close(); // must close the file to seal it and flush buffer
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found!!!");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
