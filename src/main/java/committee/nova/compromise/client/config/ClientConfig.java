package committee.nova.compromise.client.config;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.List;

public class ClientConfig {
    public static final ForgeConfigSpec CFG;
    private static final ForgeConfigSpec.ConfigValue<List<String>> compromiseLocales;

    static {
        final ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.comment("Compromise Settings").push("compromise");
        compromiseLocales = builder.comment(
                "Put compromise language codes into the list, in the order you want.",
                "Check https://minecraft.fandom.com/wiki/Language#Languages for language code reference."
        ).define("compromise_languages", Lists.newArrayList("en_uk"));
        builder.pop();
        CFG = builder.build();
    }

    public static ImmutableList<String> getCompromiseLocales() {
        return ImmutableList.copyOf(compromiseLocales.get());
    }
}
