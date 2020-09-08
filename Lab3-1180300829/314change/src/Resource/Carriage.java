package Resource;

public class Carriage {

	private final String carriagenumber;  //������
	private final String carriagetype;    //��������
	private final int carriageallseat;    //��Ա��
	private final String carriagbirth;    //�������
	
	// immutability��
	// Abstraction function:
    // AF(carriagenumber)=������
	// AF(carriagetype)=��������
	// AF(carriageallseat)=��Ա��
	// AF(carriagbirth)=�������
	// Representation invariant:
    // ��Ա��������
	// Safety from rep exposure:
	// ��carriagenumber,carriagetype,carriageallseat,carriagbirth����Ϊprivate final
	
	
	/**
	 * ��ʼ�����췽��
	 * @param a ������
	 * @param b ��������
	 * @param c ��Ա��
	 * @param d �������
	 */
	public Carriage(String a,String b,int c,String d) {
		this.carriagenumber=a;
		this.carriagetype=b;
		this.carriageallseat=c;
		this.carriagbirth=d;
	}
	
	/**
	 * ���س�����
	 * @return ������
	 */
	public String getcarriagenumber() {
		return carriagenumber;
	}
	
	/**
	 * ���س�������
	 * @return ��������
	 */
	public String getcarriagetype() {
		return carriagetype;
	}
	
	/**
	 * ���ض�Ա��
	 * @return ��Ա��
	 */
	public int getcarriageallseat() {
		return carriageallseat;
	}
	
	/**
	 * ���س���������
	 * @return ����������
	 */
	public String getcarriagbirth() {
		return carriagbirth;
	}

	/**
	 * ��дhashcode����
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((carriagbirth == null) ? 0 : carriagbirth.hashCode());
		result = prime * result + carriageallseat;
		result = prime * result + ((carriagenumber == null) ? 0 : carriagenumber.hashCode());
		result = prime * result + ((carriagetype == null) ? 0 : carriagetype.hashCode());
		return result;
	}

	/**
	 * ��дequals����
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Carriage other = (Carriage) obj;
		if (carriagbirth == null) {
			if (other.carriagbirth != null)
				return false;
		} else if (!carriagbirth.equals(other.carriagbirth))
			return false;
		if (carriageallseat != other.carriageallseat)
			return false;
		if (carriagenumber == null) {
			if (other.carriagenumber != null)
				return false;
		} else if (!carriagenumber.equals(other.carriagenumber))
			return false;
		if (carriagetype == null) {
			if (other.carriagetype != null)
				return false;
		} else if (!carriagetype.equals(other.carriagetype))
			return false;
		return true;
	}


}
