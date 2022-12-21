import java.awt.Component;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Hand {
	private ArrayList<ArrayList<Card>> cards;
	private ArrayList<Integer> score;
	private ArrayList<Boolean> bust;
	private ArrayList<Boolean> stand;
	private int wins;
	private boolean isDealer;
	private boolean flip;
	private boolean split;
	private boolean doubleDown;
	private int balance;

	private int splits;
	public Hand() {
		cards = new ArrayList<ArrayList<Card>>();
		cards.add(new ArrayList<Card>());
		score = new ArrayList<Integer>();
		score.add(0);
		bust = new ArrayList<>();
		stand = new ArrayList<>();
		bust.add(false);
		stand.add(false);
		wins = 0;
		isDealer = false;
		flip = false;
		split = false;
		doubleDown = false;
		splits = 0;
		balance = 1000;
	}
	public Hand(boolean isDealer) {
		cards = new ArrayList<ArrayList<Card>>();
		cards.add(new ArrayList<Card>());
		score = new ArrayList<Integer>();
		score.add(0);
		bust = new ArrayList<>();
		stand = new ArrayList<>();
		bust.add(false);
		stand.add(false);
		wins = 0;
		this.isDealer = isDealer;
		flip = false;
		doubleDown = false;
		balance = 0;
	}
	public void hit(Deck deck) {
		int i = 0;
		
		while(i < stand.size() && i < bust.size() && (stand.get(i) || bust.get(i))) {
			i++;
		}
		
		if(!bust.get(i) && !stand.get(i)) {
			cards.get(i).add(deck.drawTop());
			score.set(i,score.get(i) + cards.get(i).get(cards.get(i).size()-1).getValue());
			bust();
		}

	}

	public void split() {
		for(int i = 0; i<= splits; i++) {
			if(isSplittable()) {
				split = true;
				splits++;
				cards.add(new ArrayList<Card>());
				cards.get(cards.size()-1).add(cards.get(i).remove(1));
				score.add(cards.get(i).get(0).getValue());
				score.set(i, score.get(i)/2);
				stand.add(false);
				bust.add(false);
			}
		}
	}
	public boolean isSplittable() {
		return cardCount() == 2 && cards.get(0).get(0).getValue() == cards.get(0).get(1).getValue();
	}
	public void flip() {
		flip = true;
	}
	public String toString() {
		String returnString =  "";
		for(ArrayList<Card> c1 : cards)
			for(Card c : c1) {
				returnString+= c.toString() + " ";
			}
		return returnString;
	}
	public void bust() {
		for(int i = 0; i<=splits; i++) {
			if(score.get(i) > 21) {
				for(int j = 0; j < cards.get(i).size() && score.get(i) > 21; j++) {
					if(cards.get(i).get(j).checkAce())
						score.set(i, score.get(0)-10);
				}
			}
			if(score.get(i) > 21) {
				bust.set(i, true);
			}
		}



	}
	public int getScore(int i) {
		return score.get(i);
	}
	public boolean didBust(int i) {
		return bust.get(i);
	}
	public int getScore() {
		return score.get(0);
	}
	public boolean didBust() {
		return bust.get(0);
	}
	public boolean isStanding() {

		for(int i = 0; i<=splits; i++)
			if(!stand.get(i))
				return false;

		return true;
	}
	public void stand() {
		int i = 0;
		while (i<stand.size() && stand.get(i)) {
			i++;
		}
		stand.set(i, true);
	}
	public boolean isSplit() {
		return split;
	}
	public boolean isBlackJack() {
		return cardCount() == 2 && cards.get(0).get(0).getValue() + cards.get(0).get(1).getValue() == 21;

	}

	public void didWin(Hand player) {
		for(int i = 0; i<=player.splits(); i++) {
			if(player.didBust(i)) 
				Won(player);
			else if(didBust())
				player.Won(player);
			else if(player.getScore(i) < getScore()) {
				Won(player);
			}
			else if(player.getScore(i) > getScore())
				player.Won(player);
			
		}
	}
	public void doubleDown(Deck deck) {
		doubleDown = true;
		cards.get(0).add(deck.drawTop());
		score.set(0,score.get(0) + cards.get(0).get(cards.get(0).size()-1).getValue());
		bust();
		stand();
		
	}
	public boolean isDoubleable() {
		return cardCount() == 2 && splits == 0;
	}
	public boolean isDoubleDown() {
		return doubleDown;
	}
	public boolean isFlipped() {
		return flip;
	}
	public void Won(Hand player) {
		
		
		if(player.isDoubleDown())
			wins+=2;
		else
			wins++;
	}
	
	public void draw(Component c, Graphics2D g2) {
		for(int i = 0; i <= splits; i++) {
			for(int j = 0; j < cards.get(i).size(); j++) {
				cards.get(i).get(j).draw(c,g2,j,isDealer,flip,i,stand.get(i),bust.get(i));
			}
		}
	}
	public int splits() {
		return splits;
	}
	public void reset() {
		cards.clear();
		score.clear();
		score.add(0);
		bust.clear();
		stand.clear();
		bust.add(false);
		stand.add(false);
		flip = false;
		split = false;
		doubleDown = false;
		cards.add(new ArrayList<Card>());
		splits = 0;
	}
	public void autoHit(Deck deck) {
		if(score.get(0)<17)
			hit(deck);
		else
			stand.set(0, true);
	}
	public int getWins() {
		return wins;
	}
	public void setWins(int wins) {
		this.wins = wins;
	}
	public int cardCount() {
		return cards.get(0).size();
	}
}
