import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ColorPanel extends JPanel {
    private int row;
    private int col;
    Color bgColor;
    GUIView theView;

    public ColorPanel(Color bgColor, int row, int col, GUIView theView) {
        this.bgColor = bgColor;
        setBackground(bgColor);
        this.row = row;
        this.col = col;
        this.theView = theView;
        setPreferredSize(new Dimension(50, 50));
        this.addMouseListener(new PanelListener());
    }

    /*
     redraws this panel with current background color
     */
    public void paintComponent(Graphics g) {
        setBackground(bgColor);
        super.paintComponent(g);

    }

    public void setColor(Cell cell) {
        if (cell.isAlive())
            bgColor = Color.YELLOW;
        else
            bgColor = Color.GRAY;
        repaint();
    }

    public void resetTile() {
        bgColor = Color.GRAY;
        repaint();
    }

    private class PanelListener extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            theView.play(row, col); // ask the view to tell the model to make a move at row, col
            repaint(); // update all GUI components by calling their paintComponent() method
        }
    }
}
