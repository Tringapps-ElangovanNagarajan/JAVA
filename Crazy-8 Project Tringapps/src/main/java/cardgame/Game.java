package cardgame;

import java.util.*;
import cardgame.Card.*;

public class Game {
		
	public static void main(String[] args) {		
		Game game=new Game();
		PlayerOne player1=new PlayerOne();
		PlayerTwo player2=new PlayerTwo();
		List<Card> deck=new ArrayList<>();
		deck=Card.getDeck();
		Collections.shuffle(deck);
		deck=game.start(deck,player1,player2);
		game.play(deck,player1,player2);
		
	}

	List<Card> start(List<Card> deck, PlayerOne player1, PlayerTwo player2) {
		int i;
		List<Card> player1=new ArrayList<>();
		List<Card> player2=new ArrayList<>();
		for(i=0;i<14;i++) {
			if(i%2==0)
				player2.add(deck.get(0));
			else 
				player1.add(deck.get(0));
			deck.remove(0);			
		}
		logger.log();
		logger.log(" First Player Cards : ");
		for(i=0;i<player1.size();i++) {
			System.out.print(player1.get(i).getRank()+" "+player1.get(i).getSuit()+" ");
		}
		logger.log(" Second Player Cards : ");
		for(i=0;i<player2.size();i++) {
			System.out.print(player2.get(i).getRank()+" "+player2.get(i).getSuit()+" ");
		}
		logger.log();
		player1.receiveInitialCards(player1);
		player2.receiveInitialCards(player2);
		return deck;
	}
	
	void play(List<Card> deck, PlayerOne player1, PlayerTwo player2) {
		Game game=new Game();
		int point1=0,i,point2=0;
		Card topCard;
		topCard=deck.get(0);
		deck.remove(0);
		logger.log("TopCard : "+topCard.getRank()+" "+topCard.getSuit());
		Card.Suit decCard=null;
		while(point1<200 && point2<200) 
		{
			for(i=0;i<3;i++) {
				if(play2.shouldDrawCard(topCard, decCard)) {
					if(deck.size()!=0) {
						play2.receiveCard(deck.get(0));		
						deck.remove(0);			
					}
				}
				else {
					topCard=play2.playCard();
					System.out.println("TopCard : "+topCard.getRank()+" "+topCard.getSuit());
					if(topCard.getRank()==Rank.EIGHT && play2.myCards.size()!=0) {
						decCard=play2.declareSuit();
					}
					break;
				}
			}
			for(i=0;i<3;i++) {
				if(player1.shouldDrawCard(topCard, decCard)) {
					if(deck.size()!=0) {
						player1.receiveCard(deck.get(0));	
						deck.remove(0);
						
					}
				}
				else {
					topCard=player1.playCard();
					System.out.println("TopCard : "+topCard.getRank()+" "+topCard.getSuit());
					if(topCard.getRank().equals(Rank.EIGHT) && player1.myCards.size()!=0) {
						decCard=player1.declareSuit();
					}
					break;
				}
			}
			if(player1.myCards.size()==0 || deck.size()==0) {
				point2+=player2.getScore();
				logger.log("player2 :"+point2);
			}
			if(player2.myCards.size()==0 || deck.size()==0) {
				point1+=player1.getScore();
				logger.log("player1 :"+point1);
			}
			if(deck.size()==0 && point1<200 && point2<200) {
				deck=Card.getDeck();
				Collections.shuffle(deck);
				deck=game.start(deck,player1,player2);
			}
		}
		game.results(point1,point2);
	}
	
	void results(int p1,int p2) {
		if(p1 >= 200) {
			logger.log("Player2 wins");
		}
		else if(p2 >= 200) {
			logger.log("Player1 wins");
		}
	}

}
