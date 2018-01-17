package com.itdragon.keyword;

/**
 * synchronized 同步异步
 * 
 * @author itdragon
 *
 */
public class ITDragonDirtyRead {
	
	private String username = "ITDragon";
	private Double amount = 100.0;
	
	public synchronized void setAmount(String username, Double amount){
		this.username = username;
		try {
			Thread.sleep(2000); // 余额计算预计耗时两秒
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.amount = amount;
		System.out.println("setValue : username = " + username + " , amount = " + amount);
	}
	
	public synchronized void getAmount(){
		System.out.println("getValue : username = " + this.username + " , amount = " + this.amount);
	}
	
	/**
	 * 场景：1.余额计算预计耗时两秒 ， 2.一秒后查询余额
	 * 若给 setValue 和 getValue 两个方法添加synchronized关键字修饰，则setValue 方法执行结束后再执行getValue，否则会出数据的不一致。
	 */
	public static void main(String[] args) throws Exception{
		final ITDragonDirtyRead dirtyRead = new ITDragonDirtyRead();
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				dirtyRead.setAmount("ITDragon", 50.0); // 消费了50 RMB		
			}
		});
		thread.start();
		Thread.sleep(1000); // 还未等余额计算结束则查看余额
		dirtyRead.getAmount();
	}

}
