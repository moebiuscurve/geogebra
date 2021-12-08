package org.geogebra.web.full.gui.inputfield;

import java.util.Collections;
import java.util.List;

import org.geogebra.web.full.javax.swing.GPopupMenuW;
import org.geogebra.web.html5.gui.inputfield.AutoCompleteW;
import org.geogebra.web.html5.gui.util.AriaMenuBar;
import org.geogebra.web.html5.gui.util.AriaMenuItem;
import org.geogebra.web.html5.gui.view.button.StandardButton;
import org.geogebra.web.html5.main.AppW;
import org.geogebra.web.shared.SharedResources;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class AutoCompletePopup extends GPopupMenuW {

	private final MathFieldInputSuggestions suggestions;
	private final AutoCompleteW component;
	private int prefixLength = 0;

	/**
	 * constructor for the command autocomplete popup
	 * @param app - see {@link AppW}
	 */
	public AutoCompletePopup(AppW app, MathFieldInputSuggestions suggestions,
			AutoCompleteW component) {
		super(app);
		this.suggestions = suggestions;
		this.component = component;
		getPopupPanel().addStyleName("autoCompletePopup");
		getPopupPanel().addStyleName("customScrollbar");
	}

	private void fillContent(final String curWord) {
		prefixLength = curWord.length();
		getApp().getParserFunctions().getCompletions(curWord)
				.forEach(function -> addRow(function.split("\\(")[0],
						Collections.singletonList(function)));
		List<String> cmdDict = getApp().getCommandDictionary()
				.getCompletions(curWord.toLowerCase());
		if (cmdDict != null) {
			cmdDict.forEach(command -> addRow(command, suggestions.getSyntaxes(command)));
		}
	}

	private void addRow(String command, List<String> syntaxes) {
		AriaMenuBar submenu = new AriaMenuBar();
		submenu.addStyleName("autocompleteSyntaxContent");
		submenu.addStyleName("customScrollbar");
		for (String line: syntaxes) {
			AriaMenuItem item = new AriaMenuItem(line.replaceAll("[<>]", ""),
					false, () -> {
				component.insertString(line);
				hide();
			});
			item.setFocusable(false);
			submenu.addItem(item);
		}
		AriaMenuItem menuItem = new AriaMenuItem(highlightSuffix(command),
				true, submenu);
		menuItem.setSubmenuHeading(buildSubmenuHeading(command));
		menuItem.addStyleName("no-image");
		menuItem.setFocusable(false);
		addItem(menuItem, false);
	}

	private Widget buildSubmenuHeading(String command) {
		FlowPanel heading = new FlowPanel();
		heading.addStyleName("autocompleteSyntaxHeading");
		heading.add(new Label(command));
		StandardButton w = new StandardButton(SharedResources.INSTANCE.icon_help_black(),
				null, 24);
		w.addFastClickHandler(ignore -> {
			getApp().getGuiManager().openCommandHelp(getApp().getInternalCommand(command));
		});
		heading.add(w);
		return heading;
	}

	private String highlightSuffix(String command) {
		String prefix = command.substring(0, prefixLength);
		String suffix = command.substring(prefixLength);
		return prefix + "<strong>" + suffix + "</strong>";
	}

	/**
	 * fill popup with command list based on the user input
	 * @param curWord - user input
	 */
	public void fillAndShow(String curWord) {
		getPopupMenu().clear();
		fillContent(curWord);
		popupPanel.hide();
		if (getPopupMenu().getWidgetCount() > 0) {
			removeSubPopup();
			popupPanel.show();
			getPopupMenu().selectItem(0);
		} else {
			popupPanel.hide();
		}
	}

	public boolean isSuggesting() {
		return getPopupMenu().isVisible() && getPopupMenu().isAttached();
	}

	/**
	 * @return whether some syntax was inserted
	 */
	public boolean insertSelectedSyntax() {
		if (openItem != null) {
			AriaMenuItem selectedItem = openItem.getSubMenu().getSelectedItem();
			if (selectedItem != null) {
				selectedItem.getScheduledCommand().execute();
				return true;
			}
		}
		return false;
	}
}
