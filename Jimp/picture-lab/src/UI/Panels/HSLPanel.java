package UI.Panels;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class HSLPanel extends JPanel{
    public JSlider hueSlider;
    public JSlider satSlider;
    public JSlider lumSlider;
    Color FG = Color.BLACK;
    Color MG = Color.LIGHT_GRAY;
    Color BG = Color.WHITE;


    public int h = 0;
    public int s = 0;
    public int l = 0;

    public HSLPanel(int colorMode){
        super(new FlowLayout(FlowLayout.LEFT));

        if (colorMode == DARK_MODE){
            this.darkMode();
        }
        if (colorMode == LIGHT_MODE){
            this.lightMode();
        }
        this.setBackground(BG);
//        initHue();
        initSat();
        initLum();
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

//    public void initHue(){
//        JLabel jLabel = new JLabel();
//        jLabel.setForeground( FG);
//        jLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
//
//        JTextField inputColor = new JTextField(h + "");
//        inputColor.setForeground(FG);
//        inputColor.setBackground(BG);
//
//        hueSlider = new JSlider(0,255,128);
//        hueSlider.setBackground(BG);
//        hueSlider.setForeground( FG);
//
//        // paint the ticks and tarcks
//        hueSlider.setPaintTrack(true);
//        hueSlider.setPaintTicks(true);
//        hueSlider.setPaintLabels(true);
//        // set spacing
//        hueSlider.setMajorTickSpacing(127);
//        hueSlider.setMinorTickSpacing(3);
//        hueSlider.addChangeListener(new ChangeListener() {
//            @Override
//            public void stateChanged(ChangeEvent e) {
//                h = hueSlider.getValue();
////                System.out.println(r);
//                jLabel.setText("Hue   [" + numberSpacer(h,3 ) + "]: ");
//                inputColor.setText(h + "");
//                onUpdate();
//            }
//        });
//        inputColor.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                h = Integer.parseInt(inputColor.getText());
//                if (h > 255){
//                    h = 255;
//                } else if (h < 0){
//                    h = 0;
//                }
//                hueSlider.setValue(h);
//                jLabel.setText("Hue   [" + numberSpacer(h,3 ) + "]: ");
//                inputColor.setText(h + "");
//                onUpdate();
//            }
//        });
//        jLabel.setText("Hue   [" + numberSpacer(hueSlider.getValue(),3 ) + "]: ");
//        this.add(jLabel);
//        this.add(inputColor);
//        this.add(hueSlider);
//    }


    public void initLum(){
        JLabel jLabel = new JLabel();
        jLabel.setForeground( FG);
        jLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
        JTextField inputColor = new JTextField(l + "");
        inputColor.setForeground(FG);
        inputColor.setBackground(BG);

//        blueLabel.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 20));
        lumSlider = new JSlider(-100,100,0);
        lumSlider.setBackground(BG);
        lumSlider.setForeground( FG);

        // paint the ticks and tarcks
        lumSlider.setPaintTrack(true);
        lumSlider.setPaintTicks(true);
        lumSlider.setPaintLabels(true);
        // set spacing
        lumSlider.setMajorTickSpacing(25);
        lumSlider.setMinorTickSpacing(5);
        lumSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                l = lumSlider.getValue();
                System.out.println(l);
                jLabel.setText("" +
                        "Luminance  [" + numberSpacer(l,3 ) + "]:  ");
                inputColor.setText(l + "");
                onUpdate();

            }
        });
        inputColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                l = Integer.parseInt(inputColor.getText());
                if (l > 255){
                    l = 255;
                } else if (l < 0){
                    l = 0;
                }
                lumSlider.setValue(l);
                jLabel.setText("Luminance  [" + numberSpacer(l,3 ) + "]: ");
                inputColor.setText(l + "");
                onUpdate();


            }
        });
        jLabel.setText("Luminance  [" + numberSpacer(l,3 ) + "]:  ");
        this.add(jLabel);
        this.add(inputColor);
        this.add(lumSlider);
    }
    public void initSat(){
        JLabel jLabel = new JLabel();
        jLabel.setForeground( FG);

        JTextField inputColor = new JTextField(s + "");
        inputColor.setForeground(FG);
        inputColor.setBackground(BG);

        jLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
        satSlider = new JSlider(-100,100,0);
        satSlider.setBackground(BG);
        satSlider.setForeground( FG);

        // paint the ticks and tarcks
        satSlider.setPaintTrack(true);
        satSlider.setPaintTicks(true);
        satSlider.setPaintLabels(true);
        // set spacing
        satSlider.setMajorTickSpacing(50);
        satSlider.setMinorTickSpacing(10);
        satSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                s = satSlider.getValue();
                System.out.println(s);
                jLabel.setText("Saturation [" + numberSpacer(s,3 ) + "]: ");
                inputColor.setText(s + "");
                onUpdate();

            }
        });
        inputColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                s = Integer.parseInt(inputColor.getText());
                if (s > 100){
                    s = 100;
                } else if (s < -100){
                    s = -100;
                }
                satSlider.setValue(s);
                jLabel.setText("Saturation [" + numberSpacer(s,3 ) + "]: ");
                inputColor.setText(s + "");
                onUpdate();
            }
        });
        jLabel.setText("Saturation [" + numberSpacer(satSlider.getValue(),3 ) + "]: ");
        this.add(jLabel);
        this.add(inputColor);
        this.add(satSlider);
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
