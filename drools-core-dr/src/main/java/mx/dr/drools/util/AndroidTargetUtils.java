package mx.dr.drools.util;
/*
 * Copyright (C) 2011-2012 Jorge Luis Martinez Ramirez
 * Copyright (C) 2015 Ron Jennings
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 * Author: Jorge Luis Martinez Ramirez, Ron Jennings
 * Email: jorgemfk1@gmail.com, rhjennings@gmail.com
 */
public final class AndroidTargetUtils {
	public static final int DEFAULT_VERSION = 8;
	private static int targetVersion = DEFAULT_VERSION;
	
	private AndroidTargetUtils() {}
	
	public static int getTargetVersion() {
		return targetVersion;
	}

	public static void setTargetVersion(int targetVersion) {
		AndroidTargetUtils.targetVersion = targetVersion;
	}
}
