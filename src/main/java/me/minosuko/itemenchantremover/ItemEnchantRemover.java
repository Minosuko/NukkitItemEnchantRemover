package me.minosuko.itemenchantremover;

import cn.nukkit.plugin.PluginBase;
import me.minosuko.itemenchantremover.commands.ItemEnchantRemoverCommand;

public class ItemEnchantRemover extends PluginBase {
	@Override
	public void onEnable() {
		this.getServer().getCommandMap().register("remenc", new ItemEnchantRemoverCommand(this));
	}

	@Override
	public void onDisable() {
	}
}
