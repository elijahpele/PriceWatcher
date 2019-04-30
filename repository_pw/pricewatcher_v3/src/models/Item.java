package models;

import java.util.Random;

import controllers.PriceFinder;



public class Item {
    private String name; // = "Beats Studio 3 Wireless Over-Ear Headphones";
    private String url;// = "https://www.amazon.com/Beats-Solo3-Wireless-Ear-Headphones/dp/B01LWWY3E2/ref=sr_1_1_sspa?s=aht&ie=UTF8&qid=1549336080&sr=1-1-spons&keywords=beats+wireless+headphones&psc=1";
    private String dateAdded;// = "06/01/2018";
    private double priceChange;// = 0;
    private double startingPrice;// = 279.95;
    private double previousPrice;
    private double currentPrice; // = 279.95;
    
    public Item() {};
    
    public Item(String name, String url, String dateAdded, double priceChange, double startingPrice, double previousPrice, double currentPrice) {
    	this.name = name;
    	this.url = url;
    	this.dateAdded = dateAdded;
    	this.priceChange = priceChange;
    	this.startingPrice = startingPrice;
    	this.currentPrice = currentPrice;
    }
    
    public Item(String name, String url) {
        Random random = new Random();
        double simulatedStartingPrice = 250.00 + (350.00 - 250.00) * random.nextDouble(); //Later, this will come from an actual website.
        simulatedStartingPrice = Math.round(simulatedStartingPrice * 100.0) / 100.0;
        this.startingPrice = simulatedStartingPrice;
        this.previousPrice = simulatedStartingPrice - 50;
        this.currentPrice  = simulatedStartingPrice + 25;
        this.name = name;
        this.url = url;
        this.dateAdded = "(04/27/2019)";
    }

    public String getName(){
        return this.name;
    }
    public String getURL(){
        return this.url;
    }

    public String printURL(){
    	if(url.length() >= 40) {
    		String url = this.url.substring(0,40)+"...";
    	}
        return url;
    }

    public String getDateAdded(){
        return this.dateAdded;
    }

    public String getStartingPrice(){
        String startPrice = Double.toString(this.startingPrice);
        return "($"+startPrice +")";
    }

    public String getPriceChange(){
        String change = Double.toString(this.priceChange);
        return change +"%";
    }

    public double getPreviousPrice() {
        return this.previousPrice;
    }

    public double getCurrentPrice(){
        if(this.currentPrice == 0)
            return this.startingPrice;
        return this.currentPrice;
    }

    public String printCurrentPrice(){
        String currPrice = Double.toString(this.currentPrice);
        currPrice = "$" +currPrice;
        return currPrice;
    }

    public double checkLivePrice(){
        PriceFinder priceFinder = new PriceFinder();
        double newPrice = priceFinder.livePrice(this.url);
        this.previousPrice = currentPrice;
        this.currentPrice = newPrice;
        setPriceChange();
        return this.currentPrice;
    }

    public void setPriceChange(){
        
        this.priceChange = ((currentPrice - previousPrice)/previousPrice) *100;
        this.priceChange = Math.round(priceChange*100.0)/100.0;
    }
    public void setName(String s) {
    	this.name = s;
    }
    public void setURL(String u) {
    	this.url = u;
    }
}
