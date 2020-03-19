package Student;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.text.AbstractDocument.Content;

/*
 * 实现学生信息管理系统的主要功能
 * 增加  撤销  查询
 */
public class StudentJPanel {
	//声明所需变量
	public JFrame student;//主面板
	//三个界面--一个是标题、一个负责查询，一个负责增加
	private JPanel p1,p2,title;
	//头部标题：
	private JTabbedPane tab;
	//容器：用来存储两个界面--并且规范界面
	private Container container;
	//添加按钮、撤销增加按钮：
	private JButton b1,b2;
	//姓名、成绩的标签
	private JLabel nameLabel,gradeLabel;
	//姓名和成绩的文本框
	private JTextField textName,textGrade;
	//不可变的文本框--用来显示学生信息内容
	private JTextArea showGrade;
	
	/*
	 * 查找面板中的变量
	 */
	//查找的标签
	private Label searchLabel;
	//确认查找的按钮
	private JButton sBut;
	//两个文本框（一个为查找信息的文本框，一个为
	private JTextField searchText,resultText;
	
	//按钮监听类
	Listener l;
	//声明数组用来存储学号和成绩
	int[] id;
	int[] grade;
	//设置下标--下标必须是灵活的，进行赋值时才不会重复
	int index;
	//设置布尔类型判断是否有重复学生
	boolean[] bl;
	
	
	//构造器初始化
	public StudentJPanel(){
		l=new Listener();//初始化监听器
		id=new int[100];
		grade=new int[100];
		bl=new boolean[100]; 
		index=0;
		//面板初始化：
		student=new JFrame("学生信息管理系统");
		//设置标题
		tab=new JTabbedPane(JTabbedPane.TOP);
		//容器：设置在student面板上
		container=student.getContentPane();
		//初始化3个（添加、查询）面板：
		p1=new JPanel();
		p2=new JPanel();
		title=new JPanel();
		//初始化两个标签（姓名、成绩）
		nameLabel=new JLabel("学号");
		gradeLabel=new JLabel("成绩");
		//初始化两个文本框：
		textGrade=new JTextField(15);
		textName=new JTextField(15);
		//两个按钮（确认添加以及撤销添加）
		b1=new JButton("确认添加");
		b2=new JButton("撤销添加");
		//显示文本框：
		showGrade=new JTextArea(13,35);
		//showGrade.setBounds(10, 500, 500, 200);
		//添加滚动效果:想要给谁添加滚动效果小括号里写谁
		
		
		//设置面板属性
		student.setSize(500, 420);
		student.setLocationRelativeTo(null);//居中显示
		//绝对布局：
		student.setLayout(null);
		//设置窗口不可变：
		student.setResizable(false);
		//设置默认可关闭
		student.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//设置表头--两个查询以及添加面板：
		//tab.add(对象（面板）,"面板上显示的内容")
		tab.add(p1,"成绩输入");
		tab.add(p2,"成绩查询");
		title.add(new JLabel("学生信息管理系统"));
		
		//添加到容器中
		//容器.add(对象，位置)
		container.setLayout(new BorderLayout());//设置容器的位置
		container.add(title,BorderLayout.NORTH);//顶部
		container.add(tab,BorderLayout.CENTER);//中部
		
		//声明一个容器：用来存储姓名、成绩的文本框以及标题
		Container c1=new Container();
		//设置容器的位置:浮动--一般向左浮动
		c1.setLayout(new FlowLayout());
		//将姓名、成绩的文本框以及标签加到容器中
		c1.add(nameLabel);
		c1.add(textName);
		c1.add(gradeLabel);
		c1.add(textGrade);
		//容器添加到增加的面板中
		p1.add(c1,BorderLayout.WEST);//p1容器在面板中的位置
		p1.add(c1);	
		p1.add(showGrade);
		JScrollPane jp=new JScrollPane(showGrade);
		p1.add(jp);
		
		//按钮添加：
		Container c2=new Container();
		c2.setLayout(new FlowLayout());
		c2.add(b1);
		c2.add(b2);
		p1.add(c2);
		
		//给按钮添加监听事件：
		b1.addActionListener(l);
		b2.addActionListener(l);
		
		/*
		 * 初始化查询面板中的所需对象：
		 */
		searchLabel=new Label("请输入您查找的学号");
		sBut=new JButton("确认查询");
		searchText=new JTextField(15);
		resultText=new JTextField(15);
		//查询面板布局
		Container c3=new Container();
		c3.setLayout(new FlowLayout());
		c3.add(searchLabel);
		c3.add(searchText);
		c3.add(sBut);
		p2.add(c3,BorderLayout.AFTER_LAST_LINE);
		p2.add(resultText);
		sBut.addActionListener(l);
		
		
		student.setVisible(true);
		
	}
	
	//监听事件（Java内部类）：实现监听接口
	class Listener implements ActionListener{
		//被点击时/被按下时调用：
		@Override
		public void actionPerformed(ActionEvent e) {
			// ActionEvent e--指代的是按下的那个按钮
			//e.getSource():获取按下的按钮
			if(e.getSource()==b1){
				System.out.println("确认添加");
				/*
				 * 1.获取文本框内容--显示内容的文本框
				 * 两个数组--名-成绩   下标
				 * 2.添加完成后，文本框内容消失
				 */
				if(!(textName.getText().equals("")) && !(textGrade.getText().equals(""))){
					String str=textName.getText();
					id[index]=Integer.parseInt(str);
					String str1=textGrade.getText();
					grade[index]=Integer.parseInt(str1);
					
					if(bl[id[index]]==true){
						
						/*
						 * 弹出提示框：学生重复  跳转界面 
						 */
						JOptionPane .showMessageDialog(null, "学生已存在","输入学生学号提示框",JOptionPane.INFORMATION_MESSAGE);
						textName.setText(null);//清空学号文本框
						textGrade.setText(null);//清空成绩文本框
					}else{
						index++;
						sortMess();
					}
					
				}
				textName.setText("");
				textGrade.setText("");
				
			}
			if(e.getSource()==b2){
				System.out.println("撤销添加");
				if(index>0){//撤销--减小坐标
					index--;
					sortMess();
				}
			}
			if(e.getSource()==sBut){
				System.out.println("确定查询");
				if(!(searchText.getText().equals(""))){
					//不为空就进行查询
//					System.out.println(Arrays.toString(bl));
					if(bl[Integer.parseInt(searchText.getText())]==false){
						//如果学生不存在
						JOptionPane .showMessageDialog(null, "学生不存在","输入学生学号提示框",JOptionPane.INFORMATION_MESSAGE);
						searchText.setText(null);//清空查询文本框
						resultText.setText(null);//清空结果文本框
					}else{
							for (int i = 0; i < index; i++) {
							if(id[i]==Integer.parseInt(searchText.getText())){
								//如果名字数组中内容与文本框内容相等，则将内容输出到查询文本框中
								resultText.setText("学号："+id[i]+",成绩:"+grade[i]);
								return;//如果查到直接停止程序！
							}
						}
					}
					
				}
			}
			
		}
		
		//创建一个方法用于将数组内容添加到显示文本框中
		public void sortMess(){
			if(!(showGrade.getText().equals(""))){
				//如果文本框内有内容将内容清空
				showGrade.setText("");
			}
			
			//index有多大，就说明有几个值
				for (int i = 0; i < index; i++) {
					bl[id[i]]=true;
					String str="学号："+id[i]+"\t"+"成绩"+grade[i];
					//将获取到的两个数组中的内容发送到文本框中
					showGrade.append(str+"\n");
				}
			
		}
		
		
		
	}
	
	
	
	
	public static void main(String[] args) {
		new StudentJPanel();

	}

}
