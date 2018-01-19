package ca.bcit.comp2526.a2a;

import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class WhiteBishop extends ChessPiece {

    private int moveToX;
    private int moveToY;
    
    WhiteBishop() {
        super(new ImageIcon("wbishop.png"), 1);
    }



    public boolean movePiece(int posX, int posY, Square[][] squares) {
        if(validify(posX, posY, squares)){
            setPos(posX, posY);
            return true;
        }
        
        return false;
    }
    
    public boolean capture(int posX, int posY, Square[][] squares) {
        System.out.println("Moving to: " + posX + ", " + posY);
        System.out.println("Current Position: " + this.getPosX() + ", " + this.getPosY());
        captureValidify(posX, posY);
        
        if((posX == this.getPosX()+1 || posX == this.getPosX() - 1) &&
                (posY == this.getPosY() + 1 || posY == this.getPosY() - 1)){
            kill(posX, posY, squares);
            return true;
        }
        
        if(validify(moveToX, moveToY, squares)){
            kill(posX, posY, squares);            
            return true;
        }
        return false;
     
    }
    

    
    public void captureValidify(int posX, int posY){
        if(this.getPosX() < posX && this.getPosY() > posY){//quadrant 1
            moveToX = posX - 1;
            moveToY = posY + 1;
            return;
        }
        
        if(this.getPosX() > posX && this.getPosY() > posY){//quadrant 2
            moveToX = posX + 1;
            moveToY = posY + 1;
            return;
        }
        
        if(this.getPosX() > posX && this.getPosY() < posY){//quadrant 3
            moveToX = posX + 1;
            moveToY = posY - 1;
            return;
        }
        
        if(this.getPosX() < posX && this.getPosY() < posY){//quadrant 4
            moveToX = posX -1;
            moveToY = posY -1;
            
        }
        
            
    }
    
    public boolean validify(int posX, int posY, Square[][] squares){
        //System.out.println("current x,y:" + this.getPosX() + ", " + this.getPosY() );
        //System.out.println("move x,y:" + posX + ", " + posY);
        int xMoveAbsolute = Math.abs(this.getPosX() - posX);
        int yMoveAbsolute = Math.abs(this.getPosY() - posY);
        
        if(xMoveAbsolute == yMoveAbsolute){
            //moving to 1st quadrant, point x is greater, point y is smaller than current
            if(this.getPosX() < posX && this.getPosY() > posY){
                for(int i = getPosX() + 1, j = getPosY() - 1; i <= posX; i++, j--){
                    if(!squares[i][j].isEmpty()){
                        return false;
                    }
                }
               return true; 
            }
            
            //moving to 2nd quadrant, point x is smaller, point y is smaller than current
            if(this.getPosX() > posX && this.getPosY() > posY){
                for(int i = getPosX() - 1, j = getPosY() - 1; i >= posX; i--, j--){
                    if(!squares[i][j].isEmpty()){
                        return false;
                    }
                }
               return true; 
            }
            
            //moving to 3rd quadrant, point x is smaller, point y is bigger than current
            if(this.getPosX() > posX && this.getPosY() < posY){
                for(int i = getPosX() - 1, j = getPosY() + 1; i >= posX; i--, j++){
                    if(!squares[i][j].isEmpty()){
                        return false;
                    }
                }
               return true; 
            }
            
            //moving to 4th quadrant, point x is bigger, point y is bigger than current
            if(this.getPosX() < posX && this.getPosY() < posY){
                for(int i = getPosX() + 1, j = getPosY() + 1; i <= posX; i++, j++){
                    if(!squares[i][j].isEmpty()){
                        return false;
                    }
                }
               return true; 
            }
        }
        
        return false;
    }

    public String toString(){
        return "White Bishop";
    }

}
