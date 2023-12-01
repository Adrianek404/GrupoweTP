package pl.patrykv220.grupowetpcore.task;

public class Action {
	private int id;
	private Object[] objects;
	private ActionType actionType;

	public Action(final int id, final ActionType actionType, final Object[] objects) {
		this.id = id;
		this.actionType = actionType;
		this.objects = objects;
	}

	public void setObjects(final Object[] objects) {
		this.objects = objects;
	}

	public Object[] getObjects() {
		return this.objects;
	}

	public int getId() {
		return this.id;
	}

	public void setActionType(final ActionType actionType) {
		this.actionType = actionType;
	}

	public ActionType getActionType() {
		return this.actionType;
	}

	public void setId(final int id) {
		this.id = id;
	}
}
