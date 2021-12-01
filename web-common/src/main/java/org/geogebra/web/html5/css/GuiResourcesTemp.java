package org.geogebra.web.html5.css;

import org.gwtproject.resources.client.ClientBundleWithLookup;
import org.gwtproject.resources.client.ImageResource;
import org.gwtproject.resources.client.Resource;
import org.gwtproject.resources.client.TextResource;

@Resource
public interface GuiResourcesTemp extends ClientBundleWithLookup {

	GuiResourcesTemp INSTANCE = new GuiResourcesTempImpl();

	@Source("view_refresh.png")
	ImageResource viewRefresh();

	@Source("org/geogebra/web/resources/js/rewrite_pHYs.min.js")
	TextResource rewritePHYS();
}
