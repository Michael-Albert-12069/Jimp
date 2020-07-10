package UI.Panels;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public abstract class Tool {

    public String toolName;
    public int value;
    public Panel homePanel;

    JLabel toolLabel;
    JTextField toolTextInput;
    JSlider toolSlider;


    public Tool(Panel panel, String toolName){
        this.toolName = toolName;
        this.homePanel = panel;
    }

    public void init(int min, int max, int startVal){
        toolLabel = new JLabel();
        toolLabel.setForeground(homePanel.FG);
        toolLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
        toolTextInput = new JTextField(value + "");
        toolTextInput.setForeground(homePanel.FG);
        toolTextInput.setBackground(homePanel.BG);


        toolSlider = new JSlider(min, max, startVal);
        toolSlider.setBackground(homePanel.BG);
        toolSlider.setForeground(homePanel.FG);


        // paint the ticks and tarcks
        toolSlider.setPaintTrack(true);
        toolSlider.setPaintTicks(true);
        toolSlider.setPaintLabels(true);
        // set spacing
        toolSlider.setMajorTickSpacing((max-min)/3);
        toolSlider.setMinorTickSpacing((max-min)/10);

        toolSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                value = toolSlider.getValue();
//                System.out.println(b);
                toolLabel.setText(toolName + "\t[" + numberSpacer(value, 3) + "]:  ");
                toolTextInput.setText(value + "");
                JSlider source = (JSlider) e.getSource();
                if (!source.getValueIsAdjusting()) {
                    onToolChange();
                }

            }
        });

        toolLabel.setText(toolName + "\t[" + numberSpacer(toolSlider.getValue(),3 ) + "]: ");
        homePanel.add(toolLabel);
        homePanel.add(toolTextInput);
        homePanel.add(toolSlider);
    }


    public abstract void onToolChange();

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
