package im.dnn.BlockEntities.Models;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.inventory.ItemStack;

public class BlockEntity {
    private final Material BROKEN_MATERIAL = Material.AIR;
    private final Material HITBOX_MATERIAL = Material.BARRIER;

    private Location location;
    private ItemDisplay entity;
    private BlockItem blockItem;

    public BlockEntity (Location location, BlockItem blockItem) {
        this.placeBlock(location, blockItem);
    }

    private void placeBlock (Location location, BlockItem blockItem) {
        World world = location.getWorld();

        world.setBlockData(location, HITBOX_MATERIAL.createBlockData());
        Location entityLocation = getLocationFromBlock(location);

        world.spawn(entityLocation, ItemDisplay.class, entity -> {
            ItemStack item = blockItem.getItem(1);
            entity.setItemStack(item);
            entity.setPersistent(true);
            entity.setInvulnerable(true);

            this.entity = entity;
            this.location = location;
            this.blockItem = blockItem;
        });
    }

    public void breakBlock () {
        this.entity.remove();
        World world = this.location.getWorld();
        world.setBlockData(this.location, BROKEN_MATERIAL.createBlockData());
    }

    public BlockItem getBlockItem () {
        return  this.blockItem;
    }

    private Location getLocationFromBlock (Location location) {
        Location entityLocation = location.clone();
        entityLocation.setX( location.getX() + 0.5 );
        entityLocation.setY( location.getY() + 0.5 );
        entityLocation.setZ( location.getZ() + 0.5 );

        return entityLocation;
    }
}
