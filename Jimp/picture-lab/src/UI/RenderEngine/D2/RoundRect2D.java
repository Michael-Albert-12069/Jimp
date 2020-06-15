package UI.RenderEngine.D2;

import java.awt.*;

public class RoundRect2D extends Shape2D{
    boolean fill;

    int w, h;

    int radius;
    int diameter;

    Color color = Color.WHITE;

    public RoundRect2D(Point2D a, int w, int h, int radius){
        Point2D b = new Point2D(a.x + w, a.y);
        Point2D c = new Point2D(a.x + w, a.y + h);
        Point2D d = new Point2D(a.x, a.y + h);
        this.radius = radius;
        this.diameter = radius * 2;

        this.w = w;
        this.h = h;
        shapePoints.put('A', a);
        shapePoints.put('B', b);
        shapePoints.put('C', c);
        shapePoints.put('D', d);
    }
    public RoundRect2D(Point2D a, int w, int h, int radius, Color color){
        Point2D b = new Point2D(a.x + w, a.y);
        Point2D c = new Point2D(a.x + w, a.y + h);
        Point2D d = new Point2D(a.x, a.y + h);
        this.radius = radius;
        this.diameter = radius * 2;

        this.color = color;

        this.w = w;
        this.h = h;
        shapePoints.put('A', a);
        shapePoints.put('B', b);
        shapePoints.put('C', c);
        shapePoints.put('D', d);
    }


    public void fill(boolean tf){
        fill = tf;
    }

    @Override
    public void render(Graphics2D g2d, Color color) {
        color = this.color;
        Point2D a = shapePoints.get('A');
        Point2D b = shapePoints.get('B');
        Point2D c = shapePoints.get('C');
        Point2D d = shapePoints.get('D');

        //border circles
        g2d.setColor(Color.WHITE);
        g2d.fillOval(a.x, a.y, diameter, diameter); // top left
        g2d.fillOval(b.x - diameter, b.y, diameter, diameter); //top right
        g2d.fillOval(c.x- diameter, c.y - diameter, diameter, diameter); // bottom right
        g2d.fillOval(d.x , d.y - diameter, diameter, diameter); //bottom left

        //filler rectangles
        g2d.setColor(Color.WHITE);
        g2d.fillRect(a.x + radius, a.y, w - diameter, h);//vertical center rectangle
        g2d.fillRect(a.x , a.y+ radius, w, h - diameter);//horizontal center rectangle




//        Color pointCol = Color.RED;
//        a.render(g2d, pointCol);
//        b.render(g2d, pointCol);
//        c.render(g2d, pointCol);
//        d.render(g2d, pointCol);



    }
}
