package projectGUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SyntaxAnalysisWindow extends BaseWindow {

    private static final Color BACKGROUND_COLOR = Color.decode("#DC0000");

    public SyntaxAnalysisWindow(JFrame previousWindow) {
        super(previousWindow);

    }

    @Override
    public void openWindow() {
        JFrame syntaxAnalysisWindow = new JFrame("Syntax Analysis Window");
        syntaxAnalysisWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        syntaxAnalysisWindow.setSize(800, 600);

        // Calculate the position for the new window relative to the previous window
        if (previousWindow != null) {
            Point previousLocation = previousWindow.getLocationOnScreen();
            int newX = previousLocation.x;
            int newY = previousLocation.y;
            syntaxAnalysisWindow.setLocation(newX, newY);
        } else {
            // If there is no previous window, center the new window on the screen
            syntaxAnalysisWindow.setLocationRelativeTo(null);
        }

        Container contentPane = syntaxAnalysisWindow.getContentPane();
        contentPane.setBackground(BACKGROUND_COLOR);

        // Create an EmptyBorder for spacing
        int topSpacing = 13;
        EmptyBorder emptyBorderTop = new EmptyBorder(topSpacing, 0, 0, 0);

        // Create the top panel for the buttons
        JPanel topLeftPanel = new JPanel();
        topLeftPanel.setLayout(new BorderLayout());
        topLeftPanel.setBackground(BACKGROUND_COLOR);
        topLeftPanel.setBorder(emptyBorderTop);

        JButton homeButton = new JButton("Home");
        homeButton.setBackground(BACKGROUND_COLOR);
        homeButton.setForeground(Color.decode("#CDB98E"));
        homeButton.setBorderPainted(false);

        // Increase the font size
        Font buttonFont = homeButton.getFont();
        Font newButtonFont = buttonFont.deriveFont(buttonFont.getSize() + 6f);
        homeButton.setFont(newButtonFont);

        topLeftPanel.add(homeButton);

    }

}
