/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * An implementation of Graph.
 * 
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteEdgesGraph<L> implements Graph<L> {
    
    private final Set<L> vertices = new HashSet<>();
    private final List<Edge<L>> edges = new ArrayList<>();
    
    // Abstraction function:
    // AF(vertices)=ͼ�еĵ�
    // AF(edges)=ͼ�еı�
    // Representation invariant:
    // edges�����Ǵ���0��ʵ��������ʼ�Ľڵ�
    // vertex����������vertices��
    // ������֮�����ֻ��һ����
    // Safety from rep exposure:
    // ��vertices��edges����Ϊprivate
    // ����vertices��edges��mutable�������ڷ�������ʱ������defensive copies
    
    // TODO constructor
    public ConcreteEdgesGraph() {	//���췽��
	}
    // TODO checkRep
    private void checkRep() {
        for (Edge<L> edge : edges) {
            assert vertices.contains(edge.getsource());
            assert vertices.contains(edge.gettarget());
            assert edge.getweight()>0;
        }
    }
    
    /**
     * Add a vertex to this graph.
     * 
     * @param vertex label for the new vertex
     * @return true if this graph did not already include a vertex with the
     *         given label; otherwise false (and this graph is not modified)
     */
    @Override public boolean add(L vertex) {
    	if(vertices.contains(vertex)) {
    		System.out.println("��������Ѿ�����\n");
    		return false;
    	}
    	checkRep();
    	return vertices.add(vertex);
       // throw new RuntimeException("not implemented");
    }
    
    /**
     * Add, change, or remove a weighted directed edge in this graph.
     * If weight is nonzero, add an edge or update the weight of that edge;
     * vertices with the given labels are added to the graph if they do not
     * already exist.
     * If weight is zero, remove the edge if it exists (the graph is not
     * otherwise modified).
     * 
     * @param source label of the source vertex
     * @param target label of the target vertex
     * @param weight nonnegative weight of the edge
     * @return the previous weight of the edge, or zero if there was no such
     *         edge
     */
    @Override public int set(L source, L target, int weight) {
    	int fanhui=0;
    	if(weight<0) {
    		System.out.println("Ȩֵ����Ϊ��");
    		return -1;
    	}
    	else {
    		if(weight>0) {
    			Iterator<Edge<L>> it=edges.iterator();
    			while(it.hasNext()) {  //���±��Ѿ�����ʱ����ȥ�˱�
    				Edge<L> a=it.next();
    				if(a.getsource().equals(source) && a.gettarget().equals(target)) {
    					fanhui=a.getweight();
    					it.remove();
    					break;
    				}
    			}
    			vertices.add(source);  //����±�
    			vertices.add(target);
    			Edge<L> ok=new Edge<L>(source,target,weight);
    			edges.add(ok);
    		}
    		if(weight==0) {  //�±�ȨֵΪ0���Ѿ�����ʱ��ȥ�˱�
    			Iterator<Edge<L>> him=edges.iterator();
    			while(him.hasNext()) {
    				Edge<L> m=him.next();
    				if (m.getsource().equals(source) && m.gettarget().equals(target)) {
    					him.remove();
    					fanhui=m.getweight();
    					break;
    			}
    		}
    	}
    	}
    	checkRep();
    	return fanhui;	
        //throw new RuntimeException("not implemented");
    }
    
    /**
     * Remove a vertex from this graph; any edges to or from the vertex are
     * also removed.
     * 
     * @param vertex label of the vertex to remove
     * @return true if this graph included a vertex with the given label;
     *         otherwise false (and this graph is not modified)
     */
    @Override public boolean remove(L vertex) {
    	if(vertices.contains(vertex)) {  //����õ��Ѿ����ڣ���ȥ�õ����õ����ӵı�
    		vertices.remove(vertex);
    		Iterator<Edge<L>> ll=edges.iterator();
    		while(ll.hasNext()) {
    			Edge<L> b=ll.next();
    			if(b.getsource().equals(vertex)||b.gettarget().equals(vertex)) {
    				ll.remove();
    			}
    		}
    	}
    	else {  //����õ㲻���ڷ���false
    		return false; 
    	}
    	checkRep();
    	return true;
       // throw new RuntimeException("not implemented");   
    }
    
    /**
     * Get all the vertices in this graph.
     * 
     * @return the set of labels of vertices in this graph
     */
    @Override public Set<L> vertices() {
    	return new HashSet<L>(vertices);
        //throw new RuntimeException("not implemented");
    }
    
    /**
     * Get the source vertices with directed edges to a target vertex and the
     * weights of those edges.
     * 
     * @param target a label
     * @return a map where the key set is the set of labels of vertices such
     *         that this graph includes an edge from that vertex to target, and
     *         the value for each key is the (nonzero) weight of the edge from
     *         the key to target
     */
    @Override public Map<L, Integer> sources(L target) {
        Map<L,Integer> yutao=new HashMap<>();
        for(Edge<L> c:edges) {
        	if(c.gettarget().equals(target)) {   
        		yutao.put(c.getsource(),c.getweight());  //�����ʼ���Ȩֵ
        	}
        }
        checkRep();
        return yutao;
    	//throw new RuntimeException("not implemented");
    }
    
    /**
     * Get the target vertices with directed edges from a source vertex and the
     * weights of those edges.
     * 
     * @param source a label
     * @return a map where the key set is the set of labels of vertices such
     *         that this graph includes an edge from source to that vertex, and
     *         the value for each key is the (nonzero) weight of the edge from
     *         source to the key
     */
    @Override public Map<L, Integer> targets(L source) {
    	 Map<L,Integer> yutao=new HashMap<>();
         for(Edge<L> c:edges) {
         	if(c.getsource().equals(source)) {   
         		yutao.put(c.gettarget(),c.getweight());  //�����ʼ���Ȩֵ
         	}
         }
         checkRep();
         return yutao;
    	//throw new RuntimeException("not implemented");
    }
    
    // TODO toString()
    /**
     * @return ��������ͼ���ɵ��ַ���
     */
    @Override public String toString() {
    	String mm = "";
		for (Edge<L> p : edges) {
			mm = mm + p.toString();
		}
		return mm;
    }
    
}

/**
 * TODO specification
 * Immutable.
 * This class is internal to the rep of ConcreteEdgesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Edge<L> {
    
    // TODO fields
	private final L source,target;  //�ߵ�������
	private final int weight; //�ߵ�Ȩֵ
    
    // Abstraction function:
	// AF(source) = �ߵ����
    // AF(target) = �ߵ��յ�
    // AF(weight) = �ߵ�Ȩֵ
    // Representation invariant:
    // weight >=0 source��target��Ϊ��
    // Safety from rep exposure:
    // ��source,target,weight����Ϊprivate
    
    // TODO constructor
    
	/**
	 * ��ʼ�����췽������ʼ���±ߵ�������ͱߵ�Ȩֵ
	 * @param source2  �±ߵ�һ����
	 * @param target2  �±ߵ�����һ����
	 * @param wei  �±ߵ�Ȩֵ
	 */
    public Edge(L source2,L target2,int wei) {
    	source=source2;
    	target=target2;
    	weight=wei;
    	checkRep();
    }
    // TODO checkRep
	
    /**
     * ����ʾ������,�߲�Ϊ����Ȩֵ���ڵ���0
     */
    public void checkRep(){
    	assert source!=null;
    	assert target!=null;
    	assert weight>=0;
    }
    
    /**
     * 
     * @return �ߵ�һ����source
     */
    public L getsource() {
    	return source;
    }
    
    /**
     * 
     * @return �ߵ������target
     */
    public L gettarget() {
    	return target;
    }
    
    /**
	 * 
	 * @return ���رߵ�weight
	 */
	public int getweight() {
		checkRep();
		return weight;
	}
	
	// TODO toString()
    /**
     * ����һ���ߵ��ַ���
     */
	@Override
	public String toString() {
		return source.toString() + "->" + target.toString() + "Ȩ��Ϊ" + weight + '\n';
	}
	

}
