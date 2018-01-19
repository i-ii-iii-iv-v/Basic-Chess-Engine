package ca.bcit.comp2526.a2a;

import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class WhiteQueen extends ChessPiece {


    WhiteQueen() {
        super(new ImageIcon("wqueen.png"), 1);
    }
    
    public boolean capture(int posX, int posY, Square[][] squares) {
        squares[posX][posY].setEmpty(true);//make it temporary empty so that i can check to see 
                                           //if i can move to this space
        if(validify(posX, posY, squares)){
            kill(posX, posY, squares);
            return true;
        }
        else{
            squares[posX][posY].setEmpty(false);
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
        
        if(posX == this.getPosX() && posY < this.getPosY()){  
            System.out.println("Entering Rook condition 1");
            for(int i = this.getPosY() - 1; i >= posY; i-- ){
                if(!squares[posX][i].isEmpty()){
                    System.out.println("return false: " + i);
                    return false;
                }
               
            }
            
            return true;
        }
        
      //case where point to move to is bigger in y axis
        if(posX == this.getPosX() && posY > this.getPosY()){   
            System.out.println("Entering Rook condition 2");
            for(int i = this.getPosY() + 1; i <= posY; i++ ){
                if(!squares[posX][i].isEmpty()){
                    return false;
                }
                
            }
            
            return true;
        }
        
        //case where point to move to is bigger in x axis
        if(posY == this.getPosY() && posX > this.getPosX()){
            System.out.println("Entering Rook condition 3");
            for(int i = this.getPosX()+1; i <= posX; i++){
                if(!squares[i][posY].isEmpty()){
                    return false;
                }
            }
            
            return true;
        }
        
        //case where point to move to is smaller in x axis
        if(posY == this.getPosY() && posX < this.getPosX()){
            System.out.println("Entering Rook condition 4");
            for(int i = this.getPosX()-1; i >= posX; i--){
                if(!squares[i][posY].isEmpty()){
                    return false;
                }
            }
            
            return true;
        }
        
        return false;
    }

    public String toString(){
        return "White Queen";
    }


}
