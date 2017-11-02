
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

        while (true) {
            // move the doctor according to the click
            doctor.move(click.getRow(), click.getCol());

            //move the daleks towards the doctor
            dalek1.advanceTowards(doctor);
            dalek2.advanceTowards(doctor);
            dalek3.advanceTowards(doctor);

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
            //if the first dalek catches the doctor
            if ((dalek1.getCol() == doctor.getCol()) && (dalek1.getRow() == doctor.getRow())) {
                board.removePeg(doctor.getCol(), doctor.getRow());
                board.removePeg(dalek1.getRow(), dalek1.getCol());
                board.putPeg(Color.BLUE, doctor.getCol(), doctor.getRow());
                break;
            }
            //if dalek2 catches the doctor
            if ((dalek2.getCol() == doctor.getCol()) && (dalek2.getRow() == doctor.getRow())) {
                board.removePeg(doctor.getCol(), doctor.getRow());
                board.removePeg(dalek2.getRow(), dalek2.getCol());
                board.putPeg(Color.BLUE, doctor.getCol(), doctor.getRow());
                break;
            }
            //if dalek3 catches the doctor
            if ((dalek3.getCol() == doctor.getCol()) && (dalek3.getRow() == doctor.getRow())) {
                board.removePeg(doctor.getCol(), doctor.getRow());
                board.removePeg(dalek3.getRow(), dalek3.getCol());
                board.putPeg(Color.BLUE, doctor.getCol(), doctor.getRow());
                break;
            }
            // dalek cannot move
            if (dalek1.hasCrashed() == true || dalek2.hasCrashed() == true || dalek3.hasCrashed() == true) {
            }

            // if all daleks crash
            if((dalek1.getRow() == dalek2.getRow()) && (dalek1.getCol() == dalek2.getCol()
                    && (dalek3.getRow() == dalek2.getRow())) && (dalek3.getCol() == dalek2.getCol())){
                dalek1.crash();
                dalek2.crash();
                dalek3.crash();
                board.removePeg(dalek1.getRow(), dalek1.getCol());
                board.removePeg(dalek2.getRow(), dalek2.getCol());
                board.removePeg(dalek3.getRow(), dalek3.getCol());
                board.putPeg(Color.RED, dalek2.getRow(), dalek2.getCol());
                break;
            }
        }
    }
}
