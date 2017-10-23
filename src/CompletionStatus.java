public enum CompletionStatus {
	COMPLETED, NO_DRIVER, INSUFFICIENT_FUNDS;
	
	public String toString() {
		switch(this) {
		case NO_DRIVER: return "Cancelled: No Driver";
		case INSUFFICIENT_FUNDS: return "Cancelled: Insufficient Funds";
		default: return "Completed";
		}
	}
}