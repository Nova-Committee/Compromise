package committee.nova.compromise.mixin;

import com.google.common.collect.ImmutableList;
import committee.nova.compromise.client.config.ClientConfig;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.Language;
import net.minecraft.client.resources.LanguageManager;
import org.spongepowered.asm.mixin.Final;
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
    private String currentLanguage;

    @Shadow
    @Final
    private Map<String, Language> languageMap;

    @Inject(method = "onResourceManagerReload",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/client/resources/Locale;loadLocaleDataFiles(Lnet/minecraft/client/resources/IResourceManager;Ljava/util/List;)V"),
            locals = LocalCapture.CAPTURE_FAILHARD)
    private void inject$onResourceManagerReload(IResourceManager resourceManager, CallbackInfo ci, List<String> list) {
        list.remove(currentLanguage);
        for (final String l : ImmutableList.copyOf(ClientConfig.compromiseLangCodes).reverse())
            if (languageMap.containsKey(l)) list.add(l);
        list.add(currentLanguage);
    }
}
