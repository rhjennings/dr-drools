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
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import mx.dr.drools.exception.DRDroolsException;
import android.content.Context;
import android.content.res.AssetManager;

public final class AndroidAssetUtils {
	private AndroidAssetUtils() {}
	
	public static void moveAssetsToData(Context context, String assetFolder, String dataFolder, String fileExt,
			int fileMode) {
		AssetManager manager = context.getAssets();
		InputStream is = null;
		FileOutputStream fos = null;
		
		byte[] bytes = new byte[1024];
		int bytesRead = 0;
		
		try {
			for (String fileName : manager.list(assetFolder)) {
				if (fileName.endsWith(fileExt)) {
					is = manager.open(assetFolder + File.separator + fileName);
					fos = context.openFileOutput(fileName, fileMode);
					while ((bytesRead = is.read()) != -1) {
						fos.write(bytes, 0, bytesRead);
					}
					fos.close();
					is.close();
				}
			}
		} catch (IOException e) {
			throw new DRDroolsException("There was an error moving files.", e);
		}
	}
}
