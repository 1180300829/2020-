package PlanningEntry;

import java.util.List;
import Location.*;

public interface MultipleLocationEntry {

	/**
	 * ����һ��λ��
	 * @param locations �����õ�һ��λ��
	 * @return �Ƿ�ɹ�������һ��λ��
	 */
	public boolean setlocations(List<Location> locations);
	
	/**
	 * ������һ��λ��
	 * @param mylocations ���ĺ��λ��
	 * @return �Ƕ��ɹ����ĸ�λ��
	 */
	public boolean changelocations(List<Location> mylocations);
	
	/**
	 * �õ���һ��λ��
	 * @return ��һ���λ��
	 */
	public List<Location> getlocations();
	
}
