package engine;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class FinalWnd extends JFrame{

	public FinalWnd(JFrame f, String msg){
		JOptionPane.showMessageDialog(f, msg);
	}
	
}
