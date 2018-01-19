package ca.bcit.comp2526.a2a;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class ChessFrame implements ActionListener{

    private static ChessFrame frame;
    private JMenuItem item;
    private JMenuItem open;
    private JMenuItem save;
    private JMenuItem states;
    private JMenuItem newGame;
    private PieceSerializable serPiece;
    private Board board;
    private String filename;
    private static final int DIMENSION = 710;
    public ChessFrame(){
        JFrame frame  = new JFrame("Chess");
        JMenuBar menubar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        
        open = new JMenuItem("Open");
        save = new JMenuItem("Save");
        states = new JMenuItem("State");
        newGame = new JMenuItem("New Game");
        
        open.addActionListener(this);
        save.addActionListener(this);
        states.addActionListener(this);
        newGame.addActionListener(this);
        menu.add(open);
        menu.add(save);
        menu.add(states);
        menu.add(newGame);
        
        menubar.add(menu);
        
        frame.setJMenuBar(menubar);
        
        GridLayout layout = new GridLayout();
        frame.setLayout(layout);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(DIMENSION, DIMENSION);
        frame.setResizable(false);
        board = new Board();
        frame.add(board);
        frame.getContentPane();
        frame.setVisible(true);
        
        Dimension window = Toolkit.getDefaultToolkit().getScreenSize();
        int windowX = (int) ((window.getWidth() - frame.getWidth()) / 2);
        int windowY = (int) ((window.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(windowX, windowY);
    }

    public void actionPerformed(ActionEvent event) {
        item = (JMenuItem)event.getSource();
        
        if(item == save){
            try{
                serPiece = board.getSerialPieces();
                JFileChooser chooser = new JFileChooser(".");
                int read = chooser.showSaveDialog(null);
                
                if(read == JFileChooser.APPROVE_OPTION){
                    File file = chooser.getSelectedFile();
                    filename = file.getAbsolutePath();
                }
                
                else{
                    filename = null;
                    System.out.println("no such file");
                    return;
                }
                FileOutputStream f = new FileOutputStream(filename);
                ObjectOutput out = new ObjectOutputStream(f);
                
                out.writeObject(serPiece);
                out.flush();
                out.close();
                System.out.println("Saved");
            } catch(IOException e){
                e.printStackTrace();
            }
        }
        
        else if(item == open){
            try{
                JFileChooser chooser = new JFileChooser(".");
                int read = chooser.showOpenDialog(null);
                
                if(read == JFileChooser.APPROVE_OPTION){
                    File file = chooser.getSelectedFile();
                    filename = file.getAbsolutePath();
                }
                else{
                    filename = null;
                    System.out.println("no such file");
                    return;
                }
                    
                FileInputStream f2 = new FileInputStream(filename);
                
                ObjectInputStream in = new ObjectInputStream(f2);
                serPiece = (PieceSerializable)in.readObject();
                in.close();
                System.out.println("opened");
                board.setupSerialized(serPiece);
                
                //set up pieces
            } catch(IOException e) {
                e.printStackTrace();
            } catch(ClassNotFoundException e){
                e.printStackTrace();
            }
            //open file using serializataion
        }
        
        else if(item == states){
            serPiece = board.getSerialPieces();
            serPiece.printStat();
        }
        
        else if(item == newGame){
            board.resetAll();
        }
        
    }
    
    /**
     * <p>
     * Main class that tests the chess game
     * </p>
     * 
     * @param args
     *            arguments specified at command prompt.
     */
    public static void main(String args[]){
       frame = new ChessFrame();

    }

}
