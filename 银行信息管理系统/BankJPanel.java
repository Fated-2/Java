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
 * ʵ�����й���ϵͳ����Ҫ����
 * ���ӡ�ɾ�����޸ġ���ѯ
 */
public class BankJPanel {

	private JFrame Bank;//�����
	private JPanel p1,p2;//��������
	private JLabel lbTitle;//�б��ǩ
	private JTable table;//���
	String[][] data=null;
	String[] cols = {"�û���","���֤","�˻�","������","������"};
	
	private JLabel lbUser;//�û���ǩ
	private JLabel lbIdentify;//���֤��ǩ
	private JLabel lbAccount;//�˻���ǩ
	private JLabel lbDeposit;//�����б�ǩ
	private JLabel lbRemain;//����ǩ
	
	private JTextField tfUser;//�û��ı���
	private JTextField tfIdentify;//���֤�ı���
	private JTextField tfAccount;//�˻��ı���
	private JTextField tfDeposit;//�������ı���
	private JTextField tfRemain;//����ı���
	
	private JButton btAdd;//��Ӱ�ť
	private JButton btDelete;//ɾ����ť
	private JButton btUpdate;//�޸İ�ť
	
	int index;//�����±�
	String[] name;
	String[] id;
	String[] ac;
	String[] de;
	String[] re;
	Listenered l;
	
	//��������ʼ�� 
	public BankJPanel() {
		
		l = new Listenered();
		index = 1;
		data = new String[1][5];
		name = new String[100];
		id = new String[100];
		ac = new String[100];
		de = new String[100];
		re = new String[100];
		
		//���ڳ�ʼ��
		Bank=new JFrame("������Ϣ����ϵͳ");
		Bank.setSize(600, 400);
		Bank.setLocationRelativeTo(null);//����
		Bank.setResizable(false);
		Bank.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Bank.setLayout(null);
		
		//������
		lbTitle=new JLabel("�û��Ļ�����Ϣ");
		lbTitle.setBounds(250, 20, 140, 25);
		Bank.add(lbTitle);
		
		//������������
		table = new JTable();
		String[][] data = new String[1][5];
		data[0][0]=name[0]="С��";
		data[0][1]=id[0]="123456789";
		data[0][2]=ac[0]="123";
		data[0][3]=de[0]="456";
		data[0][4]=re[0]="789";
		table = new JTable(data,cols);
		table.getTableHeader().setReorderingAllowed(false);//���ɸı���˳��
		table.getTableHeader().setResizingAllowed(false);//���ɸı��п�
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);//ֻ��ѡһ��
		JScrollPane jp=new JScrollPane(table);//��ӹ���Ч��
		jp.setBounds(50, 50, 500, 200);
		Bank.add(jp);
		
		//��ӱ༭��������
		lbUser = new JLabel("�û���",JLabel.RIGHT);
		lbUser.setBounds(40, 265, 50, 25);
		Bank.add(lbUser);
		lbIdentify = new JLabel("���֤",JLabel.RIGHT);
		lbIdentify.setBounds(210, 265, 50, 25);
		Bank.add(lbIdentify);
		lbAccount = new JLabel("�˻�",JLabel.RIGHT);
		lbAccount.setBounds(370, 265, 50, 25);
		Bank.add(lbAccount);
		lbDeposit = new JLabel("������",JLabel.RIGHT);
		lbDeposit.setBounds(40, 295, 50, 25);
		Bank.add(lbDeposit);
		lbRemain = new JLabel("������",JLabel.RIGHT);
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
		
		//��Ӱ�ť
		btAdd = new JButton("�����Ϣ");
		btAdd.setBounds(100, 330, 100, 25);
		Bank.add(btAdd);
		btUpdate = new JButton("�޸���Ϣ");
		btUpdate.setBounds(250, 330, 100, 25);
		Bank.add(btUpdate);
		btDelete = new JButton("ɾ����Ϣ");
		btDelete.setBounds(400, 330, 100, 25);
		Bank.add(btDelete);
		
		//����ť��Ӽ����¼�
		btAdd.addActionListener(l);
		btDelete.addActionListener(l);
		btUpdate.addActionListener(l);
		
		//��������
		addListened();
		Bank.setVisible(true);
		
	}
	
	//��Ӱ�ť�����¼�
	public void addListened() {
		//���Ĵ����¼�
		table.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row = table.getSelectedRow();//��ȡ����б�ѡ�е���
				//�����ı��������
				tfUser.setText(data[row][0]);
				tfIdentify.setText(data[row][1]);
				tfAccount.setText(data[row][2]);
				tfDeposit.setText(data[row][3]);
				tfRemain.setText(data[row][4]);
			}
		});
	}

		
		//�����¼���java�ڲ��ࣩ��ʵ�ּ����ӿ�
		class Listenered implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				//��ť�����»����ʱ����
				// ActionEvent e--ָ�����ǰ��µ�������ť
				//e.getSource():��ȡ���µİ�ť
				
				if(e.getSource() == btAdd) {
					
					System.out.println("ȷ�����");
					int flag=0;//����Ƿ����ظ�����Ӳ�ͬ���û���Ϣ
					if(!(tfUser.getText().equals(""))) {
						
						//��ȡ�û����������
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
							JOptionPane .showMessageDialog(null, "��ӳɹ�","�����û���Ϣ��ʾ��",JOptionPane.INFORMATION_MESSAGE);
							clear();
						}else {
							
							/*
							 * ������ʾ���û��ظ�  ��ת���� 
							 */
							JOptionPane .showMessageDialog(null, "�û��Ѵ���","�����û���Ϣ��ʾ��",JOptionPane.INFORMATION_MESSAGE);
							clear();//����ı���
							
						}
						
					}
					
					
				}
				
				if(e.getSource() == btUpdate) {
					
					System.out.println("ȷ���޸�");
					if(!(tfUser.getText().equals(""))) {
						
						//��ȡ�û����������
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
						JOptionPane .showMessageDialog(null, "�޸ĳɹ�","�����û���Ϣ��ʾ��",JOptionPane.INFORMATION_MESSAGE);
						clear();
					}
				}
				
				if(e.getSource() == btDelete) {
					System.out.println("ȷ��ɾ��");
					if(!(tfUser.getText().equals(""))) {
						
						//��ȡ�û����������
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
						JOptionPane .showMessageDialog(null, "ɾ���ɹ�","�����û���Ϣ��ʾ��",JOptionPane.INFORMATION_MESSAGE);
						clear();
					}
				}
				
				
			}
			
			
			//����һ���������µ�����Ԫ����ӵ������
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
			
			//����ı���
			public void clear() {
				tfUser.setText(null);//����û��ı���
				tfIdentify.setText(null);//������֤�ı���
				tfAccount.setText(null);
				tfDeposit.setText(null);
				tfRemain.setText(null);
			}
			
		}
	
	
	public static void main(String[] args) {
		new BankJPanel();

	}

}
