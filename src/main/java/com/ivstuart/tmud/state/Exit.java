package com.ivstuart.tmud.state;

public class Exit extends BasicThing {

	private static final long serialVersionUID = 520626372370843140L;
	private String destinationRoomId;
	private transient Room destinationRoom; // optimization
	private Door door;
	
	private String[] standardExits = {"north","south","east","west","up","down"};
	private boolean isSpecial;

	public Exit() {
	}

	public Exit(String id, String destination_) {
		this.setId(id);
		isSpecial = isSpecial(id);
		destinationRoomId = destination_;
	}

	public String getDestination() {
		return destinationRoomId;
	}

	public Room getDestinationRoom() {
		if (destinationRoom == null) {
			destinationRoom = World.getRoom(destinationRoomId);
		}
		return destinationRoom;
	}

	public Door getDoor() {
		return door;
	}

	public boolean isGuarded() {

		for (Mob mob : getDestinationRoom().getMobs()) {
			if (mob.isGuarding(this.getId())) {
				return true;
			}
		}
		return false;
	}

	public boolean isScanable() {
		if (door == null) {
			return true;
		}
		return door.getState().isScanable();
	}

	public String look() {

		if (door == null) {
			return this.getId();
		}

		String exitDescription = door.state.getBegin() + this.getId()
				+ door.state.getEnd();

		if (this.isHidden()) {
			exitDescription += "(hidden)";
		}

		if (this.isInvisible()) {
			exitDescription += "(invisible)";
		}

		return exitDescription;

	}

	public void setDoor(Door door_) {
		door = door_;
	}
	
	public boolean isSpecial() {
		return isSpecial;
	}

	public boolean isSpecial(String id) {
		for (String name : standardExits) {
			if (id.equals(name)) {
				return false;
			}
		}
		return true;
	}
}
