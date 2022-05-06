package view;


import model.*;
import controller.ClickController;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**
 * 这个类表示面板上的棋盘组件对象
 */
public class Chessboard extends JComponent {
    private int a=1;
    /**
     * CHESSBOARD_SIZE： 棋盘是8 * 8的
     * <br>
     * BACKGROUND_COLORS: 棋盘的两种背景颜色
     * <br>
     * chessListener：棋盘监听棋子的行动
     * <br>
     * chessboard: 表示8 * 8的棋盘
     * <br>
     * currentColor: 当前行棋方
     */
    private static final int CHESSBOARD_SIZE = 8;

    private final ChessComponent[][] chessComponents = new ChessComponent[CHESSBOARD_SIZE][CHESSBOARD_SIZE];
    private ChessColor currentColor = ChessColor.BLACK;
    //all chessComponents in this chessboard are shared only one model controller
    private final ClickController clickController = new ClickController(this);
    private final int CHESS_SIZE;
    private ChessComponent[] chessForStore=new ChessComponent[32];

    private LinkedList<Record> huiQi=new LinkedList<>();


    private JLabel lable;

    public void setLable(JLabel lable) {
        this.lable = lable;
    }
    public void setRestarted(){

        System.out.println("reStarted");
        initiateEmptyChessboard();
        Init();
        System.out.println("After restarted");

    }

    public Chessboard(int width, int height) {
        setLayout(null); // Use absolute layout.
        setSize(width, height);
        CHESS_SIZE = width / 8;
        System.out.printf("chessboard size = %d, chess size = %d\n", width, CHESS_SIZE);

        initiateEmptyChessboard();
        Init();
    }
    public void Init(){
        // FIXME: Initialize chessboard for testing only.
        initRookOnBoard(0, 0, ChessColor.BLACK);
        initRookOnBoard(0, CHESSBOARD_SIZE - 1, ChessColor.BLACK);
        initRookOnBoard(CHESSBOARD_SIZE - 1, 0, ChessColor.WHITE);
        initRookOnBoard(CHESSBOARD_SIZE - 1, CHESSBOARD_SIZE - 1, ChessColor.WHITE);


        initKnightOnBoard(0,1,ChessColor.BLACK);
        initKnightOnBoard(0,CHESSBOARD_SIZE-2,ChessColor.BLACK);
        initKnightOnBoard(CHESSBOARD_SIZE-1,1,ChessColor.WHITE);
        initKnightOnBoard(CHESSBOARD_SIZE-1,CHESSBOARD_SIZE-2,ChessColor.WHITE);

        initBishopOnBoard(0,2,ChessColor.BLACK);
        initBishopOnBoard(0,CHESSBOARD_SIZE-3,ChessColor.BLACK);
        initBishopOnBoard(CHESSBOARD_SIZE-1,2,ChessColor.WHITE);
        initBishopOnBoard(CHESSBOARD_SIZE-1,CHESSBOARD_SIZE-3,ChessColor.WHITE);

        initQueenOnBoard(0,4,ChessColor.BLACK);
        initQueenOnBoard(CHESSBOARD_SIZE-1,4,ChessColor.WHITE);

        initKingOnBoard(0,3,ChessColor.BLACK);
        initKingOnBoard(CHESSBOARD_SIZE-1,3,ChessColor.WHITE);

        for (int i=0;i<CHESSBOARD_SIZE;i++){
            initPawnOnBoard(CHESSBOARD_SIZE-2,i,ChessColor.WHITE);
            initPawnOnBoard(1,i,ChessColor.BLACK);

        }
    }



    public void Restarted(){
        a=1;
        removeAll();
        initiateEmptyChessboard();
        initRookOnBoard(0, 0, ChessColor.BLACK);
        initRookOnBoard(0, CHESSBOARD_SIZE - 1, ChessColor.BLACK);
        initRookOnBoard(CHESSBOARD_SIZE - 1, 0, ChessColor.WHITE);
        initRookOnBoard(CHESSBOARD_SIZE - 1, CHESSBOARD_SIZE - 1, ChessColor.WHITE);


        initKnightOnBoard(0,1,ChessColor.BLACK);
        initKnightOnBoard(0,CHESSBOARD_SIZE-2,ChessColor.BLACK);
        initKnightOnBoard(CHESSBOARD_SIZE-1,1,ChessColor.WHITE);
        initKnightOnBoard(CHESSBOARD_SIZE-1,CHESSBOARD_SIZE-2,ChessColor.WHITE);

        initBishopOnBoard(0,2,ChessColor.BLACK);
        initBishopOnBoard(0,CHESSBOARD_SIZE-3,ChessColor.BLACK);
        initBishopOnBoard(CHESSBOARD_SIZE-1,2,ChessColor.WHITE);
        initBishopOnBoard(CHESSBOARD_SIZE-1,CHESSBOARD_SIZE-3,ChessColor.WHITE);

        initQueenOnBoard(0,4,ChessColor.BLACK);
        initQueenOnBoard(CHESSBOARD_SIZE-1,4,ChessColor.WHITE);

        initKingOnBoard(0,3,ChessColor.BLACK);
        initKingOnBoard(CHESSBOARD_SIZE-1,3,ChessColor.WHITE);

        for (int i=0;i<CHESSBOARD_SIZE;i++){
            initPawnOnBoard(CHESSBOARD_SIZE-2,i,ChessColor.WHITE);
            initPawnOnBoard(1,i,ChessColor.BLACK);

        }
        lable.setText("Turn For Black");

    }

    public void setCurrentColor(ChessColor currentColor) {
        this.currentColor = currentColor;
    }

    public ChessComponent[][] getChessComponents() {

        return chessComponents;
    }

    public ChessColor getCurrentColor() {
        return currentColor;
    }

    public void putChessOnBoard(ChessComponent chessComponent) {
        int row = chessComponent.getChessboardPoint().getX(), col = chessComponent.getChessboardPoint().getY();

        if (chessComponents[row][col] != null) {
            remove(chessComponents[row][col]);
        }
        add(chessComponents[row][col] = chessComponent);
    }

    public void swapChessComponents(ChessComponent chess1, ChessComponent chess2) {
        // Note that chess1 has higher priority, 'destroys' chess2 if exists.
        if (!(chess2 instanceof EmptySlotComponent)) {
//            Record record=new Record();
//            record.setChessComponent(chess1);
//            record.setStart(chess1.getChessboardPoint());
//            record.setEnd(chess2.getChessboardPoint());
//            record.setBeingEaten(chess2);
//            huiQi.add(record);
            remove(chess2);

            add(chess2 = new EmptySlotComponent(chess2.getChessboardPoint(), chess2.getLocation(), clickController, CHESS_SIZE));
        }
        chess1.swapLocation(chess2);
        int row1 = chess1.getChessboardPoint().getX(), col1 = chess1.getChessboardPoint().getY();
        chessComponents[row1][col1] = chess1;
        int row2 = chess2.getChessboardPoint().getX(), col2 = chess2.getChessboardPoint().getY();
        chessComponents[row2][col2] = chess2;
//        Record record=new Record();
//        record.setChessComponent(chess1);
//        record.setStart(chess1.getChessboardPoint());
//        record.setEnd(chess2.getChessboardPoint());
//        record.setBeingEaten(chess2); huiQi.add(record);

        chess1.repaint();
        chess2.repaint();

        a++;
        if (a%2==0){
            lable.setText("Turn For White");
        }
        else {
            lable.setText("Turn For Black");
        }

    }

    public void initiateEmptyChessboard() {
        for (int i = 0; i < chessComponents.length; i++) {
            for (int j = 0; j < chessComponents[i].length; j++) {
                putChessOnBoard(new EmptySlotComponent(new ChessboardPoint(i, j), calculatePoint(i, j), clickController, CHESS_SIZE));
            }
        }
    }



    public void swapColor() {
        currentColor = currentColor == ChessColor.BLACK ? ChessColor.WHITE : ChessColor.BLACK;
    }

    private void initRookOnBoard(int row, int col, ChessColor color) {
        ChessComponent chessComponent = new RookChessComponent(new ChessboardPoint(row, col), calculatePoint(row, col), color, clickController, CHESS_SIZE);
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }
    private void initBishopOnBoard(int row,int col, ChessColor color){
        ChessComponent chessComponent = new BishopChessComponent(new ChessboardPoint(row,col),calculatePoint(row,col),color,clickController,CHESS_SIZE);
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }
    private void initKnightOnBoard(int row,int col, ChessColor color){
        ChessComponent chessComponent = new KnightChessComponent(new ChessboardPoint(row,col),calculatePoint(row,col),color,clickController,CHESS_SIZE);
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }
    private void initQueenOnBoard(int row,int col, ChessColor color){
        ChessComponent chessComponent = new QueenChessComponent(new ChessboardPoint(row,col),calculatePoint(row,col),color,clickController,CHESS_SIZE);
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }
    private void initKingOnBoard(int row,int col, ChessColor color){
        ChessComponent chessComponent = new KingChessComponent(new ChessboardPoint(row,col),calculatePoint(row,col),color,clickController,CHESS_SIZE);
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }
    private void initPawnOnBoard(int row,int col, ChessColor color){
        ChessComponent chessComponent = new PawnChessComponent(new ChessboardPoint(row,col),calculatePoint(row,col),color,clickController,CHESS_SIZE);
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }


    private Point calculatePoint(int row, int col) {
        return new Point(col * CHESS_SIZE, row * CHESS_SIZE);
    }

    public void loadGame(List<String> chessData) {
        chessData.forEach(System.out::println);
    }

    public void setHuiQi(LinkedList<Record> huiQi) {
        this.huiQi = huiQi;
    }
    public void huiQi(){
        Record record=huiQi.pollLast();
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }
    public String theStore(){
        String theStringStore="";
        for (int i=0;i<8;i++){
            for (int j=0;j<8;j++){
                ChessComponent ches=chessComponents[i][j];
                if(ches instanceof KingChessComponent&&ches.getChessColor()==ChessColor.BLACK){
                    theStringStore+="K";
                }
                if(ches instanceof KnightChessComponent&&ches.getChessColor()==ChessColor.BLACK){
                    theStringStore+="N";
                }
                if (ches instanceof QueenChessComponent&&ches.getChessColor()==ChessColor.BLACK){
                    theStringStore+="Q";
                }
                if (ches instanceof BishopChessComponent&&ches.getChessColor()==ChessColor.BLACK){
                    theStringStore+="B";
                }
                if (ches instanceof RookChessComponent&&ches.getChessColor()==ChessColor.BLACK){
                    theStringStore+="R";
                }
                if (ches instanceof PawnChessComponent&&ches.getChessColor()==ChessColor.BLACK){
                    theStringStore+="P";
                }



                if(ches instanceof KingChessComponent&&ches.getChessColor()==ChessColor.WHITE){
                    theStringStore+="k";
                }
                if(ches instanceof KnightChessComponent&&ches.getChessColor()==ChessColor.WHITE){
                    theStringStore+="n";
                }
                if (ches instanceof QueenChessComponent&&ches.getChessColor()==ChessColor.WHITE){
                    theStringStore+="q";
                }
                if (ches instanceof BishopChessComponent&&ches.getChessColor()==ChessColor.WHITE){
                    theStringStore+="b";
                }
                if (ches instanceof RookChessComponent&&ches.getChessColor()==ChessColor.WHITE){
                    theStringStore+="r";
                }
                if (ches instanceof PawnChessComponent&&ches.getChessColor()==ChessColor.WHITE){
                    theStringStore+="p";
                }
                if (ches instanceof EmptySlotComponent ){
                    theStringStore+=" ";
                }
            }
            theStringStore+= "\n";
        }
        theStringStore+=a;
        return theStringStore.toString();
    }

}

