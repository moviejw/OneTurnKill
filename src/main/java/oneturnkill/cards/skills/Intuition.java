package oneturnkill.cards.skills;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import oneturnkill.actions.DamageUpAction;
import oneturnkill.cards.AbstractEasyCard;

import static oneturnkill.ModFile.makeID;
import static oneturnkill.util.Wiz.*;

public class Intuition extends AbstractEasyCard {
    public final static String ID = makeID("Intuition");
    // intellij stuff skill, none, basic, , , , , 2, 1

    public Intuition() {
        super(ID, 2, CardType.SKILL, CardRarity.BASIC, CardTarget.NONE);
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageUpAction(magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}