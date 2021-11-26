package org.geogebra.web.html5.gui.accessibility;

import java.util.TreeSet;

import javax.annotation.Nonnull;

import org.geogebra.common.gui.AccessibilityManagerInterface;
import org.geogebra.common.gui.MayHaveFocus;
import org.geogebra.common.kernel.geos.GeoElement;
import org.geogebra.common.main.App;
import org.geogebra.common.main.SelectionManager;

/**
 * Web implementation of AccessibilityManager.
 *
 */
public class AccessibilityManagerW implements AccessibilityManagerInterface {
	private final GeoTabber geoTabber;
	private final AltGeoTabber altGeoTabber;
	private final App app;
	private final SelectionManager selection;
	private MayHaveFocus anchor;
	private SideBarAccessibilityAdapter menuContainer;

	private final TreeSet<MayHaveFocus> components = new TreeSet<>((o1, o2) -> {
		int viewDiff = o1.getAccessibilityGroup().ordinal()
				- o2.getAccessibilityGroup().ordinal();
		if (viewDiff != 0) {
			return viewDiff;
		}
		if (o1.getViewControlId() != null && o2.getViewControlId() != null) {
			return o1.getViewControlId().ordinal() - o2.getViewControlId().ordinal();
		}
		return 0;
	});

	/**
	 * Constructor.
	 *
	 * @param app
	 *            The application.
	 */
	public AccessibilityManagerW(App app) {
		this.app = app;
		selection = app.getSelectionManager();
		this.geoTabber = new GeoTabber(app);
		altGeoTabber = new AltGeoTabber(app);
		components.add(altGeoTabber);
		components.add(geoTabber);
		components.add(new PlayButtonTabber(app.getActiveEuclidianView()));
		components.add(new ResetButtonTabber(app.getActiveEuclidianView()));
	}

	@Override
	public boolean focusNext() {
		for (MayHaveFocus entry: components) {
			if (entry.hasFocus()) {
				if (!entry.focusNext()) {
					focusFirstVisible(findNext(entry));
				}
				return true;
			}
		}

		return focusFirstVisible(components.first());
	}

	private boolean focusFirstVisible(@Nonnull MayHaveFocus entry) {
		MayHaveFocus nextEntry = entry;
		do {
			if (nextEntry.focusIfVisible(false)) {
				return true;
			}
			nextEntry = findNext(nextEntry);
		} while (nextEntry != entry);

		return false;
	}

	private boolean focusLastVisible(@Nonnull MayHaveFocus entry) {
		MayHaveFocus nextEntry = entry;
		do {
			if (nextEntry.focusIfVisible(true)) {
				return true;
			}
			nextEntry = findPrevious(nextEntry);
		} while (nextEntry != entry);

		return false;
	}

	private MayHaveFocus findNext(MayHaveFocus entry) {
		MayHaveFocus nextEntry = components.higher(entry);
		if (nextEntry == null) {
			return components.first();
		}
		return nextEntry;
	}

	private MayHaveFocus findPrevious(MayHaveFocus entry) {
		MayHaveFocus nextEntry = components.lower(entry);
		if (nextEntry == null) {
			return components.last();
		}
		return nextEntry;
	}

	@Override
	public boolean focusPrevious() {
		for (MayHaveFocus entry: components) {
			if (entry.hasFocus()) {
				if (!entry.focusPrevious()) {
					return focusLastVisible(findPrevious(entry));
				}
				return true;
			}
		}

		return focusLastVisible(components.last());
	}

	@Override
	public void register(MayHaveFocus focusable) {
		components.add(focusable);
	}

	@Override
	public void setTabOverGeos() {
		geoTabber.setFocused(true);
	}

	@Override
	public void focusFirstElement() {
		components.first().focusIfVisible(false);
	}

	@Override
	public boolean focusInput(boolean force, boolean forceFade) {
		if (menuContainer != null) {
			return menuContainer.focusInput(force, forceFade);
		}
		return false;
	}

	@Override
	public void focusGeo(GeoElement geo) {
		if (geo != null) {
			selection.addSelectedGeoForEV(geo);
			if (!geo.isGeoInputBox()) {
				app.getActiveEuclidianView().requestFocus();
			}
		} else {
			if (menuContainer != null) {
				menuContainer.focusMenu();
			}
		}
	}

	@Override
	public void setAnchor(MayHaveFocus anchor) {
		this.anchor = anchor;
	}

	@Override
	public MayHaveFocus getAnchor() {
		return anchor;
	}

	@Override
	public void focusAnchor() {
		if (anchor == null) {
			return;
		}
		anchor.focusIfVisible(false);
		cancelAnchor();
	}

	@Override
	public void focusAnchorOrMenu() {
		if (anchor == null) {
			focusFirstElement();
		} else {
			focusAnchor();
		}
	}

	@Override
	public void cancelAnchor() {
		anchor = null;
	}

	/**
	 * @param toolbarPanel side bar adapter
	 */
	public void setMenuContainer(SideBarAccessibilityAdapter toolbarPanel) {
		this.menuContainer = toolbarPanel;
	}

	@Override
	public GeoElement getAltGeoForView() {
		return altGeoTabber.getAltGeo(app.getActiveEuclidianView().getEuclidianViewNo());
	}
}
