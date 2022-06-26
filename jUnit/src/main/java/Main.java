public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();

        MarketApi marketApi = new MarketApi();
        DollarCalculator dollarCalculator = new DollarCalculator(marketApi);
        dollarCalculator.init();

        calculator.init(dollarCalculator);
        System.out.println(calculator.sum(10,20));
    }
}
