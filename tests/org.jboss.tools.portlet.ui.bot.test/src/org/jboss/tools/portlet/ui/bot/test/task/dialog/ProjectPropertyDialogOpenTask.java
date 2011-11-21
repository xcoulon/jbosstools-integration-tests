package org.jboss.tools.portlet.ui.bot.test.task.dialog;

import org.jboss.tools.portlet.ui.bot.test.task.AbstractSWTTask;
import org.jboss.tools.ui.bot.ext.SWTBotFactory;
import org.jboss.tools.ui.bot.ext.gen.ActionItem;
import org.jboss.tools.ui.bot.ext.helper.ContextMenuHelper;
import org.jboss.tools.ui.bot.ext.view.PackageExplorer;

public class ProjectPropertyDialogOpenTask extends AbstractSWTTask{

	private String project;
	
	private String propertyPage;

	@Override
	public void perform() {
		SWTBotFactory.getOpen().viewOpen(ActionItem.View.JavaPackageExplorer.LABEL);
		
		PackageExplorer projectExplorer = SWTBotFactory.getPackageexplorer();
		projectExplorer.selectProject(project);
		
		ContextMenuHelper.clickContextMenu(projectExplorer.bot().tree(),
				"Properties");

		SWTBotFactory.getEclipse().waitForShell("Properties for " + project);
		getBot().tree().expandNode(propertyPage).select();		
	}	

	public void setProject(String project) {
		this.project = project;
	}
	
	public void setPropertyPage(String propertyPage) {
		this.propertyPage = propertyPage;
	}
}