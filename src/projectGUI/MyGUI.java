package projectGUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MyGUI extends JFrame {

    public MyGUI(JFrame previousWindow) {
        // Reference to the previous window
        setTitle("Home Window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        setResizable(false);

        // Calculate the position for the new window relative to the previous window
        if (previousWindow != null) {
            Point previousLocation = previousWindow.getLocationOnScreen();
            int newX = previousLocation.x;
            int newY = previousLocation.y;
            setLocation(newX, newY);

        } else {
            // If there is no previous window, center the new window on the screen
            setLocationRelativeTo(null);

        }

        // Create an EmptyBorder for spacing
        int topSpacing = 13;
        EmptyBorder emptyBorderTop = new EmptyBorder(topSpacing, 0, 0, 0);

        // Create the main panel and set the layout
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Create the top left panel for the Home button
        JPanel topLeftPanel = new JPanel();
        topLeftPanel.setLayout(new BoxLayout(topLeftPanel, BoxLayout.X_AXIS));
        topLeftPanel.setBackground(Color.decode("#070605"));
        JButton homeButton = new JButton("Home");
        homeButton.setBackground(Color.decode("#070605"));
        homeButton.setForeground(Color.decode("#CDB98E"));
        homeButton.setBorderPainted(false);

        // Increase the font size
        Font buttonFont = homeButton.getFont();
        Font newButtonFont = buttonFont.deriveFont(buttonFont.getSize() + 6f);
        homeButton.setFont(newButtonFont);

        topLeftPanel.add(homeButton);
        topLeftPanel.setBorder(emptyBorderTop);

        mainPanel.add(topLeftPanel, BorderLayout.NORTH);

        // Create the bottom panel for Tokenization, Syntax Analysis, and Lambda Calculus buttons
        JPanel bottomPanel = new JPanel(new GridBagLayout());

        // Create GridBagConstraints for button positioning
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.NONE;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;

        // Create an EmptyBorder for spacing
        int bottomSpacing = 45;
        EmptyBorder emptyBorderBottom = new EmptyBorder(0, 0, bottomSpacing, 0);

        // Apply the EmptyBorder to the bottom panel
        bottomPanel.setBorder(emptyBorderBottom);

        // Create the Tokenization button and set its position
        JButton tokenizationButton = new JButton("Tokenization");
        tokenizationButton.setBackground(Color.decode("#2F3E41"));
        tokenizationButton.setForeground(Color.decode("#BFDB38"));
        tokenizationButton.setPreferredSize(new Dimension(200, 50));

        constraints.gridx = 1;
        constraints.gridy = 0;
        bottomPanel.add(tokenizationButton, constraints);

        tokenizationButton.addActionListener(e -> {
            TokenizationWindow tokenizationWindow = new TokenizationWindow(MyGUI.this);
            tokenizationWindow.openWindow();

            dispose();

        });

        // Create the Syntax Analysis button and set its position
        JButton syntaxAnalysisButton = new JButton("Syntax Analysis");
        syntaxAnalysisButton.setBackground(Color.decode("#2F3E41"));
        syntaxAnalysisButton.setForeground(Color.decode("#BFDB38"));
        syntaxAnalysisButton.setPreferredSize(new Dimension(200, 50));

        constraints.gridx = 0;
        constraints.gridy = 2;
        bottomPanel.add(syntaxAnalysisButton, constraints);

        syntaxAnalysisButton.addActionListener(e -> {
            SyntaxAnalysisWindow syntaxAnalysisWindow = new SyntaxAnalysisWindow(MyGUI.this);
            syntaxAnalysisWindow.openWindow();

            dispose();

        });

        // Create the Lambda Calculus button and set its position
        JButton lambdaCalculusButton = new JButton("Lambda Calculus");
        lambdaCalculusButton.setBackground(Color.decode("#2F3E41"));
        lambdaCalculusButton.setForeground(Color.decode("#BFDB38"));
        lambdaCalculusButton.setPreferredSize(new Dimension(200, 50));

        constraints.gridx = 2;
        constraints.gridy = 2;
        bottomPanel.add(lambdaCalculusButton, constraints);

        lambdaCalculusButton.addActionListener(e -> {
            LambdaCalculusWindow lambdaCalculusWindow = new LambdaCalculusWindow(MyGUI.this);
            lambdaCalculusWindow.openWindow();

            dispose();

        });

        // Create a nested panel for the ECGPanel and set its layout
        JPanel ecgPanelContainer = new JPanel();
        ecgPanelContainer.setBackground(Color.decode("#070605"));

        // Use BorderLayout to position the ECGPanel at the bottom
        ecgPanelContainer.setLayout(new BorderLayout());

        // Create the ECG animation panel
        ECGPanel ecgPanel = new ECGPanel();
        ecgPanel.startAnimation(); // Start the animation

        // Add the ECG panel to the nested panel in the Center region
        ecgPanelContainer.add(ecgPanel, BorderLayout.CENTER);

        // Add the nested panel to the main panel in the Center region
        mainPanel.add(ecgPanelContainer, BorderLayout.CENTER);

        // Changing the colour of the background
        bottomPanel.setBackground(Color.decode("#070605"));
        ecgPanel.setBackground(Color.decode("#070605"));

        // Add the components to the main panel
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Set the background panel as the content pane of the window
        setContentPane(mainPanel);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MyGUI mainWindow = new MyGUI(null);
            mainWindow.setVisible(true);

        });

    }


}