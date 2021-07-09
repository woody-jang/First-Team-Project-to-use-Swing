package Lottery;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.*;
   
/*	1) 로또 개수, 확인 버튼 -> 원하는 개수 입력 받기 
 * 	2) 수동, 반자동, 자동 버튼 Label / 수정, 삭제 버튼 
 * 	3) 번호 고르기 > 자동(반자동), 초기화, 확인 -> 총 금액, 구매하기 버튼 
 * 	4) 당첨번호, 총 구매 수, 재도전, 종료 버튼 
 *    디자인은 전적으로 그림을 그려서 수작업할 예정 + 귀여운 디자인 훔쳐오기 (내 것처럼)
 *    숫자 90개(1~45) : 당첨됐을 때 숫자 색 / 당첨 안됐을 때 숫자 색 
 *    당첨, 낙첨, 1등, 2등, 3등, 4등, 5등 글씨 
 *    로고
 *    모든 버튼의 글씨는 다 디자인으로 감
 */
public class Main extends JFrame {
//	1) 로또 개수, 확인 버튼 -> 원하는 개수 입력 받기 (최대 5개까지 가능)
	Integer[] A = { 1, 2, 3, 4, 5 };
	JComboBox<Integer> numOfBet = new JComboBox<>(A);
	int select = 1;
	static SelectingNumber selNum;
	static StatisticsDialog statisDlg;
	static Main newMain;
	static int[] scoreList = new int[6];
	
	public Main() {
		this.setLocation(700, 250);
		setTitle("로또 판매프로그램");
		JPanel logoPnl = new JPanel();
		logoPnl.setOpaque(true);
		logoPnl.setBackground(Color.white);
		
		URL img1URL = Main.class.getClassLoader().getResource("Logo1.png");
		ImageIcon img1 = new ImageIcon(img1URL);
		JLabel logoLbl = new JLabel(img1);
		logoPnl.add(logoLbl);
		
		JPanel copyPnl = new JPanel();
		URL msgLblIconURL = Main.class.getClassLoader().getResource("MainSentence.gif");
		ImageIcon msgLblIcon = new ImageIcon(msgLblIconURL);
		JLabel msgLbl = new JLabel(msgLblIcon);
		copyPnl.setOpaque(true);
		copyPnl.setBackground(Color.white);
		copyPnl.add(msgLbl);
		
		JPanel btnPnl = new JPanel();
		btnPnl.setOpaque(true);
		btnPnl.setBackground(Color.white);
		
		URL nextLblIconURL = Main.class.getClassLoader().getResource("MainNext.gif");
		ImageIcon nextLblIcon = new ImageIcon(nextLblIconURL);
		JLabel nextLbl = new JLabel(nextLblIcon);

		numOfBet.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox<Integer> tmp = (JComboBox<Integer>) e.getSource();
				select = tmp.getSelectedIndex() + 1;
			}
		});
		
		nextLbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selNum = new SelectingNumber(select);
				Main.this.dispose();
			}
		});
		
		
		btnPnl.add(numOfBet);
		btnPnl.add(nextLbl);

		add(logoPnl, "North");
		add(copyPnl);
		add(btnPnl, "South");

		showGUI();
	}

	private void showGUI() {
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
//		String input = JOptionPane.showInputDialog(null, 
//				"Select the numbers of you're betting for\n(The maximun is up to five)");
//		JButton btn = new JButton(input);
		newMain = new Main();
	}

}