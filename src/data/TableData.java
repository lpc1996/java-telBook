package data;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import model.Record;

public class TableData implements TableModel {
	private String[] title = {"���","����","�绰"};
	private Record data;
	private int Rows;
	public TableData() {
		// TODO �Զ����ɵĹ��캯�����
		Dao dao = new Dao();
		Rows = dao.getRows();
	}

	@Override
	public void addTableModelListener(TableModelListener arg0) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public Class<?> getColumnClass(int arg0) {
		// TODO �Զ����ɵķ������
		return String.class;
	}

	@Override
	public int getColumnCount() {
		// TODO �Զ����ɵķ������
		return title.length;
	}

	@Override
	public String getColumnName(int arg0) {
		// TODO �Զ����ɵķ������
		return title[arg0];
	}

	@Override
	public int getRowCount() {
		// TODO �Զ����ɵķ������
		return Rows;
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO �Զ����ɵķ������
		String thisValue = new String();
		Dao dao = new Dao();
		data = dao.getValue(arg0);
		if(arg1 == 0) {
			thisValue = data.getId()+"";
		}
		if(arg1 == 1) {
			thisValue = data.getName();
		}
		if(arg1 == 2) {
			thisValue = data.getTel();
		}
		return thisValue;
	}

	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		// TODO �Զ����ɵķ������
		return false;
	}

	@Override
	public void removeTableModelListener(TableModelListener arg0) {
		// TODO �Զ����ɵķ������

	}

	@Override
	public void setValueAt(Object arg0, int arg1, int arg2) {
		// TODO �Զ����ɵķ������
		
	}

}
