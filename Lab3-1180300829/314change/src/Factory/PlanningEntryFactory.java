package Factory;

import PlanningEntry.BlockableEntryImpl;

import PlanningEntry.MultipleLacationEntryImpl;
import PlanningEntry.MultipleSortedResourceEntryImpl;
import PlanningEntry.NoBlockableEntryImpl;
import PlanningEntry.OneDistinguishResourceEntryImpl;
import PlanningEntry.OneLocationEntryImpl;
import PlanningEntry.PlanningEntry;
import PlanningEntry.TwoLocationEntryImpl;
import Resource.Carriage;
import Resource.Flight;
import Resource.Teacher;

public interface PlanningEntryFactory {

	/**
	 * ����FlightEntry����
	 * @param a ����λ�õ���
	 * @param b һ���ɻ���Դ����
	 * @param c ����������ʱ��Ե���
	 * @return ������FlightEntry����
	 */
	public PlanningEntry getFlightEntry(MultipleLacationEntryImpl a,OneDistinguishResourceEntryImpl<Flight> b,BlockableEntryImpl c);
	
	/**
	 * ����TrainEntry����
	 * @param a ���λ�õ���
	 * @param b ���������Դ����
	 * @param c ��������ʱ��Ե���
	 * @return ������TrainEntry����
	 */
	public PlanningEntry getTrainEntry(MultipleLacationEntryImpl a,MultipleSortedResourceEntryImpl<Carriage> b,BlockableEntryImpl c);
	
	/**
	 * ����CourseEntry����
	  * @param a һ��λ�õ���
	 * @param b һ����ʦ��Դ����
	 * @param c ����������ʱ��Ե���
	 * @return ������CourseEntry����
	 */
	public PlanningEntry getCourseEntry(OneLocationEntryImpl a,MultipleSortedResourceEntryImpl<Teacher> b,NoBlockableEntryImpl c);
}
