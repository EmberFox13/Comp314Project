package projectGUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.util.List;
import functionality.TokenizationFunctionality;
import model.BaseWindow;

import java.awt.event.KeyEvent;

public class TokenizationWindow extends BaseWindow {

    private static final Color BACKGROUND_COLOR = Color.decode("#54B435");
    private JTextField inputTextField;
    private JScrollPane scrollPane;
    private JPanel topPanel;

    public TokenizationWindow(JFrame previousWindow) {
        super(previousWindow);
    }

    @Override
    public void openWindow() {
        JFrame tokenizationWindow = new JFrame("Tokenization Window");
        tokenizationWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tokenizationWindow.setSize(800, 600);
        tokenizationWindow.setLayout(new BorderLayout());

        // Calculate the position for the new window relative to the previous window
        if (previousWindow != null) {
            Point previousLocation = previousWindow.getLocationOnScreen();
            int newX = previousLocation.x;
            int newY = previousLocation.y;
            tokenizationWindow.setLocation(newX, newY);
        } else {
            // If there is no previous window, center the new window on the screen
            tokenizationWindow.setLocationRelativeTo(null);
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
        tokenizationWindow.add(topLeftPanel, BorderLayout.NORTH);

        // Create a panel for the label and input text box
        JPanel topPanel = getTopPanel(emptyBorderTop);

        // Add the top panel to the content pane
        tokenizationWindow.add(topPanel, BorderLayout.CENTER);

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
            SyntaxAnalysisWindow syntaxAnalysisWindow = new SyntaxAnalysisWindow(tokenizationWindow);
            syntaxAnalysisWindow.openWindow();

            tokenizationWindow.dispose();
        });

        // Create the lambda calculus button and set its position
        JButton lambdaCalculusButton = new JButton("Lambda Calculus");
        lambdaCalculusButton.setBackground(Color.decode("#2F3E41"));
        lambdaCalculusButton.setForeground(Color.decode("#BFDB38"));
        lambdaCalculusButton.setPreferredSize(new Dimension(200, 50));

        constraints.gridx = 1;
        constraints.gridy = 0;
        bottomPanel.add(lambdaCalculusButton, constraints);

        lambdaCalculusButton.addActionListener(e -> {
            LambdaCalculusWindow lambdaCalculusWindow = new LambdaCalculusWindow(tokenizationWindow);
            lambdaCalculusWindow.openWindow();

            tokenizationWindow.dispose();
        });

        tokenizationWindow.add(bottomPanel, BorderLayout.SOUTH);

        // Add ActionListener to the homeButton
        homeButton.addActionListener(e -> {
            MyGUI mainMenu = new MyGUI(tokenizationWindow);
            tokenizationWindow.dispose(); // Close the current window

            mainMenu.setVisible(true); // Bring back the main menu
        });

        // Set the window visible
        tokenizationWindow.setVisible(true);

    }

    private JPanel getTopPanel(EmptyBorder emptyBorderTop) {
        topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(BACKGROUND_COLOR);
        topPanel.setBorder(emptyBorderTop);

        // Panel for label and input text with FlowLayout
        JPanel labelInputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        labelInputPanel.setBackground(BACKGROUND_COLOR);

        // Add a label
        JLabel label = new JLabel("Enter text:");
        label.setForeground(Color.WHITE);
        labelInputPanel.add(label);

        // Add a text box
        inputTextField = new JTextField(20);
        labelInputPanel.add(inputTextField);

        topPanel.add(labelInputPanel, BorderLayout.NORTH);

        // Create a list of selectable options
        scrollPane = getScrollPane("");

        // Add the JScrollPane to the top panel
        topPanel.add(scrollPane, BorderLayout.CENTER);

        // Listen for "Enter" key presses in the input text field
        inputTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    updateScrollPane();
                }

            }

        });

        return topPanel;

    }

    private static JScrollPane getScrollPane(String input) {
        // Tokenize the input
        List<String> tokens = TokenizationFunctionality.tokenizeString(input);
        String[] options = tokens.toArray(new String[0]);

        // Create a JList with the tokens
        JList<String> tokenList = new JList<>(options);
        tokenList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Add the JList to a JScrollPane
        JScrollPane scrollPane = new JScrollPane(tokenList);
        scrollPane.setPreferredSize(new Dimension(200, 100));
        scrollPane.setBorder(BorderFactory.createEmptyBorder(50, 36, 36, 36));
        scrollPane.setBackground(BACKGROUND_COLOR);
        return scrollPane;

    }

    private void updateScrollPane() {
        String input = inputTextField.getText();
        JScrollPane updatedScrollPane = getScrollPane(input);
        topPanel.remove(scrollPane);
        topPanel.add(updatedScrollPane, BorderLayout.CENTER);
        topPanel.revalidate();
        topPanel.repaint();
        scrollPane = updatedScrollPane;

    }

}
