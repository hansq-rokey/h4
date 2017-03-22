package com.papabear.order.entity.enums;

/**
 * 房子内标签
 * @author yaoweiguo
 *
 */
public enum HouseTagEnum {
	
	/**客厅*/
	PARLOUR(1,"客厅"),
	MASTER_BEDROOM(2,"主卧"),
	SECOND_BEDROOM(3,"次卧"),
	STUDY(4,"书房"),
	PASSAGE(5,"走廊"),
	BALCONY(6,"餐厅"),
	OTHER(7,"其他");
	
	private Integer tagCode;
	private String tagName;
	public Integer getTagCode() {
		return tagCode;
	}
	public String getTagName() {
		return tagName;
	}
	private  HouseTagEnum(Integer tagCode, String tagName) {
		this.tagCode = tagCode;
		this.tagName = tagName;
	}
	
	public static String getTagName(Integer tagCode){
		if(tagCode==null)
			return null;
		for(HouseTagEnum ht:HouseTagEnum.values()){
			if(ht.tagCode.intValue()==tagCode.intValue()){
				return ht.getTagName();
			}
		}
		return OTHER.getTagName();
	}
}
