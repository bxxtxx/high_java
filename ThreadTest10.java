package kr.or.ddit.basic;



// 스레드의 상태를 출력하는 프로그램
public class ThreadTest10 {

	public static void main(String[] args) {
		
		StatePrintThread th = new StatePrintThread(new TargetThread());
		th.start();
		
	}
	
	
	
}

// 스레드 상태의 검사 대상이 되는 스레드

class TargetThread extends Thread{
	
	@Override
	public void run() {

		for(long i = 1L; i<=20000000000L; i++){}; //시간 지연용
		
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
		}
		
		for(long i = 1L; i<=20000000000L; i++){}; //시간 지연용
		
	}
	
}


// 검사 대상 스레드의 상태를 출력하는 스레드
class StatePrintThread extends Thread{
	private TargetThread target;
	
	public StatePrintThread(TargetThread target){
		this.target = target;
	}
	
	
	@Override
	public void run() {
		
		while(true){
			
			//스레드의 상태값 구하기
			Thread.State state = target.getState();
			System.out.println("TargetThread의 상태값 : " + state);
			
			if(state == Thread.State.NEW){ //스레드가 new 상태이면
				target.start(); //타겟 스레드 실행
				
			}
			
			if(state == Thread.State.TERMINATED){
				break;
			}
			
			try {
				Thread.sleep(500);
			} catch (Exception e) {
			}
			
			
			
		}
		
		
	
	}
	
	
}