package projectGUI;
import functionality.LambdaCalculusFunctionality;
import model.BaseWindow;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class LambdaCalculusWindow extends BaseWindow {
    private static final Color BACKGROUND_COLOR = Color.decode("#0096FF");
    private JTextField inputTextField;
    private JTextField output; // Added JTextField for output
    private JPanel topPanel;



    public LambdaCalculusWindow(JFrame previousWindow) {
        super(previousWindow);
    }

    @Override
    public void openWindow() {
        JFrame lambdaWindow = new JFrame("Lambda Window");
        lambdaWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        lambdaWindow.setSize(800, 600);
        lambdaWindow.setLayout(new BorderLayout());

        // Calculate the position for the new window relative to the previous window
        if (previousWindow != null) {
            Point previousLocation = previousWindow.getLocationOnScreen();
            int newX = previousLocation.x;
            int newY = previousLocation.y;
            lambdaWindow.setLocation(newX, newY);
        } else {
            // If there is no previous window, center the new window on the screen
            lambdaWindow.setLocationRelativeTo(null);
        }

        // Top Panel

        // Create an EmptyBorder for spacing
        int topSpacing = 13;
        EmptyBorder emptyBorderTop = new EmptyBorder(topSpacing, 0, 0, 0);

        // Create the top left panel for the Home button
        JPanel topLeftPanel = new JPanel();
        topLeftPanel.setLayout(new BoxLayout(topLeftPanel, BoxLayout.X_AXIS));
        topLeftPanel.setBackground(BACKGROUND_COLOR);
        topLeftPanel.setBorder(emptyBorderTop);

        JButton homeButton = new JButton("Home");
        homeButton.setBackground(BACKGROUND_COLOR);
        homeButton.setForeground(Color.WHITE);
        homeButton.setBorderPainted(false);

        // Increase the font size
        Font buttonFont = homeButton.getFont();
        Font newButtonFont = buttonFont.deriveFont(buttonFont.getSize() + 6f);
        homeButton.setFont(newButtonFont);

        topLeftPanel.add(homeButton);

        // Add the top left panel to the content pane
        lambdaWindow.add(topLeftPanel, BorderLayout.NORTH);

        // Create a panel for the label and input text box
        topPanel = getTopPanel(emptyBorderTop); // Use the existing topPanel field

        // Add the top panel to the content pane
        lambdaWindow.add(topPanel, BorderLayout.CENTER);

        // Bottom Panel

        // Create a panel for the buttons (SyntaxAnalysis and LambdaCalculus)
        JPanel bottomPanel = new JPanel(new GridBagLayout());
        bottomPanel.setBackground(BACKGROUND_COLOR);

        // Create an EmptyBorder for spacing
        int bottomSpacing = 36;
        EmptyBorder emptyBorderBottom = new EmptyBorder(0, 0, bottomSpacing, 0);

        // Apply the EmptyBorder to the bottom panel
        bottomPanel.setBorder(emptyBorderBottom);

        // Create GridBagConstraints for button positioning
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.NONE;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;

        // Create the syntax analysis button and set its position
        JButton syntaxAnalysisButton = new JButton("Syntax Analysis");
        syntaxAnalysisButton.setBackground(Color.decode("#2F3E41"));
        syntaxAnalysisButton.setForeground(Color.decode("#BFDB38"));
        syntaxAnalysisButton.setPreferredSize(new Dimension(200, 50));

        constraints.gridx = 0;
        constraints.gridy = 0;
        bottomPanel.add(syntaxAnalysisButton, constraints);

        syntaxAnalysisButton.addActionListener(e -> {
            SyntaxAnalysisWindow syntaxAnalysisWindow = new SyntaxAnalysisWindow(lambdaWindow);
            syntaxAnalysisWindow.openWindow();

            lambdaWindow.dispose();
        });

        // Create the Tokenization button and set its position
        JButton tokenizationButton = new JButton("Tokenization");
        tokenizationButton.setBackground(Color.decode("#2F3E41"));
        tokenizationButton.setForeground(Color.decode("#BFDB38"));
        tokenizationButton.setPreferredSize(new Dimension(200, 50));

        constraints.gridx = 1;
        constraints.gridy = 0;
        bottomPanel.add(tokenizationButton, constraints);

        tokenizationButton.addActionListener(e -> {
            TokenizationWindow tokenizationWindow = new TokenizationWindow(lambdaWindow);
            tokenizationWindow.openWindow();

            lambdaWindow.dispose();

        });

        lambdaWindow.add(bottomPanel, BorderLayout.SOUTH);

        // Add ActionListener to the homeButton
        homeButton.addActionListener(e -> {
            MyGUI mainMenu = new MyGUI(lambdaWindow);
            lambdaWindow.dispose(); // Close the current window

            mainMenu.setVisible(true); // Bring back the main menu
        });

        // Initialize the output JTextField
        output = new JTextField(20);
        output.setEditable(false);
        output.setBackground(Color.WHITE);
        output.setForeground(Color.BLACK);

        // Add the JTextField to the top panel
        topPanel.add(output, BorderLayout.SOUTH);

        // Listen for "Enter" key presses in the input text field
        inputTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    updateTextBox(); // Call the updated method
                }
            }
        });

        // Set the window visible
        lambdaWindow.setVisible(true);
    }

    private JPanel getTopPanel(EmptyBorder emptyBorderTop) {
        topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(BACKGROUND_COLOR);
        topPanel.setBorder(emptyBorderTop);

        // Panel for label and input text with FlowLayout
        JPanel labelInputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        labelInputPanel.setBackground(BACKGROUND_COLOR);

        // Add a label
        JLabel label = new JLabel("Enter a linear lambda function:  λx.");
        label.setForeground(Color.WHITE);
        labelInputPanel.add(label);

        // Add a text box
        inputTextField = new JTextField(20);
        labelInputPanel.add(inputTextField);

        topPanel.add(labelInputPanel, BorderLayout.NORTH);

        return topPanel;
    }

    private void updateTextBox() {
        String input = inputTextField.getText();
        int answer = LambdaCalculusFunctionality.evaluateLambdaExpression(input);
        output.setText("The answer is: " + answer);
    }
}

