package me.minosuko.itemenchantremover.commands;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.PluginCommand;
import cn.nukkit.command.data.CommandParamType;
import cn.nukkit.command.data.CommandParameter;
import cn.nukkit.item.enchantment.Enchantment;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.inventory.Inventory;
import cn.nukkit.item.Item;
import me.minosuko.itemenchantremover.ItemEnchantRemover;
import java.lang.Math;

public class ItemEnchantRemoverCommand extends PluginCommand<ItemEnchantRemover> {
    public ItemEnchantRemoverCommand(ItemEnchantRemover plugin) {
		super("/remove-enchant", plugin);
		this.setAliases(new String[]{ "re", "remenc", "rme", "rie" });
		this.setUsage("/remove-enchant");
		this.setDescription("Remove enchant");
		commandParameters.clear();
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		Player player = (Player) sender;
		Item item = player.getInventory().getItemInHand();
		if(!item.hasEnchantments()){
			sender.sendMessage("Item doesn't have enchantment.");
			return false;
		}
        int newLevel = player.getExperienceLevel();
		int level = 0;
		Enchantment[] itemEnchant = item.getEnchantments();
		for (int i = 0; i < itemEnchant.length; i++)
			level += itemEnchant[i].getLevel();
		if(item.getCount() > 1)
			level *= item.getCount();
		level *= 2.5;
		level = Math.round(level);
		newLevel += level;
        if (newLevel > 24791) newLevel = 24791;
        player.setExperience(player.getExperience(), newLevel);
		Item NewItem = Item.get(
				item.getId(),
				item.getDamage(),
				item.getCount()
		);
		NewItem.setCustomName(item.getName());
		player.getInventory().setItemInHand(NewItem);
		sender.sendMessage("Enchant removed.");
        return false;
    }
}
