/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thermalstrategies.eportal.manager;

import java.io.OutputStream;
import jxl.*;
import jxl.write.*;
import jxl.write.Number;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 *
 * @author Stuart
 */
public class ExcelManager {

    private HibernateTemplate tstratDamperTemplate;

    public ExcelManager(HibernateTemplate tstratDamperTemplate) throws DataAccessException {
        this.tstratDamperTemplate = tstratDamperTemplate;
    }

    public void generateDamperTestWorkBook(OutputStream os) throws Exception {

        WritableWorkbook wb = Workbook.createWorkbook(os);
        WritableSheet sheet = wb.createSheet("First Sheet", 0);
        Label label = new Label(0, 2, "A label record");
        sheet.addCell(label);

        Number number = new Number(3, 4, 3.1459);
        sheet.addCell(number);

        // All sheets and cells added. Now write out the workbook
        wb.write();
        wb.close();

    }
}
