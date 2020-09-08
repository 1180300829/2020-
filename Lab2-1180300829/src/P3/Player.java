package P3;

public class Player {
	private String name; //��������
	private Integer myturn; //���ֳ���˳��

	// Abstraction function:
    // AF(name)=��������
	// AF(myturn)=���ֳ���˳��
    // Representation invariant:
    // ���������������ַ���������˳�������0����1
    // Safety from rep exposure:
    // ��name,myturn����Ϊprivate
    // ����name,myturn��inmutable�������ڷ���ʱ����Ҫ����defensive copies
	
	/**
	 * �������ֳ���˳��
	 * @param turn ���ֳ���˳��
	 */
	public void setmyturn(Integer turn) {
		this.myturn = turn;
	}

	/**
	 * ������������
	 * @param playerName ��������
	 */
	public void setname(String Name) {
		this.name = Name;
	}
	
	/**
	 * �������ֳ���˳��
	 * @return ���ֳ���˳��
	 */
	public Integer getmyturn() {
		return myturn;
	}
	
	/**
	 * ������������
	 * @return ��������
	 */
	public String getname() {
		return name;
	}

	
}
