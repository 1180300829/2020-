package PlanningEntry;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class MultipleSortedResourceEntryImpl<R> implements MultipleSortedResourceEntry<R> {

	private List<R> mysource; //��Դ����
	
	// mutability��
	// Abstraction function:
	// AF(mytsource)=��Դ����
	// Representation invariant:
	// �������Դ����Ϊ��
	// Safety from rep exposure:
	// ��mysource����Ϊprivate
	
	/**
	 * ���ø���Դ����
	 * @param source ��Դ����
	 * @return �Ƿ�ɹ����ø���Դ����
	 */
	@Override
	public boolean setresource(List<R> source) {
		if(mysource==null&&source!=null) {
			 this.mysource=new ArrayList<>(source);
			 System.out.println("��Դ�������óɹ�");
			 return true;
		}
		 System.out.println("��Դ����ֻ������һ��");
		return false;
	}

	/**
	 * �õ�����Դ����
	 * @return ����Դ����
	 */
	@Override
	public List<R> getresource() {
		return mysource;
	}

	/**
	 * ����ĳ��Դ
	 * @param presource �����ĵ���Դ
	 * @param aftersource ���ĺ����Դ
	 * @return �Ƿ�ɹ�������Դ
	 */
	@Override
	public boolean changeresource(R presource,R aftersource) {
		int i;
		for(i=0;i<mysource.size();i++) {
			if(mysource.get(i).equals(presource) ){
				break;
			}
		}
		if(i==mysource.size()) {
			System.out.println("û�д��滻��Դ\n");
			System.out.println();
			return false;
		}
		if(mysource.contains(aftersource)) {
			System.out.println("����Դ�Ѿ�����\n");
			return false;
		}
		mysource.set(i,aftersource);
		System.out.println("����Դ�ѱ��滻Ϊָ����Դ\n");
		return true;	
	}

	/**
	 * ����Դ�����������һ����Դ
	 * @param source ���������Դ
	 * @param temp ������Դ��λ��
	 * @return �Ƿ�ɹ�������Դ
	 */
	@Override
	public boolean addresource(R source,int temp) {
		if(mysource.contains(source)) {
			System.out.println("��������Դ�Ѿ�����\n");
			return false;
		}
		else {
			if(temp<mysource.size()) {
				mysource.add(temp, source);
				System.out.println("����Դ�Ѿ�������ָ��λ��\n");
				return true;
			}
			else {
				mysource.add(source);
				System.out.println("����Դ�Ѿ�������ָ��λ��\n");
				return true;
			}
		}
	}

	/**
	 * ɾ����Դ�����е�ĳ��Դ
	 * @param source ��ɾ������Դ
	 * @return �Ƿ�ɹ�ɾ����Դ
	 */
	@Override
	public boolean deleteresource(R source) {
		boolean flag=false;
		Iterator<R> temp=mysource.iterator();
		while(temp.hasNext()) {
			R mm=temp.next();
			if(mm.equals(source)) {
				temp.remove();
				flag=true;
			}
		}
		if(flag==true) {
			System.out.println("ָ����Դ�ѱ��Ƴ�\n");
			return true;
		}
		else {
			System.out.println("û�д��Ƴ���Դ\n");
			return false;
		}
	}
	
	

}
