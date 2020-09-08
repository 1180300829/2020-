package PlanningEntry;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import Location.Location;
import Resource.Flight;
import Timeslot.Timeslot;

public class FlightEntry<Flight> extends CommonPlanningEntry implements FlightPlanningEntry<Flight>,Comparable<FlightEntry<Flight>>{

	private MultipleLacationEntryImpl a;
	private OneDistinguishResourceEntryImpl<Flight> b;
	private BlockableEntryImpl c;
	
	// mutability��
	// Abstraction function:
	// AF(a)=����λ��
	// AF(b)=һ���ɻ���Դ
	// AF(c)=����������ʱ���
	// Safety from rep exposure:
	// ��a,b,c����Ϊprivate

	/**
	 * ���췽��
	 * @param a ����λ�õ���
	 * @param b һ���ɻ���Դ����
	 * @param c ����������ʱ��Ե���
	 */
	public FlightEntry(MultipleLacationEntryImpl a,OneDistinguishResourceEntryImpl<Flight> b,BlockableEntryImpl c) {
		this.a=a;
		this.b=b;
		this.c=c;
	}
	

	/**
	 * ����һ��λ��
	 * @param locations �����õ�һ��λ��
	 * @return �Ƿ�ɹ�������һ��λ��
	 */
	@Override
	public boolean setlocations(List<Location> mylocations) {
		return a.setlocations(mylocations);
	}

	/**
	 * ������һ��λ��
	 * @param mylocations ���ĺ��λ��
	 * @return �Ƕ��ɹ����ĸ�λ��
	 */
	@Override
	public boolean changelocations(List<Location> mylocations) {
		return a.changelocations(mylocations);
	}
	
	/**
	 * �õ���һ��λ��
	 * @return ��һ���λ��
	 */
	@Override
	public List<Location> getlocations() {
		return a.getlocations();
	}


	/**
	 * ���÷ɻ���Դ
	 * @param a �����õķɻ���Դ
	 * @return �ɻ���Դ�Ƿ����óɹ�
	 */
	@Override
	public boolean setresource(Flight a) {
		return b.setresource(a);
	}

	/**
	 * �õ��÷ɻ���Դ
	 * @return �÷ɻ���Դ
	 */
	@Override
	public Flight getresource() {
		return b.getresource();
	}

	/**
	 * ���ĸ÷ɻ���Դ
	 * @param a ���ĺ����Դ
	 * @return �Ƿ���ĳɹ�
	 */
	@Override
	public boolean changeresource(Flight a) {
		return b.changeresource(a);
	}

	/**
	 * �ж��Ƿ������
	 * @return �Ƿ������
	 */
	@Override
	public boolean whetherblockable() {
		return c.whetherblockable();
	}

	/**
     * ������һ��ʱ���
     * @param alltime һ��ʱ��� 
     * @return �Ƿ�ɹ�������һ��ʱ���
     */
	@Override
	public boolean settimeslot(List<Timeslot> alltime) {
		return c.settimeslot(alltime);
	}

	/**
	 * �õ���һ��ʱ���
	 * @return ��һ��ʱ���
	 */
	@Override
	public List<Timeslot> gettimeslot() {
		return c.gettimeslot();
	}
	
	/**
	 * ��ĳ��վ���������
	 * @param toblocklocation ��������վ������
	 * @return ����վ��������վ���е�λ��
	 */
	public int trainblock(String toblocklocation) {
		int i;
		if(a.getlocations().size()<=2) {
			System.out.println("û���м�վ��ɹ�����\n");
			return -1;
		}
		else {
			for(i=0;i<a.getlocations().size();i++) {
				if(a.getlocations().get(i).getlocationname().equals(toblocklocation) ){
					break;
				}
			}
			if(i>0&&i<a.getlocations().size()-1) {
				Calendar nowtime = Calendar.getInstance();  //��ǰʱ��
				String kpr = (new SimpleDateFormat("yyyy-MM-dd HH:mm")).format(nowtime.getTime()); 
				System.out.println("��"+toblocklocation+"����������������ʱ��Ϊ"+kpr);	
				return i;
			}
			System.out.println("���������վ���յ�վ����\n");
			return -1;
		} 
	}

    /**
     * ��дcompareTo��������ɰ���ʱ�����ʼʱ�������Լƻ��������
     */
	@Override
	public int compareTo(FlightEntry<Flight> o) {
		if(c.gettimeslot().get(0).getbegintime().compareTo(o.gettimeslot().get(0).getbegintime())>0) {
			return 1;
		}
		else if(c.gettimeslot().get(0).getbegintime().compareTo(o.gettimeslot().get(0).getbegintime())==0) {
			return 0;
		}
		return -1;
	}
		

	
}
