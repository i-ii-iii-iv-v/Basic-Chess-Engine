package ca.bcit.comp2526.a2a;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * ChessPiece.java
 * 
 * @author Alex You
 * @version 1.0
 *
 */
@SuppressWarnings("serial")
abstract class ChessPiece extends JLabel {
    private int posX;
    private int posY;
    private int pieceColor;
    private boolean alive = true;
    
    /**
     * <p>Constructor for class ChessPiece.</p>
     * 
     * @param piece
     *          ImageIcon reference variable for JLabel constructor.
     */
    ChessPiece(ImageIcon piece, int color) {
        super(piece);
        pieceColor = color;
    }

    /**
     * <p>sets the position of the piece.</p>
     * @param posX
     *          horizontal position.
     * @param posY
     *          vertical position.
     */
    public void setPos(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }
    
    public void setPosY(int posY) {
        this.posY = posY;
    }
    
    public abstract boolean movePiece(int posX, int posY, Square[][] squares);
    
    public int getColor(){
        return pieceColor;
    }
    
    public abstract boolean capture(int posX, int posY, Square[][] squares);

    public boolean isAlive(){
        return alive;
    }

    public void killPiece(){
        posX = -1;
        posY = -1;
        alive = false;
    }
    
    public void kill(int posX, int posY, Square[][] squares){
        System.out.println("in process of caturing");
        squares[posX][posY].getPiece().killPiece();
        squares[posX][posY].setPiece(null);
        squares[posX][posY].setEmpty(true);
        setPos(posX, posY);
    }

    /**
     * @return posX
     *          as vertical position of the piece.
     */
    public int getPosX() {
        return posX;
    }
    
    /**
     * 
     * @return posY
     *          as horizontal position of the piece.
     */
    public int getPosY() {
        return posY;
    }
    
    public String toString(){
        return "abc";
    }
}
