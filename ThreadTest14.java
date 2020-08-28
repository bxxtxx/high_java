package kr.or.ddit.basic;


/*
 *	Thread의 stop() 메서드를 호출하면 스레드가 바로 멈춘다.
 *	이 때 사용하던 자원을 정리하지 못하고 프로그램이 종료되어 나중에 실행되는 프로그램에
 *	영향을 줄 수 있다. 그래서 stop()메서드는 비추천으로 되어있다.
 * 
 */

public class ThreadTest14 {

	public static void main(String[] args) {
		
		/*
		
		ThreadStopTest1 th1 = new ThreadStopTest1();
		
		th1.start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		
		//th1.stop();
		th1.setStop(true);   //이렇게 종료시켜야 안전하게, 데드락없이 종료가능
		
		*/
		
		
		
		/*
		
		
		ThreadStopTest2 th2 = new ThreadStopTest2();
		
		th2.start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {}
		
		th2.interrupt();
		
		*/
		
		
		
		ThreadStopTest3 th3 = new ThreadStopTest3();
		
		th3.start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {}
		
		th3.interrupt();
		
		
	}
	
}



// 쓰레드를 멈추게 하는 연습용 쓰레드
class ThreadStopTest1 extends Thread{
	
	private boolean stop;
	
	public void setStop(boolean stop){
		
		this.stop = stop;
		
	}
	
	
	@Override
	public void run() {
		
		while(!stop){
			System.out.println("쓰레드 실행 중....");
		}
		
		System.out.println("자원 정리...");
		System.out.println("스레드 종료...");
	
	}
	
}









// interrupt() 메서드를 이용하여 스레드를 멈추게하는 연습용 스레드
class ThreadStopTest2 extends Thread{
	
	@Override
	public void run() {
		// 방법1 ==> interrupt()메서드와 sleep()메서드를 이용하는 방법
		try {
			while(true){
				System.out.println("Thread 실행중....");
				Thread.sleep(1);                         //굉장히 짧은 sleep 메소드 이용하기! --> interrupt를 위해서
			}
		} catch (InterruptedException e) {
			System.out.println("자원정리 택1");
		}
		System.out.println("자원정리 택2");
		System.out.println("스레드 종료");
	}
	
}






//interrupt() 메서드를 이용하여 스레드를 멈추게하는 연습용 스레드
class ThreadStopTest3 extends Thread{
	
	@Override
	public void run() {
		
		// 방법2
		while(true){
			System.out.println("스레드 실행 중...");
			
			//interrupt() 메서드가 호출 되었는지 여부를 검사한다.
			
			//검사방법1 --> 인스턴스 메서드인 isInterrupted() 메서드를 이용해서 검사한다.
			//			  isInterrupted() 메서드는 interrupt()메서가 호출되면 true를 반환한다.
			
			if(this.isInterrupted()){
				break;
			}
			
		}
		
		System.out.println("자원정리 택2");
		System.out.println("스레드 종료");
	}
	
}






//interrupt() 메서드를 이용하여 스레드를 멈추게하는 연습용 스레드
class ThreadStopTest4 extends Thread{
	
	@Override
	public void run() {
		
		// 방법2
		while(true){
			System.out.println("스레드 실행 중...");
			
			//interrupt() 메서드가 호출 되었는지 여부를 검사한다.
			
			//검사방법2 --> Thread의 정적 메서드인 interrupted() 메서드를 이용해서 검사한다.
			//		  --> interrupt() 메서드가 호출되면 true를 반환한다.
			
			if(Thread.interrupted()){
				break;
			}
			
		}
		
		System.out.println("자원정리 택2");
		System.out.println("스레드 종료");
	}
	
}

