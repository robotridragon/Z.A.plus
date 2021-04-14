
package net.mcreator.zombieapocalypse;

import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;

import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;

import net.mcreator.zombieapocalypse.item.LiquidredstoneItem;

@ZombieApocalypseModElements.ModElement.Tag
public class LiquidredrecipeBrewingRecipe extends ZombieApocalypseModElements.ModElement {
	public LiquidredrecipeBrewingRecipe(ZombieApocalypseModElements instance) {
		super(instance, 118);
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		BrewingRecipeRegistry.addRecipe(Ingredient.fromStacks(new ItemStack(Items.WATER_BUCKET, (int) (1))),
				Ingredient.fromStacks(new ItemStack(Items.REDSTONE, (int) (1))), new ItemStack(LiquidredstoneItem.block, (int) (1)));
	}
}
