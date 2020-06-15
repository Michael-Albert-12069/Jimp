package Application;

import Application.Tools.RGB;
import UI.Canvas;
import UI.Panels.PanelMgr;
import UI.Panels.RGBPanel;
import UI.Window;
import PicHandler.Picture;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static int COLOR_MODE = PanelMgr.DARK_MODE;
    public static RGBPanel rgbPanel;
    public static Window mainWindow;
    public static Canvas canvas;
    public static PanelMgr manager;
    public static JButton renderBtn;

    public static Picture pic;

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
        pic = new Picture("C:\\Users\\Michael Albert\\Documents\\Code\\Jimp\\picture-lab\\resources\\images\\water.jpg");
        init();
        initPanelMGR();
        rgbPanel = manager.rgbPanel;

        renderBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println(rgbPanel.r/127.0 + " ; " +rgbPanel.g/127.0+ " ; " +rgbPanel.b/127.0);
                canvas.images.remove("main");
                canvas.addImage("main", RGB.rgb(pic, rgbPanel.r, rgbPanel.g, rgbPanel.b));

                canvas.repaint();
                pic = new Picture("C:\\Users\\Michael Albert\\Documents\\Code\\Jimp\\picture-lab\\resources\\images\\water.jpg");
            }
        });

    }
}

