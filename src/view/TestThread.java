package view;

public class TestThread extends Thread {
    private Chessboard chesB;
    public TestThread(Chessboard chessboard){
        chesB=chessboard;
    }

    public void run() {
        int a=0;
        while(true){

            try {
                if (a<chesB.getIntStoreHuiQI()){
                sleep(2*1000);
                String[] data=chesB.HUiFang(a).split("\n");
                chesB.HuiQIGame(data);
                chesB.repaint();
                a++;
//                System.out.println("一分钟运行一次");
                }
                else {
                    break;
                }
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }
}
