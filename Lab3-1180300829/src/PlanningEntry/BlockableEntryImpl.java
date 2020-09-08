package PlanningEntry;

import java.util.ArrayList;
import java.util.List;

import Timeslot.Timeslot;

public class BlockableEntryImpl implements BlockableEntry {

	private List<Timeslot> alltime; //һ��ʱ���
	
	// mutability��
	// Abstraction function:
	// AF(mytrain)=һ��ʱ���
	// Representation invariant:
	// �����ʱ��Լ��ϲ���Ϊ��
	// Safety from rep exposure:
	// ��alltime����Ϊprivate
	
	/**
	 * �ж��Ƿ������
	 * @return �Ƿ������
	 */
	@Override
	public boolean whetherblockable() {
		System.out.println("������");
		return true;
	}

	 /**
     * ������һ��ʱ���
     * @param alltime һ��ʱ��� 
     * @return �Ƿ�ɹ�������һ��ʱ���
     */
	@Override
	public boolean settimeslot(List<Timeslot> time) {
		if(alltime==null) {
			alltime=new ArrayList<>();
			this.alltime=time;
			System.out.println("һ��ʱ������óɹ�");
			return true;
		}
		System.out.println("ʱ��ֻ������һ��");
		return false;
	}

	/**
	 * �õ���һ��ʱ���
	 * @return ��һ��ʱ���
	 */
	@Override
	public List<Timeslot> gettimeslot() {
		return alltime;
	}
}
