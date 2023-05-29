package committee.nova.compromise.mixin;

import committee.nova.compromise.client.config.ClientConfig;
import net.minecraft.client.resources.language.LanguageInfo;
import net.minecraft.client.resources.language.LanguageManager;
import net.minecraft.server.packs.resources.ResourceManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;
import java.util.Map;

@Mixin(LanguageManager.class)
public abstract class MixinLanguageManager {
    @Shadow
    public abstract LanguageInfo getLanguage(String p_118977_);

    @Shadow
    private String currentCode;

    @Shadow
    private Map<String, LanguageInfo> languages;

    @Inject(method = "onResourceManagerReload",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/resources/language/ClientLanguage;loadFrom(Lnet/minecraft/server/packs/resources/ResourceManager;Ljava/util/List;Z)Lnet/minecraft/client/resources/language/ClientLanguage;"),
            locals = LocalCapture.CAPTURE_FAILHARD)
    private void inject$onResourceManagerReload(ResourceManager p_118973_, CallbackInfo ci, List<String> list) {
        list.remove(currentCode);
        for (final String langCode : ClientConfig.getCompromiseLocales().reverse()) {
            if (languages.containsKey(langCode)) list.add(langCode);
        }
        list.add(currentCode);
    }
}
