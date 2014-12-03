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

package de.kesper.software.bootstrap.components.calendar;

import java.io.IOException;
import java.util.Map;
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;
import javax.faces.render.Renderer;

/**
 *
 * @author kesper
 */
@ResourceDependencies({
    @ResourceDependency(library = "jsf-bootstrap", name = "datepicker.css"),
    @ResourceDependency(library = "jsf-bootstrap", name = "bootstrap-datepicker.js")
})
@FacesRenderer(componentFamily = "calendar", rendererType = "de.kesper.software.bootstrap.components.calendar.CalendarRenderer")
public class CalendarRenderer extends Renderer {

    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        ResponseWriter writer = context.getResponseWriter();

/*        
<div class="input-group date">
  <input type="text" class="form-control"><span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
</div>
*/
        
        writer.startElement("input", component);
        String id = getClientId(component);
        if (id!=null) {
            writer.writeAttribute("id", id, null);
        }
        writer.writeAttribute("name", component.getClientId(), null);
        writer.writeAttribute("type", "text", null);
        writer.writeAttribute("class", "form-control", null);
        writer.writeAttribute("data-provide", "datepicker", null);
        
        writer.endElement("input");
    }
    

    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        ResponseWriter writer = context.getResponseWriter();

        writer.startElement("script", component);
        writer.writeAttribute("type", "text/javascript", null);

        String clientId = getClientId(component);
        String format = ((CalendarTag)component).getFormat();
        writer.append("$(document).ready(function() {$('#"+clientId+"').datepicker({language: 'de'}); });");
        writer.endElement("script");
    }
    

    @Override
    public void decode(FacesContext context, UIComponent component) {
        
        Map<String, String> params = context.getExternalContext().getRequestParameterMap();
        String clientId = component.getClientId();
        String value = params.get(clientId);
        
        String format = ((CalendarTag)component).getFormat();
        
        System.out.println("DEBUG: decode "+clientId+" --> "+value);
    }

    private String getClientId(UIComponent component) {
        String id = component.getClientId();
        int idx = id.lastIndexOf(":");
        if (idx<0) return id;
        return id.substring(idx+1);
    }
    
}
