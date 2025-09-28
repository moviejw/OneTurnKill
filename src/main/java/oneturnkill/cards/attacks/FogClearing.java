package oneturnkill.cards.attacks;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import oneturnkill.cards.AbstractEasyCard;

import static oneturnkill.ModFile.makeID;
import static oneturnkill.util.Wiz.*;

public class FogClearing extends AbstractEasyCard {
    public final static String ID = makeID("FogClearing");

    public FogClearing() {
        super(ID, 2, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY);
        baseDamage = 9;
        tags.add(CardTags.STARTER_DEFEND);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmgTop(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
    }

    public void upp() {
        upgradeDamage(3);
    }
}