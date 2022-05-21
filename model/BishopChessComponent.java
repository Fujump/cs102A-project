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

public class BishopChessComponent extends ChessComponent {
    private static Image Bishop_WHITE;
    private static Image Bishop_BLACK;

    private Image bishopImage;

    public void loadResource() throws IOException {
        if (Bishop_WHITE == null) {
            Bishop_WHITE = ImageIO.read(new File("./images/bishop-white.png"));
        }

        if (Bishop_BLACK == null) {
            Bishop_BLACK = ImageIO.read(new File("./images/bishop-black.png"));
        }
    }

    private void initiateBishopImage(ChessColor color) {
        try {
            loadResource();
            if (color == ChessColor.WHITE) {
                bishopImage = Bishop_WHITE;
            } else if (color == ChessColor.BLACK) {
                bishopImage = Bishop_BLACK;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BishopChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color, ClickController listener, int size,Chessboard chessboard) {
        super(chessboardPoint, location, color, listener, size,chessboard);
        initiateBishopImage(color);
    }

    public boolean canMoveTo(ChessComponent[][] chessComponents, ChessboardPoint destination) {
        ChessboardPoint source = getChessboardPoint();
        if (Math.abs(source.getX()-destination.getX())==Math.abs(source.getY()-destination.getY())){
            int signDetaX,signDetaY;
            if (destination.getX()>=source.getX()){signDetaX=1;}else{signDetaX=-1;}
            if (destination.getY()>=source.getY()){signDetaY=1;}else{signDetaY=-1;}
            for (int i = 1; i < Math.abs(source.getX()-destination.getX()); i++) {
                if (!(chessComponents[source.getX()+signDetaX*i][source.getY()+signDetaY*i] instanceof EmptySlotComponent)){
                    return false;
                }
            }
        }else{return false;}
        return true;
    }
    public ArrayList<ChessboardPoint> canMoveTo(Chessboard chessboard){
        ChessboardPoint source=getChessboardPoint();
        ArrayList<ChessboardPoint> canMoveTo=new ArrayList<>();
        for (int i = 0; i < 8; i++) {
           a: for (int j = 0; j < 8; j++) {
                if (chessboard.getChessComponents()[i][j].getChessColor()!=this.getChessColor()) {
                    if (Math.abs(source.getX() - i) == Math.abs(source.getY() - j)) {
                        int signDetaX, signDetaY;
                        if (i >= source.getX()) {
                            signDetaX = 1;
                        } else {
                            signDetaX = -1;
                        }
                        if (j >= source.getY()) {
                            signDetaY = 1;
                        } else {
                            signDetaY = -1;
                        }
                        for (int k = 1; k < Math.abs(source.getX() - i); k++) {
                            if (!(chessboard.getChessComponents()[source.getX() + signDetaX * k][source.getY() + signDetaY * k] instanceof EmptySlotComponent)) {
                                continue a;
                            }
                        }
                    } else {
                        continue;
                    }
                    canMoveTo.add(new ChessboardPoint(i, j));
                }
            }
        }
        return canMoveTo;
    }

    @Override
    public ArrayList<ChessboardPoint> canMoveTo(ChessComponent[][] chessComponents) {
        ChessboardPoint source=getChessboardPoint();
        ArrayList<ChessboardPoint> canMoveTo=new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            a:for (int j = 0; j < 8; j++) {
                if (chessComponents[i][j].getChessColor()!=this.getChessColor()) {
                    if (Math.abs(source.getX() - i) == Math.abs(source.getY() - j)) {
                        int signDetaX, signDetaY;
                        if (i >= source.getX()) {
                            signDetaX = 1;
                        } else {
                            signDetaX = -1;
                        }
                        if (j >= source.getY()) {
                            signDetaY = 1;
                        } else {
                            signDetaY = -1;
                        }
                        for (int k = 1; k < Math.abs(source.getX() - i); k++) {
                            if (!(chessComponents[source.getX() + signDetaX * k][source.getY() + signDetaY * k] instanceof EmptySlotComponent)) {
                                continue a;
                            }
                        }
                    } else {
                        continue;
                    }
                    canMoveTo.add(new ChessboardPoint(i, j));
                }
            }
        }
        return canMoveTo;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // g.drawImage(rookImage, 0, 0, getWidth() - 13, getHeight() - 20, this);
        g.drawImage(bishopImage, 0, 0, getWidth() , getHeight(), this);
        g.setColor(Color.BLACK);
        if (isSelected()) { // Highlights the model if selected.
            g.setColor(Color.RED);
            g.drawOval(0, 0, getWidth() , getHeight());
        }
    }
}
