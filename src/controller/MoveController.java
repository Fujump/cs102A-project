package controller;

import model.ChessComponent;
import view.Chessboard;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public class MoveController {

    private final Chessboard chessboard;
    private ChessComponent chess;
    private Image path1;
    private Image path2;

    public MoveController(Chessboard chessboard) {
        this.chessboard = chessboard;

    }
    public void MoveIn(ChessComponent chessCompent){
        chess=chessCompent;
        try {
            path1=chessCompent.getImage1();
            path2=chessCompent.getImage2();
            chess.setImage1(ImageIO.read(new File("./images/move.png")));
            chess.setImage2(ImageIO.read(new File("./images/move.png")));

        }catch(Exception e) {
            e.printStackTrace();
        }


        chess.repaint();


    }

    public void MoveOut(ChessComponent chessComponent){
        try {

            chess.setImage1(path1);
            chess.setImage2(path2);

        }catch(Exception e) {
            e.printStackTrace();
        }
        chess.repaint();

    }




}
