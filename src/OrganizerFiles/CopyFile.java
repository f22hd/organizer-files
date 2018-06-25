package OrganizerFiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 *
 * @author Fahd Allebdi
 */
public class CopyFile {

    // constructor
    public CopyFile() {

    }

    public void moveFiles(ArrayList<String> extenstions, String folderName) {

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

    private String createFolder(String folderName) {
        File folder = new File(Organize.BASE_PATH + folderName);

        if (!folder.exists()) {
            folder.mkdir();
            System.out.println("created folder : " + folderName);
        }

        return folder.getAbsolutePath();
    }

    private ArrayList<File> getFiles(ArrayList<String> extenions) {
        ArrayList<File> list = new ArrayList<>();
        File allFilesInPath[] = new File(Organize.BASE_PATH).listFiles();

        String ext;
        for (File file : allFilesInPath) {
            int lastIndex = file.getName().lastIndexOf("."); // example: .png
            ext = file.getName().substring(lastIndex + 1).toLowerCase(); // without . , will be png

            if (extenions.contains(ext)) {
                // exist 
                list.add(file);
            }
        }

        // looping and finding matched ext files. 
        return list;
    }

    private void copyAllFiles(ArrayList<File> files, String destination) {
        int copiedFileCount = 0;
        String fileName;
        File dest;
        for (File file : files) {
            // perpare destination file name
            fileName = file.getName();
            dest = new File(destination + Organize.SEPARATOR + fileName); // downloads/images/example.png

            if (copySingleFile(file, dest)) {
                copiedFileCount++;
            } else {
                System.out.println("something error");
            }

        } // end for loop

        System.out.println("copied files count is " + copiedFileCount + " of " + files.size());
    }

    // only copy and delete source file
    private boolean copySingleFile(File source, File destination) {

        if (source.isDirectory()) {
            System.out.println(source.getName() + " is directory, can't move it");
            return false;
        }

        InputStream inStream = null;
        OutputStream outStream = null;

        try {
            inStream = new FileInputStream(source);
            outStream = new FileOutputStream(destination);

            byte[] buffer = new byte[1024];
            int length = 0;

            System.out.print("copying " + source.getName() + ".... ");

            while ((length = inStream.read(buffer)) > 0) {

                outStream.write(buffer, 0, length);

            }
            System.out.print("Done, it's copied into " + destination.getPath());

            System.out.println();

            // delete Source File
            source.delete();

            return true;

        } catch (IOException ex) {

            System.out.print(ex.getMessage());

        } finally {
            try {

                inStream.close();
                outStream.close();

            } catch (IOException ex) {

                System.out.print(ex.getMessage());

            }

        }

        return false;
    }

}
