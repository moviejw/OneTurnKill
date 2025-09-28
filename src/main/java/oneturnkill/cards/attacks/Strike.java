package oneturnkill.cards.attacks;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import oneturnkill.cards.AbstractEasyCard;

import static oneturnkill.ModFile.makeID;

public class Strike extends AbstractEasyCard {
    public final static String ID = makeID("Strike_Z");

    public Strike() {
        super(ID, 0, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY);
        baseDamage = 2;
        tags.add(CardTags.STRIKE);
        tags.add(CardTags.STARTER_STRIKE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmgTop(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        dmgTop(m, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
    }

    public void upp() {
        upgradeDamage(2);
    }
}