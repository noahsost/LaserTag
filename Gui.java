import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


class Gui{
    JFrame frame;
    Database database;
    Color BACKGROUND = new Color(200, 200, 200);
    int interval = 30;
    Timer timer;

    Gui(JFrame aFrame, Database aDatabase){
        frame = aFrame;
        database = aDatabase;
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
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 0, 5, 0);
        this.frame.add(startGame, gbc);
        
        //Creates red button and sets constraints; Also sets the button color to red
        JButton redButton = new JButton("Red Team"); 
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.insets = new Insets(0, 0, 10, 0);
        redButton.setBackground(new Color(210, 96, 96));
        this.frame.add(redButton, gbc);

        //Creates blue button and sets constraints; Also sets the button color to blue
        JButton blueButton= new JButton("Blue Team"); 
        gbc.gridx = 3;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.insets = new Insets(0, 0, 10, 0);
        blueButton.setBackground(new Color(79, 138, 196));
        this.frame.add(blueButton, gbc);

        JTextArea enterID = new JTextArea(1, 1);
        enterID.setText("Enter an existing ID and pick a team: ");
        gbc.gridx = 1;
        gbc.gridy = 0; 
        gbc.gridheight = 1;
        gbc.gridwidth = 2;
        enterID.setBackground(BACKGROUND);
        gbc.fill = GridBagConstraints.CENTER;
        enterID.setEditable(false);
        this.frame.add(enterID, gbc);

        //Creates textField where you can enter the player; sets all constraints for GUI
        JTextField textField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 10, 10, 10);
        this.frame.add(textField, gbc);

        //Creates textArea where the red team information is displayed; sets all constraints for GUI
        JTextArea textAreaR = new JTextArea(20,20);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.CENTER;
        textAreaR.append("RED TEAM");
        textAreaR.setEditable(false);
        this.frame.add(textAreaR, gbc);

        //Creates textArea where the blue team information is displayed; sets all constraints for GUI
        JTextArea textAreaB = new JTextArea(20,20);
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.CENTER;
        textAreaB.append("BLUE TEAM");
        textAreaB.setEditable(false);
        this.frame.add(textAreaB, gbc);

        JTextArea errorMessage = new JTextArea(1, 1); 
        gbc.gridx = 1;
        gbc.gridy = 0; 
        gbc.gridheight = 1;
        gbc.gridwidth = 2;
        errorMessage.setBackground(BACKGROUND);
        gbc.fill = GridBagConstraints.CENTER;
        errorMessage.append("");
        errorMessage.setEditable(false);
        this.frame.add(errorMessage, gbc);

        //Makes everything in the frame visible
        this.frame.setVisible(true);

        //Closes player entry screen and opens player action screen when start game is pressed
        startGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                frame.dispose();
                playerActionScreen(textAreaB, textAreaR);
            }
        });

        redButton.addActionListener(new ActionListener(){
            // when RedButton is pressed, the text in the text box is recorded and moved to the Red team
            @Override
            public void actionPerformed(ActionEvent event) {
                // records text in inputID
                String inputID = textField.getText();
                int id = Integer.parseInt(inputID);
                String codeName = database.getCodeName(id);

                //shortens the string if it's longer than 24 characters; also adds error message
                if (codeName.length() > 24) {
                    codeName = codeName.substring(0, 24);
                    errorMessage.setText("Your input is too long. It has been shortened automatically.");
                } else {
                    errorMessage.setText("");
                }

                // moves text to Red team area if the string is not blank
                if (!codeName.isBlank()) {
                    textAreaR.append("\n"+codeName);
                }
                // empties the text box
                textField.setText(null);
            }
        });

        blueButton.addActionListener(new ActionListener(){
            // when BlueButton is pressed, the text in the text box is recorded and moved to the Blue team
            @Override
            public void actionPerformed(ActionEvent event) {
                // records text in inputID
                String inputID = textField.getText();
                int id = Integer.parseInt(inputID);
                String codeName = database.getCodeName(id);

                //shortens the string if it's longer than 24 characters; also adds error message
                if (codeName.length() > 24) {
                    codeName = codeName.substring(0, 24);
                    errorMessage.setText("Your input is too long. It has been shortened automatically.");
                } else {
                    errorMessage.setText("");
                }

                // moves text to Red team area if the string is not blank
                if (!codeName.isBlank()) {
                    textAreaB.append("\n"+codeName);
                }
                // empties the text box
                textField.setText(null);
            }
        });
        return;
    }

   //F5 Key being pressed.
   void keyPressed(KeyEvent e)
   {
	int key = e.getKeyCode();
	if (key == KeyEvent.F5)
	{
		frame.dispose();
           	playerEntry();
	}
   }
	
    void addNewPlayer(){
        // creates new jPanel underneath start button
        JPanel panel = new JPanel();
        panel.setBackground(BACKGROUND);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel iDLabel = new JLabel("ID:");
    
        panel.add(iDLabel);
        JTextField newIDField = new JTextField(20);
        panel.add(newIDField);

        panel.add(Box.createRigidArea(new Dimension(0, 5)));

        panel.add(new JLabel("First Name:"));
        JTextField newFirstNameField = new JTextField(20);
        panel.add(newFirstNameField);

        panel.add(Box.createRigidArea(new Dimension(0, 5)));

        panel.add(new JLabel("Last Name:"));
        JTextField newLastNameField = new JTextField(20);
        panel.add(newLastNameField);

        panel.add(Box.createRigidArea(new Dimension(0, 5)));

        panel.add(new JLabel("Codename:"));
        JTextField newCodeNameField = new JTextField(20);
        panel.add(newCodeNameField);

        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton addNewPlayerButton = new JButton("Add New Player");
        panel.add(addNewPlayerButton);

        // adds jpanel to the jframe
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 2;

        this.frame.add(panel, gbc);
        
        this.frame.setVisible(true);

        // action listener for add new player button
        addNewPlayerButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String id = newIDField.getText();
                String firstName = newFirstNameField.getText();
                String lastName = newLastNameField.getText();
                String codeName = newCodeNameField.getText();
                if(id.isBlank() || firstName.isBlank() || lastName.isBlank() || codeName.isBlank()){
                    System.out.println("empty :(");
                } else{
                    database.addPlayer(id, firstName, lastName, codeName);
                    newIDField.setText(null);
                    newFirstNameField.setText(null);
                    newLastNameField.setText(null);
                    newCodeNameField.setText(null);
                }
            }
        });
    }

    // Creates a new JFrame for the player action screen
    void playerActionScreen(JTextArea B, JTextArea R) {
        
        int delay = 1000;
        int period = 1000;
        timer = new Timer();
        //interval = 30;



        JFrame action = new JFrame();
        action.setSize(1600, 900);
        action.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        action.add(B, gbc); //Copies over blue text area
        action.add(R, gbc); //Copies over red text area 
        action.setVisible(true);
        String temp;
        timer.scheduleAtFixedRate(new TimerTask() {

            public void run() {
                //interval--;
                String temp = Integer.toString(setInterval());
                B.setText(temp);
                   
            }
        }, delay, period);
    }

    Integer setInterval()
    {
        if(interval == 0)
        timer.cancel(); 

        return interval--;
    }

    void run(){
        this.splashScreen();
        this.playerEntry();
        this.addNewPlayer();
    }
}
