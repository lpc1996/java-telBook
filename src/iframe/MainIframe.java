package iframe;



import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import data.Dao;
import data.TableData;
import javafx.scene.layout.Border;
import model.Record;

public class MainIframe extends JFrame{
	
	private JToolBar jToolBar;
	private JPanel mainPanel;
	private JPanel bottomPanel;
	private JButton btn1;
	private JButton btn2;
	private JButton btn3;
	private JButton btn4;
	private JTextField idText;
	private JTextField nameText;
	private JTextField telText;
	
	public MainIframe() {
		super();
		Toolkit tool =Toolkit.getDefaultToolkit();
		Dimension screen = tool.getScreenSize();
		this.setSize((int)(screen.getWidth() * 0.5),(int)( screen.getHeight()*0.5));
		this.setResizable(false);
		
		Image icon = tool.getImage("D:/workspace/ͼ���ز�/ͨѶ¼.png");
		setIconImage( icon);
		
		setLocation((int)(screen.getWidth() -getWidth())/2, (int)(screen.getHeight()-getHeight())/2);
		
		createToolBar();
		getContentPane().add(jToolBar,BorderLayout.NORTH);
		mainPanel = new JPanel();
		mainPanel.setMaximumSize(new Dimension(getWidth(), getHeight()));
		mainPanel.setPreferredSize(new Dimension(300, 100));
		bottomPanel = new JPanel();
		bottomPanel.setSize(getWidth(),30);
		bottomPanel.setLayout(null);
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private void createToolBar() {
		jToolBar = new JToolBar();
//		jToolBar.setSize(getWidth(), 20);
		jToolBar.setPreferredSize(new Dimension(getWidth(), 35));
		jToolBar.setFloatable(false);
		jToolBar.setBorder(new BevelBorder(BevelBorder.RAISED));
		JButton printBtn = new JButton("��ӡ");
		printBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				createPrintPanel();
			}
		});
		JButton addBtn = new JButton("���");
		addBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO �Զ����ɵķ������
				createAddPanel();
			}
		});
		JButton changeBtn = new JButton("�޸�");
		changeBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				createChangePanel();
			}
		});
		JButton deleteBtn = new JButton("ɾ��");
		deleteBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				createDeletePanel();
			}
		});
		JButton searchBtn = new JButton("��ѯ");
		searchBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				createSearchPanel();
			}
		});
		jToolBar.add(printBtn);
		jToolBar.add(addBtn);
		jToolBar.add(changeBtn);
		jToolBar.add(deleteBtn);
		jToolBar.add(searchBtn);
	}
	
	private void createPrintPanel() {
		mainPanel.removeAll();
		bottomPanel.removeAll();
		mainPanel.setLayout(new BorderLayout());
		JTable table = new JTable(new TableData());
		JScrollPane js = new JScrollPane(table);
		js.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		mainPanel.add(js);
		mainPanel.revalidate();
		
		btn1 = new JButton("�ر�");
		btn1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				mainPanel.removeAll();
				bottomPanel.removeAll();
				mainPanel.updateUI();
				bottomPanel.updateUI();
			}
		});
		bottomPanel.setPreferredSize(new Dimension(getWidth(),30));
		btn1.setBounds(new Rectangle((int)(getWidth()-80)/2, 0, 80, 30));
		bottomPanel.add(btn1);
		mainPanel.updateUI();
		bottomPanel.updateUI();
	}
	
	private void createAddPanel() {
		mainPanel.removeAll();
		bottomPanel.removeAll();
		mainPanel.setLayout(null);
		JLabel idLab = new JLabel("���:");
		idText = new JTextField(30);
		JLabel nameLab = new JLabel("����:");
		nameText = new JTextField(30);
		JLabel telLab = new JLabel("�绰:");
		telText = new JTextField(30);
		idLab.setBounds(new Rectangle(0, 0, 40, 30));
		mainPanel.add(idLab);
		idText.setBounds(new Rectangle(45, 0, getWidth()-40, 30));
		mainPanel.add(idText);
		nameLab.setBounds(new Rectangle(0, 35, 40, 30));
		mainPanel.add(nameLab);
		nameText.setBounds(new Rectangle(45, 35, getWidth()-40,30));
		mainPanel.add(nameText);
		telLab.setBounds(new Rectangle(0,70,40,30));
		mainPanel.add(telLab);
		telText.setBounds(new Rectangle(45, 70, getWidth()-40,30));
		mainPanel.add(telText);
		
		Record rec = new Record();
		
		btn1 = new JButton("���");
		btn1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO �Զ����ɵķ������
				if(idText.getText().equals("") || nameText.getText().equals("") || telText.getText().equals("")) {
					errJOption("�������������ݣ�");
					idText.setText("");
					nameText.setText("");
					telText.setText("");
				}else {
					String str = "\\d+";
					if(!idText.getText().matches(str)) {
						errJOption("��ű��������֣�");
						idText.setText("");
						nameText.setText("");
						telText.setText("");
						return;
					}
					String telstr = "\\d{11}";
					if(!telText.getText().matches(telstr)) {
						errJOption("��������ȷ�ĵ绰���룡");
					}
					rec.setId(Integer.parseInt(idText.getText()));
					rec.setName(nameText.getText());
					rec.setTel(telText.getText());
					int i = addData(rec);  
					if(i == -1) {
						errJOption("���ʧ�ܣ�");
					}else if( i == 0) {
						idText.setText("");
						nameText.setText("");
						telText.setText("");
					}else {
						errJOption("��ӳɹ���");
					}
				}
								
			}
		});
		btn2 = new JButton("�ر�");
		btn2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				mainPanel.removeAll();
				bottomPanel.removeAll();
				mainPanel.updateUI();
				bottomPanel.updateUI();
			}
		});
		bottomPanel.setPreferredSize(new Dimension(getWidth(),30));
		btn1.setBounds(new Rectangle(getWidth()/2-85, 0, 80, 30));
		bottomPanel.add(btn1);
		btn2.setBounds(new Rectangle(getWidth()/2+5, 0, 80, 30));
		bottomPanel.add(btn2);
		mainPanel.updateUI();
		bottomPanel.updateUI();
	}
	
	private int addData(Record rec) {
		Dao dao = new Dao();
		int i = -1;
		if(rec.getId() == 0 || rec.getName().equals("") || rec.getTel().equals("")) {
			errJOption("�������������ݣ�");
		}else {
			i = dao.isInData(rec);
//			errJOption(i+"");
			if( i == 1) {
				errJOption("���"+rec.getId()+"�Ѵ���");
				return 0;
			}
			i = dao.insertData(rec);
		}
		
		return i;
	}
	private void errJOption(String str) {
		JOptionPane.showMessageDialog(getContentPane(), str);
	}
	
	private void createChangePanel() {
		mainPanel.removeAll();
		bottomPanel.removeAll();
		mainPanel.setLayout(null);
		mainPanel.setPreferredSize(new Dimension(getWidth(),(int)(getHeight()-30)));
		JTable table = new JTable(new TableData());
		JScrollPane js = new JScrollPane(table);
		js.setBounds(new Rectangle(0, 0, mainPanel.getWidth()-1,(int)(mainPanel.getHeight()*0.5)));
		mainPanel.add(js);
		JPanel inputPanel = new JPanel();
		JLabel idLab = new JLabel("��ţ�");
		idText = new JTextField();
		JLabel nameLab = new JLabel("������");
		nameText = new JTextField();
		JLabel telLab = new JLabel("�绰��");
		telText = new JTextField();
		inputPanel.setLayout(null);
		idLab.setBounds(new Rectangle(0, 0, 50, 30));
		inputPanel.add(idLab);
		idText.setBounds(new Rectangle(60, 0, 200, 30));
		inputPanel.add(idText);
		nameLab.setBounds(new Rectangle(0, 40, 50, 30));
		inputPanel.add(nameLab);
		nameText.setBounds(new Rectangle(60, 40, 200, 30));
		inputPanel.add(nameText);
		telLab.setBounds(new Rectangle(0, 80, 50, 30));
		inputPanel.add(telLab);
		telText.setBounds(new Rectangle(60, 80, 200, 30));
		inputPanel.add(telText);
		inputPanel.setBounds(new Rectangle(0,(int)(mainPanel.getHeight()*0.5),mainPanel.getWidth(),(int)(mainPanel.getHeight()*0.5)));
		mainPanel.add(inputPanel);
		Record rec = new Record();
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO �Զ����ɵķ������
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO �Զ����ɵķ������
				int index = table.getSelectedRow();
				int column = table.getSelectedColumn();
				for(int i = 0 ; i < 3; i++) {
					if(i == 0) {
						rec.setId(Integer.parseInt((String)table.getValueAt(index, i)));
					}else if(i == 1) {
						rec.setName((String)table.getValueAt(index, i));
					}else if(i == 2) {
						rec.setTel((String)table.getValueAt(index, i));
					}
				}
				idText.setText(rec.getId()+"");
				nameText.setText(rec.getName());
				telText.setText(rec.getTel());
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO �Զ����ɵķ������
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO �Զ����ɵķ������
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO �Զ����ɵķ������
				
			}
		});
		
		bottomPanel.setPreferredSize(new Dimension(getWidth(),30));
		btn1 = new JButton("�޸�");
		btn1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {				
				// TODO �Զ����ɵķ������
				Record data = new Record();
				data.setId(Integer.parseInt(idText.getText()));
				data.setName(nameText.getText());
				data.setTel(telText.getText());
				Dao dao = new Dao();
				int i = -1;
				try {
					i = dao.upData(rec.getId(),data);
				} catch (Exception e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
					errJOption("�����ˣ�");
					return;
				}
				if(i == -1) {
					errJOption("�޸�ʧ�ܣ�");
				}else if(i == 0) {
					idText.setText("");
					nameText.setText("");
					telText.setText("");
				}else if(i > 0) {
					errJOption("�޸ĳɹ���");
				}
			}
		});
		btn1.setBounds(new Rectangle((int)(getWidth()/2-85),0, 80, 30));
		bottomPanel.add(btn1);
		btn2 = new JButton("�ر�");
		btn2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO �Զ����ɵķ������
				mainPanel.removeAll();
				bottomPanel.removeAll();
				mainPanel.updateUI();
				bottomPanel.updateUI();
			}
		});
		btn2.setBounds(new Rectangle((int)(getWidth()/2),0, 80, 30));
		bottomPanel.add(btn2);
		mainPanel.updateUI();
		bottomPanel.updateUI();
	}
	
	private void createDeletePanel() {
		mainPanel.removeAll();
		bottomPanel.removeAll();
		mainPanel.setLayout(null);
		mainPanel.setPreferredSize(new Dimension(getWidth(),(int)(getHeight()-30)));
		JTable table = new JTable(new TableData());
		JScrollPane js = new JScrollPane(table);
		js.setBounds(new Rectangle(0, 0, mainPanel.getWidth()-1,(int)(mainPanel.getHeight()*0.5)));
		mainPanel.add(js);
		JLabel dataDescribeLab = new JLabel();
		dataDescribeLab.setBounds(new Rectangle(0, (int)(mainPanel.getHeight()*0.5+1),mainPanel.getWidth()-1,
				(int)(mainPanel.getHeight()*0.5-1)));
		mainPanel.add(dataDescribeLab);
		Record rec = new Record();
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO �Զ����ɵķ������
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO �Զ����ɵķ������
				int index = table.getSelectedRow();
				for(int i = 0; i < 3 ; i++) {
					if(i == 0 ) {
						rec.setId(Integer.parseInt((String)table.getValueAt(index, i)));
					}else if(i == 1){
						rec.setName((String)table.getValueAt(index, i));
					}else if(i ==2) {
						rec.setTel((String)table.getValueAt(index, i));
					}
				}
				dataDescribeLab.setText("Ҫɾ���������ǣ�"+rec.getId()+"   "+rec.getName()+"   "+rec.getTel());
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO �Զ����ɵķ������
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO �Զ����ɵķ������
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO �Զ����ɵķ������
				
			}
		});
		
		bottomPanel.setPreferredSize(new Dimension(getWidth(),30));
		btn1 = new JButton("ɾ��");
		btn1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO �Զ����ɵķ������
				Dao dao = new Dao();
				int i = dao.deleteData(rec);
				if( i == -1) {
					errJOption("ɾ��ʧ��");
				}else {
					errJOption("ɾ���ɹ�");;
				}
			}
		});
		btn1.setBounds(new Rectangle((int)(getWidth()/2-85),0, 80, 30));
		bottomPanel.add(btn1);
		btn2 = new JButton("�ر�");
		btn2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				mainPanel.removeAll();
				bottomPanel.removeAll();
				mainPanel.updateUI();
				bottomPanel.updateUI();
			}
		});
		btn2.setBounds(new Rectangle((int)(getWidth()/2+5),0, 80, 30));
		bottomPanel.add(btn2);
		mainPanel.updateUI();
		bottomPanel.updateUI();
	}
	
	private void createSearchPanel() {
		mainPanel.removeAll();
		bottomPanel.removeAll();
		mainPanel.setLayout(null);
		mainPanel.setPreferredSize(new Dimension(getWidth(),(int)(getHeight()-30)));
		JTable table = new JTable(new TableData());
		JScrollPane js = new JScrollPane(table);
		js.setBounds(new Rectangle(0, 0, mainPanel.getWidth()-1,(int)(mainPanel.getHeight()*0.5)));
		mainPanel.add(js);
		nameText = new JTextField();
		nameText.setBounds(new Rectangle((int)((mainPanel.getWidth() - 200)/2 ), (int)(mainPanel.getHeight()*0.5)+10, 200, 30));
		mainPanel.add(nameText);
		JLabel dataDescribeLab = new JLabel();
		dataDescribeLab.setBounds(new Rectangle(0, (int)(mainPanel.getHeight()*0.5)+50, mainPanel.getWidth(), 30));
		mainPanel.add(dataDescribeLab);
		
		bottomPanel.setPreferredSize(new Dimension(getWidth(),30));
		btn1 = new JButton("����");
//		Record rec = new Record();
		btn1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO �Զ����ɵķ������
				if(nameText.getText().equals("")) {
					errJOption("û�����룡");
				}else {
					String str = new String(nameText.getText());
					Dao dao = new Dao();
					Record rec = dao.searchData(str);
					if(rec.getId() != 0) {
						dataDescribeLab.setText("�ҵ��ˣ�"+rec.getId()+"   "+rec.getName()+"   "+rec.getTel());
					}else {
						dataDescribeLab.setText("�Ҳ�����");
					}
				}
			}
		});
		btn1.setBounds(new Rectangle((int)(getWidth()/2-85),0, 80, 30));
		bottomPanel.add(btn1);
		btn2 = new JButton("�ر�");
		btn2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				mainPanel.removeAll();
				bottomPanel.removeAll();
				mainPanel.updateUI();
				bottomPanel.updateUI();
			}
		});
		btn2.setBounds(new Rectangle((int)(getWidth()/2+5),0, 80, 30));
		bottomPanel.add(btn2);
		mainPanel.updateUI();
		bottomPanel.updateUI();
	}
}
