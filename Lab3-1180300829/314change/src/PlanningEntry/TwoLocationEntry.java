package PlanningEntry;

import Location.*;

public interface TwoLocationEntry {
	
	/**
	 * �������վ���յ�վ��λ��
	 * @param from ���վ��λ��
	 * @param to �յ�վ��λ��
	 * @return �Ƿ����óɹ�
	 */
	public boolean setlocations(Location from,Location to);

	/**
	 * �������վ���յ�վ��λ��
	 * @param from ���վ��λ��
	 * @param to �յ�վ��λ��
	 * @return �Ƿ����óɹ�
	 */
	public boolean changelocations(Location from,Location to);
	
	/**
	 * �õ����վ��λ��
	 * @return ���վ
	 */
	public Location getfromlocation();
	
	/**
	 * �õ��յ�վ��λ��
	 * @return ���վ
	 */
	public Location gettolocation();
}
