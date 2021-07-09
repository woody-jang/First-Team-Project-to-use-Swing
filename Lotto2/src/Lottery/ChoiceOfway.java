package Lottery;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.*;

// 숫자 레이블을 눌려도 라디오버튼을 누르것 처럼 하기
class NumLabelActionListener extends MouseAdapter {
	List<List<JRadioButton>> check;
	List<Integer> selectedNumber;
	List<List<JLabel>> checkLbl;
	JLabel tmpLbl;

	public NumLabelActionListener(List<List<JRadioButton>> check, List<Integer> selectedNumber, JLabel tmpLbl,
			List<List<JLabel>> checkLbl) {
		this.check = check;
		this.selectedNumber = selectedNumber;
		this.tmpLbl = tmpLbl;
		this.checkLbl = checkLbl;
	}

//	선택창 띄워서 숫자 선택하는 행동 
	@Override
	public void mouseClicked(MouseEvent e) {
		int tmp2 = 0;
		for (int i = 0; i < this.checkLbl.size(); i++) {
			List<JLabel> tmp = this.checkLbl.get(i);
			for (int j = 0; j < tmp.size(); j++) {
				tmp2 = (i * 7) + j + 1;
				if (tmp.get(j) == tmpLbl) {
					if (check.get(i).get(j).isSelected()) {
						check.get(i).get(j).setSelected(false);
						this.selectedNumber.remove(this.selectedNumber.indexOf(tmp2));
					} else {
						check.get(i).get(j).setSelected(true);
						if (!this.selectedNumber.contains(tmp2))
							this.selectedNumber.add(tmp2);
						if (this.selectedNumber.size() > 6) {
							check.get(i).get(j).setSelected(false);
							JOptionPane.showMessageDialog(null, "6개 이하만 선택해주세요", "선택 에러", JOptionPane.ERROR_MESSAGE);
						}
					}
					if (this.selectedNumber.contains(tmp2) && !check.get(i).get(j).isSelected())
						this.selectedNumber.remove(this.selectedNumber.indexOf(tmp2));
				}
			}
		}
	}
}

class RadioActionListener implements ActionListener {
	List<List<JRadioButton>> check;
	List<Integer> selectedNumber;

	public RadioActionListener(List<List<JRadioButton>> check, List<Integer> selectedNumber) {
		this.check = check;
		this.selectedNumber = selectedNumber;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int tmp2 = 0;
		for (int i = 0; i < this.check.size(); i++) {
			List<JRadioButton> tmp = this.check.get(i);
			for (int j = 0; j < tmp.size(); j++) {
				tmp2 = (i * 7) + j + 1;
				if (tmp.get(j).isSelected()) {
					if (!this.selectedNumber.contains(tmp2))
						this.selectedNumber.add(tmp2);
				}
				if (this.selectedNumber.size() > 6) {
					tmp.get(j).setSelected(false);
					JOptionPane.showMessageDialog(null, "6개 이하만 선택해주세요", "선택 에러", JOptionPane.ERROR_MESSAGE);
				}
				if (this.selectedNumber.contains(tmp2) && !tmp.get(j).isSelected())
					this.selectedNumber.remove(this.selectedNumber.indexOf(tmp2));
			}
		}
	}
}

class ChoiceOfway extends JFrame {
	/*
	 * 3) 번호 고르기 > 자동(반자동), 초기화, 확인 -> 총 금액, 구매하기 버튼 장당 1,000 총 금액 : 번 호 >> 반자동, 초기화
	 * 확인, 구매하기
	 */
	List<List<JRadioButton>> check = new ArrayList<>(); // 1 ~ 45 라디오 버튼 저장
	List<List<JLabel>> checkLbl = new ArrayList<>(); // 1 ~ 45 숫자 레이블 저장

	List<Integer> selectedNumberList; // 선택한 숫자 저장
	JPanel selectionPop;
	JPanel lottoPnl;
	JLabel price;
	JPanel btnPnl;
	JLabel auto;
	JLabel reset;
	JLabel confirm;

	public ChoiceOfway(JLabel[] numOfLottery, int[] selectedNumber, int index) {
		this.setLocation(550, 250);
		// SelectingNumber class의 선택한 숫자 list를 가져와서 여기에서 숫자 저장
		selectedNumberList = new ArrayList<>();

		// 전체 로또 판때기
		selectionPop = new JPanel();
		selectionPop.setLayout(new BoxLayout(selectionPop, BoxLayout.Y_AXIS));

		// 로또 금액, 숫자
		lottoPnl = new JPanel();
		lottoPnl.setLayout(new BoxLayout(lottoPnl, BoxLayout.Y_AXIS));
		lottoPnl.setOpaque(true);
		lottoPnl.setBackground(Color.white);

//		JPanel priceLblPnl = new JPanel();
//		priceLblPnl.setLayout(new FlowLayout());
//		priceLblPnl.setOpaque(true);
//		priceLblPnl.setBackground(Color.white);
//		
//		price = new JLabel("금액 : 1,000원(장당)");
//		priceLblPnl.add(price);
//		lottoPnl.add(priceLblPnl);

		// 1 ~ 45 라디오 버튼 생성
		addLottoRadio(lottoPnl);
		setLottoRadio(selectedNumber);

		// 확인 및 수정, 자동 버튼
		btnPnl = new JPanel();
		btnPnl.setLayout(new FlowLayout());
		btnPnl.setOpaque(true);
		btnPnl.setBackground(Color.white);

		// 자동 버튼
		URL autoIconURL = ChoiceOfway.class.getClassLoader().getResource("AUTO.gif");
		ImageIcon autoIcon = new ImageIcon(autoIconURL);
		auto = new JLabel(autoIcon);
		auto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MakeNumber.randomNum(selectedNumberList);
				for (int i = 0; i < check.size(); i++) {
					List<JRadioButton> tmp = check.get(i);
					for (int j = 0; j < tmp.size(); j++) {
						int tmp2 = (i * 7) + j + 1;
						if (selectedNumberList.contains(tmp2))
							tmp.get(j).setSelected(true);
						else
							tmp.get(j).setSelected(false);
					}
				}
			}
		});

		// 초기화 버튼
		URL resetIconURL = ChoiceOfway.class.getClassLoader().getResource("INIT.gif");
		ImageIcon resetIcon = new ImageIcon(resetIconURL);
		reset = new JLabel(resetIcon);
		reset.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				for (int i = 0; i < check.size(); i++) {
					List<JRadioButton> tmp = check.get(i);
					for (int j = 0; j < tmp.size(); j++) {
						tmp.get(j).setSelected(false);
					}
				}
				selectedNumberList.clear();
			}
		});

		// 확인 버튼
		URL confirmIconURL = ChoiceOfway.class.getClassLoader().getResource("CHECK.gif");
		ImageIcon confirmIcon = new ImageIcon(confirmIconURL);
		confirm = new JLabel(confirmIcon);
		confirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (selectedNumberList.size() != 6)
					JOptionPane.showMessageDialog(null, "6개를 선택하세요", "선택 에러", JOptionPane.ERROR_MESSAGE);
				else {
					Collections.sort(selectedNumberList);
					for (int i = 0; i < 6; i++) {
						int tmp = selectedNumberList.get(i);
						selectedNumber[i] = tmp;
						URL tmpIconURL = ChoiceOfway.class.getClassLoader().getResource(tmp + ".gif");
						ImageIcon tmpIcon = new ImageIcon(tmpIconURL);
						numOfLottery[i].setIcon(tmpIcon);
					}
					dispose();
				}
			}
		});

		btnPnl.add(auto);
		btnPnl.add(reset);
		btnPnl.add(confirm);

		// 전체 창에 로또 금액 숫자, 확인 및 수정 버튼 추가
		selectionPop.add(lottoPnl);
		selectionPop.add(btnPnl);

		// 전체 로또 판때기에 부착
		add(selectionPop);
	}

	public ChoiceOfway(JLabel[] numOfLottery, int[] selectedNumber) {
		this.setLocation(550, 250);
		// SelectingNumber class의 선택한 숫자 list를 가져와서 여기에서 숫자 저장
		selectedNumberList = new ArrayList<>();

		// 전체 로또 판때기
		selectionPop = new JPanel();
		selectionPop.setLayout(new BoxLayout(selectionPop, BoxLayout.Y_AXIS));

		// 로또 금액, 숫자
		lottoPnl = new JPanel();
		lottoPnl.setLayout(new BoxLayout(lottoPnl, BoxLayout.Y_AXIS));
		lottoPnl.setOpaque(true);
		lottoPnl.setBackground(Color.white);

//		JPanel priceLblPnl = new JPanel();
//		priceLblPnl.setLayout(new FlowLayout());
//		priceLblPnl.setOpaque(true);
//		priceLblPnl.setBackground(Color.white);
//		
//		price = new JLabel("금액 : 1,000원(장당)");
//		priceLblPnl.add(price);
//		lottoPnl.add(priceLblPnl);

		// 1 ~ 45 라디오 버튼 생성
		addLottoRadio(lottoPnl);

		// 확인 및 수정, 자동 버튼
		btnPnl = new JPanel();
		btnPnl.setLayout(new FlowLayout());
		btnPnl.setOpaque(true);
		btnPnl.setBackground(Color.white);

		// 자동 버튼
		URL autoIconURL = ChoiceOfway.class.getClassLoader().getResource("AUTO.gif");
		ImageIcon autoIcon = new ImageIcon(autoIconURL);
		auto = new JLabel(autoIcon);
		auto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MakeNumber.randomNum(selectedNumberList);
				for (int i = 0; i < check.size(); i++) {
					List<JRadioButton> tmp = check.get(i);
					for (int j = 0; j < tmp.size(); j++) {
						int tmp2 = (i * 7) + j + 1;
						if (selectedNumberList.contains(tmp2))
							tmp.get(j).setSelected(true);
						else
							tmp.get(j).setSelected(false);
					}
				}
			}
		});

		// 초기화 버튼
		URL resetIconURL = ChoiceOfway.class.getClassLoader().getResource("INIT.gif");
		ImageIcon resetIcon = new ImageIcon(resetIconURL);
		reset = new JLabel(resetIcon);
		reset.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				for (int i = 0; i < check.size(); i++) {
					List<JRadioButton> tmp = check.get(i);
					for (int j = 0; j < tmp.size(); j++) {
						tmp.get(j).setSelected(false);
					}
				}
				selectedNumberList.clear();
			}
		});

		// 확인 버튼
		URL confirmIconURL = ChoiceOfway.class.getClassLoader().getResource("CHECK.gif");
		ImageIcon confirmIcon = new ImageIcon(confirmIconURL);
		confirm = new JLabel(confirmIcon);
		confirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (selectedNumberList.size() != 6)
					JOptionPane.showMessageDialog(null, "6개를 선택하세요", "선택 에러", JOptionPane.ERROR_MESSAGE);
				else {
					Collections.sort(selectedNumberList);
					for (int i = 0; i < 6; i++) {
						int tmp = selectedNumberList.get(i);
						selectedNumber[i] = tmp;
						URL tmpIconURL = ChoiceOfway.class.getClassLoader().getResource(tmp + ".gif");
						ImageIcon tmpIcon = new ImageIcon(tmpIconURL);
						numOfLottery[i].setIcon(tmpIcon);
					}
					dispose();
				}
			}
		});

		btnPnl.add(auto);
		btnPnl.add(reset);
		btnPnl.add(confirm);

		// 전체 창에 로또 금액 숫자, 확인 및 수정 버튼 추가
		selectionPop.add(lottoPnl);
		selectionPop.add(btnPnl);

		// 전체 로또 판때기에 부착
		add(selectionPop);
		showGUI();
	}

	public void callSelectionPop() {
		remove(selectionPop);
		add(selectionPop);
		showGUI();
	}

//	private void modLottoRadio(JPanel lottoPnl) {
//		for (int i = 0; i < check.size(); i++) {
//			JPanel lottoTemp = new JPanel();
//			if (i == 6)
//				lottoTemp.setLayout(new FlowLayout(FlowLayout.LEFT));
//			else
//				lottoTemp.setLayout(new FlowLayout());
//			List<JRadioButton> tempRadList = check.get(i);
//			for (int j = 0; j < tempRadList.size(); j++) {
//				int tmpNum = (i * 7) + j + 1;
//				lottoTemp.add(tempRadList.get(j));
//				if (selectedNumberList.contains(tmpNum))
//					tempRadList.get(j).setSelected(true);
//				else
//					tempRadList.get(j).setSelected(false);
//			}
//			lottoPnl.add(lottoTemp);
//			check.add(tempRadList);
//		}
//	}

	private void setLottoRadio(int[] selectedNumber) {
		for (int i : selectedNumber) {
			int j = (i % 7 == 0) ? i / 7 - 1 : i / 7;
			int k = (i % 7 == 0) ? 6 : i % 7 - 1;
			check.get(j).get(k).setSelected(true);;
			selectedNumberList.add(i);
		}
	}
	
	private void addLottoRadio(JPanel lottoPnl) {
		for (int i = 0; i < 7; i++) {
			JPanel lottoTemp = new JPanel();
			lottoTemp.setOpaque(true);
			lottoTemp.setBackground(Color.white);
			if (i == 6)
				lottoTemp.setLayout(new FlowLayout(FlowLayout.LEFT));
			else
				lottoTemp.setLayout(new FlowLayout());
			List<JRadioButton> tempRadList = new ArrayList<>();
			List<JLabel> tempRadLblList = new ArrayList<>();
			for (int j = 0; j < 7; j++) {
				if (i == 6 && j > 2)
					break;
				int tmp = (i * 7) + j + 1;
				URL tmpIconURL = ChoiceOfway.class.getClassLoader().getResource(tmp + ".gif");
				ImageIcon tmpIcon = new ImageIcon(tmpIconURL);
				JRadioButton radioTemp = new JRadioButton();
				radioTemp.setOpaque(true);
				radioTemp.setBackground(Color.white);

				JLabel tmpLbl = new JLabel(tmpIcon);
				radioTemp.addActionListener(new RadioActionListener(check, selectedNumberList));
				lottoTemp.add(radioTemp);
				lottoTemp.add(tmpLbl);

				// 숫자 라벨을 선택했을때도 라디오버튼이 선택되도록 하는 함수
				tmpLbl.addMouseListener(new NumLabelActionListener(check, selectedNumberList, tmpLbl, checkLbl));
				tempRadList.add(radioTemp);
				tempRadLblList.add(tmpLbl);
			}
			lottoPnl.add(lottoTemp);
			check.add(tempRadList);
			checkLbl.add(tempRadLblList);
		}
	}

	private void showGUI() {
//		setSize(500, 500);
		pack();
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

}
