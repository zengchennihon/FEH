package org.feh.model.functions;
public class CheckMergeResult {
	private boolean flag;
	private int firstRow;
	private int lastRow;
	private int firstColumn;
	private int lastColumn;
	
	public CheckMergeResult() {
	}

	public CheckMergeResult(boolean flag, int firstRow, int lastRow, int firstColumn, int lastColumn) {
		this.flag = flag;
		this.firstRow = firstRow;
		this.lastRow = lastRow;
		this.firstColumn = firstColumn;
		this.lastColumn = lastColumn;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public int getFirstRow() {
		return firstRow;
	}

	public void setFirstRow(int firstRow) {
		this.firstRow = firstRow;
	}

	public int getLastRow() {
		return lastRow;
	}

	public void setLastRow(int lastRow) {
		this.lastRow = lastRow;
	}

	public int getFirstColumn() {
		return firstColumn;
	}

	public void setFirstColumn(int firstColumn) {
		this.firstColumn = firstColumn;
	}

	public int getLastColumn() {
		return lastColumn;
	}

	public void setLastColumn(int lastColumn) {
		this.lastColumn = lastColumn;
	}

}