package test;

public class test_thread implements Runnable{
    private  final Thread th;
    int s;
    public test_thread(int s){
        this.th=new Thread(this);
        th.start();
        this.s=s;
    }
    @Override
    public void run() {
        while(true){
            s-=1;
            System.out.println(s);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
