package PlanningEntry;

import java.util.List;

import Timeslot.Timeslot;

public interface BlockableEntry {

	/**
	 * �ж��Ƿ������
	 * @return �Ƿ������
	 */
    public boolean whetherblockable();
	
    /**
     * ������һ��ʱ���
     * @param alltime һ��ʱ��� 
     * @return �Ƿ�ɹ�������һ��ʱ���
     */
	public boolean settimeslot(List<Timeslot> alltime);
	
	/**
	 * �õ���һ��ʱ���
	 * @return ��һ��ʱ���
	 */
	public List<Timeslot> gettimeslot();
	
	
}
