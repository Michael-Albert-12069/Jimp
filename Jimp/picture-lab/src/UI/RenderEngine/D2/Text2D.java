package UI.RenderEngine.D2;

import java.awt.*;

public class Text2D extends Shape2D{
    //font-related instance variables
    String fontName = "";
    int fontStyle = Font.PLAIN;
    int fontSize = 10;

    //actual text-related instance variables
    String fontText = "";

    //Graphics-related instance variable(s)
    Point2D refPt = new Point2D(0,0);

    public Text2D(Point2D anchor, String text, int size){
        this.fontSize = size;
        this.fontText = text;
        this.refPt = anchor;
    }

    public void setFont(String fontName){
        this.fontName = fontName;
    }

    public void setBold(boolean tf){
        if (tf){
            fontStyle = Font.BOLD;
        } else {
            fontStyle = Font.PLAIN;
        }
    }

    public void setItalicized(boolean tf){
        if (tf){
            fontStyle = Font.ITALIC;
        } else {
            fontStyle = Font.PLAIN;
        }
    }

    @Override
    public void render(Graphics2D g2d, Color color) {
        if (fontName.length() <= 0) {
            fontName = g2d.getFont().getFontName();
        }
        g2d.setColor(color);
        g2d.setFont(new Font(fontName, fontStyle, fontSize));
        g2d.drawString(fontText, refPt.x, refPt.y);

    }
}
