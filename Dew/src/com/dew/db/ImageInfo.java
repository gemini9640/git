package com.dew.db;

public class ImageInfo {
    private Integer id;

	private Integer productid;

	private String oldName;

	private String newName;

	private Integer cover;

	private Integer index;

	public ImageInfo(Integer id, Integer productid, String oldName,
			String newName, Integer cover, Integer index) {
		this.id = id;
		this.productid = productid;
		this.oldName = oldName;
		this.newName = newName;
		this.cover = cover;
		this.index = index;
	}

	public ImageInfo() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id; 
	}

	public Integer getProductid() {
		return productid;
	}

	public void setProductid(Integer productid) {
		this.productid = productid;
	}

	public String getOldName() {
		return oldName;
	}

	public void setOldName(String oldName) {
		this.oldName = oldName == null ? null : oldName.trim();
	}

	public String getNewName() {
		return newName;
	}

	public void setNewName(String newName) {
		this.newName = newName == null ? null : newName.trim();
	}

	public Integer getCover() {
		return cover;
	}

	public void setCover(Integer cover) {
		this.cover = cover;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", id=").append(id);
		sb.append(", productid=").append(productid);
		sb.append(", oldName=").append(oldName);
		sb.append(", newName=").append(newName);
		sb.append(", cover=").append(cover);
		sb.append(", index=").append(index);
		sb.append("]");
		return sb.toString();
	}

}