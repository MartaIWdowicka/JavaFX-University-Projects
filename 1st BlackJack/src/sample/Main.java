package sample;

public class Main {

    private static BlackJackGame backjack = new BlackJackGame();

    public static void main(String[] args) {
        backjack.prepareNewGame();
        backjack.startNewGame();
    }
}