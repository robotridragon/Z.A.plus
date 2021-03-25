
package net.mcreator.zombieapocalypse.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.IItemTier;

import net.mcreator.zombieapocalypse.ZombieApocalypseModElements;

@ZombieApocalypseModElements.ModElement.Tag
public class Fragmentite_tierSwordItem extends ZombieApocalypseModElements.ModElement {
	@ObjectHolder("zombie_apocalypse:fragmentite_tier_sword")
	public static final Item block = null;
	public Fragmentite_tierSwordItem(ZombieApocalypseModElements instance) {
		super(instance, 50);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new SwordItem(new IItemTier() {
			public int getMaxUses() {
				return 100;
			}

			public float getEfficiency() {
				return 4f;
			}

			public float getAttackDamage() {
				return 4.6f;
			}

			public int getHarvestLevel() {
				return 1;
			}

			public int getEnchantability() {
				return 7;
			}

			public Ingredient getRepairMaterial() {
				return Ingredient.fromStacks(new ItemStack(FragmentiteitemItem.block, (int) (1)));
			}
		}, 3, -2f, new Item.Properties().group(ItemGroup.COMBAT)) {
		}.setRegistryName("fragmentite_tier_sword"));
	}
}
