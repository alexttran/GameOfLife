import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIView extends JFrame {
    private LifeModel board;  // a reference to the model (our board and the game logic
    private ColorPanel[][] panels; // our 2D array of panels representing the GUI TTTBoard
    private JPanel controlPanel;  //  a little panel to hold our "new game" button
    private JPanel boardPanel;    //  a panel in which we put our 2D array of color "tiles"
    private JButton clearAllButton; // user can click this button to clear all tiles.
    private JButton nextGenButton;

    public GUIView() {
        board = new LifeModel(20, 20);
        panels = new ColorPanel[20][20];
        for (int r = 0; r < 20; r++) {
            for (int c = 0; c < 20; c++) {
                panels[r][c] = new ColorPanel(Color.GRAY, r, c, this);
                panels[r][c].setSize(10, 10);
            }
        }
        controlPanel = new JPanel();
        controlPanel.setBackground(Color.CYAN);
        clearAllButton = new JButton("CLEAR ALL TILES");
        clearAllButton.addActionListener(new ButtonListener());
        controlPanel.add(clearAllButton);
        nextGenButton = new JButton("Next Gen");
        nextGenButton.addActionListener(new nextGenListener());
        controlPanel.add(nextGenButton);
        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(20, 20));
        for (int r = 0; r < 20; r++) {
            for (int c = 0; c < 20; c++)
                boardPanel.add(panels[r][c]);
        }

        this.setTitle("Conway's Game of Life");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        Container pane = getContentPane();
        pane.add(controlPanel, BorderLayout.NORTH);
        pane.add(boardPanel, BorderLayout.CENTER);

        this.pack();
        this.setVisible(true);
        board.clearAll();
    }

    public void play(int row, int col) {
        if (board.getCell(row, col).isAlive()) {
            board.getCell(row, col).setAlive(false);
        } else {
            board.getCell(row, col).setAlive(true);
        }
        panels[row][col].setColor(board.getCell(row, col));
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            board.clearAll(); // reset the model
            for (int r = 0; r < 20; r++) {
                for (int c = 0; c < 20; c++) {
                    panels[r][c].resetTile();
                }
            }
        }
    }

    private class nextGenListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            board.nextLife();
            for (int r = 0; r < 20; r++) {
                for (int c = 0; c < 20; c++) {
                    panels[r][c].setColor(board.getCell(r, c));
                }
            }
        }
    }
}
