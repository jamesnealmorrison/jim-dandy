package com.jimmie.domain.items;

import java.io.Serializable;

import com.jimmie.domain.NotEnoughCurrencyException;

public class Coins implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int copperPieces = 0;
	protected int silverPieces = 0;
	protected int goldPieces = 0;
	protected int platinumPieces = 0;
	protected int astralDiamonds = 0;
	public int getCopperPieces() {
		return copperPieces;
	}
	public void setCopperPieces(int copperPieces) {
		this.copperPieces = copperPieces;
	}
	public int getSilverPieces() {
		return silverPieces;
	}
	public void setSilverPieces(int silverPieces) {
		this.silverPieces = silverPieces;
	}
	public int getGoldPieces() {
		return goldPieces;
	}
	public void setGoldPieces(int goldPieces) {
		this.goldPieces = goldPieces;
	}
	public int getPlatinumPieces() {
		return platinumPieces;
	}
	public void setPlatinumPieces(int platinumPieces) {
		this.platinumPieces = platinumPieces;
	}
	public int getAstralDiamonds() {
		return astralDiamonds;
	}
	public void setAstralDiamonds(int astralDiamonds) {
		this.astralDiamonds = astralDiamonds;
	}
	public void addCopperPieces(int cp) {
		copperPieces += cp;
		// See if there are enough to trade to the next level up.
		int silverPieces = Math.floorDiv(copperPieces, 10);
		copperPieces = copperPieces % 10;
		if (silverPieces > 0) {
			addSilverPieces(silverPieces);
		}
	}
	public void addSilverPieces(int sp) {
		silverPieces += sp;
		// See if there are enough to trade to the next level up.
		int goldPieces = Math.floorDiv(silverPieces, 10);
		silverPieces = silverPieces % 10;
		if (goldPieces > 0) {
			addGoldPieces(goldPieces);
		}
	}
	public void addGoldPieces(int gp) {
		goldPieces += gp;
		// See if there are enough to trade to the next level up.
		int platinumPieces = Math.floorDiv(goldPieces, 100);
		goldPieces = goldPieces % 100;
		if (platinumPieces > 0) {
			addPlatinumPieces(platinumPieces);
		}
	}
	public void addPlatinumPieces(int pp) {
		platinumPieces += pp;
		// See if there are enough to trade to the next level up.
		int astralDiamonds = Math.floorDiv(platinumPieces, 100);
		platinumPieces = platinumPieces % 100;
		if (astralDiamonds > 0) {
			addAstralDiamonds(astralDiamonds);
		}
	}
	public void addAstralDiamonds(int ad) {
		astralDiamonds += ad;
	}
	
	public void spend(int i, CoinType coinType) throws NotEnoughCurrencyException {
		int cp = convertToCopperPieces(i,coinType);
		if (valueInCopperPieces() >= cp) {
			payCopperPieces(cp);
		}
		else {
			throw new NotEnoughCurrencyException();
		}
	}
	
	private void payCopperPieces(int cp) {
		if (copperPieces > cp) {
			copperPieces -= cp;
		} else {
			// If I need an exact amount of silver pieces, just borrow that exact amount, otherwise borrow one more.
			if (((cp-copperPieces) % 10) == 0) {
				copperPieces += borrowSilverPieces(Math.floorDiv((cp-copperPieces),10));
			} else {
				copperPieces += borrowSilverPieces(Math.floorDiv((cp-copperPieces),10)+1);
			}
			copperPieces -= cp;
		}
	}
	
	private int borrowSilverPieces(int sp) {
		if (silverPieces > sp) {
			silverPieces -= sp;
			return (10*sp);
		} else {
			// If I need an exact amount of gold pieces, just borrow that exact amount, otherwise borrow one more.
			if (((sp-silverPieces) % 10) == 0) {
				silverPieces += borrowGoldPieces(Math.floorDiv((sp-silverPieces),10));
			} else {
				silverPieces += borrowGoldPieces(Math.floorDiv((sp-silverPieces),10)+1);
			}
			silverPieces -= sp;
			return (10*sp);
		}
	}
	
	private int borrowGoldPieces(int gp) {
		if (goldPieces > gp) {
			goldPieces -= gp;
			return (10*gp);
		} else {
			// If I need an exact amount of platinum pieces, just borrow that exact amount, otherwise borrow one more.
			if (((gp-goldPieces) % 100) == 0) {
				goldPieces += borrowPlatinumPieces(Math.floorDiv((gp-goldPieces),100));
			} else {
				goldPieces += borrowPlatinumPieces(Math.floorDiv((gp-goldPieces),100)+1);
			}
			goldPieces -= gp;
			return (10 * gp);
		}
	}

	private int borrowPlatinumPieces(int pp) {
		if (platinumPieces > pp) {
			platinumPieces -= pp;
			return (100*pp);
		} else {
			// If I need an exact amount of astral diamonds, just borrow that exact amount, otherwise borrow one more.
			if (((pp-platinumPieces) % 100) == 0) {
				platinumPieces += borrowAstralDiamonds(Math.floorDiv((pp-platinumPieces),100));
			} else {
				platinumPieces += borrowAstralDiamonds(Math.floorDiv((pp-platinumPieces),100)+1);

			}
			platinumPieces -= pp;
			return (100*pp);
		}
	}
	
	private int borrowAstralDiamonds(int ad) {
		// There should be enough, or the spend method would have returned an exception.
		if (astralDiamonds >= ad) {
			astralDiamonds -= ad;
			return (100*ad);
		}
		return 0;
	}
	
	public int valueInCopperPieces() {
		return (copperPieces + (10*silverPieces) + (100*goldPieces) + (10000*platinumPieces) + (1000000*astralDiamonds));
	}
	
	private int convertToCopperPieces(int i, CoinType coinType) {
		switch (coinType) {
		case COPPER_PIECE :
			return i;
		case SILVER_PIECE :
			return 10*i;
		case GOLD_PIECE :
			return 100*i;
		case PLATINUM_PIECE :
			return 10000 * i;
		case ASTRAL_DIAMOND :
			return 1000000 * i;
		}
		return 0;
	}
}
