/*
 * CS577 HW 8
 * Author: Jason Lee (jlee967@wisc.edu)
 * Knapsack
 */

import java.util.*;

public class knapsack
{
    private int num;
    private int weightCap;
    private List <Item> itemList;
    private int [][] m;

    public knapsack(int num, int weightCap)
    {
        this.num = num;
        this.weightCap = weightCap;
        this.itemList = new ArrayList <Item>(num);
        this.m = new int[num + 1][weightCap + 1];
    }

    private class Item
    {
        private int weight;
        private int value;

        private Item(int weight, int value)
        {
            this.weight = weight;
            this.value = value;
        }
    }

    // Create a new item object and add to sack
    private void addItem(int weight, int value)
    {
        Item itemToAdd = new Item(weight, value);
        this.itemList.add(itemToAdd);
    }

    private static knapsack[] readInput()
    {
        Scanner kb = new Scanner(System.in);

        int numInstances = kb.nextInt();
        knapsack instances[] = new knapsack[numInstances];


        // Loop to go through numInstances
        for(int i = 0; i < numInstances; i++)
        {
            int num = kb.nextInt();
            int weightCap = kb.nextInt();

            instances[i] = new knapsack(num, weightCap);
            instances[i].itemList.add(null);

            kb.nextLine();


            // Loop to get weight and value, then add to list
            while(num > 0)
            {
                int weight = kb.nextInt();
                int value = kb.nextInt();

                instances[i].addItem(weight, value);
                num--;
            }
        }
        kb.close();
        return instances;
    }

    // Calculate set of items that should be added with respect to weight
    private int getOptimal()
    {
        for(int i = 0; i <= this.num; i++)
        {
            for(int j = 0; j <= this.weightCap; j++)
            {
                if(i == 0 || j == 0)
                {
                    continue;
                }

                Item current = this.itemList.get(i);

                if(current.weight > j)
                {
                    this.m[i][j] = m[i - 1][j];
                }
                // Bellman equation
                else if(current.value + this.m[i - 1][j - current.weight] > this.m[i - 1][j])
                {
                    m[i][j] = current.value + this.m[i - 1][j - current.weight];
                }
                else
                {
                    m[i][j] = m[i - 1][j];
                }
            }
        }
        return this.m[this.num][this.weightCap];

    }


    public static void main(String [] args)
    {
    	try
    	{
    		knapsack [] instances = readInput();
    		for(knapsack sack : instances)
    		{
    			int optimalValue = sack.getOptimal();
    			System.out.println(optimalValue);
    		}
    	}
    	catch(Exception e)
    	{
    		System.out.println("This shit broke");
    	}
    }
}

