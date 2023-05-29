package committee.nova.compromise;

import net.minecraftforge.fml.common.Mod;

@Mod(modid = Compromise.MODID, useMetadata = true, clientSideOnly = true, guiFactory = "committee.nova.compromise.client.factory.ConfigGuiFactory")
public class Compromise {
    public static final String MODID = "compromise";
}
