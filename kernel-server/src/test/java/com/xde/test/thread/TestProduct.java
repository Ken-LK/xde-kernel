package com.xde.test.thread;

/**
 * @author <a href="mailto:liukailk.ken@gmail.com"> Ken </a>
 * @date 2020/10/23 5:15 下午
 **/
public class TestProduct {

    public static void main(String[] args) {

        Contains contains = new Contains();

     new Productor(contains).start();
     new Consumer(contains).start();

    }

}

// 生产者
class Productor extends Thread {

    Contains contains;

    public Productor(Contains contains) {
        this.contains = contains;

    }

    @Override
    public void run() {

        for (int i = 0; i < 100; i++) {

            try {
                contains.push(new Chicken(i));
                System.out.println("生产了：" + i + "只鸡");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}


// 消费者
class Consumer extends Thread {
    Contains contains;

    public Consumer(Contains contains) {
        this.contains = contains;

    }

    @Override
    public void run() {

        for (int i = 0; i < 100; i++) {

            try {
                Chicken chicken = contains.pop();
                System.out.println("消费了：" + chicken);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

// 缓冲区
class Contains {

    Chicken[] chickens = new Chicken[10];

    int count = 0;

    // 生产产品
    public synchronized void push(Chicken chicken) throws InterruptedException {

        // 产品生产满了等待
        if (count == 10) {

            this.wait();
        }
        chickens[count] = chicken;
        count++;
        this.notifyAll();
    }

    // 消费产品
    public synchronized Chicken pop() throws InterruptedException {
        // 产品消费完了等待
        if (count == 0) {
            this.wait();
        }
        count--;
        Chicken chicken = chickens[count];
        this.notifyAll();
        return chicken;
    }


}

class Chicken {

    int id;

    public Chicken(int id) {
        this.id = id;
    }
}
