package model;

import view.Chessboard;
import view.ChessboardPoint;

public class Record {
    private ChessComponent chessComponent;
    private ChessboardPoint start;
    private ChessboardPoint end;

    private ChessComponent BeingEaten;

    public Record(){}

    public Record(ChessComponent chessComponent, ChessboardPoint start, ChessboardPoint end) {
        this.chessComponent = chessComponent;
        this.start = start;
        this.end = end;
    }

    public Record(ChessComponent chessComponent, ChessboardPoint start, ChessboardPoint end, ChessComponent beingEaten) {
        this.chessComponent = chessComponent;
        this.start = start;
        this.end = end;
        BeingEaten = beingEaten;
    }

    public ChessComponent getChessComponent() {
        return chessComponent;
    }

    public void setChessComponent(ChessComponent chessComponent) {
        this.chessComponent = chessComponent;
    }

    public ChessboardPoint getStart() {
        return start;
    }

    public ChessComponent getBeingEaten() {
        return BeingEaten;
    }

    public void setBeingEaten(ChessComponent beingEaten) {
        BeingEaten = beingEaten;
    }

    public void setStart(ChessboardPoint start) {
        this.start = start;
    }

    public ChessboardPoint getEnd() {
        return end;
    }

    public void setEnd(ChessboardPoint end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "Record{" +
                "chessComponent=" + chessComponent +
                ", start=" + start +
                ", end=" + end +
                ", BeingEaten=" + BeingEaten +
                '}';
    }
}
