package gg.archipelago.neodigap.capability;

import gg.archipelago.neodigap.APRandomizer;
import gg.archipelago.neodigap.capability.data.WorldData;
import gg.archipelago.neodigap.capability.providers.WorldDataProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.AttachCapabilitiesEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;

@EventBusSubscriber(modid = APRandomizer.MODID)
public class APCapabilities {
    public static Capability<WorldData> WORLD_DATA = CapabilityManager.get(new CapabilityToken<>() {
    });


    @SubscribeEvent
    public static void RegisterPlayerData(RegisterCapabilitiesEvent event) {
        event.register(WorldData.class);
    }

    @SubscribeEvent
    static void onAttachCapabilitiesToWorldEvent(AttachCapabilitiesEvent<Level> event) {
        event.addCapability(new ResourceLocation(APRandomizer.MODID + ":world_data"), new WorldDataProvider());
    }
}
