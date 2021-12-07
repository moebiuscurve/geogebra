package org.geogebra.web.html5.main;

import org.geogebra.common.kernel.geos.GeoText;
import org.geogebra.common.kernel.geos.ScreenReaderBuilder;
import org.geogebra.common.main.App;
import org.geogebra.web.html5.gui.accessibility.ViewAltTexts;

public class AltTextCollector {
	private final ScreenReaderBuilder sb;
	private int count;
	private final AltTextTimer timer;
	private final ViewAltTexts views;

	public AltTextCollector(App app, ViewAltTexts views) {
		this.views = views;
		count = 0;
		timer = new AltTextTimer(app.getActiveEuclidianView().getScreenReader());
		sb = new ScreenReaderBuilder(app.getLocalization());
	}

	public void add(GeoText altText) {
		if (!views.isValid(altText)) {
			return;
		}

		sb.append(altText.getAuralText());
		sb.appendSpace();
		if (isLastAltText()) {
			timer.read(sb);
			count = 0;
		} else {
			count++;
		}
	}

	private boolean isLastAltText() {
		return count == views.activeAltTextCount();
	}
}
