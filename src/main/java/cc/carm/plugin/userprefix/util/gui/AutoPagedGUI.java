package cc.carm.plugin.userprefix.util.gui;

import cc.carm.plugin.userprefix.configuration.PrefixConfig;
import cc.carm.plugin.userprefix.util.ItemStackFactory;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class AutoPagedGUI extends CommonPagedGUI {

    ItemStack previousPageUI;
    ItemStack nextPageUI;
    ItemStack noPreviousPageUI;
    ItemStack noNextPageUI;
    int previousPageSlot = -1;
    int nextPageSlot = -1;

    public AutoPagedGUI(GUIType type, String name, int[] range) {
        super(type, name, range);
    }

    public AutoPagedGUI(GUIType type, String name, int a, int b) {
        super(type, name, a, b);
    }

    public void setPreviousPageUI(ItemStack lastPageUI) {
        this.previousPageUI = lastPageUI;
    }

    public void setNextPageUI(ItemStack nextPageUI) {
        this.nextPageUI = nextPageUI;
    }

    public void setNoPreviousPageUI(ItemStack noPreviousPageUI) {
        this.noPreviousPageUI = noPreviousPageUI;
    }

    public void setNoNextPageUI(ItemStack noNextPageUI) {
        this.noNextPageUI = noNextPageUI;
    }

    public void setPreviousPageSlot(int slot) {
        this.previousPageSlot = slot;
    }

    public void setNextPageSlot(int slot) {
        this.nextPageSlot = slot;
    }


    @Override
    public void openGUI(Player user) {
        if (previousPageSlot >= 0)
            if (hasPreviousPage()) {
                setItem(previousPageSlot, new GUIItem(previousPageUI == null ? new ItemStackFactory(Material.ARROW)
                        .setDisplayName("&f上一页")
                        .addLore("&7&o右键可前往第一页哦")
                        .toItemStack() : previousPageUI) {
                    @Override
                    public void onClick(ClickType type) {
                        if (type == ClickType.RIGHT) {
                            goFirstPage();
                        } else {
                            goPreviousPage();
                        }
                        PrefixConfig.Sounds.GUI_CLICK.play(user);
                        openGUI(user);
                    }
                });
            }

        if (previousPageSlot >= 0)
            if (hasNextPage()) {
                setItem(nextPageSlot, new GUIItem(nextPageUI == null ? new ItemStackFactory(Material.ARROW)
                        .setDisplayName("下一页")
                        .addLore("&7&o右键可前往最后一页哦")
                        .toItemStack() : nextPageUI) {
                    @Override
                    public void onClick(ClickType type) {
                        if (type == ClickType.RIGHT) {
                            goLastPage();
                        } else {
                            goNextPage();
                        }
                        PrefixConfig.Sounds.GUI_CLICK.play(user);
                        openGUI(user);
                    }
                });
            }

        super.openGUI(user);
    }

}
