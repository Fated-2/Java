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
 * ʵ��ѧ����Ϣ����ϵͳ����Ҫ����
 * ����  ����  ��ѯ
 */
public class StudentJPanel {
	//�����������
	public JFrame student;//�����
	//��������--һ���Ǳ��⡢һ�������ѯ��һ����������
	private JPanel p1,p2,title;
	//ͷ�����⣺
	private JTabbedPane tab;
	//�����������洢��������--���ҹ淶����
	private Container container;
	//��Ӱ�ť���������Ӱ�ť��
	private JButton b1,b2;
	//�������ɼ��ı�ǩ
	private JLabel nameLabel,gradeLabel;
	//�����ͳɼ����ı���
	private JTextField textName,textGrade;
	//���ɱ���ı���--������ʾѧ����Ϣ����
	private JTextArea showGrade;
	
	/*
	 * ��������еı���
	 */
	//���ҵı�ǩ
	private Label searchLabel;
	//ȷ�ϲ��ҵİ�ť
	private JButton sBut;
	//�����ı���һ��Ϊ������Ϣ���ı���һ��Ϊ
	private JTextField searchText,resultText;
	
	//��ť������
	Listener l;
	//�������������洢ѧ�źͳɼ�
	int[] id;
	int[] grade;
	//�����±�--�±���������ģ����и�ֵʱ�Ų����ظ�
	int index;
	//���ò��������ж��Ƿ����ظ�ѧ��
	boolean[] bl;
	
	
	//��������ʼ��
	public StudentJPanel(){
		l=new Listener();//��ʼ��������
		id=new int[100];
		grade=new int[100];
		bl=new boolean[100]; 
		index=0;
		//����ʼ����
		student=new JFrame("ѧ����Ϣ����ϵͳ");
		//���ñ���
		tab=new JTabbedPane(JTabbedPane.TOP);
		//������������student�����
		container=student.getContentPane();
		//��ʼ��3������ӡ���ѯ����壺
		p1=new JPanel();
		p2=new JPanel();
		title=new JPanel();
		//��ʼ��������ǩ���������ɼ���
		nameLabel=new JLabel("ѧ��");
		gradeLabel=new JLabel("�ɼ�");
		//��ʼ�������ı���
		textGrade=new JTextField(15);
		textName=new JTextField(15);
		//������ť��ȷ������Լ�������ӣ�
		b1=new JButton("ȷ�����");
		b2=new JButton("�������");
		//��ʾ�ı���
		showGrade=new JTextArea(13,35);
		//showGrade.setBounds(10, 500, 500, 200);
		//��ӹ���Ч��:��Ҫ��˭��ӹ���Ч��С������д˭
		
		
		//�����������
		student.setSize(500, 420);
		student.setLocationRelativeTo(null);//������ʾ
		//���Բ��֣�
		student.setLayout(null);
		//���ô��ڲ��ɱ䣺
		student.setResizable(false);
		//����Ĭ�Ͽɹر�
		student.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//���ñ�ͷ--������ѯ�Լ������壺
		//tab.add(������壩,"�������ʾ������")
		tab.add(p1,"�ɼ�����");
		tab.add(p2,"�ɼ���ѯ");
		title.add(new JLabel("ѧ����Ϣ����ϵͳ"));
		
		//��ӵ�������
		//����.add(����λ��)
		container.setLayout(new BorderLayout());//����������λ��
		container.add(title,BorderLayout.NORTH);//����
		container.add(tab,BorderLayout.CENTER);//�в�
		
		//����һ�������������洢�������ɼ����ı����Լ�����
		Container c1=new Container();
		//����������λ��:����--һ�����󸡶�
		c1.setLayout(new FlowLayout());
		//���������ɼ����ı����Լ���ǩ�ӵ�������
		c1.add(nameLabel);
		c1.add(textName);
		c1.add(gradeLabel);
		c1.add(textGrade);
		//������ӵ����ӵ������
		p1.add(c1,BorderLayout.WEST);//p1����������е�λ��
		p1.add(c1);	
		p1.add(showGrade);
		JScrollPane jp=new JScrollPane(showGrade);
		p1.add(jp);
		
		//��ť��ӣ�
		Container c2=new Container();
		c2.setLayout(new FlowLayout());
		c2.add(b1);
		c2.add(b2);
		p1.add(c2);
		
		//����ť��Ӽ����¼���
		b1.addActionListener(l);
		b2.addActionListener(l);
		
		/*
		 * ��ʼ����ѯ����е��������
		 */
		searchLabel=new Label("�����������ҵ�ѧ��");
		sBut=new JButton("ȷ�ϲ�ѯ");
		searchText=new JTextField(15);
		resultText=new JTextField(15);
		//��ѯ��岼��
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
	
	//�����¼���Java�ڲ��ࣩ��ʵ�ּ����ӿ�
	class Listener implements ActionListener{
		//�����ʱ/������ʱ���ã�
		@Override
		public void actionPerformed(ActionEvent e) {
			// ActionEvent e--ָ�����ǰ��µ��Ǹ���ť
			//e.getSource():��ȡ���µİ�ť
			if(e.getSource()==b1){
				System.out.println("ȷ�����");
				/*
				 * 1.��ȡ�ı�������--��ʾ���ݵ��ı���
				 * ��������--��-�ɼ�   �±�
				 * 2.�����ɺ��ı���������ʧ
				 */
				if(!(textName.getText().equals("")) && !(textGrade.getText().equals(""))){
					String str=textName.getText();
					id[index]=Integer.parseInt(str);
					String str1=textGrade.getText();
					grade[index]=Integer.parseInt(str1);
					
					if(bl[id[index]]==true){
						
						/*
						 * ������ʾ��ѧ���ظ�  ��ת���� 
						 */
						JOptionPane .showMessageDialog(null, "ѧ���Ѵ���","����ѧ��ѧ����ʾ��",JOptionPane.INFORMATION_MESSAGE);
						textName.setText(null);//���ѧ���ı���
						textGrade.setText(null);//��ճɼ��ı���
					}else{
						index++;
						sortMess();
					}
					
				}
				textName.setText("");
				textGrade.setText("");
				
			}
			if(e.getSource()==b2){
				System.out.println("�������");
				if(index>0){//����--��С����
					index--;
					sortMess();
				}
			}
			if(e.getSource()==sBut){
				System.out.println("ȷ����ѯ");
				if(!(searchText.getText().equals(""))){
					//��Ϊ�վͽ��в�ѯ
//					System.out.println(Arrays.toString(bl));
					if(bl[Integer.parseInt(searchText.getText())]==false){
						//���ѧ��������
						JOptionPane .showMessageDialog(null, "ѧ��������","����ѧ��ѧ����ʾ��",JOptionPane.INFORMATION_MESSAGE);
						searchText.setText(null);//��ղ�ѯ�ı���
						resultText.setText(null);//��ս���ı���
					}else{
							for (int i = 0; i < index; i++) {
							if(id[i]==Integer.parseInt(searchText.getText())){
								//��������������������ı���������ȣ��������������ѯ�ı�����
								resultText.setText("ѧ�ţ�"+id[i]+",�ɼ�:"+grade[i]);
								return;//����鵽ֱ��ֹͣ����
							}
						}
					}
					
				}
			}
			
		}
		
		//����һ���������ڽ�����������ӵ���ʾ�ı�����
		public void sortMess(){
			if(!(showGrade.getText().equals(""))){
				//����ı����������ݽ��������
				showGrade.setText("");
			}
			
			//index�ж�󣬾�˵���м���ֵ
				for (int i = 0; i < index; i++) {
					bl[id[i]]=true;
					String str="ѧ�ţ�"+id[i]+"\t"+"�ɼ�"+grade[i];
					//����ȡ�������������е����ݷ��͵��ı�����
					showGrade.append(str+"\n");
				}
			
		}
		
		
		
	}
	
	
	
	
	public static void main(String[] args) {
		new StudentJPanel();

	}

}
