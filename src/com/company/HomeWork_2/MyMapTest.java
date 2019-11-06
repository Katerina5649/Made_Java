package com.company.HomeWork_2;


import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;
import java.util.Set;



public class MyMapTest {
    public static final MyMap<Integer, String> myMap = new MyMap<>();

    @Test
    public void checkContains() {
        Assert.assertTrue(myMap.put(1, "first string").equals("first string"));
        Assert.assertTrue(myMap.contains(1));
    }

    @Test
    public void checkRemove() {
        Assert.assertTrue(myMap.put(1, "first string").equals("first string"));
        Assert.assertTrue(myMap.contains(1));
        Assert.assertEquals("first string", myMap.remove(1));
        Assert.assertFalse(myMap.contains(1));
    }

    @Test
    public void checkVoidValue() {
        Assert.assertTrue(myMap.put(1, "first string").equals("first string"));
        Assert.assertTrue(myMap.put(2, "second string").equals("second string"));
        Collection collection = myMap.values();
        Assert.assertFalse(collection == null);
        Assert.assertEquals(collection.size(), 2);
        Assert.assertTrue(collection.contains("first string"));
        Assert.assertTrue(collection.contains("second string"));
    }

    @Test
    public void checkVoidKeySet() {
        Assert.assertTrue(myMap.put(1, "first_string").equals("first_string"));
        Assert.assertTrue(myMap.put(2, "second_string").equals("second_string"));
        Set set = myMap.keySet();
        Assert.assertFalse(set == null);
        Assert.assertEquals(set.size(), 2);
        Assert.assertTrue(set.contains(1));
        Assert.assertTrue(set.contains(1));
    }

    @Test
    public void checkVoidGet() {
        MyMap<String, String> myMap = new MyMap<>();
        Assert.assertTrue(myMap.put("1", "first").equals("first"));
        Assert.assertTrue(myMap.get("1").equals("first"));
        Assert.assertTrue(myMap.contains("1"));
    }

    @Test
    public void checkOverFlowSize() {
        int size = 4;
        MyMap<Integer, String> myMap = new MyMap<>(size);
        Assert.assertTrue(myMap.put(1, "first").equals("first"));
        Assert.assertTrue(myMap.put(2, "second").equals("second"));
        Assert.assertTrue(myMap.put(3, "third").equals("third"));
        Assert.assertEquals(2 * size, myMap.arrayLength());

    }
}
