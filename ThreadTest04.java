package kr.or.ddit.basic;

/*
 *  1~20억까지의 합계를 구하는 프로그램을 하나의 스레드가 단독으로 처리할 때와
 *  여러개의 스레드가 협력해서 처리할 때의 경과시간을 비교해보자.
 * 
 * 
 * 
 */



public class ThreadTest04 {

	public static void main(String[] args) {
		
		//단독으로 처리하는 스레드
		SumThread sm = new SumThread(1L, 2_000_000_000L);
		
		
		SumThread[] smArr = new SumThread[]{
			
			new SumThread(1L, 500_000_000L),
			
			new SumThread(500_000_001L, 1_000_000_000L),
			
			new SumThread(1_000_000_001L, 1_500_000_000L),
			
			new SumThread(1_500_000_001L, 2_000_000_000L),
			
		};
		
		
		
		
		long start = System.currentTimeMillis();
		sm.start();
		try {
			sm.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		long end = System.currentTimeMillis();
		System.out.println("단독처리했을때 경과시간 : " + (end - start));
		
		
		start = System.currentTimeMillis();
		for(int i = 0; i<smArr.length; i++){
			
			smArr[i].start();
			
		}
		
		for(SumThread sms : smArr){
			try {
				sms.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		end = System.currentTimeMillis();
		
		System.out.println("멀티처리했을때 경과시간 : " + (end - start));
		
	}
	
}


class SumThread extends Thread{
	
	private long start, end; //합계를 구할 영역의 시작값과 종료값
	
	
	SumThread(long start, long end){
		this.start = start;
		this.end = end;
	}
	
	
	@Override
	public void run() {
		
		long sum = 0L;
		for(long i = start; i<= end; i++){
			sum += i;
		}
		System.out.println("합계 : " + sum);
	}
	
	
}