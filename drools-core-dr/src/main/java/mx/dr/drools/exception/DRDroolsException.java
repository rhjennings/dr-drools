package mx.dr.drools.exception;
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
public class DRDroolsException extends RuntimeException {
	private static final long serialVersionUID = 1785017337583987424L;

	public DRDroolsException() {
		super();
	}

	public DRDroolsException(String message) {
		super(message);
	}

	public DRDroolsException(Throwable cause) {
		super(cause);
	}

	public DRDroolsException(String message, Throwable cause) {
		super(message, cause);
	}
}
