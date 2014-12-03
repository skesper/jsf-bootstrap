/*
The MIT License (MIT)

Copyright (c) 2014 Stephan Kesper

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
 */

package de.kesper.software.bootstrap.components.panel;

import de.kesper.software.bootstrap.utils.StringUtil;
import java.io.IOException;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

/**
 *
 * @author kesper
 */
@FacesComponent(value = "de.kesper.software.bootstrap.components.panel.PanelTag")
public class PanelTag extends UIOutput {
    
    private String footer;
    private String header;
    private String styleClass;

    public String getFooter() {
        return footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }
    
    @Override
    public String getFamily() {
        return "panel";
    }

    public String getStyleClass() {
        return styleClass;
    }

    public void setStyleClass(String styleClass) {
        this.styleClass = styleClass;
    }

    @Override
    public void encodeBegin(FacesContext context) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        writer.startElement("div", this);
        if (StringUtil.isNotNull(styleClass)) {
            writer.writeAttribute("class", "panel ".concat(styleClass), null);
        } else {
            writer.writeAttribute("class", "panel", null);
        }
        
        if (StringUtil.isNotNull(header)) {
            writer.startElement("div", this);
            writer.writeAttribute("class", "panel-heading", null);
            writer.startElement("h3", this);
            writer.writeAttribute("class", "panel-title", null);
            writer.append(header);
            writer.endElement("h3");
            writer.endElement("div");
        }
        
        // start panel-body
        writer.startElement("div", this);
        writer.writeAttribute("class", "panel-body", null);
    }
    
    @Override
    public void encodeEnd(FacesContext context) throws IOException {
        ResponseWriter writer = context.getResponseWriter();

        // end the panel-body
        writer.endElement("div");
        
        if (StringUtil.isNotNull(footer)) {
            writer.startElement("div", this);
            writer.writeAttribute("class", "panel-footer", null);
            writer.append(footer);
            writer.endElement("div");
        }
        
        writer.endElement("div");
    }
}
