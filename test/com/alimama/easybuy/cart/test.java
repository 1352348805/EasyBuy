package com.alimama.easybuy.cart;

import org.junit.Test;

import java.util.*;

/**
 * @author Jun Xiao
 * @date 2020/7/17 16:39
 */
public class test {
    @Test
    public void  ts1(){
       Map<String,String > dogs = new HashMap<String , String>();
        dogs.put("dog1","sdf");
        dogs.put("dog2","hr");
        dogs.put("dog3","yry");
        dogs.put("dog3","iiodd");
        Set<String> keys=dogs.keySet();
        for (String key : keys) {
            System.out.println(key+"");
        }

    }
    public void tes2(){
        List list = new ArrayList();
        list.add(2);
        list.add(1);
        list.remove(1);
        Iterator it= list.iterator();
        while(it.hasNext()){
             //int num = it.hasNext();
        }

    }
}
