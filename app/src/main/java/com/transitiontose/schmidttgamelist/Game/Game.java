package com.transitiontose.schmidttgamelist.Game;
import com.transitiontose.schmidttgamelist.R;
/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class Game {

    String gameName;
    public Cover cover;
    String rowDescription;
    String detailDescription;

    public Game(String gameName, Cover cover, String rowDescription, String detailDescription) {
        this.gameName = gameName;
        this.cover = cover;
        this.rowDescription = rowDescription;
        this.detailDescription = detailDescription;
    }

    public enum Cover { castlevania, contra, doubledragon2, duckhunt, jackal,
        kirby, kungfu, loderunner, mariobros, mariobros2, megaman, megaman2,
        supertecmobowl, metroid, tetris, zelda
    }

    public String getName() {
        return gameName;
    }

    public void setName(String name) {
        this.gameName = name;
    }

    public String getShortDescription() {
        return rowDescription;
    }

    public String getLongDescription() {
        return detailDescription;
    }

    public Cover getCover() {
        return cover;
    }

    public String toString() {
        return gameName;
    }

    public static int getIconResource(Game.Cover cover) {
        switch (cover) {
            case castlevania: return R.drawable.castlevania;
            case contra:  return R.drawable.contra;
            case doubledragon2: return R.drawable.doubledragon2;
            case duckhunt: return R.drawable.duckhunt;
            case jackal: return R.drawable.jackal;
            case kirby: return R.drawable.kirby;
            case kungfu: return R.drawable.kungfu;
            case loderunner: return R.drawable.loderunner;
            case mariobros: return R.drawable.mariobros;
            case mariobros2: return R.drawable.mariobros2;
            case megaman: return R.drawable.megaman;
            case megaman2: return R.drawable.megaman2;
            case supertecmobowl: return R.drawable.supertecmobowl;
            case metroid: return R.drawable.metroid;
            case tetris: return R.drawable.tetris;
            case zelda: return R.drawable.zelda;
        }
        return -1;
    }
}