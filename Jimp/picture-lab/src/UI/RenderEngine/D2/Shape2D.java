package UI.RenderEngine.D2;

import UI.RenderEngine.D2.Point2D;
import UI.RenderEngine.Shape;

import java.awt.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public abstract class Shape2D extends Shape {
    HashMap<Character, Point2D> shapePoints = new HashMap<>();

    public Shape2D(Point2D... points){
        int i = 65;
        for (Point2D point: points) {
            shapePoints.put(((char)i), point);
            i++;
        }
    }

    @Override
    public abstract void render(Graphics2D g2d, Color color);

    public void translateX(int pixels){
        Iterator<Map.Entry<Character, Point2D>> iterator = shapePoints.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<Character, Point2D> pair = iterator.next();
            Point2D currentPoint = pair.getValue();
            currentPoint.translateX(pixels);
        }
    }

    public void translateY(int pixels){
        Iterator<Map.Entry<Character, Point2D>> iterator = shapePoints.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<Character, Point2D> pair = iterator.next();
            Point2D currentPoint = pair.getValue();
            currentPoint.translateY(pixels);
        }
    }
}
