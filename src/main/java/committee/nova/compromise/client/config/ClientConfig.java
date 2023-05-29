package committee.nova.compromise.client.config;

import com.google.common.collect.Lists;
import committee.nova.compromise.Compromise;
import net.minecraftforge.common.config.Config;

@Config(modid = Compromise.MODID)
@Config.LangKey("cfg.compromise.title")
public class ClientConfig {
    @Config.Comment("The list of codes of compromise languages.")
    @Config.LangKey("cfg.compromise.langCodes")
    public static String[] compromiseLangCodes = Lists.newArrayList("en_gb").toArray(new String[0]);
}
