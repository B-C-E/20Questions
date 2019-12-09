package com.pack;

import java.io.PrintStream;
import java.util.Scanner;

//This class plays games of 20 questions, and stores them as binary trees
public class QuestionTree
{
    //fields
    private int gamesPlayed;
    private int myGamesWon;
    private UserInterface ui;

    private QuestionNode root;

    //constructor
    public QuestionTree(UserInterface ui)
    {
        gamesPlayed = 0;
        myGamesWon = 0;
        this.ui = ui;
        root = new QuestionNode("Computer", true);
    }

    //plays a round
    public void play()
    {
        gamesPlayed++;
        QuestionNode selected = root;//the currently selected node

        while (selected.isLeaf == false)//navigate the tree until you reach a leaf
        {
            ui.print(selected.displayString);//print the branch's question

            if (ui.nextBoolean())//if they say yes
            {
                selected = selected.left;   //go left
            } else            //otherwise
            {
                selected = selected.right;  //go right
            }

        }

        ui.print("Would your object happen to be: " + selected.displayString);

        if (ui.nextBoolean())//if the computer won
        {
            ui.print("I win!");
            myGamesWon++;
        } else//if the computer lost, ask the user for a new question to ask
        {
            ui.print("I lose. What is your object?");
            String newObject = ui.nextLine();
            ui.print("Type a yes/no question to distinguish your item from " + selected.displayString);
            String newQuestion = ui.nextLine();
            ui.print("And what is the answer for your object?");

            //change the tree!
            QuestionNode rightLeaf, leftLeaf;
            if (ui.nextBoolean())//if it should go on the left
            {
                //copy the old leaf node
                rightLeaf = new QuestionNode(selected);
                //create the new second leaf node
                leftLeaf = new QuestionNode(newObject, true);
            } else                  //if it should go on the right
            {
                //copy the old leaf node
                rightLeaf = new QuestionNode(newObject, true);
                //create the new second leaf node
                leftLeaf = new QuestionNode(selected);
            }
            //turn the old leaf node into a new branch

            selected.isLeaf = false;
            selected.displayString = newQuestion;
            selected.left = leftLeaf;
            selected.right = rightLeaf;
        }//end of if the computer lost
    }//end of play


    //saves a question tree
    public void save(PrintStream output)
    {
        recursiveSave(root,output);
    }//end of save

    //saves our tree
    private void recursiveSave(QuestionNode node, PrintStream output)
    {
        if(!node.isLeaf)//if it's a question
        {
            output.print("Q:");
        }
        else            //if it's an answer
        {
            output.print("A:");
        }
        //add what it is
        output.println(node.displayString);

        //if there is a left node to save
        if( node.left != null)
        {
            recursiveSave(node.left,output);
        }

        //if there is a right node to save
        if (node.right != null)
        {
            recursiveSave(node.right,output);
        }
    }//end of recursiveSave


    //loads a game
    public void load(Scanner input)
    {

        root = recurseLoad(input);
    }

    //uses recursion to load a saved tree
    private QuestionNode recurseLoad(Scanner input)
    {
        String nodeInfo = input.nextLine();
        boolean isLeaf = nodeInfo.startsWith("A");
        if (isLeaf)
        {
            return new QuestionNode(nodeInfo.substring(2), true);
        }
        else
        {
            QuestionNode toReturn = new QuestionNode(nodeInfo.substring(2), false);
                    toReturn.left = recurseLoad(input);
                    toReturn.right = recurseLoad(input);
                    return toReturn;
        }
    }//end of recurseLoad

    //Accesssors
    public int totalGames()
    {
        return gamesPlayed;
    }

    public int gamesWon()
    {
        return myGamesWon;
    }

}//end of QuestionTree