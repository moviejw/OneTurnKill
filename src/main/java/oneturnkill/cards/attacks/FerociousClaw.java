package oneturnkill.cards.attacks;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import oneturnkill.cards.AbstractEasyCard;

import static oneturnkill.ModFile.makeID;
import static oneturnkill.util.Wiz.*;

public class FerociousClaw extends AbstractEasyCard {
    public final static String ID = makeID("FerociousClaw");
    // intellij stuff attack, , basic, 24, 30, , , , 

    public FerociousClaw() {
        super(ID, 3, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY);
        baseDamage = 20;
        baseMagicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmgTop(m, AbstractGameAction.AttackEffect.SMASH);
        addToBot(new DiscardAction(p, p, magicNumber, false));
    }

    public void upp() {
        upgradeDamage(4);
    }
}