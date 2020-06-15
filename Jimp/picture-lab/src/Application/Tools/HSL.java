package Application.Tools;

import PicHandler.Picture;
import PicHandler.Pixel;

public class HSL {
    /**
     *
     * @param p is the picture you want to modify
     * @param factor is a number between -1, and 1
     */
    public static void saturate(Picture p, double factor){
        Pixel[][] pixels = p.getPixels2D();
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[i].length; j++) {
                short avg = (short) average(pixels[i][j].getRGB());
                int red =  pixels[i][j].getRed();
//                System.out.println(red);
                if (red > avg){
                    red = (int) (((red - avg) * factor) + red);
                } else if (red < avg){
                    red = (int) (red - ((avg - red) * factor));
                } else {
                    //do nothing
                }

                int blue =  pixels[i][j].getBlue();
                if (blue > avg){
                    blue = (int) (((blue - avg) * factor) + blue);
                } else if (blue < avg){
                    blue = (int) (blue - ((avg - blue) * factor));
                } else {
                    //do nothing
                }

                int green =  pixels[i][j].getGreen();
                if (green > avg){
                    green = (int) (((green - avg) * factor) + green);
                } else if (green < avg){
                    green = (int) (green - ((avg - green) * factor));
                } else {
                    //do nothing
                }

                pixels[i][j].setRed(red);
                pixels[i][j].setBlue(blue);
                pixels[i][j].setGreen(green);

            }
        }
    }

    public static void hue(Picture p, int degree){
        System.out.println("R: " + hueR(degree) + "; G: " + hueG(degree) + "; B: " + hueB(degree));
        Pixel[][] pixels = p.getPixels2D();
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[i].length; j++) {
                int red =  pixels[i][j].getRed();
                int green =  pixels[i][j].getGreen();
                int blue =  pixels[i][j].getBlue();

                red = degree - red;
                green = degree - green;
                blue = degree - blue;

                pixels[i][j].setRed(red);
                pixels[i][j].setBlue(blue);
                pixels[i][j].setGreen(green);

            }
        }
    }

    public static void colorize(Picture p, int degree){
        System.out.println("R: " + hueR(degree) + "; G: " + hueG(degree) + "; B: " + hueB(degree));
        Pixel[][] pixels = p.getPixels2D();
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[i].length; j++) {
                int red =  pixels[i][j].getRed();
                int green =  pixels[i][j].getGreen();
                int blue =  pixels[i][j].getBlue();

                int min = Math.min(blue, Math.min(red, green));
                int max = Math.max(blue, Math.max(red, green));
                int diff = max - min;

                red = (int) (min + (diff * hueR(degree)));

                green = (int) (min + (diff * hueG(degree)));

                blue = (int) (min + (diff * hueB(degree)));


                pixels[i][j].setRed(red);
                pixels[i][j].setBlue(blue);
                pixels[i][j].setGreen(green);

            }
        }
    }

    private static double hueB(int degree){
        if (degree >= 0 && degree <=80){
            return 0;
        }


        if (degree > 80 && degree < 120){ // up slope
            return (degree - 80) / 40.0;
        } else if (degree >200 && degree < 240){ // down slope
            return Math.abs(240 - degree)/40.0;
        }


        else {
            return 1; // hump
        }
    }
    private static double hueG(int degree){
        if (degree >= 160 && degree <=240){
            return 0;
        }


        if (degree > 0 && degree < 40){ // up slope
            return (degree - 0) / 40.0;
        } else if (degree >120 && degree < 160){ // down slope
            return Math.abs(160 - degree)/40.0;
        }


        else {
            return 1; // hump
        }
    }
    private static double hueR(int degree){
        if (degree >= 0 && degree <=40){
            return 1;
        }
        if (degree >= 200 && degree <=240){
            return 1;
        }



        if (degree > 40 && degree < 80){
            return Math.abs(80 - degree)/40.0;
        } else if (degree >160 && degree < 200){
            return (degree - 160) / 40.0;

        }
        else {
            return 0;
        }
    }

    private static int average(int[] args){
        int sum = sum(args);
        return sum/(args.length);
    }
    private static int sum(int[] args){
        int sum = 0;
        for(int num : args){
            sum += num;
        }
        return sum;
    }
}
class PixelHSL{


    public static int getHue(int r, int g, int b){
        int min = Math.min(b, Math.min(r, g));
        int max = Math.max(b, Math.max(r, g));
        double diff = max - min;
        double red = (r - min)/diff;
        double green = (g - min)/diff;
        double blue = (b - min)/diff;
        System.out.println("R: " +red + "; G: " + green + "; B: " + blue);
        for (int i = 0; i < 240; i++) {
            double similarity = 0.015;
            if ((hueR(i) < red + similarity && (hueR(i) > red - similarity) && (hueG(i) < green + similarity && (hueG(i) > green - similarity) && (hueB(i) < blue + similarity && (hueB(i) > blue - similarity))))){
                return i;
            }
        }
        return 0;
    }
    public static int getLum(int r,int g,int b){
        int max = Math.max(b, Math.max(r, g));
        return (int) ((max / 255.0) * 120);

    }
    public static int getSat(int r,int g,int b){
        int max = Math.max(b, Math.max(r, g));
        return (int) ((max / 255.0) * 120);

    }


    private static double hueB(double degree){
        if (degree >= 0 && degree <=80){
            return 0;
        }


        if (degree > 80 && degree < 120){ // up slope
            return (degree - 80) / 40.0;
        } else if (degree >200 && degree < 240){ // down slope
            return Math.abs(240 - degree)/40.0;
        }


        else {
            return 1; // hump
        }
    }
    private static double hueG(double degree){
        if (degree >= 160 && degree <=240){
            return 0;
        }


        if (degree > 0 && degree < 40){ // up slope
            return (degree - 0) / 40.0;
        } else if (degree >120 && degree < 160){ // down slope
            return Math.abs(160 - degree)/40.0;
        }


        else {
            return 1; // hump
        }
    }
    private static double hueR(double degree){
        if (degree >= 0 && degree <=40){
            return 1;
        }
        if (degree >= 200 && degree <=240){
            return 1;
        }



        if (degree > 40 && degree < 80){
            return Math.abs(80 - degree)/40.0;
        } else if (degree >160 && degree < 200){
            return (degree - 160) / 40.0;

        }
        else {
            return 0;
        }
    }
}
