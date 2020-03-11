package com.itdragon.keyword;

/**
 * synchronized 关键字，可以修饰方法，也可以修饰代码块。建议采用后者，通过减小锁的粒度，以提高系统性能。
 * synchronized 关键字，如果以字符串作为锁，请注意String常量池的缓存功能和字符串改变后锁是否的情况。
 * synchronized 锁重入，当一个线程得到了一个对象的锁后，再次请求此对象时是可以再次得到该对象的锁。
 * synchronized 同异步，一个线程获得锁后，另外一个线程可以执行非synchronized修饰的方法，这是异步。若另外一个线程执行任何synchronized修饰的方法则需要等待，这是同步
 * synchronized 类锁，用static + synchronized 修饰则表示对整个类进行加锁
 */
public class ITDragonSynchronized {
	
    private void thisLock () {  // 对象锁  
        synchronized (this) {  
            System.out.println("this 对象锁!");  
        }  
    }  
      
    private void classLock () {  // 类锁  
        synchronized (ITDragonSynchronized.class) {  
            System.out.println("class 类锁!");  
        }  
    }  
      
    private Object lock = new Object();  
    private void objectLock () {  // 任何对象锁  
        synchronized (lock) {  
            System.out.println("object 任何对象锁!");  
        }  
    }  
      
    private void stringLock () {  // 字符串锁，注意String常量池的缓存功能  
        synchronized ("string") { // 用 new String("string")  t4 和 t5 同时进入。用string t4完成后，t5在开始
            try {  
                for(int i = 0; i < 3; i++) {  
                    System.out.println("thread : " + Thread.currentThread().getName() + " stringLock !");  
                    Thread.sleep(500);       
                }  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
      
    private String strLock = "lock";  // 字符串锁改变  
    private void changeStrLock () {  
        synchronized (strLock) {  
            try {  
                System.out.println("thread : " + Thread.currentThread().getName() + " changeLock start !");  
                strLock = "changeLock";  
                Thread.sleep(500);  
                System.out.println("thread : " + Thread.currentThread().getName() + " changeLock end !");  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
    
    private synchronized void method1() {  // 锁重入
        System.out.println("^^^^^^^^^^^^^^^^^^^^ method1");  
        method2();  
    }  
    private synchronized void method2() {  
        System.out.println("-------------------- method2");  
        method3();  
    }  
    private synchronized void method3() {  
        System.out.println("******************** method3");  
    }  
    
    private synchronized void syncMethod() {  
        try {  
            System.out.println(Thread.currentThread().getName() + " synchronized method!");  
            Thread.sleep(2000);  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
    }  
      
    // 若次方法也加上了 synchronized，就必须等待t1线程执行完后，t2才能调用  ，两个synchronized块之间具有互斥性，synchronized块获得的是一个对象锁，换句话说，synchronized块锁定的是整个对象
    private void asyncMethod() {  
        System.out.println(Thread.currentThread().getName() + " asynchronized method!");  
    } 
      
    // 类锁：static + synchronized 修饰则表示类锁，打印的结果是 thread1 线程先执行完，然后在执行thread2线程。若没有被static 修饰，则thread1 和 thread2 几乎同时执行，同时结束
	private synchronized void classLock(String args) {
		System.out.println(args + "start......");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(args + "end......");
	}
	
    public static void main(String[] args) throws Exception {  
        final ITDragonSynchronized itDragonSynchronized = new ITDragonSynchronized();  
        System.out.println("------------------------- synchronized 代码块加锁 -------------------------");
        Thread thread1 = new Thread(new Runnable() {  
            @Override  
            public void run() {  
                itDragonSynchronized.thisLock();  
            }  
        });  
        Thread thread2 = new Thread(new Runnable() {  
            @Override  
            public void run() {  
                itDragonSynchronized.classLock();  
            }  
        });  
        Thread thread3 = new Thread(new Runnable() {  
            @Override  
            public void run() {  
                itDragonSynchronized.objectLock();  
            }  
        });  
        thread1.start();  
        thread2.start();  
        thread3.start();  
        Thread.sleep(2000);
        System.out.println("------------------------- synchronized 字符串加锁 -------------------------");
        // 如果字符串锁，用new String("string") t4，t5线程是可以获取锁的，如果直接使用"string" ，若锁不释放，t5线程一直处理等待中  
        Thread thread4 = new Thread(new Runnable() {  
            @Override  
            public void run() {  
                itDragonSynchronized.stringLock();  
            }  
        }, "t4");  
        Thread thread5 = new Thread(new Runnable() {  
            @Override  
            public void run() {  
                itDragonSynchronized.stringLock();  
            }  
        }, "t5");  
        thread4.start();  
        thread5.start();  
          
        Thread.sleep(3000);
        System.out.println("------------------------- synchronized 字符串变锁 -------------------------");
        // 字符串变了，锁也会改变，导致t7线程在t6线程未结束后变开始执行,但一个对象的属性变了，不影响这个对象的锁。  
        Thread thread6 = new Thread(new Runnable() {  
            @Override  
            public void run() {  
                itDragonSynchronized.changeStrLock();  
            }  
        }, "t6");  
        Thread thread7 = new Thread(new Runnable() {  
            @Override  
            public void run() {  
                itDragonSynchronized.changeStrLock();  
            }  
        }, "t7");  
        thread6.start();  
        thread7.start(); 
        
        Thread.sleep(2000);
        System.out.println("------------------------- synchronized 锁重入 -------------------------");
        Thread thread8 = new Thread(new Runnable() {  
            @Override  
            public void run() {  
                itDragonSynchronized.method1();  
            }  
        }, "t8");  
        thread8.start(); 
        Thread thread9 = new Thread(new Runnable() {  
            @Override  
            public void run() {  
                SunClass sunClass = new SunClass();  
                sunClass.sunMethod();  
            }  
        }, "t9");  
        thread9.start(); 
        
        Thread.sleep(2000);
        System.out.println("------------------------- synchronized 同步异步 -------------------------");
        Thread thread10 = new Thread(new Runnable() {  
            @Override  
            public void run() {  
                itDragonSynchronized.syncMethod();  
            }  
        }, "t10");  
        Thread thread11 = new Thread(new Runnable() {  
            @Override  
            public void run() {  
            	itDragonSynchronized.asyncMethod();  
            }  
        }, "t11");  
        thread10.start(); 
        thread11.start(); 
        
        Thread.sleep(2000);
        System.out.println("------------------------- synchronized 同步异步 -------------------------");
        ITDragonSynchronized classLock1 = new ITDragonSynchronized();
        ITDragonSynchronized classLock2 = new ITDragonSynchronized();
		Thread thread12 = new Thread(new Runnable() {
			@Override
			public void run() {
				classLock1.classLock("classLock1");
			}
		});
		thread12.start();
		Thread thread13 = new Thread(new Runnable() {
			@Override
			public void run() {
				classLock2.classLock("classLock2");
			}
		});
		thread13.start();
    }  
    
    // 有父子继承关系的类，如果都使用了synchronized 关键字，也是线程安全的。  
    static class FatherClass {  
        public synchronized void fatherMethod(){  
            System.out.println("#################### fatherMethod");  
        }  
    }  
      
    static class SunClass extends FatherClass{  
        public synchronized void sunMethod() {  
            System.out.println("@@@@@@@@@@@@@@@@@@@@ sunMethod");  
            this.fatherMethod();
        }  
    }  
}
