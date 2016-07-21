package hypixel.utils;

import org.bukkit.entity.Entity;

import java.text.DecimalFormat;
import java.util.Random;

/**
 * Created by Niels on 21-07-16.
 */
public class UtilMath {

    public static double trim(int degree, double d)
    {
        String format = "#.#";
        for (int i = 1; i < degree; i++) {
            format = format + "#";
        }
        DecimalFormat twoDForm = new DecimalFormat(format);
        return Double.valueOf(twoDForm.format(d)).doubleValue();
    }

    public static Random random = new Random();

    public static int r(int i)
    {
        return random.nextInt(i);
    }
}
