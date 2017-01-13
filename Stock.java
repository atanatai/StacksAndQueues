//Kai Rahm
//CS3240

public class Stock {
	private String name;
	private int shares;
	private double price;
	
	public Stock(String name, int shares, double purchasePrice){
		this.name = name;
		this.shares = shares;
		this.price= purchasePrice;
	}
	public void setName(String newName){
		name = newName;
	}
	public String getName(){
		return name;
	}
	public void setShares(int newShares){
		shares = newShares;
	}
	public int getShares(){
		return shares;
	}
	public void setPrice(double newPrice){
		price = newPrice;
	}
	public double getPrice(){
		return price;
	}
	public double findBoughtPrice(){
		return (shares*price);
	}
	public double findPartialBoughtPrice(int shareAmount){
		if(shareAmount<=shares && shareAmount>=0){
			return (shareAmount*price);
		}
		return 0;
	}
	public double findSalePrice(double currentPrice){
		return (shares*currentPrice);
	}
	public double findPartialSalePrice(double currentPrice, int shareAmount){
		while(shareAmount<=shares && shareAmount>=0){
			return (shareAmount*currentPrice);
		}
		return 0;
	}
	public double findSaleDifference(double currentPrice){
		double priceDifference = this.findSalePrice(currentPrice)-this.findBoughtPrice(); 
		return priceDifference;
	}
	public double findPartialSaleDifference(double currentPrice, int shareAmount){
		while(shareAmount<=shares && shareAmount>=0){
			return (this.findPartialSalePrice(currentPrice, shareAmount)-this.findPartialBoughtPrice(shareAmount));
		}
		return 0;
	}
	public String toString(){
		return(String.format("%-5s",name)+String.format("%6s", shares)+" "+String.format("%8s", "$"+price));
	}
	
	
}
