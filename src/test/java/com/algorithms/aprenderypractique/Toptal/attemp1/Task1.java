package com.algorithms.aprenderypractique.Toptal.attemp1;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class Task1 extends BaseTest {

    @Test
    public void solution() {
        int k=3;
        count("ABCA");
        List<Node> list = count("AAABXXAAA");
        print(list);
        int[] substring = find(list,k);
        System.out.println(substring[0] +"---"+ substring[1]);
        System.out.println(getResult(list));

        System.out.println(solution("AAABXXAAA",3));
    }

    public int solution(String S, int K) {
        List<Node> list = count(S);
        int[] substring = find(list,K);
        return getResult(list).length();
    }

    public List count(String str) {
        List<Node> nodeList = new LinkedList<>();
        for(int i=0;i<str.length();i++) {
            Node node;
            int count=1;
            char c = str.charAt(i);
            while(i!=str.length()-1 && c == str.charAt(i+1)) {
                i++;
                count++;
            }
            node = new Node(c,count);
            nodeList.add(node);
        }
        return nodeList;
    }

    public int[] find(List<Node> nodeList, int k) {
        int countMiddle=0;
        int[] substring = new int[2];
        for (int i=0;i<nodeList.size();i++) {
            char key = nodeList.get(i).key;
            int value = nodeList.get(i).value;
            int j=i;
            for (;j<nodeList.size() && countMiddle<value;j++) {
                countMiddle+=nodeList.get(j).value;
            }
            if(countMiddle == k && j<nodeList.size() && key == nodeList.get(j).key) {
                substring[0] = i;
                substring[1] = j;
            }
        }
        return substring;
    }

    public String getResult(List<Node> nodeList) {
        StringBuilder result = new StringBuilder();
        for (Node node: nodeList) {
            result.append(node.value==1?"":node.value);
            result.append(node.key);
        }
        return result.toString();
    }

    void print(List<Node> list) {
        for (Node n : list) {
            System.out.println(n.key.toString() + ' ' + n.value);
        }
    }

}
