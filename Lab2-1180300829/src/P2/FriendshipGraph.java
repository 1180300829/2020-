package P2;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import P1.graph.ConcreteEdgesGraph;


public class FriendshipGraph {

	private final ConcreteEdgesGraph<Person> personGraph;
	
	// Abstraction function:
    // AF(personFraph)=Social NetWork
    // Representation invariant:
    // û���ظ���Person
    // Safety from rep exposure:
    // ��personGraph����Ϊprivate
    // ����personGraph��inmutable�������ڷ���ʱ����Ҫ����defensive copies
	
	/**
	 * ��ʼ�����췽��
	 */
	public FriendshipGraph() {
		personGraph = new ConcreteEdgesGraph<>();
	}
	
	/**
	 * ��ͼ����������
	 * @param newperson ���������Person
	 */
	public void addVertex(Person newperson) {
		personGraph.add(newperson);
	}

	/**
	 * Ϊĳ������������
	 * @param a �����
	 * @param b ���ӵ�����
	 */
	public void addEdge(Person a, Person b) {
		personGraph.set(a, b, 1);
	}
	
	/**
	 * ����ConcreteVerticesGraphͼ
	 * @return ConcreteVerticesGraphͼ
	 */
	public ConcreteEdgesGraph<Person> getallprople() {
		return personGraph;
	}

	/**
	 * �õ�������֮�����̾���
	 * @param c1 ��һ����
	 * @param c2 �ڶ�����
	 * @return ������֮�����̾���
	 */
	public int getDistance(Person c1, Person c2) {  //�õ�������֮��ľ���
		if(c1==c2) {
			return 0;
		}
		Map<Person,Integer> theway=new HashMap<>();
		Queue<Person> myqueue=new LinkedList<>();
		myqueue.offer(c1);
		theway.put(c1,0);
		int distance;
		while(!myqueue.isEmpty()) {  //ֻҪ���в���
			Person top=myqueue.poll();
			distance=theway.get(top);
			Map<Person, Integer> friend=personGraph.targets(top);
			Set<Person> allfriend=friend.keySet();
			for(Person m:allfriend) {
				if(!theway.containsKey(m)) {
					theway.put(m,distance+1);  //��ĳ�˵�����ȫ�����Ϊ��һ����
					myqueue.offer(m);
					if(m==c2) {
						return theway.get(c2);
					}
				}
			}
		}
		return -1;
	}
	
	public static void main(String args[]) {
		System.out.println("ԭ��ʵ�鱨��������Ӧͼ������Ϊ:");
		FriendshipGraph graph = new FriendshipGraph();
		Person rachel = new Person("Rachel");
		Person ross = new Person("Ross");
		Person ben = new Person("Ben");
		Person kramer = new Person("Kramer");
		graph.addVertex(rachel);
		graph.addVertex(ross);
		graph.addVertex(ben);
		graph.addVertex(kramer);
		graph.addEdge(rachel, ross);
		graph.addEdge(ross, rachel);
		graph.addEdge(ross, ben);
		graph.addEdge(ben, ross);
		System.out.println("(rachel, ross)֮�����Ϊ"+graph.getDistance(rachel, ross));
		// should print 1
		System.out.println("(rachel, ben)֮�����Ϊ"+graph.getDistance(rachel, ben));
		// should print 2
		System.out.println("(rachel, rachel)֮�����Ϊ"+graph.getDistance(rachel, rachel));
		// should print 0
		System.out.println("(rachel, kramer)֮�����Ϊ"+graph.getDistance(rachel, kramer));
		// should print -1
	}
}
