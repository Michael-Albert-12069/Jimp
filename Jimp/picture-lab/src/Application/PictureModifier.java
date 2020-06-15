package Application;

import PicHandler.Picture;
import PicHandler.Pixel;

import java.awt.*;

public class PictureModifier {
    /**
     * Method to set the blue to 0
     */
    public Picture basePic;
    public PictureModifier(Picture picture){
        basePic = picture;
    }
    
    public void zeroBlue() {
        Pixel[][] pixels = basePic.getPixels2D();
        for (Pixel[] rowArray : pixels) {
            for (Pixel pixelObj : rowArray) {
                pixelObj.setBlue(0);
            }
        }
    }
    public void keepOnlyBlue() {
        Pixel[][] pixels = basePic.getPixels2D();
        for (Pixel[] rowArray : pixels) {
            for (Pixel pixelObj : rowArray) {
                pixelObj.setGreen(0);
                pixelObj.setRed(0);
            }
        }
    }
    public void negate() {
        Pixel[][] pixels = basePic.getPixels2D();
        for (Pixel[] rowArray : pixels) {
            for (Pixel pixelObj : rowArray) {
                pixelObj.setGreen(255 - pixelObj.getGreen());
                pixelObj.setRed(255 - pixelObj.getRed());
                pixelObj.setBlue(255 - pixelObj.getBlue());
            }
        }
    }
    public void grayScale() {
        Pixel[][] pixels = basePic.getPixels2D();
        for (Pixel[] rowArray : pixels) {
            for (Pixel pixelObj : rowArray) {
                int gray = (pixelObj.getGreen() + pixelObj.getRed() + pixelObj.getBlue())/3;
                pixelObj.setGreen(gray);
                pixelObj.setRed(gray);
                pixelObj.setBlue(gray);
            }
        }
    }
    public void fixUnderwater() {
        Pixel[][] pixels = basePic.getPixels2D();

        for (Pixel[] rowArray : pixels) {
            for (Pixel pixelObj : rowArray) {
                pixelObj.colorCorrect(new int[]{30, -100, -77});
                pixelObj.addContrast(2.5);
            }
        }
    }


    //    for (Pixel[] rowArray : pixels) {
    //    for (Pixel pixelObj : rowArray) {
    public void mirrorVerticalRightToLeft() {
        Pixel[][] pixels = basePic.getPixels2D();

        for (int i = 0; i < pixels.length; i++) {
            Pixel[] pixelRow = pixels[i];
            for (int j = 0; j < pixelRow.length/2; j++) {
                Pixel right = pixelRow[pixelRow.length - j - 1];
                int[] pRGB = right.getRGB();
                pixels[i][j].setRGB(pRGB);
            }
        }
    }
    public void mirrorArms(){
        mirrorOBJx(160, 105, 170, 190);//left
        mirrorOBJx(170, 240, 295, 195);//right // his arm be hangin' on by a thread
    }
    public void addBirb(){
        cloneStampFeather(232, 235, 325, 343, 222, 126, 4);//left

    }

    private void cloneStampFeather(int yi, int xi, int ymax, int xmax, int yf, int xf, int feather){
        Pixel[][] pixels = basePic.getPixels2D();
        Pixel src;
        Pixel dest;
        for(int y = yi; y < ymax; y ++){
            for(int x = xi; x < xmax; x ++){
                int deltaX = x - xi;
                int deltaY = y - yi;
                src = pixels[y][x];
                dest = pixels[(deltaY) + yf][(deltaX) + xf];

                if (deltaY < feather ||  y > (ymax + -feather) ) {
                    dest.setRGB(avgVals(dest.getRGB(), src.getRGB(), (2+deltaX/(xmax - xi))));
                }else if (deltaX < feather ||  x > (xmax + -feather)){
                    dest.setRGB(avgVals(dest.getRGB(), src.getRGB(), (2+deltaY/(ymax - yi))));

                } else {
                    dest.setRGB(src.getRGB());
                }
            }
        }
    }
    private void cloneStamp(int yi, int xi, int ymax, int xmax, int yf, int xf) {
        Pixel[][] pixels = basePic.getPixels2D();
        Pixel src;
        Pixel dest;
        for(int y = yi; y < ymax; y ++){
            for(int x = xi; x < xmax; x ++){
                src = pixels[y][x];
                dest = pixels[(y - yi) + yf][(x - xi) + xf];
                dest.setRGB(src.getRGB());
            }
        }
    }
    public int[] avgVals(int[] arr1, int[] arr2, int scale){
        int[] returned = new int[arr1.length];
        for (int i = 0; i < arr1.length; i++) {
            returned[i] = (arr1[i] + arr2[i])/(scale);
        }
        return returned;

    }




    private void mirrorOBJx(int yi, int xi, int xmax, int mid) {
        Pixel[][] pixels = basePic.getPixels2D();
        Pixel src;
        Pixel dest;
        for(int y = yi; y < mid; y ++){
            for(int x = xi; x < xmax; x ++){
                src = pixels[y][x];
                dest = pixels[(mid - y) + mid][x];
                dest.setColor(src.getColor());
            }
        }
    }
    public void chromaKeyBlue(Picture bckgrnd) {
        Pixel[][] replace = bckgrnd.getPixels2D();
        Pixel[][] pixels = basePic.getPixels2D();
        Pixel localsrc;
        Pixel extsrc;
        for(int y = 0; y < pixels.length; y ++){
            for(int x = 0; x < pixels[y].length; x ++){
                localsrc = pixels[y][x];
                extsrc = replace[y][x];
                int blue = localsrc.getBlue();
                int red = localsrc.getRed();
                int green = localsrc.getGreen();

                if (blue > red && blue > green){
                    localsrc.setColor(extsrc.getColor());
                }

            }
        }
    }


//    public void copySection(int startRow, int startCol,int endRow, int endCol, int w, int h) {
//        for (int i = 0; i < w; i++) {
//            for (int j = 0; j < ; j++) {
//
//            }
//        }
//    }

    public void mirrorHorizontal() {
        Pixel[][] pixels = basePic.getPixels2D();
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[i].length; j++) {
                Pixel pixel = pixels[i][j];
                int[] pRGB = pixel.getRGB();
                pixels[pixels.length - i - 1][j].setRGB(pRGB);
            }
        }
    }

    public void mirrorHorizontalBotToTop() {
        Pixel[][] pixels = basePic.getPixels2D();

        for (int i = pixels.length -1; i > 0; i--) {
            for (int j = 0; j < pixels[i].length; j++) {
                Pixel pixel = pixels[i][j];
                int[] pRGB = pixel.getRGB();
                pixels[pixels.length - i - 1][j].setRGB(pRGB);
            }
        }
    }
    public void mirrorSection(int midline){
        int mirrorPoint = 276;
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int count = 0;
        Pixel[][] pixels = basePic.getPixels2D();

        // loop through the rows
        for (int row = 27; row < 97; row++) {
            // loop from 13 to just before the mirror point
            for (int col = 13; col < mirrorPoint; col++) {

                leftPixel = pixels[row][col];
                rightPixel = pixels[row][mirrorPoint - col + mirrorPoint];
                rightPixel.setColor(leftPixel.getColor());
            }
        }
    }
    public void mirrorDiagonal() {
        Pixel[][] pixels = basePic.getPixels2D();
        int width = pixels[0].length;
        int height = pixels.length;
        if (width > height){
            width = height;
        } else if (height > width){
            height = width;
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {

                Pixel pixel = pixels[i][j];
                int[] pRGB = pixel.getRGB();
                pixels[j][i].setRGB(pRGB);


            }

        }
    }

    public static int average(int[] args){
        int sum = sum(args);
        return sum/(args.length);
    }
    public static int sum(int[] args){
        int sum = 0;
        for(int num : args){
            sum += num;
        }
        return sum;
    }

    /**
     * Method that mirrors the picture around a vertical mirror in the center of
     * the picture from left to right
     */
    public void mirrorVertical() {
        Pixel[][] pixels = basePic.getPixels2D();
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int width = pixels[0].length;
        for (int row = 0; row < pixels.length; row++) {
            for (int col = 0; col < width / 2; col++) {
                leftPixel = pixels[row][col];
                rightPixel = pixels[row][width - 1 - col];
                rightPixel.setColor(leftPixel.getColor());
            }
        }
    }

    /**
     * Mirror just part of a picture of a temple
     */
    public void mirrorTemple() {
        int mirrorPoint = 276;
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int count = 0;
        Pixel[][] pixels = basePic.getPixels2D();

        // loop through the rows
        for (int row = 27; row < 97; row++) {
            // loop from 13 to just before the mirror point
            for (int col = 13; col < mirrorPoint; col++) {

                leftPixel = pixels[row][col];
                rightPixel = pixels[row][mirrorPoint - col + mirrorPoint];
                rightPixel.setColor(leftPixel.getColor());
            }
        }
    }

    /**
     * copy from the passed fromPic to the specified startRow and startCol in
     * the current picture
     *
     * @param fromPic the picture to copy from
     * @param startRow the start row to copy to
     * @param startCol the start col to copy to
     */
    public void copy(Picture fromPic,
                     int startRow, int startCol) {
        Pixel fromPixel = null;
        Pixel toPixel = null;
        Pixel[][] toPixels = basePic.getPixels2D();
        Pixel[][] fromPixels = fromPic.getPixels2D();
        for (int fromRow = 0, toRow = startRow;
             fromRow < fromPixels.length
                     && toRow < toPixels.length;
             fromRow++, toRow++) {
            for (int fromCol = 0, toCol = startCol;
                 fromCol < fromPixels[0].length
                         && toCol < toPixels[0].length;
                 fromCol++, toCol++) {
                fromPixel = fromPixels[fromRow][fromCol];
                toPixel = toPixels[toRow][toCol];
                toPixel.setColor(fromPixel.getColor());
            }
        }
    }

    /**
     * Method to create a collage of several pictures
     */


    /**
     * Method to show large changes in color
     *
     * @param edgeDist the distance for finding edges
     */
    public void edgeDetection(int edgeDist) {
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        Pixel[][] pixels = basePic.getPixels2D();
        Color rightColor = null;
        for (int row = 0; row < pixels.length; row++) {
            for (int col = 0;
                 col < pixels[0].length - 1; col++) {
                leftPixel = pixels[row][col];
                rightPixel = pixels[row][col + 1];
                rightColor = rightPixel.getColor();
                if (leftPixel.colorDistance(rightColor)
                        > edgeDist) {
                    leftPixel.setColor(Color.BLACK);
                } else {
                    leftPixel.setColor(Color.WHITE);
                }
            }
        }
    }
}
