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

package de.kesper.software.bootstrap.components.message;

import java.io.IOException;
import java.util.Iterator;
import javax.faces.application.FacesMessage;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

/**
 *
 * @author kesper
 */
@FacesComponent(value = "de.kesper.software.bootstrap.components.message.MessagesTag")
public class MessagesTag extends UIOutput {

    private boolean globalOnly = false;

    public boolean isGlobalOnly() {
        return globalOnly;
    }

    public void setGlobalOnly(boolean globalOnly) {
        this.globalOnly = globalOnly;
    }

    @Override
    public String getFamily() {
        return "messages";
    }

    @Override
    public void encodeEnd(FacesContext context) throws IOException {
        super.encodeEnd(context); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void encodeBegin(FacesContext context) throws IOException {
        ResponseWriter writer = context.getResponseWriter();

        String forM = (String) this.getAttributes().get("for");
        String clazz = (String) this.getAttributes().get("styleClass");
        if (clazz != null && "".equals(clazz.trim())) {
            clazz = null;
        }

        Iterator<FacesMessage> msg;

        if (forM == null) {
            msg = globalOnly ? context.getMessages(null) : context.getMessages();
        } else {
            msg = globalOnly ? context.getMessages(null) : context.getMessages(forM);
        }

        if (msg != null) {
            while (msg.hasNext()) {
                FacesMessage fm = msg.next();
                if (fm.isRendered()) continue;
                
                String severity = null;
                if (fm.getSeverity() != null) {
                    switch (fm.getSeverity().getOrdinal()) {
                        case (0): {
                            severity = "alert-info";
                        }
                        break;

                        case (1): {
                            severity = "alert-warning";
                        }
                        break;

                        case (2):
                        case (3): {
                            severity = "alert-danger";
                        }
                        break;
                    }
                }
                if (severity == null) {
                    severity = "alert-info";
                }

                writer.startElement("div", this);
                String style = clazz;
                if (style == null) {
                    style = "alert alert-dismissable ".concat(severity);
                } else {
                    style = style.concat(" ").concat("alert alert-dismissable ").concat(severity);
                }
                writer.writeAttribute("class", style, null);
                // <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                writer.startElement("button", this);
                writer.writeAttribute("type", "button", null);
                writer.writeAttribute("class", "close", null);
                writer.writeAttribute("data-dismiss", "alert", null);
                writer.writeAttribute("aria-hidden", "true", null);
                writer.write("&times;");
                writer.endElement("button");

                if (fm.getSummary() != null) {
                    if (!fm.getSummary().equals(fm.getDetail())) {
                        writer.startElement("strong", this);
                        writer.write(fm.getSummary());
                        writer.endElement("strong");
                    }
                }
                if (fm.getDetail() != null) {
                    writer.write(" ");
                    writer.write(fm.getDetail());
                }
                writer.endElement("div");
                
                fm.rendered();
            }
        }
    }

}
