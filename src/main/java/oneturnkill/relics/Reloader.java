package oneturnkill.relics;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import oneturnkill.CharacterFile;
import oneturnkill.cards.skills.Reload;

import static oneturnkill.ModFile.makeID;

public class Reloader extends AbstractEasyRelic {
    public static final String ID = makeID("Reloader");

    public Reloader() {
        super(ID, RelicTier.STARTER, LandingSound.FLAT, CharacterFile.Enums.ZASKIA_COLOR);
    }

    @Override
    public void atBattleStartPreDraw() {
        this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        this.addToBot(new MakeTempCardInHandAction((AbstractCard)new Reload(), 1, false));
    }
}
