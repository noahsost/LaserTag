import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
			Thread.sleep( 3000);
		} catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
        splash.setVisible(false);
        return;
    }

    void playerEntry(){
        //defining locations in relation to each object
        GridBagConstraints constraint = new GridBagConstraints();
        constraint.gridx = 0;
        constraint.gridy = 0;
        GridBagConstraints constraintR = new GridBagConstraints();
        constraint.gridx = 0;
        constraint.gridy = 1;
        GridBagConstraints constraintB = new GridBagConstraints();
        constraint.gridx = 2;
        constraint.gridy = 1;
        GridBagConstraints constraintRT = new GridBagConstraints();
        constraint.gridx = 0;
        constraint.gridy = -1;
        GridBagConstraints constraintBT = new GridBagConstraints();
        constraint.gridx = 8;
        constraint.gridy = -1;
        JButton redButton= new JButton("Red Team"); 
        JButton blueButton= new JButton("Blue Team"); 
        // when the column value is changed, it changes the width of 
        JTextField textField = new JTextField(20);
        // making the areas where teams are displayed, defining size and labelling
        JTextArea textAreaR = new JTextArea(20,20);
        textAreaR.append("RED TEAM");
        textAreaR.setEditable(false);
        JTextArea textAreaB = new JTextArea(20,20);
        textAreaB.append("BLUE TEAM");
        textAreaB.setEditable(false);
        //adding elements to the screen
        this.frame.add(textField, constraint);
        this.frame.add(redButton, constraintR);
        this.frame.add(blueButton, constraintB);
        this.frame.add(textAreaR, constraintRT);
        this.frame.add(textAreaB, constraintBT);
        this.frame.setVisible(true);
        redButton.addActionListener(new ActionListener(){
            // when RedButton is pressed, the text in the text box is recorded and moved to the Red team
            @Override
            public void actionPerformed(ActionEvent event) {
                // records text in aString
                String aString = textField.getText();
                // moves text to Red team area
                textAreaR.append("\n"+aString);
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
                // moves text to Blue team area
                textAreaB.append("\n"+aString);
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