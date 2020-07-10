package Application.Gallery;

import Application.Editor;
import Application.Main;
import PicHandler.Picture;
import UI.Canvas;
import UI.Window;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Gallery extends Window {
    public ImageList images = new ImageList();
    public Canvas canvas = new Canvas(Main.MONITOR_W, (int)(Main.MONITOR_H * 0.9));

    public Gallery(String dirpath) {
        super(Main.MONITOR_W, (int)(Main.MONITOR_H * 0.9));
        images.addFromFolder(dirpath);
        this.add(canvas);
    }
    public Gallery(File dir) {
        super(Main.MONITOR_W, (int)(Main.MONITOR_H * 0.9));
        images.addFromFolder(dir);
        this.add(canvas);
    }
    public Gallery() {
        super(Main.MONITOR_W, (int)(Main.MONITOR_H * 0.9));
        this.add(canvas);
        repaint();
    }

    public void loadImages(){
        for (File image: images){
            addImgBtn(image.getAbsolutePath());
        }
    }
    public void addImgBtn(String imgPath){
        JButton button = new JButton();
        Picture p = new Picture(imgPath);
        ImageIcon thumbnail = new ImageIcon(getScaledImage(p.getImage(), 128, 128));
        button.setIcon(thumbnail);
        canvas.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Editor.editPicture(imgPath);
            }
        });
        repaint();
    }

    private Image getScaledImage(Image srcImg, int w, int h){
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }

    public void repaint(){
        this.show();
        this.frame.show();
        canvas.show();
        this.frame.repaint();
        this.canvas.repaint();
    }
}
