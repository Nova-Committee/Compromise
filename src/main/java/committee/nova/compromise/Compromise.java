package committee.nova.compromise;

import committee.nova.compromise.client.config.ClientConfig;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

@Mod("compromise")
public class Compromise {
    public Compromise() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ClientConfig.CFG);
        ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.DISPLAYTEST, () -> Pair.of(() -> "", (s, l) -> true));
    }
}
