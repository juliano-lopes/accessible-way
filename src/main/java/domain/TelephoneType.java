package domain;

public enum TelephoneType implements TelephoneDescriptionType {
	CELL {
		@Override
		public String getDescriptionType() {
			return "Cell Phone";
		}

		@Override
		public int getValue() {
			return 0;
		}

	},
	PHONE {
		@Override
		public String getDescriptionType() {
			return "Telephone";
		}

		@Override
		public int getValue() {
			return 1;
		}
	};

}
