package model;

public class Record {
	private int id;
	private String name;
	private String tel;
	/**
	 * @return id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id Ҫ���õ� id
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name Ҫ���õ� name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return tel
	 */
	public String getTel() {
		return tel;
	}
	/**
	 * @param tel Ҫ���õ� tel
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}
	/**
	 * @param id
	 * @param name
	 * @param tel
	 */
	public Record(int id, String name, String tel) {
		super();
		this.id = id;
		this.name = name;
		this.tel = tel;
	}
	/**
	 * 
	 */
	public Record() {
		super();
	}
	
	
}
