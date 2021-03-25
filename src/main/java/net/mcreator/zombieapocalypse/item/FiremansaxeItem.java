
package net.mcreator.zombieapocalypse.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.IItemTier;
import net.minecraft.item.AxeItem;

import net.mcreator.zombieapocalypse.ZombieApocalypseModElements;

@ZombieApocalypseModElements.ModElement.Tag
public class FiremansaxeItem extends ZombieApocalypseModElements.ModElement {
	@ObjectHolder("zombie_apocalypse:firemansaxe")
	public static final Item block = null;
	public FiremansaxeItem(ZombieApocalypseModElements instance) {
		super(instance, 29);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new AxeItem(new IItemTier() {
			public int getMaxUses() {
				return 250;
			}

			public float getEfficiency() {
				return 7.5f;
			}

			public float getAttackDamage() {
				return 5f;
			}

			public int getHarvestLevel() {
				return 2;
			}

			public int getEnchantability() {
				return 8;
			}

			public Ingredient getRepairMaterial() {
				return Ingredient.EMPTY;
			}
		}, 1, -3.1f, new Item.Properties().group(ItemGroup.TOOLS)) {
		}.setRegistryName("firemansaxe"));
	}
}
