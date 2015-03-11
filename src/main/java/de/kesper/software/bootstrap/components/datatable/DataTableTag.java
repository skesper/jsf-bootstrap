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

package de.kesper.software.bootstrap.components.datatable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.faces.application.Application;
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponent;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

/**
 *
 * @author kesper
 */
@ResourceDependencies({
    @ResourceDependency(library="jsf-bootstrap", name="jquery-2.1.1.js"),
    @ResourceDependency(library="jsf-bootstrap", name="jquery.dataTables.css"),
    @ResourceDependency(library="jsf-bootstrap", name="jquery.dataTables.min.js")
})
@FacesComponent(value = "de.kesper.software.bootstrap.components.datatable.DataTableTag")
public class DataTableTag extends UIData {
//    private String var;
    private ArrayList<ColumnTag> columns = null;

    public DataTableTag() {
        setRendered(true);
    }
    
    public List<ColumnTag> getColumns() {
        if (columns==null) {
            columns = new ArrayList<>();
            List<UIComponent> children = this.getChildren();
            if (children!=null) {
                children.stream().filter((child) -> (child instanceof ColumnTag)).forEach((child) -> {
                    columns.add((ColumnTag)child);
                });
            }
        }
        return columns;
    }
    
//    @Override
//    public String getVar() {
//        return var;
//    }
//
//    @Override
//    public void setVar(String var) {
//        this.var = var;
//    }
    
    @Override
    public String getFamily() {
        return "datatable";
    }
    
    @Override
    public boolean getRendersChildren() {
        return true;
    }
    
    @Override
    public void encodeEnd(FacesContext context) throws IOException {
        
        ResponseWriter writer = context.getResponseWriter();

        Application app = context.getApplication();
        ExpressionFactory ef = app.getExpressionFactory();
        ELContext elContext = context.getELContext();

        Map<String, Object> requestMap = context.getExternalContext().getRequestMap();
        
        writer.startElement("table", this);
        writer.writeAttribute("id", getId(), null);
        writer.writeAttribute("class", "display", null);
        writer.startElement("thead", this);
        writer.startElement("tr", this);
        for(ColumnTag ct : getColumns()) {
            writer.startElement("th", this);
            writer.append(ct.getHeaderText());
            writer.endElement("th");
        }
        writer.endElement("tr");
        writer.endElement("thead");

        writer.startElement("tbody", this);

        Object value = this.getValue();
        if (value!=null && (value instanceof Collection)) {
            Collection collection = (Collection)value;
            int rowNum = 0;
            for(Object row : collection) {
                if (row!=null) {
                    writer.startElement("tr", this);
                    // Populate variable var to child components
                    requestMap.put(this.getVar(), row);
                    this.setRowIndex(rowNum++);

                    for(ColumnTag ct : getColumns()) {
                        writer.startElement("td", this);
                        ct.encodeAll(context);
                        writer.endElement("td");
                    }
                    writer.endElement("tr");
                }
            }
        }

        writer.endElement("tbody");
        writer.endElement("table");
        
        writer.startElement("script", this);
        writer.append(script1);
        writer.append(getId());
        writer.append(script2);
        writer.endElement("script");
        
        this.setRendered(true);
    }

    
    private static final String script1 = "$(document).ready(function() {\n" +
                "		$('#";
    private static final String script2 = "').DataTable({\n \"pageLength\": 50, \"order\" : [[]], " +
                "        \"sFilterInput\":\"input-sm\", " +
                "        \"language\":\n" +
                "		{\n" +
                "			\"sEmptyTable\":      \"Keine Daten in der Tabelle vorhanden\",\n" +
                "			\"sInfo\":            \"_START_ bis _END_ von _TOTAL_ Einträgen\",\n" +
                "			\"sInfoEmpty\":       \"0 bis 0 von 0 Einträgen\",\n" +
                "			\"sInfoFiltered\":    \"(gefiltert von _MAX_ Einträgen)\",\n" +
                "			\"sInfoPostFix\":     \"\",\n" +
                "			\"sInfoThousands\":   \".\",\n" +
                "			\"sLengthMenu\":      \"_MENU_ Einträge anzeigen\",\n" +
                "			\"sLoadingRecords\":  \"Wird geladen...\",\n" +
                "			\"sProcessing\":      \"Bitte warten...\",\n" +
                "			\"sSearch\":          \"Filter\",\n" +
                "			\"sZeroRecords\":     \"Keine Einträge vorhanden.\",\n" +
                "			\"oPaginate\": {\n" +
                "				\"sFirst\":       \"Erste\",\n" +
                "				\"sPrevious\":    \"Zurück\",\n" +
                "				\"sNext\":        \"Nächste\",\n" +
                "				\"sLast\":        \"Letzte\"\n" +
                "			},\n" +
                "			\"oAria\": {\n" +
                "				\"sSortAscending\":  \": aktivieren, um Spalte aufsteigend zu sortieren\",\n" +
                "				\"sSortDescending\": \": aktivieren, um Spalte absteigend zu sortieren\"\n" +
                "			}\n" +
                "		}});\n" +
                "	} ); "
            +   " $.fn.dataTableExt.oStdClasses[\"sFilterInput\"] = \"form-control input-sm\"; "
            +   " $.fn.dataTableExt.oStdClasses[\"sLengthSelect\"] = \"form-control input-sm\"; ";
}
