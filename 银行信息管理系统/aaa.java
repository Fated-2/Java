package StudentSystem;
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JFrame;
class GridTest extends JFrame 
{
	GridTest()
	{
		setSize(300,200);
		setVisible(true);
		setTitle("ajkshjkahsjk");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new GridLayout(2, 3));
		char str='a';
		for(int i=0;i<6;i++)
		{
			add(new JButton("°´Å¥"+str++));
		}
		validate();
	}
}
public class aaa
{
	public static void main(String[] args) {
		new GridTest();
	}

}