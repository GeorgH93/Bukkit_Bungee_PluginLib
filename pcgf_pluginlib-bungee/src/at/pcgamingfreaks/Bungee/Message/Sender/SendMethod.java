/*
 *   Copyright (C) 2020 GeorgH93
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package at.pcgamingfreaks.Bungee.Message.Sender;

import at.pcgamingfreaks.Message.Sender.IMetadata;
import at.pcgamingfreaks.Message.Sender.ISendMethod;

import net.md_5.bungee.api.connection.ProxiedPlayer;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import lombok.Getter;

import java.util.Collection;
import java.util.function.Supplier;

public enum SendMethod implements ISendMethod, Sender
{
	CHAT(new ChatSender()),
	TITLE(new TitleSender(), TitleMetadata.class, TitleMetadata::new),
	ACTION_BAR(new ActionBarSender()),
	//BOSS_BAR(new BossBarSender()), //TODO
	DISABLED(new DisabledSender());

	@Getter @NotNull private final Sender sender;
	@Getter @Nullable private final Class<? extends IMetadata> metadataClass;
	@Getter @Nullable private final Supplier<? extends IMetadata> metadataSupplier;

	SendMethod(@NotNull Sender sender)
	{
		this(sender, null, null);
	}

	SendMethod(@NotNull Sender sender, @Nullable Class<? extends IMetadata> metadataClass, @Nullable Supplier<? extends IMetadata> metadataSupplier)
	{
		this.sender = sender;
		this.metadataClass = metadataClass;
		this.metadataSupplier = metadataSupplier;
	}

	@Override
	public @Nullable IMetadata parseMetadata(final @NotNull String json)
	{
		if(metadataSupplier == null) return null;
		IMetadata metadata = metadataSupplier.get();
		metadata.parseJson(json);
		return metadata;
	}

	@Override
	public void doSend(@NotNull ProxiedPlayer proxiedPlayer, @NotNull String json)
	{
		sender.doSend(proxiedPlayer, json);
	}

	@Override
	public void doSend(@NotNull ProxiedPlayer proxiedPlayer, @NotNull String json, @Nullable Object optionalMetadata)
	{
		sender.doSend(proxiedPlayer, json, optionalMetadata);
	}

	@Override
	public void doSend(@NotNull Collection<? extends ProxiedPlayer> proxiedPlayers, @NotNull String json)
	{
		sender.doSend(proxiedPlayers, json);
	}

	@Override
	public void doSend(@NotNull Collection<? extends ProxiedPlayer> proxiedPlayers, @NotNull String json, @Nullable Object optionalMetadata)
	{
		sender.doSend(proxiedPlayers, json, optionalMetadata);
	}

	@Override
	public void doBroadcast(@NotNull String json)
	{
		sender.doBroadcast(json);
	}

	@Override
	public void doBroadcast(@NotNull String json, @Nullable Object optionalMetadata)
	{
		sender.doBroadcast(json, optionalMetadata);
	}
}