package ca.bcit.comp2526.a2a;

import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class BlackKnight extends ChessPiece {

    BlackKnight() {
        super(new ImageIcon("bknight.png"), 0);
    }

    public boolean capture(int posX, int posY, Square[][] squares) {
        if(validify(posX, posY, squares)){
            kill(posX, posY, squares);
            return true;
        }
        return false;
     
    }
    
    //return 0 open unsuccessful, 1 upon valid
    public boolean movePiece(int posX, int posY, Square[][] squares) {
        if(validify(posX, posY, squares)){
            setPos(posX, posY);
            return true;
        }
        
        return false;
    }
    
    public boolean validify(int posX, int posY, Square[][] squares){
        System.out.println("current x,y:" + this.getPosX() + ", " + this.getPosY() );
        System.out.println("move x,y:" + posX + ", " + posY);
        if(posX == this.getPosX() + 2 && (posY == this.getPosY() + 1 || posY == this.getPosY() - 1)){
            return true;
        }
        
        if(posX == this.getPosX() - 2 && (posY == this.getPosY() + 1 || posY == this.getPosY() - 1)){
            return true;
        }

        if(posY == this.getPosY() + 2 && (posX == this.getPosX() + 1 || posX == this.getPosX() - 1)){
            return true;
            
        }
        
        if(posY == this.getPosY() - 2 && (posX == this.getPosX() + 1 || posX == this.getPosX() - 1)){
            return true;
        }
        return false;
    }
    
    public String toString(){
        return "Black Knight";
    }


}
