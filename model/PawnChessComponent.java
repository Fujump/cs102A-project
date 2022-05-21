package model;

import view.Chessboard;
import view.ChessboardPoint;
import controller.ClickController;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    public PawnChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color, ClickController listener, int size,Chessboard chessboard) {
        super(chessboardPoint, location, color, listener, size,chessboard);
        initiatePawnImage(color);
    }

    public boolean canMoveTo(ChessComponent[][] chessComponents, ChessboardPoint destination) {
        ChessboardPoint source = getChessboardPoint();
        if (this.getChessColor()==ChessColor.BLACK){
            if (source.getX()==1){
                if (((destination.getY() == source.getY() + 1) && (destination.getX() - source.getX() == 1)) &&
                        (chessComponents[source.getX() + 1][source.getY() + 1] .getChessColor()==ChessColor.WHITE)) {
                    return true;
                } else if (((destination.getY() == source.getY() - 1) && (destination.getX() - source.getX() == 1)) &&
                        (chessComponents[source.getX() + 1][source.getY() - 1] .getChessColor()==ChessColor.WHITE)) {
                    return true;
                }else if (((destination.getX()-source.getX()==1||(destination.getX()-source.getX()==2
                        &&chessComponents[source.getX()+1][source.getY()] instanceof EmptySlotComponent))&&(destination.getY()==source.getY()))
                        &&(chessComponents[destination.getX()][destination.getY()] instanceof EmptySlotComponent)){
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
                }else if (((destination.getY()==source.getY())&&(destination.getX()- source.getX()==1))
                        &&(chessComponents[destination.getX()][destination.getY()] instanceof EmptySlotComponent)){
                    return true;
                }else {return false;}
            }
        }
        if (this.getChessColor()==ChessColor.WHITE){
            if (source.getX()==6){
                if (((destination.getY() == source.getY() + 1) && (destination.getX() - source.getX() == -1)) &&
                        (chessComponents[source.getX() - 1][source.getY() + 1] .getChessColor()==ChessColor.BLACK)) {
                    return true;
                } else if (((destination.getY() == source.getY() - 1) && (destination.getX() - source.getX() == -1)) &&
                        (chessComponents[source.getX() - 1][source.getY() - 1] .getChessColor()==ChessColor.BLACK)) {
                    return true;
                }else if (((destination.getX()-source.getX()==-1||(destination.getX()-source.getX()==-2&&
                        chessComponents[source.getX()-1][source.getY()] instanceof EmptySlotComponent))&&(destination.getY()==source.getY()))
                        &&(chessComponents[destination.getX()][destination.getY()] instanceof EmptySlotComponent)){
                    return true;}
                else{return false;}
            }else{
                if (((destination.getY()== source.getY()+1)&&(destination.getX()- source.getX()==-1))&&
                !(chessComponents[source.getX()-1][source.getY()+1] instanceof EmptySlotComponent)){
                    return true;
                }else if (((destination.getY()== source.getY()-1)&&(destination.getX()- source.getX()==-1))&&
                !(chessComponents[source.getX()-1][source.getY()-1] instanceof EmptySlotComponent)){
                    return true;
                }else if (((destination.getY()==source.getY())&&(destination.getX()- source.getX()==-1))
                        &&(chessComponents[destination.getX()][destination.getY()] instanceof EmptySlotComponent)){
                    return true;
                }else {return false;}
            }
        }
        return false;
    }
    public ArrayList<ChessboardPoint> canMoveTo(Chessboard chessboard){
        ChessboardPoint source = getChessboardPoint();
        ArrayList<ChessboardPoint> canMoveTo=new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (chessboard.getChessComponents()[i][j].getChessColor() != this.getChessColor()) {
                    if (this.getChessColor() == ChessColor.BLACK) {
                        if (source.getX() == 1) {
                            if (((j == source.getY() + 1) && (i - source.getX() == 1)) &&
                                    (chessboard.getChessComponents()[source.getX() + 1][source.getY() + 1] .getChessColor()==ChessColor.WHITE)) {
                                canMoveTo.add(new ChessboardPoint(i, j));
                            } else if (((j == source.getY() - 1) && (i - source.getX() == 1)) &&
                                    (chessboard.getChessComponents()[source.getX() + 1][source.getY() - 1] .getChessColor()==ChessColor.WHITE)) {
                                canMoveTo.add(new ChessboardPoint(i, j));
                            }else if ((i - source.getX() == 1 || (i - source.getX() == 2&&
                                    chessboard.getChessComponents()[source.getX()+1][source.getY()] instanceof EmptySlotComponent)) && (j == source.getY())
                                    &&(chessboard.getChessComponents()[i][j] instanceof EmptySlotComponent)) {
                                canMoveTo.add(new ChessboardPoint(i, j));
                            } else {
                                continue;
                            }
                        } else {
                            if (((j == source.getY() + 1) && (i - source.getX() == 1)) &&
                                    !(chessboard.getChessComponents()[source.getX() + 1][source.getY() + 1] instanceof EmptySlotComponent)
                            ) {
                                canMoveTo.add(new ChessboardPoint(i, j));
                            } else if (((j == source.getY() - 1) && (i - source.getX() == 1)) &&
                                    !(chessboard.getChessComponents()[source.getX() + 1][source.getY() - 1] instanceof EmptySlotComponent)) {
                                canMoveTo.add(new ChessboardPoint(i, j));
                            } else if ((j == source.getY()) && (i - source.getX() == 1)
                                    &&(chessboard.getChessComponents()[i][j] instanceof EmptySlotComponent)) {
                                canMoveTo.add(new ChessboardPoint(i, j));
                            } else {
                                continue;
                            }
                        }
                    }
                    if (this.getChessColor() == ChessColor.WHITE) {
                        if (source.getX() == 6) {
                            if (((j == source.getY() + 1) && (i - source.getX() == -1)) &&
                                    (chessboard.getChessComponents()[source.getX() - 1][source.getY() + 1] .getChessColor()==ChessColor.BLACK)) {
                                canMoveTo.add(new ChessboardPoint(i, j));
                            } else if (((j == source.getY() - 1) && (i - source.getX() == -1)) &&
                                    (chessboard.getChessComponents()[source.getX() - 1][source.getY() - 1] .getChessColor()==ChessColor.BLACK)) {
                                canMoveTo.add(new ChessboardPoint(i, j));
                            }else if ((i - source.getX() == -1 ||( i - source.getX() == -2&&
                                    chessboard.getChessComponents()[source.getX()-1][source.getY()] instanceof EmptySlotComponent)) && (j == source.getY())
                                    &&(chessboard.getChessComponents()[i][j] instanceof EmptySlotComponent)) {
                                canMoveTo.add(new ChessboardPoint(i, j));
                            } else {
                                continue;
                            }
                        } else {
                            if (((j == source.getY() + 1) && (i - source.getX() == -1)) &&
                                    !(chessboard.getChessComponents()[source.getX() - 1][source.getY() + 1] instanceof EmptySlotComponent)) {
                                canMoveTo.add(new ChessboardPoint(i, j));
                            } else if (((j == source.getY() - 1) && (i - source.getX() == -1)) &&
                                    !(chessboard.getChessComponents()[source.getX() - 1][source.getY() - 1] instanceof EmptySlotComponent)) {
                                canMoveTo.add(new ChessboardPoint(i, j));
                            } else if ((j == source.getY()) && (i - source.getX() == -1)
                                    &&(chessboard.getChessComponents()[i][j] instanceof EmptySlotComponent)) {
                                canMoveTo.add(new ChessboardPoint(i, j));
                            } else {
                                continue;
                            }
                        }
                    }
                    continue;
                }
            }
        }
        return canMoveTo;
    }

    @Override
    public ArrayList<ChessboardPoint> canMoveTo(ChessComponent[][] chessComponents) {
        ChessboardPoint source = getChessboardPoint();
        ArrayList<ChessboardPoint> canMoveTo=new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (chessComponents[i][j].getChessColor() != this.getChessColor()) {
                    if (this.getChessColor() == ChessColor.BLACK) {
                        if (source.getX() == 1) {
                            if (((j == source.getY() + 1) && (i - source.getX() == 1)) &&
                                    (chessComponents[source.getX() + 1][source.getY() + 1] .getChessColor()==ChessColor.WHITE)) {
                                canMoveTo.add(new ChessboardPoint(i, j));
                            } else if (((j == source.getY() - 1) && (i - source.getX() == 1)) &&
                                    (chessComponents[source.getX() + 1][source.getY() - 1] .getChessColor()==ChessColor.WHITE)) {
                                canMoveTo.add(new ChessboardPoint(i, j));
                            }else if ((i - source.getX() == 1 || (i - source.getX() == 2&&
                                    chessComponents[source.getX()+1][source.getY()] instanceof EmptySlotComponent)) && (j == source.getY())
                                    &&(chessComponents[i][j] instanceof EmptySlotComponent)) {
                                canMoveTo.add(new ChessboardPoint(i, j));
                            } else {
                                continue;
                            }
                        } else {
                            if (((j == source.getY() + 1) && (i - source.getX() == 1)) &&
                                    !(chessComponents[source.getX() + 1][source.getY() + 1] instanceof EmptySlotComponent)
                            ) {
                                canMoveTo.add(new ChessboardPoint(i, j));
                            } else if (((j == source.getY() - 1) && (i - source.getX() == 1)) &&
                                    !(chessComponents[source.getX() + 1][source.getY() - 1] instanceof EmptySlotComponent)) {
                                canMoveTo.add(new ChessboardPoint(i, j));
                            } else if ((j == source.getY()) && (i - source.getX() == 1)
                                    &&(chessComponents[i][j] instanceof EmptySlotComponent)) {
                                canMoveTo.add(new ChessboardPoint(i, j));
                            } else {
                                continue;
                            }
                        }
                    }
                    if (this.getChessColor() == ChessColor.WHITE) {
                        if (source.getX() == 6) {
                            if (((j == source.getY() + 1) && (i - source.getX() == -1)) &&
                                    (chessComponents[source.getX() - 1][source.getY() + 1] .getChessColor()==ChessColor.BLACK)) {
                                canMoveTo.add(new ChessboardPoint(i, j));
                            } else if (((j == source.getY() - 1) && (i - source.getX() == -1)) &&
                                    (chessComponents[source.getX() - 1][source.getY() - 1] .getChessColor()==ChessColor.BLACK)) {
                                canMoveTo.add(new ChessboardPoint(i, j));
                            }else if ((i - source.getX() == -1 || (i - source.getX() == -2&&
                                    chessComponents[source.getX()-1][source.getY()] instanceof EmptySlotComponent)) && (j == source.getY())
                                    &&(chessComponents[i][j] instanceof EmptySlotComponent)) {
                                canMoveTo.add(new ChessboardPoint(i, j));
                            } else {
                                continue;
                            }
                        } else {
                            if (((j == source.getY() + 1) && (i - source.getX() == -1)) &&
                                    !(chessComponents[source.getX() - 1][source.getY() + 1] instanceof EmptySlotComponent)) {
                                canMoveTo.add(new ChessboardPoint(i, j));
                            } else if (((j == source.getY() - 1) && (i - source.getX() == -1)) &&
                                    !(chessComponents[source.getX() - 1][source.getY() - 1] instanceof EmptySlotComponent)) {
                                canMoveTo.add(new ChessboardPoint(i, j));
                            } else if ((j == source.getY()) && (i - source.getX() == -1)
                                    &&(chessComponents[i][j] instanceof EmptySlotComponent)) {
                                canMoveTo.add(new ChessboardPoint(i, j));
                            } else {
                                continue;
                            }
                        }
                    }
                    continue;
                }
            }
        }
        return canMoveTo;
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
