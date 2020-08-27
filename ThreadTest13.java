package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 *  10마리의 말들이 경주하는 경마 프로그램 작성하기
 * 
 *  말은 Horse라는 이름의 클래스로 구성한다.
 */


public class ThreadTest13 {

	public static void main(String[] args) {
		
		Horse[] horse = new Horse[]{
				new Horse("01번마"),
				new Horse("02번마"),
				new Horse("03번마"),
				new Horse("04번마"),
				new Horse("05번마"),
				new Horse("06번마"),
				new Horse("07번마"),
				new Horse("08번마"),
				new Horse("09번마"),
				new Horse("10번마")
		};
		
		DisplayRace printer = new DisplayRace(horse);
		
		for(int i = 0; i< horse.length; i++){
			horse[i].start();
		}
		
		printer.start();
		
		for(int i = 0; i< horse.length; i++){
			try {
				horse[i].join();
			} catch (InterruptedException e) {}
		}
		try {
			printer.join();
		} catch (InterruptedException e) {}
		
		printRank(horse);
		
		
	}
	
	public static void printRank(Horse[] horse){
		
		System.out.println("\n\n--------------------등수----------------------");
		
		Map<Integer, String> raceMap = new HashMap<>();
		
		for(int i = 0; i< horse.length; i++){
			raceMap.put(horse[i].getRank(), horse[i].getHorseName());
		}
		
		List<Integer> raceList = new ArrayList<>(raceMap.keySet());
		
		for(int key : raceList){
			System.out.println(key +"등 :\t" + raceMap.get(key));
		}
		System.out.println("---------------------------------------------");
	}
	
}


class DisplayRace extends Thread{
	
	Horse[] list;
	
	public DisplayRace(Horse[] horse){
		list = horse;
	}
	
	@Override
	public void run() {
		
		while(true){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			printRace();
			if(Horse.setRank == list.length){
				break;
			}
		}
	}
	
	private void printRace(){
		System.out.println("**********************************************************");
		
		for(int i = 0; i< list.length; i++){
			
			System.out.print(list[i].getHorseName() + " : ");
			for(int j = 1; j <= 50; j++){
				
				if(list[i].getPosition() == j){
					System.out.print(">");
				}
				else{
					System.out.print("-");
				}
			}
			System.out.println();
		}
		System.out.println("**********************************************************\n\n");
	}
}



class Horse extends Thread implements Comparable<Horse>{
	
	public static int setRank;
	private int rank;
	private int position;
	private String name;
	
	
	public Horse(String name){
		this.name = name;
	}
	
	
	@Override
	public void run() {
		
		for(int i = 1; i<= 50; i++){
			
			position = i;
			try {
				Thread.sleep((int)(Math.random() * 50 + 70));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		setRank ++;
		rank = setRank;
		
	}
	
	@Override
	public int compareTo(Horse o) {
		
		return this.rank - o.getRank();
	}

	public int getRank() {
		return rank;
	}
	
	public int getPosition() {
		return position;
	}
	
	public String getHorseName() {
		return name;
	}
	
}