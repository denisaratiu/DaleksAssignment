
import java.awt.Color;

/**
 * This class manages the interactions between the different pieces of the game:
 * the Board, the Daleks, and the Doctor. It determines when the game is over
 * and whether the Doctor won or lost.
 */
public class CatchGame {

    private Board board;
    private Dalek dalek1;
    private Dalek dalek2;
    private Dalek dalek3;
    private Doctor doctor;
    private Coordinate click;
    boolean endGame;

    /**
     * Instance variables go up here Make sure to create a Board, 3 Daleks, and
     * a Doctor
     */
    /**
     * The constructor for the game. Use it to initialize your game variables.
     * (create people, set positions, etc.)
     */
    public CatchGame() {
        // create the board
        board = new Board(12, 12);

        // set the position of the first dalek
        dalek1 = new Dalek((int) (Math.random() * 12), (int) (Math.random() * 12));
        // set the position of the second dalek
        dalek2 = new Dalek((int) (Math.random() * 12), (int) (Math.random() * 12));
        // set the position of the third dalek
        dalek3 = new Dalek((int) (Math.random() * 12), (int) (Math.random() * 12));

        // make the daleks appear on the board in random positions
        board.putPeg(Color.BLACK, dalek1.getRow(), dalek1.getCol());
        board.putPeg(Color.BLACK, dalek2.getRow(), dalek2.getCol());
        board.putPeg(Color.BLACK, dalek3.getRow(), dalek3.getCol());

        // set the position of the doctor
        doctor = new Doctor((int) (Math.random() * 12), (int) (Math.random() * 12));

        // make the doctor appear on the board in a random position
        board.putPeg(Color.GREEN, doctor.getRow(), doctor.getCol());

        //make a variable to recieve a click from the user
        click = board.getClick();
    }

    /**
     * The playGame method begins and controls a game: deals with when the user
     * selects a square, when the Daleks move, when the game is won/lost.
     */
    public void playGame() {
        endGame = false;

        // all the daleks have crashed
        while ((dalek1.getCol() == dalek2.getCol() && dalek1.getRow() == dalek2.getRow())
                && (dalek2.getCol() == dalek3.getCol() && dalek2.getRow() == dalek3.getRow())
                && (dalek1.getCol() == dalek3.getCol() && dalek1.getRow() == dalek3.getRow())) {
            dalek1.crash();
            dalek2.crash();
            dalek3.crash();
            board.putPeg(Color.yellow, dalek1.getCol(), dalek2.getRow());
            System.out.println("The doctor has won!");
            endGame = true;
            break;
        }
        
        // if the first dalek has caught the doctor
        while (doctor.getCol() == dalek1.getCol() && doctor.getRow() == dalek1.getRow()) {
            // remove both pegs
            board.removePeg(doctor.getRow(), doctor.getCol());
            board.removePeg(dalek1.getCol(), dalek1.getRow());
            // place a blue peg that represents that the doctor is caught
            board.putPeg(Color.BLUE, doctor.getRow(), doctor.getCol());
            System.out.println("The daleks have won!");
            endGame = true;
            break;
        }
        // if the second dalek has caught the doctor
        while (doctor.getCol() == dalek2.getCol() && doctor.getRow() == dalek2.getRow()) {
            // remove both pegs
            board.removePeg(doctor.getRow(), doctor.getCol());
            board.removePeg(dalek2.getCol(), dalek2.getRow());
            // place a blue peg that represents that the doctor is caught
            board.putPeg(Color.BLUE, doctor.getRow(), doctor.getCol());
            System.out.println("The daleks have won!");
            endGame = true;
            break;
        }
        // if the third dalek has caught the doctor
        while (doctor.getCol() == dalek3.getCol() && doctor.getRow() == dalek3.getRow()) {
            // remove both pegs
            board.removePeg(doctor.getRow(), doctor.getCol());
            board.removePeg(dalek3.getCol(), dalek3.getRow());
            // place a blue peg that represents that the doctor is caught
            board.putPeg(Color.BLUE, doctor.getRow(), doctor.getCol());
            System.out.println("The daleks have won!");
            endGame = true;
            break;
        }
        // if dalek 1 and 2 crash remove one peg and place another one that is red
        if ((dalek1.getRow() == dalek2.getRow()) && (dalek1.getCol() == dalek2.getCol())) {
            dalek1.hasCrashed();
            dalek2.hasCrashed();
            board.removePeg(dalek1.getRow(), dalek1.getCol());
            board.removePeg(dalek2.getRow(), dalek2.getCol());
            board.putPeg(Color.RED, dalek1.getRow(), dalek1.getCol());
        }

        // if dalek 1 and 3 crash remove one peg and place another one that is red
        if ((dalek1.getRow() == dalek3.getRow()) && (dalek1.getCol() == dalek3.getCol())) {
            dalek1.hasCrashed();
            dalek3.hasCrashed();
            board.removePeg(dalek1.getRow(), dalek1.getCol());
            board.removePeg(dalek3.getRow(), dalek3.getCol());
            board.putPeg(Color.RED, dalek1.getRow(), dalek1.getCol());
        }

        // if dalek 2 and 3 crash remove one peg and place another one that is red
        if ((dalek3.getRow() == dalek2.getRow()) && (dalek3.getCol() == dalek2.getCol())) {
            dalek3.hasCrashed();
            dalek2.hasCrashed();
            board.removePeg(dalek3.getRow(), dalek3.getCol());
            board.removePeg(dalek2.getRow(), dalek2.getCol());
            board.putPeg(Color.RED, dalek2.getRow(), dalek2.getCol());

        }

        while (true && !endGame) {
            // allow the doctor to move
            // remove the peg
            board.removePeg(doctor.getRow(), doctor.getCol());
            // move the doctor
            doctor.move(click.getRow(), click.getCol());
            // put the new peg where the doctor clicked
            board.putPeg(Color.GREEN, doctor.getRow(), doctor.getCol());

            // move all the daleks towards the doctor
            // make sure it has not crashed
            if (!dalek1.hasCrashed()) {
                // remove the first dalek
                board.removePeg(dalek1.getRow(), dalek1.getCol());
                // move it towards the doctor
                dalek1.advanceTowards(doctor);
                // change its coordinates to be closer to the doctor
                board.putPeg(Color.BLACK, dalek1.getRow(), dalek1.getCol());
            }

            // make sure to move it only if its not crashed
            if (!dalek2.hasCrashed()) {
                // move it by removing the original peg
                board.removePeg(dalek2.getRow(), dalek2.getCol());
                // make the new peg coordinates near the doctor
                dalek2.advanceTowards(doctor);
                // put the peg down
                board.putPeg(Color.BLACK, dalek2.getRow(), dalek2.getCol());
            }
            // same as before
            if (!dalek3.hasCrashed()) {
                board.removePeg(dalek3.getRow(), dalek3.getCol());
                dalek3.advanceTowards(doctor);
                board.putPeg(Color.BLACK, dalek3.getRow(), dalek3.getCol());
            }
        }
    }
}
