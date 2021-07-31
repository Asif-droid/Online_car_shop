package sample;

import util.NetworkUtil;
import util.rqst_class;

import java.io.IOException;

public class refresh_thread implements Runnable{

    private final Thread th;
    private final Main main;
    private NetworkUtil networkUtil;
    private rqst_class rq;
    public refresh_thread(Main main, rqst_class rq){
        this.main=main;
        this.th=new Thread(this);
        th.start();
        this.rq=rq;
    }


    @Override
    public void run() {
        while (true){
            try {
                main.getNetworkUtil().write(rq);
                //networkUtil.write(rq);
                System.out.println("hello");
                Thread.sleep(15000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }


    }


}
