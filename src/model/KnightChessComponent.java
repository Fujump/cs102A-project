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

public class KnightChessComponent extends ChessComponent{
    private static Image Knight_WHITE;
    private static Image Knight_BLACK;

    private Image knightImage;

    public void loadResource() throws IOException {
        if (Knight_WHITE == null) {
            Knight_WHITE = ImageIO.read(new File("./images/knight-white.png"));
        }

        if (Knight_BLACK == null) {
            Knight_BLACK = ImageIO.read(new File("./images/knight-black.png"));
        }
    }

    private void initiateKnightImage(ChessColor color) {
        try {
            loadResource();
            if (color == ChessColor.WHITE) {
                knightImage = Knight_WHITE;
            } else if (color == ChessColor.BLACK) {
                knightImage = Knight_BLACK;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public KnightChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color, ClickController listener, int size, Chessboard chessboard, MoveController moveController) {
        super(chessboardPoint, location, color, listener, size,chessboard,moveController);
        initiateKnightImage(color);
    }

    public boolean canMoveTo(ChessComponent[][] chessComponents, ChessboardPoint destination) {
        ChessboardPoint source = getChessboardPoint();
        if ((Math.abs(source.getX()-destination.getX())==1&&Math.abs(source.getY()-destination.getY())==2)||
                (Math.abs(source.getX()-destination.getX())==2&&Math.abs(source.getY()-destination.getY())==1)){
            return true;
        }
        return false;
    }
    public ArrayList<ChessboardPoint> canMoveTo(Chessboard chessboard){
        ChessboardPoint source = getChessboardPoint();
        ArrayList<ChessboardPoint> canMoveTo=new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (chessboard.getChessComponents()[i][j].getChessColor() != this.getChessColor()) {
                    if ((Math.abs(source.getX() - i) == 1 && Math.abs(source.getY() - j) == 2) ||
                            (Math.abs(source.getX() - i) == 2 && Math.abs(source.getY() - j) == 1)) {
                        canMoveTo.add(new ChessboardPoint(i, j));
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
                    if ((Math.abs(source.getX() - i) == 1 && Math.abs(source.getY() - j) == 2) ||
                            (Math.abs(source.getX() - i) == 2 && Math.abs(source.getY() - j) == 1)) {
                        canMoveTo.add(new ChessboardPoint(i, j));
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
        g.drawImage(knightImage, 0, 0, getWidth() , getHeight(), this);
        g.setColor(Color.BLACK);
        if (isSelected()) { // Highlights the model if selected.
            g.setColor(Color.RED);
            g.drawOval(0, 0, getWidth() , getHeight());
        }
    }
}
