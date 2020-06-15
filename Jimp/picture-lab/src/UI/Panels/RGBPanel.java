package UI.Panels;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RGBPanel extends JPanel{
    public JSlider redSlider;
    public JSlider greenSlider;
    public JSlider blueSlider;
    Color FG = Color.BLACK;
    Color MG = Color.LIGHT_GRAY;
    Color BG = Color.WHITE;


    public int r = 127;
    public int g = 127;
    public int b = 127;

    public RGBPanel(int colorMode){
        super(new FlowLayout(FlowLayout.LEFT));

        if (colorMode == DARK_MODE){
            this.darkMode();
        }
        if (colorMode == LIGHT_MODE){
            this.lightMode();
        }
        this.setBackground(BG);
        initRed();
        initGreen();
        initBlue();
    }
    public static final int LIGHT_MODE = 0;
    public static final int DARK_MODE = 1;

    public static final int WIDTH = 235 - 15;
    public static final int HEIGHT = 280 - 38;


    public void lightMode(){
        FG = Color.BLACK;
        MG = Color.LIGHT_GRAY;
        BG = Color.WHITE;
    }
    public void darkMode(){
        FG = Color.WHITE;
        MG = Color.DARK_GRAY;
        BG = Color.DARK_GRAY;
    }

    public void initRed(){
        JLabel jLabel = new JLabel();
        jLabel.setForeground( FG);
        jLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));

        JTextField inputColor = new JTextField(r + "");
        inputColor.setForeground(FG);
        inputColor.setBackground(BG);

        redSlider = new JSlider(0,255,128);
        redSlider.setBackground(BG);
        redSlider.setForeground( FG);

        // paint the ticks and tarcks
        redSlider.setPaintTrack(true);
        redSlider.setPaintTicks(true);
        redSlider.setPaintLabels(true);
        // set spacing
        redSlider.setMajorTickSpacing(127);
        redSlider.setMinorTickSpacing(3);
        redSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                r = redSlider.getValue();
//                System.out.println(r);
                jLabel.setText("Red   [" + numberSpacer(r,3 ) + "]: ");
                inputColor.setText(r + "");
            }
        });
        inputColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                r = Integer.parseInt(inputColor.getText());
                if (r > 255){
                    r = 255;
                } else if (r < 0){
                    r = 0;
                }
                redSlider.setValue(r);
                jLabel.setText("Red   [" + numberSpacer(r,3 ) + "]: ");
                inputColor.setText(r + "");
            }
        });
        jLabel.setText("Red   [" + numberSpacer(redSlider.getValue(),3 ) + "]: ");
        this.add(jLabel);
        this.add(inputColor);
        this.add(redSlider);
    }
    public void initBlue(){
        JLabel jLabel = new JLabel();
        jLabel.setForeground( FG);
        jLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
        JTextField inputColor = new JTextField(b + "");
        inputColor.setForeground(FG);
        inputColor.setBackground(BG);

//        blueLabel.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 20));
        blueSlider = new JSlider(0,255,128);
        blueSlider.setBackground(BG);
        blueSlider.setForeground( FG);

        // paint the ticks and tarcks
        blueSlider.setPaintTrack(true);
        blueSlider.setPaintTicks(true);
        blueSlider.setPaintLabels(true);
        // set spacing
        blueSlider.setMajorTickSpacing(127);
        blueSlider.setMinorTickSpacing(3);
        blueSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                b = blueSlider.getValue();
//                System.out.println(b);
                jLabel.setText("Blue  [" + numberSpacer(b,3 ) + "]:  ");
                inputColor.setText(b + "");

            }
        });
        inputColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b = Integer.parseInt(inputColor.getText());
                if (b > 255){
                    b = 255;
                } else if (b < 0){
                    b = 0;
                }
                blueSlider.setValue(b);
                jLabel.setText("Blue  [" + numberSpacer(b,3 ) + "]: ");
                inputColor.setText(b + "");
            }
        });
        jLabel.setText("Blue  [" + numberSpacer(blueSlider.getValue(),3 ) + "]:  ");
        this.add(jLabel);
        this.add(inputColor);
        this.add(blueSlider);
    }
    public void initGreen(){
        JLabel jLabel = new JLabel();
        jLabel.setForeground( FG);

        JTextField inputColor = new JTextField(g + "");
        inputColor.setForeground(FG);
        inputColor.setBackground(BG);

        jLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
        greenSlider = new JSlider(0,255,128);
        greenSlider.setBackground(BG);
        greenSlider.setForeground( FG);

        // paint the ticks and tarcks
        greenSlider.setPaintTrack(true);
        greenSlider.setPaintTicks(true);
        greenSlider.setPaintLabels(true);
        // set spacing
        greenSlider.setMajorTickSpacing(127);
        greenSlider.setMinorTickSpacing(3);
        greenSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                g = greenSlider.getValue();
//                System.out.println(g);
                jLabel.setText("Green [" + numberSpacer(g,3 ) + "]: ");
                inputColor.setText(g + "");

            }
        });
        inputColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                g = Integer.parseInt(inputColor.getText());
                if (g > 255){
                    g = 255;
                } else if (g < 0){
                    g = 0;
                }
                greenSlider.setValue(g);
                jLabel.setText("Green [" + numberSpacer(g,3 ) + "]: ");
                inputColor.setText(g + "");
            }
        });
        jLabel.setText("Green [" + numberSpacer(greenSlider.getValue(),3 ) + "]: ");
        this.add(jLabel);
        this.add(inputColor);
        this.add(greenSlider);
    }

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


}
