package PlanningEntry;

import java.util.ArrayList;
import java.util.List;

import Location.*;

public class MultipleLacationEntryImpl implements MultipleLocationEntry {

	private List<Location> locations; //һ��λ��
	
	// mutability��
	// Abstraction function:
	// AF(mytrain)=һ��λ��
	// Representation invariant:
	// �����λ�ü��ϲ���Ϊ��
	// Safety from rep exposure:
	// ��locations����Ϊprivate
	
	/**
	 * ����һ��λ��
	 * @param locations �����õ�һ��λ��
	 * @return �Ƿ�ɹ�������һ��λ��
	 */
	@Override
	public boolean setlocations(List<Location> mylocations) {
		if(locations==null&&mylocations!=null) {
			this.locations=new ArrayList<>(mylocations);
			System.out.println("λ�����óɹ�");
			return true;
		}
		System.out.println("λ��ֻ������һ��");
		return false;
	}

	/**
	 * ������һ��λ��
	 * @param mylocations ���ĺ��λ��
	 * @return �Ƕ��ɹ����ĸ�λ��
	 */
	@Override
	public boolean changelocations(List<Location> mylocations) {
			System.out.println("λ�ò��ɸ���");
			return false;
	}

	/**
	 * �õ���һ��λ��
	 * @return ��һ���λ��
	 */
	@Override
	public List<Location> getlocations() {
		return this.locations;
	}
}
