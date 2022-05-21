package view;


import javax.swing.*;

import java.awt.*;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class Menu extends JFrame implements ActionListener {
        private JButton buttonStart;

        private JButton buttonOver;

        private JLabel jb;
    BufferedImage bg = null;



        ImageIcon image;

        public Menu(){


//定义按钮的排列方式
            setLocationRelativeTo(null);

            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            this.setSize(400,400);

            this.setLocation(300,200);
            setLayout(null);
            Color coo=new Color(0,90,90);

            buttonStart = new JButton("Start");
            buttonStart.setSize(100,50);
            buttonStart.setFont(new Font("Rockwell", Font.BOLD, 25));
            buttonStart.setForeground(coo);
            buttonStart.setLocation(150,150);


            buttonOver = new JButton("Over");
            buttonOver.setSize(100,50);
            buttonOver.setFont(new Font("Rockwell", Font.BOLD, 25));
            buttonOver.setForeground(coo);
            buttonOver.setLocation(150,250);

            this.add(buttonStart);

            this.add(buttonOver);

            buttonStart.addActionListener(this);

            buttonOver.addActionListener(this);
            ImageIcon imag=new ImageIcon("./images/400004.png");
//        JLabel jb=new JLabel(imag);
//
//        jb.setBounds(0,0,getWidth(),getHeight());
//
//        add(jb);

            Image image=imag.getImage();
            Image doSomImage=image.getScaledInstance(400,400,Image.SCALE_FAST);
            ImageIcon tureBackground = new ImageIcon(doSomImage);

            jb=new JLabel(tureBackground);
            jb.setBounds(0,0,getWidth(),getHeight());
            add(jb);















//
//
//
//
//
            this.setVisible(true);


        }










        /**

         * Invoked when an action occurs.

         *

         * @param e

         */

        @Override

        public void actionPerformed(ActionEvent e) {

            if(e.getSource() == buttonStart){

                this.dispose();
                String file = "./Music/music.mp3";
                    Music play = new Music(file);
                    // 开启
                    play.start();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
//                    System.out.println("stop!");
//                取消打印
                    ChessGameFrame mainFrame = new ChessGameFrame(1000, 760);
                    mainFrame.setVisible(true);

//                SwingUtilities.invokeLater(() -> {
//                    String file = "F:\\music.mp3";
//                    Music play = new Music(file);
//                    // 开启
//                    play.start();
//                    try {
//                        Thread.sleep(100);
//                    } catch (InterruptedException e1) {
//                        e1.printStackTrace();
//                    }
//                    System.out.println("stop!");
//                    ChessGameFrame mainFrame = new ChessGameFrame(1000, 760);
//                    mainFrame.setVisible(true);
//
//                });}

            }

            if(e.getSource() == buttonOver){

                this.dispose();

                System.exit(0);

            }

        }

    }



