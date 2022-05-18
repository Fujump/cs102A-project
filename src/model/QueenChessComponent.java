package model;

import controller.MoveController;
import view.Chessboard;
import view.ChessboardPoint;
import controller.ClickController;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QueenChessComponent extends ChessComponent{
    private static Image Queen_WHITE;
    private static Image Queen_BLACK;

    private Image queenImage;

    public void loadResource() throws IOException {
        if (Queen_WHITE == null) {
            Queen_WHITE = ImageIO.read(new File("./images/queen-white.png"));
        }

        if (Queen_BLACK == null) {
            Queen_BLACK = ImageIO.read(new File("./images/queen-black.png"));
        }
    }

    private void initiateQueenImage(ChessColor color) {
        try {
            loadResource();
            if (color == ChessColor.WHITE) {
                queenImage = Queen_WHITE;
            } else if (color == ChessColor.BLACK) {
                queenImage = Queen_BLACK;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public QueenChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color, ClickController listener, int size, Chessboard chessboard, MoveController moveController) {
        super(chessboardPoint, location, color, listener, size,chessboard,moveController);
        initiateQueenImage(color);
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
        }else if (source.getX() == destination.getX()) {
            int row = source.getX();
            for (int col = Math.min(source.getY(), destination.getY()) + 1;
                 col < Math.max(source.getY(), destination.getY()); col++) {
                if (!(chessComponents[row][col] instanceof EmptySlotComponent)) {
                    return false;
                }
            }
        } else if (source.getY() == destination.getY()) {
            int col = source.getY();
            for (int row = Math.min(source.getX(), destination.getX()) + 1;
                 row < Math.max(source.getX(), destination.getX()); row++) {
                if (!(chessComponents[row][col] instanceof EmptySlotComponent)) {
                    return false;
                }
            }
        }else{return false;}
        return true;
    }
    public ArrayList<ChessboardPoint> canMoveTo(Chessboard chessboard){
        ChessboardPoint source = getChessboardPoint();
        ArrayList<ChessboardPoint> canMoveTo=new ArrayList<>();
        for (int i = 0; i < 8; i++) {
           a: for (int j = 0; j < 8; j++) {
                if (chessboard.getChessComponents()[i][j].getChessColor() != this.getChessColor()) {
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
                    } else if (source.getX() == i) {
                        int row = source.getX();
                        for (int col = Math.min(source.getY(), j) + 1;
                             col < Math.max(source.getY(), j); col++) {
                            if (!(chessboard.getChessComponents()[row][col] instanceof EmptySlotComponent)) {
                                continue a;
                            }
                        }
                    } else if (source.getY() == j) {
                        int col = source.getY();
                        for (int row = Math.min(source.getX(), i) + 1;
                             row < Math.max(source.getX(), i); row++) {
                            if (!(chessboard.getChessComponents()[row][col] instanceof EmptySlotComponent)) {
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
        ChessboardPoint source = getChessboardPoint();
        ArrayList<ChessboardPoint> canMoveTo=new ArrayList<>();
        for (int i = 0; i < 8; i++) {
           a: for (int j = 0; j < 8; j++) {
                if (chessComponents[i][j].getChessColor() != this.getChessColor()) {
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
                    } else if (source.getX() == i) {
                        int row = source.getX();
                        for (int col = Math.min(source.getY(), j) + 1;
                             col < Math.max(source.getY(), j); col++) {
                            if (!(chessComponents[row][col] instanceof EmptySlotComponent)) {
                                continue a;
                            }
                        }
                    } else if (source.getY() == j) {
                        int col = source.getY();
                        for (int row = Math.min(source.getX(), i) + 1;
                             row < Math.max(source.getX(), i); row++) {
                            if (!(chessComponents[row][col] instanceof EmptySlotComponent)) {
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
        g.drawImage(queenImage, 0, 0, getWidth() , getHeight(), this);
        g.setColor(Color.BLACK);
        if (isSelected()) { // Highlights the model if selected.
            g.setColor(Color.RED);
            g.drawOval(0, 0, getWidth() , getHeight());
        }
    }
}
