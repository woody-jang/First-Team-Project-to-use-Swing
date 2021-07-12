package Lottery;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LottoHistoryDialog extends JDialog {
	List<List<Integer>> totalLottoNum = Main.newMain.totalLottoNum;;
	List<Integer> bonusNumList = Main.newMain.bonusNumList;
	List<List<JLabel>> historyNumLbl = new ArrayList<>();
	List<List<JLabel>> historyNumCntLbl = new ArrayList<>();
	
	public LottoHistoryDialog() {
		this.setLocation(700, 300);
		
		setModal(true);
		JPanel totalNumber = new JPanel();
		totalNumber.setLayout(new BoxLayout(totalNumber, BoxLayout.Y_AXIS));
		totalNumber.setOpaque(true);
		totalNumber.setBackground(Color.white);
		
		JPanel titlePnl = new JPanel();
		titlePnl.setOpaque(true);
		titlePnl.setBackground(Color.white);
		
		JLabel titleString = new JLabel("숫자별 1등 출현 빈도");
		titleString.setFont(new Font(titleString.getFont().getName(), Font.PLAIN, 16));
		titlePnl.add(titleString);
		totalNumber.add(titlePnl);
		
		for (int i = 0; i < 7; i++) {
			JPanel eachTotalNumber = new JPanel();
			if (i == 6)
				eachTotalNumber.setLayout(new FlowLayout(FlowLayout.LEFT));
			eachTotalNumber.setOpaque(true);
			eachTotalNumber.setBackground(Color.white);
			List<JLabel> tempNumList = new ArrayList<>();
			List<JLabel> tempNumCntList = new ArrayList<>();
			for (int j = 0; j < 7; j++) {
				int tmp = (i * 7) + j + 1;
				if (tmp > 45)
					break;
				JLabel tmpNumLbl = null;
				if (tmp < 10)
					tmpNumLbl = new JLabel("  " + String.valueOf(tmp) + " : ");
				else
					tmpNumLbl = new JLabel(" " + String.valueOf(tmp) + " : ");
				tmpNumLbl.setFont(new Font(tmpNumLbl.getFont().getName(), Font.PLAIN, 15));
				int cntNum = getOutComeNumCnt(tmp);
				JLabel tmpNumCntLbl = null;
				if (j == 6 || tmp == 45)
					tmpNumCntLbl = new JLabel(String.valueOf(cntNum) + "회 ");
				else
					tmpNumCntLbl = new JLabel(String.valueOf(cntNum) + "회, ");
				tmpNumCntLbl.setFont(new Font(tmpNumCntLbl.getFont().getName(), Font.PLAIN, 15));
				if (cntNum != 0) {
					tmpNumLbl.setForeground(Color.red);
					tmpNumCntLbl.setForeground(Color.red);
				}
				tempNumList.add(tmpNumLbl);
				tempNumCntList.add(tmpNumCntLbl);
				eachTotalNumber.add(tmpNumLbl);
				eachTotalNumber.add(tmpNumCntLbl);
			}
			historyNumLbl.add(tempNumList);
			historyNumCntLbl.add(tempNumCntList);
			totalNumber.add(eachTotalNumber);
		}
		add(totalNumber);
		pack();
		setVisible(true);
	}
	
	private int getOutComeNumCnt(int tmp) {
		int count = 0;
		if (totalLottoNum.isEmpty())
			return count;
		else {
			for (int i = 0; i < totalLottoNum.size(); i++) {
				List<Integer> tmpList = totalLottoNum.get(i);
				if (tmpList.contains(tmp))
					count++;
			}
			if (bonusNumList.contains(tmp))
				count++;
			return count;
		}
	}
	
//	public void ShowStatistics() {
//		SetCountLbl();
//		setVisible(true);
//	}
//	
//	public void SetCountLbl() {
//		for (int i = 0; i < historyNumLbl.size(); i++) {
//			for (int j = 0; j < historyNumLbl.get(i).size(); j++) {
//				int tmp = (i * 7) + j + 1;
//				int cntNum = getOutComeNumCnt(tmp);
//				if (j == 6 || tmp == 45)
//					historyNumCntLbl.get(i).get(j).setText(String.valueOf(cntNum) + "회 ");
//				else
//					historyNumCntLbl.get(i).get(j).setText(String.valueOf(cntNum) + "회, ");
//				if (cntNum != 0) {
//					historyNumLbl.get(i).get(j).setForeground(Color.red);
//					historyNumCntLbl.get(i).get(j).setForeground(Color.red);
//				}
//			}
//		}
//	}
}
