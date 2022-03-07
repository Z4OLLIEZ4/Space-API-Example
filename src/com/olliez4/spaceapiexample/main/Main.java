package com.olliez4.spaceapiexample.main;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import com.olliez4.space.energy.Machine;
import com.olliez4.space.main.SpaceAPI;

public class Main extends JavaPlugin implements Listener {

	// API instance
	private SpaceAPI spaceAPI;
	// This will be the actual placable machine item
	private ItemStack machineStack;

	public void onEnable() {
		// Initialize the API
		spaceAPI = new SpaceAPI();
		// Register this class as an event
		getServer().getPluginManager().registerEvents(this, this);
		// Create our stack
		machineStack = new ItemStack(Material.GOLD_BLOCK);
		// Register our stack as a machine
		spaceAPI.registerAsMachine(machineStack, "exampleMachine");
		// Get the machine (Obviously do this bit differently)
		for (Player p : getServer().getOnlinePlayers())
			p.getInventory().addItem(machineStack);
	}

	@EventHandler
	public void place(BlockPlaceEvent e) {
		// Check whether or not the machine is our custom machine
		if (spaceAPI.isMachine(machineStack, "exampleMachine"))
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
		boolean active = true;
		boolean showHologram = true;
		boolean emitsOxygen = false;
		boolean emitsEnergy = false;
		Machine machine = new Machine(name, energy, maxEnergy, oxygen, maxOxygen, location, active, showHologram,
				emitsOxygen, emitsEnergy);
		machine.setHasInventory();
		return machine;
	}
}
