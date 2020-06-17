package UI.Panels;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class RGBPanel extends JPanel{
    public JSlider redSlider;
    public JTextField rInput;
    public JLabel rLabel;

    public JSlider greenSlider;
    public JTextField gInput;
    public JLabel gLabel;


    public JSlider blueSlider;
    public JTextField bInput;
    public JLabel bLabel;


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
        rLabel = new JLabel();
        rLabel.setForeground( FG);
        rLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));

        rInput = new JTextField(r + "");
        rInput.setForeground(FG);
        rInput.setBackground(BG);

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
//                System.out.println(b);
                rLabel.setText("Red  [" + numberSpacer(r,3 ) + "]:  ");
                rInput.setText(r + "");
                onUpdate();

            }
        });
        rInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                r = Integer.parseInt(rInput.getText());
                if (r > 255){
                    r = 255;
                } else if (r < 0){
                    r = 0;
                }
                redSlider.setValue(r);
                rLabel.setText("Red  [" + numberSpacer(r,3 ) + "]:  ");
                rInput.setText(r + "");
                onUpdate();
            }
        });


        rLabel.setText("Red   [" + numberSpacer(redSlider.getValue(),3 ) + "]: ");
        this.add(rLabel);
        this.add(rInput);
        this.add(redSlider);
    }
    public void initBlue(){
        bLabel = new JLabel();
        bLabel.setForeground( FG);
        bLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
        bInput = new JTextField(b + "");
        bInput.setForeground(FG);
        bInput.setBackground(BG);

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
                bLabel.setText("Blue  [" + numberSpacer(b,3 ) + "]:  ");
                bInput.setText(b + "");
                onUpdate();

            }
        });
        bInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b = Integer.parseInt(bInput.getText());
                if (b > 255){
                    b = 255;
                } else if (b < 0){
                    b = 0;
                }
                blueSlider.setValue(b);
                bLabel.setText("Blue  [" + numberSpacer(b,3 ) + "]: ");
                bInput.setText(b + "");
                onUpdate();

            }
        });
        bLabel.setText("Blue  [" + numberSpacer(blueSlider.getValue(),3 ) + "]:  ");
        this.add(bLabel);
        this.add(bInput);
        this.add(blueSlider);
    }
    public void initGreen(){
        gLabel = new JLabel();
        gLabel.setForeground( FG);

        gInput = new JTextField(g + "");
        gInput.setForeground(FG);
        gInput.setBackground(BG);

        gLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
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
                gLabel.setText("Green [" + numberSpacer(g,3 ) + "]: ");
                gInput.setText(g + "");
                onUpdate();

            }
        });
        gInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                g = Integer.parseInt(gInput.getText());
                if (g > 255){
                    g = 255;
                } else if (g < 0){
                    g = 0;
                }
                greenSlider.setValue(g);
                gLabel.setText("Green [" + numberSpacer(g,3 ) + "]: ");
                gInput.setText(g + "");
                onUpdate();

            }
        });
        gLabel.setText("Green [" + numberSpacer(greenSlider.getValue(),3 ) + "]: ");
        this.add(gLabel);
        this.add(gInput);
        this.add(greenSlider);
    }

    public void setR(int red){
        r = red;
        if (r > 255){
            r = 255;
        } else if (r < 0){
            r = 0;
        }
        redSlider.setValue(r);
        rLabel.setText("Red  [" + numberSpacer(r,3 ) + "]:  ");
        rInput.setText(r + "");
        onUpdate();
    }
    public void setG(int green){
        g = green;
        if (g > 255){
            g = 255;
        } else if (g < 0){
            g = 0;
        }
        greenSlider.setValue(g);
        gLabel.setText("Green[" + numberSpacer(g,3 ) + "]:  ");
        gInput.setText(g + "");
        onUpdate();
    }
    public void setB(int blue){
        b = blue;
        if (b > 255){
            b = 255;
        } else if (b < 0){
            b = 0;
        }
        blueSlider.setValue(b);
        bLabel.setText("Green[" + numberSpacer(b,3 ) + "]:  ");
        bInput.setText(b + "");
        onUpdate();
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

    public abstract void onUpdate();

}
