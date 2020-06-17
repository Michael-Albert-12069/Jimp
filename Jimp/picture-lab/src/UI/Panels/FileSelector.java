package UI.Panels;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.io.File;

public abstract class FileSelector  extends JFileChooser {

    String filePath = "null";

    public FileSelector(){
        super(FileSystemView.getFileSystemView().getHomeDirectory());
        this.addChoosableFileFilter(new FileNameExtensionFilter("Images", "png", "jpg", "jpeg", "bmp"));
        // invoke the showsOpenDialog function to show the save dialog
        int r = this.showOpenDialog(null);

        // if the user selects a file
        if (r == JFileChooser.APPROVE_OPTION) {
            //print Filepath
            System.out.println(this.getSelectedFile().getAbsolutePath());
            filePath = this.getSelectedFile().getAbsolutePath();
            onSelection(filePath);
        }
        // if the user cancelled the operation
        else {
            System.out.println(("the user cancelled the operation"));
            onCancellation();
        }
    }

    public abstract void onSelection(String filepath);
    public abstract void onCancellation();


}
