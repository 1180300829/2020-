/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * Tests for ConcreteVerticesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteVerticesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteVerticesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteVerticesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteVerticesGraph<String>();
    }
    
    /*
     * Testing ConcreteVerticesGraph...
     */
    
    // Testing strategy for ConcreteVerticesGraph.toString()
    // �����������Ƿ��ܹ�toString
    
    // TODO tests for ConcreteVerticesGraph.toString()
    @Test
    public void testConcreteEdgesGraphtoString() {
    	Graph<String> graph =emptyInstance();
    	graph.add("a");
    	graph.add("b");
    	graph.add("c");
    	graph.set("a", "b", 5);
    	graph.set("b", "c", 4);
    	graph.set("c", "a", 3);
    	assertEquals("��a��ָ�����ĵ�ͱߵ�ȨֵΪ��{c=3}����ָ��ĵ�ͱߵ�ȨֵΪ��{b=5}\n"+"��b��ָ�����ĵ�ͱߵ�ȨֵΪ��{a=5}����ָ��ĵ�ͱߵ�ȨֵΪ��{c=4}\n"+"��c��ָ�����ĵ�ͱߵ�ȨֵΪ��{b=4}����ָ��ĵ�ͱߵ�ȨֵΪ��{a=3}\n",graph.toString());
    } 
    /*
     * Testing Vertex...
     */
    
    // Testing strategy for Vertex
    // ֻ��Ҫ����Vertex�е�ÿһ�ַ�������
    
    // TODO tests for operations of Vertex
    
    /* Testing strategy
     * ���Ե�ķ���ֵ����
     */
    @Test
    public void testGetmark() {
    	Vertex<String> vertex = new Vertex<String>("a");
		assertEquals("a", vertex.getmark());
	}
    
    /* Testing strategy
     * ���Ե������Դ��ͱ߷���ֵ����
     */
    @Test
	public void testGetsource() {
    	Vertex<String> vertex = new Vertex<String>("a");
		vertex.addsource("b", 5);
		vertex.addsource("c", 3);
    	Map<String,Integer> map=new HashMap<String,Integer>();
    	map.put("b",5);
    	map.put("c",3);
    	assertEquals(map, vertex.getsource());
    }
    
    /* Testing strategy
     * ���Ե�������յ�ͱ߷���ֵ����
     * ����ÿ��ȡֵ���£�
     */
    @Test
	public void testGettarget() {
    	Vertex<String> vertex = new Vertex<String>("a");
		vertex.addtarget("b", 5);
		vertex.addtarget("c", 3);
    	Map<String,Integer> map=new HashMap<String,Integer>();
    	map.put("b",5);
    	map.put("c",3);
    	assertEquals(map, vertex.gettarget());
    }
    
    /* Testing strategy
     * ���ռ���ĵ㻮�֣����Ѿ����ڣ��㲻����
     * ���ռ����Ȩֵ���֣�ȨֵΪ0��Ȩֵ����0��ȨֵС��0
     * ����ÿ��ȡֵ���£�
     */
    @Test
	public void testAddsource() {
    	Vertex<String> vertex = new Vertex<String>("a");
    	assertEquals(0, vertex.addsource("b", 5));
    	assertEquals(0, vertex.addsource("c", 3));
    	assertEquals(5, vertex.addsource("b", 4));
    	assertEquals(4, vertex.addsource("b", 0));
    	assertEquals(-1, vertex.addsource("b", -1));
    }
    
    /* Testing strategy
     * ���ռ���ĵ㻮�֣����Ѿ����ڣ��㲻����
     * ���ռ����Ȩֵ���֣�ȨֵΪ0��Ȩֵ����0��ȨֵС��0
     */
    @Test
	public void testAddtarget() {
    	Vertex<String> vertex = new Vertex<String>("a");
    	assertEquals(0, vertex.addtarget("b", 5));
    	assertEquals(0, vertex.addtarget("c", 3));
    	assertEquals(5, vertex.addtarget("b", 4));
    	assertEquals(4, vertex.addtarget("b", 0));
    	assertEquals(-1, vertex.addtarget("b", -1));
    }
    
    /* Testing strategy
     * �����Ƴ��ĵ㻮�֣����Ѿ����ڣ��㲻����
     * ����ÿ��ȡֵ���£�
     */
    @Test
	public void testRemovesource() {
    	Vertex<String> vertex = new Vertex<String>("a");
    	vertex.addsource("b", 5);
    	vertex.addsource("c", 3);
    	assertEquals(0, vertex.removesource("d"));
    	assertEquals(5, vertex.removesource("b"));
    }
    
    /* Testing strategy
     * �����Ƴ��ĵ㻮�֣����Ѿ����ڣ��㲻����
     * ����ÿ��ȡֵ���£�
     */
    @Test
	public void testRemovetarget() {
    	Vertex<String> vertex = new Vertex<String>("a");
    	vertex.addtarget("b", 5);
    	vertex.addtarget("c", 3);
    	assertEquals(0, vertex.removetarget("d"));
    	assertEquals(5, vertex.removetarget("b"));
    }
}
