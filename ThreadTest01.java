package kr.or.ddit.basic;

public class ThreadTest01 {

	
	public static void main(String[] args) {
		
		// 싱글 스레드 프로그램 : main안에서 실행되는 것이라고 보면됨
		
		for(int i = 1 ; i <= 200; i++){
			System.out.print("*");
			if(i%10 == 0){
				System.out.println();
			}
		}
		
		System.out.println();
		System.out.println();
		
		for(int i = 1 ; i <= 200; i++){
			System.out.print("$");
			if(i%10 == 0){
				System.out.println();
			}
		}
	}
}
