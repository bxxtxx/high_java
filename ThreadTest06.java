package kr.or.ddit.basic;

import javax.swing.JOptionPane;

public class ThreadTest06 {

	public static void main(String[] args) {
		
		Thread dit = new DataInputThread();
		Thread cdt = new Thread(new CountDownThread());
		
		dit.start();
		cdt.start();
		
	}
	
	
}


//데이터를 입력하는 스레드
class DataInputThread extends Thread{
	//입력 여부를 확인
	public static boolean inputCheck; //thread에서 공통으로 사용할 변수
	
	@Override
	public void run() {
		
		String str = JOptionPane.showInputDialog("아무거나 입력하세요.");
		System.out.println("입력값 : " + str);
		inputCheck = true;
		
	}
	
}

//카운트를 하는 스레드
class CountDownThread implements Runnable{

	@Override
	public void run() {
		for(int i = 10; i>=0; i--){
			
			if(DataInputThread.inputCheck){
				return; //run 메서드가 종료되면 thread도 멈추게 종료되게
			}
			System.out.println(i);
			try {
				Thread.sleep(1000); //1초 동안 잠시 멈춘다.
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("지정된 시간이 경과되었습니다. 프로그램을 종료합니다.");
		System.exit(0);
		
	}
	
	
}