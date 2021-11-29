package org.geogebra.web.full.css;

import org.geogebra.web.resources.SVGResource;
import org.gwtproject.resources.client.ClientBundle;

import com.google.gwt.core.client.GWT;

/**
 * SVG icons for toolbar
 *
 * @author csilla
 *
 */
@SuppressWarnings("javadoc")
public interface ToolbarSvgResourcesSync extends ClientBundle {
	/** singleton instance */
	ToolbarSvgResourcesSync INSTANCE = GWT
			.create(ToolbarSvgResourcesSync.class);

	@Source("org/geogebra/common/icons/svg/web/toolIcons/mode_tool.svg")
	SVGResource mode_tool_32();

	@Source("org/geogebra/common/icons/svg/web/toolIcons/mode_showcheckbox.svg")
	SVGResource mode_showcheckbox_32();

	@Source("org/geogebra/common/icons/svg/web/toolIcons/mode_slider.svg")
	SVGResource mode_slider_32();

	@Source("org/geogebra/common/icons/svg/web/toolIcons/mode_showhidelabel.svg")
	SVGResource mode_showhidelabel_32();

	@Source("org/geogebra/common/icons/svg/web/toolIcons/mode_extension.svg")
	SVGResource mode_extension();

	@Source("org/geogebra/common/icons/svg/web/toolIcons/mode_ruler.svg")
	SVGResource mode_ruler();

	@Source("org/geogebra/common/icons/svg/web/toolIcons/mode_protractor.svg")
	SVGResource mode_protractor();
}
