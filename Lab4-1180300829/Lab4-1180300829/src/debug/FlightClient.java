package debug;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;


/**
 * Note that this class may use the other two class: Flight and Plane.
 * 
 * Debug and fix errors. DON'T change the initial logic of the code.
 *
 */
public class FlightClient {
	
	/**
	 * Given a list of flights and a list of planes, suppose each flight has not yet been
	 * allocated a plane to, this method tries to allocate a plane to each flight and ensures that
	 * there're no any time conflicts between all the allocations. 
	 * For example:
	 *  Flight 1 (2020-01-01 8:00-10:00) and Flight 2 (2020-01-01 9:50-10:40) are all allocated 
	 *  the same plane B0001, then there's conflict because from 9:50 to 10:00 the plane B0001
	 *  cannot serve for the two flights simultaneously.
	 *  
	 * @param planes a list of planes
	 * @param flights a list of flights each of which has no plane allocated
	 * @return false if there's no allocation solution that could avoid any conflicts
	 */
	
	public boolean planeAllocation(List<Plane> planes, List<Flight> flights) {
		//��ǰ���������м��
		boolean result=true;            //��planes���м�飬ȷ�����м�����û���ظ�Plane
		for(int i=0;i<planes.size()-1;i++) {
			for(int j=i+1;j<planes.size();j++) {
				if(planes.get(i).equals(planes.get(j))) {
					result=false;
					break;
				}
			}
		}  
		assert result==true;
		
		for(int m=0;m<flights.size();m++) { //��flights���м�飬ȷ����û�з���ɻ�
			assert flights.get(m).getPlane()==null;
		}
		
		boolean bFeasible = true;
		Random r = new Random();
		
		Collections.sort(flights,new Comparator<Flight>() {   //ԭ��û��ʵ��Comparator�����compare�������޸ģ����ӹ�����ʼʱ���compare����ʵ�� 
			@Override
			public int compare(Flight o1, Flight o2) {
				if(o1.getDepartTime().compareTo(o2.getDepartTime())>0) {
					return 1;
				}
				else if(o1.getDepartTime().compareTo(o2.getDepartTime())==0) {
					return 0;
				}
				return -1;
			}
		});
		
		for (Flight f : flights) {
			boolean bAllocated = false;
			List<Plane> list=new ArrayList<>(planes); //����һ����ʱ��list�жϳ�ͻ��ֹ����
			while (!bAllocated) {
				if(list.size()==0) {   //��ͻ��ֹ����
					bFeasible=false;
					break;
				}
				Plane p = list.get(r.nextInt(list.size())); //���list�е�һ�������Plane
				Calendar fStart = f.getDepartTime();
				Calendar fEnd = f.getArrivalTime();
				boolean bConflict = false;
				
				for (Flight t : flights) {
					Plane q = t.getPlane();
					if (! p.equals(q))  //ԭ����getPlaneΪnull���׳��쳣���޸ģ���p��q��һ��
						continue;
						
					Calendar tStart = t.getDepartTime();
					Calendar tEnd = t.getArrivalTime();
						
					if ((fStart.compareTo(tStart)>=0&&fStart.compareTo(tEnd)<0)||(tStart.compareTo(fStart)>=0&&tStart.compareTo(fEnd)<0)) {  //ԭ����><�ж�Calendar��û�п���ʱ��߽��غϵ�������޸ģ�ʹ��compareTo���ж��Ҽ��ϵȺż���
						bConflict = true;
						list.remove(q); //��ȥ����������Plane
						break;
					}
					
				}
				
				if (!bConflict) {
					f.setPlane(p);
					bAllocated=true;  //�ı����״̬
					break;
				}
			}
		}
		return bFeasible;
	}
	
}
