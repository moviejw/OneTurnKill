package oneturnkill.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.blights.AbstractBlight;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import javassist.CtBehavior;

import static oneturnkill.ModFile.makeID;

public class CardPatch {
    private static final UIStrings uiStrings =
            CardCrawlGame.languagePack.getUIString(makeID("CantDrawFromDeck"));
    public static final String[] TEXT = uiStrings.TEXT;

    @SpirePatch(
            clz = AbstractPlayer.class,
            method = "useCard"
    )
    public static class UseCardPatch {
        @SpirePrefixPatch
        public static void Prefix(AbstractPlayer __instance, AbstractCard c, AbstractMonster monster, int energyOnUse) {
            if (c.costForTurn > 0) {
                AbstractDungeon.actionManager.addToBottom(
                        new DrawCardAction(__instance, c.costForTurn)
                );
            }
        }

        @SpireInsertPatch(
                locator = Locator.class
        )
        public static SpireReturn<Void> Insert(AbstractPlayer __instance, AbstractCard c, AbstractMonster monster, int energyOnUse) {
            return SpireReturn.Return(null);
        }

        private static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(EnergyManager.class, "use");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }

    @SpirePatch(
            clz = AbstractCard.class,
            method = "hasEnoughEnergy"
    )
    public static class EnergyCheckPatch {
        @SpirePostfixPatch()
        public static boolean Postfix(boolean __result, AbstractCard __instance) {
            for (AbstractPower p : AbstractDungeon.player.powers) {
                if (p.canPlayCard(__instance)) continue;
                return false;
            }
            for (AbstractRelic r : AbstractDungeon.player.relics) {
                if (r.canPlay(__instance)) continue;
                return false;
            }
            for (AbstractBlight b : AbstractDungeon.player.blights) {
                if (b.canPlay(__instance)) continue;
                return false;
            }
            for (AbstractCard c : AbstractDungeon.player.hand.group) {
                if (c.canPlay(__instance)) continue;
                return false;
            }
            if (!AbstractDungeon.actionManager.turnHasEnded && !(AbstractDungeon.player.hasPower("Entangled") && __instance.type == AbstractCard.CardType.ATTACK)) {
                if (__instance.costForTurn <= AbstractDungeon.player.drawPile.size()) {
                    return true;
                }
                else {
                    __instance.cantUseMessage = TEXT[0];
                    return false;
                }
            }
            else return false;
        }
    }

    @SpirePatch(
            clz = AbstractCard.class,
            method = "canUse"
    )
    public static class CanUseDrawCheckPatch {
        @SpirePrefixPatch()
        public static SpireReturn<Boolean> Prefix(AbstractCard __instance, AbstractPlayer p, AbstractMonster m) {

            if (__instance.type == AbstractCard.CardType.STATUS
                    && __instance.costForTurn < -1
                    && !AbstractDungeon.player.hasRelic("Medical Kit")) {
                return SpireReturn.Return(false);
            }
            if (__instance.type == AbstractCard.CardType.CURSE
                    && __instance.costForTurn < -1
                    && !AbstractDungeon.player.hasRelic("Blue Candle")) {
                return SpireReturn.Return(false);
            }

            int totalCardsAvailable = p.drawPile.size();

            if (__instance.costForTurn <= 0) {
                return SpireReturn.Return(__instance.cardPlayable(m));
            }

            if (totalCardsAvailable < __instance.costForTurn) {
                return SpireReturn.Return(false);
            }

            return SpireReturn.Return(__instance.cardPlayable(m));
        }
    }
}
