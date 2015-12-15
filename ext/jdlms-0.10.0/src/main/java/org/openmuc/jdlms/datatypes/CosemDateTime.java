/*
 * Copyright 2012-15 Fraunhofer ISE
 *
 * This file is part of jDLMS.
 * For more information visit http://www.openmuc.org
 *
 * jDLMS is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * jDLMS is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jDLMS.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package org.openmuc.jdlms.datatypes;

import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.TimeZone;

public class CosemDateTime implements CosemDateFormat {

	/**
	 * last three bytes of the final ocletString.
	 */
	private byte[] subOcletString;

	private final CosemDate date;
	private final CosemTime time;

	public enum ClockStatus {
		INVALID_VALUE(0x1),
		DUBTFUL_VALUE(0x2),
		DIFFERENT_CLOCK_BASE(0x4),
		INVALID_CLOCK_STATUS(0x8),
		DAYLIGHT_SAVING_ACTIVE(0x80);

		private byte flagMask;

		private ClockStatus(int bitmask) {
			this.flagMask = (byte) bitmask;
		}

		public static byte clockStatusToBitString(ClockStatus... clockStatus) {
			if (clockStatus.length > 5) {
				throw new IllegalArgumentException("Too many status flags set.");
			}

			byte bitString = 0;

			for (ClockStatus status : clockStatus) {
				bitString |= status.flagMask;
			}
			return bitString;
		}

		public static List<ClockStatus> clockStatusFrom(byte bitString) {
			List<ClockStatus> clockStatus = new LinkedList<ClockStatus>();
			for (ClockStatus statusFlag : ClockStatus.values()) {
				if ((bitString & statusFlag.flagMask) == statusFlag.flagMask) {
					clockStatus.add(statusFlag);
				}
			}

			return clockStatus;
		}
	}

	/**
	 * Constructs a a COSEM Date_Time.
	 * 
	 * @param year
	 *            the year from 0 to 0xffff.
	 * @param month
	 *            the month from 1 to 12. Set to 0xff if not specified.
	 * @param dayOfMonth
	 *            the day of the month starting from 1 to max 31. Set to 0xfe for the last day of a month and 0xfd for
	 *            the second last day of a month. Set to 0xff if not specified.
	 * @param hour
	 *            the hour from 0 to 23. 0xff if not specified.
	 * @param minute
	 *            the minute from 0 to 59. 0xff if not specified.
	 * @param second
	 *            the second from 0 to 59. 0xff if not specified.
	 * @param deviation
	 *            the deviation in minutes from local time to GMT. From -720 to 720. 0x8000 if not specified
	 * @param clockStatus
	 *            the clock status flags
	 * @throws IllegalArgumentException
	 *             if a parameter does not fit the range
	 */
	public CosemDateTime(int year, int month, int dayOfMonth, int hour, int minute, int second, int deviation,
			ClockStatus... clockStatus) {
		this(year, month, dayOfMonth, 0xff, hour, minute, second, 0xff, deviation, clockStatus);
	}

	/**
	 * Constructs a a COSEM Date_Time.
	 * 
	 * @param year
	 *            the year from 0 to 0xffff.
	 * @param month
	 *            the month from 1 to 12. Set to 0xff if not specified.
	 * @param dayOfMonth
	 *            the day of the month starting from 1 to max 31. Set to 0xfe for the last day of a month and 0xfd for
	 *            the second last day of a month. Set to 0xff if not specified.
	 * @param dayOfWeek
	 *            the day of a week from 1 to 7. 1 is Monday. Set to 0xff if not specified.
	 * @param hour
	 *            the hour from 0 to 23. 0xff if not specified.
	 * @param minute
	 *            the minute from 0 to 59. 0xff if not specified.
	 * @param second
	 *            the second from 0 to 59. 0xff if not specified.
	 * @param hundredths
	 *            the hundredths seconds from 0 to 99. 0xff if not specified.
	 * @param deviation
	 *            the deviation in minutes from local time to GMT. From -720 to 720. 0x8000 if not specified
	 * @param clockStatus
	 *            the clock status flags
	 * @throws IllegalArgumentException
	 *             if a parameter does not fit the range
	 */
	public CosemDateTime(int year, int month, int dayOfMonth, int dayOfWeek, int hour, int minute, int second,
			int hundredths, int deviation, ClockStatus... clockStatus) {
		this.date = new CosemDate(year, month, dayOfMonth, dayOfWeek);
		this.time = new CosemTime(hour, minute, second, hundredths);

		initFields(deviation, clockStatus);
	}

	/**
	 * Constructs a a COSEM Date_Time.
	 * 
	 * @param date
	 *            the date
	 * @param time
	 *            the time
	 * @param deviation
	 *            the deviation in minutes from local time to GMT. From -720 to 720. 0x8000 if not specified
	 * @param clockStatus
	 *            the clock status flags
	 * @throws IllegalArgumentException
	 *             if a parameter does not fit the range
	 */
	public CosemDateTime(CosemDate date, CosemTime time, int deviation, ClockStatus... clockStatus) {
		this.date = date;
		this.time = time;

		initFields(deviation, clockStatus);
	}

	public CosemDateTime(byte[] ocletString) {
		int offset = 0;
		this.date = new CosemDate(Arrays.copyOfRange(ocletString, offset, CosemDate.SIZE));

		offset += CosemDate.SIZE;
		this.time = new CosemTime(Arrays.copyOfRange(ocletString, offset, CosemTime.SIZE + offset));

		offset += 3;
		this.subOcletString = Arrays.copyOfRange(ocletString, offset, ocletString.length);
	}

	private void initFields(int deviation, ClockStatus... clockStatus) {
		validateDeviation(deviation);

		subOcletString = new byte[3];

		subOcletString[0] = (byte) ((deviation & 0xff00) >> 8);
		subOcletString[1] = (byte) (deviation & 0xff);
		subOcletString[2] = ClockStatus.clockStatusToBitString(clockStatus);
	}

	private void validateDeviation(int deviation) {
		if (deviation < -720 || deviation > 720 && deviation != 0x8000) {
			throw new IllegalArgumentException("Deviation is out of range.");
		}
	}

	@Override
	public byte[] ocletString() {
		byte[] returnList = new byte[size()];

		int offset = 0;
		byte[] dateOcletString = date.ocletString();
		returnList = append(returnList, dateOcletString, offset);

		offset += date.size();
		byte[] timeOcletString = time.ocletString();
		returnList = append(returnList, timeOcletString, offset);

		offset += time.size();
		returnList = append(returnList, this.subOcletString, offset);

		return returnList;
	}

	private byte[] append(byte[] returnList, byte[] listToAppend, int offset) {
		for (int i = offset; i < listToAppend.length + offset; i++) {
			returnList[i] = listToAppend[i - offset];
		}

		return returnList;
	}

	@Override
	public Calendar toCalendar() {
		Calendar dateCalendar = date.toCalendar();

		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("gmt"));
		cal.set(Calendar.YEAR, dateCalendar.get(Calendar.YEAR));
		cal.set(Calendar.MONTH, dateCalendar.get(Calendar.MONTH));
		cal.set(Calendar.DAY_OF_MONTH, dateCalendar.get(Calendar.DAY_OF_MONTH));

		Calendar timeCalendar = time.toCalendar();
		cal.set(Calendar.HOUR, timeCalendar.get(Calendar.HOUR));
		cal.set(Calendar.MINUTE, timeCalendar.get(Calendar.MINUTE));
		cal.set(Calendar.SECOND, timeCalendar.get(Calendar.SECOND));
		cal.set(Calendar.MILLISECOND, timeCalendar.get(Calendar.MILLISECOND));

		int timeZoneOffset = valueFor(Field.DEVIATION);
		cal.set(Calendar.ZONE_OFFSET, timeZoneOffset * 60000);

		return cal;
	}

	@Override
	public int size() {
		return 12;
	}

	@Override
	public int valueFor(Field field) {
		switch (field) {
		case DEVIATION:
			int deviation = this.subOcletString[0] << 8;
			deviation |= this.subOcletString[1] & 0xff;
			return deviation;

		case CLOCK_STATUS:
			return this.subOcletString[3];

		case HOUR:
		case MINUTE:
		case SECOND:
		case HUNDREDTHS:
			return this.time.valueFor(field);

		case YEAR:
		case MONTH:
		case DAY_OF_MONTH:
		case DAY_OF_WEEK:
			return this.date.valueFor(field);

		default:
			// can't occur
			throw new IllegalArgumentException();
		}

	}

}
