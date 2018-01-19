package ca.bcit.comp2526.a2a;

import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class BlackPawn extends ChessPiece {

    BlackPawn() {
        super(new ImageIcon("bpawn.png"), 0);
    }

    public boolean capture(int posX, int posY, Square[][] squares) {
        //System.out.println("Moving to: " + posX + ", " + posY);
        //System.out.println("Current Position: " + this.getPosX() + ", " + this.getPosY());
        if((posX == this.getPosX()+1 || posX == this.getPosX() - 1) && posY == this.getPosY() + 1){

            System.out.println("in process of caturing");
            kill(posX, posY, squares);
            
            return true;
        }
        return false;
     
    }


    public boolean movePiece(int posX, int posY, Square[][] squares) {
        if(validify(posX, posY, squares)){
            setPos(posX, posY);
            return true;
        }
        
        return false;
    }
    
    public boolean validify(int posX, int posY, Square[][] squares){
       // System.out.println("current x,y:" + this.getPosX() + ", " + this.getPosY() );
        //System.out.println("move x,y:" + posX + ", " + posY);

        if(posX == this.getPosX() && this.getPosY() == 1 && posY == this.getPosY() + 2){
            if(squares[getPosX()][getPosY() + 1].isEmpty()){
                return true;                
            }
        }
        
        if(posX == this.getPosX() && posY == this.getPosY() + 1){
            if(squares[getPosX()][getPosY() + 1].isEmpty()){
                return true;                
            }
        }
        
        return false;
    }

    public String toString(){
        return "Black Pawn";
    }


}
