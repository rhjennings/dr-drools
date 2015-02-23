package mx.dr.drools.generator;
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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import mx.dr.drools.exception.DRDroolsException;

import com.android.dx.dex.file.DexFile;

public final class DRDroolsApkGenerator {
	private File file;
	private DexFile dexFile;
	
	private DRDroolsApkGenerator() {}
	
	public static DRDroolsApkGenerator newGenerator() {
		return new DRDroolsApkGenerator();
	}
	
	public DRDroolsApkGenerator withFile(File file) {
		this.file = file;
		return this;
	}
	
	public DRDroolsApkGenerator withDexFile(DexFile dexFile) {
		this.dexFile = dexFile;
		return this;
	}
	
	public void generate() {
		ZipOutputStream zos = null;

		try {
			zos = new ZipOutputStream(new FileOutputStream(file));
			ZipEntry entry = new ZipEntry("classes.dex");
			zos.putNextEntry(entry);
			dexFile.writeTo(zos, null, false);
		} catch (FileNotFoundException e) {
			throw new DRDroolsException("There was an error finding the file.", e);
		} catch (IOException e) {
			throw new DRDroolsException("There was an IOException.", e);
		} finally {
			try {
				if (zos != null) {
					zos.closeEntry();
					zos.close();
				}
			} catch (IOException e) {/* ignore */}
		}
	}
}
