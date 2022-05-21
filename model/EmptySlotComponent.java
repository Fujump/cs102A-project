package model;

import view.Chessboard;
import view.ChessboardPoint;
import controller.ClickController;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 这个类表示棋盘上的空位置
 */
public class EmptySlotComponent extends ChessComponent {

    public EmptySlotComponent(ChessboardPoint chessboardPoint, Point location, ClickController listener, int size,Chessboard chessboard) {
        super(chessboardPoint, location, ChessColor.NONE, listener, size,chessboard);
    }

    @Override
    public boolean canMoveTo(ChessComponent[][] chessboard, ChessboardPoint destination) {
        return false;
    }
    public ArrayList<ChessboardPoint> canMoveTo(Chessboard chessboard){
        ArrayList<ChessboardPoint> canMoveTo=new ArrayList<>(){};
        return canMoveTo;
    }

    @Override
    public ArrayList<ChessboardPoint> canMoveTo(ChessComponent[][] chessComponents) {
        ArrayList<ChessboardPoint> canMoveTo=new ArrayList<>(){};
        return canMoveTo;
    }

    @Override
    public void loadResource() throws IOException {
        //No resource!
    }

}
