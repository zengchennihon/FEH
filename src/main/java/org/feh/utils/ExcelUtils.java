package org.feh.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.feh.enums.FieldType;
import org.feh.model.functions.CheckMergeResult;

public class ExcelUtils<T> {

	@SuppressWarnings("unchecked")
	public static <T> List<T> resolveExcel(Class<?> clazz, Workbook workbook, String... fieldsName) {
		List<T> ts = new ArrayList<>();
		Iterator<Sheet> sheetIterator = workbook.sheetIterator();
		while (sheetIterator.hasNext()) {
			Sheet sheet = sheetIterator.next();
			for (int i = 0; i < sheet.getLastRowNum() + 1; i++) {
				Row row = sheet.getRow(i);
				try {
					T t = (T) clazz.newInstance();
					int cellNum = row.getPhysicalNumberOfCells();
					Field[] _fields = null;
					if (fieldsName != null && fieldsName.length > 0) {
					} else {
						_fields = clazz.getDeclaredFields();
					}
					for (int j = 0; j < cellNum; j++) {
						CheckMergeResult mergeResult = checkMergeCell(sheet, i, j);
						Cell cell = row.getCell(j);
						String cellValue = getCellValue(cell);
						if (mergeResult.isFlag()) {
							cellValue = getCellValue(sheet.getRow(i).getCell(j));
						}
						Field _field = null;
						if (_fields == null) {
							if (j >= fieldsName.length)
								break;
							_field = clazz.getDeclaredField(fieldsName[j]);
						} else {
							if (j >= _fields.length)
								break;
							_field = _fields[j];
						}
						_field.setAccessible(true);
						setFieldValue(t, cellValue, _field);
						_field.setAccessible(false);
					}
					ts.add(t);
				} catch (InstantiationException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
					e.printStackTrace();
				}
			}
		}
		return ts;
	}

	public static <T> Workbook createWorkbook(List<T> ts) {
		Workbook workbook = new HSSFWorkbook();
		if (ts == null || ts.size() == 0)
			return workbook;
		Class<?> clazz = ts.get(0).getClass();
		Field fields[] = clazz.getDeclaredFields();
		Sheet sheet = workbook.createSheet();
		ts.forEach(t -> {
			Row row = sheet.createRow(ts.indexOf(t));
			for (Field _field : fields) {
				_field.setAccessible(true);
				try {
					Object _obj = _field.get(t);
					Cell cell = row.createCell(arrayIndexOf(fields, _field));
					setCellValue(_obj, cell, sheet);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
				_field.setAccessible(false);
			}
		});
		return workbook;
	}

	private static void setCellValue(Object _obj, Cell cell, Sheet sheet) {
		if (_obj instanceof Boolean) {
			cell.setCellValue((boolean) _obj);
		} else if (_obj instanceof Calendar) {
			cell.setCellValue((Calendar) _obj);
		} else if (_obj instanceof Date) {
			cell.setCellValue(DateUtils.getDateStr((Date) _obj, 0));
		} else if (_obj instanceof Double) {
			cell.setCellValue((double) _obj);
		} else if (_obj instanceof RichTextString) {
			cell.setCellValue((RichTextString) _obj);
		} else {
			cell.setCellValue(String.valueOf(_obj));
		}
		if (_obj.toString().getBytes().length * 256 > sheet.getColumnWidth(cell.getColumnIndex())) {
			sheet.setColumnWidth(cell.getColumnIndex(), _obj.toString().getBytes().length * 256);
		}
	}

	private static String getCellValue(Cell cell) {
		String result = "";
		switch (cell.getCellTypeEnum()) {
		case _NONE:
		case BLANK:
			break;
		case BOOLEAN:
			result = String.valueOf(cell.getBooleanCellValue());
			break;
		case ERROR:
			result = String.valueOf(cell.getErrorCellValue());
			break;
		case FORMULA:
			result = cell.getCellFormula();
			break;
		case NUMERIC:
			boolean isDate = DateUtil.isCellDateFormatted(cell);
			if (isDate) {
				result = DateUtils.getDateStr(cell.getDateCellValue(), 0);
			} else {
				result = String.valueOf(cell.getNumericCellValue());
			}
			break;
		case STRING:
			result = cell.getStringCellValue();
			break;
		default:
			break;
		}
		return result;
	}

	private static <T> void setFieldValue(T t, String cellValue, Field _field) throws IllegalAccessException {
		Class<?> type = _field.getType();
		switch (FieldType.getKeyByType(type.getSimpleName())) {
		case BYTE:
			_field.set(t, Byte.valueOf(cellValue));
			break;
		case SHORT:
			_field.set(t, Short.valueOf(cellValue));
			break;
		case INTEGER:
			_field.set(t, Integer.valueOf(cellValue));
			break;
		case FLOAT:
			_field.set(t, Float.valueOf(cellValue));
			break;
		case LANG:
			_field.set(t, Long.valueOf(cellValue));
			break;
		case DOUBLE:
			_field.set(t, Double.valueOf(cellValue));
			break;
		case CHARACTER:
			_field.setChar(t, cellValue.charAt(0));
			break;
		case BOOLEAN:
			_field.set(t, Boolean.valueOf(cellValue));
			break;
		case STRING:
		case OBJECT:
			_field.set(t, cellValue);
			break;
		case DATE:
			_field.set(t, DateUtils.getDate(cellValue, 0));
			break;
		default:
			break;
		}
	}

	@SuppressWarnings("unused")
	private static CellStyle getCellStyle(HSSFWorkbook workbook) {
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		HSSFFont font = workbook.createFont();

		return null;
	}

	private static int arrayIndexOf(Object[] objs, Object obj) {
		for (int i = 0; i < objs.length; i++) {
			if (obj instanceof Integer)
				return objs[i] == obj ? i : -1;
			if (obj instanceof String)
				return objs[i].equals(obj) ? i : -1;
			if (obj instanceof Date)
				return ((Date) objs[i]).compareTo((Date) obj) == 0 ? i : -1;
			if (objs[i].toString().equals(obj.toString()))
				return i;
		}
		return -1;
	}

	private static CheckMergeResult checkMergeCell(Sheet sheet, int rowI, int columnI) {
		int mergeCount = sheet.getNumMergedRegions();
		for (int i = 0; i < mergeCount; i++) {
			CellRangeAddress rangeAddress = sheet.getMergedRegion(i);
			int firstRow = rangeAddress.getFirstRow();
			int lastRow = rangeAddress.getLastRow();
			int firstColumn = rangeAddress.getFirstColumn();
			int lastColumn = rangeAddress.getLastColumn();
			if (rowI >= firstRow && rowI <= lastRow)
				if (columnI >= firstColumn && columnI <= lastColumn)
					return new CheckMergeResult(true, firstRow, lastRow, firstColumn, lastColumn);
		}
		return new CheckMergeResult(false, 0, 0, 0, 0);
	}

}
