package org.geogebra.web.html5.main;

import org.geogebra.common.kernel.geos.GeoText;
import org.gwtproject.timer.client.Timer;

class AltTextTimer extends Timer {
	private final AppW appW;
	private GeoText altText;

	public AltTextTimer(AppW appW) {
		this.appW = appW;
	}

	@Override
	public void run() {
		appW.getEuclidianView1().setAltText(altText);
	}

	public void schedule(GeoText altText, int milis) {
		this.altText = altText;
		schedule(milis);
	}
}
