package view;

import controller.ClickController;
import controller.GameController;
import model.ChessColor;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URL;

import java.applet.AudioClip;
import java.io.*;
import java.applet.Applet;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.channels.ScatteringByteChannel;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.*;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

import static java.lang.Thread.sleep;


/**
 * 这个类表示游戏过程中的整个游戏界面，是一切的载体
 */
public class ChessGameFrame extends JFrame {
    public int cc=0;
    //    public final Dimension FRAME_SIZE ;
    private final int WIDTH;
    private final int HEIGTH;
    public final int CHESSBOARD_SIZE;
    private GameController gameController;
    private JLabel jb;








    public ChessGameFrame(int width, int height) {
        setTitle("2022 CS102A Project Demo"); //设置标题
        this.WIDTH = width;
        this.HEIGTH = height;
        this.CHESSBOARD_SIZE = HEIGTH * 4 / 5;



//        int fraWidth=this.getWidth();
//        int fraHeight=this.getHeight();
//        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//        int scWidth=screenSize.width;
//        int scHight=screenSize.height;
////        this.setSize(scWidth,scHight);
//
//        float proportionW=scWidth/fraWidth;
//        float proportionH=scHight/fraHeight;
//
//
//          FrameShow.modifyComponentSize(this, proportionW,proportionH);
//        this.toFront();




       setSize(WIDTH, HEIGTH);
        setLocationRelativeTo(null); // Center the window.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        setLayout(null);


        addChangeBackground();

        addChessboard();


        ImageIcon imag=new ImageIcon("images/back5.png");
//        JLabel jb=new JLabel(imag);
//
//        jb.setBounds(0,0,getWidth(),getHeight());
//
//        add(jb);

        Image image=imag.getImage();
        Image doSomImage=image.getScaledInstance(WIDTH,HEIGTH,Image.SCALE_FAST);
        ImageIcon tureBackground = new ImageIcon(doSomImage);

        jb=new JLabel(tureBackground);

        jb.setBounds(0,0,getWidth(),getHeight());
        add(jb);
//        String file = "F:\\music.mp3";
//            Music play = new Music(file);
//            // 开启
//            play.start();
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("stop!");










    }


    /**
     * 在游戏面板中添加棋盘
     */
    private void addChessboard() {


        Chessboard chessboard = new Chessboard(CHESSBOARD_SIZE, CHESSBOARD_SIZE);
        gameController = new GameController(chessboard);
        chessboard.setLocation(HEIGTH / 10, HEIGTH / 10);
        add(chessboard);

    /**
     * 在游戏面板中添加标签
     */
        JLabel statusLabel = new JLabel("Turn For White");
        statusLabel.setLocation(HEIGTH, HEIGTH / 10);
        statusLabel.setSize(200, 60);
        statusLabel.setFont(new Font("Rockwell", Font.BOLD, 20));
        statusLabel.setForeground(Color.red);
        add(statusLabel);
        chessboard.setLable(statusLabel);


          JButton button = new JButton("Restart");
            button.setLocation(HEIGTH, HEIGTH / 10 + 240);
            button.setSize(200, 60);
            button.setFont(new Font("Rockwell", Font.BOLD, 20));
            add(button);

            button.addActionListener(e -> {
//                System.out.println("Click Restart");
                int n=JOptionPane.showConfirmDialog(null,"Are you sure to restart","Confirm",JOptionPane.YES_NO_OPTION);
                if (n==JOptionPane.YES_OPTION){
                    chessboard.setCurrentColor(ChessColor.WHITE);
                    chessboard.Restarted();
//                    System.out.printf("%d",chessboard.getA());
                    repaint();

                }
            });





    /**
     * 在游戏面板中增加一个按钮，如果按下的话就会显示Hello, world!
     */


        JButton button1 = new JButton("Store");
        button1.setLocation(HEIGTH, HEIGTH / 10 + 80);
        button1.setSize(200, 60);
        button1.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button1);
        button1.addActionListener(e -> {
            System.out.println("Click store");
            int n=JOptionPane.showConfirmDialog(null,"Are you sure to store","Confirm",JOptionPane.YES_NO_OPTION);
            if (n==JOptionPane.YES_OPTION){
                System.out.println(chessboard.theStore());
                String a=chessboard.theStore();
//                saveAsFileWriter(chessboard.theStore());




//                JFileChooser fileChooser = new JFileChooser();
////后缀名过滤器
//                FileNameExtensionFilter filter = new FileNameExtensionFilter("标签文件(*.txt)", "txt");
//                fileChooser.setFileFilter(filter);
//// 在容器上打开文件选择器
//                fileChooser.showSaveDialog(fileChooser);
//                File f=fileChooser.getSelectedFile();
////字节输出流
//                FileOutputStream fos = null;
//                try {
//                    String fname = f.getName(); //从文件名输入框中获取文件名
//
//
//
//                    //创建文件
//                    File file=new File(fileChooser.getCurrentDirectory()+"/"+fname+".txt");
//                    fos = new FileOutputStream(file);
//                    //写入文件操作
//                    String Datas = chessboard.theStore();
//                    fos.write(Datas.getBytes());
//                    fos.close();
//
//                } catch (IOException e1) {
//                    System.err.println("IO异常");
//                    e1.printStackTrace();
//                }

                saveFile(a);

            }
        });




        JButton buttonLoad = new JButton("Load");
        buttonLoad.setLocation(HEIGTH, HEIGTH / 10 + 160);
        buttonLoad.setSize(200, 60);
        buttonLoad.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(buttonLoad);

//        buttonLoad.addActionListener(e -> {
//            System.out.println("Click load");
//            String path = JOptionPane.showInputDialog(this,"Input Path here");   E://xxx.txt
//            boolean pathForm=true;
//
//                if ((path.charAt(path.length()-3)!='t')||(path.charAt(path.length()-2)!='x')||(path.charAt(path.length()-1)!='t')){
//                    pathForm=false;
//                }
//
//
//            gameController.loadGameFromFile(path);
//            if(chessboard.isLoadTest()&&pathForm){
//
//                repaint();
//            }
//            else {
//                JOptionPane.showMessageDialog(null, "the message is not correct!", "Warnings", JOptionPane.WARNING_MESSAGE);
//            }



//        });


        buttonLoad.addActionListener(e->{//csdn

                JFileChooser chooser = new JFileChooser();
                chooser.setMultiSelectionEnabled(false); // 设为多选
                int returnVal = chooser.showOpenDialog(buttonLoad); // 是否打开文件选择框，选择保存是0，退出和1
//                System.out.println("returnVal=" + returnVal);

                if (returnVal == JFileChooser.APPROVE_OPTION) { //点击确定
                    boolean pathForm=true;
                    String filepath = chooser.getSelectedFile().getAbsolutePath();
//                    System.out.println(filepath);
//
//                    System.out.println("You chose to open this file: " + chooser.getSelectedFile().getName());
                    String path =filepath;
//                    System.out.println(path);// 输出相对路径
                     if ((path.charAt(path.length()-3)!='t')||(path.charAt(path.length()-2)!='x')||(path.charAt(path.length()-1)!='t')){
                         pathForm=false;
                         JOptionPane.showMessageDialog(null, "文件格式错误（错误编码：104）", "Warnings", JOptionPane.WARNING_MESSAGE);

                }
                     else {
                    gameController.loadGameFromFile(path);



            if(chessboard.isLoadTest()&&pathForm){

                repaint();
            }
            else {

                if (!chessboard.isTheILength()&&chessboard.isTheChessName()&&chessboard.isTheInt()){
                JOptionPane.showMessageDialog(null, "棋盘并非8*8", "Warnings", JOptionPane.WARNING_MESSAGE);
                repaint();
                    System.out.println(chessboard.isTheChessName());
                    System.out.println(chessboard.isTheChessName());
                    System.out.println(chessboard.isTheInt());
            }
                else if (!chessboard.isTheChessName()&&chessboard.isTheILength()&&chessboard.isTheInt()){
                    JOptionPane.showMessageDialog(null, "棋子并非六种之一，棋子并非黑白棋子", "Warnings", JOptionPane.WARNING_MESSAGE);

               repaint(); }
                else if (!chessboard.isTheInt()&&chessboard.isTheChessName()&&chessboard.isTheILength()){
                    JOptionPane.showMessageDialog(null, "缺少行棋方", "Warnings", JOptionPane.WARNING_MESSAGE);
                repaint();}
                else{
                    JOptionPane.showMessageDialog(null, "多处存在问题", "Warnings", JOptionPane.WARNING_MESSAGE);
                    repaint();}

            }
                }}


        });



        JButton huiqi=new JButton("HuiQi");
        huiqi.setLocation(HEIGTH, HEIGTH / 10 + 400);
        huiqi.setSize(200, 60);
        huiqi.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(huiqi);
        huiqi.addActionListener(e -> {
            if (chessboard.getIntStoreHuiQI()<2){
                JOptionPane.showMessageDialog(null, "已经是开始状态", "Warnings", JOptionPane.WARNING_MESSAGE);
            }
            else {
            String daiFuYuanChessBoard=chessboard.ForHUi();
            String[] data=daiFuYuanChessBoard.split("\n");
            chessboard.HuiQIGame(data);
            repaint();

            }

        });
        JButton review = new JButton("Review");
        review.setLocation(HEIGTH, HEIGTH / 10 + 480);
        review.setSize(200, 60);
        review.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(review);
        review.addActionListener(e->{
            int length=chessboard.getIntStoreHuiQI();
            int a=0;
            TestThread testThread = new TestThread(chessboard);
            testThread.start();

        });

    }




//    private static   String savefile = "E:\\test.txt";
//    private static void saveAsFileWriter(String content) {
//
//        FileWriter fwriter = null;
//        try {
//            fwriter = new FileWriter(savefile);
//            fwriter.write(content);
//        } catch (IOException ex) {
//              ex.printStackTrace();
//        } finally {
//            try {
//                fwriter.flush();
//                fwriter.close();
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//        }
//    }

    public void saveFile(String data) {//csdn
        //弹出文件选择框
        JFileChooser chooser = new JFileChooser();

        //后缀名过滤器
//        FileNameExtensionFilter filter = new FileNameExtensionFilter(
//                "(*.txt)", "txt");
//        chooser.setFileFilter(filter);

        //下面的方法将阻塞，直到【用户按下保存按钮且“文件名”文本框不为空】或【用户按下取消按钮】
        int option = chooser.showSaveDialog(null);
        if(option==JFileChooser.APPROVE_OPTION){	//假如用户选择了保存
            File file = chooser.getSelectedFile();

            String fileName = chooser.getName(file);	//从文件名输入框中获取文件名

            //假如用户填写的文件名不带我们制定的后缀名，那么我们给它添上后缀
            if(fileName.indexOf(".txt")==-1){
                file = new File(chooser.getCurrentDirectory(),fileName+".txt");
                System.out.println("renamed");
                System.out.println(file.getName());
            }

            try {
                FileOutputStream fos = new FileOutputStream(file);
                    fos.write(data.getBytes());
                    fos.close();


            } catch (IOException e) {
//                System.err.println("IO异常");
                e.printStackTrace();
            }
        }
    }


//    static void playMusic(){//背景音乐播放
//        try {
//            URL cb;
//            File f = new File("F:\\QQmusicDownLoad\\music.wav"); // 引号里面的是音乐文件所在的路径
//            cb = f.toURL();
//            AudioClip aau;
//            aau = Applet.newAudioClip(cb);
//
//            aau.play();
//            aau.loop();//循环播放
//            System.out.println("可以播放");
//            // 循环播放 aau.play()
//            //单曲 aau.stop()停止播放
//
//        } catch (MalformedURLException e) {
//
//            e.printStackTrace();
//        }
//    }

    public void addChangeBackground(){

        JButton changeBackground = new JButton("change background");
        changeBackground.setLocation(HEIGTH, HEIGTH / 10 + 320 );
        changeBackground.setSize(200, 60);
        add(changeBackground);




        changeBackground.addActionListener(e -> {JFileChooser chooser = new JFileChooser();
            chooser.setMultiSelectionEnabled(false); // 设为多选
            int returnVal = chooser.showOpenDialog(changeBackground); // 是否打开文件选择框，选择保存是0，退出和1


            if (returnVal == JFileChooser.APPROVE_OPTION) { //点击确定
                remove(jb);
                repaint();
                String filepath = chooser.getSelectedFile().getAbsolutePath();
//                System.out.println(filepath);

//                System.out.println("You chose to open this file: " + chooser.getSelectedFile().getName());
                String path =filepath;
//                System.out.println(path);

                ImageIcon imag=new ImageIcon(path);


                Image image=imag.getImage();
                Image doSomImage=image.getScaledInstance(WIDTH,HEIGTH,Image.SCALE_FAST);
                ImageIcon tureBackgroundd = new ImageIcon(doSomImage);

                 jb=new JLabel(tureBackgroundd);
                jb.setBounds(0,0,getWidth(),getHeight());
                add(jb);
                jb.repaint();

            }});

}





}





