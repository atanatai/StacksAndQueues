//Kai Rahm
//CS3240

import java.util.*;

/**
 * Uses the Linked class to demonstrate pop and push with stacks/queues with a simple stock management app.
 * @author Kai
 *
 */
public class StockDatabase {
	private Stock[] stockList = new Stock[0];
	private ArrayList<Stock> stockArray;
	private Linked<Stock> stockQueue;
	
	
	public StockDatabase(){
	}
	public StockDatabase(Stock[] stockList){
		this.stockList = stockList;
	}
	public StockDatabase(Stock stock){
		addStock(stock);
	}
	public void addStock(Stock newStock){
		//System.out.println("Adding Stock:");
		Stock[] tempList = new Stock[(stockList.length+1)];
		for(int i=0; i<stockList.length; i++){
			tempList[i] = stockList[i];
			//System.out.println(tempList[i]);
		}
		tempList[stockList.length] = newStock;
		System.out.println("Adding Stock: "+tempList[stockList.length]+"\n");
		this.stockList = tempList;
	}
	public void printStocks(){
		stockArray = this.LIFOStack(); //makes sure list is updated with correct stocks
		if(stockList.length == 0){
			System.out.println("There are no stocks currently owned.");
		} else{
		System.out.println(String.format("%-8s", "Name")+String.format("%-8s", "Shares")+"Purchase Price");
		for(Stock s : stockList){
			System.out.println(s);
		}
		}
		System.out.println();
	}
	public int stocksLength(){
		return(stockList.length);
	}
// LIFO Code	
	public ArrayList<Stock> LIFOStack(){
		ArrayList<Stock> tempArray = new ArrayList<Stock>();
		for(int i=0; i<stockList.length; i++){
			tempArray.add(stockList[i]);
		}
		//stockArray = tempArray; //this could automatically assign the created array to class' stockArray...
		return tempArray;
	}
	public double findLIFOPrice(String stockName, int sharesNeeded, double currentStockPrice){
		System.out.println("Checking Stock prices using LIFO (stack):");
		stockArray = this.LIFOStack();
		int shareCounter=0;
		double sharePrice=0;
		double shareLoss=0;
		double boughtPrice=0;
		for(int i=(stockArray.size()-1); i>=0; i--){
			if(stockArray.get(i).getName().equalsIgnoreCase(stockName) && shareCounter<=sharesNeeded){
				int remainingShares = sharesNeeded-shareCounter;
					if(stockArray.get(i).getShares() <= remainingShares && remainingShares !=0){
						sharePrice += stockArray.get(i).findSalePrice(currentStockPrice);
						shareLoss += stockArray.get(i).findSaleDifference(currentStockPrice);
						shareCounter += stockArray.get(i).getShares();
						boughtPrice += stockArray.get(i).findBoughtPrice();
				} else if(stockArray.get(i).getShares() > remainingShares && remainingShares !=0){
					sharePrice += stockArray.get(i).findPartialSalePrice(currentStockPrice, remainingShares);
					shareLoss += stockArray.get(i).findPartialSaleDifference(currentStockPrice, remainingShares);
					shareCounter += remainingShares; 
					boughtPrice += stockArray.get(i).findPartialBoughtPrice(remainingShares);
				}
			}
		}
		String word = " profit ";
		if(shareLoss < 0){
			word = " loss ";
			shareLoss = shareLoss*(-1);
		}
		System.out.println(shareCounter+" shares of "+stockName+" at $"+currentStockPrice+" a share can be sold for a total of: $"+sharePrice);
		System.out.println("These shares were originally bought for: $"+boughtPrice);
		System.out.println("Buying these shares at this price would produce a"+word+"of $"+shareLoss+"\n");
		return sharePrice;
	}
	public ArrayList<Stock> buyLIFOStocks(String stockName, int sharesNeeded, double currentStockPrice){
		System.out.println("Buying Stock using LIFO (stack):");
		stockArray = this.LIFOStack();
		int shareCounter=0;
		double sharePrice=0;
		double shareLoss=0;
		double boughtPrice=0;
		for(int i=(stockArray.size()-1); i>=0; i--){
			if(stockArray.get(i).getName().equalsIgnoreCase(stockName) && shareCounter<=sharesNeeded){
				int remainingShares = sharesNeeded-shareCounter;
				if(stockArray.get(i).getShares() <= remainingShares){
					sharePrice += stockArray.get(i).findSalePrice(currentStockPrice);
					shareLoss += stockArray.get(i).findSaleDifference(currentStockPrice);
					shareCounter += stockArray.get(i).getShares();
					boughtPrice += stockArray.get(i).findBoughtPrice();
					System.out.println(stockArray.get(i).getShares()+" shares of "+stockName+" are being sold for: $"+stockArray.get(i).findSalePrice(currentStockPrice));
					stockArray.remove(i);
				} else if(stockArray.get(i).getShares() > remainingShares && remainingShares !=0){
					sharePrice += stockArray.get(i).findPartialSalePrice(currentStockPrice, remainingShares);
					shareLoss += stockArray.get(i).findPartialSaleDifference(currentStockPrice, remainingShares);
					shareCounter += remainingShares;
					boughtPrice += stockArray.get(i).findPartialBoughtPrice(remainingShares);
					System.out.println(remainingShares+" shares of "+stockName+" are being sold for: $"+stockArray.get(i).findPartialSalePrice(currentStockPrice, remainingShares));
					stockArray.get(i).setShares((stockArray.get(i).getShares()-remainingShares));
				}
			}
		}
		String word = " profit ";
		if(shareLoss < 0){
			word = " loss ";
			shareLoss = shareLoss*(-1);
		}
		System.out.println("LIFO Transaction Completed. Summary:");
		System.out.println(shareCounter+" shares of "+stockName+" at $"+currentStockPrice+" a share were sold for a total of: $"+sharePrice);
		System.out.println("These shares were originally bought for: $"+boughtPrice);
		System.out.println("Buying them at current price produced a"+word+"of $"+shareLoss+"\n");
		
		stockList = stockArray.toArray(new Stock[stockArray.size()]);
		return stockArray;
	}
//FIFO Code	
	public Linked<Stock> FIFOQueue(){
		Linked<Stock> tempLinkedList = new Linked<Stock>();
		for(int i=0; i<stockList.length; i++){
			tempLinkedList.insertLast(stockList[i]);
		}
		return tempLinkedList;
	}
	public double findFIFOPrice(String stockName, int sharesNeeded, double currentStockPrice){
		System.out.println("Checking Stock prices using FIFO (queue):");
		stockQueue = this.FIFOQueue(); 
		int shareCounter=0;
		double sharePrice=0;
		double shareLoss=0;
		double boughtPrice=0;
		for(int i=0; i<stockQueue.listCount(); i++){
			int remainingShares = sharesNeeded-shareCounter;
			if(stockQueue.returnData(i).getName().equalsIgnoreCase(stockName) && shareCounter<=sharesNeeded && remainingShares !=0){
				if(stockQueue.returnData(i).getShares() <= remainingShares ){
					sharePrice += stockQueue.returnData(i).findSalePrice(currentStockPrice);
					shareLoss += stockQueue.returnData(i).findSaleDifference(currentStockPrice);
					shareCounter += stockQueue.returnData(i).getShares();
					boughtPrice += stockQueue.returnData(i).findBoughtPrice();
				} else if(stockQueue.returnData(i).getShares() > remainingShares && remainingShares !=0){
					sharePrice += stockQueue.returnData(i).findPartialSalePrice(currentStockPrice, remainingShares);
					shareLoss += stockQueue.returnData(i).findPartialSaleDifference(currentStockPrice, remainingShares);
					shareCounter += remainingShares;
					boughtPrice += stockQueue.returnData(i).findPartialBoughtPrice(remainingShares);
				}
			}
		}  
		String word = " profit ";
		if(shareLoss < 0){
			word = " loss ";
			shareLoss = shareLoss*(-1);
		}
		System.out.println(shareCounter+" shares of "+stockName+" at $"+currentStockPrice+" a share can be sold for a total of: $"+sharePrice);
		System.out.println("These shares were originally bought for: $"+boughtPrice);
		System.out.println("Buying these shares at this price would produce a"+word+"of $"+shareLoss+"\n");
		return sharePrice;
	}
	public Linked<Stock> buyFIFOStocks(String stockName, int sharesNeeded, double currentStockPrice){
		System.out.println("Buying Stock using FIFO (queue):");
		stockQueue = this.FIFOQueue(); 
		int shareCounter=0;
		double sharePrice=0;
		double shareLoss=0;
		double boughtPrice=0;
		for(int i=0; i<stockQueue.listCount(); i++){
			if(stockQueue.returnData(i).getName().equalsIgnoreCase(stockName)){
				int remainingShares = sharesNeeded-shareCounter;
				if(stockQueue.returnData(i).getShares() <= remainingShares){
					sharePrice += stockQueue.returnData(i).findSalePrice(currentStockPrice);
					shareLoss += stockQueue.returnData(i).findSaleDifference(currentStockPrice);
					shareCounter += stockQueue.returnData(i).getShares();
					boughtPrice += stockQueue.returnData(i).findBoughtPrice();
					System.out.println(stockQueue.returnData(i).getShares()+" shares of "+stockName+" are being sold for: $"+stockQueue.returnData(i).findSalePrice(currentStockPrice));
					stockQueue.deleteItem(i);
					i--; //to make up for deleting a node, the iteration must be set back one to not skip stocks
				} else if(stockQueue.returnData(i).getShares() > remainingShares && remainingShares !=0){
					sharePrice += stockQueue.returnData(i).findPartialSalePrice(currentStockPrice, remainingShares);
					shareLoss += stockQueue.returnData(i).findPartialSaleDifference(currentStockPrice, remainingShares);
					shareCounter += remainingShares;
					boughtPrice += stockQueue.returnData(i).findPartialBoughtPrice(remainingShares);
					System.out.println(remainingShares+" shares of "+stockName+" are being sold for: $"+stockQueue.returnData(i).findPartialSalePrice(currentStockPrice, remainingShares));
					stockQueue.returnData(i).setShares((stockQueue.returnData(i).getShares()-remainingShares));
				}
			}
		}
		String word = " profit ";
		if(shareLoss < 0){
			word = " loss ";
			shareLoss = shareLoss*(-1);
		}
		System.out.println("FIFO Transaction Completed. Summary:");
		System.out.println(shareCounter+" shares of "+stockName+" at $"+currentStockPrice+" a share were sold for a total of: $"+sharePrice);
		System.out.println("These shares were originally bought for: $"+boughtPrice);
		System.out.println("Buying them at current price produced a"+word+"of $"+shareLoss+"\n");
		
		stockList = new Stock[stockQueue.listCount()];
		for(int i=0; i < stockQueue.listCount(); i++){
		stockList[i] = stockQueue.returnData(i);
		}
		return stockQueue;
	}
}
