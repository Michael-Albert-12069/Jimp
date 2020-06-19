package UI.Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import Application.Main;
import UI.JFramePositioner;
import UI.Window;
public abstract class PanelMgr extends JPanel{
    public JSlider redSlider;
    public JSlider greenSlider;
    public JSlider blueSlider;
    Color FG = Color.BLACK;
    Color MG = hexColor("#e8e8e8");
    Color BG = Color.WHITE;

//    public boolean adjustingInProgress = false;

    private int mode;

    public PanelMgr(int colorMode){
        super(new FlowLayout(FlowLayout.LEFT));

        if (colorMode == DARK_MODE){
            this.darkMode();
        }
        if (colorMode == LIGHT_MODE){
            this.lightMode();
        }
        this.mode = colorMode;

        this.setBackground(BG);
        setRenderButton();
        initRGB();
        initHSL();
    }
    public static final int LIGHT_MODE = 0;
    public static final int DARK_MODE = 1;

    public static final int WIDTH = (int) (Main.MONITOR_W * (3.0/4.0));
    public static final int HEIGHT = 50;

    public RGBPanel rgbPanel;
    public HSLPanel hslPanel;

    public void lightMode(){
        FG = Color.BLACK;
        MG = hexColor("#e8e8e8");
        BG = Color.WHITE;
    }
    public void darkMode(){
        FG = Color.WHITE;
        MG = Color.DARK_GRAY;
        BG = Color.DARK_GRAY;
    }

    public static final String RGB_BUTTON = "RGB";
    public static final String RGB_WINDOW = "RGBW";
    public static boolean RGB_VISIBLE = false;

    public static final String HSL_BUTTON = "HSL";
    public static final String HSL_WINDOW = "HSLW";
    public static boolean HSL_VISIBLE = false;

    public static final String RENDER_BUTTON = "RENDER";


    public HashMap<String, JButton> buttons = new HashMap<>();
    public HashMap<String, Window> windows = new HashMap<>();

    public void setRenderButton(){
        JButton button = new JButton("Render Picture");
        button.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        if (mode == DARK_MODE){
            button.setBackground(hexColor("#00b57c"));
        } else {
            button.setBackground(hexColor("#00ffae"));
        }
        button.setForeground(FG);
        this.add(button);
        buttons.put(RENDER_BUTTON, button);
    }


    public void initRGB(){
        JButton button = new JButton("RGB");
        button.setFont(new Font(Font.MONOSPACED, Font.BOLD, 17));
        button.setBackground(MG);
        button.setForeground(FG);
        this.add(button);

        buttons.put(RGB_BUTTON, button);
        Window window = new Window(240, 280);
        window.frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        window.setText("RGB");
        rgbPanel = new RGBPanel(this.mode) {
            @Override
            public void onUpdate() {
                onChange();
            }
        };
        window.add(rgbPanel);
        window.hide();
        windows.put(RGB_WINDOW, window);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PanelMgr.RGB_VISIBLE = !PanelMgr.RGB_VISIBLE;
                if (PanelMgr.RGB_VISIBLE){
                    window.show();
                } else {
                    window.hide();
                }
            }
        });

    }
    public void initHSL(){
        JButton button = new JButton("HSL");
        button.setFont(new Font(Font.MONOSPACED, Font.BOLD, 17));
        button.setBackground(MG);
        button.setForeground(FG);
        this.add(button);
        buttons.put(HSL_BUTTON, button);
        Window window = new Window(240, 280);

        window.frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        window.setText("HSL");
        hslPanel = new HSLPanel(this.mode) {
            @Override
            public void onUpdate() {
                onChange();
            }
        };
        window.add(hslPanel);
        window.hide();
        windows.put(HSL_WINDOW, window);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PanelMgr.HSL_VISIBLE = !PanelMgr.HSL_VISIBLE;
                if (PanelMgr.HSL_VISIBLE){
                    window.show();
                } else {
                    window.hide();
                }
            }
        });

    }

    public void positionWindows(){
        Window RGB = windows.get(RGB_WINDOW);
        JFramePositioner.setLocationToTopRight(RGB.frame);
        Window HSL = windows.get(HSL_WINDOW);
        JFramePositioner.setLocationToTopRight(HSL.frame);
        JFramePositioner.setYTo(HSL.frame, 0 + RGBPanel.HEIGHT + (Main.WINDOW_PADDING * 2));
    }

    /**
     * @return whether or not the mouse it clicked
     * true = clicked
     * false = not clicked
     */
    public abstract void onChange();
    public abstract void onRender();


    private static String numberSpacer(int number, int digits){
        Integer num = number;
        String n = num.toString();
        String out = "";
        for (int i = 0; i < digits - n.length(); i++) {
            out += "_";
        }
        out += n;
        return out;
    }

    private static Color hexColor(String colorStr) {
        return new Color(
                Integer.valueOf( colorStr.substring( 1, 3 ), 16 ),
                Integer.valueOf( colorStr.substring( 3, 5 ), 16 ),
                Integer.valueOf( colorStr.substring( 5, 7 ), 16 ) );
    }
}
