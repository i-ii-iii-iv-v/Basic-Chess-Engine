package ca.bcit.comp2526.a2a;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Square extends JPanel {

    /**
     * <p>Variable for square size.</p>
     */
    private static final int DIMENSION = 80;
    
    private boolean selected = false;
    private boolean empty = true;
    
    private ChessPiece piece;
    private boolean redBorder = false;
    private int posX;
    private int posY;

    /**
     * <p>constructor for class Square.</p>
     * @param color
     *          as defining which color to set the board as.
     */
    Square(int color) {

        this.setPreferredSize(new Dimension(DIMENSION, DIMENSION));

        if (color == 0) {
            this.setBackground(new Color(190, 141, 28));
        } else {
            this.setBackground(new Color(75, 57, 17));
        }
    }

    /**
     * <p>sets blue border around the square and selects it.</p>
     */
    public void setBlueBorder() {
        this.setBorder(BorderFactory.createLineBorder(Color.blue, 5));
        selected = true;
    }

    /**
     * <p>erases any border around the square and deselects it.</p>
     */
    public void setNullBorder() {
        this.setBorder(null);
        selected = false;
    }

    public void setRedBorder() {
        this.setBorder(BorderFactory.createLineBorder(Color.red, 5));
        selected = true;//change to false
        redBorder = true;
    }
    
    public boolean getRedBorder(){
        return redBorder;
    }
    /**
     * <p>checks if square is selected.</p>
     * @return selected
     *              as variable the defines if squre is selected or not.
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * 
     * <p>sets up the position for the square.</p>
     * @param posX
     *          horizontal position of the square
     * @param posY
     *          vertical position of the square
     */
    public void setPos(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    /**
     * 
     * @return posX
     *           as current vertical position of the square.
     */
    public int getPosX() {
        return posX;
    }

    /**
     * 
     * @return posY
     *           as current horizontal position of the square.
     */
    public int getPosY() {
        return posY;
    }

    /**
     * 
     * @return empty
     *          as variable that checks if square is empty or not.
     */
    public boolean isEmpty() {
        return empty;
    }

    /**
     * <p>defines if the square is empty or not.</p>
     * @param space
     *          if true, empty, else false.
     */
    public void setEmpty(boolean space) {
        empty = space;
    }

    /**
     * <p>sets ChessPiece on the square.</p>
     * @param piece
     *           chess piece set up to this square.
     *           
     */
    public void setPiece(ChessPiece piece) {
        this.piece = piece;
    }

    /**
     * 
     * @return piece
     *             as piece currently on the square.
     */
    public ChessPiece getPiece() {
        return piece;
    }
}
