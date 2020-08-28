package kr.or.ddit.basic;

public class ThreadTest03 {

	
	public static void main(String[] args) {

		// 스레드가 수행되는 시간 체크해보기... 
		
		Thread th = new Thread(new MyRunner());
		
		
		//System.currentTimeMillis() : 1970년 1월 1일 0시 0분 0초(표준시간)부터 경과한 시간을 ms 단위로
		
		long startTime = System.currentTimeMillis();
		
		th.start();
		
		try {
			th.join(); //현재 실행중인 스레드에서 대상이되는 스레드가 (변수th)가 종료될 때까지 기다린다.
		} catch (InterruptedException e) {
		}
		

		long endTime = System.currentTimeMillis();
		
		System.out.println("경과시간 : " + (endTime - startTime));
	}
	
}


class MyRunner implements Runnable{

	@Override
	public void run() {
		long sum = 0L;
		
		
		
		for(long i =1L ; i <= 1_000_000_000L; i++){
			sum += i;
		}
		System.out.println("합계 : " + sum);
		
	}
	
}