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

package de.kesper.software.bootstrap.components.button;

import java.io.IOException;
import java.util.logging.Logger;
import javax.faces.component.FacesComponent;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.context.FacesContext;

/**
 *
 * @author kesper
 */
@FacesComponent(value = "de.kesper.software.bootstrap.components.button.ButtonTag")
public class ButtonTag extends HtmlCommandButton {

    @Override
    public String getFamily() {
        return "button";
    }
    
    
    @Override
    public void encodeEnd(FacesContext context) throws IOException {
        super.encodeEnd(context);
        System.out.println("DEBUG: in encodeEnd() - ButtonTag");
    }

    @Override
    public void encodeBegin(FacesContext context) throws IOException {
        System.out.println("DEBUG: in encodeBegin() - ButtonTag");
        String clazz = this.getStyleClass();
        if (clazz==null) {
            clazz = "btn";
        } else {
            if (!clazz.startsWith("btn ")) {
                clazz = "btn ".concat(clazz);
            }
        }
        this.setStyleClass(clazz);
        super.encodeBegin(context);
    }
    
    
}
