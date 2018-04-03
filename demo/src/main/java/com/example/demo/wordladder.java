package com.example.demo;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class wordladder {

    HashSet<String> dictionary=new HashSet<String>();

    public void Read() throws FileNotFoundException {
        File file = ResourceUtils.getFile("classpath:static/EnglishWords.txt");
        try {
            FileReader dic=new FileReader(file);
            BufferedReader edic=new BufferedReader(dic);
            String word=null;
            while((word=edic.readLine())!=null) {
                dictionary.add(word);

            }
            edic.close();
            dic.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean search(String word) {
        if (dictionary.add(word)) {
            dictionary.remove(word);
            return false;
        }
        dictionary.remove(word);
        return true;

    }
    public static String change(String s1, int index , int length, char letter) {
        String str = null;
        if(index!=0||(index+1)!=length) {
            String str1=s1.substring(0,index);
            String str2=s1.substring(index+1);
            str=str1+letter+str2;
        }
        else if (index==0) {
            String substr=s1.substring(1);
            str=letter+substr;
        }
        else if((index+1)==length) {
            String substr=s1.substring(0, index-1);
            str=substr+letter;
        }
        return str;
    }
    public String main(String w1,String w2) throws FileNotFoundException {
        wordladder englishwords = new wordladder();
        englishwords.Read();                                        //English dictionary
        Queue<Stack<String>> queue = new LinkedList<Stack<String>>(); //store the possible word ladder



        String s1 = w1;

        if ((!englishwords.search(s1))) {
            return(" word 1 is not in the dictionary.");
        }
        englishwords.dictionary.add(s1);                            //add first word to the dictionary


        String s2 = w2;

        if ((!(englishwords.search(s2)))) {
            return("word 2 is not in the dictionary.");
        }



        int len1 = s1.length();
        int len2 = s2.length();
        englishwords.dictionary.add(s2);                            //add target to the dictionary
        if (len1 != len2) {
            return("The length of \"" + s1 + "\" is not equal to the length of \"" + s2 + "\".");
        } else {
            if (s1 != s2) {
                Stack<String> stack = new Stack<String>();
                stack.push(s1);
                queue.offer(stack);

                Loop:
                while (!queue.isEmpty()) {
                    Stack<String> localstack = new Stack<String>();
                    localstack = queue.poll();
                    s1 = localstack.peek();
                    for (int j = 0; j < len1; j++) {
                        for (int i = 'a'; i <= 'z'; i++) {
                            String newString = null;
                            newString = englishwords.change(s1, j, len1, (char) i);
                            if (newString.equals(s2)) {
                                localstack.push(newString);
                                stack = localstack;
                                break Loop;
                            } else if (englishwords.search(newString) && (!newString.equals(s1))) {
                                Stack<String> sclone = (Stack<String>) localstack.clone();
                                sclone.push(newString);
                                queue.offer(sclone);
                            }
                        }
                    }
                }
                if (stack.size() != 1) {
                    String ladder="";
                    while(!stack.isEmpty()){
                        ladder+=(stack.pop()+"\t");
                    }
                    return(ladder);

                } else {
                    return("There is no ladder between "+s1+" and "+"s2.");
                }
            } else {
                return("The two words must be different.");
            }

        }


    }
}