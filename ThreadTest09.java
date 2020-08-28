package kr.or.ddit.basic;

import javax.swing.JOptionPane;


// 데몬 스레드 연습 ==> 자동 저장하는 스레드

public class ThreadTest09 {

	
	public static void main(String[] args) {
		
		Thread demon = new AutoSaveThread();
		
		//데몬 스레드로 설정하기 --> 반드시 start() 메서드를 호출하기 전에 설정해야한다.
		demon.setDaemon(true);
		demon.start();

		
		while(true){
			
			String ans = JOptionPane.showInputDialog("입력 >");
			System.out.println(ans);
			if(ans.equals("x")){
				break;
			}
			
		}
		
	}
	
	
	
}

// 자동 저장하는 스레드 (3초에 한번씩 저장하는 스레드)
class AutoSaveThread extends Thread{
	
	//지금까지의 작업내용을 저장하는 메서드
	public void save(){
		System.out.println("작업내용을 저장합니다...");
		
	}
	
	@Override
	public void run() {
		
		while(true){
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			save();
		}
	
	}
	
}