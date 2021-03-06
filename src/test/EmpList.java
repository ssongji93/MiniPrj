package test;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class EmpList extends JFrame implements ActionListener, MouseListener{
	JFrame frame;
	JPanel panel, panel_1;
	JScrollPane scrollPane;
	JTable table;
	JButton btnSearch, btnHead, btnWkStart, btnWkEnd, btnRefresh;
	JTextField xSearch;
	JLabel vHead;
	DefaultTableModel model;
	EmpDTO dto;
	EmpList empList;
//	String[] col = {"사번", "이름", "출근", "퇴근", "일당"};
	
	Vector v, cols;
	static int wkStart, wkEnd;
	
	public EmpList() {}
	
	public EmpList(EmpDTO dto) {
		this.dto = dto;
		EmpDAO dao = new EmpDAO();
		v = dao.getEmpList(dto);
		cols = getColumn();

//		String[] headings = new String[] { "사번", "이름", "출근", "퇴근", "일당" };
//		Object[][] data = new Object[][] { { "1", "장원석", "am11", "pm17", "800,000" },
//				{ "2", "장원석", "am11", "pm17", "800,000" }, { "3", "장원석", "am11", "pm17", "800,000" },
//				{ "4", "장원석", "am11", "pm17", "800,000" }, { "5", "장원석", "am11", "pm17", "800,000" },
//				{ "7", "장원석", "am11", "pm17", "800,000" }, { "1", "장원석", "am11", "pm17", "800,000" },
//				{ "1", "장원석", "am11", "pm17", "800,000" }, { "1", "장원석", "am11", "pm17", "800,000" },
//				{ "1", "장원석", "am11", "pm17", "800,000" }, { "1", "장원석", "am11", "pm17", "800,000" },
//				{ "1", "장원석", "am11", "pm17", "800,000" }, { "1", "장원석", "am11", "pm17", "800,000" }
//
//		};

		super.setBounds(100, 100, 1280, 720);
		getContentPane().setLayout(null);

		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(300, 0, 975, 685);
		getContentPane().add(panel);
		panel.setLayout(null);

		model = new DefaultTableModel(v, cols);
		table = new JTable(model);
		table.addMouseListener(this);
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(14, 127, 947, 471);
		panel.add(scrollPane);

		vHead = new JLabel("사원정보");
		vHead.setFont(new Font("경기천년제목V Bold", Font.PLAIN, 32));
		vHead.setBounds(14, 35, 239, 79);
		panel.add(vHead);


		btnWkStart = new JButton("출근도장");
		btnWkStart.setForeground(new Color(82, 54, 55));
		btnWkStart.setBackground(new Color(255, 215, 0));
		btnWkStart.addActionListener(this);
		btnWkStart.setBounds(750, 50, 97, 30);
		panel.add(btnWkStart);
		
		btnWkEnd = new JButton("퇴근도장");
		btnWkEnd.setForeground(new Color(82, 54, 55));
		btnWkEnd.setBackground(new Color(255, 215, 0));
		btnWkEnd.addActionListener(this);
		btnWkEnd.setBounds(857, 50, 97, 30);
		panel.add(btnWkEnd);

		xSearch = new JTextField();
		xSearch.setText("날짜입력 : yyyy-MM-dd");
		xSearch.setBounds(550, 90, 198, 30);
		xSearch.addMouseListener(this);
		panel.add(xSearch);
		xSearch.setColumns(10);
		
		btnSearch = new JButton("검색");
		btnSearch.setForeground(new Color(82, 54, 55));
		btnSearch.setBackground(new Color(255, 215, 0));
		btnSearch.addActionListener(this);
		btnSearch.setBounds(750, 90, 97, 30);
		panel.add(btnSearch);
		
		btnRefresh= new JButton("새로고침");
		btnRefresh.setForeground(Color.white);
		btnRefresh.setBackground(Color.GRAY);
		btnRefresh.addActionListener(this);
		btnRefresh.setBounds(857, 90, 97, 30);
		panel.add(btnRefresh);

		panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 215, 0));
		panel_1.setForeground(new Color(82, 54, 52));
		panel_1.setBounds(0, 0, 300, 685);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		btnHead = new JButton("Kakao Team");
		btnHead.setBorderPainted(false);
		btnHead.setContentAreaFilled(false);
		btnHead.setFocusPainted(false);
		btnHead.setForeground(new Color(82, 54, 52));
		btnHead.setFont(new Font("경기천년제목V Bold", Font.PLAIN, 35));
		btnHead.setBounds(12, 10, 276, 78);
		panel_1.add(btnHead);
		
		setVisible(true);

	}
	
	private Vector getColumn() {
		Vector cols = new Vector();
		
		cols.add("사번");
		cols.add("이름");
		cols.add("직급");
		cols.add("아이디");
		cols.add("출근일");
		cols.add("출근시간");
		cols.add("퇴근시간");
		cols.add("하루 임금");

		return cols;
	}

	public void jTableRefresh(){
		
		EmpDAO dao = new EmpDAO();
		v = dao.getEmpList(dto);
		cols = getColumn();
		model= new DefaultTableModel(v, cols);
		table.setModel(model);	
		
	}
	
	public static void main(String[] args) {
		new EmpList();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnSearch) {

//			String[] name;
			String search = xSearch.getText();
			EmpDAO.selectSearchEmpNo(model, search);
//			name = EmpDAO.selectSearch(search);
//			for(int i = 0; i < name.length; i++) {
//				System.out.println(name[i]);
//			}
		}
//		jTableRefresh();
		
		if(e.getSource() == btnWkStart) {
//			EmpDTO dto = new EmpDTO();

			
			LocalDateTime now = LocalDateTime.now();
			
			DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("HH:mm");
			
			String nowDay = now.format(dtf1);
			String nowHourMin = now.format(dtf2);
						
			int result = JOptionPane.showConfirmDialog(null, "출근하시겠습니까?", "출근도장", JOptionPane.YES_NO_OPTION);
			if(result == JOptionPane.YES_OPTION) {
				WkAndPayData wkInfo = new WkAndPayData();
				wkInfo.insertWkDayAndStart(nowDay, nowHourMin);
				JOptionPane.showMessageDialog(null, "출근되었습니다.", "확인", JOptionPane.INFORMATION_MESSAGE);
				btnWkStart.setEnabled(false);
			}else
				return;
			
			jTableRefresh();
		}
		
		if(e.getSource() == btnWkEnd) {
//			EmpDTO dto = new EmpDTO();
//			EmpDAO dao = new EmpDAO();
			
			LocalDateTime now = LocalDateTime.now();
		
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");

			String nowHourMin = now.format(dtf);
						
			int result = JOptionPane.showConfirmDialog(null, "퇴근하시겠습니까?", "퇴근도장", JOptionPane.YES_NO_OPTION);
			if(result == JOptionPane.YES_OPTION) {
				String empNo = Login.empNo;
				String empPos = Login.empPos;
				
				WkAndPayData wkAndPayInfo = new WkAndPayData();
				String[] wk = wkAndPayInfo.getWkDay(empNo);
				String wkDay = wk[0];
				String wkStart = wk[1];
				
				System.out.println(wkDay);
				System.out.println(wkStart);
				
				String[] wkStartHourMin = wkStart.split(":");
				String[] wkEndHourMin = nowHourMin.split(":");
				
				double wkStartHour = Double.parseDouble(wkStartHourMin[0]);
				double wkStartMin = Double.parseDouble(wkStartHourMin[1]);
				double wkEndHour = Double.parseDouble(wkEndHourMin[0]);
				double wkEndMin = Double.parseDouble(wkEndHourMin[1]);
				
				String hourWage = wkAndPayInfo.getHourlyWage(empPos);
				int iHourWage = Integer.parseInt(hourWage);
//				
				int pay = (int) (((wkEndHour - wkStartHour) * iHourWage) + ((wkEndMin - wkStartMin)/60) * iHourWage);
				String strPay = String.valueOf(pay) + "원";
				System.out.println(strPay);
				wkAndPayInfo.updateWkEndAndPay(empNo, wkDay, nowHourMin, strPay);
				
				JOptionPane.showMessageDialog(null, "퇴근되었습니다.", "확인", JOptionPane.INFORMATION_MESSAGE);
				btnWkEnd.setEnabled(false);
			}else
				return;
			
			jTableRefresh();
		}		
		if(e.getSource() == btnRefresh) {
			jTableRefresh();
		}
		

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == xSearch) {
			xSearch.setText("");
		}
		
		if(e.getSource() == table) {
			int r = table.getSelectedRow();
			String id = "";
			id += table.getValueAt(r, 3);
			System.out.println(id);
			EmpProc emp = new EmpProc(id, this);
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {

		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}