package domain;

public enum TelephoneType {
	CELL(0, "Cell Phone"), PHONE(1, "Phone");
	private int type;
	private String description;

	TelephoneType(int type, String description) {
		this.type = type;
		this.description = description;
	}

	public int getType() {
		return type;
	}

	public String getDescription() {
		return description;
	}

	public String getDescriptionByType(int type) {
		for (TelephoneType telephone : TelephoneType.values()) {
			if (telephone.getType() == type) {
				return telephone.getDescription();
			}
		}
		return "";

	}
}
