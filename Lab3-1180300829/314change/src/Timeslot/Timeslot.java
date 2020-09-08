package Timeslot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Timeslot {

	private final Calendar begintime;  //һ��ʱ��Եĳ�ʼʱ��
	private final Calendar endtime;    //һ��ʱ��Ե���ֹʱ��
	
    //immutability��
	// Abstraction function:
    // AF(begintime)=һ��ʱ��Եĳ�ʼʱ��
	// AF(endtime)=һ��ʱ��Ե���ֹʱ��
	// Representation invariant:
    // ʱ���ʽ������yyyy-MM-dd HH:mm
    // Safety from rep exposure:
    // ��begintime,endtime����Ϊprivate final
	
	/**
	 * ���췽��
	 * @param begin ��ʼʱ���ַ���
	 * @param end   ��ֹʱ���ַ���
	 * @throws ParseException ʱ���ʽ��Ϊyyyy-MM-dd HH:mm
	 */
	public Timeslot(String begin,String end) throws ParseException{
		this.begintime= Calendar.getInstance(); 
		begintime.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(begin));
		this.endtime= Calendar.getInstance(); 
		endtime.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(end));
		
	}
	
	/**
	 * ���س�ʼʱ��
	 * @return ��ʼʱ��
	 */
	public Calendar getbegintime() {
		return begintime;
	}
	
	/**
	 * ������ֹʱ��
	 * @return ��ֹʱ��
	 */
	public Calendar getendtime() {
		return endtime;
	}

	/**
	 * ��дhashCode����
	 */
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((begintime == null) ? 0 : begintime.hashCode());
		result = prime * result + ((endtime == null) ? 0 : endtime.hashCode());
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
		Timeslot other = (Timeslot) obj;
		if (begintime == null) {
			if (other.begintime != null)
				return false;
		} else if (!begintime.equals(other.begintime))
			return false;
		if (endtime == null) {
			if (other.endtime != null)
				return false;
		} else if (!endtime.equals(other.endtime))
			return false;
		return true;
	}

	
}
