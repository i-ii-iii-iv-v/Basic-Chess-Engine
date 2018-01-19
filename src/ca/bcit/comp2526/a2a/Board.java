package ca.bcit.comp2526.a2a;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Board.java
 * 
 * @author Alex You
 * @version 1.0
 *
 */
@SuppressWarnings("serial")
public class Board extends JPanel implements Serializable{

    /**
     * <p>
     * variable for common number used in chess: 8.
     * </p>
     */
    public static final int CHESSNUMBER = 8;

    /**
     * <p>
     * variable for specifying chess game size.
     * </p>
     */
    PieceSerializable serialPiece;
    public static int turn = 1; // if 1, white turn, 0, black turn
    private Square clicked;
    private Square prev;    
    private ChessPiece blackKing;
    private ChessPiece whiteKing;
    private static int white;
    Square[][] squares = new Square[CHESSNUMBER][CHESSNUMBER];
    ChessListener listener;

    Board() {
        listener = new ChessListener();

        for (int i = 0; i < CHESSNUMBER; i++) {

            for (int j = 0; j < CHESSNUMBER; j++) {
                // even odd, even even, odd even, odd odd
                if (i % 2 == 0 && j % 2 == 0) {
                    white = 1;
                } else if (i % 2 == 0 && j % 2 == 1) {
                    white = 0;
                } else if (i % 2 == 1 && j % 2 == 0) {
                    white = 0;
                } else if (i % 2 == 1 && j % 2 == 1) {
                    white = 1;
                }

                squares[j][i] = new Square(white);
                squares[j][i].setPos(j, i);
                squares[j][i].addMouseListener(listener);
                this.add(squares[j][i]);
            }
        }
        serialPiece = new PieceSerializable();
        setup();
    }

    private class ChessListener implements MouseListener ,Serializable {

        @Override
        public void mouseClicked(MouseEvent event) {
            clicked = (Square) event.getSource();

            if(turn ==   -1){
                return;
            }
            if(!clicked.isEmpty() &&/* prev == null &&*/ clicked.getPiece().getColor() != turn){
                if(turn == 1){
                    JOptionPane.showMessageDialog(null, "White's turn");
                }
                else if(turn == 0){
                    JOptionPane.showMessageDialog(null, "Black's turn");
                }
                return;
            }
            
            /*if(!clicked.isEmpty() && clicked.getPiece().getColor() != turn){
                JOptionPane.showMessageDialog(null, "Wrong Color");
                return;
            }*/
          //clicking new space where previous click does not exist or 
            //a move was completed
            if (prev == null ) {
                clicked.setBlueBorder();
                prev = clicked;

                System.out.println("Condition 1");
            } 
          //clicking empty space or piece after previous click was empty space
            else if (clicked != prev && prev.isSelected() && prev.isEmpty()) {
                clicked.setBlueBorder();
                prev.setNullBorder();
                prev = clicked;
                System.out.println("Condition 2");
            } 
            
            else if (clicked != prev && prev.isSelected() //moving piece to empty space
                    && !prev.isEmpty() && clicked.isEmpty()) {
                
                    if(!prev.getPiece().movePiece(clicked.getPosX(), clicked.getPosY(), squares)){
                        clicked.setRedBorder();
                        prev.setNullBorder();
                        System.out.println("invalid");
                        prev = clicked;
                        return;
                }   
                    
                moveMove();
                changeTurn();
                System.out.println("Condition 3");

            } 
            
            else if (clicked == prev && prev.isSelected()) {//unclicking clicked space
                clicked.setNullBorder();
                prev = null;
                System.out.println("Condition 4");

                //when clicking pieces where previous click was piece
            } 
            
            else if (clicked != prev && !prev.isEmpty() && !clicked.isEmpty() && prev.isSelected()) {
                
                //case where prev click is same color as curr click
                    if(clicked.getPiece().getColor() == prev.getPiece().getColor()){
                        prev.setNullBorder();
                        clicked.setBlueBorder();
                        prev = clicked;
                        System.out.println("same color condition");
                    }   
                  //case where prev click is not the same color as curr click
                    //therefore, attack
                    else if(prev.getPiece().capture(clicked.getPosX(), clicked.getPosY(), squares)){                       {
                            System.out.println("capturing");
                            moveMove();
                            changeTurn();
                        }
                    
                }
                    
            }
            
            if(!whiteKing.isAlive()){
                JOptionPane.showMessageDialog(null, "Black Wins");
                turn = -1;
            }
            
            if(!blackKing.isAlive()){
                JOptionPane.showMessageDialog(null, "White Wins");       
                turn = -1;
            }
            validate();
            repaint();
           // System.out.println("End of loop");
            
        }

        @Override
        public void mousePressed(MouseEvent event) {

        }

        @Override
        public void mouseReleased(MouseEvent event) {

        }

        @Override
        public void mouseEntered(MouseEvent event) {
        }

        @Override
        public void mouseExited(MouseEvent event) {

        }
        
        public void moveMove(){
            clicked.removeAll();
            prev.setNullBorder();
            clicked.add(prev.getPiece());
            clicked.setPiece(prev.getPiece());
            prev.setPiece(null);
            prev.removeAll();
            clicked.setEmpty(false);
            prev.setEmpty(true);
            prev = null;
            
        }
        
        public void changeTurn(){
            if(turn == 1){
                turn = 0;
            }
            else{
                turn = 1;
            }
            
            return;
               
        }

    }


    /**
     * <p>
     * Sets up the board with white and black chess pieces.
     * </p>
     */
    public void setup() {
        setPieceBoard(0, 0, new BlackRook());
        setPieceBoard(0, 1, new BlackKnight());
        setPieceBoard(0, 2, new BlackBishop());
        setPieceBoard(0, 3, new BlackQueen());
        blackKing = new BlackKing();
        setPieceBoard(0, 4, blackKing);
        setPieceBoard(0, 5, new BlackBishop());
        setPieceBoard(0, 6, new BlackKnight());
        setPieceBoard(0, 7, new BlackRook());

        setPieceBoard(7, 0, new WhiteRook());
        setPieceBoard(7, 1, new WhiteKnight());
        setPieceBoard(7, 2, new WhiteBishop());
        setPieceBoard(7, 4, new WhiteQueen());
        whiteKing = new WhiteKing();
        setPieceBoard(7, 3, whiteKing);
        setPieceBoard(7, 5, new WhiteBishop());
        setPieceBoard(7, 6, new WhiteKnight());
        setPieceBoard(7, 7, new WhiteRook());

        for (int i = 0; i < CHESSNUMBER; i++) {
            setPieceBoard(1, i, new BlackPawn());
        }
        for (int i = 0; i < CHESSNUMBER; i++) {
            setPieceBoard(6, i, new WhitePawn());
        }

    }

    public void removeAllPieces(){
        for(int i = 0; i < CHESSNUMBER; i++){
            for(int j = 0; j < CHESSNUMBER; j++){
                squares[i][j].setPiece(null);
                squares[i][j].removeAll();
                squares[i][j].setEmpty(true);
                
            }
        }
        
        validate();
        repaint();
        
        
    }
    
    
    /**
     * <p>
     * Sets up an individual piece on the specified square.
     * </p>
     * 
     * @param posX
     *            horizontal position of the square.
     * @param posY
     *            vertical position of the square.
     * @param piece
     *            chess piece to be setup.
     */
    public void setPieceBoard(int posY, int posX, ChessPiece piece) {
        piece.setPos(posX, posY);
        squares[posX][posY].add(piece);
        squares[posX][posY].setEmpty(false);
        squares[posX][posY].setPiece(piece);
        serialPiece.add(piece);
    }

    public void setPieceBoardSerialized(int posX, int posY, ChessPiece piece) {
        piece.setPos(posX, posY);
        squares[posX][posY].add(piece);
        squares[posX][posY].setEmpty(false);
        squares[posX][posY].setPiece(piece);
        
    }
    public PieceSerializable getSerialPieces(){
        serialPiece.setTurn(turn);
        return serialPiece;
    }
    
    public void setupSerialized(PieceSerializable serPiece){
        ChessPiece tempPiece;
        removeAllPieces();
        for(int i = 0; i < 32; i++){
            tempPiece = serPiece.getPiece(i);
                if(tempPiece.isAlive()){
                    setPieceBoardSerialized(tempPiece.getPosX(), tempPiece.getPosY(), tempPiece);
                }
        }
        turn = serPiece.getTurn();
        
        validate();
        repaint();
    }
    
    public void resetAll(){

        removeAllPieces();
        for(int i = 0; i < CHESSNUMBER; i++){
            setPieceBoardSerialized(i, 0, serialPiece.getPiece(i));
            setPieceBoardSerialized(i, 1, serialPiece.getPiece(i+8+8));
            setPieceBoardSerialized(i, 6, serialPiece.getPiece(i+8+8+8));
        }

        for(int i = 7, j = 0; i >= 0; i--, j++){
            setPieceBoardSerialized(j, 7, serialPiece.getPiece(i+8));
        }       
        turn = 1;
        validate();
        repaint();
        
    }
}
