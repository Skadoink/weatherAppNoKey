package weather.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {
    public static void main(String[] args) throws Exception{
        JFrame mainFrame = new JFrame("Weather App"); //make window
        mainFrame.setLayout(new GridBagLayout());
        mainFrame.setLocationRelativeTo(null);  
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);

        GridBagConstraints gbc = new GridBagConstraints();

        JLabel locationLabel = new JLabel("Location:");
        gbc.weighty = 0;
        gbc.weightx = 0; //0 so the text field takes the free space
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        mainFrame.add(locationLabel, gbc);

        JTextField inputField = new JTextField();
        inputField.setPreferredSize(new Dimension(200, 20));
        gbc.fill = GridBagConstraints.HORIZONTAL; //don't want text field to be taller.
        gbc.weighty = 0;
        gbc.weightx = 0.5;
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        mainFrame.add(inputField, gbc);

        //Have to have this above the search button so the dropDown can be found
        String[] testStrings = {"test 1", "test 2", "test 3"};
        JComboBox<String> dropDown = new JComboBox<>(testStrings); 
        dropDown.setPreferredSize(new Dimension(200, 20));
        gbc.fill = GridBagConstraints.HORIZONTAL; //don't want text field to be taller.
        gbc.weighty = 0;
        gbc.weightx = 0.5;
        gbc.gridx = 1;
        gbc.gridy = 1;
        mainFrame.add(dropDown, gbc);

        JButton searchButton = new JButton("Search");
        searchButton.setPreferredSize(new Dimension(80, 20));
        gbc.weighty = 0;
        gbc.weightx = 0;
        gbc.gridx = 2;
        gbc.gridy = 0;
        mainFrame.add(searchButton, gbc);
        searchButton.addActionListener((new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                weatherApp getArray = new weatherApp();
                String[] locationArray = getArray.getLocations(inputField.getText());
                dropDown.setModel(new DefaultComboBoxModel<>(locationArray));
            }
        }));

        JLabel chooseLabel = new JLabel("Choose:");
        gbc.weighty = 0;
        gbc.weightx = 0; //0 so the text field takes the free space
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 5, 5, 5);
        mainFrame.add(chooseLabel, gbc);

        JButton goButton = new JButton("Go");
        goButton.setPreferredSize(new Dimension(80, 20));
        gbc.weighty = 0;
        gbc.weightx = 0;
        gbc.gridx = 2;
        gbc.gridy = 1;
        mainFrame.add(goButton, gbc);

        JLabel fillerTestLabel = new JLabel("This is a test to fill space");
        gbc.weighty = 1; 
        gbc.gridx = 0; 
        gbc.gridy = 2; 
        gbc.gridwidth = 3; //so it doesnt make grid 1 wide
        mainFrame.add(fillerTestLabel, gbc);

        mainFrame.pack();
    }
}
