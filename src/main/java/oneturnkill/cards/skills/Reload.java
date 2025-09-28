package oneturnkill.cards.skills;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import oneturnkill.actions.ReloadAction;
import oneturnkill.actions.ReturnToTopDeckAction;
import oneturnkill.cards.AbstractEasyCard;

import static oneturnkill.ModFile.makeID;
import static oneturnkill.util.Wiz.*;

public class Reload extends AbstractEasyCard {
    public final static String ID = makeID("Reload");

    public Reload() {
        super(ID, 0, CardType.SKILL, CardRarity.SPECIAL, CardTarget.NONE, CardColor.COLORLESS);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ReloadAction(15));
    }

    public void upp() {

    }
}