package graphic;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import utils.Utils;
import engine.Engine;
import engine.Person;
import engine.FinalWnd;


@SuppressWarnings({"serial","rawtypes","unused"})
public class Wnd extends JFrame{

	private JFrame f=this;
	
	private Person person;
	private Engine engine;
	
	private final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	private final Calendar cal = new GregorianCalendar();
	
	private final int WIDTH=400;
	private final int HEIGHT=210;
	
	private final int S_WDT=Integer.valueOf((int)screen.getWidth());
	private final int S_HGT=Integer.valueOf((int)screen.getHeight());
	private final int POS_HOR=Integer.valueOf((S_WDT-WIDTH)/2);
	private final int POS_VER=Integer.valueOf((S_HGT-HEIGHT)/2);
	
	private JButton ok=new JButton("CALCOLA CODICE FISCALE");
	
	private JTextField jtf_surname=new JTextField();
	private JTextField jtf_name=new JTextField();
	private ButtonGroup chk_s=new ButtonGroup();
	private JRadioButton chk_f=new JRadioButton("F");
	private JRadioButton chk_m=new JRadioButton("M");
	
	private JComboBox jcb_d=new JComboBox();
	private JComboBox jcb_m=new JComboBox();
	private JComboBox jcb_y=new JComboBox();
	private JComboBox jcb_c=new JComboBox();
	
	public Wnd() throws IOException{
		setTitle("CODICE FISCALE");
		setBounds(POS_HOR,POS_VER,WIDTH,HEIGHT);
		setLayout(new BorderLayout());
		add(new JLabel(" "),BorderLayout.NORTH);
		add(new JLabel("    "),BorderLayout.EAST);
		add(new JLabel("    "),BorderLayout.WEST);
		add(okPanel(),BorderLayout.SOUTH);
		add(CentralPanel(),BorderLayout.CENTER);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private JPanel CentralPanel(){
		
		JLabel[] arrayL=new JLabel[5];
		
		JPanel p=new JPanel();
		p.setLayout(new GridLayout(6,2));
		
		for(int i=0;i<arrayL.length;i++)
		{
			switch(i)
			{
			case 0: arrayL[i]=new JLabel("COGNOME  ",JLabel.RIGHT); 
					p.add(arrayL[i]);
					p.add(jtf_surname);
					break;
			case 1: arrayL[i]=new JLabel("NOME  ",JLabel.RIGHT);
					p.add(arrayL[i]);
					p.add(jtf_name);
					break;
			case 2: arrayL[i]=new JLabel("SESSO  ",JLabel.RIGHT);
					p.add(arrayL[i]);
					p.add(checkSex());
					break;
			case 3: arrayL[i]=new JLabel("DATA DI NASCITA  ",JLabel.RIGHT);
					p.add(arrayL[i]);
					p.add(bornDate());
					break;
			case 4: arrayL[i]=new JLabel("COMUNE DI NASCITA  ",JLabel.RIGHT);
					p.add(arrayL[i]);
					p.add(jcb_c);
					break;
			default: System.exit(JFrame.EXIT_ON_CLOSE);
			}
		}

		return p;
		
	}

	@SuppressWarnings("unchecked")
	private JPanel bornDate() {
		
		JPanel p=new JPanel();
		p.setLayout(new GridLayout(1,3));
		
		for(int i=1;i<=31;i++)
			jcb_d.addItem(i+"");

		for(int i=1;i<=12;i++)
			jcb_m.addItem(i+"");

		for(int i=1900;i<=cal.get(Calendar.YEAR);i++)
			jcb_y.addItem(i+"");

		p.add(jcb_d);
		p.add(jcb_m);
		p.add(jcb_y);
		
		return p;
	}

	private JPanel checkSex() {
		JPanel p=new JPanel();
		p.setLayout(new GridLayout(1,2));
		chk_s.add(chk_m);
		chk_s.add(chk_f);
		p.add(chk_m);
		p.add(chk_f);
		return p;
	}
	
	@SuppressWarnings("unchecked")
	private void cityNames() throws IOException {
        for (Map.Entry<String, String> e : Utils.getCitiesCodes().entrySet()) {
        	jcb_c.addItem(e.getValue());
        }
	}
	
	private JPanel okPanel() throws IOException {
		JPanel p=new JPanel();
		cityNames();
		ActionListener al=new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				person=new Person();
				
				if(jtf_surname.getText().compareTo("")==0)
					new FinalWnd(f,"Inserire un cognome valido!");
				
				person.setSurname(jtf_surname.getText());
				
				if(jtf_name.getText().compareTo("")==0)
					new FinalWnd(f,"Inserire un nome valido!");

				person.setName(jtf_name.getText());
				person.setDay((String)jcb_d.getSelectedItem());
				person.setMonth((String)jcb_m.getSelectedItem());
				person.setYear((String)jcb_y.getSelectedItem());
				person.setBornCity((String)jcb_c.getSelectedItem());
				
				if(chk_f.isSelected() && !chk_m.isSelected())
					person.setSex("F");
				else if(!chk_f.isSelected() && chk_m.isSelected())
					person.setSex("M");
				else
					new FinalWnd(f,"Definire il sesso!");
				
				try {
					engine=new Engine(person);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				new FinalWnd(f,engine.getCode());
			}
		};
		ok.addActionListener(al);
		p.add(ok);
		return p;
	}
	
}
