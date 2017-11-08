
/**
 * This class models a Delek in the game. A Delek has a position and can advance
 * towards the Doctor.
 */
public class Dalek {

    private int row, col;
    private boolean hasCrashed;

    /**
     * Initializes the variables for a Dalek.
     *
     * @param theRow The row this Dalek starts at.
     * @param theCol The column this Dalek starts at.
     */
    public Dalek(int theRow, int theCol) {
        this.row = theRow;
        this.col = theCol;
    }

    /**
     * Attempts to move the Dalek towards the Doctor by the most direct route,
     * moving up, down, right, left or diagonally. For example, if the Doctor is
     * above and to the right of a Dalek, it will move diagonally. If the Doctor
     * is directly below a Dalek, it will move down.
     *
     * @param doc The Doctor to move towards.
     */
    public void advanceTowards(Doctor doc) {
        // if the column is to the left of the doctor and the dalek's row is below the doctor 
        if ((this.col < doc.getCol()) && (this.row < doc.getRow())) {
            // move the dalek closer to the doctor
            // move it one column to the right
            this.col++;
            // move it one row higher
            this.row++;
            // if the dalek's column is the same but row is below the doctor
        } else if ((this.col == doc.getCol()) && (this.row < doc.getRow())) {
            // move dalek higher one row
            this.row++;
            // if the column is the same but the dalek is above the doctor
        } else if ((this.col == doc.getCol()) && (this.row > doc.getRow())) {
            // move dalek lower
            this.row--;
            // if the row is the same but the dalek is to the left of the doctor
        } else if ((this.col < doc.getCol()) && (this.row == doc.getRow())) {
            // move dalek to the right
            this.col++;
            // if the row is the same but dalek is to the right of the doctor
        } else if ((this.col > doc.getCol()) && (this.row == doc.getRow())) {
            // move the dalek left
            this.col--;
            // if the dalek is to the right and higher than the doctor
        } else if ((this.col > doc.getCol()) && (this.row > doc.getRow())) {
            // move to the left
            this.col--;
            // move lower
            this.row--;
            // if the dalek is lower and to the right
        } else if ((this.col < doc.getCol()) && (this.row > doc.getRow())) {
            // move to the left
            this.col++;
            // move higher
            this.row--;
            // if the dalek is higher and to the left
        } else if ((this.col > doc.getCol()) && (this.row < doc.getRow())) {
            // move dalek to the right
            this.col--;
            // move dalek lower
            this.row++;
        } 
    }

    /**
     * Returns the row of this Dalek.
     *
     * @return This Dalek's row.
     */
    public int getRow() {
        // return the dalek's row
        return this.row;
    }

    /**
     * Returns the column of this Dalek.
     *
     * @return This Dalek's column.
     */
    public int getCol() {
        // return the dalek's column
        return this.col;
    }

    /**
     * Sets the Dalek to be in a crashed state.
     */
    public void crash() {
        this.hasCrashed = true;
    }

    /**
     * Returns whether or not this Dalek has crashed.
     *
     * @return true if this Dalek has crashed, false otherwise
     */
    public boolean hasCrashed() {
        // dalek has crashed into another dalek 
        if (this.hasCrashed) {
            return true;
            // if they're not on the same spot then continue
        } else {
            return false;
        }
    }
}
