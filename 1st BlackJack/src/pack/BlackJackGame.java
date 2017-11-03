package pack;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.*;

public class BlackJackGame{

    private List<Card> cardsInDeck;
    private static List<Card> playerHand;
    private static int sumaPlayerhand;
    private static List<Card> dealerHand;
    private static int sumaDealerHand;
    private static int playerMoney;
    boolean gameOn;
    Random generator = new Random();
    boolean gameEnd;
    StringBuffer message;
    int currentBet;
    int counter;

    class Card{
        public int value;
        public String name;
        Card(int value, String name){
            this.value = value;
            this.name = name;
        }
    }

    public BlackJackGame() {
        currentBet = 10;
    }

    public void prepareNewGame(){
        cardsInDeck = new ArrayList<>();
        playerHand = new ArrayList<>();
        dealerHand = new ArrayList<>();
        sumaDealerHand = 0;
        sumaPlayerhand = 0;
        gameEnd = false;
        cardsInDeck.add(new Card(1, "Jedynka Kier"));
        cardsInDeck.add(new Card(1, "Jedynka Trefl"));
        cardsInDeck.add(new Card(2, "Dwójka Kier"));
        cardsInDeck.add(new Card(2, "Dwojka Trefl"));
        cardsInDeck.add(new Card(3, "Trójka Kier"));
        cardsInDeck.add(new Card(3, "Trojka Trefl"));
        cardsInDeck.add(new Card(4, "Czwórka Kier"));
        cardsInDeck.add(new Card(4, "Czworka Trefl"));
        cardsInDeck.add(new Card(5, "Piątka Kier"));
        cardsInDeck.add(new Card(5, "Piatka Trefl"));
        cardsInDeck.add(new Card(6, "Szóstka Kier"));
        cardsInDeck.add(new Card(6, "Szostka Trefl"));
        cardsInDeck.add(new Card(7, "Siódemka Kier"));
        cardsInDeck.add(new Card(7, "Siodemka Trefl"));
        cardsInDeck.add(new Card(8, "Ósemka Kier"));
        cardsInDeck.add(new Card(8, "Osemka Trefl"));
        cardsInDeck.add(new Card(9, "Dziewiątka Kier"));
        cardsInDeck.add(new Card(9, "Dziewiątka Trelf"));
        cardsInDeck.add(new Card(10, "Dziesiątka Kier"));
        cardsInDeck.add(new Card(10, "Dziesiątka Trefl"));
        cardsInDeck.add(new Card(10, "Walet Kier"));
        cardsInDeck.add(new Card(10, "Walet Trefl"));
        cardsInDeck.add(new Card(10, "Dama Kier"));
        cardsInDeck.add(new Card(10, "Dama Trefl"));
        cardsInDeck.add(new Card(10, "Król Kier"));
        cardsInDeck.add(new Card(10, "Król Trefl"));
        cardsInDeck.add(new Card(11, "As Kier"));
        cardsInDeck.add(new Card(11, "As Trefl"));
        cardsInDeck.add(new Card(1, "Jedynka Karo"));
        cardsInDeck.add(new Card(1, "Jedynka Pik"));
        cardsInDeck.add(new Card(2, "Dwójka Karo"));
        cardsInDeck.add(new Card(2, "Dwojka Pik"));
        cardsInDeck.add(new Card(3, "Trójka Karo"));
        cardsInDeck.add(new Card(3, "Trojka Pik"));
        cardsInDeck.add(new Card(4, "Czwórka Karo"));
        cardsInDeck.add(new Card(4, "Czworka Pik"));
        cardsInDeck.add(new Card(5, "Piątka Karo"));
        cardsInDeck.add(new Card(5, "Piatka Pik"));
        cardsInDeck.add(new Card(6, "Szóstka Karo"));
        cardsInDeck.add(new Card(6, "Szostka Pik"));
        cardsInDeck.add(new Card(7, "Siódemka Karo"));
        cardsInDeck.add(new Card(7, "Siodemka Pik"));
        cardsInDeck.add(new Card(8, "Ósemka Karo"));
        cardsInDeck.add(new Card(8, "Osemka Pik"));
        cardsInDeck.add(new Card(9, "Dziewiątka Karo"));
        cardsInDeck.add(new Card(9, "Dziewiatka Pik"));
        cardsInDeck.add(new Card(10, "Dziesiątka Karo"));
        cardsInDeck.add(new Card(10, "Dziesiatka Pik"));
        cardsInDeck.add(new Card(10, "Walet Karo"));
        cardsInDeck.add(new Card(10, "Walet Pik"));
        cardsInDeck.add(new Card(10, "Dama Karo"));
        cardsInDeck.add(new Card(10, "Dama Pik"));
        cardsInDeck.add(new Card(10, "Król Karo"));
        cardsInDeck.add(new Card(10, "Krol Pik"));
        cardsInDeck.add(new Card(11, "As Karo"));
        cardsInDeck.add(new Card(11, "As Pik"));
        giveDealerCard();
        giveDealerCard();
        givePlayerCard();
        shoutDealerHand();
        shoutPlayerHand();
    }


    public void startNewGame(){
        gameOn = true;
        playerMoney = 200;
        int choice = 0;
        displayHameBoard();
        emptyMessage();
        while (gameOn){
            choice = input();
            switch(choice){
                case 1:
                    givePlayerCard();
                    displayHameBoard();
                    if (!checkWinner()){
                        break;
                    }
                    break;
                case 2:
                    if (sumaDealerHand < sumaPlayerhand){
                        giveDealerCard();
                        displayHameBoard();
                        System.out.println("|--------------------------------------------------------|");
                        System.out.println("|                                                        |");
                        System.out.println("|        TWOJA SUMA KARD NA RECE JEST BLIŻEJ 21.         |");
                        System.out.println("|                DEALER DOCIAGA KARTE.                   |");
                        System.out.println("|                                                        |");
                        System.out.println("|--------------------------------------------------------|");
                        try {
                            Thread.sleep(1000);
                        } catch (Exception e){
                        }
                        displayHameBoard();
                        if (!checkWinner()){
                            break;
                        }
                    } else {
                        displayHameBoard();
                        System.out.println("|--------------------------------------------------------|");
                        System.out.println("|                                                        |");
                        System.out.println("|        DEALER PATRZY SIĘ NA CIEBIE WYCZEKUJĄCO.        |");
                        System.out.println("|                 DEALER DOCIAGA NOSEM.                  |");
                        System.out.println("|                                                        |");
                        System.out.println("|--------------------------------------------------------|");
                    }
                    break;
                case 3:
                    if (playerMoney >= currentBet * 2){
                        currentBet *= 2;
                    }
                    displayHameBoard();
                    break;
                case 4:
                    playerMoney -= currentBet;
                    if (playerMoney <= 0){
                        sprawdzSaldo();
                        break;
                    }
                    prepareNewGame();
                    displayHameBoard();
                    emptyMessage();
                    break;
                case 5:
                    prepareNewGame();
                    playerMoney = 200;
                    startNewGame();
                    break;
                default:
                    break;
            }
        }
    }

    public static int input(){
        LineNumberReader playerInput = new LineNumberReader(new InputStreamReader(System.in));
        String input = "";
        int i = 0;
        try {
            input = playerInput.readLine();
        } catch (IOException e) {
            System.err.println("|--------------------------------------------------------|");
            System.err.println("|                 Error taking input...                  |");
            System.err.println("|--------------------------------------------------------|");
        }

        try {
            i = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.err.println("|--------------------------------------------------------|");
            System.err.println("|              Error input is not a number...            |");
            System.err.println("|--------------------------------------------------------|");
        }
        return i;
    }

    public void displayHameBoard(){
        try {
            Runtime.getRuntime().exec("cls");
        } catch (IOException e){
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        }
            System.out.println("|--------------------------------------------------------|");
            System.out.println("|                      BLACK JACK                        |");
            System.out.println("|------------------------ Graj --------------------------|");
            System.out.println("|                                                        |");
            System.out.println("|    1)Dobierz 2)Czekaj 3)Podbij 4)Poddaj 5)Nowa Gra     |");
            System.out.println("|                                                        |");
            System.out.println("|--------------------------------------------------------|");
            shoutPlayerMoney();
            shoutPlayerBet();
            shoutPlayerHand();
            shoutDealerHand();
            System.out.println("|--------------------------------------------------------|");
    }

    public boolean checkWinner(){
        if (sumaPlayerhand == 21 || (sumaDealerHand > 21 && sumaPlayerhand < 21)) {
            if (!gameEnd) {
                gameEnd = true;
                playerMoney += currentBet;
            }
            System.out.println("                                                          ");
            System.out.println("                  ZWYCIĘSTWO GRACZA                       ");
            System.out.println("                     WYGRALES " + currentBet + " $        ");
            System.out.println("                                                          ");
            System.out.println("|--------------------------------------------------------|");
            return sprawdzSaldo();
        }

        if ((sumaPlayerhand != 21 && sumaDealerHand == 21) || (sumaPlayerhand > 21)){
            if (!gameEnd){
                gameEnd = true;
                playerMoney -= currentBet;
            }
            System.out.println("                                                          ");
            System.out.println("                  ZWYCIĘSTWO KRUPIERA                     ");
            System.out.println("                     PRZEGRALES " + currentBet + " $      ");
            System.out.println("                                                          ");
            System.out.println("                                                 4)DALEJ  ");
            System.out.println("|--------------------------------------------------------|");
            return sprawdzSaldo();
        }

            System.out.println("|            Your Hand: " + sumaPlayerhand + " | Dealer's Hand: " + sumaDealerHand);
            System.out.println("|                                                        |");
            System.out.println("|--------------------------------------------------------|");
            return sprawdzSaldo();
    }

    public boolean sprawdzSaldo(){
        if (playerMoney <= 0){
            displayHameBoard();
            System.err.println("|--------------------------------------------------------|");
            System.err.println("|                 Gracz przegrywa.                       |");
            System.err.println("|              Bank zgarnia wszystko.                    |");
            System.err.println("|--------------------------------------------------------|");
            gameOn = false;
            return false;
        }
        return true;
    }

    public void emptyMessage(){
            System.out.println("|                                                        |");
            System.out.println("|                                                        |");
            System.out.println("|                                                        |");
            System.out.println("|                                                        |");
            System.out.println("|--------------------------------------------------------|");
    }

    private String messageBuilder(String start, List<Card> arrayList) {
        message = new StringBuffer();
        counter = 0;
        message.append(start);
        for (Card a : arrayList) {
            message.append(a.name).append(". ");
            counter += a.value;
        }
        return message.toString();
    }

    public void shoutDealerHand() {
        System.out.println(messageBuilder("   Dealer Hand: ", dealerHand));
    }

    public void shoutPlayerHand() {
        System.out.println(messageBuilder("   Your Hand: ", playerHand));
    }

    public void shoutPlayerMoney() {
        System.out.println("   Your money $" + playerMoney);
    }

    public void shoutPlayerBet() {
        System.out.println("   Current bet - " + currentBet + " $");
    }

    public Card takeCard() {
        Integer a = generator.nextInt(cardsInDeck.size());
        Card cardInHand = cardsInDeck.get(a);
        cardsInDeck.remove(cardInHand);
        return cardInHand;
    }

    public void giveDealerCard() {
        dealerHand.add(takeCard());
        sumaDealerHand = 0;
        for (Card c : dealerHand) {
            sumaDealerHand += c.value;
        }
    }

    public void givePlayerCard() {
        playerHand.add(takeCard());
        sumaPlayerhand = 0;
        for (Card c : playerHand) {
            if (c.value == 11) {
                sumaPlayerhand += (sumaPlayerhand + 11 <= 21) ? 11 : 1;
            } else {
                sumaPlayerhand += c.value;
            }
        }
    }
}