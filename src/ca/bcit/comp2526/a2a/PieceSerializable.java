package ca.bcit.comp2526.a2a;

import java.io.Serializable;

@SuppressWarnings("serial")
public class PieceSerializable implements Serializable {

    int turn;
    ChessPiece piece[] = new ChessPiece[32];
    public static int counter = 0;
    
    public void add(ChessPiece pieceStoring){
        piece[counter] = pieceStoring;
        counter++;
    }
    
    public void setTurn(int turn){
        this.turn = turn;
    }
    
    public int getTurn(){
        return turn;
    }
    
    public void printStat(){
        for(int value = 0; value < 32; value++){
            System.out.println(piece[value].getPosX() + ", " + piece[value].getPosY() + "\t" + "state: " 
                    + piece[value].isAlive() );
        }
    }
    
    public ChessPiece getPiece(int i){
        return piece[i];
    }

    
    
}
