package bitmap.transformer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Bitmap {

    private String inputFile;
    private String outputFile;
    private String transformation;
    private String result;
    public Bitmap (String inputFile, String outputFile, String transformation) {
        this.inputFile = inputFile + ".bmp";
        this.outputFile = outputFile + ".bmp";
        this.transformation = transformation;
        if (transformation.equals("Black and White")){
            result = BWImage();
        }else if (transformation.equals("Border")){
            result = addBorder();
        }else if (transformation.equals("Rotate")){
            result = RotateImage();
        }else {
            System.out.println("The method that you entered doesn't exist please enter either !!!!!!!! Black and White , Border , Rotate !!!!!!! ");
        }

    }

    public String getResult() {
        return result;
    }

    public String BWImage () {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            String path = classLoader.getResource(inputFile).getPath();

            BufferedImage image = ImageIO.read(new File(path));
            BufferedImage result = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_BINARY);

            Graphics2D graphics = result.createGraphics();
            graphics.drawImage(image, 0, 0, Color.WHITE, null);
            graphics.dispose();

            File output = new File (outputFile);
            ImageIO.write(result, "bmp", output);
            return "Black and White";

        } catch (IOException e){
            e.printStackTrace();
            return "";
        }
    }

    public String RotateImage () {

        try {
            ClassLoader classLoader = getClass().getClassLoader();
            String path = classLoader.getResource(inputFile).getPath();

            BufferedImage image = ImageIO.read(new File(path));
            BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());

            Graphics2D graphics = newImage.createGraphics();
            graphics.rotate(Math.toRadians(180), image.getWidth() / 2, image.getHeight() / 2);
            graphics.drawImage(image, 0, 0, null);

            File output = new File(outputFile);
            ImageIO.write(newImage, "bmp", output);
            return "Rotate";

        }catch (IOException e){
            e.printStackTrace();
            return "";
        }

    }

    public String addBorder () {

        try {
            ClassLoader classLoader = getClass().getClassLoader();
            String path = classLoader.getResource(inputFile).getPath();

            BufferedImage image = ImageIO.read(new File(path));
            BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());

            Graphics2D graphics2D = (Graphics2D) image.getGraphics();
            graphics2D.setStroke(new BasicStroke(5));
            graphics2D.setColor(Color.blue);
            graphics2D.drawRect(0, 0, image.getWidth() , image.getHeight() );

            ImageIO.write(image, "bmp", new File(outputFile));

            return "Border";

        }catch (IOException e){
            e.printStackTrace();
            return "";
        }

    }


}
