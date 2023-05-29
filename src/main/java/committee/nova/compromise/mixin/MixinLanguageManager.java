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

@Mixin(LanguageManager.class)
public abstract class MixinLanguageManager {
    @Shadow
    private LanguageInfo currentLanguage;

    @Shadow
    public abstract LanguageInfo getLanguage(String p_118977_);

    @Inject(method = "onResourceManagerReload",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/resources/language/ClientLanguage;loadFrom(Lnet/minecraft/server/packs/resources/ResourceManager;Ljava/util/List;)Lnet/minecraft/client/resources/language/ClientLanguage;"),
            locals = LocalCapture.CAPTURE_FAILHARD)
    private void inject$onResourceManagerReload(ResourceManager p_118973_, CallbackInfo ci, LanguageInfo languageinfo, List<LanguageInfo> list) {
        list.remove(currentLanguage);
        for (final String langCode : ClientConfig.getCompromiseLocales().reverse()) {
            final LanguageInfo lang = getLanguage(langCode);
            if (lang != null) list.add(lang);
        }
        list.add(currentLanguage);
    }
}
