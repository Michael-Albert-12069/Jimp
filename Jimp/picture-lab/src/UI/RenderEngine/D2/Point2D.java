package UI.RenderEngine.D2;

import java.awt.*;

import UI.RenderEngine.Oscillator;
import UI.RenderEngine.Shape;

public class Point2D extends Shape {
    int x, y;

    public Point2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void render(Graphics2D g2d, Color color) {
        g2d.setColor(color);
        g2d.fillOval(x-2, y-2, 4, 4);
    }

    public void translateX(int pixels){
        this.x += pixels;
    }
    public void translateY(int pixels){
        this.y += pixels;
    }
    public Point lastPoint = new Point();
    public void rotateAroundPoint(Point2D center, int degrees){
        int curXAmp = this.x - center.x;
        int curYAmp = this.y - center.y;
        double curDegrees = Oscillator.getDegs(curYAmp, curXAmp);
        System.out.println(curDegrees);
        double maxXAmp =  Oscillator.maxAmp(curXAmp, (int) curDegrees);
        double maxYAmp =  Oscillator.maxAmp(curYAmp, (int) curDegrees);
        double maxAmp = (maxXAmp + maxYAmp)/2.0;
        System.out.println(maxAmp);

        this.x = (int) (center.x + Oscillator.oscillate(maxXAmp, degrees));
//        this.y = (int) (center.x + Oscillator.oscillate(maxYAmp, degrees));

    }

}
