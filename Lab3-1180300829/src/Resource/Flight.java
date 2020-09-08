package Resource;

public class Flight {

	private final String flightnumber;  //�ɻ����
	private final String flighttype;    //�ɻ����ͺ�
	private final int flightallseat;    //��λ��
	private final double flightage;     //����
	
	    // immutability��
		// Abstraction function:
	    // AF(flightnumber)=�ɻ����
		// AF(flighttype)=�ɻ����ͺ�
		// AF(flightallseat)=��λ��
		// AF(flightage)=����
		// Representation invariant:
	    // ������С��
		// Safety from rep exposure:
		// ��flightnumber,flighttype,flightallseat,flightage����Ϊprivate final
	
	/**
	 * ��ʼ�����췽��
	 * @param a �ɻ����
	 * @param b �ɻ����ͺ�
	 * @param c ��λ��
	 * @param d ����
	 */
	public Flight(String a,String b,int c,double d) {
		this.flightnumber=a;
		this.flighttype=b;
		this.flightallseat=c;
		this.flightage=d;
	}
	
	/**
	 * ���طɻ����
	 * @return �ɻ����
	 */
	public String getflightnumber() {
		return flightnumber;
	}
	
	/**
	 * ���طɻ����ͺ�
	 * @return �ɻ����ͺ�
	 */
	public String getflighttype() {
		return flighttype;
	}
	
	/**
	 * ���طɻ���λ��
	 * @return �ɻ���λ��
	 */
	public int getflightallseat() {
		return flightallseat;
	}
	
	/**
	 * ���طɻ�����
	 * @return �ɻ�����
	 */
	public double getflightage() {
		return flightage;
	}

	/**
	 * ��дhashcode����
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(flightage);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + flightallseat;
		result = prime * result + ((flightnumber == null) ? 0 : flightnumber.hashCode());
		result = prime * result + ((flighttype == null) ? 0 : flighttype.hashCode());
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
		Flight other = (Flight) obj;
		if (Double.doubleToLongBits(flightage) != Double.doubleToLongBits(other.flightage))
			return false;
		if (flightallseat != other.flightallseat)
			return false;
		if (flightnumber == null) {
			if (other.flightnumber != null)
				return false;
		} else if (!flightnumber.equals(other.flightnumber))
			return false;
		if (flighttype == null) {
			if (other.flighttype != null)
				return false;
		} else if (!flighttype.equals(other.flighttype))
			return false;
		return true;
	}

}
