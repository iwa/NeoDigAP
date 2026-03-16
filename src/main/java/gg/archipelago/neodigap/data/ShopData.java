package gg.archipelago.neodigap.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.saveddata.SavedData;

import java.util.*;

import static net.neoforged.neoforge.common.util.NeoForgeExtraCodecs.setOf;

public class ShopData extends SavedData {
    // Item Shop: purchased shop location indices (0-19)
    private Set<Integer> purchasedShopLocations = new HashSet<>();
    // Item Shop: cached item flags from scouting (index -> flags)
    private Map<Integer, Integer> shopItemFlags = new HashMap<>();
    // Item Shop: cached item names from scouting (index -> name)
    private Map<Integer, String> shopItemNames = new HashMap<>();
    // Item Shop: cached player names from scouting (index -> player name)
    private Map<Integer, String> shopItemPlayers = new HashMap<>();
    // Shop tier unlocked (0 = none, 1 = row 1, 2 = row 1+2, 3 = all rows)
    private int shopTierUnlocked = 0;

    public static final Codec<ShopData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            setOf(Codec.INT).fieldOf("purchasedShopLocations").forGetter(data -> new HashSet<>(data.purchasedShopLocations)),
            Codec.unboundedMap(Codec.INT, Codec.INT).fieldOf("shopItemFlags").forGetter(data -> data.shopItemFlags),
            Codec.unboundedMap(Codec.INT, Codec.STRING).fieldOf("shopItemNames").forGetter(data -> data.shopItemNames),
            Codec.unboundedMap(Codec.INT, Codec.STRING).fieldOf("shopItemPlayers").forGetter(data -> data.shopItemPlayers),
            Codec.INT.fieldOf("shopTierUnlocked").forGetter(data -> data.shopTierUnlocked)
    ).apply(instance, ShopData::new));

    public ShopData(Set<Integer> purchasedShopLocations, Map<Integer, Integer> shopItemFlags,
                    Map<Integer, String> shopItemNames, Map<Integer, String> shopItemPlayers,
                    int shopTierUnlocked) {
        this.purchasedShopLocations = new HashSet<>(purchasedShopLocations);
        this.shopItemFlags = shopItemFlags;
        this.shopItemNames = shopItemNames;
        this.shopItemPlayers = shopItemPlayers;
        this.shopTierUnlocked = shopTierUnlocked;
    }

    public Set<Integer> getPurchasedShopLocations() {
        return purchasedShopLocations;
    }

    public void setPurchasedShopLocations(Set<Integer> purchasedShopLocations) {
        this.purchasedShopLocations = new HashSet<>(purchasedShopLocations);
        this.setDirty();
    }

    public Map<Integer, Integer> getShopItemFlags() {
        return shopItemFlags;
    }

    public void setShopItemFlags(Map<Integer, Integer> shopItemFlags) {
        this.shopItemFlags = shopItemFlags;
        this.setDirty();
    }

    public Map<Integer, String> getShopItemNames() {
        return shopItemNames;
    }

    public void setShopItemNames(Map<Integer, String> shopItemNames) {
        this.shopItemNames = shopItemNames;
        this.setDirty();
    }

    public Map<Integer, String> getShopItemPlayers() {
        return shopItemPlayers;
    }

    public void setShopItemPlayers(Map<Integer, String> shopItemPlayers) {
        this.shopItemPlayers = shopItemPlayers;
        this.setDirty();
    }

    public int getShopTierUnlocked() {
        return shopTierUnlocked;
    }

    public void setShopTierUnlocked(int shopTierUnlocked) {
        this.shopTierUnlocked = shopTierUnlocked;
        this.setDirty();
    }
}
