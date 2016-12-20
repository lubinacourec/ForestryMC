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
package forestry.core.network.packets;

import java.io.IOException;

import forestry.api.core.IErrorLogic;
import forestry.api.core.IErrorLogicSource;
import forestry.core.network.ForestryPacket;
import forestry.core.network.IForestryPacketClient;
import forestry.core.network.IForestryPacketHandlerClient;
import forestry.core.network.PacketBufferForestry;
import forestry.core.network.PacketIdClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class PacketErrorUpdateEntity extends ForestryPacket implements IForestryPacketClient {
	private final Entity entity;
	private final IErrorLogic errorLogic;

	public PacketErrorUpdateEntity(Entity entity, IErrorLogicSource errorLogicSource) {
		this.entity = entity;
		this.errorLogic = errorLogicSource.getErrorLogic();
	}

	@Override
	public PacketIdClient getPacketId() {
		return PacketIdClient.ERROR_UPDATE_ENTITY;
	}

	@Override
	protected void writeData(PacketBufferForestry data) throws IOException {
		data.writeEntityById(entity);
		errorLogic.writeData(data);
	}

	public static class Handler implements IForestryPacketHandlerClient {
		@Override
		public void onPacketData(PacketBufferForestry data, EntityPlayer player) throws IOException {
			Entity entity = data.readEntityById(player.world);
			if (entity instanceof IErrorLogicSource) {
				IErrorLogicSource errorSourceTile = (IErrorLogicSource) entity;
				IErrorLogic errorLogic = errorSourceTile.getErrorLogic();
				errorLogic.readData(data);
			}
		}
	}
}
