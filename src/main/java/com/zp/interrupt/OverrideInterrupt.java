package com.zp.interrupt;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class OverrideInterrupt extends Thread {
    private final Socket socket;
    private  final InputStream in;

    public OverrideInterrupt(Socket socket,InputStream in){
        this.in = in;
        this.socket = socket;
    }
    private void t(){

    }

    public void interrupt(){
        try {
            //关闭底层套接字
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //同时中断线程
            super.interrupt();
        }
    }
}
