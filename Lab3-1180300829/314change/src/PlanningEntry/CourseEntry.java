package PlanningEntry;

import java.util.List;

import Location.Location;
import Resource.Teacher;
import Timeslot.Timeslot;

public class CourseEntry<Teacher> extends CommonPlanningEntry implements CoursePlanningEntry<Teacher>,Comparable<CourseEntry<Teacher>>{

	private OneLocationEntryImpl a;
	private MultipleSortedResourceEntry<Teacher> b;
	private NoBlockableEntryImpl c;
	
	// mutability��
	// Abstraction function:
	// AF(a)=һ��λ��
	// AF(b)=һ����ʦ��Դ
	// AF(c)=����������ʱ���
	// Safety from rep exposure:
	// ��a,b,c����Ϊprivate
	
	/**
	 * ���췽��
	 * @param a һ��λ�õ���
	 * @param b һ����ʦ��Դ����
	 * @param c ����������ʱ��Ե���
	 */
	public CourseEntry(OneLocationEntryImpl a,MultipleSortedResourceEntry<Teacher> b,NoBlockableEntryImpl c) {
		this.a=a;
		this.b=b;
		this.c=c;
	}

	/**
	 * ���ø�λ��
	 * @param only ��λ��
	 * @return ��λ���Ƿ����óɹ�
	 */
	@Override
	public boolean setlocations(Location only) {
		return a.setlocations(only);
	}

	/**
	 * ���ĸ�λ��
	 * @param only ���ĺ��λ��
	 * @return λ���Ƿ���ĳɹ�
	 */
	@Override
	public boolean changelocations(Location only) {
		return a.changelocations(only);
	}

	/**
	 * ɾ����λ��
	 * @param waittodelete ��ɾ����λ��
	 * @return λ���Ƿ�ɾ���ɹ�
	 */
	@Override
	public boolean deletelocations(Location waittodelete) {
		return a.deletelocations(waittodelete);
	}
	
	/**
	 * �õ���λ��
	 * @return ��λ��
	 */
	@Override
	public Location getlocations() {
		return a.getlocations();
	}

	/**
	 * ���øý�ʦ��Դ
	 * @param train ��ʦ��Դ����
	 * @return �Ƿ�ɹ����øÿγ���Դ
	 */
	@Override
	public boolean setresource(List<Teacher> train) {
		return b.setresource(train);
	}

	/**
	 * �õ��ý�ʦ��Դ
	 * @return �ý�ʦ��Դ
	 */
	@Override
	public List<Teacher> getresource() {
		return b.getresource();
	}

	/**
	 * ����ĳ��ʦ��Դ
	 * @param precarriage �����ĵĽ�ʦ��Դ
	 * @param aftercarriage ���ĺ�Ľ�ʦ��Դ
	 * @return �Ƿ�ɹ����Ľ�ʦ��Դ
	 */
	@Override
	public boolean changeresource(Teacher mycarriage,Teacher aftercarriage) {
		return b.changeresource(mycarriage,aftercarriage);
	}

	/**
	 * ���ʦ��Դ�������һ�ڽ�ʦ
	 * @param mycarriage ������Ľ�ʦ
	 * @param temp �����ʦ��λ��
	 * @return �Ƿ�ɹ������ʦ
	 */
	@Override
	public boolean addresource(Teacher mycarriage,int temp) {
		return b.addresource(mycarriage,temp);
	}

	/**
	 * ɾ����ʦ��Դ�е�ĳ��ʦ
	 * @param mycarriage ��ɾ���Ľ�ʦ
	 * @return �Ƿ�ɹ�ɾ����ʦ
	 */
	@Override
	public boolean deleteresource(Teacher mycarriage) {
		return b.deleteresource(mycarriage);
	}
	
	

	/**
	 * �жϸ����Ƿ������
	 * @return �����Ƿ������
	 */
	@Override
	public boolean whetherblockable() {
		return c.whetherblockable();
	}

	/**
	 * ���ø�ʱ���
	 * @param mytime ʱ���
	 * @return �Ƿ�ɹ����ø�ʱ���
	 */
	@Override
	public boolean settimeslot(Timeslot mytime) {
		return c.settimeslot(mytime);
	}

	/**
	 * �õ���ʱ���
	 * @return ��ʱ���
	 */
	@Override
	public Timeslot gettimeslot() {
		return c.gettimeslot();
	}

    /**
     * ��дcompareTo��������ɰ���ʱ�����ʼʱ�������Լƻ��������
     */
	@Override
	public int compareTo(CourseEntry<Teacher> o) {
		if(c.gettimeslot().getbegintime().compareTo(o.gettimeslot().getbegintime())>0) {
			return 1;
		}
		else if(c.gettimeslot().getbegintime().compareTo(o.gettimeslot().getbegintime())==0) {
			return 0;
		}
		return -1;
	}
}
