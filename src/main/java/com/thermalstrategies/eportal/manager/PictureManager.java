/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thermalstrategies.eportal.manager;

import com.thermalstrategies.eportal.model.Pictures;
import com.thermalstrategies.eportal.model.Repairhistory;
import com.thermalstrategies.eportal.security.EPortalSecurityContext;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 *
 * @author smurry
 */
public class PictureManager {

    private HibernateTemplate tstratDamperTemplate;

    public PictureManager(HibernateTemplate tstratDamperTemplate) {
        this.tstratDamperTemplate = tstratDamperTemplate;
    }

    public void picture(OutputStream out, Integer Id) {
        Pictures pictures = (Pictures) tstratDamperTemplate.load(Pictures.class, Id);
        Blob blob = pictures.getImage();
        InputStream in = null;
        try {
            in = blob.getBinaryStream();
            int c;
            while ((c = in.read()) != -1) {
                out.write(c);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                }

            }
            if (out != null) {
                try {
                    out.close();
                } catch (Exception e) {
                }
            }
        }

    }

    public List<Integer> getPictureIdList(Integer damperId) {
        StringBuffer hql = new StringBuffer();
        hql.append("Select p.id  ");
        hql.append(" from Pictures p \n");
        hql.append("   inner join p.dampertest as d \n");
        hql.append("   where d.id=" + damperId);

        return tstratDamperTemplate.find(hql.toString());

    }

    public void deletePicture(Integer pictureId)throws SQLException{
        if ("Customer".equalsIgnoreCase(EPortalSecurityContext.getRole().getRole())) {
            throw new SQLException("You don't have permissions to delete this damper.  Contact your administrator.");
        }
        Pictures picture = (Pictures)tstratDamperTemplate.load(Pictures.class, pictureId);
        tstratDamperTemplate.delete(picture);
    }
}
