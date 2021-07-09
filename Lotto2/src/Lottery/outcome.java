package Lottery;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Collections;
import java.util.List;

import javax.swing.*;

/*
4) 당첨번호, 총 구매 수, 재도전, 종료 버튼 
*  숫자 90개(1~45) : 당첨됐을 때 숫자 색 / 당첨 안됐을 때 숫자 색 
*  당첨, 낙첨, 1등, 2등, 3등, 4등, 5등 글씨 
*/
class outcome extends JFrame {
	public outcome(int[][] selectedNum) {
		this.setLocation(550, 250);
		// 메인 패널
		JPanel totalPnl = new JPanel();
		totalPnl.setLayout(new BoxLayout(totalPnl, BoxLayout.Y_AXIS));

//		String a = "";
		List<Integer> outcomeNum = MakeNumber.randomNum();

		int bonusNum = outcomeNum.get(6); // 보너스넘버
		outcomeNum.remove(6);
		Collections.sort(outcomeNum);

		// 당첨번호를 담을 패널
		JPanel winNumPnl = new JPanel();
		winNumPnl.setOpaque(true);
		winNumPnl.setBackground(Color.white);
		
		// 당첨번호 글씨용 레이블
		URL winNumIconURL = outcome.class.getClassLoader().getResource("winningNum.gif");
		ImageIcon winNumIcon = new ImageIcon(winNumIconURL);
		JLabel winNum = new JLabel(winNumIcon);
		winNumPnl.add(winNum);
		
		// 당첨번호 숫자용 레이블
		for (int i = 0; i < 6; i++) {
			int nowNum = outcomeNum.get(i);
			URL winNumsIconURL = outcome.class.getClassLoader().getResource(nowNum + ".gif");
			ImageIcon winNumsIcon = new ImageIcon(winNumsIconURL);
			JLabel winNums = new JLabel(winNumsIcon);
			winNumPnl.add(winNums);
		}
		
		// 당첨번호 + 보너스숫자용 레이블
		URL bonusURL = outcome.class.getClassLoader().getResource("add.gif");
		ImageIcon bonus = new ImageIcon(bonusURL);
		JLabel lbl = new JLabel(bonus);
		
		URL bonus2URL = outcome.class.getClassLoader().getResource(bonusNum + ".gif");
		ImageIcon bonus2 = new ImageIcon(bonus2URL);
		JLabel lbl2 = new JLabel(bonus2);

		winNumPnl.add(lbl);
		winNumPnl.add(lbl2);
		
		totalPnl.add(winNumPnl);

		for (int i = 0; i < selectedNum.length; i++) {
			// 각 선택숫자들을 담을 패널
			JPanel eachNumsPnl = new JPanel();
			eachNumsPnl.setOpaque(true);
			eachNumsPnl.setBackground(Color.white);
			
			// 순서의 알파벳을 담을 레이블
			URL headLblIconURL = outcome.class.getClassLoader().getResource((char)(i + 65) + ".gif");
			ImageIcon headLblIcon = new ImageIcon(headLblIconURL);
			JLabel headLbl = new JLabel(headLblIcon);
			eachNumsPnl.add(headLbl);
			
			// 통계를 위해 등수를 담을 변수
			
			// 몇개 맞췄는지 담을 변수
			int count = 0;
			boolean bonusChk = false; // 보너스 번호를 가졌는지 아닌지 체크

			for (int j = 0; j < 6; j++) {
				int tmpNum = selectedNum[i][j];
				boolean checkNum = outcomeNum.contains(tmpNum);
				if (checkNum) {
					// 라벨 추가 당첨이니까 컬러 라벨 imageicon("number\\" + 숫자 + ".gif")
					URL withinLblIconURL = outcome.class.getClassLoader().getResource(tmpNum + ".gif");
					ImageIcon withinLblIcon = new ImageIcon(withinLblIconURL);
					JLabel withinLbl = new JLabel(withinLblIcon);
					eachNumsPnl.add(withinLbl);
					count++;
				} else {
					// 라벨 추가 미당첨이니까 흑백 라벨 imageicon("number\\" + 숫자 + "b.gif")
					URL withoutLblIconURL = outcome.class.getClassLoader().getResource(tmpNum + "b.gif");
					ImageIcon withoutLblIcon = new ImageIcon(withoutLblIconURL);
					JLabel withoutLbl = new JLabel(withoutLblIcon);
					eachNumsPnl.add(withoutLbl);
				}
				if (tmpNum == bonusNum)
					bonusChk = true; // 보너스 넘버를 가지고 있는걸 체크함
			}
			// 한묶음의 선택한 숫자 당첨 확인이 끝나고 나서
			// count가 5라면 이 배열이 뽀나쓰 넘버를 들고있는지 확인해야함
			// 왜냐하면 2등은 숫자 5개와 뽀나쓰 넘버가 맞을때 이기 때문
			JLabel resultLbl = null;
			if (count == 5 && bonusChk) {// 이건 2등
				URL score2URL = outcome.class.getClassLoader().getResource("2nd.gif");
				ImageIcon score2 = new ImageIcon(score2URL);
				resultLbl = new JLabel(score2);
				
				eachNumsPnl.add(resultLbl);
				totalPnl.add(eachNumsPnl);
				Main.scoreList[1]++;
				break; // 2등이면 밑에는 안봐도 되니까 브레이크로 끝냄
			}
			
			switch (count) {
			case 6:
				URL score1URL = outcome.class.getClassLoader().getResource("1st.gif");
				ImageIcon score1 = new ImageIcon(score1URL);
				resultLbl = new JLabel(score1);
				Main.scoreList[0]++;
				break;
			case 5:
				URL score3URL = outcome.class.getClassLoader().getResource("3rd.gif");
				ImageIcon score3 = new ImageIcon(score3URL);
				resultLbl = new JLabel(score3);
				Main.scoreList[2]++;
				break;
			case 4:
				URL score4URL = outcome.class.getClassLoader().getResource("4th.gif");
				ImageIcon score4 = new ImageIcon(score4URL);
				resultLbl = new JLabel(score4);
				Main.scoreList[3]++;
				break;
			case 3:
				URL score5URL = outcome.class.getClassLoader().getResource("5th.gif");
				ImageIcon score5 = new ImageIcon(score5URL);
				resultLbl = new JLabel(score5);
				Main.scoreList[4]++;
				break;
			default:
				URL scoreOtherURL = outcome.class.getClassLoader().getResource("unlucky.gif");
				ImageIcon scoreOther = new ImageIcon(scoreOtherURL);
				resultLbl = new JLabel(scoreOther);
				Main.scoreList[5]++;
				break;
			}
			eachNumsPnl.add(resultLbl);
			totalPnl.add(eachNumsPnl);
		}
		
		// 다시 구매하기 or 끝내기 버튼 + 통계버튼
		
		JPanel btnPNl = new JPanel();
		btnPNl.setOpaque(true);
		btnPNl.setBackground(Color.white);
		
		JButton statisticsBtn = new JButton("통계");
		statisticsBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Main.statisDlg == null)
					Main.statisDlg = new StatisticsDialog(outcomeNum, bonusNum);
				else
					Main.statisDlg.ShowStatistics();
			}
		});
		
		JButton purchaseBtn = new JButton("재구매");
		purchaseBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.newMain.show();
				outcome.this.dispose();
			}
		});
		
		JButton exitBtn = new JButton("끝내기");
		exitBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		btnPNl.add(statisticsBtn);
		btnPNl.add(purchaseBtn);
		btnPNl.add(exitBtn);
		
		totalPnl.add(btnPNl);

		add(totalPnl);

		showGUI();
	}

	private void showGUI() {
//		setSize(500, 500);
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
}
