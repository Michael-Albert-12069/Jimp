package UI.Panels;

import UI.Window;

import javax.swing.*;
import java.awt.*;

public abstract class Panel extends JPanel {

    //dark and light mode handling
    //----------------------------------------------------------------------------
    public Color FG = Color.BLACK;
    public Color MG = Color.LIGHT_GRAY;
    public Color BG = Color.WHITE;
    //emphasized button;
    public Color EB = hexColor("#00ffae");

    private void lightMode(){
        FG = Color.BLACK;
        MG = Color.LIGHT_GRAY;
        EB = hexColor("#00ffae");
        BG = Color.WHITE;
    }
    private void darkMode(){
        FG = Color.WHITE;
        MG = Color.DARK_GRAY;
        EB =  hexColor("#00b57c");
        BG = Color.DARK_GRAY;
    }

    private static Color hexColor(String colorStr) {
        return new Color(
                Integer.valueOf( colorStr.substring( 1, 3 ), 16 ),
                Integer.valueOf( colorStr.substring( 3, 5 ), 16 ),
                Integer.valueOf( colorStr.substring( 5, 7 ), 16 ) );
    }

    public static final int LIGHT_MODE = 0;
    public static final int DARK_MODE = 1;

    public void initTool(String toolName) {
        new Tool(this, toolName) {
            @Override
            public void onToolChange() {
                onPanelChange();
            }
        }.init(100,200,150);
    }
    public Panel(String... tools) {
        super(new FlowLayout(FlowLayout.LEFT));
        int colorMode = DARK_MODE;
        if (colorMode == DARK_MODE){
            this.darkMode();
        }
        if (colorMode == LIGHT_MODE){
            this.lightMode();
        }
        this.setBackground(BG);

        for (String tool: tools) {
            initTool(tool);
        }
    }

    public abstract void onPanelChange();


    public static void main(String[] args) {
        UI.Window window = new Window(240, 280);
        window.frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        window.setText("RGB");
        Panel p = new Panel("red") {
            @Override
            public void onPanelChange() {
                System.out.println("change");
            }
        };
        window.add(p);
        p.repaint();
        p.show();
        window.show();

    }



}
