package model;

import controller.MoveController;
import view.ChessboardPoint;
import controller.ClickController;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class KingChessComponent extends ChessComponent{
    private static Image King_WHITE;
    private static Image King_BLACK;

    private Image kingImage;

    public void loadResource() throws IOException {
        if (King_WHITE == null) {
            King_WHITE = ImageIO.read(new File("./images/king-white.png"));
        }

        if (King_BLACK == null) {
            King_BLACK = ImageIO.read(new File("./images/king-black.png"));
        }
    }

    private void initiateKingImage(ChessColor color) {
        try {
            loadResource();
            if (color == ChessColor.WHITE) {
                kingImage = King_WHITE;
            } else if (color == ChessColor.BLACK) {
                kingImage = King_BLACK;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public KingChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color, ClickController listener, int size, MoveController moveController) {
        super(chessboardPoint, location, color, listener, size,moveController);
        initiateKingImage(color);
    }

    public boolean canMoveTo(ChessComponent[][] chessComponents, ChessboardPoint destination) {
        ChessboardPoint source = getChessboardPoint();
        if (source.getY()==destination.getY()) {
            if (Math.abs(source.getX() - destination.getX()) <= 1) {return true;}
            else{return false;}
        }
        else if (source.getX()==destination.getX()){
            if (Math.abs(source.getY() - destination.getY()) <= 1){return true;}
            else{return false;}
        }
        else if (Math.abs(source.getX() - destination.getX()) == 1&&Math.abs(source.getY() - destination.getY()) == 1){
            return true;
        }
        else {return false;}
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // g.drawImage(rookImage, 0, 0, getWidth() - 13, getHeight() - 20, this);
        g.drawImage(kingImage, 0, 0, getWidth() , getHeight(), this);
        g.setColor(Color.BLACK);
        if (isSelected()) { // Highlights the model if selected.
            g.setColor(Color.RED);
            g.drawOval(0, 0, getWidth() , getHeight());
        }
    }
}
