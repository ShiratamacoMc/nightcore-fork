package su.nightexpress.nightcore.integration.item.adapter.impl;

import net.momirealms.craftengine.bukkit.api.CraftEngineItems;
import net.momirealms.craftengine.core.item.CustomItem;
import net.momirealms.craftengine.core.util.Key;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import su.nightexpress.nightcore.integration.item.data.ItemIdData;
import su.nightexpress.nightcore.integration.item.adapter.IdentifiableItemAdapter;

public class CraftEngineAdapter extends IdentifiableItemAdapter {

    public CraftEngineAdapter() {
        super("craftengine");
    }

    @Override
    @Nullable
    public ItemStack createItem(@NotNull String itemId) {
        Key key = Key.of(itemId);
        CustomItem<ItemStack> customItem = CraftEngineItems.byId(key);
        return customItem == null ? null : customItem.buildItemStack();
    }

    @Override
    public boolean canHandle(@NotNull ItemStack itemStack) {
        return CraftEngineItems.isCustomItem(itemStack);
    }

    @Override
    public boolean canHandle(@NotNull ItemIdData data) {
        Key key = Key.of(data.getItemId());
        return CraftEngineItems.byId(key) != null;
    }

    @Override
    @Nullable
    public String getItemId(@NotNull ItemStack item) {
        if (item.getType().isAir()) return null;

        Key key = CraftEngineItems.getCustomItemId(item);
        return key == null ? null : key.asString();
    }
}
