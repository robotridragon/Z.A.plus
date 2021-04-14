
package net.mcreator.zombieapocalypse.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.IItemTier;

import net.mcreator.zombieapocalypse.ZombieApocalypseModElements;

@ZombieApocalypseModElements.ModElement.Tag
public class FlinthatchetItem extends ZombieApocalypseModElements.ModElement {
	@ObjectHolder("zombie_apocalypse:flinthatchet")
	public static final Item block = null;
	public FlinthatchetItem(ZombieApocalypseModElements instance) {
		super(instance, 163);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new PickaxeItem(new IItemTier() {
			public int getMaxUses() {
				return 64;
			}

			public float getEfficiency() {
				return 1.5f;
			}

			public float getAttackDamage() {
				return 0f;
			}

			public int getHarvestLevel() {
				return 0;
			}

			public int getEnchantability() {
				return 2;
			}

			public Ingredient getRepairMaterial() {
				return Ingredient.EMPTY;
			}
		}, 1, -2.5999999999999999f, new Item.Properties().group(ItemGroup.TOOLS)) {
		}.setRegistryName("flinthatchet"));
	}
}
