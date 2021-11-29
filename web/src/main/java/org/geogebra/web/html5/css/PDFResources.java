package org.geogebra.web.html5.css;

import org.gwtproject.resources.client.ClientBundle;
import org.gwtproject.resources.client.TextResource;

import com.google.gwt.core.client.GWT;

public interface PDFResources extends ClientBundle {

	PDFResources INSTANCE = GWT.create(PDFResources.class);

	@Source("org/geogebra/web/resources/js/pdfjs/pdf.min.js")
	TextResource pdfJs();

	@Source("org/geogebra/web/resources/js/pdfjs/pdf.worker.min.js")
	TextResource pdfWorkerJs();
}