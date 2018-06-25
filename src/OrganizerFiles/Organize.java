package OrganizerFiles;

import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
/**
 *
 * @author Fahd Allebdi
 */
public class Organize {

    public static final String SEPARATOR = File.separator;
    public static final String BASE_PATH = "/Users/fahd/Downloads/";

    // extenstions
    private final String[] PICTURES = {"jpeg", "jpg", "png", "gif", "bmp", "ico"};
    private final String[] VIDEOS = {"mp4", "flv", "mp3", "wmv", "3gp", "avi"};
    private final String[] ARCHIVES = {"zip", "tar", "gz"};
    private final String[] DOCUMENTS = {"docx", "doc", "pdf", "ppt","pptx", "xls", "txt", "odt", "ott", "xml", "csv"};
    private final String[] APPLICATION = {"exe", "sh", "dmg", "msi", "pkg"};
    private final String[] OTHERS = {"json", "php", "css", "sql", "py", "mo", "po", "jar", "war"};

    // folders name
    private final String APPLICATION_FOLDER = "Applications";
    private final String PICTURES_FOLDER = "Pictures";
    private final String VIDEOS_FOLDER = "VIDEOS";
    private final String ARCHIVES_FOLDER = "Archives";
    private final String DOCUMENTS_FOLDER = "Documents";
    private final String OTHERS_FOLDER = "Others";

    public Organize() {
    }

    public void start() {
 
        CopyFile.moveFiles(getPictures(), PICTURES_FOLDER);
        CopyFile.moveFiles(getVideos(), VIDEOS_FOLDER);
        CopyFile.moveFiles(getApplication(), APPLICATION_FOLDER);
        CopyFile.moveFiles(getDocuments(), DOCUMENTS_FOLDER);
        CopyFile.moveFiles(getArchives(), ARCHIVES_FOLDER);
        CopyFile.moveFiles(getOthers(), OTHERS_FOLDER);

        System.out.println("Done");
    }

    public ArrayList<String> getPictures() {
        return toArrayList(PICTURES);
    }

    public ArrayList<String> getVideos() {
        return toArrayList(VIDEOS);
    }

    public ArrayList<String> getArchives() {
        return toArrayList(ARCHIVES);
    }

    public ArrayList<String> getDocuments() {
        return toArrayList(DOCUMENTS);
    }

    public ArrayList<String> getApplication() {
        return toArrayList(APPLICATION);
    }

    public ArrayList getOthers() {
        return toArrayList(OTHERS);
    }

    // converting regular array to ArrayList
    private ArrayList<String> toArrayList(String[] ext) {
        ArrayList<String> list = new ArrayList<>();

        list.addAll(Arrays.asList(ext));

        return list;
    }

}
