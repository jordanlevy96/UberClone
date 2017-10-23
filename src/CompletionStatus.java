/**
 * enum for possible outcomes of a Trip
 * @author Jordan
 *
 */
public enum CompletionStatus {
	IN_PROGRESS, COMPLETED, NO_DRIVER, INSUFFICIENT_FUNDS;
	
	public String toString() {
		switch(this) {
		case IN_PROGRESS: return "In Progress";
		case NO_DRIVER: return "Cancelled: No Driver";
		case INSUFFICIENT_FUNDS: return "Cancelled: Insufficient Funds";
		default: return "Completed";
		}
	}
}