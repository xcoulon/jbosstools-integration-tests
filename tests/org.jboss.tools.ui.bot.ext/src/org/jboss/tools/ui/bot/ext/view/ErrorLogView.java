package org.jboss.tools.ui.bot.ext.view;

import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.jboss.tools.ui.bot.ext.gen.ActionItem;
import org.jboss.tools.ui.bot.ext.helper.ContextMenuHelper;

/**
 * Error Log view SWTBot Extensions
 * 
 * @author jpeterka
 * 
 */
public class ErrorLogView extends ViewBase {

	public void logMessages() {
		SWTBotTreeItem[] items = getView().bot().tree().getAllItems();
		for (SWTBotTreeItem i : items)
			passTree(i);
	}

	private void passTree(SWTBotTreeItem item) {
		int i = 0;
		item.expand();
		while (!item.getNodes().isEmpty() && i < item.getNodes().size()) {
			passTree(item.getNode(i));
			i++;
		}
		log.info(item.getText());
	}

	public void clear() {
		ContextMenuHelper.clickContextMenu(getView().bot().tree(),
				"Clear Log Viewer");
	}

	private SWTBotView getView() {
		SWTBotView view = open.viewOpen(ActionItem.View.GeneralErrorLog.LABEL);
		return view;
	}

	public int getRecordCount() {
		int count = getView().bot().tree().getAllItems().length;
		return count;
	}

}