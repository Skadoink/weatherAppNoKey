package weather.app;

import javax.swing.*;
import java.awt.*;

public class GUI {
    public static void main(String[] args) throws Exception{
        JFrame mainFrame = new JFrame("Weather App"); //make window
        mainFrame.setLayout(new GridBagLayout());
        mainFrame.setLocationRelativeTo(null);  
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);

        GridBagConstraints gbc = new GridBagConstraints();

        JLabel locationLabel = new JLabel("Location:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainFrame.add(locationLabel, gbc);

        JTextField inputField = new JTextField();
        inputField.setPreferredSize(new Dimension(200, 20));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        mainFrame.add(inputField, gbc);

        mainFrame.pack();
    }
}
