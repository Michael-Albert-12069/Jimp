package UI.RenderEngine.D2;

import java.awt.*;

public class Square2D extends Shape2D{
    boolean fill;

    int w, h;

    public Square2D(Point2D a, int w, int h){
        Point2D b = new Point2D(a.x + w, a.y);
        Point2D c = new Point2D(a.x + w, a.y + h);
        Point2D d = new Point2D(a.x, a.y + h);

        this.w = w;
        this.h = h;
        shapePoints.put('A', a);
        shapePoints.put('B', b);
        shapePoints.put('C', c);
        shapePoints.put('D', d);

    }

    public Square2D(Point2D ... points){
        super(points);
    }

    public void fill(boolean tf){
        fill = tf;
    }

    @Override
    public void render(Graphics2D g2d, Color color) {
        Point2D a = shapePoints.get('A');
        Point2D b = shapePoints.get('B');
        Point2D c = shapePoints.get('C');
        Point2D d = shapePoints.get('D');



        g2d.setColor(Color.LIGHT_GRAY);
        g2d.drawLine(a.x, a.y, b.x, b.y);
        g2d.drawLine(b.x, b.y, c.x, c.y);
        g2d.drawLine(c.x, c.y, d.x, d.y);
        g2d.drawLine(d.x, d.y, a.x, a.y);

        g2d.fillRect(a.x, a.y, w, h);


//        Color pointCol = Color.RED;
//        a.render(g2d, pointCol);
//        b.render(g2d, pointCol);
//        c.render(g2d, pointCol);
//        d.render(g2d, pointCol);



    }
}
