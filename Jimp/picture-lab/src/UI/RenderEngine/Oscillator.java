package UI.RenderEngine;


public class Oscillator {
    public static double oscillate(double maxAmp, int degrees){
        return Math.cos(Math.toRadians(degrees)) * maxAmp;
    }

    public static double maxAmp(int curAmp, int degrees){
        double scaler = 1.0/Math.cos(Math.toRadians(degrees));
        if (Math.round(degrees) == 0){
            return curAmp;
        }
        return Math.abs(curAmp * scaler);
    }

    public static double getDegs(double rise, double run){
        return Math.toDegrees(Math.atan(rise/run));
    }
}
