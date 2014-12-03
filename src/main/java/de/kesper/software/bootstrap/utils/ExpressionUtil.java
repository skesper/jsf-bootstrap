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

package de.kesper.software.bootstrap.utils;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.el.ELContext;
import javax.el.ELException;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.FacesException;
import javax.faces.application.Application;
import javax.faces.context.FacesContext;

/**
 *
 * @author kesper
 */
public class ExpressionUtil {
    public String evaluateExpression(String expression) {
        FacesContext facesCtx = FacesContext.getCurrentInstance();
        Application app = facesCtx.getApplication();

        try {
            ELContext elContext = facesCtx.getELContext();
            ExpressionFactory expressionFactory = app.getExpressionFactory();
            
            ValueExpression ve = expressionFactory.createValueExpression(elContext, expression, String.class);
            
            return (String) ve.getValue(elContext);
        } 
        catch (ELException ex) {
            Logger.getLogger("ExpressionUtil").log(Level.SEVERE, "Exception during expression evaluation", ex);
            throw new FacesException(ex);
        }
    }
}
