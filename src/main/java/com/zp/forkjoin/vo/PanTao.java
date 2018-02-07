package com.zp.forkjoin.vo;

public class PanTao {
	
	private final Color color;
	
	private final Size size;
	
	private final int year;

	public PanTao(Color color, Size size, int year) {
		super();
		this.color = color;
		this.size = size;
		this.year = year;
	}

	public Color getColor() {
		return color;
	}

	public Size getSize() {
		return size;
	}

	public int getYear() {
		return year;
	}

	@Override
	public String toString() {
		return "PanTao [color=" + color + ", size=" + size + ", year=" + year
				+ "]";
	}
	
}
