package com.pack;

public class QuestionNode
{
    public QuestionNode left;
    public QuestionNode right;
    public boolean isLeaf;
    public String displayString;//The question to ask, or the correct answer

    public QuestionNode(String displayString, boolean isLeaf)
    {
        this.displayString = displayString;
        this.isLeaf = isLeaf;
        left = null;
        right = null;
    }

    public QuestionNode(QuestionNode other)
    {
        this.displayString = other.displayString;
        this.isLeaf = other.isLeaf;
        this.left = other.left;
        this.right = other.right;
    }
}
