package ca.bcit.comp2526.a2a;

import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class BlackRook extends ChessPiece {

    BlackRook() {
        super(new ImageIcon("brook.png"), 0);
    }

    public boolean capture(int posX, int posY, Square[][] squares) {
        System.out.println("Moving to: " + posX + ", " + posY);
        System.out.println("Current Position: " + this.getPosX() + ", " + this.getPosY());
        
        if((posX == this.getPosX() && posY > this.getPosY())){//rook moves downward
            if(validify(posX, posY - 1, squares)){
                System.out.println("in process of caturing");
                kill(posX, posY, squares);
                
                return true;
            }
            
        }
        
        if((posX == this.getPosX() && posY < this.getPosY())){//rook moves upward
            if(validify(posX, posY + 1, squares)){
                System.out.println("in process of caturing");
                kill(posX, posY, squares);
                
                return true;
            }
            
        }
        
        if((posY == this.getPosY() && posX < this.getPosX())){//rook moves left
            if(validify(posX + 1, posY, squares)){
                System.out.println("in process of caturing");
                kill(posX, posY, squares);
                
                return true;
            }
            
        }
        
        if((posY == this.getPosY() && posX > this.getPosX())){//rook moves right
            if(validify(posX - 1, posY, squares)){
                System.out.println("in process of caturing");
                kill(posX, posY, squares);
                
                return true;
            }
            
        }
        
        if((posY == getPosY() && posX == getPosX()+1) || (posY == getPosY() && posX == getPosX()-1)
                || (posX == getPosX() && posY == getPosY()+1) || (posX == getPosX() && posY == getPosY()-1)){
            
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
        //System.out.println("current x,y:" + this.getPosX() + ", " + this.getPosY() );
       // System.out.println("move x,y:" + posX + ", " + posY);

        //case where point to move to is smaller in y axis
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
        return "Black Rook";
    }

}
