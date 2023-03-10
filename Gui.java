import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
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


class Gui {
    JFrame frame;
    Database database;
    Color BACKGROUND = new Color(200, 200, 200);
    Color redText = new Color(210, 96, 96);
    Color blueText = new Color(79, 138, 196);
    Color scoreText = new Color(255, 255, 255);
    int interval = 30;
    Timer timer;
    int first = 1;
    List<Player> redTeam = new ArrayList();
    List<Player> blueTeam = new ArrayList();

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

    void panelBase(JPanel panel, String input, Color color, int columns) {
        panel.setBackground(BACKGROUND);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel());
        JTextField textField = new JTextField(columns);
        textField.setBackground(color);
        textField.setEditable(false);
        textField.setText(input);
        panel.add(textField);
        panel.add(Box.createRigidArea(new Dimension(0, 0)));
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
                playerActionScreen();
            }
        });

        redButton.addActionListener(new ActionListener(){
            // when RedButton is pressed, the text in the text box is recorded and moved to the Red team
            @Override
            public void actionPerformed(ActionEvent event) {
                // records text in inputID
                String inputID = textField.getText();
                int id = Integer.parseInt(inputID);
                Player aPlayer = database.getExistingPlayer(id);
                String codeName = aPlayer.codeName; 

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
                    redTeam.add(aPlayer);
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
                Player aPlayer = database.getExistingPlayer(id);
                String codeName = aPlayer.codeName;

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
                    blueTeam.add(aPlayer); 
                }
                // empties the text box
                textField.setText(null);
            }
        });
        return;
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
    void playerActionScreen() {
        JFrame action = new JFrame();
        action.setSize(1600, 900);
        action.setLayout(new GridBagLayout());
        action.setMinimumSize(new Dimension(1000, 700));
        action.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        action.getContentPane().setBackground(new Color(200, 200, 200));

        GridBagConstraints gbc = new GridBagConstraints();
        action.setVisible(true);

        //Defines panels
        JPanel redPanel = new JPanel();
        JPanel bluePanel = new JPanel();
        JPanel red = new JPanel();
        JPanel blue = new JPanel();

        //Displays score for red team
        panelBase(redPanel, "Red Score", redText, 8);
        for (int i = 0; i < 15; i++) {
            panelBase(redPanel, "", scoreText, 8);
        }
        panelBase(redPanel, "", redText, 8);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.CENTER;
        action.add(redPanel, gbc);

        //Displays red team
        panelBase(red, "Red Team", redText, 20);
        for (int i = 0; i < 15; i++) {
            boolean inBounds = (i >= 0) && (i < redTeam.size());
            if (!inBounds) {
                panelBase(red, "", scoreText, 20);
            } else {
                panelBase(red, redTeam.get(i).codeName, scoreText, 20);
            }
        }
        panelBase(red, "", redText, 20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.CENTER;
        action.add(red, gbc);

        //Displays score for blue team
        panelBase(bluePanel, "Blue Score", blueText, 8);
        for (int i = 0; i < 15; i++) {
            panelBase(bluePanel, "", scoreText, 8);
        }
        panelBase(bluePanel, "", blueText, 8);
        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.CENTER;
        action.add(bluePanel, gbc);

        //Displayes blue team
        panelBase(blue, "Blue Team", blueText, 20);
        for (int i = 0; i < 15; i++) { 
            boolean inBounds = (i >= 0) && (i < blueTeam.size());
            if (!inBounds) {
                panelBase(blue, "", scoreText, 20);
            } else {
                panelBase(blue, blueTeam.get(i).codeName, scoreText, 20);
            }
        }
        panelBase(blue, "", blueText, 20);
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.CENTER;
        action.add(blue, gbc);

        //Says when the game is starting
        JTextArea gameStartText = new JTextArea(1, 10);
        gameStartText.setBackground(BACKGROUND);
        gbc.gridx = 1; 
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.CENTER;
        gameStartText.setText("   Game starting in: ");
        gbc.insets = new Insets(5, 0, 5, 0);
        gameStartText.setEditable(false);
        action.add(gameStartText, gbc);

        //Displays the timer at the bottom of the page
        JTextField timerDisplay = new JTextField(5);
        gbc.gridx = 1; 
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5, 0, 5, 0);
        timerDisplay.setEditable(false);
        action.add(timerDisplay, gbc);

        int delay = 1000;
        int period = 1000;
        timer = new Timer();
        //interval = 30;

        //Sets timer
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                //interval--;
                int second;
                int minute;
                minute = getInterval()/60;
                second = getInterval()%60;
                setInterval1(gameStartText);
                String temp = Integer.toString(minute)+ ":" +Integer.toString(second);
                if (second <= 9) {
                    temp = Integer.toString(minute)+ ":0" +Integer.toString(second);
                }
                timerDisplay.setText(temp);
            }
        }, delay, period);
    }

    Integer setInterval1(JTextArea text)
    {
        if(interval <= 0)
        {
            text.setText("   Time remaining: ");
            if(first == 1) {
                interval = 360;
                first = 0;
            } else {
            timer.cancel();
            } 
        }
        return interval--;
    }
    Integer getInterval()
    {
        return interval;
    }

    void run(){
        this.splashScreen();
        this.playerEntry();
        this.addNewPlayer();
    }
}
