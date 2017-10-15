package com.bookstore.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.servlet.http.Part;
import org.apache.commons.io.IOUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

public abstract class UtilsBean {

    public static Long getId(FacesContext fc, String key) {

        ExternalContext ex = fc.getExternalContext();

        String id = ex.getRequestParameterMap().get(key);
        if (id == null) {
            return null;
        }

        return Long.parseLong(id);
    }

    public static byte[] getByteArray(Part p) {

        byte[] array = {};
        try {
            array = IOUtils.toByteArray(p.getInputStream());
        } catch (IOException ex) {
            System.err.println(ex);
        }

        return array;
    }

    public static StreamedContent getImage(FacesContext fc, byte[] arr) {

        if (fc.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            return new DefaultStreamedContent();
        } else {
            return new DefaultStreamedContent(new ByteArrayInputStream(arr));
        }
    }
}
