package main.java.tetris.render;

import javafx.scene.paint.Color;
import main.java.tetris.game.TetrominoType;

import java.util.EnumMap;
import java.util.Map;

public class ColorPalette {
    private static final Map<TetrominoType, Color> COLORS = new EnumMap<>(TetrominoType.class);

    static {
        COLORS.put(TetrominoType.I, Color.LIGHTGREEN);
        COLORS.put(TetrominoType.O, Color.YELLOW);
        COLORS.put(TetrominoType.T, Color.MEDIUMPURPLE);
        COLORS.put(TetrominoType.S, Color.INDIANRED);
        COLORS.put(TetrominoType.Z, Color.GREEN);
        COLORS.put(TetrominoType.J, Color.ORANGE);
        COLORS.put(TetrominoType.L, Color.BLUE);
    }


    private ColorPalette() {

    }

    public static Color get(TetrominoType type) {
        return COLORS.get(type);
    }
}
