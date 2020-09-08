package Timeslot;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Test;

import Resource.Teacher;

public class TimeslotTest {

	/* Testing strategy
	 * ����getbegintime����
     * ������ʼʱ��ķ���ֵ����
     */
	@Test
	public void getbegintimetest() throws ParseException {
		Timeslot temp=new Timeslot("2020-01-01 15:45","2020-01-01 17:30");
		Calendar begintime= Calendar.getInstance(); 
		begintime.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2020-01-01 15:45"));
		assertEquals(begintime,temp.getbegintime());
	}
	
	/* Testing strategy
	 * ����getendtime����
     * ���Խ���ʱ��ķ���ֵ����
     */
	@Test
	public void getendtimetest() throws ParseException {
		Timeslot temp=new Timeslot("2020-01-01 15:45","2020-01-01 17:30");
		Calendar endtime= Calendar.getInstance(); 
		endtime.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2020-01-01 17:30"));
		assertEquals(endtime,temp.getendtime());
	}
	
	
	/* Testing strategy
	 * ����hashcode����
     * ����������ͬ��ʱ����hashcode�Ƿ���ͬ����
     */
	@Test
	public void hashcodetest() throws ParseException {
		Timeslot temp=new Timeslot("2020-01-01 15:45","2020-01-01 17:30");
		Timeslot temp1=new Timeslot("2020-01-01 15:45","2020-01-01 17:30");
		assertEquals(temp.hashCode(),temp1.hashCode());
	}
	
	/* Testing strategy
	 * ����equals����
     * ��������ʱ���Ƿ���ͬ���ֵȼ��ࣺʱ����ͬ��ʱ�䲻ͬ
     */
	@Test
	public void equalstest() throws ParseException {
		Timeslot temp=new Timeslot("2020-01-01 15:45","2020-01-01 17:30");
		Timeslot temp1=new Timeslot("2020-01-01 12:45","2020-01-01 17:30");
		Timeslot temp2=new Timeslot("2020-01-01 15:45","2020-01-01 17:30");
		assertEquals(false,temp.equals(temp1));
		assertEquals(true,temp.equals(temp2));
	}

}
