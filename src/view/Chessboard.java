package view;


import controller.MoveController;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import model.*;
import controller.ClickController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 这个类表示面板上的棋盘组件对象
 */
public class Chessboard extends JComponent {
    private int a=1;
    private boolean loadTest=true;
    ArrayList<String> storeHuiQI=new ArrayList<>();

    private JLabel forRound;
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
    private ChessColor currentColor = ChessColor.WHITE;
    //all chessComponents in this chessboard are shared only one model controller
    private final ClickController clickController = new ClickController(this);
    private final MoveController moveController=new MoveController(this);
    private final int CHESS_SIZE;
    private LinkedList<Record> huiQi=new LinkedList<>();
    private JLabel lable;
    private String pa1;
    private String pa2;



    private JLabel jb;
    private boolean theInt=true;
    private boolean theILength=true;
    private boolean theChessName=true;


    private Color color;




    public void setJb(JLabel jb) {
        this.jb = jb;
    }

    public void setLable(JLabel lable) {
        this.lable = lable;

    }
    public void setRestarted(){
        initiateEmptyChessboard();
        Init();
    }

    public Chessboard(int width, int height) {
        setLayout(null); // Use absolute layout.
        setSize(width, height);
        CHESS_SIZE = width / 8;
//        System.out.printf("chessboard size = %d, chess size = %d\n", width, CHESS_SIZE); //取消打印数据
        initiateEmptyChessboard();
        Init();
        color=new Color(10,100,255);


        pa1="./images/white11.png";
        pa2="./images/blacksmall.png";




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
        storeHuiQI.add(theStore());
    }



    public void Restarted(){
//        for(int i=0;i<8;i++){
//            for (int j = 0; j < 8; j++) {
//
//                clickController.onClick();
//
//
//            }
//        }
//        if(avoidBugChessOnChick()){
//            for (int i=0;i<8;i++){
//                for (int j = 0; j < 8; j++) {
//                    chessComponents[i][j].setSelected(false);
//                    repaint();
//                }
//            }
//        }
        stopClick();

        storeHuiQI.clear();
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
        lable.setText("Turn For White");
        jb.setText("Round: 0");
        storeHuiQI.add(theStore());
        chessboardBlock();



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

            add(chess2 = new EmptySlotComponent(chess2.getChessboardPoint(), chess2.getLocation(), clickController, CHESS_SIZE,moveController));
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
        SoundOfChess play0= new SoundOfChess("./Music/ChessSound.mp3");
        chessboardBlock();

        play0.start();
        chess1.repaint();
        chess2.repaint();


        a++;
        if (a%2==0){
            lable.setText("Turn For Black");
        }
        else {
            lable.setText("Turn For White");
        }
        jb.setText("Round: "+(a)/2);

        storeHuiQI.add(theStore());
//        System.out.println(theStore());  //打印存储文件

    }

    public void initiateEmptyChessboard() {
        for (int i = 0; i < chessComponents.length; i++) {
            for (int j = 0; j < chessComponents[i].length; j++) {
                putChessOnBoard(new EmptySlotComponent(new ChessboardPoint(i, j), calculatePoint(i, j), clickController, CHESS_SIZE,moveController));
            }
        }
    }



    public void swapColor() {
        currentColor = currentColor == ChessColor.BLACK ? ChessColor.WHITE : ChessColor.BLACK;
    }

    private void initRookOnBoard(int row, int col, ChessColor color) {
        ChessComponent chessComponent = new RookChessComponent(new ChessboardPoint(row, col), calculatePoint(row, col), color, clickController, CHESS_SIZE,moveController);
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }
    private void initBishopOnBoard(int row,int col, ChessColor color){
        ChessComponent chessComponent = new BishopChessComponent(new ChessboardPoint(row,col),calculatePoint(row,col),color,clickController,CHESS_SIZE,moveController);
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }
    private void initKnightOnBoard(int row,int col, ChessColor color){
        ChessComponent chessComponent = new KnightChessComponent(new ChessboardPoint(row,col),calculatePoint(row,col),color,clickController,CHESS_SIZE,moveController);
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }
    private void initQueenOnBoard(int row,int col, ChessColor color){
        ChessComponent chessComponent = new QueenChessComponent(new ChessboardPoint(row,col),calculatePoint(row,col),color,clickController,CHESS_SIZE,moveController);
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }
    private void initKingOnBoard(int row,int col, ChessColor color){
        ChessComponent chessComponent = new KingChessComponent(new ChessboardPoint(row,col),calculatePoint(row,col),color,clickController,CHESS_SIZE,moveController);
        chessComponent.setVisible(true);
        putChessOnBoard(chessComponent);
    }
    private void initPawnOnBoard(int row,int col, ChessColor color){
        ChessComponent chessComponent = new PawnChessComponent(new ChessboardPoint(row,col),calculatePoint(row,col),color,clickController,CHESS_SIZE,moveController);
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
        chessboardBlock();
        storeHuiQI.clear();
        boolean test = theLoadTest( chessData);


        if(!test){
                loadTest=false;
        }
        else {
            loadTest=true;
            initiateEmptyChessboard();
//            chessData.forEach(System.out::println);
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    char theSimpleChess = chessData.get(i).charAt(j);
                    ChessComponent chess = chessComponents[i][j];
                    ChessboardPoint point = new ChessboardPoint(i, j);

                    if (theSimpleChess == 'R') {
                        initRookOnBoard(i, j, ChessColor.BLACK);
                    }
                    if (theSimpleChess == 'K') {
                        initKingOnBoard(i, j, ChessColor.BLACK);
                    }
                    if (theSimpleChess == 'Q') {
                        initQueenOnBoard(i, j, ChessColor.BLACK);
                    }
                    if (theSimpleChess == 'N') {
                        initKnightOnBoard(i, j, ChessColor.BLACK);
                    }
                    if (theSimpleChess == 'B') {
                        initBishopOnBoard(i, j, ChessColor.BLACK);
                    }
                    if (theSimpleChess == 'P') {
                        initPawnOnBoard(i, j, ChessColor.BLACK);
                    }


                    if (theSimpleChess == 'r') {
                        initRookOnBoard(i, j, ChessColor.WHITE);
                    }
                    if (theSimpleChess == 'k') {
                        initKingOnBoard(i, j, ChessColor.WHITE);
                    }
                    if (theSimpleChess == 'q') {
                        initQueenOnBoard(i, j, ChessColor.WHITE);
                    }
                    if (theSimpleChess == 'n') {
                        initKnightOnBoard(i, j, ChessColor.WHITE);
                    }
                    if (theSimpleChess == 'b') {
                        initBishopOnBoard(i, j, ChessColor.WHITE);
                    }
                    if (theSimpleChess == 'p') {
                        initPawnOnBoard(i, j, ChessColor.WHITE);
                    }
                }
            }

            String nn="";
            for(int i=0;i<chessData.get(8).length();i++){
                nn+=chessData.get(8).charAt(i);
            }

            a = Integer.parseInt(nn);

            System.out.println(a);

            if (a % 2 == 1) {
                setCurrentColor(ChessColor.WHITE);
                lable.setText("Turn For White");
                lable.setForeground(color);
            } else {
                setCurrentColor(ChessColor.BLACK);
                lable.setText("Turn For Black");
                lable.setForeground(color);
            }
            jb.setText("Round: "+a/2);

        }
        storeHuiQI.add(theStore());
    }
    public void HuiQIGame(String[] chessData) {

            int pp=0;
            initiateEmptyChessboard();
//            chessData.forEach(System.out::println);
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    char theSimpleChess = chessData[i].charAt(j);

                    ChessboardPoint point = new ChessboardPoint(i, j);

                    if (theSimpleChess == 'R') {
                        initRookOnBoard(i, j, ChessColor.BLACK);
                    }
                    if (theSimpleChess == 'K') {
                        initKingOnBoard(i, j, ChessColor.BLACK);
                    }
                    if (theSimpleChess == 'Q') {
                        initQueenOnBoard(i, j, ChessColor.BLACK);
                    }
                    if (theSimpleChess == 'N') {
                        initKnightOnBoard(i, j, ChessColor.BLACK);
                    }
                    if (theSimpleChess == 'B') {
                        initBishopOnBoard(i, j, ChessColor.BLACK);
                    }
                    if (theSimpleChess == 'P') {
                        initPawnOnBoard(i, j, ChessColor.BLACK);
                    }


                    if (theSimpleChess == 'r') {
                        initRookOnBoard(i, j, ChessColor.WHITE);
                    }
                    if (theSimpleChess == 'k') {
                        initKingOnBoard(i, j, ChessColor.WHITE);
                    }
                    if (theSimpleChess == 'q') {
                        initQueenOnBoard(i, j, ChessColor.WHITE);
                    }
                    if (theSimpleChess == 'n') {
                        initKnightOnBoard(i, j, ChessColor.WHITE);
                    }
                    if (theSimpleChess == 'b') {
                        initBishopOnBoard(i, j, ChessColor.WHITE);
                    }
                    if (theSimpleChess == 'p') {
                        initPawnOnBoard(i, j, ChessColor.WHITE);
                    }
                }
            }
            for (int i=0;i<chessData[8].length();i++){
                pp+=chessData[8].charAt(i)-'0';
            }
            a = pp;

            if (a % 2 == 1) {
                setCurrentColor(ChessColor.WHITE);
                lable.setText("Turn For White");
                lable.setForeground(color);
            } else {
                setCurrentColor(ChessColor.BLACK);
                lable.setText("Turn For Black");
                lable.setForeground(color);
            }

            jb.setText("Round: "+a/2);
        chessboardBlock();




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
    public boolean theLoadTest(List<String> chessData){//test the load is right or not ,
          theILength=true;
          theChessName=true;//contains the length 8*8 ,the a--the turn,
          theInt=true;

        if (chessData.size()!=9){
            return  false;
        }
        else {
            for (int i=0;i<8;i++){
                if (chessData.get(i).length()!=8){
                    theILength=false;
                    break;
                }
            }
            for(int i=0;i<chessData.get(8).length();i++){
                int chr=chessData.get(8).charAt(i);

                if (chr<48||chr>57){
                    theInt=false;
                    break;
                }
            }
            for (int i=0;i<8;i++){
                for(int j=0;j<8;j++){
                    char chess=chessData.get(i).charAt(j);
                    if (chess!='r'&&chess!='R'&&chess!='K'&&chess!='Q'&&chess!='k'&&chess!='q'&&chess!='B'&&chess!='b'&&chess!='N'&&chess!='n'&&chess!='P'&&chess!='p'&&chess!=' '){
                        theChessName=false;
                        break;
                    }
                }
            }

            if (theInt&&theILength&&theChessName){
                return true;
            }
            else{
                return false;
            }
        }

    }

    public boolean isLoadTest() {
        return loadTest;
    }
    public String ForHUi(){

       int size=storeHuiQI.size();
       if (size>=2){


       String QianYibu=storeHuiQI.get(size-2);

       storeHuiQI.remove(size-1);
       return QianYibu;}
      return null;

    }


    public Color getColor() {
        return color;
    }

    public int getIntStoreHuiQI() {
        return storeHuiQI.size();
    }

    public String HUiFang(int a){
        String rereview = storeHuiQI.get(a);

        return rereview;


    }

    public boolean isTheInt() {
        return theInt;
    }

    public boolean isTheILength() {
        return theILength;
    }

    public boolean isTheChessName() {
        return theChessName;
    }


    public void change(String path,String pat){
        pa1=path;
        pa2=pat;
        for (int i=0;i<8;i++){
            for (int j = 0; j < 8; j++) {
                try {
               chessComponents[i][j].setImage1(ImageIO.read(new File(pa1)));
               chessComponents[i][j].setImage2(ImageIO.read(new File(pa2)));
//            image3=ImageIO.read(new File("./images/yellow.png"));
            }catch(Exception e) {
                e.printStackTrace();
            }

            }
        }

        repaint();
    }
    public void chessboardBlock(){
        for (int i=0;i<8;i++){
            for (int j = 0; j < 8; j++) {
                try {
                    chessComponents[i][j].setImage1(ImageIO.read(new File(pa1)));
                    chessComponents[i][j].setImage2(ImageIO.read(new File(pa2)));
//            image3=ImageIO.read(new File("./images/yellow.png"));
                }catch(Exception e) {
                    e.printStackTrace();
                }

            }
        }
        repaint();



    }

    public boolean avoidBugChessOnChick(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
               if (chessComponents[i][j].isSelected()==true){
                   return false;
               }
            }
        }
        return true;
    }

    public void stopClick(){
        for (int i=0;i<8;i++){
            for (int j=0;j<8;j++){
                chessComponents[i][j].setSelected(false);


                }
            }
        }
    }



//    public void printtt(){
//        for (int i = 0; i < 8; i++) {
//            for (int j=0;j<8;j++){
//                ches
//            }
//        }
//    }













class SoundOfChess extends Thread{
    Player player;
    String music;
    public SoundOfChess(String file) {
        this.music = file;
    }
    public void run() {
        try {
            play();
        } catch (FileNotFoundException | JavaLayerException e) {
            e.printStackTrace();
        }
    }
    public void play() throws FileNotFoundException, JavaLayerException {
        BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(music));
        player = new Player(buffer);
        player.play();
    }











}
