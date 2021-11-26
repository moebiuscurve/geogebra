package org.geogebra.web.html5.gui.accessibility;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.geogebra.common.euclidian.ScreenReaderAdapter;
import org.geogebra.common.gui.AccessibilityGroup;
import org.geogebra.common.gui.MayHaveFocus;
import org.geogebra.common.kernel.Kernel;
import org.geogebra.common.kernel.geos.GeoElement;
import org.geogebra.common.kernel.geos.GeoText;
import org.geogebra.common.main.App;

public class AltGeoTabber implements MayHaveFocus {
	private static final List<Integer> views = Arrays.asList(App.VIEW_EUCLIDIAN,
			App.VIEW_EUCLIDIAN2, App.VIEW_EUCLIDIAN3D);
	private static final Map<Integer, String>
			ALT_TEXTS = new HashMap<>();
	private final Kernel kernel;
	private final ScreenReaderAdapter screenReader;
	private final App app;
	private GeoElement altGeo;
	private boolean focus = false;
	private int viewIndex = 0;
	static {
		ALT_TEXTS.put(App.VIEW_EUCLIDIAN, "altText");
		ALT_TEXTS.put(App.VIEW_EUCLIDIAN2, "altText2");
		ALT_TEXTS.put(App.VIEW_EUCLIDIAN3D, "altText3D");
	}

	/**
	 * @param app the application
	 */
	public AltGeoTabber(App app) {
		kernel = app.getKernel();
		screenReader = app.getActiveEuclidianView().getScreenReader();
		this.app = app;
	}

	@Override
	public boolean focusIfVisible(boolean reverse) {
		viewIndex = reverse ? views.size() - 1 : 0;
		if (readNextView())  {
			return focus;
		}
		return false;
	}

	private boolean readNextView() {
		nextVisibleViewIndex();
		if (viewIndex < views.size()) {
			readCurrentAltText();
			return true;
		}
		return false;
	}

	private void readCurrentAltText() {
		readAltTextForView(ALT_TEXTS.get(views.get(viewIndex)));
	}

	private void nextVisibleViewIndex() {
		while (viewIndex < views.size() && isViewHidden()) {
			viewIndex++;
		}
	}

	private boolean isViewHidden() {
		return !app.showView(views.get(viewIndex));
	}

	private void readAltTextForView(String altTextId) {
		altGeo = getAltGeo(altTextId);
		if (hasInvisibleAltGeo()) {
			readText(screenReader, altGeo);
			focus = true;
		}
	}

	private boolean hasInvisibleAltGeo() {
		return altGeo != null && !altGeo.isEuclidianVisible();
	}

	private void readText(ScreenReaderAdapter screenReader, GeoElement altGeo) {
		if (!altGeo.isGeoText()) {
			return;
		}
		screenReader.readText(((GeoText)altGeo).getAuralText());
	}

	@Override
	public boolean hasFocus() {
		return focus;
	}

	@Override
	public boolean focusNext() {
		viewIndex++;
		focus = readNextView();
		return focus;
	}

	@Override
	public boolean focusPrevious() {
		viewIndex--;
		focus = readPreviousView();
		return focus;
	}

	private boolean readPreviousView() {
		previousVisibleViewIndex();
		if (viewIndex >= 0) {
			readCurrentAltText();
			return true;
		}
		return false;
	}

	private void previousVisibleViewIndex() {
		while (viewIndex >= 0 && isViewHidden()) {
			viewIndex--;
		}
	}

	@Override
	public AccessibilityGroup getAccessibilityGroup() {
		return AccessibilityGroup.ALT_GEOTEXT;
	}

	@Override
	public AccessibilityGroup.ViewControlId getViewControlId() {
		return AccessibilityGroup.ViewControlId.ALT_GEO;
	}

	/**
	 * @return the element with the name ("altText" + viewNumber), or "altText",
	 * if it exists
	 * @param altTextId the id of the actual euclidian view.
	 */
	public GeoElement getAltGeo(String altTextId) {
		return kernel.lookupLabel(altTextId);
	}

	public GeoElement getAltGeo(int euclidianViewNo) {
		return getAltGeo(ALT_TEXTS.get(euclidianViewNo));
	}
}
