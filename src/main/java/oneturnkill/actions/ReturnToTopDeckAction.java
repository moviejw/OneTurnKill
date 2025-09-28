package oneturnkill.actions;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import oneturnkill.cardmodifiers.FragileModifier;

import java.util.ArrayList;
import java.util.List;

import static oneturnkill.ModFile.makeID;

public class ReturnToTopDeckAction extends AbstractGameAction {
    private static final UIStrings uiStrings =
            CardCrawlGame.languagePack.getUIString(makeID("ReturnToTopDeckAction"));
    public static final String[] TEXT = uiStrings.TEXT;
    private final int amount;
    private final boolean anyNumber;

    public ReturnToTopDeckAction(int amount, boolean anyNumber) {
        this.amount = amount;
        this.anyNumber = anyNumber;
        this.actionType = ActionType.CARD_MANIPULATION;
    }

    @Override
    public void update() {
        addToBot(new SelectCardsInHandAction(
                amount,
                TEXT[0],
                anyNumber,
                false,
                c -> true,
                (List<AbstractCard> selectedCards) -> {
                    List<AbstractCard> toMove = new ArrayList<>(selectedCards);
                    selectedCards.clear();
                    addToTop(new AbstractGameAction() {
                        @Override
                        public void update() {
                            for (AbstractCard card : toMove) {
                                AbstractDungeon.player.hand.moveToDeck(card, false);
                                ArrayList<AbstractCardModifier> fragiles = CardModifierManager.getModifiers(card, makeID("FragileModifier"));
                                if (!fragiles.isEmpty()) {
                                    FragileModifier fragile = (FragileModifier) fragiles.get(0);
                                    fragile.resetAmount(card);
                                }
                            }
                            AbstractDungeon.player.hand.refreshHandLayout();
                            isDone = true;
                        }
                    });
                }
        ));
        this.isDone = true;
    }
}