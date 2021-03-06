package org.jboss.tools.ui.bot.ext.zest;

import static org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory.withMnemonic;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.WidgetResult;
import org.eclipse.swtbot.swt.finder.widgets.AbstractSWTBot;
import org.hamcrest.Matcher;

/**
 * Context menu bot for Zest widgets
 * 
 * @author jpeterka
 * 
 */
public class SWTBotZestContextMenu extends AbstractSWTBot<Control> {

	/**
	 * Default Constructor
	 * 
	 * @param control
	 */
	SWTBotZestContextMenu(Control control) {
		super(control);
	}

	/**
	 * Invokes context menu sequence
	 * 
	 * @param texts
	 */
	public void clickMenu(final String... texts) {
		log.debug("Clicking graph menu: ");

		// Run in UI Thread
		final MenuItem menuItem = UIThreadRunnable
				.syncExec(new WidgetResult<MenuItem>() {
					public MenuItem run() {
						// Get menu from control

						Menu menu = widget.getMenu();

						MenuItem lastMenuItem = null;
						lastMenuItem = getLastMenuItem(texts, menu);

						log.debug("Final menu returned");
						// if last menu found, click and hide
						if (lastMenuItem != null) {
							// Click
							clickOnMenuItem(lastMenuItem);
							// Hide
							hideMenu(lastMenuItem.getParent());
						}
						return lastMenuItem;
					}
				});
		if (menuItem == null)
			throw new WidgetNotFoundException("Unable to find menuitem");

	}

	/**
	 * Gets last menitem on menuitem hierary. UIThread methods
	 * 
	 * @param text
	 */
	private MenuItem getLastMenuItem(String[] texts, Menu menu) {

		if (menu == null)
			throw new WidgetNotFoundException("No context menu found");

		Menu nextMenu = menu;
		MenuItem nextMenuItem = null;
		for (String text : texts) {
			// Get next menu
			nextMenuItem = null;

			nextMenuItem = getNextMenuItem(text, nextMenu);
			// Set menu as next menu item menu
			if (nextMenuItem == null) {
				hideMenu(nextMenu);
				return null;
			} else {
				log.debug("next menu for menu item " + nextMenuItem.getText()
						+ " found");
				nextMenu = nextMenuItem.getMenu();
			}
		}
		return nextMenuItem;
	}

	/**
	 * Gets next menuitem of given text. UIThread method
	 * 
	 * @param text
	 * @param menu
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private MenuItem getNextMenuItem(String text, Menu menu) {

		MenuItem nextMenuItem = null;

		Matcher<?> matcher = allOf(instanceOf(MenuItem.class),
				withMnemonic(text));

		showMenu(menu);
		MenuItem[] items = menu.getItems();
		log.debug("Menu items found:" + items.length + " items");
		// menu item loops
		for (MenuItem i : items) {
			log.debug("Trying to match " + text + " with " + i.getText());
			if (matcher.matches(i)) {
				nextMenuItem = i;
				log.debug("Next menu item with text:" + text + " found");
				break;
			}
		}

		return nextMenuItem;
	}

	/**
	 * Clicks on given menuitem. UIThread method
	 * 
	 * @param menuItem
	 */
	private void clickOnMenuItem(final MenuItem menuItem) {
		System.out.println("Menuitem:" + menuItem);
		final Event event = new Event();
		event.time = (int) System.currentTimeMillis();
		event.widget = menuItem;
		event.display = menuItem.getDisplay();
		event.type = SWT.Selection;

		log.debug("Click event generated");

		menuItem.notifyListeners(SWT.Selection, event);
		log.debug("Event sent");
	}

	/**
	 * Hides menu including all predecessor. UIThread method
	 * 
	 * @param menu
	 */
	private static void hideMenu(final Menu menu) {
		menu.notifyListeners(SWT.Hide, new Event());
		if (menu.getParentMenu() != null) {
			hideMenu(menu.getParentMenu());
		}
	}

	/**
	 * Shows menu. UIThread method
	 */
	private void showMenu(final Menu menu) {
		menu.notifyListeners(SWT.Show, new Event());
	}
}
