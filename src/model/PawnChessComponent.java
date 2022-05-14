package model;

import view.ChessboardPoint;
import controller.ClickController;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class PawnChessComponent extends ChessComponent{
    private static Image Pawn_WHITE;
    private static Image Pawn_BLACK;

    private Image pawnImage;

    public void loadResource() throws IOException {
        if (Pawn_WHITE == null) {
            Pawn_WHITE = ImageIO.read(new File("./images/pawn-white.png"));
        }

        if (Pawn_BLACK == null) {
            Pawn_BLACK = ImageIO.read(new File("./images/pawn-black.png"));
        }
    }

    private void initiatePawnImage(ChessColor color) {
        try {
            loadResource();
            if (color == ChessColor.WHITE) {
                pawnImage = Pawn_WHITE;
            } else if (color == ChessColor.BLACK) {
                pawnImage = Pawn_BLACK;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PawnChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color, ClickController listener, int size) {
        super(chessboardPoint, location, color, listener, size);
        initiatePawnImage(color);
    }

    public boolean canMoveTo(ChessComponent[][] chessComponents, ChessboardPoint destination) {
        ChessboardPoint source = getChessboardPoint();
        if (this.getChessColor()==ChessColor.BLACK){
            if (source.getX()==1){
                if ((destination.getX()-source.getX()==1||destination.getX()-source.getX()==2)&&(destination.getY()==source.getY())){
                    return true;}
                else{return false;}
            }else{
                if (((destination.getY()== source.getY()+1)&&(destination.getX()- source.getX()==1))&&
                        !(chessComponents[source.getX()+1][source.getY()+1] instanceof EmptySlotComponent)
                        ){
                    return true;
                }else if (((destination.getY()== source.getY()-1)&&(destination.getX()- source.getX()==1))&&
                        !(chessComponents[source.getX()+1][source.getY()-1] instanceof EmptySlotComponent)){
                    return true;
                }else if ((destination.getY()==source.getY())&&(destination.getX()- source.getX()==1)){
                    return true;
                }else {return false;}
            }
        }
        if (this.getChessColor()==ChessColor.WHITE){
            if (source.getX()==6){
                if ((destination.getX()-source.getX()==-1||destination.getX()-source.getX()==-2)&&(destination.getY()==source.getY())){
                    return true;}
                else{return false;}
            }else{
                if (((destination.getY()== source.getY()+1)&&(destination.getX()- source.getX()==-1))&&
                !(chessComponents[source.getX()-1][source.getY()+1] instanceof EmptySlotComponent)){
                    return true;
                }else if (((destination.getY()== source.getY()-1)&&(destination.getX()- source.getX()==-1))&&
                !(chessComponents[source.getX()-1][source.getY()-1] instanceof EmptySlotComponent)){
                    return true;
                }else if ((destination.getY()==source.getY())&&(destination.getX()- source.getX()==-1)){
                    return true;
                }else {return false;}
            }
        }
        return false;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // g.drawImage(rookImage, 0, 0, getWidth() - 13, getHeight() - 20, this);
        g.drawImage(pawnImage, 0, 0, getWidth() , getHeight(), this);
        g.setColor(Color.BLACK);
        if (isSelected()) { // Highlights the model if selected.
            g.setColor(Color.RED);
            g.drawOval(0, 0, getWidth() , getHeight());
        }
    }
}
