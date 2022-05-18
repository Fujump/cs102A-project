package controller;


import model.ChessColor;
import model.ChessComponent;
import model.KingChessComponent;
import view.Chessboard;
import view.ChessboardPoint;

import javax.swing.*;
import java.util.ArrayList;


public class ClickController {
    private final Chessboard chessboard;
    private ChessComponent first;

    public ClickController(Chessboard chessboard) {this.chessboard = chessboard;}

    public void onClick(ChessComponent chessComponent) {
        if (first == null) {
            if (handleFirst(chessComponent)) {
                chessComponent.setSelected(true);
                first = chessComponent;
                first.repaint();
            }
        } else {
            if (first == chessComponent) { // 再次点击取消选取
                chessComponent.setSelected(false);
                ChessComponent recordFirst = first;
                first = null;
                recordFirst.repaint();
            } else if (handleSecond(chessComponent)) {
                //repaint in swap chess method.
                chessboard.swapChessComponents(first, chessComponent);
                chessboard.swapColor();

                first.setSelected(false);
                first = null;
            }
        }

        boolean winOrNotjs=WinOrNotjs(chessboard.getCurrentColor());
        boolean heqiwzkd=Heqiwzkd(chessboard.getCurrentColor());
        if (winOrNotjs){
//                    GameOver gameOver=new GameOver();
            String winner=String.format("%s side win",chessboard.getCurrentColor());
//                    JLabel outcome=new JLabel( winner);
//                    gameOver.add(outcome,BorderLayout.CENTER);



            JOptionPane.showMessageDialog(null, winner, "Warnings", JOptionPane.WARNING_MESSAGE);
//                    JDialog gameOver=new JDialog();
            chessboard.Restarted();
            chessboard.repaint();
        }
        if(heqiwzkd){
//                     GameOver gameOver=new GameOver();
//                     String outcome="   无子可动和棋";
//                     JLabel output=new JLabel(outcome);
            JOptionPane.showMessageDialog(null, "无子可动和棋", "Warnings", JOptionPane.WARNING_MESSAGE);
//                     gameOver.add(output,BorderLayout.CENTER);
        }
    }

    /**
     * @param chessComponent 目标选取的棋子
     * @return 目标选取的棋子是否与棋盘记录的当前行棋方颜色相同
     */

    private boolean handleFirst(ChessComponent chessComponent) {
        return chessComponent.getChessColor() == chessboard.getCurrentColor();
    }

    /**
     * @param chessComponent first棋子目标移动到的棋子second
     * @return first棋子是否能够移动到second棋子位置
     */

    private boolean handleSecond(ChessComponent chessComponent) {
        return chessComponent.getChessColor() != chessboard.getCurrentColor() &&
                FinalCanMoveTo(chessboard.getChessComponents(), chessComponent.getChessboardPoint());
    }


    public boolean IsKingAttacked(ChessColor beijiangfang){
        int KingX=-1,KingY=-1;
        for (int k = 0; k < 8; k++) {
            for (int l = 0; l < 8; l++) {
                if ((chessboard.getChessComponents()[k][l] instanceof KingChessComponent)&&
                        chessboard.getChessComponents()[k][l].getChessColor()==beijiangfang){
                    KingX=chessboard.getChessComponents()[k][l].getChessboardPoint().getX();
                    KingY=chessboard.getChessComponents()[k][l].getChessboardPoint().getY();
                }
            }
        }

        boolean isKingAttacked=false;
        for (int k = 0; k < 8; k++) {
            for (int l = 0; l < 8; l++) {
                if (chessboard.getChessComponents()[k][l].getChessColor()!=beijiangfang) {
                    for (int m = 0; m < chessboard.getChessComponents()[k][l].canMoveTo(chessboard).size(); m++) {
                        if (chessboard.getChessComponents()[k][l].canMoveTo(chessboard).get(m).getX() == KingX
                                && chessboard.getChessComponents()[k][l].canMoveTo(chessboard).get(m).getY() == KingY) {
                            isKingAttacked=true;
                        }
                    }
                }
            }
        }
        return isKingAttacked;
    }

    //    public ChessComponent FindKing(ChessColor chessColor){
//        for (int i = 0; i < 8; i++) {
//            for (int j = 0; j < 8; j++) {
//                if ((chessboard.getChessComponents()[i][j] instanceof KingChessComponent)
//                        && chessboard.getChessComponents()[i][j].getChessColor() == chessColor) {
//                    return chessboard.getChessComponents()[i][j];
//                }
//            }
//        }
//        return null;
//    }
    public boolean WinOrNotjs(ChessColor jiangjunfang){
        int KingX=-1,KingY=-1;
        ChessColor beijiangfang;
        if (jiangjunfang==ChessColor.WHITE){beijiangfang=ChessColor.BLACK;}
        else{beijiangfang=ChessColor.WHITE;}
        for (int k = 0; k < 8; k++) {
            for (int l = 0; l < 8; l++) {
                if ((chessboard.getChessComponents()[k][l] instanceof KingChessComponent)&&
                        chessboard.getChessComponents()[k][l].getChessColor()==beijiangfang){
                    KingX=chessboard.getChessComponents()[k][l].getChessboardPoint().getX();
                    KingY=chessboard.getChessComponents()[k][l].getChessboardPoint().getY();
                }
            }
        }

        boolean isKingAttacked=false;
        for (int k = 0; k < 8; k++) {
            for (int l = 0; l < 8; l++) {
                if (chessboard.getChessComponents()[k][l].getChessColor()==jiangjunfang) {
                    for (int m = 0; m < chessboard.getChessComponents()[k][l].canMoveTo(chessboard).size(); m++) {
                        if (chessboard.getChessComponents()[k][l].canMoveTo(chessboard).get(m).getX() == KingX
                                && chessboard.getChessComponents()[k][l].canMoveTo(chessboard).get(m).getY() == KingY) {
                            isKingAttacked=true;
                        }
                    }
                }
            }
        }

        boolean canyingjiang=false;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (chessboard.getChessComponents()[i][j].getChessColor()==beijiangfang&&
                        !(chessboard.getChessComponents()[i][j].FinalCanMoveTo(chessboard).isEmpty())){
                    canyingjiang=true;
                }
            }
        }

        if (isKingAttacked&&!canyingjiang){
            return true;
        }else{return false;}
    }
    public boolean Heqiwzkd(ChessColor currentColor){
        ChessColor nextColor;
        if (currentColor==ChessColor.WHITE){nextColor=ChessColor.BLACK;}
        else {nextColor=ChessColor.WHITE;}
        boolean isKingAttacked=IsKingAttacked(nextColor);
        boolean wzkd=true;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (chessboard.getChessComponents()[i][j].getChessColor()!=currentColor){
                    if (!chessboard.getChessComponents()[i][j].FinalCanMoveTo(chessboard).isEmpty()){wzkd= false;}
                }
            }
        }
        return wzkd&&!isKingAttacked;
    }//不被将时

    public boolean FinalCanMoveTo(ChessComponent[][] chessComponents, ChessboardPoint chessboardPoint){
        ArrayList<ChessboardPoint> finalList=first.FinalCanMoveTo(chessboard);
        for (int i = 0; i < finalList.size(); i++) {
            if (chessboardPoint.getX()==finalList.get(i).getX()&&
                    chessboardPoint.getY()==finalList.get(i).getY()){
                return true;
            }
        }
        return false;
    }
//
//    public Chessboard retunChess(){
//        for (int i=0;i<8;i++){
//            for (int j = 0; j < 8; j++) {
//                chessboard.
//
//            }
//        }
//    }

}
