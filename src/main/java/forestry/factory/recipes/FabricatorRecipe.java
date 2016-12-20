/*******************************************************************************
 * Copyright (c) 2011-2014 SirSengir.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 *
 * Various Contributors including, but not limited to:
 * SirSengir (original work), CovertJaguar, Player, Binnie, MysteriousAges
 ******************************************************************************/
package forestry.factory.recipes;

import com.google.common.base.Preconditions;
import forestry.api.recipes.IFabricatorRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fluids.FluidStack;

public class FabricatorRecipe implements IFabricatorRecipe {
	private final ItemStack plan;
	private final FluidStack molten;
	private final NonNullList<NonNullList<ItemStack>> ingredients;
	private final ItemStack result;

	public FabricatorRecipe(ItemStack plan, FluidStack molten, ItemStack result, NonNullList<NonNullList<ItemStack>> ingredients) {
		Preconditions.checkNotNull(plan);
		Preconditions.checkNotNull(molten);
		Preconditions.checkNotNull(result);
		Preconditions.checkArgument(!result.isEmpty());
		Preconditions.checkNotNull(ingredients);
		this.plan = plan;
		this.molten = molten;
		this.result = result;
		this.ingredients = ingredients;
	}

	@Override
	public NonNullList<NonNullList<ItemStack>> getIngredients() {
		return ingredients;
	}

	@Override
	public int getWidth() {
		return 3;
	}

	@Override
	public int getHeight() {
		return 3;
	}

	@Override
	public ItemStack getPlan() {
		return plan;
	}

	@Override
	public FluidStack getLiquid() {
		return molten;
	}

	@Override
	public ItemStack getRecipeOutput() {
		return result;
	}
}
