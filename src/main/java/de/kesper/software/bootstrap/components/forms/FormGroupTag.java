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

package de.kesper.software.bootstrap.components.forms;

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
@FacesComponent(value = "de.kesper.software.bootstrap.components.forms.FormGroupTag")
public class FormGroupTag extends UIOutput {
    private String label;
    private boolean horizontal = false;
    private String horizontalDivClass;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean isHorizontal() {
        return horizontal;
    }

    public void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }

    public String getHorizontalDivClass() {
        return horizontalDivClass;
    }

    public void setHorizontalDivClass(String horizontalDivClass) {
        this.horizontalDivClass = horizontalDivClass;
    }
    
    @Override
    public String getFamily() {
        return "grid";
    }
    
    @Override
    public void encodeBegin(FacesContext context) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        
        String ffor = null;
        Object forRef = context.getAttributes().get("for");
        if (forRef!=null) {
            ffor = forRef.toString();
        }
        writer.startElement("div", this);
        writer.writeAttribute("class", "form-group", null);
        
        writer.startElement("label", this);
        if (ffor!=null) {
            writer.writeAttribute("for", ffor, null);
        }
        if (horizontal) {
            writer.writeAttribute("class", "col-sm-2 control-label", null);
        }
        writer.append(label);
        writer.endElement("label");
        if (horizontal) {
            writer.startElement("div", this);
            String clzz = "col-sm-10";
            if (StringUtil.isNotNull(horizontalDivClass)) {
                clzz = horizontalDivClass;
            }
            writer.writeAttribute("class", clzz, null);
        }
    }
    
    @Override
    public void encodeEnd(FacesContext context) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        if (horizontal) {
            writer.endElement("div");
        }
        writer.endElement("div");
    }
    
}
