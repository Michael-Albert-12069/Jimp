package Application.Gallery;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

class ImageList extends ArrayList<File> {
    public static final String[] FILETYPES = new String[] {".jpg", ".jpeg",".png", ".bmp"};

    public static boolean isImg(String filename){
        String fname = filename.toLowerCase();
        for (String filetype: FILETYPES) {
            if (fname.endsWith(filetype)){
                return true;
            }
        }
        return false;
    }


    public void addFromFolder(final File folder) {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                addFromFolder(fileEntry);
            } else {
                String filename=  fileEntry.getName();
                if (isImg(filename)) {
                    this.add(fileEntry);
                }
            }
        }
    }
    public void addFromFolder(final String dir) {
        File folder = new File(dir);
        for (final File fileEntry : Objects.requireNonNull(folder.listFiles())) {
            if (fileEntry.isDirectory()) {
                addFromFolder(fileEntry);
            } else {
                String filename=  fileEntry.getName();
                if (isImg(filename)) {
                    this.add(fileEntry);
                }
            }
        }
    }

    public String toString(){
        String out = "";
        for (File f: this) {
            out += f.getAbsolutePath() + ", ";
        }
        return out;
    }


}