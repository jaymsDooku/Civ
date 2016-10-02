package com.devotedmc.ExilePearl.holder;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;

import com.devotedmc.ExilePearl.ExilePearl;
import com.devotedmc.ExilePearl.util.Guard;

/**
 * A location holding an exile pearl
 * @author Gordon
 *
 */
public class LocationHolder implements PearlHolder {

	private final Location loc;
	
	/**
	 * Creates a new LocationHolder instance
	 * @param loc The location
	 */
	public LocationHolder(final Location loc) {
		Guard.ArgumentNotNull(loc, "loc");
		
		this.loc = loc;
	}

	@Override
	public String getName() {
		return "nobody";
	}

	@Override
	public Location getLocation() {
		return loc;
	}

	@Override
	public HolderVerifyResult validate(ExilePearl pearl, StringBuilder feedback) {
		 // Location holder
		Chunk chunk = loc.getChunk();
		for (Entity entity : chunk.getEntities()) {
			if (entity instanceof Item) {
				Item item = (Item)entity;
				ItemStack is = item.getItemStack();

				if (pearl.validateItemStack(is)) {
					feedback.append(String.format("Found on ground at (%d,%d,%d)",
							entity.getLocation().getBlockX(),
							entity.getLocation().getBlockY(),
							entity.getLocation().getBlockZ()));
					return HolderVerifyResult.ON_GROUND;
				}
			}
		}
		feedback.append("On ground not in chunk");
		return HolderVerifyResult.ENTITY_NOT_IN_CHUNK;
	}
	
	@Override
	public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LocationHolder other = (LocationHolder) o;

		return loc.equals(other.loc);
	}
}
