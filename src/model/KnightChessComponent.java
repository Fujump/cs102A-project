package model;

import view.ChessboardPoint;
import controller.ClickController;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;


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
    public KnightChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor chessColor, ClickController clickController, int size) {
        super(chessboardPoint, location, chessColor, clickController, size);
        initiateKnightImage(chessColor);
    }


    @Override
    public boolean canMoveTo(ChessComponent[][] chessComponents, ChessboardPoint destination) {
        ChessboardPoint source = getChessboardPoint();
        if (source.getX() == destination.getX()) {
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
        } else { // Not on the same row or the same column.
            return false;
        }
        return true;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(knightImage, 0, 0, getWidth() , getHeight(), this);
        g.setColor(Color.BLACK);
        if (isSelected()) { // Highlights the model if selected.
            g.setColor(Color.RED);
            g.drawOval(0, 0, getWidth() , getHeight());
        }
    }
}
