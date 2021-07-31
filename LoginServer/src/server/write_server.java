package server;

import java.util.Scanner;

public class write_server implements Runnable {
    Thread th;
    public write_server(){
        this.th=new Thread(this);
        th.start();
    }
    @Override
    public void run() {
        while(true){
            Scanner input=new Scanner(System.in);
            System.out.println("Enter 'add,user name, pass");
            System.out.println("Enter exit to close");
            String s1=input.nextLine();
            if(s1.equalsIgnoreCase("exit")){
                ReadThreadServer.file_write();
                break;
            }
            else{
                String sa[]=s1.split(",");
                Server.userMap.put(sa[1],sa[2]);
                ReadThreadServer.file_write();
            }
        }

    }
}
