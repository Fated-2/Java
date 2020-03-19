package Student;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
//(只能有一个公共public类)
public class Main {

	
	public static void main(String[] args) {
		new LoginDemo();

	}

}

//书写登录页面：
class LoginDemo extends JFrame{
	//用户名以及密码：
	private String username="123";
	private String password="123";
	//窗口
	public JFrame window;
	//用户名、密码、文本框：
	public JTextField user;
	public JPasswordField pwd;
	//登录的按钮
	public JButton login;
	//构造器初始化
	public LoginDemo() {
		//面板初始化：
		window=new JFrame("学生管理系统");
		//设置尺寸  600   400：
		window.setSize(600, 400);
		//居中显示：
		window.setLocationRelativeTo(null);
		//默认可关闭：
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//设置为绝对布局：
		window.setLayout(null);
		//设置窗口大小不可变：
		window.setResizable(false);
		
		/*
		 * 用户标签username_label：100 100 100 50
		 * 密码标签password_label：100 200 100 50
		 * 用户名文本框user：150 100 300 50
		 * 密码文本框pwd：150 200 300 50
		 * 按钮login：250 300 100 50
		 */
		//用户
		JLabel username_label = new JLabel("用户");
		username_label.setBounds(100, 100, 100, 50);
		window.add(username_label);
		
		user=new JTextField();
		user.setBounds(150, 100, 300, 50);
		window.add(user);
		
		//密码
		JLabel password=new JLabel("密码");
		password.setBounds(100, 200, 100, 50);
		window.add(password);
		
		pwd=new JPasswordField();
		pwd.setBounds(150, 200, 300, 50);
		window.add(pwd);
		
		//按钮
		login=new JButton("登录");
		login.setBounds(250, 300, 100, 50);
		window.add(login);
		
		init();//调用监听事件
		
		//设置面板可见
		window.setVisible(true);
	}
	
	//监听按钮事件
	public void init(){
		//登录按钮的监听器
		login.addActionListener(new ActionListener() {
			//当鼠标被按下时会调用该方法
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(user.getText());
				System.out.println(pwd.getText());
				//用户名及密码正确 不正确
				if(user.getText().equals(username) && pwd.getText().equals(password)){
					
					/*
					 * 弹出提示框：正确  跳转界面  登录页面消失
					 */
					JOptionPane .showMessageDialog(null, "密码正确","登录提示框",JOptionPane.INFORMATION_MESSAGE);
					StudentJPanel sp=new StudentJPanel();
					window.dispose();//关闭窗口！
					
				}else{
					//密码不正确  弹出提示框：错误  登录页面文本框内容消失
					JOptionPane .showMessageDialog(null, "密码错误，请重新登录","登录提示框",JOptionPane.INFORMATION_MESSAGE);
					user.setText(null);
					pwd.setText(null);//设置内容为空
					
				}
				
			}
		});
	}
	
	
}
