package oneturnkill.cards.skills;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import oneturnkill.actions.CostZeroAction;
import oneturnkill.cards.AbstractEasyCard;

import static oneturnkill.ModFile.makeID;

public class TeaCeremony extends AbstractEasyCard {
    public final static String ID = makeID("TeaCeremony");

    public TeaCeremony() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new CostZeroAction(2, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}