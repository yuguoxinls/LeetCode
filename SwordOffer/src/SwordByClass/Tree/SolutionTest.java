package SwordByClass.Tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SolutionTest {

    @Test
    public void levelOrder3() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        int size = list.size();
        List<Integer> list2 = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list2.add(list.remove(0));
        }
        System.out.println(list2);
        System.out.println(list.size());
    }
}