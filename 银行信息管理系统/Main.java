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
 * ������Ϣ����ϵͳ
 * 1����¼��ͬ�û��Ļ�����Ϣ���û��������֤���˻��������С��������
 * 2���ܶ������û���Ϣ������ɾ��ĵȲ���
 * 3����ʵ�ִ�ȡ���¼��ע��ȹ��ܡ�
 * 4���л�ӭ����
 * 5����������Ϣ�������
 */
public class Main {
	

	public static void main(String[] args) {
		new WelcomeDemo();

	}

}


//��д�������
class WelcomeDemo extends JFrame{
	
	private JFrame window;
	private JButton enter,exit;
	private JLabel lb;
	private JPanel pEnter;
	
	public WelcomeDemo() {
		window=new JFrame("�Ҽ����л�ӭ��");
		window.setSize(750, 540);//���ڴ�С
		window.setLocationRelativeTo(null);//������ʾ
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Ĭ�Ͽɹر�
		window.setLayout(null);//���Բ���
		window.setResizable(false);//���ڴ�С���ɱ�
		window.setIconImage(new ImageIcon("4811FF28.png").getImage());//����ͼ��
		
		ImageIcon icon = new ImageIcon("450007f6f34d9d1e.jpg");
		icon.setImage(icon.getImage().getScaledInstance((int)(icon.getIconWidth()), 
				(int)(icon.getIconHeight()*0.95),0 ));
		lb=new JLabel(new ImageIcon("450007f6f34d9d1e.jpg"));
		lb.setBounds(0, 0, (int)(icon.getIconWidth()), (int)(icon.getIconHeight()*0.95));
		window.add(lb,BorderLayout.NORTH);//��Ӵ�ͼƬ�ı�ǩ
		
		exit = new JButton("�˳�ϵͳ");
		enter=new JButton("����ϵͳ");
		enter.setBounds(200,440,120,50);
		exit.setBounds(350, 440,120,50);
		window.add(enter);
		window.add(exit);
		
		addListened();
		window.setVisible(true);
		
	}
	
	//��Ӱ�ť�����¼�
	public void addListened() {
		enter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("��ť������");
				//����������Ϣ�������
					new BankJPanel();
					window.dispose();//�رմ���
				
			}
		});
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				window.dispose();//�رմ���
				
			}
		});
	}
	
	
}
