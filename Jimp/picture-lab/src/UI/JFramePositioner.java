package UI;

import javax.swing.*;
import java.awt.*;

public class JFramePositioner {
    private static Rectangle getMaxWindowBounds(JFrame frame) {
        GraphicsConfiguration config = frame.getGraphicsConfiguration();
        Rectangle bounds = config.getBounds();
        Insets insets = Toolkit.getDefaultToolkit().getScreenInsets(config);
        bounds.x += insets.left;
        bounds.y += insets.top;
        bounds.width -= insets.left + insets.right;
        bounds.height -= insets.top + insets.bottom;
        return bounds;
    }
    public static void setLocationToTop(JFrame frame) {
        frame.setLocation(frame.getX(), getMaxWindowBounds(frame).y);
    }

    public static void setLocationToLeft(JFrame frame) {
        frame.setLocation(getMaxWindowBounds(frame).x, frame.getY());
    }

    public static void setLocationToBottom(JFrame frame) {
        Rectangle bounds = getMaxWindowBounds(frame);
        frame.setLocation(frame.getX(), bounds.y + bounds.height - frame.getHeight());
    }

    public static void setLocationToRight(JFrame frame) {
        Rectangle bounds = getMaxWindowBounds(frame);
        frame.setLocation(bounds.x + bounds.width - frame.getWidth(), frame.getY());
    }

    public static void setLocationToTopLeft(JFrame frame){
        setLocationToTop(frame);
        setLocationToLeft(frame);
    }
    public static void setLocationToTopRight(JFrame frame){
        setLocationToTop(frame);
        setLocationToRight(frame);


    }
    public static void setLocationToBottomLeft(JFrame frame){
        setLocationToBottom(frame);
        setLocationToLeft(frame);
    }
    public static void setLocationToBottomRight(JFrame frame){
        setLocationToBottom(frame);
        setLocationToRight(frame);

    }

    public static void setLocationTo(JFrame frame, int x, int y){
        frame.setLocation(x, y);
    }
    public static void setYTo(JFrame frame, int y){
        frame.setLocation(frame.getX(), y);
    }

}
