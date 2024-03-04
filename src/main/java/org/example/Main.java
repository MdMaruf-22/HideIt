package org.example;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main extends JFrame {
    private JButton selectImageButton;
    private JButton encodeButton;
    private JButton decodeButton;
    private JButton helpButton;
    private JLabel imageLabel;
    private String selectedImagePath;

    public Main() {
        setTitle("Steganography Tool");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 4));

        selectImageButton = new JButton("Select Image");
        encodeButton = new JButton("Encode");
        decodeButton = new JButton("Decode");
        helpButton = new JButton("Help");
        encodeButton.setEnabled(false);
        decodeButton.setEnabled(false);

        selectImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectImage();
            }
        });

        encodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                encodeMessage();
            }
        });

        decodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                decodeMessage();
            }
        });
        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                helpScreen();
            }
        });
        buttonPanel.add(selectImageButton);
        buttonPanel.add(encodeButton);
        buttonPanel.add(decodeButton);
        buttonPanel.add(helpButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        imageLabel = new JLabel();
        panel.add(imageLabel, BorderLayout.CENTER);

        add(panel);
        setVisible(true);
    }

    private void helpScreen() {
        JOptionPane.showMessageDialog(this, "Help Information:\n\n" +
                "1. Select a PNG image using the 'Select Image' button.\n" +
                "2. Encode a message by clicking the 'Encode' button. Enter the message to encrypt.\n" +
                "3. Decode an image by clicking the 'Decode' button. (The selected image must be encrypted beforehand.)");
    }


    private void selectImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose Image File");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png", "bmp", "gif");
        fileChooser.setFileFilter(filter);

        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            selectedImagePath = selectedFile.getAbsolutePath();

            try {
                BufferedImage image = ImageIO.read(new File(selectedImagePath));
                displayImage(image);
                encodeButton.setEnabled(true);
                decodeButton.setEnabled(true);
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error occurred while loading the image.");
            }
        }
    }

    private void encodeMessage() {
        if (selectedImagePath != null && !selectedImagePath.isEmpty()) {
            String message = JOptionPane.showInputDialog(this, "Enter the message to encode:");
            SteganographyApp.encodeAndSave(selectedImagePath, message);
            JOptionPane.showMessageDialog(this,"Message encoded successfully.");
        } else {
            JOptionPane.showMessageDialog(this, "Please select an image first.");
        }
    }
    private void decodeMessage() {
        if (selectedImagePath != null && !selectedImagePath.isEmpty()) {
            String messgae = SteganographyApp.decodeAndPrint(selectedImagePath);
            if (messgae != null) {
                JOptionPane.showMessageDialog(this, "Decoded message: " + messgae);
            } else {
                JOptionPane.showMessageDialog(this, "No message found in the image.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select an image first.");
        }
    }

    private void displayImage(BufferedImage image) {
        ImageIcon icon = new ImageIcon(image);
        imageLabel.setIcon(icon);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Main();
            }
        });
    }
}
