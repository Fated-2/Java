package StudentSystem;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javafx.scene.image.Image;

/*
 * 银行信息管理系统
 * 1、记录不同用户的基本信息：用户名、身份证、账户、开户行、存款余额等
 * 2、能对银行用户信息进行增删查改等操作
 * 3、能实现存款、取款、登录、注册等功能。
 * 4、有欢迎界面
 * 5、有银行信息管理界面
 */
public class Main {
	

	public static void main(String[] args) {
		new WelcomeDemo();

	}

}


//书写进入界面
class WelcomeDemo extends JFrame{
	
	private JFrame window;
	private JButton enter,exit;
	private JLabel lb;
	private JPanel pEnter;
	
	public WelcomeDemo() {
		window=new JFrame("我家银行欢迎您");
		window.setSize(750, 540);//窗口大小
		window.setLocationRelativeTo(null);//居中显示
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//默认可关闭
		window.setLayout(null);//绝对布局
		window.setResizable(false);//窗口大小不可变
		window.setIconImage(new ImageIcon("4811FF28.png").getImage());//设置图标
		
		ImageIcon icon = new ImageIcon("450007f6f34d9d1e.jpg");
		icon.setImage(icon.getImage().getScaledInstance((int)(icon.getIconWidth()), 
				(int)(icon.getIconHeight()*0.95),0 ));
		lb=new JLabel(new ImageIcon("450007f6f34d9d1e.jpg"));
		lb.setBounds(0, 0, (int)(icon.getIconWidth()), (int)(icon.getIconHeight()*0.95));
		window.add(lb,BorderLayout.NORTH);//添加带图片的标签
		
		exit = new JButton("退出系统");
		enter=new JButton("进入系统");
		enter.setBounds(200,440,120,50);
		exit.setBounds(350, 440,120,50);
		window.add(enter);
		window.add(exit);
		
		addListened();
		window.setVisible(true);
		
	}
	
	//添加按钮监听事件
	public void addListened() {
		enter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("按钮被按下");
				//进入银行信息管理界面
					new BankJPanel();
					window.dispose();//关闭窗口
				
			}
		});
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				window.dispose();//关闭窗口
				
			}
		});
	}
	
	
}
