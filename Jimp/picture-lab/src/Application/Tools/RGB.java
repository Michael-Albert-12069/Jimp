package Application.Tools;

import PicHandler.Picture;
import PicHandler.Pixel;

public class RGB {
    public static Picture rgb(Picture p, int r, int g, int b){
        double factorR = r/127.0;
        double factorG = g/127.0;
        double factorB = b/127.0;

        Picture out = new Picture(p.getHeight(), p.getWidth());
        out.copyPicture(p);

        Pixel[][] pixels = out.getPixels2D();
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[i].length; j++) {
                int red =  pixels[i][j].getRed();
                int green =  pixels[i][j].getGreen();
                int blue =  pixels[i][j].getBlue();



                red *= factorR;
                blue *= factorB;
                green *= factorG;


                pixels[i][j].setRed(red);
                pixels[i][j].setGreen(green);
                pixels[i][j].setBlue(blue);

            }
        }
        return out;
    }

}
