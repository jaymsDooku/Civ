package com.github.igotyou.FactoryMod;

import org.bukkit.Location;
import org.bukkit.block.Chest;
import org.bukkit.block.Furnace;
import org.bukkit.inventory.Inventory;

import com.github.igotyou.FactoryMod.interfaces.Properties;

//original file:
/**
 * MachineObject.java
 * Purpose: Basic object base for machines to extend
 *
 * @author MrTwiggy
 * @version 0.1 1/14/13
 */
//edited version:
/**
 * FactoryObject.java	 
 * Purpose basic object base for factorys to extend
 * @author igotyou
 *
 */
public class FactoryObject
{
	//the diffrent factory types, NOTE: these are not the sub-factory types, these are the main types.
	public enum FactoryType
	{
		PRODUCTION, POWER
	}
	
	
	protected Location factoryLocation; // Current location of factory center
	protected Location factoryInventoryLocation; //Current location of factory inventory(normmaly a chest)
	protected Location factoryPowerSourceLocation;//Current location of factory power source(normmaly a furnace)
	protected boolean active; // Whether factory is currently active
	protected Inventory factoryInventory; // The inventory of the factory
	protected Inventory factoryPowerInventory;//The inventory of the power source.
	protected FactoryType factoryType; // The type this factory is
	protected String subFactoryType;//the SUBfactory type(the ones loaded from the config file)
	protected Properties factoryProperties; // The properties of this factory type and tier
	
	protected boolean upgraded; // Whether the tier has recently upgraded
	
	/**
	 * Constructor
	 */
	public FactoryObject(Location factoryLocation, Location factoryInventoryLocation, Location factoryPowerSource,
			FactoryType factoryType, String subFactoryType)
	{
		this.factoryLocation = factoryLocation;
		this.factoryInventoryLocation = factoryInventoryLocation;
		this.factoryPowerSourceLocation = factoryPowerSource;
		this.active = false;
		this.factoryType = factoryType;
		this.subFactoryType = subFactoryType;
		this.upgraded = false;
		initializeInventory();
		updateProperties();
	}

	/**
	 * Constructor
	 */
	public FactoryObject(Location factoryLocation, Location factoryInventoryLocation, Location factoryPowerSource,
			boolean active, FactoryType factoryType, String subFactoryType)
	{
		this.factoryLocation = factoryLocation;
		this.factoryInventoryLocation = factoryInventoryLocation;
		this.factoryPowerSourceLocation = factoryPowerSource;
		this.active = active;
		this.factoryType = factoryType;
		this.subFactoryType = subFactoryType;
		this.upgraded = false;
		initializeInventory();
		updateProperties();
	}
	
	/**
	 * Constructor
	 */
	public FactoryObject(Location factoryLocation, Location factoryInventoryLocation, Location factoryPowerSource,
			boolean active, int tierLevel, FactoryType factoryType, Inventory factoryInventory,
			String subFactoryType)
	{
		this.factoryLocation = factoryLocation;
		this.factoryInventoryLocation = factoryInventoryLocation;
		this.factoryPowerSourceLocation = factoryPowerSource;
		this.active = active;
		this.factoryType = factoryType;
		this.subFactoryType = subFactoryType;
		this.factoryInventory = factoryInventory;
		updateProperties();
	}

	/**
	 * Initializes the inventory for this factory
	 */
	public void initializeInventory()
	{
		switch(factoryType)
		{
		case PRODUCTION:
			Chest chestBlock = (Chest)factoryInventoryLocation.getBlock().getState();
			factoryInventory = chestBlock.getInventory();
			Furnace furnaceBlock = (Furnace)factoryPowerSourceLocation.getBlock().getState();
			factoryPowerInventory = furnaceBlock.getInventory();
			break;
		default:
			break;
		}
	}
	
	/**
	 * Updates the current properties for the factory.
	 */
	public void updateProperties()
	{
		factoryProperties = FactoryModPlugin.getProperties(factoryType, subFactoryType);
	}
	
	/**
	 * Returns the user-friendly name for this factory type
	 */
	public String factoryName()
	{
		switch (factoryType)
		{
		case PRODUCTION:
			return "Production";
		default: 
			return null;
		}
	}

	/**
	 * returns the factory Inventory(normally a chest), updates the inventory variable aswell.
	 */
	public Inventory getInventory()
	{
		switch (factoryType)
		{
		case PRODUCTION:
			Chest chestBlock = (Chest)factoryInventoryLocation.getBlock().getState();
			factoryInventory = chestBlock.getInventory();
			return factoryInventory;
		default:
			return factoryInventory;
		}
	}

	/**
	 * Returns the power Source inventory, updates it aswell.
	 */
	public Inventory getPowerSourceInventory()
	{
		switch (factoryType)
		{
		case PRODUCTION:
			Furnace furnaceBlock = (Furnace)factoryPowerSourceLocation.getBlock().getState();
			factoryPowerInventory = furnaceBlock.getInventory();
			return factoryPowerInventory;
		default:
			return factoryPowerInventory;
		}
		
	
	}
	
	/**
	 * Returns the sub-factory type of the factory. 
	 */
	public String getSubFactoryType()
	{
		return subFactoryType;
	}
	
	
	/**
	 * returns if the factory is on or not.
	 */
	public boolean getActive()
	{
		return active;
	}
}