package controller;


import model.ChessColor;
import model.ChessComponent;
import model.KingChessComponent;
import view.Chessboard;
import controller.GameController;
import view.ChessboardPoint;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

//class GameOver extends JFrame{
//    GameOver(){
//        setLocation(600,300);
//        setSize(250,100);
//        setLayout(new BorderLayout());
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setVisible(true);
//    }
//}

public class ClickController {
    private final Chessboard chessboard;
    private ChessComponent first;
    private GameController gameController;

    public ClickController(Chessboard chessboard) {
        this.chessboard = chessboard;
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

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
                first.setSelected(false);
                first = null;

                boolean winOrNotjs=WinOrNotjs(chessboard.getCurrentColor());
                boolean heqiwzkd=Heqiwzkd(chessboard.getCurrentColor());
                boolean heqisbcf=Heqisbcf(chessboard);
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
                if(heqiwzkd||heqisbcf){
//                     GameOver gameOver=new GameOver();
                     String outcome="无子可动和棋";
//                     JLabel output=new JLabel(outcome);
//                     gameOver.add(output,BorderLayout.CENTER);

                    JOptionPane.showMessageDialog(null,outcome , "Warnings", JOptionPane.WARNING_MESSAGE);
                    chessboard.Restarted();
                    chessboard.repaint();
                }
                if (IsKingAttacked(getNextColor())){
                    String outcome=String.format("%s side king is attacked!",getNextColor());
                    JOptionPane.showMessageDialog(null, outcome, "Warnings", JOptionPane.WARNING_MESSAGE);
                }


                chessboard.swapColor();
            }
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

    public ChessColor getNextColor(){
        if (chessboard.getCurrentColor()==ChessColor.WHITE){return ChessColor.BLACK;}
        else{return ChessColor.WHITE;}
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
                        !(chessboard.getChessComponents()[i][j].CanMoveTobubeijiang(chessboard).isEmpty())){
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
                    if (!chessboard.getChessComponents()[i][j].CanMoveTobubeijiang(chessboard).isEmpty()){wzkd= false;}
                }
            }
        }
        return wzkd&&!isKingAttacked;
    }
    public boolean Heqisbcf(Chessboard chessboard){
        ArrayList<String> historyList=chessboard.getStoreHuiQI();
        int size=historyList.size();
        if (size>=5&&historyList.get(size-1).equals(historyList.get(size-3))&&historyList.get(size-1).equals(historyList.get(size-5))){
            return true;
        }
        return false;
    }


    public boolean FinalCanMoveTo(ChessComponent[][] chessComponents, ChessboardPoint chessboardPoint){
        ArrayList<ChessboardPoint> finalList=first.CanMoveTobubeijiang(chessboard);
        for (int i = 0; i < finalList.size(); i++) {
            if (chessboardPoint.getX()==finalList.get(i).getX()&&
                    chessboardPoint.getY()==finalList.get(i).getY()){
                return true;
            }
        }
        return false;
    }
}
