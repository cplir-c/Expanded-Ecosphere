package cristelknight.wwoo.config.configs;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import cristelknight.wwoo.config.jankson.config.CommentedConfig;
import net.minecraft.Util;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;


public record BannedBiomesConfig(List<String> bannedBiomes)
        implements CommentedConfig<BannedBiomesConfig> {

    private static BannedBiomesConfig INSTANCE = null;

    public static final BannedBiomesConfig DEFAULT = new BannedBiomesConfig(List.of());

    public static final Codec<BannedBiomesConfig> CODEC = RecordCodecBuilder.create(builder ->
            builder.group(
                    Codec.list(Codec.STRING).fieldOf("banned_biomes").orElse(List.of()).forGetter(config -> config.bannedBiomes)
            ).apply(builder, BannedBiomesConfig::new)
    );

    @Override
    public String getSubPath() {
        return "wwoo/banned_biomes";
    }

    @Override
    public BannedBiomesConfig getInstance() {
        return INSTANCE;
    }

    @Override
    public BannedBiomesConfig getDefault() {
        return DEFAULT;
    }

    @Override
    public Codec<BannedBiomesConfig> getCodec() {
        return CODEC;
    }

    @Override
    public @NotNull HashMap<String, String> getComments() {
        return Util.make(new HashMap<>(), map -> {
            map.put("banned_biomes", """
                Enter all WWOO biomes that should be deactivated here
                (only works when compatible mode is activated in wwoo.json5)
                Here is an example: ["andesite_crags", "bayou", "snowy_thermal_taiga"]""");
        });
    }

    @Override
    public @Nullable String getHeader() {
        return null;
    }

    @Override
    public boolean isSorted() {
        return false;
    }

    @Override
    public void setInstance(BannedBiomesConfig instance) {
        INSTANCE = instance;
    }
}
