package Application;

import Application.Tools.HSL;
import Application.Tools.RGB;
import UI.Canvas;
import UI.JFramePositioner;
import UI.Panels.FileSelector;
import UI.Panels.HSLPanel;
import UI.Panels.PanelMgr;
import UI.Panels.RGBPanel;
import UI.Window;
import PicHandler.Picture;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static final int WINDOW_PADDING = 38;
    public static final int MONITOR_W = 1400;
    public static final int MONITOR_H = (int) (900 * (2.5/4.0));


    public static double windowShrink;


    public static int shrinkFactor;

    public static int COLOR_MODE = PanelMgr.DARK_MODE;
    public static Window mainWindow;
    public static Canvas canvas;

    public static PanelMgr manager;
    public static RGBPanel rgbPanel;
    public static HSLPanel hslPanel;


    public static JButton renderBtn;

    public static Picture pic;
    public static String imgPath;



    public static void initPanelMGR(){
        manager = new PanelMgr(COLOR_MODE) {
            @Override
            public void onChange() {
                //very memory intensive
//                C:\Users\Micha\Pictures\Camera Roll\20191210_080916.jpg
                    render();
            }

            @Override
            public void onRender() {
                render();
            }
        };

        Window panel = new Window(MONITOR_W - RGBPanel.WIDTH - WINDOW_PADDING, PanelMgr.HEIGHT);
        JFramePositioner.setLocationToLeft(panel.frame);
        manager.positionWindows();
        panel.setText("Tools");
        panel.add(manager);
        panel.show();
        renderBtn = manager.buttons.get(PanelMgr.RENDER_BUTTON);
    }

    public static void init(){
//        pic.explore();
        mainWindow = new Window((int) (pic.getWidth()),
                                (int) (pic.getHeight()));
//        System.out.println(pic.getWidth() + ", " + pic.getHeight());
        JFramePositioner.setLocationTo(mainWindow.frame, 0, (int) (PanelMgr.HEIGHT + (WINDOW_PADDING * 1.5)));
        // the eight and thirty are to adjust for internal window rendering BS
        canvas = new Canvas();
        mainWindow.add(canvas);
        canvas.addImage("main", pic);
        JButton render = new JButton("render");
        render.setLocation(canvas.getWidth(), canvas.getHeight());
        canvas.add(render);
        canvas.setLayout(null);
        mainWindow.show();
        mainWindow.setText("Picture " + imgPath);

    }

    public static void loadPicture(){
        FileSelector selector = new FileSelector() {
            @Override
            public void onSelection(String filepath) {
                imgPath = filepath;
                if (filepath.equals("null")){ //
                    System.exit(0);
                }
            }

            @Override
            public void onCancellation() {
                System.exit(0);
            }
        };
        Picture reference = new Picture(imgPath);
        int maxWH = Math.max(reference.getHeight() / MONITOR_H, reference.getWidth() / MONITOR_W);
        /**
         * if the picture is smaller than the monitor size
         * the integer 'maxWH' will cast to zero
         * the Max(1, maxWH)
         */
        shrinkFactor = Math.max(maxWH, 1);
        System.out.println(shrinkFactor);
        pic = new Picture(imgPath).shrink(shrinkFactor);
    }

    public static void main(String[] args) throws InterruptedException {
        loadPicture();
        init();
        initPanelMGR();

        rgbPanel = manager.rgbPanel;
        hslPanel = manager.hslPanel;

        renderBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                render();
            }
        });

    }

    public static void render(){
        canvas.images.remove("main");
//                System.out.println(rgbPanel.r/127.0 + " ; " +rgbPanel.g/127.0+ " ; " +rgbPanel.b/127.0);

        Picture newImg = new Picture(pic.getHeight(), pic.getWidth());
        newImg.copyPicture(pic);



        if (hslPanel.h != -1){
            newImg = HSL.hue(newImg, hslPanel.h);
        }
        if (hslPanel.s != 0){
            newImg = HSL.saturate(newImg, (hslPanel.s / 100.0));
        }
        if (hslPanel.l != 0){
            int lum = (int) (((hslPanel.l / 100.0) * 127.0) + 127);
//            System.out.println("brightness = " + lum);
            newImg = HSL.luminate(newImg, lum);
        }
        if (!(rgbPanel.r == 127 && rgbPanel.g == 127 && rgbPanel.b == 127)){
            newImg = RGB.rgb(newImg, rgbPanel.r, rgbPanel.g, rgbPanel.b);
        }



        canvas.addImage("main", newImg);

        canvas.repaint();
    }

}

