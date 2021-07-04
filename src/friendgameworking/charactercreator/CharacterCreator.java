/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package friendgameworking.charactercreator;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;

/**
 * Singleton class to handle character creation
 * @author julian
 */
public class CharacterCreator {
    
    
    private static CharacterCreator single_instance = null;
    private static final int IMAGE_WIDTH = 384;
    private static final int IMAGE_HEIGHT = 192;
    private static Random rand = new Random();
    private static BufferedImage characterImage;
    
    // paths to resources used by character creator
    static File pathOtherTop = new File("src/assets/charactergenerator/othertop");
    static File pathHats = new File("src/assets/charactergenerator/hats");
    static File pathHair = new File("src/assets/charactergenerator/hair");
    static File pathEyebrows = new File("src/assets/charactergenerator/eyebrows");
    static File pathEyes = new File("src/assets/charactergenerator/eyes");
    static File pathNoses = new File("src/assets/charactergenerator/noses");
    static File pathMouths = new File("src/assets/charactergenerator/mouths");
    static File pathClothes = new File("src/assets/charactergenerator/clothes");
    static File pathOtherBottom = new File("src/assets/charactergenerator/otherbottom");
    static File pathExpressions = new File("src/assets/charactergenerator/expressions");
    static File pathSkin = new File("src/assets/charactergenerator/skin");
    
    // export path of character images
    static File pathExport = new File("src/data/characters");

    // arrays of each type of resource
    static ArrayList<BufferedImage> othertop = new ArrayList<>();
    static ArrayList<BufferedImage> hats = new ArrayList<>();
    static ArrayList<BufferedImage> hair = new ArrayList<>();
    static ArrayList<BufferedImage> eyebrows = new ArrayList<>();
    static ArrayList<BufferedImage> eyes = new ArrayList<>();
    static ArrayList<BufferedImage> noses = new ArrayList<>();
    static ArrayList<BufferedImage> mouths = new ArrayList<>();
    static ArrayList<BufferedImage> clothes = new ArrayList<>();
    static ArrayList<BufferedImage> otherbottom = new ArrayList<>();
    static ArrayList<BufferedImage> expressions = new ArrayList<>();
    static ArrayList<BufferedImage> skin = new ArrayList<>();
    
    // Stuff to store current character data
    public static Characteristic cOtherTop;
    public static Characteristic cHat;
    public static Characteristic cHair;
    public static Characteristic cEyebrows;
    public static Characteristic cEyes;
    public static Characteristic cNose;
    public static Characteristic cMouth;
    public static Characteristic cClothes;
    public static Characteristic cOtherBottom;
    public static Characteristic cExpression;
    public static Characteristic cSkin;
    public static int personalityEI = 50;
    public static int personalityNS = 50;
    public static int personalityFT = 50;
    public static int personalityPJ = 50;
    
    
    // acceptable filetypes
    static final String[] EXTENSIONS = new String[] {
        "png"
    };
    
    // filter to identify images based on their extensions
    static final FilenameFilter IMAGE_FILTER = new FilenameFilter() {
        @Override
        public boolean accept(final File dir, final String name) {
            for (final String ext : EXTENSIONS) {
                if (name.endsWith("." + ext)) {
                    return (true);
                }
            }
            return (false);
        }
    };
    

    
    private CharacterCreator() throws IOException {
        
        // use the loadImages method to load in all of the assets
        cOtherTop = new Characteristic(loadImages(othertop, pathOtherTop));
        cHat = new Characteristic(loadImages(hats, pathHats));
        cHair = new Characteristic(loadImages(hair, pathHair));
        cHair.setColor(new Color(181, 101, 29));
        cEyebrows = new Characteristic(loadImages(eyebrows, pathEyebrows));
        cEyebrows.setColor(new Color(181, 101, 29));
        cEyes = new Characteristic(loadImages(eyes, pathEyes));
        cNose = new Characteristic(loadImages(noses, pathNoses));
        cMouth = new Characteristic(loadImages(mouths, pathMouths));
        cClothes = new Characteristic(loadImages(clothes, pathClothes));
        cClothes.setColor(Color.WHITE);
        cOtherBottom = new Characteristic(loadImages(otherbottom, pathOtherBottom));
        cExpression = new Characteristic(loadImages(expressions, pathExpressions));
        cSkin = new Characteristic(loadImages(skin, pathSkin));
        
    }
    
    public static void generateCharacter(boolean save) throws IOException {
        BufferedImage otherTopImage = othertop.get(cOtherTop.getId());
        BufferedImage hatImage = hats.get(cHat.getId());
        BufferedImage hairImage = copyImage(hair.get(cHair.getId()));
        hairImage = tint(hairImage, cHair.getColor());
        BufferedImage eyebrowsImage = copyImage(eyebrows.get(cEyebrows.getId()));
        eyebrowsImage = tint(eyebrowsImage, cEyebrows.getColor());
        BufferedImage eyeImage = eyes.get(cEyes.getId());
        BufferedImage noseImage = noses.get(cNose.getId());
        BufferedImage mouthImage = mouths.get(cMouth.getId());
        BufferedImage clothesImage = copyImage(clothes.get(cClothes.getId()));
        clothesImage = tint(clothesImage, cClothes.getColor());
        BufferedImage otherBottomImage = otherbottom.get(cOtherBottom.getId());
        BufferedImage expressionImage = expressions.get(cExpression.getId());
        BufferedImage skinImage = skin.get(cSkin.getId());
        BufferedImage combined = new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        Graphics g = combined.getGraphics();

        g.drawImage(skinImage, 0, 0, null);
        g.drawImage(expressionImage, 0, 0, null);
        g.drawImage(otherBottomImage, 0, 0, null);
        g.drawImage(clothesImage, 0, 0, null);
        g.drawImage(mouthImage, 6, 20, null);
        g.drawImage(noseImage, 12, 0, null);
        g.drawImage(eyeImage, 9, -6, null);
        g.drawImage(eyebrowsImage, 0, 0, null);
        g.drawImage(hairImage, 0, 0, null);
        g.drawImage(hatImage, 0, 0, null);
        g.drawImage(otherTopImage, 0, 0, null);
        g.dispose();
        
        characterImage = combined;
        if (save) {
            ImageIO.write(combined, "PNG", new File(pathExport, rand.nextInt(10000)+".png"));
        }
    }
    
    public static CharacterCreator getInstance() throws IOException {
        if (single_instance == null) {
            single_instance = new CharacterCreator();
        }
        return single_instance;
    }
    
    public static BufferedImage getImage() {
        return characterImage;
    }
    
    private static BufferedImage copyImage(BufferedImage source) {
        BufferedImage b = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
        Graphics g = b.getGraphics();
        g.drawImage(source, 0, 0, null);
        g.dispose();
        return b;
    }
    
    private static int loadImages(ArrayList<BufferedImage> arrayList, File dir) {
        int currentIndex = 0;
        if (dir.isDirectory()) { // make sure it's a directory
            for (final File f : dir.listFiles(IMAGE_FILTER)) {
                BufferedImage img = null;
                try {
                    img = ImageIO.read(f);
                    arrayList.add(img);
                } catch (final IOException e) {
                    // handle errors here
                }
            }
        }
        System.out.println(dir +": "+arrayList.size());
        return arrayList.size();
    }
    
    public static String calculatePersonality() {
        String personalityType = "";
        if (personalityEI < 50) {
            personalityType += "I";
        } else {
            personalityType += "E";
        }
        if (personalityNS < 50) {
            personalityType += "N";
        } else {
            personalityType += "S";
        }
        if (personalityFT < 50) {
            personalityType += "F";
        } else {
            personalityType += "T";
        }
        if (personalityPJ > 50) {
            personalityType += "P";
        } else {
            personalityType += "J";
        }
        return personalityType;
    }
    
    
    public static BufferedImage tint(BufferedImage image, Color color) {
        BufferedImage output = image;
        for (int x = 0; x < output.getWidth(); x++) {
            for (int y = 0; y < output.getHeight(); y++) {
                Color pixelColor = new Color(output.getRGB(x, y), true);
                int r = (pixelColor.getRed() + color.getRed()) / 2;
                int g = (pixelColor.getGreen() + color.getGreen()) / 2;
                int b = (pixelColor.getBlue() + color.getBlue()) / 2;
                int a = pixelColor.getAlpha();
                int rgba = (a << 24) | (r << 16) | (g << 8) | b;
                output.setRGB(x, y, rgba);
            }
    }
    return output;
}
    
}
