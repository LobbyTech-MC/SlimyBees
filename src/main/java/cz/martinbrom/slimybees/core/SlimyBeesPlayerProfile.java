package cz.martinbrom.slimybees.core;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import cz.martinbrom.slimybees.SlimyBeesPlugin;
import cz.martinbrom.slimybees.core.genetics.alleles.AlleleSpecies;
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile;
import me.mrCookieSlime.Slimefun.cscorelib2.config.Config;

/**
 * This class represents holds cached player data.
 * It is basically a copy of {@link PlayerProfile} from Slimefun 4,
 * but until that class is fully extendable for addons,
 * a separate implementation is the way for this addon.
 */
@ParametersAreNonnullByDefault
public class SlimyBeesPlayerProfile {

    public static final String BEE_SPECIES_KEY = "discovered_bee_species";

    private final UUID uuid;

    private final Config beeConfig;

    private final Set<String> discoveredBees;
    private final BeeAtlasHistory beeAtlasHistory = new BeeAtlasHistory(this);

    private boolean dirty = false;
    private boolean markedForDeletion = false;

    private SlimyBeesPlayerProfile(OfflinePlayer p) {
        uuid = p.getUniqueId();

        beeConfig = new Config("data-storage/SlimyBees/Players/" + uuid + ".yml");

        List<String> allSpecies = SlimyBeesPlugin.getAlleleRegistry().getAllSpeciesNames();
        discoveredBees = allSpecies.stream()
                .filter(name -> beeConfig.contains(BEE_SPECIES_KEY + "." + name))
                .collect(Collectors.toSet());
    }

    @Nonnull
    public static SlimyBeesPlayerProfile get(OfflinePlayer p) {
        SlimyBeesPlayerProfile profile = find(p);
        if (profile == null) {
            profile = new SlimyBeesPlayerProfile(p);
            SlimyBeesPlugin.getRegistry().getPlayerProfiles().put(p.getUniqueId(), profile);
        }

        return profile;
    }

    @Nullable
    public static SlimyBeesPlayerProfile find(OfflinePlayer p) {
        Map<UUID, SlimyBeesPlayerProfile> playerProfiles = SlimyBeesPlugin.getRegistry().getPlayerProfiles();
        return playerProfiles.get(p.getUniqueId());
    }

    /**
     * This returns the {@link Player} who this {@link SlimyBeesPlayerProfile} belongs to.
     * If the {@link Player} is offline, null will be returned.
     *
     * @return The {@link Player} of this {@link SlimyBeesPlayerProfile} or null
     */
    @Nullable
    public Player getPlayer() {
        return Bukkit.getPlayer(uuid);
    }

    public void markForDeletion() {
        markedForDeletion = true;
    }

    public boolean isDirty() {
        return dirty;
    }

    public boolean isMarkedForDeletion() {
        return markedForDeletion;
    }

    public void save() {
        beeConfig.save();
        dirty = false;
    }

    /**
     * Marks given {@link AlleleSpecies} as discovered / undiscovered
     *
     * @param species  The {@link AlleleSpecies} to discover
     * @param discover True if the {@link AlleleSpecies} should be discovered,
     *                 false if "undiscovered"
     */
    public void discoverBee(AlleleSpecies species, boolean discover) {
        Validate.notNull(species, "The discovered bee species must not be null!");

        discoverBee(species.getName(), discover);
    }

    /**
     * Marks a {@link AlleleSpecies} identified by given name as discovered / undiscovered
     *
     * @param species  The name of a {@link AlleleSpecies} to discover
     * @param discover True if the {@link AlleleSpecies} should be discovered,
     *                 false if "undiscovered"
     */
    public void discoverBee(String species, boolean discover) {
        Validate.notNull(species, "The discovered bee species must not be null!");

        String key = BEE_SPECIES_KEY + "." + species;
        if (discover) {
            beeConfig.setValue(key, true);
            discoveredBees.add(species);
        } else {
            beeConfig.setValue(key, null);
            discoveredBees.remove(species);
        }

        dirty = true;
    }

    public boolean hasDiscovered(AlleleSpecies species) {
        Validate.notNull(species, "The bee species must not be null!");

        return discoveredBees.contains(species.getName());
    }

    @Nonnull
    public Set<String> getDiscoveredBees() {
        return discoveredBees;
    }

    public BeeAtlasHistory getBeeAtlasHistory() {
        return beeAtlasHistory;
    }

}
