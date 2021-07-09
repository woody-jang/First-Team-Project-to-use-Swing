package Lottery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 랜덤 숫자 만들어내는 클래스 
class MakeNumber {
	private MakeNumber() {
	}
	
	public static List<Integer> randomNum() {
		List<Integer> lottoNumber = new ArrayList<>();
		
		while (lottoNumber.size() != 7) {
			int temp = (int) (Math.random() * 1000) % 45 + 1;
			if (!lottoNumber.contains(temp))
				lottoNumber.add(temp);
		}
		
		return lottoNumber;
	}
	
	public static void randomNum(List<Integer> selectedNumber) {
		while (selectedNumber.size() != 6) {
			int temp = (int) (Math.random() * 1000) % 45 + 1;
			if (!selectedNumber.contains(temp))
				selectedNumber.add(temp);
		}
	}
	
	public static int[] randomNumArray() {
		int[] lottoNumber = new int[6];
		List<Integer> tempNum = randomNum();
		tempNum.remove(6);
		int cnt = 0;
		
		for (int i : tempNum) {
			lottoNumber[cnt++] = i;
		}
		
		Arrays.sort(lottoNumber);
		
		return lottoNumber;
	}

}
