package modelo;

import java.util.Date;
public class clsUserType {

	private int id;
	private String userType, description;
	private Date createDate, deleteDate;
	private String icon;

	public clsUserType(int id, String userType, String description, Date createDate, Date deleteDate, String icon) {
		this.id = id;
		this.userType= userType;
		this.description = description;
		this.createDate = createDate;
		this.deleteDate = deleteDate;
		this.icon=icon;
	}

	public int getId() {
		return this.id; }

	public void setId(int id) {
		this.id = id; }

	public String getUserType() {
		return this.userType; 	}

	public void setUserType(String userType) {
		this.userType = userType; 	}

	public String getDescription() {
		return this.description; 	}

	public void setDescription(String description) {
		this.description = description; 	}

	public Date getCreateDate() {
		return this.createDate; 	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate; 	}

	public Date getDeleteDate() {
		return this.deleteDate; 	}

	public void setDeleDate(Date deleteDate) {
		this.deleteDate = deleteDate; 	}
	
	public String getIcon() {
		return this.icon; 	}

	public void setIcon(String icon) {
		this.icon = icon; 	}

	
	
}