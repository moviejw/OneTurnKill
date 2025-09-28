package oneturnkill.cardmodifiers;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static oneturnkill.ModFile.makeID;

public class FragileModifier extends AbstractCardModifier {
    private static final Logger logger = LogManager.getLogger(AbstractCardModifier.class.getName());
    private static final UIStrings uiStrings =
            CardCrawlGame.languagePack.getUIString(makeID("Fragile"));
    public static final String[] TEXT = uiStrings.TEXT;
    private int amount;
    private int baseAmount;

    public FragileModifier(int amount) {
        this.amount = amount;
        this.baseAmount = amount;
    }

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        return TEXT[0] + amount + TEXT[1] + rawDescription;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new FragileModifier(amount);
    }

    @Override
    public void onInitialApplication(AbstractCard card) {
        card.initializeDescription();
    }

    @Override
    public void onOtherCardPlayed(AbstractCard other, AbstractCard self, CardGroup group) {
        if (group == AbstractDungeon.player.hand) {
            amount--;
            if (amount <= 0) {
                AbstractDungeon.actionManager.addToBottom(
                        new ExhaustSpecificCardAction(other, AbstractDungeon.player.hand)
                );
            }
            other.initializeDescription();
        }
    }

    @Override
    public String identifier(AbstractCard card) {
        return makeID("FragileModifier");
    }

    public void resetAmount(AbstractCard card) {
        this.amount = this.baseAmount;
        card.initializeDescription();
    }

    public void upgradeAmount(AbstractCard card, int upgrade) {
        this.amount += upgrade;
        this.baseAmount += upgrade;
        card.initializeDescription();
    }
}
