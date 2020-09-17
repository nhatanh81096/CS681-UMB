package edu.umb.cs681.hw01;

public class Main {

    public static void main(String[] args) {
        StockQuoteObservable s = new StockQuoteObservable();

        s.addObserver((Observable o, Object obj) -> {
            String ticker = ((StockEvent) obj).getTicker();
            Float quote = ((StockEvent) obj).getQuote();
            System.out.println("Observer 1 - Stock event: " + ticker + " " + quote);
        });

        s.addObserver((Observable o, Object obj) -> {
            String ticker = ((StockEvent) obj).getTicker();
            Float quote = ((StockEvent) obj).getQuote();
            System.out.println("Observer 2 - Stock event: " + ticker + " " + quote);
        });

        System.out.println("Number of observers: " + s.countObservers());

        String MSFTCode = "MSFT";
        Float MSFTValue = 10.0f;

        System.out.println("Add new Stock: " + MSFTCode);
        s.setQuote(MSFTCode, MSFTValue);
        s.notifyObservers(new StockEvent(MSFTCode, MSFTValue));

        MSFTValue = 20.0f;
        System.out.println("MSFT changed");
        s.changeQuote(MSFTCode, MSFTValue);
        s.notifyObservers(new StockEvent(MSFTCode, MSFTValue));
    }
}
