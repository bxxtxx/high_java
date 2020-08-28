package kr.or.ddit.basic;

import javax.swing.JOptionPane;

/*
 *	컴퓨터와 가위 바위 보를 진행하는 프로그램을 작성하시오 
 * 
 *  컴퓨터의 가위 바위 보는 난수를 이용해서 구하고
 *  
 *  사용자의 가위 바위 보는 showInputDialog()를 이용해서 입력받는다
 *  
 *  입력시간은 5초로 제한하고 카운트 다운을 한다
 *  
 *  5초안에 입력이 없으면 게임 진것으로 처리한다.
 *  
 *  5초안에 입력이 완료되면 승패를 구해서 출력한다.
 *  
 *  
 *  결과 예시
 *   -- 결 과 --
 *   컴퓨터 : 가위
 *   사용자 : 바위
 *   결 과 : 당신이 이겼습니다.
 */




public class ThreadTest07 {
	
	
	public enum example{A,B,C};
	
	
	public enum RPS{ 
	
	ROCK("바위",0), PAPER("보",1), SCISSORS("가위",2);
	
	private String name;
	private int num;
	
	RPS(String name, int num){
		this.name = name;
		this.num = num;
	}

	public int getNum() {
		return num;
	}
	
	public String getName() {
		return name;
	}
	
	};
	
	
	/********************************************************/
	
	public static void main(String[] args) {
		
		new ThreadTest07().start();
		
		
		
	}
	
	
	void start(){
		
		Thread com = new Com();
		Thread user = new User();
		
		com.start();
		user.start();
		
		try {
			com.join();
			user.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		

		System.out.println("-- 결 과 --");
		System.out.println("컴퓨터 : " + Com.ans);
		System.out.println("사용자 : " + User.ans);
		System.out.print("결 과 :");
		if(Com.ans.equals(User.ans)){
			System.out.println("비겼습니다.");
		}
		else if(Com.ans.equals(RPS.SCISSORS.getName())){
			if(User.ans.equals(RPS.ROCK.getName())) System.out.println("당신이 이겼습니다.");
			else System.out.println("컴퓨터가 이겼습니다");
		}
		else if(Com.ans.equals(RPS.ROCK.getName())){
			if(User.ans.equals(RPS.PAPER.getName())) System.out.println("당신이 이겼습니다.");
			else System.out.println("컴퓨터가 이겼습니다");
		}
		else if(Com.ans.equals(RPS.PAPER.getName())){
			if(User.ans.equals(RPS.SCISSORS.getName())) System.out.println("당신이 이겼습니다.");
			else System.out.println("컴퓨터가 이겼습니다");
		}
		
	}
	
}




class Com extends Thread{
	public static String ans;
	@Override
	public void run() {
		int num = (int)(Math.random()*1000 + 1) % 3;
		switch (num) {
		case 0: ans = "가위";  break;
		case 1: ans = "바위";break;
		case 2: ans = "보";break;
		}
		
		for(int i = 5; i >=0; i--){
			System.out.println(i);
			if(User.playerinput == true){
				return;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("입력 제한시간이 초과되었습니다. 프로그램을 종료합니다.");
		System.exit(0);
	}
}



class User extends Thread{
	
	public static boolean playerinput;
	public static String ans;
	
	@Override
	public void run() {
		
		ans = JOptionPane.showInputDialog("가위 바위 보, 세 개중 하나를 선택하세요");
		playerinput = true;
		
	}
}




