package UI.RenderEngine.D2;

import java.awt.*;

import UI.RenderEngine.D2.Point2D;

public class Triangle2D extends Shape2D{

    public Triangle2D(Point2D... points){
        super(points);
    }



    @Override
    public void render(Graphics2D g2d, Color color) {
        Point2D a = shapePoints.get('A');
        Point2D b = shapePoints.get('B');
        Point2D c = shapePoints.get('C');


        g2d.setColor(Color.BLUE);
        g2d.drawLine(a.x, a.y, b.x, b.y);
        g2d.drawLine(b.x, b.y, c.x, c.y);
        g2d.drawLine(c.x, c.y, a.x, a.y);


        Color pointCol = Color.RED;
        a.render(g2d, pointCol);
        b.render(g2d, pointCol);
        c.render(g2d, pointCol);



    }
}
