package committee.nova.compromise.mixin;

import committee.nova.compromise.client.config.ClientConfig;
import net.minecraft.client.resources.Language;
import net.minecraft.client.resources.LanguageManager;
import net.minecraft.resources.IResourceManager;
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
    private Language field_239504_e_;

    @Shadow
    public abstract Language getLanguage(String p_191960_1_);

    @Inject(method = "onResourceManagerReload",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/resources/ClientLanguageMap;func_239497_a_(Lnet/minecraft/resources/IResourceManager;Ljava/util/List;)Lnet/minecraft/client/resources/ClientLanguageMap;"),
            locals = LocalCapture.CAPTURE_FAILHARD)
    private void inject$onResourceManagerLoad(IResourceManager resourceManager, CallbackInfo ci, Language language, List<Language> list) {
        list.remove(field_239504_e_);
        for (final String langCode : ClientConfig.getCompromiseLocales().reverse()) {
            final Language lang = getLanguage(langCode);
            if (lang != null) list.add(lang);
        }
        list.add(field_239504_e_);
    }
}
