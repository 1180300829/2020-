/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for ConcreteEdgesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteEdgesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteEdgesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteEdgesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteEdgesGraph<String>();
    }
    
    /*
     * Testing ConcreteEdgesGraph...
     */
    
    // Testing strategy for ConcreteEdgesGraph.toString()
    // �����������Ƿ��ܹ�toString
    
    // TODO tests for ConcreteEdgesGraph.toString()
    @Test
    public void testConcreteEdgesGraphtoString() {
    	Graph<String> graph =emptyInstance();
    	graph.add("a");
    	graph.add("b");
    	graph.add("c");
    	graph.set("a", "b", 5);
    	graph.set("b", "c", 4);
    	graph.set("a", "c", 3);
    	assertEquals("a->bȨ��Ϊ5\n"+"b->cȨ��Ϊ4\n"+"a->cȨ��Ϊ3\n",graph.toString());
    }
    
    /*
     * Testing Edge...
     */
    
    // Testing strategy for Edge
    // ֻ��Ҫ����Edge�е�ÿһ�ַ�������
    
    // TODO tests for operations of Edge
    
    /* Testing strategy
     * �����յ�ķ���ֵ����
     */
    @Test
    public void testGetsource() {
    	Edge<String> one=new Edge<String>("a","b",5);
    	assertEquals("a", one.getsource());
    }
    
    /* Testing strategy
     * ����Դ��ķ���ֵ����
     */
    @Test
    public void testGettarget() {
    	Edge<String> one=new Edge<String>("a","b",5);
    	assertEquals("b", one.gettarget());
    }
    
    /* Testing strategy
     * ����Ȩֵ�ķ���ֵ����
     */
    @Test
    public void testGetweight() {
    	Edge<String> one=new Edge<String>("a","b",5);
    	assertEquals(5, one.getweight());
    }
    
}
