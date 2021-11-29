package org.geogebra.web.html5.css;

import org.geogebra.web.resources.SVGResource;
import org.gwtproject.resources.client.ClientBundle;
import org.gwtproject.resources.client.ImageResource;
import org.gwtproject.resources.client.Resource;
import org.gwtproject.resources.client.TextResource;

@Resource
public interface GuiResourcesSimple extends ClientBundle {

	GuiResourcesSimple INSTANCE = new GuiResourcesSimpleImpl();

	@Source("org/geogebra/web/resources/js/rewrite_pHYs.min.js")
	TextResource rewritePHYS();

	@Source("org/geogebra/web/resources/js/xmlutil.js")
	TextResource xmlUtil();

	@Source("org/geogebra/web/resources/js/fflate.min.js")
	TextResource fflateJs();

	@Source("org/geogebra/web/resources/js/base64.js")
	TextResource base64Js();

	@Source("org/geogebra/web/resources/js/domvas.js")
	TextResource domvas();

	@Source("org/geogebra/web/resources/js/clipboard.js")
	TextResource clipboardJs();

	@Source("view_refresh.png")
	ImageResource viewRefresh();

	@Source("org/geogebra/web/resources/images/spinner.gif")
	ImageResource getGeoGebraWebSpinner();

	@Source("org/geogebra/web/resources/images/ggbSplash.html")
	TextResource ggbSplashHtml();

	// INFO, WARNING, QUESTION, ERROR
	@Source("org/geogebra/common/icons/png/web/dialog-error.png")
	ImageResource dialog_error();

	@Source("org/geogebra/common/icons/png/web/dialog-info.png")
	ImageResource dialog_info();

	@Source("org/geogebra/common/icons/png/web/dialog-question.png")
	ImageResource dialog_question();

	@Source("org/geogebra/common/icons/png/web/dialog-warning.png")
	ImageResource dialog_warning();

	@Source("org/geogebra/common/icons/png/web/icon-dialog-warning.png")
	ImageResource icon_dialog_warning();

	@Source("org/geogebra/common/icons/png/web/question-mark.png")
	ImageResource questionMark();

	@Source("org/geogebra/common/icons/svg/web/matDesignIcons/mow/video_player_black_opacity54_360px.png")
	ImageResource mow_video_player();

	// MOW Rulings
	@Source("org/geogebra/common/icons/svg/web/matDesignIcons/mow/rulings/elementary12.svg")
	SVGResource mow_ruling_elementary12();

	@Source("org/geogebra/common/icons/svg/web/matDesignIcons/mow/rulings/elementary12colored.svg")
	SVGResource mow_ruling_elementary12colored();

	@Source("org/geogebra/common/icons/svg/web/matDesignIcons/mow/rulings/elementary12house.svg")
	SVGResource mow_ruling_elementary12house();

	@Source("org/geogebra/common/icons/svg/web/matDesignIcons/mow/rulings/elementary34.svg")
	SVGResource mow_ruling_elementary34();

	@Source("org/geogebra/common/icons/svg/web/matDesignIcons/mow/rulings/music.svg")
	SVGResource mow_ruling_music();

	@Source("org/geogebra/common/icons/svg/common/pause.svg")
	SVGResource pause();

	@Source("org/geogebra/common/icons/svg/common/play.svg")
	SVGResource play();

	@Source("org/geogebra/common/icons/svg/common/stop.svg")
	SVGResource stop();

	@Source("org/geogebra/common/icons/svg/common/replay.svg")
	SVGResource replay();

	@Source("org/geogebra/common/icons/svg/common/skip_next.svg")
	SVGResource skip_next();

	@Source("org/geogebra/common/icons/svg/common/skip_previous.svg")
	SVGResource skip_previous();

	@Source("org/geogebra/common/icons/svg/common/loop.svg")
	SVGResource loop();

	@Source("org/geogebra/common/icons/svg/common/zoom_in.svg")
	SVGResource zoom_in();

	@Source("org/geogebra/common/icons/svg/common/zoom_out.svg")
	SVGResource zoom_out();

	@Source("org/geogebra/common/icons/svg/common/close.svg")
	SVGResource close();

	@Source("org/geogebra/common/icons/svg/common/arrow_upward.svg")
	SVGResource arrow_up();

	@Source("org/geogebra/common/icons/svg/common/arrow_downward.svg")
	SVGResource arrow_down();

	@Source("org/geogebra/common/icons/svg/common/arrow_backward.svg")
	SVGResource arrow_back();

	@Source("org/geogebra/common/icons/svg/common/arrow_forward.svg")
	SVGResource arrow_forward();

	@Source("org/geogebra/common/icons/svg/common/fast_forward.svg")
	SVGResource fast_forward();

	@Source("org/geogebra/common/icons/svg/common/fast_rewind.svg")
	SVGResource fast_rewind();

	@Source("org/geogebra/common/icons/svg/web/matDesignIcons/contextMenu/ic_play_circle_outline_black_24px.svg")
	SVGResource play_circle();

	@Source("org/geogebra/common/icons/svg/web/matDesignIcons/av/ic_pause_circle_outline_black_24px.svg")
	SVGResource pause_circle();

	// RULER AND PROTRACTOR
	@Source("org/geogebra/common/icons/svg/web/ruler_protractor/ruler.svg")
	SVGResource ruler();

	@Source("org/geogebra/common/icons/svg/web/ruler_protractor/protractor.svg")
	SVGResource protractor();
}
