package PlanningEntry;

import Location.*;

public class TwoLocationEntryImpl implements TwoLocationEntry{

	private Location fromlocation;  //���վ
	private Location tolocation;   //�յ�վ
	
	    // mutability��
		// Abstraction function:
	    // AF(fromlocation)=���վ
		// AF(tolocation)=�յ�վ
		// Representation invariant:
	    // ֻ������һ�����վ���յ�վ
	    // Safety from rep exposure:
	    // ��fromlocation,tolocation����Ϊprivate
	
	/**
	 * �������վ���յ�վ��λ��
	 * @param from ���վ��λ��
	 * @param to �յ�վ��λ��
	 * @return �Ƿ����óɹ�
	 */
	@Override
	public boolean setlocations(Location from, Location to) {
		if(fromlocation==null&&tolocation==null) {
			this.fromlocation=from;
			this.tolocation=to;
			System.out.println("λ�����óɹ�");
			return true;
		}
		System.out.println("λ��ֻ������һ��");
		return false;
	}

	/**
	 * �������վ���յ�վ��λ��
	 * @param from ���վ��λ��
	 * @param to �յ�վ��λ��
	 * @return �Ƿ����óɹ�
	 */
	@Override
	public boolean changelocations(Location from, Location to) {
			System.out.println("λ�ò��ɸ���");
			return false;
	}

	/**
	 * �õ����վ��λ��
	 * @return ���վ
	 */
	@Override
	public Location getfromlocation() {
		return fromlocation;
	}

	/**
	 * �õ��յ�վ��λ��
	 * @return ���վ
	 */
	@Override
	public Location gettolocation() {
		return tolocation;
	}
	
	

}
