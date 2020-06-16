package Application;

import Application.Tools.HSL;
import Application.Tools.RGB;
import Application.Tools.Transform;
import UI.Canvas;
import UI.Panels.HSLPanel;
import UI.Panels.PanelMgr;
import UI.Panels.RGBPanel;
import UI.Window;
import PicHandler.Picture;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static final int monitorW = 1440;
    public static final int monitorH = 600;


    public static int COLOR_MODE = PanelMgr.DARK_MODE;
    public static Window mainWindow;
    public static Canvas canvas;

    public static PanelMgr manager;
    public static RGBPanel rgbPanel;
    public static HSLPanel hslPanel;


    public static JButton renderBtn;

    public static Picture pic;
    public static String imgPath = "G:\\Shared drives\\Photo 2019\\Photography_ALBERTM\\Freelance\\MtSi2\\IMG_0020.jpg";



    public static void initPanelMGR(){
        manager = new PanelMgr(COLOR_MODE);
        Window panel = new Window(PanelMgr.WIDTH, PanelMgr.HEIGHT);
        panel.setText("Tools");
        panel.add(manager);
        panel.show();
        renderBtn = manager.buttons.get(PanelMgr.RENDER_BUTTON);
    }

    public static void init(){
//        pic.explore();
        mainWindow = new Window(pic.getWidth(), pic.getHeight() );
//        System.out.println(pic.getWidth() + ", " + pic.getHeight());
        canvas = new Canvas();
        mainWindow.add(canvas);
        canvas.addImage("main", pic);
        JButton render = new JButton("render");
        render.setLocation(canvas.getWidth(), canvas.getHeight());
        canvas.add(render);
        canvas.setLayout(null);

        mainWindow.setText("RGB");

    }

    public static void main(String[] args) throws InterruptedException {
        Transform.simpleTransform(pic = new Picture(imgPath),(int) Math.round(pic.getHeight()/((double)monitorH))).write("G:\\Shared drives\\Photo 2019\\Photography_ALBERTM\\Freelance\\MtSi2\\IMG_00202.jpg");
        Thread.sleep(500);
        pic = new Picture("G:\\Shared drives\\Photo 2019\\Photography_ALBERTM\\Freelance\\MtSi2\\IMG_00202.jpg");

        init();
        initPanelMGR();
        rgbPanel = manager.rgbPanel;
        hslPanel = manager.hslPanel;

        renderBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvas.images.remove("main");
//                System.out.println(rgbPanel.r/127.0 + " ; " +rgbPanel.g/127.0+ " ; " +rgbPanel.b/127.0);

                Picture newImg = RGB.rgb(pic, rgbPanel.r, rgbPanel.g, rgbPanel.b);

                System.out.println("hue: " + (hslPanel.s / 100.0));
                if (hslPanel.s != 0){
                    newImg = HSL.saturate(newImg, (hslPanel.s / 100.0));
                }
                canvas.addImage("main", newImg);

                canvas.repaint();
                pic = new Picture(imgPath);
            }
        });

    }


}

