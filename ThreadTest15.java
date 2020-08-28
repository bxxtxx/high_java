package kr.or.ddit.basic;

// 스레드에서 객체를 공통으로 사용하는 예제 연습

/*
 * 	- 원주율을 계산하는 스레드와 원주율을 출력하는 스레드가 있다.
 *  - 원주율을 저장하는 객체가 필요하다.
 *  - 이 객체를 두 스레드에서 공통으로 사용해서 처리한다.
 * 
 */


public class ThreadTest15 {

	public static void main(String[] args) {
		
		ShareData sd = new ShareData();
		
		
		CalcPIThread cpt = new CalcPIThread(sd);
		PrintPIThread ppt = new PrintPIThread(sd);
		
		cpt.start();
		ppt.start();
		
		
	}
	
	
}



// 원주율을 관리하는 객체(클래스) 작성 (공통으로 사용 할 클래스)
class ShareData{
	
	public double result; // 계산된 원주율이 저장될 변수
	
	//volatile ==> 이 키워드가 붙은 변수는 컴파일러의 최적화 대상에서 제외 된다.   -- 데이터를 CPU 캐시안에 저장하지 않는다. --무조건 RAM에 있는 데이터를 가져와라!
	//             즉, 값이 변경되는 즉시 변수에 적용시킨다.
	public volatile boolean isOk;  // 계산이 완료되었는지 여부를 나타내는 변수 (계산이 완료되면 true가 된다.)   (사실 getter setter로 만들어야하는거 알지)
	
}

// 원주율을 계산만 하는 스레드
class CalcPIThread extends Thread{
	
	private ShareData sd;
	
	public CalcPIThread(ShareData sd) {
		this.sd = sd;
	}
	
	@Override
	public void run() {
		/*
		 	원주율 = (1/1 - 1/3 + 1/5 - 1/7 + 1/9...) * 4;
		 */
		
		double sum = 0;
		
		for(int i = 1; i< 1000000000; i += 2){
				
			if((i/2)%2 == 0){
				sum += (1.0/i);
			}
			else{
				sum -= (1.0/i);
			}
		}
		
		sd.result = sum * 4;
		sd.isOk = true;
		System.out.println("계산완료");
	}
	
}


// 원주율 계산이 완료되면 출력만 하는 스레드
class PrintPIThread extends Thread{
	
	private ShareData sd;
	
	public PrintPIThread( ShareData sd) {
		this.sd = sd;
	}
	
	
	@Override
	public void run() {
		while(true){
			if(sd.isOk == true){
				break;
			}
		}
		// 계산된 원주율 출력하기
		System.out.println();
		System.out.println("결과 : " + sd.result);
		System.out.println("PI : " + Math.PI);
	}
	
	
}