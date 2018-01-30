package com.zp;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

public class PipTransfer {

    private static class Print implements Runnable{

        private PipedReader in;

        public Print(PipedReader in){
            this.in=in;
        }
        public void run() {
            int receive = 0;
            try {
                while((receive=in.read())!=-1){
                    System.out.println((char)receive);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception{
        PipedWriter out = new PipedWriter();
        PipedReader in = new PipedReader();
        out.connect(in);
        Thread t1 = new Thread(new Print(in));
        t1.start();
        int receive = 0;
        try {
            while((receive=in.read())!=-1){
                System.out.println((char)receive);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            out.close();
        }
    }


}
