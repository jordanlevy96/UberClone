public enum Status {
	OFFLINE, AVAILABLE, OCCUPIED;
	
	public String toString() {
		switch(this) {
		case AVAILABLE: return "Available";
		case OCCUPIED: return "Occupied";
		default: return "Offline";
		}
	}
}