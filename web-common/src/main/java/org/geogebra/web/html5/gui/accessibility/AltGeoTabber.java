package org.geogebra.web.html5.gui.accessibility;

import java.util.HashMap;
import java.util.Map;

import org.geogebra.common.euclidian.EuclidianView;
import org.geogebra.common.euclidian.ScreenReaderAdapter;
import org.geogebra.common.gui.AccessibilityGroup;
import org.geogebra.common.gui.MayHaveFocus;
import org.geogebra.common.kernel.Kernel;
import org.geogebra.common.kernel.geos.GeoElement;
import org.geogebra.common.kernel.geos.GeoText;
import org.geogebra.common.main.App;

public class AltGeoTabber implements MayHaveFocus {
	private static final Map<Integer, String>
			ALT_TEXTS = new HashMap<>();
	private final Kernel kernel;
	private final App app;
	private GeoElement altGeo;
	private boolean focus = false;
	static {
		ALT_TEXTS.put(1, "altText");
		ALT_TEXTS.put(2, "altText2");
		ALT_TEXTS.put(3, "altText3D");
	}

	/**
	 * @param app the application
	 */
	public AltGeoTabber(App app) {
		kernel = app.getKernel();
		this.app = app;
	}

	@Override
	public boolean focusIfVisible(boolean reverse) {
		EuclidianView view = app.getActiveEuclidianView();
		altGeo = getAltGeo(view.getEuclidianViewNo());

		if (hasInvisibleAltGeo()) {
			readText(view.getScreenReader(), altGeo);
			focus = true;
			return true;
		}

		return false;
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
		focus = false;
		return false;
	}

	@Override
	public boolean focusPrevious() {
		focus = false;
		return false;
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
	 * @param viewNumber the id of the actual euclidian view.
	 */
	public GeoElement getAltGeo(int viewNumber) {
		return kernel.lookupLabel(ALT_TEXTS.getOrDefault(viewNumber, ALT_TEXTS.get(1)));
	}
}
