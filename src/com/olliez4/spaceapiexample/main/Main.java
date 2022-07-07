package com.olliez4.spaceapiexample.main;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import com.olliez4.space.energy.Machine;
import com.olliez4.space.gui.guide.categories.Category;
import com.olliez4.space.gui.guide.categories.CategoryManager;
import com.olliez4.space.main.SpaceAPI;

public class Main extends JavaPlugin implements Listener {

	// API instance
	private SpaceAPI spaceAPI;
	// This will be the actual placable machine item
	private ItemStack machineStack;
	// This will be the icon for our category in the space guide
	private ItemStack iconStack;

	public void onEnable() {
		// Initialize the API
		spaceAPI = new SpaceAPI();
		// Register this class as an event
		getServer().getPluginManager().registerEvents(this, this);
		// Create our machine stack
		machineStack = new ItemStack(Material.GOLD_BLOCK);
		// Register our machine stack as a machine
		spaceAPI.registerAsMachine(machineStack, "exampleMachine");

		// Create our category icon
		iconStack = new ItemStack(Material.GOLD_BLOCK);
		ItemMeta meta = iconStack.getItemMeta();
		meta.setDisplayName("Category name");
		iconStack.setItemMeta(meta);

		// Create our custom category
		Category customCategory = new Category(iconStack);
		// Add items to the category
		customCategory.add(machineStack);
		// Register the category with space
		CategoryManager.registerCategory(customCategory);
	}

	@EventHandler
	public void place(BlockPlaceEvent e) {
		// Check whether or not the machine is our custom machine
		if (spaceAPI.isMachine(e.getItemInHand(), "exampleMachine"))
			// Place the machine
			spaceAPI.attemptPlaceMachine(machineStack, e, createMachine(e.getBlock().getLocation()));
	}

	// Create our custom machine with whatever options we require
	private Machine createMachine(Location location) {
		String name = "Machine Name";
		int energy = 0;
		int maxEnergy = 1000;
		int oxygen = 0;
		int maxOxygen = 750;
		int coolant = 0;
		int maxCoolant = 0;
		boolean active = true;
		boolean showHologram = true;
		boolean emitsOxygen = false;
		boolean emitsEnergy = false;
		boolean emitsCoolant = false;
		Machine machine = new Machine(name, energy, maxEnergy, oxygen, maxOxygen, coolant, maxCoolant, location, active,
				showHologram, emitsOxygen, emitsEnergy, emitsCoolant);
		machine.setHasInventory();
		return machine;
	}
}
