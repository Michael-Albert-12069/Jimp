package UI.Panels;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.jar.JarInputStream;

public abstract class HSLPanel extends JPanel{
    public JSlider hueSlider;
    public JLabel hueLabel;
    public JTextField hueInput;

    public JSlider satSlider;
    public JLabel satLabel;
    public JTextField satInput;

    public JSlider lumSlider;
    public JLabel lumLabel;
    public JTextField lumInput;



    Color FG = Color.BLACK;
    Color MG = Color.LIGHT_GRAY;
    Color BG = Color.WHITE;
    //emphasized button;
    Color EB = hexColor("#00ffae");

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
        initHue();
        initSat();
        initLum();
        addReset();
    }
    public static final int LIGHT_MODE = 0;
    public static final int DARK_MODE = 1;

    public static final int WIDTH = 235 - 15;
    public static final int HEIGHT = 280 - 38;


    public void lightMode(){
        FG = Color.BLACK;
        MG = Color.LIGHT_GRAY;
        EB = hexColor("#00ffae");
        BG = Color.WHITE;
    }
    public void darkMode(){
        FG = Color.WHITE;
        MG = Color.DARK_GRAY;
        EB =  hexColor("#00b57c");
        BG = Color.DARK_GRAY;
    }



    public void initHue(){
        hueLabel = new JLabel();
        hueLabel.setForeground( FG);
        hueLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
        hueInput = new JTextField(l + "");
        hueInput.setForeground(FG);
        hueInput.setBackground(BG);

//        blueLabel.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 20));
        hueSlider = new JSlider(0,255,0);
        hueSlider.setBackground(BG);
        hueSlider.setForeground( FG);

        // paint the ticks and tarcks
        hueSlider.setPaintTrack(true);
        hueSlider.setPaintTicks(true);
        hueSlider.setPaintLabels(true);
        // set spacing
        hueSlider.setMajorTickSpacing(127);
        hueSlider.setMinorTickSpacing(3);
        hueSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                h = hueSlider.getValue();
                System.out.println(h);
                hueLabel.setText("Hue        [" + numberSpacer(h,3 ) + "]:  ");
                hueInput.setText(h + "");
                JSlider source = (JSlider)e.getSource();
                if(!source.getValueIsAdjusting())
                {
                    onUpdate();
                }
            }
        });
        hueInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                h = Integer.parseInt(hueInput.getText());
                if (h > 255){
                    h = 255;
                } else if (h < 0){
                    h = 0;
                }
                hueSlider.setValue(l);
                hueLabel.setText("Hue        [" + numberSpacer(h,3 ) + "]: ");
                hueInput.setText(h + "");
                onUpdate();


            }
        });
        hueLabel.setText("Hue        [" + numberSpacer(h,3 ) + "]:  ");
        this.add(hueLabel);
        this.add(hueInput);
        this.add(hueSlider);
    }
    public void initLum(){
        lumLabel = new JLabel();
        lumLabel.setForeground( FG);
        lumLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
        lumInput = new JTextField(l + "");
        lumInput.setForeground(FG);
        lumInput.setBackground(BG);

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
                lumLabel.setText("" +
                        "Luminance  [" + numberSpacer(l,3 ) + "]:  ");
                lumInput.setText(l + "");
                JSlider source = (JSlider)e.getSource();
                if(!source.getValueIsAdjusting())
                {
                    onUpdate();
                }
            }
        });
        lumInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                l = Integer.parseInt(lumInput.getText());
                if (l > 255){
                    l = 255;
                } else if (l < 0){
                    l = 0;
                }
                lumSlider.setValue(l);
                lumLabel.setText("Luminance  [" + numberSpacer(l,3 ) + "]: ");
                lumInput.setText(l + "");
                onUpdate();


            }
        });
        lumLabel.setText("Luminance  [" + numberSpacer(l,3 ) + "]:  ");
        this.add(lumLabel);
        this.add(lumInput);
        this.add(lumSlider);
    }
    public void initSat(){
        satLabel = new JLabel();
        satLabel.setForeground( FG);

        satInput = new JTextField(s + "");
        satInput.setForeground(FG);
        satInput.setBackground(BG);

        satLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
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
                satLabel.setText("Saturation [" + numberSpacer(s,3 ) + "]: ");
                satInput.setText(s + "");
                JSlider source = (JSlider)e.getSource();
                if(!source.getValueIsAdjusting())
                {
                    onUpdate();
                }
            }
        });
        satInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                s = Integer.parseInt(satInput.getText());
                if (s > 100){
                    s = 100;
                } else if (s < -100){
                    s = -100;
                }
                satSlider.setValue(s);
                satLabel.setText("Saturation [" + numberSpacer(s,3 ) + "]: ");
                satInput.setText(s + "");
                onUpdate();
            }
        });
        satLabel.setText("Saturation [" + numberSpacer(satSlider.getValue(),3 ) + "]: ");
        this.add(satLabel);
        this.add(satInput);
        this.add(satSlider);
    }
    public void addReset(){
        JButton reset = new JButton("RESET");
        reset.setBackground(EB);
        reset.setForeground(FG);
        this.add(reset);
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setHue(-1);
                setLum(0);
                setSat(0);
                onUpdate();
            }
        });
    }

    public void setHue(int n){
        h = n;
        if (h > 255){
            h = 255;
        } else if (h < 0){
            h = 0;
        }
        hueSlider.setValue(l);
        hueInput.setText(l + "");
        hueLabel.setText("Hue        [" + numberSpacer(h,3 ) + "]:  ");
        if (n == -1){
            h = -1;
            hueSlider.setValue(0);
            hueInput.setText(-1 + "");
            hueLabel.setText("Hue        [" + numberSpacer(-1,3 ) + "]:  ");
        }
    }
    public void setLum(int n){
        l = n;
        if (l > 255){
            l = 255;
        } else if (l < 0){
            l = 0;
        }
        lumSlider.setValue(l);
        lumInput.setText(l + "");
        lumLabel.setText("Luminance  [" + numberSpacer(l,3 ) + "]:  ");
    }
    public void setSat(int n){
        s = n;
        if (s > 100){
            s = 100;
        } else if (s < -100){
            s = -100;
        }
        satSlider.setValue(s);
        satInput.setText(s + "");
        satLabel.setText("Saturation [" + numberSpacer(s,3 ) + "]:  ");
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


    private static Color hexColor(String colorStr) {
        return new Color(
                Integer.valueOf( colorStr.substring( 1, 3 ), 16 ),
                Integer.valueOf( colorStr.substring( 3, 5 ), 16 ),
                Integer.valueOf( colorStr.substring( 5, 7 ), 16 ) );
    }


}
