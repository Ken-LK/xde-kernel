package com.xde.test.thread;

/**
 * @author <a href="mailto:liukailk.ken@gmail.com"> Ken </a>
 * @date 2020/10/22 7:37 下午
 **/
public class TestThread {


    public static void main(String[] args) {

        Good good = ()->{
            System.out.println("价格1000");
        };

        System.out.println("主线线程优先级："+Thread.currentThread().getPriority());

        good.price();

        new Thread(()->{

            for (int i = 0; i < 100; i++) {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程开始:"+i);
            }

        }).start();

    }

}


interface Good {

    void price();
}

class Animal implements Runnable{

    @Override
    public void run() {



    }
}



