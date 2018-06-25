package OrganizerFiles;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fahd Allebdi
 */
public class CopyFile {

    // constructor
    public CopyFile() {

    }

    public static void moveFiles(ArrayList<String> extenstions, String folderName) {

        // find a list of files matched extenstions array
        ArrayList<File> files = getFiles(extenstions);

        if (files.isEmpty()) {
            return; // no files , go back.
        }
        // create new folder if not exist by folderName value.
        String folderPath = createFolder(folderName);
        // copy all files into destination folder
        System.out.println("start moving files....");
        copyAllFiles(files, folderPath);

    }

    private static String createFolder(String folderName) {

        Path path = Paths.get(Organize.BASE_PATH + folderName);
        try {
            path = Files.createDirectory(path);
        } catch (IOException ex) {
            System.out.println("Directory is exist");
        }
        System.out.println(path.toString());
        return path.toString();
    }

    private static ArrayList<File> getFiles(ArrayList<String> extenions) {
        ArrayList<File> list = new ArrayList<>();
        File allFilesInPath[] = new File(Organize.BASE_PATH).listFiles();

        String ext;
        for (File file : allFilesInPath) {
            int lastIndex = file.getName().lastIndexOf("."); // example: .png
            ext = file.getName().substring(lastIndex + 1).toLowerCase(); // remove . , will be png only

            if (extenions.contains(ext)) {
                // exist 
                list.add(file);
            }
        }

        // looping and finding matched ext files. 
        return list;
    }

    private static void copyAllFiles(ArrayList<File> files, String destination) {
        int copiedFileCount = 0;
        String fileName;

        for (File file : files) {
            // perpare destination file name
            fileName = destination + Organize.SEPARATOR + file.getName(); // downloads/images/example.png

            Path dest = Paths.get(fileName);

            if (copySingleFile(file.toPath(), dest))
                copiedFileCount++;
            

        } // end for loop

        System.out.println("copied files count is " + copiedFileCount + " of " + files.size());
    }

    private static boolean copySingleFile(Path source, Path destination) {

        try {

            Files.move(source, destination,StandardCopyOption.REPLACE_EXISTING);
            return true;

        }catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return false;

    }

}
