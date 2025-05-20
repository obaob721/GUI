/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author PATRICIA
 */
public class ImageHandler {
    
    private static String currentImagePath = "";

    public static void chooseImage(JLabel imageLabel) {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            try {
                File selectedFile = fileChooser.getSelectedFile();
                String destination = "src/usersImages/" + selectedFile.getName();
                
                if (fileExists(destination)) {
                    JOptionPane.showMessageDialog(null, "File already exists! Rename or choose another.");
                } else {
                    Files.copy(selectedFile.toPath(), new File(destination).toPath(), StandardCopyOption.REPLACE_EXISTING);
                    imageLabel.setIcon(resizeImage(destination, imageLabel));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static boolean fileExists(String path) {
        return Files.exists(Paths.get(path));
    }

    public static ImageIcon resizeImage(String imagePath, JLabel label) {
        try {
            ImageIcon imageIcon = new ImageIcon(imagePath);
            Image img = imageIcon.getImage();
            int width = label.getWidth();
            int height = getHeightFromWidth(imagePath, width);
            Image newImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(newImg);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int getHeightFromWidth(String imagePath, int desiredWidth) {
        try {
            BufferedImage image = ImageIO.read(new File(imagePath));
            int originalWidth = image.getWidth();
            int originalHeight = image.getHeight();
            return (int) ((double) desiredWidth / originalWidth * originalHeight);
        } catch (IOException ex) {
            ex.printStackTrace();
            return -1;
        }
    }
}
