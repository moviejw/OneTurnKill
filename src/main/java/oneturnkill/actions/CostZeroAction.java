package oneturnkill.actions;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.List;

import static oneturnkill.ModFile.makeID;

public class CostZeroAction extends AbstractGameAction {
    private static final UIStrings uiStrings =
            CardCrawlGame.languagePack.getUIString(makeID("CostZeroAction"));
    public static final String[] TEXT = uiStrings.TEXT;
    private final int amount;
    private final int limit;

    public CostZeroAction(int amount, int limit) {
        this.amount = amount;
        this.limit = limit;
        this.actionType = ActionType.CARD_MANIPULATION;
    }

    @Override
    public void update() {
        addToBot(new SelectCardsInHandAction(
                amount,
                TEXT[0],
                false,
                false,
                c -> c.costForTurn <= limit && c.costForTurn > 0,
                (List<AbstractCard> selectedCards) -> {
                    for (AbstractCard card : selectedCards) {
                        card.setCostForTurn(0);
                        card.superFlash();
                    }
                }
        ));
        this.isDone = true;
    }
}