package StudentSystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.OutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;


/*
 * 实现银行管理系统的主要功能
 * 增加、删除、修改、查询
 */
public class BankJPanel {

	private JFrame Bank;//主面板
	private JPanel p1,p2;//两个界面
	private JLabel lbTitle;//列表标签
	private JTable table;//表格
	String[][] data=null;
	String[] cols = {"用户名","身份证","账户","开户行","存款余额"};
	
	private JLabel lbUser;//用户标签
	private JLabel lbIdentify;//身份证标签
	private JLabel lbAccount;//账户标签
	private JLabel lbDeposit;//开户行标签
	private JLabel lbRemain;//余额标签
	
	private JTextField tfUser;//用户文本框
	private JTextField tfIdentify;//身份证文本框
	private JTextField tfAccount;//账户文本框
	private JTextField tfDeposit;//开户行文本框
	private JTextField tfRemain;//余额文本框
	
	private JButton btAdd;//添加按钮
	private JButton btDelete;//删除按钮
	private JButton btUpdate;//修改按钮
	
	int index;//设置下标
	String[] name;
	String[] id;
	String[] ac;
	String[] de;
	String[] re;
	Listenered l;
	
	//构造器初始化 
	public BankJPanel() {
		
		l = new Listenered();
		index = 1;
		data = new String[1][5];
		name = new String[100];
		id = new String[100];
		ac = new String[100];
		de = new String[100];
		re = new String[100];
		
		//窗口初始化
		Bank=new JFrame("银行信息管理系统");
		Bank.setSize(600, 400);
		Bank.setLocationRelativeTo(null);//居中
		Bank.setResizable(false);
		Bank.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Bank.setLayout(null);
		
		//表格标题
		lbTitle=new JLabel("用户的基本信息");
		lbTitle.setBounds(250, 20, 140, 25);
		Bank.add(lbTitle);
		
		//表格添加与设置
		table = new JTable();
		String[][] data = new String[1][5];
		data[0][0]=name[0]="小张";
		data[0][1]=id[0]="123456789";
		data[0][2]=ac[0]="123";
		data[0][3]=de[0]="456";
		data[0][4]=re[0]="789";
		table = new JTable(data,cols);
		table.getTableHeader().setReorderingAllowed(false);//不可改变列顺序
		table.getTableHeader().setResizingAllowed(false);//不可改变列宽
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);//只能选一行
		JScrollPane jp=new JScrollPane(table);//添加滚动效果
		jp.setBounds(50, 50, 500, 200);
		Bank.add(jp);
		
		//添加编辑区域的组件
		lbUser = new JLabel("用户名",JLabel.RIGHT);
		lbUser.setBounds(40, 265, 50, 25);
		Bank.add(lbUser);
		lbIdentify = new JLabel("身份证",JLabel.RIGHT);
		lbIdentify.setBounds(210, 265, 50, 25);
		Bank.add(lbIdentify);
		lbAccount = new JLabel("账户",JLabel.RIGHT);
		lbAccount.setBounds(370, 265, 50, 25);
		Bank.add(lbAccount);
		lbDeposit = new JLabel("开户行",JLabel.RIGHT);
		lbDeposit.setBounds(40, 295, 50, 25);
		Bank.add(lbDeposit);
		lbRemain = new JLabel("存款余额",JLabel.RIGHT);
		lbRemain.setBounds(200, 295, 70, 25);
		Bank.add(lbRemain);
		tfUser = new JTextField();
		tfUser.setBounds(90, 265, 120, 25);
		Bank.add(tfUser);
		tfIdentify = new JTextField();
		tfIdentify.setBounds(261, 265, 120, 25);
		Bank.add(tfIdentify);
		tfAccount = new JTextField();
		tfAccount.setBounds(421, 265, 120, 25);
		Bank.add(tfAccount);
		tfDeposit = new JTextField();
		tfDeposit.setBounds(90, 295, 120, 25);
		Bank.add(tfDeposit);
		tfRemain = new JTextField();
		tfRemain.setBounds(271, 295, 120, 25);
		Bank.add(tfRemain);
		
		//添加按钮
		btAdd = new JButton("添加信息");
		btAdd.setBounds(100, 330, 100, 25);
		Bank.add(btAdd);
		btUpdate = new JButton("修改信息");
		btUpdate.setBounds(250, 330, 100, 25);
		Bank.add(btUpdate);
		btDelete = new JButton("删除信息");
		btDelete.setBounds(400, 330, 100, 25);
		Bank.add(btDelete);
		
		//给按钮添加监听事件
		btAdd.addActionListener(l);
		btDelete.addActionListener(l);
		btUpdate.addActionListener(l);
		
		//鼠标表格监听
		addListened();
		Bank.setVisible(true);
		
	}
	
	//添加按钮监听事件
	public void addListened() {
		//表格的处理事件
		table.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row = table.getSelectedRow();//获取表格中被选中的行
				//设置文本框的内容
				tfUser.setText(data[row][0]);
				tfIdentify.setText(data[row][1]);
				tfAccount.setText(data[row][2]);
				tfDeposit.setText(data[row][3]);
				tfRemain.setText(data[row][4]);
			}
		});
	}

		
		//监听事件（java内部类）：实现监听接口
		class Listenered implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				//按钮被按下货点击时调用
				// ActionEvent e--指代的是按下的辣个按钮
				//e.getSource():获取按下的按钮
				
				if(e.getSource() == btAdd) {
					
					System.out.println("确认添加");
					int flag=0;//标记是否有重复，添加不同的用户信息
					if(!(tfUser.getText().equals(""))) {
						
						//获取用户输入的数据
						name[index] = tfUser.getText();
						
						id[index] = tfIdentify.getText();
						ac[index] = tfAccount.getText();
						de[index] = tfDeposit.getText();
						re[index] = tfRemain.getText();
						for (int i = 0; i < index; i++) {
							if(id[index].equals(id[i])) {
								flag=1;
							}
						}
						
						if(flag == 0) {
							
							index++;
							sortMess();
							JOptionPane .showMessageDialog(null, "添加成功","输入用户信息提示框",JOptionPane.INFORMATION_MESSAGE);
							clear();
						}else {
							
							/*
							 * 弹出提示框：用户重复  跳转界面 
							 */
							JOptionPane .showMessageDialog(null, "用户已存在","输入用户信息提示框",JOptionPane.INFORMATION_MESSAGE);
							clear();//清空文本框
							
						}
						
					}
					
					
				}
				
				if(e.getSource() == btUpdate) {
					
					System.out.println("确认修改");
					if(!(tfUser.getText().equals(""))) {
						
						//获取用户输入的数据
						String user = tfUser.getText();
						String identify = tfIdentify.getText();
						String account = tfAccount.getText();
						String deposit = tfDeposit.getText();
						String remain = tfRemain.getText();
						for (int i = 0; i < index; i++) {
							if(identify.equals(id[i])) {
								name[index] = user;
								id[index] = identify;
								ac[index] = account;
								de[index] = deposit;
								re[index] = remain;
							}
						}
						sortMess();
						JOptionPane .showMessageDialog(null, "修改成功","输入用户信息提示框",JOptionPane.INFORMATION_MESSAGE);
						clear();
					}
				}
				
				if(e.getSource() == btDelete) {
					System.out.println("确认删除");
					if(!(tfUser.getText().equals(""))) {
						
						//获取用户输入的数据
						String identify = tfIdentify.getText();
						for (int i = 0; i < index-1; i++) {
							if(identify.equals(id[i])) {
								name[index] = name[index+1];
								id[index] = id[index+1];
								ac[index] = ac[index+1];
								de[index] = de[index+1];
								re[index] = re[index+1];
							}
						}
						index = index-1;
						sortMess();
						JOptionPane .showMessageDialog(null, "删除成功","输入用户信息提示框",JOptionPane.INFORMATION_MESSAGE);
						clear();
					}
				}
				
				
			}
			
			
			//创建一个方法把新的数组元素添加到表格中
			public void sortMess() {
				data = new String[index][5];
				for (int i = 0; i < index; i++) {
					data[i][0] = name[i];
					data[i][1] = id[i];
					data[i][2] = ac[i];
					data[i][3] = de[i];
					data[i][4] = re[i];
				}
				DefaultTableModel tableModel = new DefaultTableModel(data, cols);
				table.setModel(tableModel);
			}
			
			//清空文本框
			public void clear() {
				tfUser.setText(null);//清空用户文本框
				tfIdentify.setText(null);//清空身份证文本框
				tfAccount.setText(null);
				tfDeposit.setText(null);
				tfRemain.setText(null);
			}
			
		}
	
	
	public static void main(String[] args) {
		new BankJPanel();

	}

}
