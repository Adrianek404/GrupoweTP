package pl.patrykv220.grupowetpcore.task;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import pl.yspar.core.sidebar.Sidebar;
import pl.yspar.core.basic.*;



public class RefreshThread extends Thread {
	private Object locker;
	private List<Action> actions;
	private static RefreshThread instance;
	private int lastId;

	public RefreshThread() {
		final boolean lastId = false;
		this.lastId = (lastId ? 1 : 0);
		this.actions = new ArrayList<Action>();
		this.locker = new Object();
		(RefreshThread.instance = this).setName("RefreshThread");
	}

	public static RefreshThread getInstance() {
		return RefreshThread.instance;
	}

	public static void action(final ActionType type) {
		action(type, new Object[0]);
	}

	public void actionAdd(final ActionType type, final Object[] objects) {
		++this.lastId;
		this.actions.add(new Action(this.lastId, type, objects));
		synchronized (getInstance().locker) {
			getInstance().locker.notify();
		}
		// monitorexit(getInstance().locker)
	}

	public static void action(final ActionType type, final Object... objects) {
		getInstance().actionAdd(type, objects);
	}

	@Override
	public void run() {
		while (true) {
			try {
				while (true) {
					final List currentActions;
					final List list = currentActions = new ArrayList(this.actions);
					this.actions.clear();
					final Iterator<Action> iterator2;
					Iterator<Action> iterator = iterator2 = list.iterator();
					while (iterator.hasNext()) {
						final Action action = iterator2.next();
						switch (action.getActionType().ordinal()) {
						case 13: {
							Sidebar.globalUpdate();
							iterator = iterator2;
							continue;
						}
						case 14: {
							((User) action.getObjects()[0]).getSidebar().restart();
							iterator = iterator2;
							continue;
						}
						case 11: {
							final Action action2 = action;
							((User) action2.getObjects()[0]).getSidebar().create();
							iterator = iterator2;
							continue;
						}
						case 12: {
							((User) action.getObjects()[0]).getSidebar().update();
							break;
						}
						}
						iterator = iterator2;
					}
					synchronized (this.locker) {
						this.locker.wait();
					}
					// monitorexit(this.locker)
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
				continue;
			}
		}
	}
}
