package org.geogebra.web.full.gui.inputfield;

import javax.annotation.Nonnull;

import org.geogebra.web.html5.gui.inputfield.AutoCompleteW;
import org.geogebra.web.html5.gui.inputfield.InputSuggestions;
import org.geogebra.web.html5.main.AppW;

public class MathFieldInputSuggestions extends InputSuggestions {

	private String curWord;
	private final AutoCompletePopup autoCompletePopup;
	private final @Nonnull AutoCompleteW component;

	/**
	 * @param app
	 *            application
	 * @param component
	 *            text input
	 * @param forCAS
	 *            whether to use this for CAS
	 */
	public MathFieldInputSuggestions(AppW app, AutoCompleteW component,
			boolean forCAS) {
		super(app, forCAS);
		this.component = component;
		curWord = "";
		autoCompletePopup = new AutoCompletePopup(app, this, component);
	}

	/**
	 * Update current word from UI
	 */
	public void updateCurrentWord() {
		curWord = component.getCommand();
	}

	/**
	 * Show suggestions.
	 */
	public void popupSuggestions() {
		updateCurrentWord();
		if (curWord != null
				&& !"sqrt".equals(curWord)
				&& needsAutocomplete(this.curWord)) {
			autoCompletePopup.fillAndShow(curWord);
		} else {
			autoCompletePopup.hide();
		}
	}

	public boolean isSuggesting() {
		return autoCompletePopup.isSuggesting();
	}

	/**
	 * @return whether enter should be consumed by suggestions
	 */
	public boolean needsEnterForSuggestion() {
		if (autoCompletePopup.isSuggesting()) {
			return autoCompletePopup.insertSelectedSyntax();
		}
		return false;
	}

	public void onKeyDown() {
		autoCompletePopup.moveSelectionDown();
	}

	public void onKeyUp() {
		autoCompletePopup.moveSelectionUp();
	}

}
