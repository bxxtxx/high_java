package kr.or.ddit.basic;

public class ThreadTest16 {

	
	public static void main(String[] args) {
		
		ShradObject sObj = new ShradObject();
		TestThread th1 = new TestThread("테스트1", sObj);
		TestThread th2 = new TestThread("테스트2", sObj);
		
		th1.start();
		th2.start();
	}
	
	
}


class ShradObject{
	
	private int sum = 0;
	
	
	//동기화 처리하기
	/*public synchronized void add(){ //동기화 방법 1 : 메서드 자체에 동기화 설정을 한다.*/		
	
	public synchronized void add(){  		
		synchronized (this) { //동기화 방법 2 : 동기화 블럭으로 설정한다.
			int n = sum;
			
			n += 10;
			
			sum = n;
			
			System.out.println(Thread.currentThread().getName() + " 합계 : " + sum);
			
		}
		
	}
	
}


class TestThread extends Thread{
	
	private ShradObject sObj;
	
	public TestThread(String name, ShradObject sObj) {
		super(name);   //스레드의 name 설정 굿굿
		this.sObj = sObj;
	}
	
	
	@Override
	public void run() {

		for(int i = 0 ; i< 10; i++){
			
			sObj.add();
			
		}
	}
	
	
	
	
	
}