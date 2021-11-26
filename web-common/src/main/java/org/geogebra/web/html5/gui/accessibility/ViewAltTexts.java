package org.geogebra.web.html5.gui.accessibility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.geogebra.common.kernel.Kernel;
import org.geogebra.common.kernel.geos.GeoElement;
import org.geogebra.common.main.App;

public class ViewAltTexts {
	private static final List<Integer> views = Arrays.asList(
			App.VIEW_EUCLIDIAN,
			App.VIEW_EUCLIDIAN2,
			App.VIEW_EUCLIDIAN3D);
	private static final Map<Integer, String>
			ALT_TEXTS = new HashMap<>();
	private List<Integer> visibleViews;
	static {
		ALT_TEXTS.put(App.VIEW_EUCLIDIAN, "altText");
		ALT_TEXTS.put(App.VIEW_EUCLIDIAN2, "altText2");
		ALT_TEXTS.put(App.VIEW_EUCLIDIAN3D, "altText3D");
	}

	private final Kernel kernel;

	private final App app;

	public ViewAltTexts(App app) {
		this.app = app;
		kernel = app.getKernel();
		visibleViews = new ArrayList<>();
	}

	public int viewCount() {
		return visibleViews.size();
	}

	public void update() {
		visibleViews = views.stream().filter(app::showView).collect(Collectors.toList());
	}

	public String get(int index) {
		return ALT_TEXTS.get(visibleViews.get(index));
	}

	/**
	 * @return the geo element containing the alt text for the view
	 * specified by viewIndex.
	 * @param viewIndex the index of the euclidian view.
	 */
	public GeoElement getAltGeo(int viewIndex) {
		GeoElement geoElement = kernel.lookupLabel(get(viewIndex));
		return (geoElement == null || geoElement.isEuclidianVisible())
				? null
				: geoElement;
	}
}