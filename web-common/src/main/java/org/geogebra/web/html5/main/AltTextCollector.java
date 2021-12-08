package org.geogebra.web.html5.main;

import java.util.ArrayList;
import java.util.List;

import org.geogebra.common.kernel.geos.GeoText;
import org.geogebra.common.kernel.geos.ScreenReaderBuilder;
import org.geogebra.common.main.App;
import org.geogebra.common.main.Localization;
import org.geogebra.web.html5.gui.accessibility.ViewAltTexts;

public class AltTextCollector {
	private final List<String> lines;
	private final Localization loc;
	private final AltTextTimer timer;
	private final ViewAltTexts views;

	public AltTextCollector(App app, ViewAltTexts views) {
		this.views = views;
		timer = new AltTextTimer(app.getActiveEuclidianView().getScreenReader());
		loc = app.getLocalization();
		lines = new ArrayList<>();
	}

	public void add(GeoText altText) {
		if (!views.isValid(altText)) {
			return;
		}

		lines.add(altText.getAuralText());
		if (isLastAltText()) {
			timer.read(concatLines());
			lines.clear();
		}
	}

	private ScreenReaderBuilder concatLines() {
		ScreenReaderBuilder sb = new ScreenReaderBuilder(loc);
		for (String line: lines) {
			sb.append(line);
			sb.appendSpace();
		}
		return sb;
	}

	private boolean isLastAltText() {
		return lines.size() == views.activeAltTextCount();
	}
}
