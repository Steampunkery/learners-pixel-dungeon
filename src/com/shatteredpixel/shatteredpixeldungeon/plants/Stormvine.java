package com.shatteredpixel.shatteredpixeldungeon.plants;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.ConfusionGas;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.MagicalSleep;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Vertigo;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfLevitation;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;

/**
 * Created by Evan on 23/10/2014.
 */
public class Stormvine extends Plant {

    private static final String TXT_DESC =
            "Stormvine is an unusual sort fo vine which 'hangs' on the air. " +
            "Gravity affects it strangely, and anything caught in the vine is confused as a result.";

    {
        image = 9;
        plantName = "Stormvine";
    }

    @Override
    public void activate( Char ch ) {
        super.activate( ch );

        if (ch != null) {
            Buff.affect(ch, Vertigo.class, Vertigo.duration( ch ) );
        }
    }

    @Override
    public String desc() {
        return TXT_DESC;
    }

    public static class Seed extends Plant.Seed {
        {
            plantName = "Stormvine";

            name = "seed of " + plantName;
            image = ItemSpriteSheet.SEED_STORMVINE;

            plantClass = Stormvine.class;
            alchemyClass = PotionOfLevitation.class;
        }

        @Override
        public String desc() {
            return TXT_DESC;
        }
    }
}
