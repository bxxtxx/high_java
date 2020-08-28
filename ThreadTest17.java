package kr.or.ddit.basic;


//은행의 입출금 작업을 스레드로 처리하는 예제


public class ThreadTest17 {

	private int balance ;		//잔액이 저장될 변수
	
	
	public synchronized int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}


	// 입금을 처리하는 메서드
	public void deposit(int money){
		
		balance += money;
		
	}
	
	// 출금을 처리하는 메서드
	// 출글이 성공하면 true 실패하면 false반환한다.
	// 동기화 영역에서 호출하는 메서드도 동기화 처리를 해주어야 안전하다~!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	
	public boolean withdraw(int money){
		synchronized (this) {
			
			if(balance >= money){ // 잔액 확인
				for(int i = 1; i<1000000; i++){   //시간떄우기용
					
				}
				balance -= money;
				System.out.println("메서드 안에서 balance = " + getBalance());
				return true;
			}
			else{
				return false;
			}
		}
	}
	
	public static void main(String[] args) {
		
		final ThreadTest17 acount = new ThreadTest17();
		acount.setBalance(10000);
		
		Runnable r1 = new Runnable() {           //익명 구현체 내에서, 밖의 지역변수를 사용하기위해선, 지역변수가 final 이여야 한다.
			
			@Override
			public void run() {
				
				boolean result = acount.withdraw(6000); //6000원 출금
				System.out.println("스레드에서 result : " + result + ", balance = " + acount.getBalance());
				
			}
			
		};
		// 익명 구현체 끝...-----------------------------
		
		Thread th = new Thread(r1);
		Thread th2 = new Thread(r1);
		
		th.start();
		th2.start();
		
		
		
	}
	
	
}
