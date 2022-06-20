import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LifeModel {
    private Cell[][] world;
    int numRows;
    int numCols;


    public LifeModel(String fName) throws FileNotFoundException {
        Scanner reader = new Scanner(new File(fName));
        numRows = reader.nextInt();
        numCols = reader.nextInt();
        world = new Cell[numRows][numCols];
        for (int r = 0; r < numRows; r++) {
            for (int c = 0; c < numCols; c++) {
                world[r][c] = new Cell();
            }
        }
        while (reader.hasNextInt()) {
            world[reader.nextInt()][reader.nextInt()].setAlive(true);
        }
    }

    public LifeModel(int rows, int cols) {
        numRows = rows;
        numCols = cols;
        world = new Cell[numRows][numCols];
        world = new Cell[numRows][numCols];
        for (int r = 0; r < numRows; r++) {
            for (int c = 0; c < numCols; c++) {
                world[r][c] = new Cell();
            }
        }
    }

    public Cell getCell(int row, int col) {
        return world[row][col];
    }


    public String toModelString() {
        String str = "";
        for (int r = 0; r < numRows; r++) {
            for (int c = 0; c < numCols; c++) {
                str += world[r][c].toString();
            }
            str += "\n";
        }
        return str;
    }

    private boolean inBounds(int row, int col) {
        if (row >= 0 && row < numRows) {
            if (col >= 0 && col < numCols) {
                return true;
            }
        }
        return false;
    }

    public int countNeighbors(int r, int c) {
        int countAlive = 0;
        for (int i = r - 1; i <= r + 1; i++) {
            for (int j = c - 1; j <= c + 1; j++) {
                if (i == r && j == c) {
                } else if (inBounds(i, j)) {
                    if (world[i][j].isAlive()) {
                        countAlive++;
                    }
                }
            }
        }
        return countAlive;
    }

    public void nextLife() {
        Cell[][] newCells = new Cell[numRows][numCols];
        for (int r = 0; r < numRows; r++) {
            for (int c = 0; c < numCols; c++) {
                newCells[r][c] = new Cell();
            }
        }
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                int numNeighbors = countNeighbors(row, col);
                if (world[row][col].isAlive()) {
                    if (numNeighbors == 2 || numNeighbors == 3) {
                        newCells[row][col].setAlive(true);
                    } else {
                        newCells[row][col].setAlive(false);
                    }
                } else {
                    if (numNeighbors == 3) {
                        newCells[row][col].setAlive(true);
                    } else {
                        newCells[row][col].setAlive(false);
                    }
                }
            }
        }
        world = newCells;
    }

    public void clearAll() {
        for (int r = 0; r < numRows; r++) {
            for (int c = 0; c < numCols; c++) {
                world[r][c].setAlive(false);
            }
        }
    }


}
