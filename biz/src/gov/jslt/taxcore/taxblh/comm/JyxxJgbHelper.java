package gov.jslt.taxcore.taxblh.comm;

import java.sql.SQLException;

public class JyxxJgbHelper {

    public static String queryJyxxx(String jyZtMc, String sjNr)
            throws SQLException {
        String temp = "";
        if (null != sjNr) {
            if ("$".equals(jyZtMc)) {
                return sjNr;
            } else {
                for (int j = 0; j < jyZtMc.split("\\$").length; j++) {
                    if (j < sjNr.split("\\$").length) {
                        temp = temp + jyZtMc.split("\\$")[j]
                                + sjNr.split("\\$")[j];
                    } else {
                        temp = temp + jyZtMc.split("\\$")[j];
                    }
                }
                return temp.toString();
            }
        }
        return jyZtMc;
    }

}
