package mx.dr.drools.util;
/*
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
 * Author: Ron Jennings
 * Email: rhjennings@gmail.com
 */
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AndroidFileUtilsTest {
	private final static String PACKAGE_NAME = "mx.dr.drools";
	private final static String EXPECTED_FOLDER = "/data/data/" + PACKAGE_NAME + "/files/";
	
	@Before
	public void setUp() throws Exception {
		AndroidFileUtils.setPackageName(PACKAGE_NAME);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetFilePath() {
		assertEquals("The folder is not correct", EXPECTED_FOLDER, AndroidFileUtils.getFilePath());
	}
}
