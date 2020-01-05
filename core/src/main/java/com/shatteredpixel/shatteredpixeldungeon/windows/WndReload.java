/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2019 Evan Debenham
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.shatteredpixel.shatteredpixeldungeon.windows;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.GamesInProgress;
import com.shatteredpixel.shatteredpixeldungeon.ShatteredPixelDungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.InterlevelScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.RankingsScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.TitleScene;
import com.shatteredpixel.shatteredpixeldungeon.ui.RedButton;
import com.shatteredpixel.shatteredpixeldungeon.ui.Window;
import com.watabou.noosa.Game;
import com.watabou.utils.Callback;
import com.watabou.utils.FileUtils;

import java.io.IOException;

public class WndReload extends Window {

    private static final int WIDTH		= 120;
    private static final int BTN_HEIGHT	= 20;
    private static final int GAP		= 2;

    private int pos;
    private Object cause;

    public WndReload(Object cause) {
        super();

        this.cause = cause;

        addButton( new RedButton( Messages.get(this, "reload") ) {
            @Override
            protected void onClick() {
                Game.runOnRenderThread(new Callback() {
                    @Override
                    public void call() {
                        String fileName = GamesInProgress.gameSave(GamesInProgress.curSlot);
                        if (FileUtils.fileExists(fileName)) {
                            FileUtils.deleteFile(fileName);
                        }

                        try {
                            Dungeon.loadGame(GamesInProgress.curSlot, true);
                        } catch (IOException e) {
                            ShatteredPixelDungeon.reportException(e);
                        }
                    }
                });
            }
        });


        addButton( new RedButton( Messages.get(this, "die") ) {
            @Override
            protected void onClick() {
                Hero.reallyDie(cause);
                hide();
                GameScene.show(new WndGame());
            }
        });

        resize(WIDTH, pos);
    }

    private void addButton( RedButton btn ) {
        add( btn );
        btn.setRect( 0, pos > 0 ? pos += GAP : 0, WIDTH, BTN_HEIGHT );
        pos += BTN_HEIGHT;
    }
}
