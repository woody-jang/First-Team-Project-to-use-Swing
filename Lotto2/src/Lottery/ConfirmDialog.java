package Lottery;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.*;

public class ConfirmDialog extends JDialog {
	int returnVal; // 호출한 프레임에 넘겨줄 버튼 선택값
	
	public ConfirmDialog(int select) {
		this.setLocation(700, 300);
		setModal(true);
//		JDialog cfmDialog = new JDialog();

		// 메인 패널
		JPanel mainPnl = new JPanel();
		mainPnl.setLayout(new BoxLayout(mainPnl, BoxLayout.Y_AXIS));
		mainPnl.setOpaque(true);
		mainPnl.setBackground(Color.white);

		// 금액 표시용 패널
		JPanel priceLblPnl = new JPanel();
		priceLblPnl.setLayout(new FlowLayout());
		priceLblPnl.setOpaque(true);
		priceLblPnl.setBackground(Color.white);

		URL priceTitleLblIconURL = ConfirmDialog.class.getClassLoader().getResource("PRICE.gif");
		ImageIcon priceTitleLblIcon = new ImageIcon(priceTitleLblIconURL);
		JLabel priceTitleLbl = new JLabel(priceTitleLblIcon);

		URL priceLblIconURL = ConfirmDialog.class.getClassLoader().getResource(select + "000.gif");
		ImageIcon priceLblIcon = new ImageIcon(priceLblIconURL);
		JLabel priceLbl = new JLabel(priceLblIcon);

		priceLblPnl.add(priceTitleLbl);
		priceLblPnl.add(priceLbl);

		// 버튼용 패널
		JPanel btnPnl = new JPanel();
		btnPnl.setLayout(new FlowLayout());
		btnPnl.setOpaque(true);
		btnPnl.setBackground(Color.white);

		JButton purchaseBtn = new JButton("구매");
		purchaseBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				returnVal = 1;
				ConfirmDialog.this.dispose();
			}
		});

		JButton cancelBtn = new JButton("취소");
		cancelBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				returnVal = 0;
				ConfirmDialog.this.dispose();
			}
		});

		btnPnl.add(purchaseBtn);
		btnPnl.add(cancelBtn);

		mainPnl.add(priceLblPnl);
		mainPnl.add(btnPnl);

		add(mainPnl);
		
		pack();
		setVisible(true);
	}
	
	public int getReturnVal() {
		return this.returnVal;
	}
}
