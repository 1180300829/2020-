/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.poet;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

/**
 * Tests for GraphPoet.
 */
public class GraphPoetTest {
    
    // Testing strategy
    //   TODO
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    // TODO tests
    
    /* Testing strategy
     * ����Ҫ������ļ����֣����ļ���һ�����룬��������
     * ����ÿ��ȡֵ���£�
     */
    @Test
    public void testGraphPoet() throws IOException{
    	 final GraphPoet nimoy = new GraphPoet(new File("test/P1/poet/empty.txt"));
         final String input = "Test the system.";
         assertEquals("Test the system.",nimoy.poem(input));
         final GraphPoet nimoy1 = new GraphPoet(new File("test/P1/poet/oneline.txt"));
         final String input1 = "Test the system.";
         assertEquals("Test of the system.",nimoy1.poem(input1));
         final GraphPoet nimoy2 = new GraphPoet(new File("test/P1/poet/mutiline.txt"));
         final String input2 = "Theater system am";
         assertEquals("Theater sound system i am",nimoy2.poem(input2));  
    }
    
    /* Testing strategy
     * ��ͼ�б�Ȩֵ���֣�ȨֵȫΪ1������֮��Ȩֵ�в�Ϊ1���м�����
     * ����ÿ��ȡֵ���£�
     */
    @Test
    public void testPoem() throws IOException{
    	 final GraphPoet nimoy = new GraphPoet(new File("test/P1/poet/oneweight.txt"));
         final String input = "Test the system.";
         assertEquals("Test of the system.",nimoy.poem(input));
         final GraphPoet nimoy1 = new GraphPoet(new File("test/P1/poet/mutiweight.txt"));
         final String input1 = "Test the system";
         assertEquals("Test a the system",nimoy1.poem(input1));  
    }
    
    /* Testing strategy
     * ����Ҫ������ļ����֣����ļ������ǿ��ļ�
     * ����ÿ��ȡֵ���£�
     */
    @Test
    public void testtoString() throws IOException{
    	 final GraphPoet nimoy = new GraphPoet(new File("test/P1/poet/empty.txt"));
         assertEquals("",nimoy.toString());
         final GraphPoet nimoy1 = new GraphPoet(new File("test/P1/poet/oneline.txt"));
         assertEquals("this->isȨ��Ϊ1\n" + "is->aȨ��Ϊ1\n" + "a->testȨ��Ϊ1\n" + "test->ofȨ��Ϊ1\n" + "of->theȨ��Ϊ1\n" + "the->mugarȨ��Ϊ1\n" + 
         		      "mugar->omniȨ��Ϊ1\n" + "omni->theaterȨ��Ϊ1\n" + "theater->soundȨ��Ϊ1\n" + "sound->system.Ȩ��Ϊ1\n",nimoy1.toString());
    }
}
