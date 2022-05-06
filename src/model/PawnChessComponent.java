package model;

import controller.ClickController;
import view.ChessboardPoint;

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
    public PawnChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor chessColor, ClickController clickController, int size) {
        super(chessboardPoint, location, chessColor, clickController, size);
        initiatePawnImage(chessColor);
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
        g.drawImage(pawnImage, 0, 0, getWidth() , getHeight(), this);
        g.setColor(Color.BLACK);
        if (isSelected()) { // Highlights the model if selected.
            g.setColor(Color.RED);
            g.drawOval(0, 0, getWidth() , getHeight());
        }
    }
}
