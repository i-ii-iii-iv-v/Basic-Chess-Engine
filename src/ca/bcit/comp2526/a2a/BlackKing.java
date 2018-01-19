package ca.bcit.comp2526.a2a;

import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class BlackKing extends ChessPiece {

    BlackKing() {
        super(new ImageIcon("bking.png"), 0);
    }

    public boolean capture(int posX, int posY, Square[][] squares) {
        if((posY == getPosY() && posX == getPosX()+1) || (posY == getPosY() && posX == getPosX()-1)
                || (posX == getPosX() && posY == getPosY()+1) || (posX == getPosX() && posY == getPosY()-1)){
            
            System.out.println("in process of caturing");
            kill(posX, posY, squares);
            
            return true;
        }
        
        if((posX == this.getPosX()+1 || posX == this.getPosX() - 1) &&
                (posY == this.getPosY() + 1 || posY == this.getPosY() - 1)){
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
        //System.out.println("current x,y:" + this.getPosX() + ", " + this.getPosY() );
        //System.out.println("move x,y:" + posX + ", " + posY);
        int xMoveAbsolute = Math.abs(this.getPosX() - posX);
        int yMoveAbsolute = Math.abs(this.getPosY() - posY);
        
        if(xMoveAbsolute == yMoveAbsolute && xMoveAbsolute == 1){
            //moving to 1st quadrant, point x is greater, point y is smaller than current
            if(this.getPosX() < posX && this.getPosY() > posY){
                if(!squares[posX][posY].isEmpty()){
                    return false;
                }
               return true; 
            }
            
            //moving to 2nd quadrant, point x is smaller, point y is smaller than current
            if(this.getPosX() > posX && this.getPosY() > posY){
                    if(!squares[posX][posY].isEmpty()){
                        return false;

                }
               return true; 
            }
            
            //moving to 3rd quadrant, point x is smaller, point y is bigger than current
            if(this.getPosX() > posX && this.getPosY() < posY){
                    if(!squares[posX][posY].isEmpty()){
                        return false;
                }
               return true; 
            }
            
            //moving to 4th quadrant, point x is bigger, point y is bigger than current
            if(this.getPosX() < posX && this.getPosY() < posY){
                    if(!squares[posX][posY].isEmpty()){
                        return false;
                    }
                return true; 
            }
        }
        
        if(posX == this.getPosX() && posY == this.getPosY() + 1){
                if(!squares[posX][posY].isEmpty()){
                    return false;
                }
            return true;
        }
        
      //case where point to move to is bigger in y axis
        if(posX == this.getPosX() && posY == this.getPosY() - 1){   
                if(!squares[posX][posY].isEmpty()){
                    return false;
                }        
            return true;
        }
        
        //case where point to move to is bigger in x axis
        if(posY == this.getPosY() && posX == this.getPosX() + 1){
                if(!squares[posX][posY].isEmpty()){
                    return false;
                }            
            return true;
        }
        
        //case where point to move to is smaller in x axis
        if(posY == this.getPosY() && posX == this.getPosX() - 1){
                if(!squares[posX][posY].isEmpty()){
                    return false;
                }
            
            return true;
        }
        
        return false;
    }  
    
    public String toString(){
        return "Black King";
    }
    
    
}





