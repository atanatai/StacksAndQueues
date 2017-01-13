//Kai Rahm
//CS3240

import java.util.Scanner;

public class StockMenu {
	public static void print(Object line) {
	    System.out.println(line);
	}
	public static void main(String[] args){
		StockDatabase yourStocks = new StockDatabase();
		
		
		Scanner input = new Scanner(System.in);
	    String choice = null;
	    
	 
	    while (!"7".equals(choice)) {
	    	print("*******************************************************");
	    	print("Welcome to your stocks menu!");
	    	print("*******************************************************");
	    	print("Press 1 to print your list of stocks");
            print("Press 2 to insert a newly bought stock to your list of stocks");
            print("Press 3 to query the LIFO and FIFO prices for selling your stock");
			print("Press 4 to sell (remove) any stock using FIFO accounting");
			print("Press 5 to sell (remove) any stock using LIFO accounting");
			print("Press 6 to see an example of stocks");
			print("Press 7 to quit");
	    
	        choice = input.nextLine();
	        
	        if ("1".equals(choice)) {
	        	System.out.println("All currently owned stocks:");
	    		yourStocks.printStocks();
	        	choice = null;
	          }
	        if ("2".equals(choice)) {
	        	print("Please enter the following information for the new stock");
	        	print("Name of Stock:");
	        	String stockName = input.nextLine();
	        	print("Number of stocks in "+stockName+":");
	        	int stockAmount = Integer.parseInt(input.nextLine());
	        	print("Purchase price per stock:");
	        	double stockPrice = Double.parseDouble(input.nextLine());
	        	yourStocks.addStock(new Stock(stockName,stockAmount,stockPrice));
	        	choice = null;
	        }
	        if ("3".equals(choice)) {
	        	if(yourStocks.stocksLength() == 0){
	        		print("You don't have any stocks.");
	        		continue;
	        	}else{
	        	print("To find the FIFO & LIFO costs for comparison, more details are needed:");
	        	print("Name of Stock to search for:");
	        	String stockName = input.nextLine();
	        	print("Number of "+stockName+" stocks you want to see prices for:");
	        	int stockAmount = Integer.parseInt(input.nextLine());
	        	print("What is the current price for "+stockName+" stock?");
	        	double stockPrice = Double.parseDouble(input.nextLine());
	        	yourStocks.findFIFOPrice(stockName, stockAmount, stockPrice);
	        	yourStocks.findLIFOPrice(stockName, stockAmount, stockPrice);
	        	}
	        	choice = null;
	        }
	        if ("4".equals(choice)) {
	        	if(yourStocks.stocksLength() == 0){
	        		print("You don't have any stocks.");
	        		continue;
	        	}else{
	        	print("To sell your stock using FIFO accounting, the following is needed:");
	        	print("Name of Stock to sell:");
	        	String stockName = input.nextLine();
	        	print("Number of "+stockName+" stocks you want to sell:");
	        	int stockAmount = Integer.parseInt(input.nextLine());
	        	print("What is the current price for "+stockName+" stock?");
	        	double stockPrice = Double.parseDouble(input.nextLine());
	        	print("Your Stock Before Selling:");
	        	yourStocks.printStocks();
	        	yourStocks.buyFIFOStocks(stockName, stockAmount, stockPrice);
	        	print("Your Stock After Selling:");
	        	yourStocks.printStocks();
	        	}
	        	choice = null;
	        }
	        if ("5".equals(choice)) {
	        	if(yourStocks.stocksLength() == 0){
	        		print("You don't have any stocks.");
	        		continue;
	        	}else{
	        	print("To sell your stock using LIFO accounting, the following is needed:");
	        	print("Name of Stock to sell:");
	        	String stockName = input.nextLine();
	        	print("Number of "+stockName+" stocks you want to sell:");
	        	int stockAmount = Integer.parseInt(input.nextLine());
	        	print("What is the current price for "+stockName+" stock?");
	        	double stockPrice = Double.parseDouble(input.nextLine());
	        	print("Your Stock Before Selling:");
	        	yourStocks.printStocks();
	        	yourStocks.buyLIFOStocks(stockName, stockAmount, stockPrice);
	        	print("Your Stock After Selling:");
	        	yourStocks.printStocks();
	        	}
	        	choice = null;
	        }
	        if ("6".equals(choice)) {
	        	print("This is an example of code used to demonstrate LIFO & FILO accounting:");
	        	Stock s1 = new Stock("Apple",2,1.50);
	    		Stock s2 = new Stock("Google",3,7.00);
	    		Stock s3 = new Stock("Apple",3,4);
	    		Stock s4 = new Stock("applE",4,0.5);
	    		Stock s5 = new Stock("Google",10,2.5);
	    		Stock[] slist = {s1,s2};
	    		StockDatabase sbase = new StockDatabase(slist);
	    		
	    		print("--------------------------------------------------------------");
	    		sbase.printStocks();
	    		sbase.addStock(s3);
	    		sbase.addStock(s5);
	    		sbase.printStocks();
	    		print("--------------------------------------------------------------");
	    		sbase.findFIFOPrice("apple", 2, 2);
	    		sbase.findLIFOPrice("apple", 2, 2);
	    		print("--------------------------------------------------------------");
	    		print("Stock Before Sales:");
	    		sbase.printStocks();
	    		sbase.buyFIFOStocks("apple", 1, 3.5);
	    		sbase.buyLIFOStocks("apple", 1, 3.5);
	    		print("Stock After Sales:");
	    		sbase.printStocks();
	    		print("--------------------------------------------------------------");
	    		sbase.addStock(s4);
	    		print("--------------------------------------------------------------");
	    		print("Queue removal:");
	    		print("Queue Before FIFO Sales:");
	    		sbase.printStocks();
	    		sbase.buyFIFOStocks("apple", 2, 3.5);
	    		sbase.buyFIFOStocks("google", 4, 2.25);
	    		print("Stock After FIFO Sales:");
	    		sbase.printStocks();
	    		print("--------------------------------------------------------------");
	    		print("Stack removal:");
	    		print("Stock Before LIFO Sales:");
	    		sbase.printStocks();
	    		sbase.buyLIFOStocks("apple", 2, 10);
	    		sbase.buyLIFOStocks("google", 4, 10);
	    		print("Stock After LIFO Sales:");
	    		sbase.printStocks();
	    		print("--------------------------------------------------------------");
	    		print("Thank you for viewing this example");
	        }
	        
		    if ("7".equals(choice)) {
		        	System.out.println("Goodbye!");  
		        choice = null;
		        endProgram();
		    }
		    System.out.println();
	    }
	    
	    input.close();
		
	}
	
	private static void endProgram() {
		System.exit(0);
	}
		

}
