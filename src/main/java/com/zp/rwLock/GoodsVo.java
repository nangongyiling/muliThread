package com.zp.rwLock;

public class GoodsVo {

	private final String id;
	//总销售数
	private int totalSaleNumber;
	//当前库存
	private int depotNumber;
	
	public GoodsVo(String id,int totalSaleNumber,int depotNumber){
		this.id=id;
		this.totalSaleNumber = totalSaleNumber;
		this.depotNumber = depotNumber;
	}

	public int getTotalSaleNumber() {
		return totalSaleNumber;
	}

	public int getDepotNumber() {
		return depotNumber;
	}
	public void setGoodsVoNumber(int changeNumber){
		this.totalSaleNumber+=changeNumber;
		this.depotNumber-=changeNumber;
	}
}
