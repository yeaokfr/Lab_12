import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import static java.nio.file.StandardOpenOption.CREATE;

public class  ReadingFiles {
    public static void main(String[] args) throws IOException   {
        JFileChooser chooser = new JFileChooser();

        try {
            File workingDirectory = new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(workingDirectory);

            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                File selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();

                InputStream in = new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                int line = 0;
                int space = 0;
                int character = 0;
                String rec = "";
                System.out.println("File Path: " + file);

                while (reader.ready()) {
                    rec = reader.readLine();
                    line++;
                    System.out.printf("\nLine%4d: %-60s ", line, rec);
                    for (int i = 0; i < rec.length(); i++) {
                        if (rec.substring(i, i+1).equals(" ")) {
                            space++;
                        }
                        character++;
                    }
                }
                reader.close();
                System.out.println("\n\nData file read!");
                System.out.println();
                System.out.println("File Summary:");
                System.out.println("Number of Lines: " + line);
                System.out.println("Number of Spaces: " + space);
                System.out.println("Number of Words: " + (space + line));
                System.out.println("Number of Characters: " + character);
            } else {
                System.out.println("File not selected. Please restart program.");
                System.exit(0); //Shuts down program
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found!");
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
