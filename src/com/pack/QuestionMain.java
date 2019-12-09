package com.pack;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;


//This class is the one provided by the assignment;
//I did not write it!

public class QuestionMain implements UserInterface
{
    private Scanner console;
    private QuestionTree tree;

    public QuestionMain()
    {
        console = new Scanner(System.in);
        tree = new QuestionTree(this);
    }

    public static void main(String[] args)
    {
        QuestionMain tq = new QuestionMain();
        tq.run();
    }

    public String nextLine()
    {
        return console.nextLine();
    }

    public void print(String message)
    {
        System.out.print(message);
        System.out.print(" ");
    }

    public void println(String message)
    {
        System.out.println(message);
    }

    /**
     * Prints a blank line to the console.
     */
    public void println()
    {
        System.out.println();
    }

    /**
     * Waits for the user to answer a yes/no question on the console and returns the     * user's response as a boolean (true for anything that starts with "y" or "Y").
     */

    public boolean nextBoolean()
    {
        String answer = console.nextLine();
        return answer.trim().toLowerCase().startsWith("y");
    }

    private void run()
    {
        println("Welcome to the game of 20 Questions!");
        load();
        println("\n" + BANNER_MESSAGE);
        do
        {
            println();
            tree.play();
            print(PLAY_AGAIN_MESSAGE);
        } while (nextBoolean());
        println("\n" + String.format(STATUS_MESSAGE, tree.totalGames(), tree.gamesWon()));
        save();
    }

    private void load()
    {
        print(LOAD_MESSAGE);
        if (nextBoolean())
        {
            print(SAVE_LOAD_FILENAME_MESSAGE);
            String filename = nextLine();
            try
            {
                Scanner in = new Scanner(new File(filename));
                tree.load(in);
            } catch (FileNotFoundException e)
            {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void save()
    {
        print(SAVE_MESSAGE);
        if (nextBoolean())
        {
            print(SAVE_LOAD_FILENAME_MESSAGE);
            String filename = nextLine();
            try
            {
                PrintStream out = new PrintStream(new File(filename));
                tree.save(out);
                out.close();
            } catch (FileNotFoundException e)
            {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
