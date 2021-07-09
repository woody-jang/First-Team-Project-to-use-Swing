package Lottery;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

class AutoLblMouseAdapter extends MouseAdapter {
	@Override
	public void mouseClicked(MouseEvent e) {
		// 현재 이벤트를 발생시킨 라벨 객체 저장
		JLabel tempClick = (JLabel) e.getSource();

		// idx를 계속 사용하기위해 for문 밖에서 선언
		int idx = 0;
		for (; idx < Main.selNum.editOfLottery.size(); idx++) {
			// 만약 이벤트를 발생시킨 애가 있다면 j가 6이 되기전에 멈추기때문에
			// 탈출 조건을 써야되서 for문 밖에 j 선언
			// 만약 이벤트를 발생시킨 애와 j번째 객체가 같다면 브레이크
			if (Main.selNum.editOfLottery.get(idx).contains(tempClick))
				break;
		}
		if (idx == Main.selNum.editOfLottery.size()) {
			for (int i = 0; i < Main.selNum.editOfLottery.size(); i++) {
				Main.selNum.selectedNum[i] = MakeNumber.randomNumArray();
				Main.selNum.setNumLabel();

				Main.selNum.choiceOfwayList[i] = new ChoiceOfway(Main.selNum.numOfLottery[i],
						Main.selNum.selectedNum[i], i);
			}
		} else {
			Main.selNum.selectedNum[idx] = MakeNumber.randomNumArray();
			Main.selNum.setNumLabel();

			Main.selNum.choiceOfwayList[idx] = new ChoiceOfway(Main.selNum.numOfLottery[idx],
					Main.selNum.selectedNum[idx], idx);
		}
	}
}

class DelLblMouseAdapter extends MouseAdapter {
	@Override
	public void mouseClicked(MouseEvent e) {
		// 현재 이벤트를 발생시킨 라벨 객체 저장
		JLabel tempClick = (JLabel) e.getSource();

		// idx를 계속 사용하기위해 for문 밖에서 선언
		int idx = 0;
		for (; idx < Main.selNum.editOfLottery.size(); idx++) {
			// 만약 이벤트를 발생시킨 애가 있다면 j가 6이 되기전에 멈추기때문에
			// 탈출 조건을 써야되서 for문 밖에 j 선언
			// 만약 이벤트를 발생시킨 애와 j번째 객체가 같다면 브레이크
			if (Main.selNum.editOfLottery.get(idx).contains(tempClick))
				break;
		}

		if (Main.selNum.choiceOfwayList[idx] == null)
			JOptionPane.showMessageDialog(null, "선택하지 않아 삭제가 불가능합니다", "삭제 에러", JOptionPane.ERROR_MESSAGE);
		else {
			if (idx != Main.selNum.choiceOfwayList.length - 1) {
				if (Main.selNum.choiceOfwayList[idx + 1] != null) {
					for (int i = idx; i < Main.selNum.choiceOfwayList.length; i++) {
						if (i == Main.selNum.choiceOfwayList.length - 1) {
							Main.selNum.choiceOfwayList[i] = null;
							Main.selNum.selectedNum[i] = new int[6];
						} else {
							Main.selNum.choiceOfwayList[i] = Main.selNum.choiceOfwayList[i + 1];
							Main.selNum.selectedNum[i] = Main.selNum.selectedNum[i + 1];
						}
					}
				} else {
					Main.selNum.choiceOfwayList[idx] = null;
					Main.selNum.selectedNum[idx] = new int[6];
				}
			} else {
				Main.selNum.choiceOfwayList[idx] = null;
				Main.selNum.selectedNum[idx] = new int[6];
			}
			Main.selNum.setNumLabel();
		}
	}
}

// XX 라벨용 마우스 아답타
class NumLblMouseAdapter extends MouseAdapter {
	@Override
	public void mouseClicked(MouseEvent e) {
		// 현재 이벤트를 발생시킨 라벨 객체 저장
		JLabel tempClick = (JLabel) e.getSource();

		// idx를 계속 사용하기위해 for문 밖에서 선언
		int idx = 0;

		for (; idx < Main.selNum.numOfLottery.length; idx++) {
			// 만약 이벤트를 발생시킨 애가 있다면 j가 6이 되기전에 멈추기때문에
			// 탈출 조건을 써야되서 for문 밖에 j 선언
			int j = 0;
			for (; j < 6; j++) {
				// 만약 이벤트를 발생시킨 애와 j번째 객체가 같다면 브레이크
				if (tempClick == Main.selNum.numOfLottery[idx][j])
					break;
				// 중간에 브레이크 됐다면 j는 6보다 작기때문에 밖의 for문을
				// 탈출하기위해 브레이크 한번더
			}
			if (j != 6)
				break;
		}
		boolean editchk = false;
		if (idx == Main.selNum.numOfLottery.length) {
			// idx를 계속 사용하기위해 for문 밖에서 선언
			idx = 0;

			for (; idx < Main.selNum.editOfLottery.size(); idx++) {
				// 만약 이벤트를 발생시킨 애가 있다면 j가 6이 되기전에 멈추기때문에
				// 탈출 조건을 써야되서 for문 밖에 j 선언
				if (Main.selNum.editOfLottery.get(idx).contains(tempClick)) {
					break;
				}
			}
			editchk = true;
		}

		// 여기까지 나온 idx는 각 열의 1~6번까지 어느 버튼을 선택하든
		// A열의 1~6번을 선택하면 idx는 0
		// B열이라면 idx는 1이 나오도록 해놓음 - for문에서 브레이크를 두번 쓰고 idx를 밖으로 뺀 이유

		// 순서대로 입력을 안할경우를 대비한 boolean값 선언 및
		// 탐색작업
		boolean checkOrder = true;

		// 위에서 알아낸 호출한 객체의 순서(A, B, C, D, E중 한명의 인덱스)의 직전까지
		// 탐색해서 만약 선택한놈보다 전의 객체도 비어있다면 순서대로 누른게 아니므로
		// boolean 값을 false로 바꾸고 에러창 출력
		for (int i = 0; i < idx; i++) {
			if (Main.selNum.choiceOfwayList[i] == null) {
				JOptionPane.showMessageDialog(null, "순서대로 입력하세요", "순서 에러", JOptionPane.ERROR_MESSAGE);
				checkOrder = false;
				// null인 객체가 하나라도 있다면 순서가 안맞으므로
				// 다음은 볼 필요도 없이 바로 브레이크
				break;
			}
		}
		// 위에서 비어있는 객체가 없어서 false로 바뀌지 않았다면
		// 순서가 맞으므로 if문 안의 다음 창 띄우기 실행
		if (checkOrder) {
			// 만약 이번에 선택할 숫자들의 기존 입력값이 없다면
			// 새로운 객체를 생성해서 실행후 리스트에 추가
			if (Main.selNum.choiceOfwayList[idx] == null)
				if (editchk) {
					JOptionPane.showMessageDialog(null, "X를 눌려서 번호를 선택해주세요.", "선택 에러", JOptionPane.ERROR_MESSAGE);
				} else {
					// 첫번째 전달인자로 라벨의 숫자를 바꾸기 위해 라벨 배열을 넘겨주고
					// 두번째 전달인자로 선택한 숫자를 저장하기 위해 선택한 숫자 배열을 넘겨줌
					Main.selNum.choiceOfwayList[idx] = new ChoiceOfway(Main.selNum.numOfLottery[idx],
							Main.selNum.selectedNum[idx]);
					// 기존에 입력했던 값을 수정하는거라면
					// 기존 객체 불러오기
				}
			else {
				Main.selNum.choiceOfwayList[idx].callSelectionPop();
			}
		}
	}
}

class SelectingNumber extends JFrame {
	/*
	 * 2) 수동, 반자동, 자동 버튼 Label / 수정, 삭제 버튼 3) 번호 고르기 > 자동(반자동), 초기화, 확인 -> 총 금액,
	 * 구매하기 버튼 A XXXXXXX 수정 삭제 장당 1,000 B XXXXXXX 수정 삭제 >> 번 호 >> 총 금액 : C XXXXXXX
	 * 수정 삭제 D XXXXXXX 수정 삭제 반자동 초기화 확인 구매하기
	 */

	// 배열로 선언한 이유는 우리가 원하는대로 인덱스에 접근할 수 있고
	// 리스트에서 add를 했을때 조건을 제대로 안잡는다면
	// 자칫 로또 번호가 담긴 라벨의 인덱스와 숫자 선택창의 인덱스, 선택한 숫자들의 인덱스가
	// 다를 수 있으므로 배열로 수정함

	List<JLabel> nameOfLottery = new ArrayList<>(); // 선택한 개수만큼 담을 이름 그릇
	JLabel[][] numOfLottery; // 선택한 번호나 xxx를 표시할 라벨의 배열
	List<List<JLabel>> editOfLottery = new ArrayList<>(); // 수정 삭제 버튼 담을 그릇
	ChoiceOfway[] choiceOfwayList; // 숫자 선택창을 저장할 배열
	int[][] selectedNum; // 선택한 숫자들이 담길 배열
	JPanel main;
	JPanel warningPnl; // 제일 위에 설명할 라벨을 위한 패널
	JLabel warningMsg;

//	Main에서 next버튼 눌렸을 때, ActionListener로 인해 호출 
//	ActionListener cast this constructor to choose the numbers of lottery

	public SelectingNumber(int select) { // Main 콤보박스에서 선택한 숫자값 들고오기
		this.setLocation(550, 250);
		setTitle("게임 선택");
		// 3개를 생성자에서 초기화 한 이유는
		// 콤보박스에서 선택한 횟수를 알아야지
		// 정확하게 초기화가 가능하기 때문임
		numOfLottery = new JLabel[select][6]; // 선택한 숫자나 xxx가 담길 라벨 배열 초기화
		choiceOfwayList = new ChoiceOfway[select]; // 숫자 선택창들이 담길 배열 초기화
		selectedNum = new int[select][6]; // 선택한 숫자들이 담길 배열 초기화

		main = new JPanel(); // 전체를 관리할 패널
		main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS)); // 세로정렬

		warningPnl = new JPanel(); // 설명용 패널
		URL warningIconURL = SelectingNumber.class.getClassLoader().getResource("sentence.gif");
		ImageIcon warningIcon = new ImageIcon(warningIconURL);
		warningMsg = new JLabel(warningIcon);
		warningPnl.setOpaque(true);
		warningPnl.setBackground(Color.white);
		warningPnl.add(warningMsg);
		main.add(warningPnl);

		// The names
		// 1) integer A 배열을 그대로 가지고 와서 1,2,3,4,5를 A,B,C,D,E로 만들어서 뽑기
		for (int i = 0; i < select; i++) {
			// A와 XXXXX 및 버튼이 담길 패널 생성
			JPanel choice = new JPanel();
			choice.setOpaque(true);
			choice.setBackground(Color.white);
			choice.setLayout(new FlowLayout()); // FlowLayout이 기본이라 생략가능

			// A ~ E까지 선택한 횟수만큼 라벨을 만들어서 패널에 추가
			// A는 65이기 때문에 i가 0일때 65를 더해 A라는 문자를 추출
			URL abcIconURL = outcome.class.getClassLoader().getResource((char) (i + 65) + ".gif");
			ImageIcon abcIcon = new ImageIcon(abcIconURL);
			JLabel numOfName = new JLabel(abcIcon);
			nameOfLottery.add(numOfName);
			choice.add(numOfName);

			// 2) 아무것도 선택하지 않은 상태에서 보이는 레이블
			JLabel[] tmpNumList = new JLabel[6]; // 2차원 배열이므로 하나하나 만들때마다 초기화
			for (int j = 0; j < 6; j++) { // x로 바뀐다면 j는 0부터 시작가능
				URL tmpNumIconURL = SelectingNumber.class.getClassLoader().getResource("x.gif");
				ImageIcon tmpNumIcon = new ImageIcon(tmpNumIconURL);
				JLabel num1 = new JLabel(tmpNumIcon);
				// 나중에 사용하기위해 임시로 만든 배열에 저장
				tmpNumList[j] = num1;
				choice.add(num1);
			}
			// 임시로 만든 배열을 실제로 쓸 2차원 배열에 저장
			numOfLottery[i] = tmpNumList;

			// Reset, Confirm buttons
			List<JLabel> lblList = new ArrayList<JLabel>(); // 버튼들을 담을 임시 그릇
			for (int j = 0; j < 3; j++) { // 버튼이 2개 이므로 for 조건도 2보다 작을때까지
				JLabel tmpLbl;
				if (j == 0) {
					URL tmpIconURL = SelectingNumber.class.getClassLoader().getResource("AUTO.gif");
					ImageIcon tmpIcon = new ImageIcon(tmpIconURL);
					tmpLbl = new JLabel(tmpIcon); // 담은 버튼을 담을 임시 저장소
					tmpLbl.addMouseListener(new AutoLblMouseAdapter());
				} else if (j == 1) {
					URL tmpIconURL = SelectingNumber.class.getClassLoader().getResource("EDIT.gif");
					ImageIcon tmpIcon = new ImageIcon(tmpIconURL);
					tmpLbl = new JLabel(tmpIcon); // 담은 버튼을 담을 임시 저장소
					tmpLbl.addMouseListener(new NumLblMouseAdapter());
				} else {
					URL tmpIconURL = SelectingNumber.class.getClassLoader().getResource("DELETE.gif");
					ImageIcon tmpIcon = new ImageIcon(tmpIconURL);
					tmpLbl = new JLabel(tmpIcon); // 담은 버튼을 담을 임시 저장소
					tmpLbl.addMouseListener(new DelLblMouseAdapter());
				}
				lblList.add(tmpLbl); // 버튼에 임시 저장소에 있는 원소 넣기
				choice.add(tmpLbl); // 임시 버튼을 choice판에 붙이기
			}
			editOfLottery.add(lblList);
			main.add(choice);
		}

		for (int i = 0; i < select; i++) {
			// 숫자 라벨이 총 6개 이므로 6개중 어떤걸 선택해도
			// 해당 라인이 눌려진걸로 인식되도록 for문을 한번 더 씀
			// 해당 라인이 눌려진걸로 인식하는건 LblMouseAdapter 안에 구현해 놓음
			for (int j = 0; j < 6; j++) {
				numOfLottery[i][j].addMouseListener(new NumLblMouseAdapter());
			}
		}

		JPanel nextPnl = new JPanel();
		nextPnl.setOpaque(true);
		nextPnl.setBackground(Color.white);

		URL autoLblIconURL = SelectingNumber.class.getClassLoader().getResource("AUTO.gif");
		ImageIcon autoLblIcon = new ImageIcon(autoLblIconURL);
		JLabel autoLbl = new JLabel(autoLblIcon); // 담은 버튼을 담을 임시 저장소
		autoLbl.addMouseListener(new AutoLblMouseAdapter());
		nextPnl.add(autoLbl);

		URL nextLblIconURL = SelectingNumber.class.getClassLoader().getResource("NEXT.gif");
		ImageIcon nextLblIcon = new ImageIcon(nextLblIconURL);
		JLabel nextLbl = new JLabel(nextLblIcon);

		nextLbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (choiceOfwayList[select - 1] == null)
					JOptionPane.showMessageDialog(null, "모든 게임을 선택해주세요", "미선택 에러", JOptionPane.ERROR_MESSAGE);
				else {
					ConfirmDialog cfmDialog = new ConfirmDialog(select);
					if (cfmDialog.returnVal == 1) {
						new outcome(selectedNum);
						SelectingNumber.this.dispose();
					}
				}
			}
		});

		nextPnl.add(nextLbl);
		
		URL cancleLblIconURL = SelectingNumber.class.getClassLoader().getResource("NEXT.gif");
		ImageIcon cancleLblIcon = new ImageIcon(cancleLblIconURL);
		JLabel cancleLbl = new JLabel("취소");
		
		cancleLbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Main.newMain.show();
				dispose();
			}
		});
		
		nextPnl.add(cancleLbl);

		main.add(nextPnl);

		add(main);

		showGUI();
	}

	private void showGUI() {
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	public void setNumLabel() {
		for (int i = 0; i < selectedNum.length; i++) {
			int[] tempInt = selectedNum[i];
			JLabel[] tempLbl = numOfLottery[i];
			for (int j = 0; j < 6; j++) {
				if (tempInt[j] == 0) {
					// tempLbl[j].setIcon(new ImageIcon("number/x.gif"));
					URL tempLblIconURL = SelectingNumber.class.getClassLoader().getResource("x.gif");
					tempLbl[j].setIcon(new ImageIcon(tempLblIconURL));
				} else {
					URL tempLblIconURL = SelectingNumber.class.getClassLoader().getResource(tempInt[j] + ".gif");
					tempLbl[j].setIcon(new ImageIcon(tempLblIconURL));
				}
			}
		}
	}
}
