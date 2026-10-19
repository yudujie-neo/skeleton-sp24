package main;

import edu.princeton.cs.algs4.In;

public class FileReadDemo {
    public static void main(String[] args) {
        In in = new In("./data/ngrams/very_short.csv");
        int i = 0;

        while (!in.isEmpty()) {
            i += 1;
            String nextLine = in.readLine();
            // 中文：第 i 行的内容是：
            System.out.print("Line " + i + " is: ");
            System.out.println(nextLine);
            // 中文：按制表符分割后，第一个单词是：
            System.out.print("After splitting on tab characters, the first word is: ");
            String[] splitLine = nextLine.split("\t");
            System.out.println(splitLine[0]);
        }
    }
}
