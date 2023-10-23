package projectGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Exception;

public class LogIn {
    //create CreateLoginForm class to create login form
    //class extends JFrame to create a window where our component add
    //class implements ActionListener to perform an action on button click
    static class CreateLoginForm extends JFrame implements ActionListener
    {

        //initialize button, panel, label, and text field
        JButton b1;
        JPanel newPanel;
        JLabel userLabel, passLabel;

        final JTextField  textField1, textField2;

        //calling constructor
        CreateLoginForm()
        {
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            newPanel = new JPanel(new GridBagLayout()) {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    // Load and draw the background image
                    ImageIcon background = new ImageIcon("code pic.jpg");
                    g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
                }
            };
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);


            userLabel = new JLabel("Username:");
            Font labelFont = userLabel.getFont(); // Get the current font
            userLabel.setFont(new Font(labelFont.getName(), Font.BOLD, 20)); // Set a new font with a larger size

            textField1 = new JTextField(15);
            passLabel = new JLabel("Password:");
            passLabel.setFont(new Font(labelFont.getName(), Font.BOLD, 20));
            textField2 = new JPasswordField(15);
            b1 = new JButton("SUBMIT");


            gbc.gridx = 0;
            gbc.gridy = 0;
            newPanel.add(userLabel, gbc);
            gbc.gridx = 1;
            newPanel.add(textField1, gbc);
            gbc.gridx = 0;
            gbc.gridy = 1;
            newPanel.add(passLabel, gbc);
            gbc.gridx = 1;
            newPanel.add(textField2, gbc);
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.gridwidth = 2;
            newPanel.add(b1, gbc);

            // Change the background and foreground color of labels, text fields, and button
            userLabel.setBackground(Color.LIGHT_GRAY);
            userLabel.setForeground(Color.WHITE);

            passLabel.setBackground(Color.LIGHT_GRAY);
            passLabel.setForeground(Color.WHITE);

            textField1.setBackground(Color.WHITE);
            textField1.setForeground(Color.BLACK);

            textField2.setBackground(Color.WHITE);
            textField2.setForeground(Color.BLACK);

            b1.setBackground(Color.BLUE);
            b1.setForeground(Color.WHITE);

// Change the background color of the panel
            //newPanel.setBackground(Color.MAGENTA);


            add(newPanel, BorderLayout.CENTER);

            //perform action on button click
            b1.addActionListener(this);     //add action listener to button
            setTitle("LOGIN FORM");         //set title to the login form





        }

        //define abstract method actionPerformed() which will be called on button click
        public void actionPerformed(ActionEvent ae)     //pass action listener as a parameter
        {
            String userValue = textField1.getText();        //get user entered username from the textField1
            String passValue = textField2.getText();        //get user entered password from the textField2

            //check whether the credentials are authentic or not
            if (userValue.equals("Morgan@gmail.com") && passValue.equals("Morgan") || userValue.equals("GroupF@gmail.com") && passValue.equals("GroupF") ) {  //if authentic, navigate user to a new page

                SwingUtilities.invokeLater(() -> {
                    MyGUI mainWindow = new MyGUI(this);
                    mainWindow.setVisible(true);
                    JLabel wel_label = new JLabel("Welcome: " + userValue);

                });
                SwingUtilities.invokeLater(this::dispose);
            }

            else{
                //show error message
                JOptionPane.showMessageDialog(this, "Invalid Username or Password",
                        "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    //create the main class

    //main() method start
    public static void main(String[] arg)
    {

        try {
            // Create the login form
            SwingUtilities.invokeLater(() -> {
                CreateLoginForm form = new CreateLoginForm();
                form.setSize(800, 600); // Set the size of the login form
                form.setVisible(true);  // Make the login form visible to the user
                form.setLocationRelativeTo(null);
            });
        } catch (Exception e) {
            // Handle exception
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }


}


