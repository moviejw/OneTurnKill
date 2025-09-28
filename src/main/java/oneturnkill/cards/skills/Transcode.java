package oneturnkill.cards.skills;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import oneturnkill.actions.ReturnToTopDeckAction;
import oneturnkill.cardmodifiers.FragileModifier;
import oneturnkill.cards.AbstractEasyCard;

import static oneturnkill.ModFile.makeID;
import static oneturnkill.util.Wiz.*;

public class Transcode extends AbstractEasyCard {
    public final static String ID = makeID("Transcode");

    public Transcode() {
        super(ID, 4, CardType.SKILL, CardRarity.BASIC, CardTarget.NONE);
        baseMagicNumber = magicNumber = 3;
        baseSecondMagic = secondMagic = 3;
        CardModifierManager.addModifier(this, new FragileModifier(secondMagic));
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ReturnToTopDeckAction(magicNumber, false));
    }

    public void upp() {
        upgradeSecondMagic(1);
        FragileModifier mod = (FragileModifier) CardModifierManager.getModifiers(this, makeID("FragileModifier")).get(0);
        mod.upgradeAmount(this, 1);
    }
}