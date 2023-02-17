import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;


class Gui{
    JFrame frame;
    Gui(JFrame aFrame){
        frame = aFrame;
    }

    void splashScreen(){
        JLabel splash = new JLabel(new ImageIcon("logo_resized.jpg"));
        this.frame.add(splash);
        this.frame.setVisible(true);
        try
		{
			Thread.sleep(3000);
		} catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
        splash.setVisible(false);
        return;
    }

    void playerEntry(){
        GridBagConstraints gbc = new GridBagConstraints();

        //Creates start game button and sets constraints
        JButton startGame = new JButton("Start Game");
        gbc.gridx = 1; 
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5, 0, 5, 0);
        this.frame.add(startGame, gbc);
        
        //Creates red button and sets constraints; Also sets the button color to red
        JButton redButton = new JButton("Red Team"); 
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.insets = new Insets(0, 0, 10, 0);
        redButton.setBackground(new Color(210, 96, 96));
        this.frame.add(redButton, gbc);

        //Creates blue button and sets constraints; Also sets the button color to blue
        JButton blueButton= new JButton("Blue Team"); 
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.insets = new Insets(0, 0, 10, 0);
        blueButton.setBackground(new Color(79, 138, 196));
        this.frame.add(blueButton, gbc);

        //Creates textField where you can enter the player; sets all constraints for GUI
        JTextField textField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 10, 10, 10);
        this.frame.add(textField, gbc);

        //Creates textArea where the red team information is displayed; sets all constraints for GUI
        JTextArea textAreaR = new JTextArea(20,20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.CENTER;
        textAreaR.append("RED TEAM");
        textAreaR.setEditable(false);
        this.frame.add(textAreaR, gbc);

        //Creates textArea where the blue team information is displayed; sets all constraints for GUI
        JTextArea textAreaB = new JTextArea(20,20);
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.CENTER;
        textAreaB.append("BLUE TEAM");
        textAreaB.setEditable(false);
        this.frame.add(textAreaB, gbc);

        JTextArea errorMessage = new JTextArea(1, 1); 
        gbc.gridx = 1;
        gbc.gridy = 3; 
        gbc.gridheight = 1;
        gbc.gridwidth = 2;
        errorMessage.setBackground(new Color(110, 110, 110));
        gbc.fill = GridBagConstraints.CENTER;
        errorMessage.append("");
        errorMessage.setEditable(false);
        this.frame.add(errorMessage, gbc);

        //Makes everything in the frame visible
        this.frame.setVisible(true);

        redButton.addActionListener(new ActionListener(){
            // when RedButton is pressed, the text in the text box is recorded and moved to the Red team
            @Override
            public void actionPerformed(ActionEvent event) {
                // records text in aString
                String aString = textField.getText();

                //shortens the string if it's longer than 24 characters; also adds error message
                if (aString.length() > 24) {
                    aString = aString.substring(0, 24);
                    errorMessage.setText("Your input is too long. It has been shortened automatically.");
                } else {
                    errorMessage.setText("");
                }

                // moves text to Red team area if the string is not blank
                if (!aString.isBlank()) {
                    textAreaR.append("\n"+aString);
                }
                // empties the text box
                textField.setText(null);
            }
        });

        blueButton.addActionListener(new ActionListener(){
            // when BlueButton is pressed, the text in the text box is recorded and moved to the Blue team
            @Override
            public void actionPerformed(ActionEvent event) {
                // records text in aString
                String aString = textField.getText();

                //shortens the string if it's longer than 24 characters; also adds error message
                if (aString.length() > 24) {
                    aString = aString.substring(0, 24);
                    errorMessage.setText("Your input is too long. It has been shortened automatically.");
                } else {
                    errorMessage.setText("");
                }
            
                // moves text to Blue team area if the string is not blank
                if (!aString.isBlank()) {
                    textAreaB.append("\n"+aString);
                } 

                // empties the text box
                textField.setText(null);
            }
        });
        return;
    }

    void run(){
        this.splashScreen();
        this.playerEntry();
    }

}