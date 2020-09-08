package PlanningEntry;

import Timeslot.Timeslot;

public class NoBlockableEntryImpl implements NoBlockableEntry {

	private Timeslot mytime;
	
	// mutability��
	// Abstraction function:
	// AF(mytime)=��ʱ���
	// Representation invariant:
	// �����ʱ��Բ���Ϊ��
	// Safety from rep exposure:
	// ��mytime����Ϊprivate
	
	/**
	 * �жϸ����Ƿ������
	 * @return �����Ƿ������
	 */
	@Override
	public boolean whetherblockable() {
		System.out.println("��������");
		return false;
	}

	/**
	 * ���ø�ʱ���
	 * @param mytime ʱ���
	 * @return �Ƿ�ɹ����ø�ʱ���
	 */
	@Override
	public boolean settimeslot(Timeslot time) {
		if(mytime==null){
			this.mytime=time;
			System.out.println("ʱ�����óɹ�");
			return true;
		}
		System.out.println("ʱ��ֻ������һ��");
		return false;
	}

	/**
	 * �õ���ʱ���
	 * @return ��ʱ���
	 */
	@Override
	public Timeslot gettimeslot() {
		return mytime;
	}

}
