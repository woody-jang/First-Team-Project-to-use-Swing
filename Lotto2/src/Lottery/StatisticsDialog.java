package Lottery;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class StatisticsDialog extends JDialog {
	List<JLabel> scoreLblList;
	JLabel totalGame;

	public StatisticsDialog() {
		this.setLocation(700, 300);
		setModal(true);

		JPanel totalPnl = new JPanel();
		totalPnl.setLayout(new BoxLayout(totalPnl, BoxLayout.Y_AXIS));
		totalPnl.setOpaque(true);
		totalPnl.setBackground(Color.white);

		JPanel titlePnl = new JPanel();
		titlePnl.setOpaque(true);
		titlePnl.setBackground(Color.white);
		JLabel titleLbl = new JLabel("등수");
		JLabel blankLbl = new JLabel("        ");
		JLabel titleCntLbl = new JLabel("횟수");
		titleLbl.setFont(new Font(titleLbl.getFont().getName(), Font.PLAIN, 15));
		titleCntLbl.setFont(new Font(titleCntLbl.getFont().getName(), Font.PLAIN, 15));

		titlePnl.add(titleLbl);
		titlePnl.add(blankLbl);
		titlePnl.add(titleCntLbl);

		totalPnl.add(titlePnl);

		scoreLblList = new ArrayList<>();
		int count = 0;
		for (int i = 0; i < 6; i++) {
			JPanel scorePnl = new JPanel();
			scorePnl.setOpaque(true);
			scorePnl.setBackground(Color.white);
			JLabel scoreLbl = null;
			JLabel blankLbl1 = null;
			if (i != 5) {
				scoreLbl = new JLabel(i + 1 + "등 ");
				blankLbl1 = new JLabel("        ");
			} else {
				scoreLbl = new JLabel("미당첨");
				blankLbl1 = new JLabel("  ");
			}
			JLabel scoreCntLbl = new JLabel(" " + Main.scoreList[i] + "회 ");
			scoreLbl.setFont(new Font(scoreLbl.getFont().getName(), Font.PLAIN, 15));
			scoreCntLbl.setFont(new Font(scoreCntLbl.getFont().getName(), Font.PLAIN, 15));
			scorePnl.add(scoreLbl);
			scorePnl.add(blankLbl1);
			scorePnl.add(scoreCntLbl);
			totalPnl.add(scorePnl);

			scoreLblList.add(scoreCntLbl);

			count += Main.scoreList[i];
		}

		JPanel totalCntGame = new JPanel();
		totalCntGame.setOpaque(true);
		totalCntGame.setBackground(Color.white);

		totalGame = new JLabel("총 게임 횟수 : " + (long) count + "회(" + ((long) count * 1000l) + "원)");
		totalGame.setFont(new Font(totalGame.getFont().getName(), Font.PLAIN, 20));
		totalCntGame.add(totalGame);

		totalPnl.add(totalCntGame);

		JPanel historyNumPnl = new JPanel();
		historyNumPnl.setOpaque(true);
		historyNumPnl.setBackground(Color.white);

		JButton goToHistoryBtn = new JButton("숫자별 1등 출현 빈도");
		goToHistoryBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new LottoHistoryDialog();
			}
		});
		historyNumPnl.add(goToHistoryBtn);

		totalPnl.add(historyNumPnl);

		add(totalPnl);

		pack();
		setVisible(true);
	}

	public void ShowStatistics() {
		SetScoreLbl();
		setVisible(true);
	}

	public void SetScoreLbl() {
		int count = 0;
		for (int i = 0; i < 6; i++) {
			scoreLblList.get(i).setText(" " + Main.scoreList[i] + "회 ");
			count += Main.scoreList[i];
		}
		totalGame.setText("총 게임 횟수 : " + (long) count + "회(" + ((long) count * 1000l) + "원)");
	}
}
