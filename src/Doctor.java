
import java.awt.Color;

/**
 * This class models the Doctor in the game. A Doctor has a position and can
 * move to a new position.
 */
public class Doctor {

    private int row, col;

    /**
     * Initializes the variables for a Doctor.
     *
     * @param theRow The row this Doctor starts at.
     * @param theCol The column this Doctor starts at.
     */
    public Doctor(int theRow, int theCol) {
        this.row = theRow;
        this.col = theCol;
    }

    /**
     * Move the Doctor. If the player clicks on one of the squares immediately
     * surrounding the Doctor, the peg is moved to that location. Clicking on
     * the Doctor does not move the peg, but instead allows the Doctor to wait
     * in place for a turn. Clicking on any other square causes the Doctor to
     * teleport to a random square (perhaps by using a �sonic screwdriver�).
     * Teleportation is completely random.
     *
     * @param newRow The row the player clicked on.
     * @param newCol The column the player clicked on.
     */
    public void move(int newRow, int newCol) {
        // if the user clicks on the doctor
        if (newRow == this.row && newCol == this.col) {
            // make the doctor stay where it is and "skip a turn"
            newRow = this.row;
            newCol = this.col;
        }
        // if the user presses one of the 8 surronding squares for the doctor to move
        if (((newRow - this.row == -1 || newRow - this.row ==0) && (newCol - this.col ==0 || newCol - this.col == -1 || newCol - this.col ==1))
                || ((newRow - this.row == -1 || newRow - this.row == 0) && (newCol - this.col == -1))
                || ((newRow - this.row ==1 || newRow - this.row ==0) && (newCol - this.col ==-1 || newCol - this.col ==0 || newCol - this.col ==1))
                || ((newRow - this.row ==0) && (newCol - this.col ==1))){
            // move the doctor to the pressed square
            this.row = newRow;
            this.col = newCol;
        }
        // if the user does not press on a surrounding square or itelf
        if ((newRow != this.row + 1 || newRow != this.row - 1)
                && (newCol != this.col + 1 || newCol != this.col - 1)
                || (newRow != this.row && newCol != this.col)) {
            // move the doctor to a random position by making the row and col random
            this.row = (int) (Math.random() * 12);
            this.col = (int) (Math.random() * 12);
        }
    }

    /**
     * Returns the row of this Doctor.
     *
     * @return This Doctor's row.
     */
    public int getRow() {
        // get the row of the doctor
        return this.row;
    }

    /**
     * Returns the column of this Doctor.
     *
     * @return This Doctor's column.
     */
    public int getCol() {
        // get the column of the doctor
        return this.col;
    }
}
