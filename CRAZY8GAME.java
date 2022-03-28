package cardgame;

import static cardgame.Card.getDeck;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import cardgame.Card.Rank;
import cardgame.Card.Suit;

public class GameEngine implements PlayerStrategy{

	private static List<Card> drawPile = getDeck();
	private static Card discardPile;
	static Map<PlayerTurn, List<Card>> allPlayersAndTheirCards = new LinkedHashMap<>();
	static int maxScore = 0;
	static boolean cheated = true;
	static boolean out = false;
	static boolean max = false;
	static List<Integer> playersScore;
	static Card.Suit declaredSuit = null;

	@Override
	public void init(int playerId, List<Integer> opponentIds) {
		PlayerTurn player = new PlayerTurn();
		player.playerId = playerId;
		List<Card> list = new ArrayList<>();
		for(int i = 0; i <  5; i++) {
			list.add(drawPile.get(0));
			drawPile.remove(0);
		}
		allPlayersAndTheirCards.put(player, list);

		for(int i = 0; i < opponentIds.size(); i++) {
			PlayerTurn tempPlayer = new PlayerTurn();
			tempPlayer.playerId = opponentIds.get(i);
			List<Card> tempList = new ArrayList<>();
			for(int j = 0; j <  5; j++) {
				tempList.add(drawPile.get(0));
				drawPile.remove(0);
			}
			allPlayersAndTheirCards.put(tempPlayer, tempList);
		}

		int totalPlayers = opponentIds.size()+1;
		playersScore = new ArrayList<Integer>();
		for(int i = 0; i < totalPlayers; i++) {
			playersScore.add(0);
		}

		discardPile = drawPile.get(0);
		drawPile.remove(0);
		}

		@Override
		public void receiveInitialCards(List<Card> cards) {
			System.out.println("Your current cards are :");
			for(Card card:cards) {
				System.out.println(card.getSuit() + " " + card.getRank());
			}
			System.out.println("----------------------------------");
		}


		@Override
		public boolean shouldDrawCard(Card topPileCard, Suit changedSuit) {
			if(changedSuit != null) {
				System.out.println("You Should choose  cards only of Suit : " + changedSuit);
			}
			else {
				System.out.println("Top Pile card is : " + topPileCard.getSuit() + " " + topPileCard.getRank());
			}
			Scanner sc = new Scanner(System.in);
			System.out.println("Should you need to draw the card?");
			System.out.println("Enter 1 for YES or 0 for NO");
			int choice = sc.nextInt();
			if(choice == 1) {
				return true;
			}
			else {
				return false;
			}
		}


		@Override
		public void receiveCard(Card drawnCard) {
			drawPile.remove(0);
		}

		@Override
		public Card playCard() {
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter the Suit of card you want to put in discard pile:");
			String suit = sc.nextLine();
			System.out.println("Enter the Rank of card you want to put in discard pile:");
			String rank = sc.nextLine();
			Card card = new Card(Suit.valueOf(suit), Rank.valueOf(rank));
			return card;
		}


		@Override
		public Suit declareSuit() {
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter the Suit of card you want to declare for the next player to choose:");
			String suit = sc.nextLine();
			return Suit.valueOf(suit);
		}


		@Override
		public void processOpponentActions(List<PlayerTurn> opponentActions) {
		// TODO Auto-generated method stub

		}

		@Override
		public void reset() {
			drawPile = getDeck();
			Collections.shuffle(drawPile);
			for(Map.Entry<PlayerTurn, List<Card>> m: allPlayersAndTheirCards.entrySet()) {
				List<Card> tempList = new ArrayList<>();
				for(int j = 0; j <  5; j++) {
					tempList.add(drawPile.get(0));
					drawPile.remove(0);
				}
				allPlayersAndTheirCards.put(m.getKey(), tempList);
			}
			discardPile = drawPile.get(0);
			drawPile.remove(0);
		}
		
		public boolean isPresentToDraw(List<Card> currentPlayerCards) {
			for(int i = 0; i < currentPlayerCards.size(); i++) {
				if(currentPlayerCards.get(i).getSuit() == discardPile.getSuit() || currentPlayerCards.get(i).getRank() == discardPile.getRank() || currentPlayerCards.get(i).getRank() == Rank.EIGHT) {
					return true;
				}
			}
			return false;
		}

		public int getCurrentPlayerScore(List<Card> currentPlayerCards) {
			if(currentPlayerCards.isEmpty()) {
				return 0;
			}
			int sum = 0;
			for(Card card: currentPlayerCards) {
				sum += card.getPointValue();
			}
			return sum;
		}

		public boolean playCardPresentWithPlayer(Card playCard, List<Card>  currentPlayerCards) {
			for(int i = 0; i < currentPlayerCards.size(); i++) {
				if(currentPlayerCards.get(i).getSuit() == playCard.getSuit() && currentPlayerCards.get(i).getRank() == playCard.getRank()) {
					return true;
				}
			}
			return false;
		}

		public static void main(String[] args) {

		//Shuffling deck
		Collections.shuffle(drawPile);

		//creating a instance of GameEngine and creating instances for players with allocating their cards
		GameEngine gameEngine = new GameEngine();
		gameEngine.init(1, new ArrayList<Integer>(Arrays.asList(2))); //UnComment for 2 players
		// gameEngine.init(1, new ArrayList<Integer>(Arrays.asList(2,3,4))); //UnComment for 4 players
		// gameEngine.init(1, new ArrayList<Integer>(Arrays.asList(2,3,4,5))); //Uncomment for 5 players

		//running loop until anyone's score reaches 200 and above	
		while(maxScore < 200) {
			int k = 0;
			for(Map.Entry<PlayerTurn, List<Card>> m: allPlayersAndTheirCards.entrySet()) {
				System.out.println("----------------------------------");
				System.out.println("Player:" + m.getKey().playerId);
				gameEngine.receiveInitialCards(m.getValue());

				//asking whether to draw or skipping their move to opponent		
				if(gameEngine.shouldDrawCard(discardPile, declaredSuit)) {
					List<Card> currentPlayerCards = m.getValue();
					//checks whether card is available to draw: If no card present receive a new card from deck in else part
					if(gameEngine.isPresentToDraw(currentPlayerCards)) {
						Card playCard = gameEngine.playCard();
	
						//TO CHECK A CARD WITH SUIT AND RANK ENTERED IS PRESENT IN PLAYER
						if(gameEngine.playCardPresentWithPlayer(playCard, currentPlayerCards)) {
							//Looping towards all the cards of current player and finding the required index of card
							for(int i = 0; i < currentPlayerCards.size(); i++) {
								if(currentPlayerCards.get(i).getSuit() == playCard.getSuit() && (playCard.getSuit()==discardPile.getSuit() || playCard.getRank()==discardPile.getRank() || playCard.getRank()==Rank.EIGHT)) {
									if(declaredSuit != null) {
										declaredSuit = null;
									}
									if(playCard.getRank() == Rank.EIGHT) { 
									//Checking EIGHT for declaring suit
										declaredSuit = gameEngine.declareSuit();
										Card card = new Card(declaredSuit, discardPile.getRank());
										discardPile = card;
									}else {
										discardPile = playCard;
										currentPlayerCards.remove(i);
									}
									cheated = false;
									break;
								}
							}		
						}
						else {
							System.out.println("Entered Card is not present with player!");
							cheated = true;
							out = true;
							break;
						}
						if(cheated == true) {
							out = true;
							System.out.println("Chosen Card cannot be played!");
							break;
						}
					}
					else {
						System.out.println("No cards available to play from your deck. So recieving card from the deck..........");
						Card recieveCard = drawPile.get(0);
						gameEngine.receiveCard(recieveCard);
						currentPlayerCards.add(recieveCard);
						System.out.println("Recieved Card is: " + recieveCard.getSuit() + " " + recieveCard.getRank());
					}
				}
				int currentPlayerScore = gameEngine.getCurrentPlayerScore(m.getValue());
				if(currentPlayerScore+ playersScore.get(k) > maxScore) {
					maxScore = currentPlayerScore+ playersScore.get(k);
				}
				if(maxScore >= 200) {
					max = true;
					break;
				}

				//If any of the player's card is empty storing the scores of that round in playersScore list and resetting the cards
				if(currentPlayerScore == 0) {	
					int j = 0;
					for(Map.Entry<PlayerTurn, List<Card>> x: allPlayersAndTheirCards.entrySet()) {
						playersScore.add(j, (playersScore.get(j) + gameEngine.getCurrentPlayerScore(x.getValue())));
						j++;
					}
					gameEngine.reset();
				}
				k++;
			}
			if(out == true || max == true) {
				break;
			}
		}
		if(out == true) {
			System.out.println("Cheated! Game Over!");
		}
		else if(max == true){
			System.out.println("Game reached above 200");
			int minimumScore = Collections.min(playersScore);
			int minIndex = playersScore.indexOf(minimumScore);
			int i = 0;
			for(Map.Entry<PlayerTurn, List<Card>> m: allPlayersAndTheirCards.entrySet()) {
				if(i == minIndex) {
					System.out.println("The winner is Player : " + m.getKey().playerId);
				}
				i++;
			}
		}
	}
}
