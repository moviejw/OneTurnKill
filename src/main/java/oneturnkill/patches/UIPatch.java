package oneturnkill.patches;

import basemod.abstracts.CustomEnergyOrb;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.ui.panels.AbstractPanel;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class UIPatch {
    @SpirePatch(
            clz = CustomEnergyOrb.class,
            method = "renderOrb"
    )
    public static class EnergyOrbPatch {
        @SpirePrefixPatch()
        public static SpireReturn<Void> Prefix(CustomEnergyOrb __instance, SpriteBatch sb, boolean enabled, float current_x, float current_y) {
            return SpireReturn.Return();
        }
    }

    @SpirePatch(
            clz = EnergyPanel.class,
            method = "render"
    )
    public static class EnergyPanelPatch {
        @SpirePrefixPatch()
        public static SpireReturn<Void> Prefix(AbstractPanel __instance, SpriteBatch sb) {
            return SpireReturn.Return();
        }
    }
}
