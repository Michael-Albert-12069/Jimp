package Application.Tools;

import PicHandler.Picture;
import PicHandler.Pixel;

public class Transform {
    public static Picture simpleTransform(Picture p, int factor){
        Picture out = new Picture(p.getHeight() / factor, p.getWidth()/ factor);


        Pixel[][] srcPic = p.getPixels2D();
        Pixel[][] newPic = out.getPixels2D();
        for (int srcI = 0; srcI < srcPic.length; srcI+= factor) {
            for (int srcJ = 0; srcJ < srcPic[srcI].length; srcJ+= factor) {
                int destI = srcI / factor;
                int destJ = srcJ / factor;
                newPic[destI][destJ].setRGB(srcPic[srcI][srcJ].getRGB());
            }
        }

        return out;
    }
}
