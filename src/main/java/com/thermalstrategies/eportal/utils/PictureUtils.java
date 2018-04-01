/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thermalstrategies.eportal.utils;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author vmurry
 */
public class PictureUtils {

    public static InputStream scaleImage(InputStream is) throws Exception {

        RandomGUID rnd = new RandomGUID();
        File tmpFile = File.createTempFile(rnd.toString(), "tmp");
        writeToFile(is, tmpFile);
        return new FileInputStream(tmpFile);
    }

    private static void writeToFile(InputStream is, File file) throws Exception {

        DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
        InputStream is2 = is;
        boolean again = true;
        while (again) {
            if (is2.read() > -1) {
                out.writeByte(is.read());
            } else {
                again = false;
            }
        }
        is.close();
        out.close();


    }
}
