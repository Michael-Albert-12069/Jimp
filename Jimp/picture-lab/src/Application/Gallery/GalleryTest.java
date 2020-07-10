package Application.Gallery;

import Application.Main;
import UI.Window;

public class GalleryTest {
    public static Gallery gallery;
    public static String directory = "C:\\Users\\Michael Albert\\Documents\\Code\\Python\\Pimp\\pics";

    public static void main(String[] args){
        gallery = new Gallery(directory);
        gallery.loadImages();
        //        System.out.println(ImageList.isImg("c/d/f/g/img.txt"));
    }
}
