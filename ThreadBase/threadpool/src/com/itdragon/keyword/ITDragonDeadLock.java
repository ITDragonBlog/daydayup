package com.itdragon.keyword;

/**
 * 死锁: 线程A拥有锁一，却还想要锁二。线程B拥有锁二，却还想要锁一。两个线程互不相让，两个线程将永远等待。
 * 避免: 在设计阶段，了解锁的先后顺序，减少锁的交互数量。
 * 排查: 
 * 第一步：控制台输入 jps 用于获得当前JVM进程的pid
 * 第二步：jstack pid 用于打印堆栈信息 
 * "Thread-1" #11 prio=5 os_prio=0 tid=0x0000000055ff1800 nid=0x1bd4 waiting for monitor entry [0x0000000056e2e000]
 * - waiting to lock <0x00000000ecfdf9d0> - locked <0x00000000ecfdf9e0> 
 * "Thread-0" #10 prio=5 os_prio=0 tid=0x0000000055ff0800 nid=0x1b14 waiting for monitor entry [0x0000000056c7f000]
 * - waiting to lock <0x00000000ecfdf9e0> - locked <0x00000000ecfdf9d0> 
 * 可以看出，两个线程持有的锁都是对方想要得到的锁(得不到的永远在骚动)，而且最后一行也打印了 Found 1 deadlock.
 */
public class ITDragonDeadLock {
	
	private final Object left = new Object();
    private final Object right = new Object();
    
    public void leftRight(){
        synchronized (left) {
            try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
            synchronized (right) {
                System.out.println("leftRight end!");
            }
        }
    }
    
    public void rightLeft(){
        synchronized (right) {
            try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
            synchronized (left) {
                System.out.println("rightLeft end!");
            }
        }
    }
    
    public static void main(String[] args) {
    	ITDragonDeadLock itDragonDeadLock = new ITDragonDeadLock();
		Thread thread1 = new Thread(new Runnable() {
			@Override
			public void run() {
				itDragonDeadLock.leftRight();
			}
		});
		thread1.start();
		Thread thread2 = new Thread(new Runnable() {
			@Override
			public void run() {
				itDragonDeadLock.rightLeft();
			}
		});
		thread2.start();
	}

}
