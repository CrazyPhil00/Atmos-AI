package mcp.client;

import java.util.Arrays;

import it.sieben.Atmos;
import net.minecraft.client.main.Main;
import org.checkerframework.checker.units.qual.A;

public class Start
{
    public static void main(String[] args)  {
        /*
         * start minecraft game application
         * --version is just used as 'launched version' in snoop data and is required
         * Working directory is used as gameDir if not provided
         */
        String assets = System.getenv().containsKey("assetDirectory") ? System.getenv("assetDirectory") : "assets";
        Main.main(concat(new String[]{"--version", "mcp", "--accessToken", "0", "--assetsDir", assets, "--assetIndex", "3", "--userProperties", "{}"}, args));
        Atmos atmos = new Atmos();

    }

    public static <T> T[] concat(T[] first, T[] second)
    {
        T[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }
}